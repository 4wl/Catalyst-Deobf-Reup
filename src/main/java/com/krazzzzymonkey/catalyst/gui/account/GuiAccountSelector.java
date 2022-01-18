/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.account;

import net.minecraft.client.gui.FontRenderer;
import com.krazzzzymonkey.catalyst.managers.accountManager.tools.EncryptionTools;
import com.krazzzzymonkey.catalyst.managers.accountManager.alt.AltDatabase;
import org.lwjgl.input.Keyboard;
import com.krazzzzymonkey.catalyst.Main;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.managers.accountManager.alt.AccountData;
import com.krazzzzymonkey.catalyst.managers.accountManager.tools.HttpTools;
import com.krazzzzymonkey.catalyst.managers.accountManager.AlreadyLoggedInException;
import com.krazzzzymonkey.catalyst.managers.accountManager.tools.JavaTools;
import com.krazzzzymonkey.catalyst.managers.accountManager.EnumBool;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.managers.accountManager.alt.AltManager;
import com.krazzzzymonkey.catalyst.managers.accountManager.config.ConfigValues;
import net.minecraft.client.resources.I18n;
import com.krazzzzymonkey.catalyst.managers.accountManager.tools.SkinTools;
import com.krazzzzymonkey.catalyst.managers.accountManager.Config;
import net.minecraft.client.gui.GuiTextField;
import com.krazzzzymonkey.catalyst.managers.accountManager.ExtendedAccountData;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiAccountSelector extends GuiScreen
{
    public String query;
    public Throwable loginfailed;
    public GuiButton delete;
    public int prevIndex;
    public GuiButton login;
    public GuiAccountSelector$List accountsgui;
    public GuiScreen Screen;
    public GuiButton reloadskins;
    public ArrayList<ExtendedAccountData> queriedaccounts;
    public GuiButton edit;
    public int selectedAccountIndex;
    public GuiTextField search;
    public GuiButton loginoffline;
    
    public void reloadSkins() {
        Config.save();
        SkinTools.cacheSkins();
        this.updateShownSkin();
    }
    
    public void updateQueried() {
        this.queriedaccounts = (ArrayList<ExtendedAccountData>)this.convertData();
        if (!this.query.equals(I18n.format("ias.search", new Object[0])) && !this.query.equals("")) {
            for (int i = 0; i < this.queriedaccounts.size(); ++i) {
                if (!this.queriedaccounts.get(i).alias.contains(this.query) && ConfigValues.CASESENSITIVE) {
                    this.queriedaccounts.remove(i);
                    --i;
                }
                else if (!this.queriedaccounts.get(i).alias.toLowerCase().contains(this.query.toLowerCase()) && !ConfigValues.CASESENSITIVE) {
                    this.queriedaccounts.remove(i);
                    --i;
                }
            }
        }
        if (!this.queriedaccounts.isEmpty()) {
            while (this.selectedAccountIndex >= this.queriedaccounts.size()) {
                --this.selectedAccountIndex;
            }
        }
    }
    
    public void login(final int index) {
        final ExtendedAccountData extendedAccountData = this.queriedaccounts.get(index);
        this.loginfailed = AltManager.getInstance().setUser(extendedAccountData.user, extendedAccountData.pass);
        if (this.loginfailed == null) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
            final ExtendedAccountData currentAsEditable = this.getCurrentAsEditable();
            currentAsEditable.premium = EnumBool.TRUE;
            final ExtendedAccountData extendedAccountData2 = currentAsEditable;
            ++extendedAccountData2.useCount;
            currentAsEditable.lastused = JavaTools.getJavaCompat().getDate();
        }
        else if (this.loginfailed instanceof AlreadyLoggedInException) {
            this.getCurrentAsEditable().lastused = JavaTools.getJavaCompat().getDate();
        }
        else if (HttpTools.ping("https://minecraft.net")) {
            this.getCurrentAsEditable().premium = EnumBool.FALSE;
        }
    }
    
    public ExtendedAccountData getCurrentAsEditable() {
        for (final AccountData accountData : this.getAccountList()) {
            if (accountData instanceof ExtendedAccountData) {
                if (accountData.equals(this.queriedaccounts.get(this.selectedAccountIndex))) {
                    return (ExtendedAccountData)accountData;
                }
                continue;
            }
        }
        return null;
    }
    
    public void escape() {
        this.mc.displayGuiScreen((GuiScreen)null);
    }
    
    public static void access$200(final GuiAccountSelector guiAccountSelector) {
        guiAccountSelector.updateButtons();
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.accountsgui.drawScreen(n, n2, n3);
        Main.fontRenderer.drawCenteredString("Catalyst Account Manager", (float)(this.width / 2), 4.0f, -1);
        if (this.loginfailed != null) {
            Main.fontRenderer.drawCenteredString(this.loginfailed.getLocalizedMessage(), (float)(this.width / 2), (float)(this.height - 62), 16737380);
        }
        this.search.drawTextBox();
        super.drawScreen(n, n2, n3);
        Main.fontRenderer.drawString("Logged in as: " + Minecraft.getMinecraft().getSession().getUsername(), 3.0f, 3.0f, 6618980);
        if (!this.queriedaccounts.isEmpty()) {
            if (this.queriedaccounts.get(this.selectedAccountIndex).premium == EnumBool.TRUE) {
                Main.fontRenderer.drawString("(Premium)", 3.0f, 15.0f, 6618980);
            }
            else if (this.queriedaccounts.get(this.selectedAccountIndex).premium == EnumBool.FALSE) {
                Main.fontRenderer.drawString("(Non Premium)", 3.0f, 15.0f, 16737380);
            }
        }
    }
    
    public static int access$102(final GuiAccountSelector guiAccountSelector, final int selectedAccountIndex) {
        return guiAccountSelector.selectedAccountIndex = selectedAccountIndex;
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        (this.accountsgui = new GuiAccountSelector$List(this, this.mc)).registerScrollButtons(5, 6);
        this.query = "Look for account";
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 40, this.height - 52, 120, 20, "Add Account"));
        this.buttonList.add(this.login = new GuiButton(1, this.width / 2 - 154 - 10, this.height - 52, 120, 20, "Login"));
        this.buttonList.add(this.edit = new GuiButton(7, this.width / 2 - 40, this.height - 52, 80, 20, "Edit"));
        this.buttonList.add(this.loginoffline = new GuiButton(2, this.width / 2 - 154 - 10, this.height - 28, 110, 20, "Offline Mode"));
        this.buttonList.add(new GuiButton(3, this.width / 2 + 4 + 50, this.height - 28, 110, 20, "Cancel"));
        this.buttonList.add(this.delete = new GuiButton(4, this.width / 2 - 50, this.height - 28, 100, 20, "Delete"));
        (this.search = new GuiTextField(8, this.fontRenderer, this.width / 2 - 80, 14, 160, 16)).setText(this.query);
        this.updateButtons();
        if (!this.queriedaccounts.isEmpty()) {
            SkinTools.buildSkin(this.queriedaccounts.get(this.selectedAccountIndex).alias);
        }
    }
    
    public void updateScreen() {
        this.search.updateCursorCounter();
        this.updateText();
        this.updateButtons();
        if (this.prevIndex != this.selectedAccountIndex) {
            this.updateShownSkin();
            this.prevIndex = this.selectedAccountIndex;
        }
    }
    
    public GuiAccountSelector() {
        this.selectedAccountIndex = 0;
        this.Screen = this;
        this.prevIndex = 0;
        this.queriedaccounts = (ArrayList<ExtendedAccountData>)this.convertData();
    }
    
    public void updateShownSkin() {
        if (!this.queriedaccounts.isEmpty()) {
            SkinTools.buildSkin(this.queriedaccounts.get(this.selectedAccountIndex).alias);
        }
    }
    
    public void delete() {
        AltDatabase.getInstance().getAlts().remove(this.getCurrentAsEditable());
        if (this.selectedAccountIndex > 0) {
            --this.selectedAccountIndex;
        }
        this.updateQueried();
        this.updateButtons();
    }
    
    public static int access$100(final GuiAccountSelector guiAccountSelector) {
        return guiAccountSelector.selectedAccountIndex;
    }
    
    public void updateText() {
        this.search.setText(this.query);
    }
    
    public ArrayList convertData() {
        final ArrayList list = (ArrayList)AltDatabase.getInstance().getAlts().clone();
        final ArrayList<ExtendedAccountData> list2 = new ArrayList<ExtendedAccountData>();
        int index = 0;
        for (final AccountData accountData : list) {
            if (accountData instanceof ExtendedAccountData) {
                list2.add((ExtendedAccountData)accountData);
            }
            else {
                list2.add(new ExtendedAccountData(EncryptionTools.decode(accountData.user), EncryptionTools.decode(accountData.pass), accountData.alias));
                AltDatabase.getInstance().getAlts().set(index, new ExtendedAccountData(EncryptionTools.decode(accountData.user), EncryptionTools.decode(accountData.pass), accountData.alias));
            }
            ++index;
        }
        return list2;
    }
    
    public void updateButtons() {
        this.login.enabled = (!this.queriedaccounts.isEmpty() && !EncryptionTools.decode(this.queriedaccounts.get(this.selectedAccountIndex).pass).equals(""));
        this.loginoffline.enabled = !this.queriedaccounts.isEmpty();
        this.delete.enabled = !this.queriedaccounts.isEmpty();
        this.edit.enabled = !this.queriedaccounts.isEmpty();
    }
    
    public ArrayList getAccountList() {
        return AltDatabase.getInstance().getAlts();
    }
    
    public void edit() {
        this.mc.displayGuiScreen((GuiScreen)new GuiEditAccount(this.selectedAccountIndex));
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        Config.save();
    }
    
    public void keyTyped(final char c, final int n) {
        if (n == 200 && !this.queriedaccounts.isEmpty()) {
            if (this.selectedAccountIndex > 0) {
                --this.selectedAccountIndex;
            }
        }
        else if (n == 208 && !this.queriedaccounts.isEmpty()) {
            if (this.selectedAccountIndex < this.queriedaccounts.size() - 1) {
                ++this.selectedAccountIndex;
            }
        }
        else if (n == 1) {
            this.escape();
        }
        else if (n == 211 && this.delete.enabled) {
            this.delete();
        }
        else if (c == '+') {
            this.add();
        }
        else if (c == '/' && this.edit.enabled) {
            this.edit();
        }
        else if (!this.search.isFocused() && n == 19) {
            this.reloadSkins();
        }
        else if (n == 28 && !this.search.isFocused() && (this.login.enabled || this.loginoffline.enabled)) {
            if ((Keyboard.isKeyDown(54) || Keyboard.isKeyDown(42)) && this.loginoffline.enabled) {
                this.logino(this.selectedAccountIndex);
            }
            else if (this.login.enabled) {
                this.login(this.selectedAccountIndex);
            }
        }
        else if (n == 14) {
            if (this.search.isFocused() && this.query.length() > 0) {
                this.query = this.query.substring(0, this.query.length() - 1);
                this.updateText();
                this.updateQueried();
            }
        }
        else if (n == 63) {
            this.reloadSkins();
        }
        else if (c != '\0') {
            if (this.search.isFocused()) {
                if (n == 28) {
                    this.search.setFocused(false);
                    this.updateText();
                    this.updateQueried();
                    return;
                }
                this.query += c;
                this.updateText();
                this.updateQueried();
            }
        }
    }
    
    public static GuiButton access$300(final GuiAccountSelector guiAccountSelector) {
        return guiAccountSelector.login;
    }
    
    public static void access$400(final GuiAccountSelector guiAccountSelector, final int n) {
        guiAccountSelector.login(n);
    }
    
    public void add() {
        this.mc.displayGuiScreen((GuiScreen)new GuiAddAccount());
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        final boolean focused = this.search.isFocused();
        this.search.mouseClicked(n, n2, n3);
        if (!focused) {
            if (this.search.isFocused()) {
                this.query = "";
                this.updateText();
                this.updateQueried();
            }
        }
    }
    
    public static FontRenderer access$500(final GuiAccountSelector guiAccountSelector) {
        return guiAccountSelector.fontRenderer;
    }
    
    public void logino(final int index) {
        AltManager.getInstance().setUserOffline(this.queriedaccounts.get(index).alias);
        this.loginfailed = null;
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
        final ExtendedAccountData currentAsEditable;
        final ExtendedAccountData extendedAccountData = currentAsEditable = this.getCurrentAsEditable();
        ++currentAsEditable.useCount;
        extendedAccountData.lastused = JavaTools.getJavaCompat().getDate();
    }
    
    public static ArrayList access$000(final GuiAccountSelector guiAccountSelector) {
        return guiAccountSelector.queriedaccounts;
    }
    
    public void handleMouseInput() {
        super.handleMouseInput();
        this.accountsgui.handleMouseInput();
    }
    
    public void actionPerformed(final GuiButton guiButton) {
        if (guiButton.enabled) {
            if (guiButton.id == 3) {
                this.escape();
            }
            else if (guiButton.id == 0) {
                this.add();
            }
            else if (guiButton.id == 4) {
                this.delete();
            }
            else if (guiButton.id == 1) {
                this.login(this.selectedAccountIndex);
            }
            else if (guiButton.id == 2) {
                this.logino(this.selectedAccountIndex);
            }
            else if (guiButton.id == 7) {
                this.edit();
            }
            else if (guiButton.id == 8) {
                this.reloadSkins();
            }
            else {
                this.accountsgui.actionPerformed(guiButton);
            }
        }
    }
}
