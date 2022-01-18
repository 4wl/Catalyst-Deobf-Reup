/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.base;

public enum ComponentType
{
    BUTTON("BUTTON", 2), 
    COLOR_PICKER("COLOR_PICKER", 10);
    
    public static ComponentType[] $VALUES;
    
    COMBO_BOX("COMBO_BOX", 9), 
    FRAME("FRAME", 0), 
    KEYBIND("KEYBIND", 6), 
    SLIDER("SLIDER", 5), 
    PANEL("PANEL", 1), 
    CHECK_BUTTON("CHECK_BUTTON", 4), 
    TEXT("TEXT", 7), 
    EXPANDING_BUTTON("EXPANDING_BUTTON", 3), 
    DROPDOWN("DROPDOWN", 8);
    
    static {
        ComponentType.$VALUES = new ComponentType[] { ComponentType.FRAME, ComponentType.PANEL, ComponentType.BUTTON, ComponentType.EXPANDING_BUTTON, ComponentType.CHECK_BUTTON, ComponentType.SLIDER, ComponentType.KEYBIND, ComponentType.TEXT, ComponentType.DROPDOWN, ComponentType.COMBO_BOX, ComponentType.COLOR_PICKER };
    }
    
    public ComponentType(final String name, final int ordinal) {
    }
}
