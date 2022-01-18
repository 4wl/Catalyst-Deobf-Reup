/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.actions;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiMainMenu;
import org.lwjgl.input.Keyboard;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.gui.GuiCustom;

public class ActionRefresh implements IAction
{
    @Override
    public void perform(final Object source, final GuiCustom menu) {
        Main.INSTANCE.reload();
        if (Keyboard.isKeyDown(42)) {
            menu.mc.refreshResources();
        }
        menu.mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
    }
}
