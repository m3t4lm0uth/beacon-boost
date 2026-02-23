package org.autismoschosen.mc.btwo.mixin;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.autismoschosen.mc.btwo.BeaconRegistry;
import org.autismoschosen.mc.btwo.BeaconDataCache; // *** NEW
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityLifecycleMixin {

	@Inject(method = "setWorld", at = @At("TAIL"))
	private void beaconboost$onSetWorld(World world, CallbackInfo ci) {
		if (world instanceof ServerWorld serverWorld) {
			BeaconBlockEntity self = (BeaconBlockEntity) (Object) this;
			BeaconRegistry.addBeacon(serverWorld, self.getPos());
		}
	}

	@Inject(method = "markRemoved", at = @At("HEAD"))
	private void beaconboost$onRemoved(CallbackInfo ci) {
		BeaconBlockEntity self = (BeaconBlockEntity) (Object) this;
		World world = self.getWorld();
		if (world instanceof ServerWorld serverWorld) {
			BeaconRegistry.removeBeacon(serverWorld, self.getPos());
			BeaconDataCache.remove(serverWorld, self.getPos()); // *** NEW
		}
	}
}
