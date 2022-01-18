/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import java.util.Iterator;
import org.lwjgl.input.Keyboard;
import com.krazzzzymonkey.catalyst.command.Command;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.managers.CommandManager;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.events.CommandEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiChat;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.GuiScreen;

@Mixin({ GuiChat.class })
public abstract class MixinGuiChat extends GuiScreen
{
    String predictedCommand;
    @Shadow
    protected GuiTextField inputField;
    
    @Inject(method = { "drawScreen" }, at = { @At("HEAD") })
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks, final CallbackInfo ci) {
        CommandEvent.inputField = this.inputField.getText();
        if (this.inputField.getText().isEmpty()) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Use " + CommandManager.prefix + "help for Catalyst commands.", 4.0f, (float)(this.height - 12), -1);
        }
        if (this.inputField.getText().startsWith(CommandManager.prefix)) {
            RenderUtils.drawBorderedRect(2.0, this.height - 14, this.width - 2, this.height - 2, 2.0f, ColorUtils.rainbow().getRGB(), new Color(0, 0, 0, 0).getRGB());
            if (this.inputField.getText().length() > 1) {
                for (final Command command : CommandManager.getInstance().getCommands()) {
                    if ((CommandManager.prefix + command.getCommand()).contains(this.inputField.getText())) {
                        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(CommandManager.prefix + command.getSyntax(), 4.0f, (float)(this.height - 12), -1);
                        this.predictedCommand = command.getCommand();
                        break;
                    }
                }
                if (Keyboard.isKeyDown(15) && !this.inputField.getText().contains(" ")) {
                    this.inputField.setText(CommandManager.prefix + this.predictedCommand);
                }
            }
        }
    }
}
