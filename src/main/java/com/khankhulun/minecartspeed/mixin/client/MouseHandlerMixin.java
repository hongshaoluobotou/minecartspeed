package com.khankhulun.minecartspeed.mixin.client;

import com.khankhulun.minecartspeed.network.SetMinecartSpeedPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MouseHandler.class)
public abstract class MouseHandlerMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "onScroll", at = @At("HEAD"), cancellable = true)
    private void minecartspeed$onScroll(long handle, double xoffset, double yoffset, CallbackInfo ci) {
        if (handle != this.minecraft.getWindow().handle()) {
            return;
        }
        if (this.minecraft.gui.screen() != null || this.minecraft.gui.overlay() != null || this.minecraft.player == null) {
            return;
        }
        if (!(this.minecraft.player.getVehicle() instanceof AbstractMinecart)) {
            return;
        }

        int direction = (int) Math.signum(yoffset);
        if (direction == 0) {
            return;
        }

        ClientPlayNetworking.send(new SetMinecartSpeedPayload(direction));
        ci.cancel();
    }
}
