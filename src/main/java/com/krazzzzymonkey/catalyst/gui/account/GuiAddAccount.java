/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.account;

import com.krazzzzymonkey.catalyst.managers.accountManager.ExtendedAccountData;
import com.krazzzzymonkey.catalyst.managers.accountManager.alt.AltDatabase;

public class GuiAddAccount extends AbstractAccountGui
{
    @Override
    public void complete() {
        AltDatabase.getInstance().getAlts().add(new ExtendedAccountData(this.getUsername(), this.getPassword(), this.getUsername()));
    }
    
    public GuiAddAccount() {
        super("Add Account");
    }
}
