/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui;

import net.minecraft.client.Minecraft;
import java.util.List;
import net.minecraft.client.gui.GuiMainMenu;

public class GuiFakeMain extends GuiMainMenu
{
    public List getButtonList() {
        return this.buttonList;
    }
    
    public void initGui() {
    }
    
    public GuiFakeMain() {
        this.mc = Minecraft.getMinecraft();
    }
}
