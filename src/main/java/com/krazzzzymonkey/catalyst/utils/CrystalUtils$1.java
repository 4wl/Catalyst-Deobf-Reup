/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraft.world.EnumDifficulty;

public class CrystalUtils$1
{
    public static int[] $SwitchMap$net$minecraft$world$EnumDifficulty;
    
    static {
        CrystalUtils$1.$SwitchMap$net$minecraft$world$EnumDifficulty = new int[EnumDifficulty.values().length];
        try {
            CrystalUtils$1.$SwitchMap$net$minecraft$world$EnumDifficulty[EnumDifficulty.PEACEFUL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            CrystalUtils$1.$SwitchMap$net$minecraft$world$EnumDifficulty[EnumDifficulty.EASY.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            CrystalUtils$1.$SwitchMap$net$minecraft$world$EnumDifficulty[EnumDifficulty.HARD.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
    }
}
