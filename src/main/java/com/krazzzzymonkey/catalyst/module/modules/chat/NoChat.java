/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;

public class NoChat extends Modules
{
    public NoChat() {
        super("NoChat", ModuleCategory.CHAT, "Stops rendering chat");
    }
    
    @Override
    public void onDisable() {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages(false);
        super.onDisable();
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null) {
                if (Minecraft.getMinecraft().player.getUniqueID() != null) {
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages(true);
                }
            }
        }
    }
}
