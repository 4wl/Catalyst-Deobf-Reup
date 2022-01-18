/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class RenderChams extends Modules
{
    public static BooleanValue Chests;
    public static ColorValue visibleColor;
    public static ColorValue hiddenColor;
    public static BooleanValue raindbowColor;
    public static ModeValue Mode;
    public static ColorValue singleColor;
    
    public RenderChams() {
        super("Chams", ModuleCategory.RENDER, "See entities through walls");
        RenderChams.hiddenColor = new ColorValue("Hidden Color", 0);
        RenderChams.singleColor = new ColorValue("SingleColor", 0);
        RenderChams.visibleColor = new ColorValue("Visible Color", 0);
        RenderChams.Chests = new BooleanValue("Chests", false);
        RenderChams.Mode = new ModeValue("Mode", new Mode[] { new Mode("Basic", true), new Mode("Color", false), new Mode("TwoColor", false) });
        this.addValue(RenderChams.Mode, RenderChams.visibleColor, RenderChams.hiddenColor, RenderChams.singleColor, RenderChams.raindbowColor);
    }
    
    static {
        RenderChams.raindbowColor = new BooleanValue("RainbowColor", false);
    }
}
