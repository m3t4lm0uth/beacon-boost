package org.autismoschosen.mc.btwo.mixin;

import java.util.function.BooleanSupplier;

import net.minecraft.server.MinecraftServer;
import org.autismoschosen.mc.btwo.BeaconEffectManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerBeaconTickMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void beaconboost$tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        MinecraftServer server = (MinecraftServer)(Object)this;
        BeaconEffectManager.tick(server);
    }
}
