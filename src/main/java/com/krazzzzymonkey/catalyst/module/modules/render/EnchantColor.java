/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class EnchantColor extends Modules
{
    public static ColorValue colorValue;
    public static ModeValue modes;
    
    static {
        EnchantColor.modes = new ModeValue("Modes", new Mode[] { new Mode("Rainbow", false), new Mode("Color", true) });
    }
    
    public EnchantColor() {
        super("EnchantColor", ModuleCategory.RENDER, "Changes color of enchants");
        EnchantColor.colorValue = new ColorValue("Color", Color.CYAN);
        this.addValue(EnchantColor.modes, EnchantColor.colorValue);
    }
}
