/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers.accountManager.alt;

import com.krazzzzymonkey.catalyst.managers.accountManager.tools.EncryptionTools;
import java.io.Serializable;

public class AccountData implements Serializable
{
    public static final long serialVersionUID = -147985492L;
    public final String user;
    public final String pass;
    public String alias;
    
    protected AccountData(final String user, final String pass, final String alias) {
        this.user = EncryptionTools.encode(user);
        this.pass = EncryptionTools.encode(pass);
        this.alias = alias;
    }
    
    public boolean equalsBasic(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final AccountData other = (AccountData)obj;
        return this.user.equals(other.user);
    }
}
