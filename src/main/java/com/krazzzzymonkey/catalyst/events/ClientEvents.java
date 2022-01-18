/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.events;

import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.client.event.GuiScreenEvent$InitGuiEvent$Post;
import com.krazzzzymonkey.catalyst.gui.account.GuiAccountSelector;
import com.krazzzzymonkey.catalyst.managers.accountManager.Config;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent;
import net.minecraft.client.gui.GuiScreen;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.Main;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;

public class ClientEvents
{
    @SubscribeEvent
    public void configChanged(final ConfigChangedEvent configChangedEvent) {
        if (configChangedEvent.getModID().equals("catalyst")) {
            Main.syncConfig();
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent$RenderTickEvent tickEvent$RenderTickEvent) {
        final GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
        if (currentScreen instanceof GuiMultiplayer) {
            currentScreen.drawString(Minecraft.getMinecraft().fontRenderer, "Logged in as: " + Minecraft.getMinecraft().getSession().getUsername(), 2, 2, -1);
            if (Minecraft.getMinecraft().getSession().getToken().equals("0")) {
                currentScreen.drawString(Minecraft.getMinecraft().fontRenderer, "Non Premium", 2, 16, ColorUtils.color(247, 77, 77, 255));
            }
        }
    }
    
    @SubscribeEvent
    public void onClick(final GuiScreenEvent$ActionPerformedEvent guiScreenEvent$ActionPerformedEvent) {
        if (guiScreenEvent$ActionPerformedEvent.getGui() instanceof GuiMainMenu && guiScreenEvent$ActionPerformedEvent.getButton().id == 20) {
            if (Config.getInstance() == null) {
                Config.load();
            }
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiAccountSelector());
        }
    }
    
    @SubscribeEvent
    public void guiEvent(final GuiScreenEvent$InitGuiEvent$Post guiScreenEvent$InitGuiEvent$Post) {
        final GuiScreen gui = guiScreenEvent$InitGuiEvent$Post.getGui();
        if (gui instanceof GuiMainMenu) {
            guiScreenEvent$InitGuiEvent$Post.getButtonList().add(new GuiButton(20, gui.width / 2 - 100, gui.height / 4 + 48 + 72 + 12 + 25, 200, 20, "Catalyst Account Manager"));
        }
    }
}
