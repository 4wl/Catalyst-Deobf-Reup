/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.account;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import org.apache.commons.lang3.StringUtils;
import com.krazzzzymonkey.catalyst.managers.accountManager.ExtendedAccountData;
import net.minecraft.client.gui.GuiSlot;

public class GuiAccountSelector$List extends GuiSlot
{
    public GuiAccountSelector this$0;
    
    public void elementClicked(final int n, final boolean b, final int n2, final int n3) {
        GuiAccountSelector.access$102(this.this$0, n);
        GuiAccountSelector.access$200(this.this$0);
        if (b && GuiAccountSelector.access$300(this.this$0).enabled) {
            GuiAccountSelector.access$400(this.this$0, n);
        }
    }
    
    public void drawSlot(final int index, final int n, final int n2, final int n3, final int n4, final int n5, final float n6) {
        final ExtendedAccountData extendedAccountData = GuiAccountSelector.access$000(this.this$0).get(index);
        String s = extendedAccountData.alias;
        if (StringUtils.isEmpty((CharSequence)s)) {
            s = I18n.format("ias.alt", new Object[0]) + " " + (index + 1);
        }
        int n7 = 16777215;
        if (Minecraft.getMinecraft().getSession().getUsername().equals(extendedAccountData.alias)) {
            n7 = 65280;
        }
        this.this$0.drawString(GuiAccountSelector.access$500(this.this$0), s, (n ^ 0x2) - 1, n2 + 1, n7);
    }
    
    public int getSize() {
        return GuiAccountSelector.access$000(this.this$0).size();
    }
    
    public int getContentHeight() {
        return GuiAccountSelector.access$000(this.this$0).size() * 14;
    }
    
    public void drawBackground() {
        this.this$0.drawDefaultBackground();
    }
    
    public GuiAccountSelector$List(final GuiAccountSelector this$0, final Minecraft minecraft) {
        this.this$0 = this$0;
        super(minecraft, this$0.width, this$0.height, 32, this$0.height - 64, 14);
    }
    
    public boolean isSelected(final int n) {
        return n == GuiAccountSelector.access$100(this.this$0);
    }
}
