/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.item.EntityItem;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ItemESP extends Modules
{
    public ColorValue color;
    public BooleanValue rainbow;
    
    public ItemESP() {
        super("ItemESP", ModuleCategory.RENDER, "Shows you where items are");
        this.color = new ColorValue("EspColor", Color.CYAN);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.addValue(this.color, this.rainbow);
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        for (final Entity next : Wrapper.INSTANCE.world().loadedEntityList) {
            if (next instanceof EntityItem || next instanceof EntityArrow) {
                final Entity entity = next;
                if (this.rainbow.getValue()) {
                    RenderUtils.drawESP(entity, ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 1.0f, renderWorldLastEvent.getPartialTicks());
                }
                else {
                    RenderUtils.drawESP(entity, this.color.getColor().getRed() / 255.0f, this.color.getColor().getGreen() / 255.0f, this.color.getColor().getBlue() / 255.0f, 1.0f, renderWorldLastEvent.getPartialTicks());
                }
            }
        }
    }
}
