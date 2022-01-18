/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiYesNo;

public class GuiCustomConfirmOpenLink extends GuiYesNo
{
    public String openLinkWarning;
    public String linkText;
    public static String __OBFID;
    public String copyLinkButtonText;
    public boolean showSecurityWarning;
    
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
        if (this.showSecurityWarning) {
            this.drawCenteredString(this.fontRenderer, this.openLinkWarning, this.width / 2, 110, 16764108);
        }
    }
    
    public void actionPerformed(final GuiButton guiButton) {
        if (guiButton.id == 2) {
            this.copyLinkToClipboard();
        }
        this.parentScreen.confirmClicked(guiButton.id == 0, this.parentButtonClickedId);
    }
    
    public void initGui() {
        super.initGui();
        this.buttonList.remove(0);
        this.buttonList.remove(0);
        this.buttonList.add(new GuiButton(0, this.width / 2 - 50 - 105, this.height / 6 + 96, 100, 20, this.confirmButtonText));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 50, this.height / 6 + 96, 100, 20, this.copyLinkButtonText));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 50 + 105, this.height / 6 + 96, 100, 20, this.cancelButtonText));
    }
    
    public GuiCustomConfirmOpenLink(final GuiYesNoCallback guiYesNoCallback, final String linkText, final int n, final boolean b) {
        super(guiYesNoCallback, I18n.format(b ? "chat.link.confirmTrusted" : "chat.link.confirm", new Object[0]), linkText, n);
        this.showSecurityWarning = true;
        this.confirmButtonText = I18n.format(b ? "chat.link.open" : "gui.yes", new Object[0]);
        this.cancelButtonText = I18n.format(b ? "gui.cancel" : "gui.no", new Object[0]);
        this.copyLinkButtonText = I18n.format("chat.copy", new Object[0]);
        this.openLinkWarning = I18n.format("chat.link.warning", new Object[0]);
        this.linkText = linkText;
    }
    
    public void disableSecurityWarning() {
        this.showSecurityWarning = false;
    }
    
    static {
        GuiCustomConfirmOpenLink.__OBFID = "CL_00000683";
    }
    
    public void copyLinkToClipboard() {
        setClipboardString(this.linkText);
    }
}
