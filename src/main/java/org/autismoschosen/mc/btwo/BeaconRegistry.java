package org.autismoschosen.mc.btwo;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BeaconRegistry {

    public static final class BeaconKey {
        public final ServerWorld world;
        public final BlockPos pos;

        public BeaconKey(ServerWorld world, BlockPos pos) {
            this.world = world;
            this.pos = pos;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BeaconKey that)) return false;
            return world == that.world && pos.equals(that.pos);
        }

        @Override
        public int hashCode() {
            return Objects.hash(world, pos);
        }
    }

    private static final Set<BeaconKey> BEACONS = new HashSet<>();

    public static void addBeacon(ServerWorld world, BlockPos pos) {
        BEACONS.add(new BeaconKey(world, pos.toImmutable()));
    }

    public static void removeBeacon(ServerWorld world, BlockPos pos) {
        BEACONS.remove(new BeaconKey(world, pos.toImmutable()));
    }

    public static Set<BeaconKey> getBeacons() {
        return BEACONS;
    }
}
