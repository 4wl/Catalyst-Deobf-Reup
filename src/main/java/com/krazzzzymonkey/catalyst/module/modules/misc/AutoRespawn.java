/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoRespawn extends Modules
{
    public AutoRespawn() {
        super("AutoRespawn", ModuleCategory.MISC, "Automatically respawns player");
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (Minecraft.getMinecraft().player.isDead || Minecraft.getMinecraft().player.getHealth() < 0.0f) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
            Minecraft.getMinecraft().player.respawnPlayer();
        }
        if (Minecraft.getMinecraft().currentScreen instanceof GuiGameOver) {
            Minecraft.getMinecraft().player.respawnPlayer();
        }
    }
}
