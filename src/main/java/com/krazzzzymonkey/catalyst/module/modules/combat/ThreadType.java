/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

public enum ThreadType
{
    BLOCK("BLOCK", 0);
    
    public static ThreadType[] $VALUES;
    
    CRYSTAL("CRYSTAL", 1);
    
    static {
        ThreadType.$VALUES = new ThreadType[] { ThreadType.BLOCK, ThreadType.CRYSTAL };
    }
    
    public ThreadType(final String name, final int ordinal) {
    }
}
