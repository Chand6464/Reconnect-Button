package net.chand.reconnect.button.mixin;

import net.chand.reconnect.button.ReconnectButtonMod;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisconnectedScreen.class)
public abstract class DisconnectedScreenMixin extends Screen {

    protected DisconnectedScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    protected void init(CallbackInfo ci) {
        if (ReconnectButtonMod.lastServer != null && this.client != null) {
            
            this.addDrawableChild(ButtonWidget.builder(Text.literal("Reconnect"), button -> {
                // We use the static method from ConnectScreen
                // Ensure the address is correctly parsed from the stored lastServer
                ServerAddress address = ServerAddress.parse(ReconnectButtonMod.lastServer.address);
                
                ConnectScreen.connect(
                    this, 
                    this.client, 
                    address, 
                    ReconnectButtonMod.lastServer, 
                    false, 
                    null
                );
            })
            .dimensions(this.width / 2 - 100, this.height / 4 + 120 + 24, 200, 20)
            .build());
        }
    }
}