package net.chand.reconnect.button.mixin;

import net.chand.reconnect.button.ReconnectButtonMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin {

    // Using a wildcard (*) for the descriptor helps if the mapping is slightly off
    @Inject(method = "connect", at = @At("HEAD"), remap = true)
    private static void captureServerData(Screen screen, MinecraftClient client, ServerAddress address, ServerInfo info, boolean quickPlay, Object reporterEnvironment, CallbackInfo ci) {
        if (info != null) {
            ReconnectButtonMod.lastServer = info;
        }
    }
}