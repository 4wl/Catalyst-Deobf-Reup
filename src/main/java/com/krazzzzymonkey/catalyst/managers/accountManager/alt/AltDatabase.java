/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers.accountManager.alt;

import com.krazzzzymonkey.catalyst.managers.accountManager.Config;
import java.util.ArrayList;
import java.io.Serializable;

public class AltDatabase implements Serializable
{
    public static final long serialVersionUID = -1585600597L;
    private static AltDatabase instance;
    private final ArrayList<AccountData> altList;
    
    private AltDatabase() {
        this.altList = new ArrayList<AccountData>();
    }
    
    private static void loadFromConfig() {
        if (AltDatabase.instance == null) {
            AltDatabase.instance = (AltDatabase)Config.getInstance().getKey("altaccounts");
        }
    }
    
    private static void saveToConfig() {
        Config.getInstance().setKey("altaccounts", AltDatabase.instance);
    }
    
    public static AltDatabase getInstance() {
        loadFromConfig();
        if (AltDatabase.instance == null) {
            AltDatabase.instance = new AltDatabase();
            saveToConfig();
        }
        return AltDatabase.instance;
    }
    
    public ArrayList<AccountData> getAlts() {
        return this.altList;
    }
}
