/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.actions;

import com.krazzzzymonkey.catalyst.gui.GuiCustom;

public class ActionQuit implements IAction
{
    @Override
    public void perform(final Object source, final GuiCustom menu) {
        menu.mc.shutdown();
    }
}
