package org.autismoschosen.mc.btwo.mixin;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.autismoschosen.mc.btwo.BeaconDataCache;
import org.autismoschosen.mc.btwo.ExtendedBeaconData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityEffectMixin {

    @Inject(
            method = "applyPlayerEffects(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;ILnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/registry/entry/RegistryEntry;)V",
            at = @At("HEAD")
    )
    private static void beaconboost$markActive(
            World world,
            BlockPos pos,
            int beaconLevel,                         // vanilla 0–4 level
            RegistryEntry<StatusEffect> primaryEffect,
            RegistryEntry<StatusEffect> secondaryEffect,
            CallbackInfo ci) {

        if (world == null || world.isClient()) {
            return;
        }

        if (!(world.getBlockEntity(pos) instanceof BeaconBlockEntity beacon)) {
            return;
        }

        if (!(beacon instanceof ExtendedBeaconData ext)) {
            return;
        }

        // This tick vanilla is applying effects: beacon is valid and unblocked.
        ext.beaconboost$setActive(true);

        if (world instanceof ServerWorld serverWorld) {
            // pass vanilla beaconLevel into the snapshot
            BeaconDataCache.updateSnapshot(serverWorld, pos, ext, beaconLevel);
        }
    }
}

