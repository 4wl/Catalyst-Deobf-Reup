/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers.accountManager.config;

import net.minecraftforge.common.config.ConfigElement;
import com.krazzzzymonkey.catalyst.Main;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigGui extends GuiConfig
{
    public ConfigGui(final GuiScreen parentScreen) {
        super(parentScreen, new ConfigElement(Main.altConfig.getCategory("general")).getChildElements(), "catalyst", false, false, GuiConfig.getAbridgedConfigPath(Main.altConfig.toString()));
    }
}
