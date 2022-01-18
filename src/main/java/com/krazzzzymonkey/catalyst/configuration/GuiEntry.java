/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.configuration;

import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.gui.GuiCustom;

public class GuiEntry
{
    public GuiCustom standard;
    public GuiCustom small;
    public GuiCustom auto;
    public GuiCustom normal;
    public GuiCustom large;
    
    public GuiCustom getCurrentGUI() {
        final int guiScale = Minecraft.getMinecraft().gameSettings.guiScale;
        if (guiScale == 0 && this.auto != null) {
            return this.auto;
        }
        if (guiScale == 1) {
            if (this.small != null) {
                return this.small;
            }
        }
        if (guiScale == 2 && this.normal != null) {
            return this.normal;
        }
        if (guiScale == 3 && this.large != null) {
            return this.large;
        }
        return this.standard;
    }
}
