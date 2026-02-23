package org.autismoschosen.mc.btwo.mixin;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.autismoschosen.mc.btwo.ExtendedBeaconData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockState;

import java.util.List;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityVanillaActiveMixin {

	@Shadow
	int level;
	@Shadow
	List<BeaconBlockEntity.BeamSegment> beamSegments;

	@Inject(method = "tick(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/block/entity/BeaconBlockEntity;)V", at = @At("TAIL"))
	private static void beaconboost$afterTick(
			World world,
			BlockPos pos,
			BlockState state,
			BeaconBlockEntity beacon,
			CallbackInfo ci) {
		if (world == null || world.isClient()) {
			return;
		}

		if (!(beacon instanceof ExtendedBeaconData ext)) {
			return;
		}

		// Beacon is "vanilla active" exactly when level > 0 and beamSegments is
		// non-empty,
		// matching the condition used to call applyPlayerEffects in
		// BeaconBlockEntity.tick.
		BeaconBlockEntityVanillaActiveMixin self = (BeaconBlockEntityVanillaActiveMixin) (Object) beacon;
		boolean vanillaActive = self.level > 0 && !self.beamSegments.isEmpty();

		ext.beaconboost$setVanillaActive(vanillaActive);
	}
}
