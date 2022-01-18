/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class NoOverlay extends Modules
{
    public BooleanValue fire;
    public BooleanValue block;
    public BooleanValue water;
    public BooleanValue portal;
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (this.portal.getValue()) {
            Minecraft.getMinecraft().player.inPortal = false;
        }
    }
    
    @SubscribeEvent
    public void onRenderBlockOverlay(final RenderBlockOverlayEvent renderBlockOverlayEvent) {
        if ((boolean)this.fire.getValue() && renderBlockOverlayEvent.getOverlayType() == RenderBlockOverlayEvent$OverlayType.FIRE) {
            renderBlockOverlayEvent.setCanceled(true);
        }
        if ((boolean)this.water.getValue() && renderBlockOverlayEvent.getOverlayType() == RenderBlockOverlayEvent$OverlayType.WATER) {
            renderBlockOverlayEvent.setCanceled(true);
        }
        if ((boolean)this.block.getValue() && renderBlockOverlayEvent.getOverlayType() == RenderBlockOverlayEvent$OverlayType.BLOCK) {
            renderBlockOverlayEvent.setCanceled(true);
        }
    }
    
    public NoOverlay() {
        super("NoOverlay", ModuleCategory.RENDER, "Stops rendering overlays");
        this.fire = new BooleanValue("Fire", true);
        this.water = new BooleanValue("Water", true);
        this.block = new BooleanValue("InsideBlock", true);
        this.portal = new BooleanValue("Portal", false);
        this.addValue(this.fire, this.water, this.block, this.portal);
    }
}
