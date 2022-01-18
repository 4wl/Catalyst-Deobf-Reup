/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.account;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.managers.accountManager.tools.EncryptionTools;
import com.krazzzzymonkey.catalyst.managers.accountManager.alt.AccountData;
import com.krazzzzymonkey.catalyst.managers.accountManager.alt.AltDatabase;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiScreen;

public abstract class AbstractAccountGui extends GuiScreen
{
    public GuiTextField username;
    public GuiButton complete;
    public boolean hasUserChanged;
    public GuiTextField password;
    public String actionString;
    
    public void escape() {
        this.mc.displayGuiScreen((GuiScreen)new GuiAccountSelector());
    }
    
    public String getPassword() {
        return this.password.getText();
    }
    
    public abstract void complete();
    
    public void setUsername(final String text) {
        this.username.setText(text);
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(this.complete = new GuiButton(2, this.width / 2 - 152, this.height - 28, 150, 20, I18n.format(this.actionString, new Object[0])));
        this.buttonList.add(new GuiButton(3, this.width / 2 + 2, this.height - 28, 150, 20, I18n.format("gui.cancel", new Object[0])));
        (this.username = new GuiTextField(0, this.fontRenderer, this.width / 2 - 100, 60, 200, 20)).setFocused(true);
        this.username.setMaxStringLength(64);
        (this.password = new GuiPasswordField(1, this.fontRenderer, this.width / 2 - 100, 90, 200, 20)).setMaxStringLength(64);
        this.complete.enabled = false;
    }
    
    public AbstractAccountGui(final String actionString) {
        this.hasUserChanged = false;
        this.actionString = actionString;
    }
    
    public void setPassword(final String text) {
        this.password.setText(text);
    }
    
    public void keyTyped(final char c, final int n) {
        if (n == 1) {
            this.escape();
        }
        else if (n == 28) {
            if (this.username.isFocused()) {
                this.username.setFocused(false);
                this.password.setFocused(true);
            }
            else if (this.password.isFocused() && this.complete.enabled) {
                this.complete();
                this.escape();
            }
        }
        else if (n == 15) {
            this.username.setFocused(!this.username.isFocused());
            this.password.setFocused(!this.password.isFocused());
        }
        else {
            this.username.textboxKeyTyped(c, n);
            this.password.textboxKeyTyped(c, n);
            if (this.username.isFocused()) {
                this.hasUserChanged = true;
            }
        }
    }
    
    public boolean canComplete() {
        return this.getUsername().length() > 0 && this.accountNotInList();
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        this.username.mouseClicked(n, n2, n3);
        this.password.mouseClicked(n, n2, n3);
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.format(this.actionString, new Object[0]), this.width / 2, 7, -1);
        this.drawCenteredString(this.fontRenderer, "Username: ", this.width / 2 - 130, 66, -1);
        this.drawCenteredString(this.fontRenderer, I18n.format("Password: ", new Object[0]), this.width / 2 - 130, 96, -1);
        this.username.drawTextBox();
        this.password.drawTextBox();
        super.drawScreen(n, n2, n3);
    }
    
    public void actionPerformed(final GuiButton guiButton) {
        if (guiButton.enabled) {
            if (guiButton.id == 2) {
                this.complete();
                this.escape();
            }
            else if (guiButton.id == 3) {
                this.escape();
            }
        }
    }
    
    public String getUsername() {
        return this.username.getText();
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
    
    public boolean accountNotInList() {
        final Iterator<AccountData> iterator = AltDatabase.getInstance().getAlts().iterator();
        while (iterator.hasNext()) {
            if (EncryptionTools.decode(iterator.next().user).equals(this.getUsername())) {
                return false;
            }
        }
        return true;
    }
    
    public void updateScreen() {
        this.username.updateCursorCounter();
        this.password.updateCursorCounter();
        this.complete.enabled = this.canComplete();
    }
}
