package org.autismoschosen.mc.btwo;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.autismoschosen.mc.btwo.mixin.BeaconBlockEntityAccessor;

public class BeaconEffectManager {

    public static void tick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            for (PlayerEntity player : world.getPlayers()) {
                applyBeaconsToPlayer(world, player);
            }
        }
    }

    private static void applyBeaconsToPlayer(ServerWorld world, PlayerEntity player) {
        Vec3d playerPos = new Vec3d(player.getX(), player.getY(), player.getZ());

        for (BeaconRegistry.BeaconKey key : BeaconRegistry.getBeacons()) {
            if (key.world != world)
                continue;

            BlockPos beaconPos = key.pos;

            BeaconBlockEntity beacon = null;
            BeaconDataCache.BeaconSnapshot snap = BeaconDataCache.getSnapshot(world, beaconPos);
            if (snap == null) {
                continue;
            }

            boolean chunkLoaded = world.isChunkLoaded(beaconPos);
            boolean vanillaActiveNow = false;

            if (chunkLoaded) {
                BlockEntity be = world.getBlockEntity(beaconPos);
                if (be instanceof BeaconBlockEntity b) {
                    beacon = b;
                    if (b instanceof ExtendedBeaconData extLoaded) {
                        vanillaActiveNow = extLoaded.beaconboost$isVanillaActive();
                    }
                }
            } else {
                // When unloaded, fall back to last known vanilla-active snapshot
                vanillaActiveNow = snap.active;
            }

            int beaconLevel = snap.levels;
            if (!vanillaActiveNow || beaconLevel <= 0) {
                continue;
            }

            int range = snap.extendedRange;
            if (range <= 0) {
                continue;
            }

            double dx = playerPos.x - (beaconPos.getX() + 0.5);
            double dz = playerPos.z - (beaconPos.getZ() + 0.5);
            double distSq = dx * dx + dz * dz;
            if (distSq > (double) range * (double) range) {
                continue;
            }

            RegistryEntry<StatusEffect> primary;
            RegistryEntry<StatusEffect> secondary;

            if (beacon != null) {
                BeaconBlockEntityAccessor accessor = (BeaconBlockEntityAccessor) beacon;
                primary = accessor.beaconboost$getPrimary();
                secondary = accessor.beaconboost$getSecondary();
            } else {
                primary = snap.getPrimaryEntry();
                secondary = snap.getSecondaryEntry();
            }

            if (primary == null && secondary == null) {
                continue;
            }

            int duration = (9 + beaconLevel * 2) * 20;
            int amplifier = 0;
            if (beaconLevel >= 4 && primary != null && primary == secondary) {
                amplifier = 1;
            }

            if (primary != null) {
                player.addStatusEffect(
                        new StatusEffectInstance(primary, duration, amplifier, true, true));
            }

            if (beaconLevel >= 4 && secondary != null && secondary != primary) {
                player.addStatusEffect(new StatusEffectInstance(secondary, duration, 0, true, true));
            }
        }
    }
}

