package net.chand.reconnect.button;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.network.ServerInfo;

public class ReconnectButtonMod implements ClientModInitializer {
    public static ServerInfo lastServer;

    @Override
    public void onInitializeClient() {
        // Initialization code
    }
}