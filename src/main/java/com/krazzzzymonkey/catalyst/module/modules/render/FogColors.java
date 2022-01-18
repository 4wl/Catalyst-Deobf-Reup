/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraftforge.client.event.EntityViewRenderEvent$FogDensity;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraftforge.client.event.EntityViewRenderEvent$FogColors;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class FogColors extends Modules
{
    public BooleanValue rainbow;
    public ColorValue color;
    
    @SubscribeEvent
    public void onEntityViewRenderEventFogColors(final EntityViewRenderEvent$FogColors entityViewRenderEvent$FogColors) {
        if (this.rainbow.getValue()) {
            entityViewRenderEvent$FogColors.setBlue(ColorUtils.rainbow().getBlue() / 255.0f);
            entityViewRenderEvent$FogColors.setGreen(ColorUtils.rainbow().getGreen() / 255.0f);
            entityViewRenderEvent$FogColors.setRed(ColorUtils.rainbow().getRed() / 255.0f);
        }
        else {
            entityViewRenderEvent$FogColors.setBlue(this.color.getColor().getBlue() / 255.0f);
            entityViewRenderEvent$FogColors.setGreen(this.color.getColor().getGreen() / 255.0f);
            entityViewRenderEvent$FogColors.setRed(this.color.getColor().getRed() / 255.0f);
        }
    }
    
    public FogColors() {
        super("FogColors", ModuleCategory.RENDER, "Changes the color of fog");
        this.color = new ColorValue("FogColor", Color.CYAN);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.addValue(this.color, this.rainbow);
    }
    
    @SubscribeEvent
    public void onEntityViewRenderEventFogDensity(final EntityViewRenderEvent$FogDensity entityViewRenderEvent$FogDensity) {
        entityViewRenderEvent$FogDensity.setDensity(1.0f);
    }
}
