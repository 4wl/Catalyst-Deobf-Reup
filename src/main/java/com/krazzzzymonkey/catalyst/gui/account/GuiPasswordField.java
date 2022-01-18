/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.account;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import joptsimple.internal.Strings;
import net.minecraft.client.gui.GuiTextField;

public class GuiPasswordField extends GuiTextField
{
    public void drawTextBox() {
        final String text = this.getText();
        this.replaceText(Strings.repeat('*', this.getText().length()));
        super.drawTextBox();
        this.replaceText(text);
    }
    
    public boolean mouseClicked(final int n, final int n2, final int n3) {
        final String text = this.getText();
        this.replaceText(Strings.repeat('*', this.getText().length()));
        super.mouseClicked(n, n2, n3);
        this.replaceText(text);
        return true;
    }
    
    public void replaceText(final String text) {
        final int cursorPosition = this.getCursorPosition();
        final int selectionEnd = this.getSelectionEnd();
        this.setText(text);
        this.setCursorPosition(cursorPosition);
        this.setSelectionPos(selectionEnd);
    }
    
    public boolean textboxKeyTyped(final char c, final int n) {
        return !GuiScreen.isKeyComboCtrlC(n) && !GuiScreen.isKeyComboCtrlX(n) && super.textboxKeyTyped(c, n);
    }
    
    public GuiPasswordField(final int n, final FontRenderer fontRenderer, final int n2, final int n3, final int n4, final int n5) {
        super(n, fontRenderer, n2, n3, n4, n5);
    }
}
