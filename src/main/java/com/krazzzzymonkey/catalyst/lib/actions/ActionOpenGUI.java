/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.actions;

import net.minecraft.client.gui.GuiWinGame;
import com.google.common.util.concurrent.Runnables;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.gui.GuiSnooper;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.gui.account.GuiAccountSelector;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.GuiModList;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.gui.GuiCustom;

public class ActionOpenGUI implements IAction
{
    String guiName;
    
    public ActionOpenGUI(final String guiName) {
        this.guiName = guiName;
    }
    
    @Override
    public void perform(final Object source, final GuiCustom menu) {
        GuiScreen gui = null;
        if (this.guiName.startsWith("custom.")) {
            final String customName = this.guiName.substring(7);
            gui = Main.config.getGUI(customName);
        }
        else {
            if (this.guiName.equalsIgnoreCase("mods")) {
                gui = (GuiScreen)new GuiModList((GuiScreen)menu);
            }
            if (this.guiName.equalsIgnoreCase("clickGui")) {
                Wrapper.INSTANCE.mc().displayGuiScreen((GuiScreen)new GuiAccountSelector());
            }
            if (this.guiName.equalsIgnoreCase("account")) {
                Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiAccountSelector());
            }
            else if (this.guiName.equalsIgnoreCase("singleplayer")) {
                gui = (GuiScreen)new GuiWorldSelection((GuiScreen)menu);
            }
            else if (this.guiName.equalsIgnoreCase("singleplayer.createworld")) {
                gui = (GuiScreen)new GuiCreateWorld((GuiScreen)menu);
            }
            else if (this.guiName.equalsIgnoreCase("multiplayer")) {
                gui = (GuiScreen)new GuiMultiplayer((GuiScreen)menu);
            }
            else if (this.guiName.equalsIgnoreCase("options")) {
                gui = (GuiScreen)new GuiOptions((GuiScreen)menu, menu.mc.gameSettings);
            }
            else if (this.guiName.equalsIgnoreCase("languages")) {
                gui = (GuiScreen)new GuiLanguage((GuiScreen)menu, menu.mc.gameSettings, menu.mc.getLanguageManager());
            }
            else if (this.guiName.equalsIgnoreCase("options.ressourcepacks")) {
                gui = (GuiScreen)new GuiScreenResourcePacks((GuiScreen)menu);
            }
            else if (this.guiName.equalsIgnoreCase("options.snooper")) {
                gui = (GuiScreen)new GuiSnooper((GuiScreen)menu, menu.mc.gameSettings);
            }
            else if (this.guiName.equalsIgnoreCase("options.sounds")) {
                gui = (GuiScreen)new GuiScreenOptionsSounds((GuiScreen)menu, menu.mc.gameSettings);
            }
            else if (this.guiName.equalsIgnoreCase("options.skinsettings")) {
                gui = (GuiScreen)new GuiCustomizeSkin((GuiScreen)menu);
            }
            else if (this.guiName.equalsIgnoreCase("options.video")) {
                gui = (GuiScreen)new GuiVideoSettings((GuiScreen)menu, menu.mc.gameSettings);
            }
            else if (this.guiName.equalsIgnoreCase("options.controls")) {
                gui = (GuiScreen)new GuiControls((GuiScreen)menu, menu.mc.gameSettings);
            }
            else if (this.guiName.equalsIgnoreCase("options.multiplayer")) {
                gui = (GuiScreen)new ScreenChatOptions((GuiScreen)menu, menu.mc.gameSettings);
            }
            else if (this.guiName.equalsIgnoreCase("mainmenu")) {
                gui = (GuiScreen)new GuiMainMenu();
            }
            else if (this.guiName.equalsIgnoreCase("realms")) {
                final RealmsBridge realmsbridge = new RealmsBridge();
                realmsbridge.switchToRealms(Minecraft.getMinecraft().currentScreen);
            }
            else if (this.guiName.equalsIgnoreCase("credits")) {
                gui = (GuiScreen)new GuiWinGame(false, Runnables.doNothing());
            }
        }
        if (gui != null) {
            Minecraft.getMinecraft().displayGuiScreen(gui);
        }
    }
}
