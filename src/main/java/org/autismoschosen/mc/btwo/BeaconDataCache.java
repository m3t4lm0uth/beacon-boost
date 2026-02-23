package org.autismoschosen.mc.btwo;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.autismoschosen.mc.btwo.mixin.BeaconBlockEntityAccessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeaconDataCache {

    public static final class BeaconSnapshot {
        public final int extendedRange;
        public final int levels;          // vanilla beaconLevel
        public final boolean active;
        public final Identifier primaryId;
        public final Identifier secondaryId;

        public BeaconSnapshot(
                int extendedRange,
                int levels,
                boolean active,
                Identifier primaryId,
                Identifier secondaryId
        ) {
            this.extendedRange = extendedRange;
            this.levels = levels;
            this.active = active;
            this.primaryId = primaryId;
            this.secondaryId = secondaryId;
        }

        public RegistryEntry<StatusEffect> getPrimaryEntry() {
            if (primaryId == null) return null;
            StatusEffect effect = Registries.STATUS_EFFECT.get(primaryId);
            if (effect == null) return null;
            return Registries.STATUS_EFFECT.getEntry(effect);
        }

        public RegistryEntry<StatusEffect> getSecondaryEntry() {
            if (secondaryId == null) return null;
            StatusEffect effect = Registries.STATUS_EFFECT.get(secondaryId);
            if (effect == null) return null;
            return Registries.STATUS_EFFECT.getEntry(effect);
        }
    }

    private static final Map<BeaconRegistry.BeaconKey, BeaconSnapshot> SNAPSHOTS = new ConcurrentHashMap<>();

    // take beaconLevel from vanilla
    public static void updateSnapshot(ServerWorld world, BlockPos pos, ExtendedBeaconData ext, int beaconLevel) {
        BeaconRegistry.BeaconKey key = new BeaconRegistry.BeaconKey(world, pos.toImmutable());

        Identifier primaryId = null;
        Identifier secondaryId = null;

        if (world.getBlockEntity(pos) instanceof BeaconBlockEntity beacon) {
            BeaconBlockEntityAccessor accessor = (BeaconBlockEntityAccessor) beacon;
            RegistryEntry<StatusEffect> primary = accessor.beaconboost$getPrimary();
            RegistryEntry<StatusEffect> secondary = accessor.beaconboost$getSecondary();

            if (primary != null && primary.hasKeyAndValue()) {
                primaryId = primary.getKey().get().getValue();
            }
            if (secondary != null && secondary.hasKeyAndValue()) {
                secondaryId = secondary.getKey().get().getValue();
            }
        }

        SNAPSHOTS.put(key, new BeaconSnapshot(
                ext.beaconboost$getExtendedRange(),
                beaconLevel,                       // USE vanilla beaconLevel
                ext.beaconboost$isActive(),
                primaryId,
                secondaryId
        ));
    }

    public static BeaconSnapshot getSnapshot(ServerWorld world, BlockPos pos) {
        return SNAPSHOTS.get(new BeaconRegistry.BeaconKey(world, pos.toImmutable()));
    }

    public static void remove(ServerWorld world, BlockPos pos) {
        SNAPSHOTS.remove(new BeaconRegistry.BeaconKey(world, pos.toImmutable()));
    }
}

