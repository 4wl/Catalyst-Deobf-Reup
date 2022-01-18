/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui;

import com.krazzzzymonkey.catalyst.lib.MODE;

public class GuiCustom$1
{
    public static int[] $SwitchMap$com$krazzzzymonkey$catalyst$lib$MODE;
    
    static {
        GuiCustom$1.$SwitchMap$com$krazzzzymonkey$catalyst$lib$MODE = new int[MODE.values().length];
        try {
            GuiCustom$1.$SwitchMap$com$krazzzzymonkey$catalyst$lib$MODE[MODE.FILL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            GuiCustom$1.$SwitchMap$com$krazzzzymonkey$catalyst$lib$MODE[MODE.STRETCH.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            GuiCustom$1.$SwitchMap$com$krazzzzymonkey$catalyst$lib$MODE[MODE.CENTER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            GuiCustom$1.$SwitchMap$com$krazzzzymonkey$catalyst$lib$MODE[MODE.TILE.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
    }
}
