package org.autismoschosen.mc.btwo.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import org.autismoschosen.mc.btwo.ExtendedBeaconData;
import org.autismoschosen.mc.btwo.BeaconDataCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityBaseMixin {

	 // After vanilla updates its level / beam / base info, compute extended range.

	@Inject(method = "updateLevel(Lnet/minecraft/world/World;III)I", at = @At("TAIL"))
	private static void beaconboost$afterUpdateLevel(World world, int x, int y, int z,
			CallbackInfoReturnable<Integer> cir) {
		if (world == null)
			return;

		BlockPos pos = new BlockPos(x, y, z);

		if (!(world.getBlockEntity(pos) instanceof BeaconBlockEntity beacon)) {
			return;
		}
		if (!(beacon instanceof ExtendedBeaconData ext)) {
			return;
		}

		int[] counts = beaconboost$countBaseBlocks(world, pos);
		ext.beaconboost$setIronBlocks(counts[0]);
		ext.beaconboost$setGoldBlocks(counts[1]);
		ext.beaconboost$setEmeraldBlocks(counts[2]);
		ext.beaconboost$setDiamondBlocks(counts[3]);
		ext.beaconboost$setNetheriteBlocks(counts[4]);

		int levels = beaconboost$estimateLevels(counts);
		int range = beaconboost$computeRange(counts, levels);
		
		ext.beaconboost$setExtendedRange(range);
	}

	private static int[] beaconboost$countBaseBlocks(World world, BlockPos beaconPos) {
		int iron = 0, gold = 0, emerald = 0, diamond = 0, netherite = 0;

		for (int layer = 1; layer <= 4; layer++) {
			BlockPos below = beaconPos.down(layer);
			int r = layer;

			for (int dx = -r; dx <= r; dx++) {
				for (int dz = -r; dz <= r; dz++) {
					BlockPos pos = below.add(dx, 0, dz);
					Block block = world.getBlockState(pos).getBlock();

					if (block == Blocks.IRON_BLOCK)
						iron++;
					else if (block == Blocks.GOLD_BLOCK)
						gold++;
					else if (block == Blocks.EMERALD_BLOCK)
						emerald++;
					else if (block == Blocks.DIAMOND_BLOCK)
						diamond++;
					else if (block == Blocks.NETHERITE_BLOCK)
						netherite++;
				}
			}
		}

		return new int[] { iron, gold, emerald, diamond, netherite };
	}

	// group-of-two math + clamp at 500
	private static int beaconboost$computeRange(int[] c, int levels) {
		int iron = c[0];
		int gold = c[1];
		int emerald = c[2];
		int diamond = c[3];
		int netherite = c[4];

		// groups of 2
		int groupsIron = iron / 2;
		int groupsGold = gold / 2;
		int groupsEmerald = emerald / 2;
		int groupsDiamond = diamond / 2;
		int groupsNetherite = netherite / 2;

		// 1. start from vanilla radius, based on level: 1→20, 2→30, 3→40, 4→50
		int range = 0;
		if (levels >= 1)
			range = levels * 10 + 10;

		// 2. add your weighted groups
		range += groupsIron * 0;
		range += groupsGold * 1;
		range += groupsEmerald * 2;
		range += groupsDiamond * 4;
		range += groupsNetherite * 6;

		// 3. clamp at 500
		if (range > 500) {
			range = 500;
		}

		return range;
	}

	private static int beaconboost$estimateLevels(int[] counts) {
		int total = counts[0] + counts[1] + counts[2] + counts[3] + counts[4];
		if (total >= 164)
			return 4;
		if (total >= 83)
			return 3;
		if (total >= 34)
			return 2;
		if (total >= 9)
			return 1;
		return 0;
	}
}
