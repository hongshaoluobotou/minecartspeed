package com.khankhulun.minecartspeed.command;

import com.khankhulun.minecartspeed.access.MinecartSpeedAccessor;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;

import java.util.Locale;

public final class MinecartSpeedCommand {
    private static final double MIN_SPEED = 0.1;
    private static final double MAX_SPEED = 10.0;
    private static final SimpleCommandExceptionType NOT_RIDING_MINECART = new SimpleCommandExceptionType(Component.literal("目标玩家当前不在矿车上"));

    private MinecartSpeedCommand() {
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) -> dispatcher.register(
            Commands.literal("minecartspeed")
                .then(Commands.literal("get")
                    .executes(ctx -> executeGet(ctx, getSelf(ctx)))
                    .then(Commands.argument("player", EntityArgument.player())
                        .executes(ctx -> executeGet(ctx, EntityArgument.getPlayer(ctx, "player")))))
                .then(Commands.literal("set")
                    .then(Commands.argument("speed", DoubleArgumentType.doubleArg(MIN_SPEED, MAX_SPEED))
                        .executes(ctx -> executeSet(ctx, getSelf(ctx), DoubleArgumentType.getDouble(ctx, "speed")))
                        .then(Commands.argument("player", EntityArgument.player())
                            .executes(ctx -> executeSet(ctx, EntityArgument.getPlayer(ctx, "player"), DoubleArgumentType.getDouble(ctx, "speed"))))))
                .then(Commands.literal("add")
                    .then(Commands.argument("delta", DoubleArgumentType.doubleArg(-MAX_SPEED, MAX_SPEED))
                        .executes(ctx -> executeAdd(ctx, getSelf(ctx), DoubleArgumentType.getDouble(ctx, "delta")))
                        .then(Commands.argument("player", EntityArgument.player())
                            .executes(ctx -> executeAdd(ctx, EntityArgument.getPlayer(ctx, "player"), DoubleArgumentType.getDouble(ctx, "delta"))))))
        ));
    }

    private static int executeGet(CommandContext<CommandSourceStack> ctx, ServerPlayer player) throws CommandSyntaxException {
        MinecartSpeedAccessor accessor = getAccessor(player);
        double speed = accessor.minecartspeed$getMaxSpeed();
        ctx.getSource().sendSuccess(() -> Component.literal(String.format(Locale.ROOT, "%s 的矿车最大速度: %.1f", player.getScoreboardName(), speed)), false);
        return 1;
    }

    private static int executeSet(CommandContext<CommandSourceStack> ctx, ServerPlayer player, double speed) throws CommandSyntaxException {
        MinecartSpeedAccessor accessor = getAccessor(player);
        accessor.minecartspeed$setMaxSpeed(speed);
        double result = accessor.minecartspeed$getMaxSpeed();
        ctx.getSource().sendSuccess(() -> Component.literal(String.format(Locale.ROOT, "已设置 %s 的矿车最大速度: %.1f", player.getScoreboardName(), result)), true);
        return 1;
    }

    private static int executeAdd(CommandContext<CommandSourceStack> ctx, ServerPlayer player, double delta) throws CommandSyntaxException {
        MinecartSpeedAccessor accessor = getAccessor(player);
        double next = Mth.clamp(accessor.minecartspeed$getMaxSpeed() + delta, MIN_SPEED, MAX_SPEED);
        accessor.minecartspeed$setMaxSpeed(next);
        double result = accessor.minecartspeed$getMaxSpeed();
        ctx.getSource().sendSuccess(() -> Component.literal(String.format(Locale.ROOT, "已调整 %s 的矿车最大速度: %.1f", player.getScoreboardName(), result)), true);
        return 1;
    }

    private static ServerPlayer getSelf(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        return ctx.getSource().getPlayerOrException();
    }

    private static MinecartSpeedAccessor getAccessor(ServerPlayer player) throws CommandSyntaxException {
        if (!(player.getVehicle() instanceof AbstractMinecart minecart) || !(minecart instanceof MinecartSpeedAccessor accessor)) {
            throw NOT_RIDING_MINECART.create();
        }
        return accessor;
    }
}
