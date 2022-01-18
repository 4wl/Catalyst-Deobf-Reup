/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.account;

import com.krazzzzymonkey.catalyst.managers.accountManager.tools.EncryptionTools;
import com.krazzzzymonkey.catalyst.managers.accountManager.EnumBool;
import com.krazzzzymonkey.catalyst.managers.accountManager.tools.JavaTools;
import com.krazzzzymonkey.catalyst.managers.accountManager.alt.AltDatabase;
import com.krazzzzymonkey.catalyst.managers.accountManager.alt.AccountData;
import com.krazzzzymonkey.catalyst.managers.accountManager.ExtendedAccountData;

public class GuiEditAccount extends AbstractAccountGui
{
    public int selectedIndex;
    public ExtendedAccountData data;
    
    public GuiEditAccount(final int n) {
        super("Edit Account");
        this.selectedIndex = n;
        final AccountData accountData = AltDatabase.getInstance().getAlts().get(n);
        if (accountData instanceof ExtendedAccountData) {
            this.data = (ExtendedAccountData)accountData;
        }
        else {
            this.data = new ExtendedAccountData(accountData.user, accountData.pass, accountData.alias, 0, JavaTools.getJavaCompat().getDate(), EnumBool.UNKNOWN);
        }
    }
    
    @Override
    public void complete() {
        AltDatabase.getInstance().getAlts().set(this.selectedIndex, new ExtendedAccountData(this.getUsername(), this.getPassword(), this.hasUserChanged ? this.getUsername() : this.data.alias, this.data.useCount, this.data.lastused, this.data.premium));
    }
    
    @Override
    public void initGui() {
        super.initGui();
        this.setUsername(EncryptionTools.decode(this.data.user));
        this.setPassword(EncryptionTools.decode(this.data.pass));
    }
}
