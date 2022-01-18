/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.actions;

import com.krazzzzymonkey.catalyst.lib.StringReplacer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import com.krazzzzymonkey.catalyst.gui.GuiCustomConfirmOpenLink;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.gui.GuiCustom;

public class ActionOpenLink implements IAction
{
    String link;
    
    public ActionOpenLink(final String link) {
        this.link = link;
    }
    
    @Override
    public void perform(final Object source, final GuiCustom menu) {
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiCustomConfirmOpenLink((GuiYesNoCallback)menu, this.getLink(), -1, false));
        menu.beingChecked = source;
    }
    
    public String getLink() {
        return StringReplacer.replacePlaceholders(this.link);
    }
}
