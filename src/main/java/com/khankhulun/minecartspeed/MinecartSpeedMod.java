package com.khankhulun.minecartspeed;

import com.khankhulun.minecartspeed.access.MinecartSpeedAccessor;
import com.khankhulun.minecartspeed.command.MinecartSpeedCommand;
import com.khankhulun.minecartspeed.network.SetMinecartSpeedPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public final class MinecartSpeedMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("minecartspeed");

    @Override
    public void onInitialize() {
        MinecartSpeedCommand.register();
        PayloadTypeRegistry.serverboundPlay().register(SetMinecartSpeedPayload.TYPE, SetMinecartSpeedPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SetMinecartSpeedPayload.TYPE, (payload, context) -> {
            if (!(context.player().getVehicle() instanceof AbstractMinecart minecart)) {
                return;
            }
            if (!(minecart instanceof MinecartSpeedAccessor accessor)) {
                return;
            }

            accessor.minecartspeed$changeMaxSpeed(payload.direction());
            double speed = accessor.minecartspeed$getMaxSpeed();
            context.player().sendSystemMessage(Component.literal(String.format(Locale.ROOT, "矿车最大速度: %.1f", speed)), true);
        });
        LOGGER.info("MinecartSpeed mod initialized");
    }
}
