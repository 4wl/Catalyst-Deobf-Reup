/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers.accountManager.tools;

import com.krazzzzymonkey.catalyst.managers.accountManager.legacySupport.OldJava;
import com.krazzzzymonkey.catalyst.managers.accountManager.legacySupport.NewJava;
import com.krazzzzymonkey.catalyst.managers.accountManager.legacySupport.ILegacyCompat;

public class JavaTools
{
    private static double getJavaVersion() {
        final String version = System.getProperty("java.version");
        int pos = version.indexOf(46);
        pos = version.indexOf(46, pos + 1);
        return Double.parseDouble(version.substring(0, pos));
    }
    
    public static ILegacyCompat getJavaCompat() {
        if (getJavaVersion() >= 1.8) {
            return new NewJava();
        }
        return new OldJava();
    }
}
