/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.gui;

import net.minecraft.client.gui.GuiScreen;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.managers.FileManager;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;

public class HudEditor extends Modules
{
    public HudEditor() {
        super("HudEditor", ModuleCategory.GUI, "Allows you to set position of hud elements");
    }
    
    @Override
    public void onEnable() {
        FileManager.saveHacks();
        Wrapper.INSTANCE.mc().displayGuiScreen((GuiScreen)Main.moduleManager.getHudGui());
        super.onEnable();
        this.toggle();
    }
}
