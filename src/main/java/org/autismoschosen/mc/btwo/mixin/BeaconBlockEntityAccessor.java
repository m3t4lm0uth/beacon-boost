package org.autismoschosen.mc.btwo.mixin;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BeaconBlockEntity.class)
public interface BeaconBlockEntityAccessor {

    @Accessor("primary")
    @Nullable
    RegistryEntry<StatusEffect> beaconboost$getPrimary();

    @Accessor("secondary")
    @Nullable
    RegistryEntry<StatusEffect> beaconboost$getSecondary();
}

