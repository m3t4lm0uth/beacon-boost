package org.autismoschosen.mc.btwo;

import net.minecraft.util.math.BlockPos;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.entity.effect.StatusEffect;

public interface ExtendedBeaconData {

	void beaconboost$setExtendedRange(int range);

	int beaconboost$getExtendedRange();

	void beaconboost$setIronBlocks(int count);

	int beaconboost$getIronBlocks();

	void beaconboost$setGoldBlocks(int count);

	int beaconboost$getGoldBlocks();

	void beaconboost$setEmeraldBlocks(int count);

	int beaconboost$getEmeraldBlocks();

	void beaconboost$setDiamondBlocks(int count);

	int beaconboost$getDiamondBlocks();

	void beaconboost$setNetheriteBlocks(int count);

	int beaconboost$getNetheriteBlocks();

	BlockPos beaconboost$getPos();

	int beaconboost$getLevels();

	boolean beaconboost$isActive();

	void beaconboost$setActive(boolean active);

	boolean beaconboost$isVanillaActive();

	void beaconboost$setVanillaActive(boolean active);

}
