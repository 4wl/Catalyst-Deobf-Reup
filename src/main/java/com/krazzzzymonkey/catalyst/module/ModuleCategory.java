/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module;

public enum ModuleCategory
{
    PLAYER("PLAYER", 5), 
    WORLD("WORLD", 7), 
    RENDER("RENDER", 6), 
    MISC("MISC", 3), 
    HUD("HUD", 8);
    
    public static ModuleCategory[] $VALUES;
    
    GUI("GUI", 2), 
    CHAT("CHAT", 0), 
    MOVEMENT("MOVEMENT", 4), 
    COMBAT("COMBAT", 1);
    
    static {
        ModuleCategory.$VALUES = new ModuleCategory[] { ModuleCategory.CHAT, ModuleCategory.COMBAT, ModuleCategory.GUI, ModuleCategory.MISC, ModuleCategory.MOVEMENT, ModuleCategory.PLAYER, ModuleCategory.RENDER, ModuleCategory.WORLD, ModuleCategory.HUD };
    }
    
    public ModuleCategory(final String name, final int ordinal) {
    }
}
