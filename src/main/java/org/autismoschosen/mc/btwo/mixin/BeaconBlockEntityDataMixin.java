package org.autismoschosen.mc.btwo.mixin;

import org.autismoschosen.mc.btwo.ExtendedBeaconData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.util.math.BlockPos;

@Mixin(BeaconBlockEntity.class)
public class BeaconBlockEntityDataMixin implements ExtendedBeaconData {

	@Unique
	private int beaconboost$extendedRange;
	@Unique
	private int beaconboost$ironBlocks;
	@Unique
	private int beaconboost$goldBlocks;
	@Unique
	private int beaconboost$emeraldBlocks;
	@Unique
	private int beaconboost$diamondBlocks;
	@Unique
	private int beaconboost$netheriteBlocks;

	@Unique
	private boolean beaconboost$active;

	@Unique
	private boolean beaconboost$vanillaActive;

	@Override
	public void beaconboost$setExtendedRange(int range) {
		this.beaconboost$extendedRange = range;
	}

	@Override
	public int beaconboost$getExtendedRange() {
		return this.beaconboost$extendedRange;
	}

	@Override
	public void beaconboost$setIronBlocks(int count) {
		this.beaconboost$ironBlocks = count;
	}

	@Override
	public int beaconboost$getIronBlocks() {
		return this.beaconboost$ironBlocks;
	}

	@Override
	public void beaconboost$setGoldBlocks(int count) {
		this.beaconboost$goldBlocks = count;
	}

	@Override
	public int beaconboost$getGoldBlocks() {
		return this.beaconboost$goldBlocks;
	}

	@Override
	public void beaconboost$setEmeraldBlocks(int count) {
		this.beaconboost$emeraldBlocks = count;
	}

	@Override
	public int beaconboost$getEmeraldBlocks() {
		return this.beaconboost$emeraldBlocks;
	}

	@Override
	public void beaconboost$setDiamondBlocks(int count) {
		this.beaconboost$diamondBlocks = count;
	}

	@Override
	public int beaconboost$getDiamondBlocks() {
		return this.beaconboost$diamondBlocks;
	}

	@Override
	public void beaconboost$setNetheriteBlocks(int count) {
		this.beaconboost$netheriteBlocks = count;
	}

	@Override
	public int beaconboost$getNetheriteBlocks() {
		return this.beaconboost$netheriteBlocks;
	}

	@Override
	public BlockPos beaconboost$getPos() {
		return ((BeaconBlockEntity) (Object) this).getPos();
	}

	@Override
	public int beaconboost$getLevels() {
		int total = beaconboost$ironBlocks + beaconboost$goldBlocks +
				beaconboost$emeraldBlocks + beaconboost$diamondBlocks +
				beaconboost$netheriteBlocks;
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

	@Override
	public boolean beaconboost$isActive() {
		return this.beaconboost$active;
	}

	@Override
	public void beaconboost$setActive(boolean active) {
		this.beaconboost$active = active;
	}

	@Override
	public boolean beaconboost$isVanillaActive() {
		return this.beaconboost$vanillaActive;
	}

	@Override
	public void beaconboost$setVanillaActive(boolean active) {
		this.beaconboost$vanillaActive = active;
	}
}
