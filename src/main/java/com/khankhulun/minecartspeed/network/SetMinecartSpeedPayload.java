package com.khankhulun.minecartspeed.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record SetMinecartSpeedPayload(int direction) implements CustomPacketPayload {
    public static final Type<SetMinecartSpeedPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath("minecartspeed", "set_minecart_speed"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SetMinecartSpeedPayload> CODEC = StreamCodec.composite(
        ByteBufCodecs.VAR_INT,
        SetMinecartSpeedPayload::direction,
        SetMinecartSpeedPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
