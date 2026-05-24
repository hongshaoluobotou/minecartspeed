package com.khankhulun.minecartspeed.mixin;

import com.khankhulun.minecartspeed.access.MinecartSpeedAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecart.class)
public abstract class AbstractMinecartMixin implements MinecartSpeedAccessor {
    @Unique
    private static final double MINECARTSPEED_MIN = 0.1;
    @Unique
    private static final double MINECARTSPEED_MAX = 10.0;
    @Unique
    private static final double MINECARTSPEED_STEP = 0.1;
    @Unique
    private static final double MINECARTSPEED_DEFAULT = 0.4;
    @Unique
    private double minecartspeed$maxSpeed = -1.0;

    @Inject(method = "getMaxSpeed", at = @At("HEAD"), cancellable = true)
    private void minecartspeed$useCustomMaxSpeed(ServerLevel level, CallbackInfoReturnable<Double> cir) {
        if (this.minecartspeed$maxSpeed > 0.0) {
            cir.setReturnValue(this.minecartspeed$maxSpeed);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void minecartspeed$readCustomSpeed(ValueInput input, CallbackInfo ci) {
        double value = input.getDoubleOr("MinecartSpeedMax", -1.0);
        this.minecartspeed$maxSpeed = value > 0.0 ? Mth.clamp(value, MINECARTSPEED_MIN, MINECARTSPEED_MAX) : -1.0;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void minecartspeed$saveCustomSpeed(ValueOutput output, CallbackInfo ci) {
        if (this.minecartspeed$maxSpeed > 0.0) {
            output.putDouble("MinecartSpeedMax", this.minecartspeed$maxSpeed);
        }
    }

    @Override
    public void minecartspeed$changeMaxSpeed(int direction) {
        if (direction == 0) {
            return;
        }

        double current = this.minecartspeed$maxSpeed > 0.0 ? this.minecartspeed$maxSpeed : MINECARTSPEED_DEFAULT;
        this.minecartspeed$maxSpeed = Mth.clamp(current + Math.signum(direction) * MINECARTSPEED_STEP, MINECARTSPEED_MIN, MINECARTSPEED_MAX);
    }

    @Override
    public void minecartspeed$setMaxSpeed(double speed) {
        this.minecartspeed$maxSpeed = Mth.clamp(speed, MINECARTSPEED_MIN, MINECARTSPEED_MAX);
    }

    @Override
    public double minecartspeed$getMaxSpeed() {
        return this.minecartspeed$maxSpeed > 0.0 ? this.minecartspeed$maxSpeed : MINECARTSPEED_DEFAULT;
    }
}
