/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.world.storage.MapData;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Slot;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.world.World;
import net.minecraft.item.ItemAir;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemMap;
import net.minecraftforge.client.event.RenderTooltipEvent$PostBackground;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import com.krazzzzymonkey.catalyst.module.Modules;

public class MapTooltip extends Modules
{
    public static ResourceLocation RES_MAP_BACKGROUND;
    public static Minecraft mc;
    public static int y;
    public static int x;
    
    static {
        MapTooltip.RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");
        MapTooltip.mc = Minecraft.getMinecraft();
    }
    
    @SubscribeEvent
    public void onPostBackgroundTooltipRender(final RenderTooltipEvent$PostBackground renderTooltipEvent$PostBackground) {
        if (renderTooltipEvent$PostBackground.getStack().getItem() instanceof ItemMap) {
            MapTooltip.x = renderTooltipEvent$PostBackground.getX();
            MapTooltip.y = renderTooltipEvent$PostBackground.getY();
        }
    }
    
    @SubscribeEvent
    public void onItemTooltip(final ItemTooltipEvent itemTooltipEvent) {
        if (itemTooltipEvent.getItemStack().getItem() instanceof ItemMap) {
            itemTooltipEvent.getToolTip().clear();
            itemTooltipEvent.getToolTip().add(itemTooltipEvent.getItemStack().getDisplayName());
        }
    }
    
    public MapTooltip() {
        super("MapTooltip", ModuleCategory.RENDER, "Displays map content as tooltip");
    }
    
    @SubscribeEvent
    public void onPostDrawScreen(final GuiScreenEvent$DrawScreenEvent$Post guiScreenEvent$DrawScreenEvent$Post) {
        if (!(guiScreenEvent$DrawScreenEvent$Post.getGui() instanceof GuiContainer)) {
            return;
        }
        if (!(MapTooltip.mc.player.inventory.getItemStack().getItem() instanceof ItemAir)) {
            return;
        }
        final Slot slotUnderMouse = ((GuiContainer)guiScreenEvent$DrawScreenEvent$Post.getGui()).getSlotUnderMouse();
        if (slotUnderMouse == null || !slotUnderMouse.getHasStack()) {
            return;
        }
        final ItemStack stack = slotUnderMouse.getStack();
        if (!(stack.getItem() instanceof ItemMap)) {
            return;
        }
        final MapData mapData = ((ItemMap)stack.getItem()).getMapData(stack, (World)MapTooltip.mc.world);
        if (mapData == null) {
            return;
        }
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        MapTooltip.mc.getTextureManager().bindTexture(MapTooltip.RES_MAP_BACKGROUND);
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        GlStateManager.translate((double)MapTooltip.x, MapTooltip.y + 15.5, 0.0);
        GlStateManager.scale(0.5, 0.5, 1.0);
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(-7.0, 135.0, 0.0).tex(0.0, 1.0).endVertex();
        buffer.pos(135.0, 135.0, 0.0).tex(1.0, 1.0).endVertex();
        buffer.pos(135.0, -7.0, 0.0).tex(1.0, 0.0).endVertex();
        buffer.pos(-7.0, -7.0, 0.0).tex(0.0, 0.0).endVertex();
        instance.draw();
        MapTooltip.mc.entityRenderer.getMapItemRenderer().renderMap(mapData, true);
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
    }
}
