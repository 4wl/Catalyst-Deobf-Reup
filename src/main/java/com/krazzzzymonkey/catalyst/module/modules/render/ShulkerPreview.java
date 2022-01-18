/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import javax.annotation.Nullable;
import com.krazzzzymonkey.catalyst.utils.font.CFontRenderer;
import net.minecraftforge.fml.client.config.GuiUtils;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.math.RayTraceResult$Type;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.BufferBuilder;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ShulkerPreview extends Modules
{
    public static int drawY;
    public static boolean $assertionsDisabled;
    public static boolean active;
    public static int drawX;
    public BooleanValue itemFrame;
    public static int mouseY;
    public static ItemStack itemStack;
    public static int guiTop;
    public static int guiLeft;
    public static boolean pinned;
    public static int mouseX;
    public static NBTTagCompound nbt;
    
    public ShulkerPreview() {
        super("ShulkerPreview", ModuleCategory.RENDER, "Show shulker contents when you hover over them");
        this.itemFrame = new BooleanValue("ItemFramePeek", true);
        this.addValue(this.itemFrame);
    }
    
    public void draw(final BufferBuilder bufferBuilder, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)(n + 0), (double)(n2 + 0), 0.0).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos((double)(n + 0), (double)(n2 + n4), 0.0).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos((double)(n + n3), (double)(n2 + n4), 0.0).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos((double)(n + n3), (double)(n2 + 0), 0.0).color(n5, n6, n7, n8).endVertex();
        Tessellator.getInstance().draw();
    }
    
    static {
        ShulkerPreview.$assertionsDisabled = !ShulkerPreview.class.desiredAssertionStatus();
        ShulkerPreview.pinned = false;
        ShulkerPreview.drawX = 0;
        ShulkerPreview.drawY = 0;
        ShulkerPreview.mouseX = 0;
        ShulkerPreview.mouseY = 0;
        ShulkerPreview.guiLeft = 0;
        ShulkerPreview.guiTop = 0;
    }
    
    public boolean isPointInRegion(final int n, final int n2, final int n3, final int n4, int n5, int n6) {
        final int guiLeft = ShulkerPreview.guiLeft;
        final int guiTop = ShulkerPreview.guiTop;
        n5 -= guiLeft;
        n6 -= guiTop;
        return n5 >= n - 1 && n5 < n + n3 + 1 && n6 >= n2 - 1 && n6 < n2 + n4 + 1;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (!Keyboard.isKeyDown(42)) {
            ShulkerPreview.pinned = false;
        }
        if (!(boolean)this.itemFrame.getValue()) {
            return;
        }
        try {
            if (ShulkerPreview.mc.objectMouseOver.typeOfHit == RayTraceResult$Type.ENTITY) {
                if (ShulkerPreview.mc.objectMouseOver.entityHit instanceof EntityItemFrame && ((EntityItemFrame)ShulkerPreview.mc.objectMouseOver.entityHit).getDisplayedItem().getItem() instanceof ItemShulkerBox) {
                    ShulkerPreview.itemStack = ((EntityItemFrame)ShulkerPreview.mc.objectMouseOver.entityHit).getDisplayedItem();
                }
            }
            else {
                ShulkerPreview.itemStack = null;
            }
        }
        catch (Exception ex) {
            ShulkerPreview.itemStack = null;
        }
    }
    
    @SubscribeEvent
    public void onDrawScreen(final GuiScreenEvent guiScreenEvent) {
        if (ShulkerPreview.active || ShulkerPreview.pinned) {
            if (ShulkerPreview.itemStack == null) {
                return;
            }
            final NonNullList withSize = NonNullList.withSize(27, (Object)ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(ShulkerPreview.nbt, withSize);
            GlStateManager.enableBlend();
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            ShulkerPreview.mc.getRenderItem().zLevel = 300.0f;
            final int drawX = ShulkerPreview.drawX;
            final int drawY = ShulkerPreview.drawY;
            RenderUtils.drawBorderedRect(drawX + 9, drawY - 14, drawX + 173, drawY + 52, 1.0f, ColorUtils.rainbow().getRGB(), ColorUtils.getColor(180, 0, 0, 0));
            Main.fontRenderer.drawStringWithShadow(ShulkerPreview.itemStack.getDisplayName(), drawX + 12, drawY - 14, 16777215);
            GlStateManager.enableBlend();
            GlStateManager.enableTexture2D();
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableGUIStandardItemLighting();
            for (int i = 0; i < withSize.size(); ++i) {
                final int n = drawX + i % 9 * 18 + 11;
                final int n2 = drawY + i / 9 * 18 - 11 + 8;
                final ItemStack itemStack = (ItemStack)withSize.get(i);
                ShulkerPreview.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n, n2);
                this.renderItemOverlayIntoGUI(Main.fontRenderer, itemStack, n, n2, null);
                if (ShulkerPreview.pinned && this.isPointInRegion(n, n2, 18, 18, ShulkerPreview.mouseX, ShulkerPreview.mouseY)) {
                    itemStack.getItem().getFontRenderer(itemStack);
                    GuiUtils.preItemToolTip(itemStack);
                    guiScreenEvent.getGui().drawHoveringText(guiScreenEvent.getGui().getItemToolTip(itemStack), n, n2);
                    GuiUtils.postItemToolTip();
                }
            }
            RenderHelper.disableStandardItemLighting();
            ShulkerPreview.mc.getRenderItem().zLevel = 0.0f;
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
            ShulkerPreview.active = false;
        }
    }
    
    public void renderItemOverlayIntoGUI(final CFontRenderer cFontRenderer, final ItemStack itemStack, final int n, final int n2, @Nullable final String s) {
        Label_0893: {
            if (!itemStack.isEmpty()) {
                if (itemStack.getCount() != 1 || s != null) {
                    final String s2 = (s == null) ? String.valueOf(itemStack.getCount()) : s;
                    GlStateManager.disableLighting();
                    GlStateManager.disableDepth();
                    GlStateManager.disableBlend();
                    cFontRenderer.drawStringWithShadow(s2, (float)(n + 19 - 2 - cFontRenderer.getStringWidth(s2)), n2 + 6 + 3 - 2.0f, 16777215);
                    GlStateManager.enableLighting();
                    GlStateManager.enableDepth();
                    GlStateManager.enableBlend();
                }
                final Item item = itemStack.getItem();
                while (true) {
                    switch (-623250612 + 1807194475 + 1) {
                        case -1701743098: {
                            continue;
                        }
                        case -1318163929: {
                            if (item.showDurabilityBar(itemStack)) {
                                GlStateManager.disableLighting();
                                GlStateManager.disableDepth();
                                GlStateManager.disableTexture2D();
                                GlStateManager.disableAlpha();
                                GlStateManager.disableBlend();
                                final BufferBuilder buffer = Tessellator.getInstance().getBuffer();
                                final double durabilityForDisplay = itemStack.getItem().getDurabilityForDisplay(itemStack);
                                final int rgbDurabilityForDisplay = itemStack.getItem().getRGBDurabilityForDisplay(itemStack);
                                final int round = Math.round(13.0f - (float)durabilityForDisplay * 13.0f);
                                final int n3 = rgbDurabilityForDisplay;
                                this.draw(buffer, n + 2, n2 + 13, 13, 2, 0, 0, 0, 255);
                                this.draw(buffer, n + 2, n2 + 13, round, 1, n3 >> 16 & 0xFF, n3 >> 8 & 0xFF, n3 & 0xFF, 255);
                                GlStateManager.enableBlend();
                                GlStateManager.enableAlpha();
                                GlStateManager.enableTexture2D();
                                GlStateManager.enableLighting();
                                GlStateManager.enableDepth();
                            }
                            final EntityPlayerSP player = Minecraft.getMinecraft().player;
                            final float n4 = (player == null) ? 0.0f : player.getCooldownTracker().getCooldown(itemStack.getItem(), Minecraft.getMinecraft().getRenderPartialTicks());
                            if (n4 > 0.0f) {
                                GlStateManager.disableLighting();
                                GlStateManager.disableDepth();
                                GlStateManager.disableTexture2D();
                                this.draw(Tessellator.getInstance().getBuffer(), n, n2 + MathHelper.floor(16.0f * (1.0f - n4)), 16, MathHelper.ceil(16.0f * n4), 255, 255, 255, 127);
                                GlStateManager.enableTexture2D();
                                GlStateManager.enableLighting();
                                GlStateManager.enableDepth();
                                break Label_0893;
                            }
                            break Label_0893;
                        }
                        default: {
                            throw null;
                        }
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent renderGameOverlayEvent) {
        if (ShulkerPreview.itemStack != null) {
            if (this.itemFrame.getValue()) {
                try {
                    assert ShulkerPreview.itemStack.getTagCompound() != null;
                    ShulkerPreview.nbt = ShulkerPreview.itemStack.getTagCompound().getCompoundTag("BlockEntityTag");
                    ShulkerPreview.active = true;
                    ShulkerPreview.drawX = ShulkerPreview.mc.displayWidth / 4;
                    ShulkerPreview.drawY = ShulkerPreview.mc.displayHeight / 4;
                    Label_0723: {
                        if (!ShulkerPreview.active) {
                            if (!ShulkerPreview.pinned) {
                                break Label_0723;
                            }
                        }
                        final NonNullList withSize = NonNullList.withSize(27, (Object)ItemStack.EMPTY);
                        ItemStackHelper.loadAllItems(ShulkerPreview.nbt, withSize);
                        ShulkerPreview.mc.getRenderItem().zLevel = 300.0f;
                        final int drawX = ShulkerPreview.drawX;
                        final int drawY = ShulkerPreview.drawY;
                        RenderUtils.drawBorderedRect(drawX + 9, drawY - 14, drawX + 173, drawY + 52, 1.0f, ColorUtils.rainbow().getRGB(), ColorUtils.getColor(10, 0, 0, 0));
                        Main.fontRenderer.drawStringWithShadow(ShulkerPreview.itemStack.getDisplayName(), drawX + 12, drawY - 14, 16777215);
                        RenderHelper.enableGUIStandardItemLighting();
                        for (int i = 0; i < withSize.size(); ++i) {
                            final int n = drawX + i % 9 * 18 + 11;
                            final int n2 = drawY + i / 9 * 18 - 11 + 8;
                            final ItemStack itemStack = (ItemStack)withSize.get(i);
                            ShulkerPreview.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n, n2);
                            this.renderItemOverlayIntoGUI(Main.fontRenderer, itemStack, n, n2, null);
                            if (ShulkerPreview.pinned && this.isPointInRegion(n, n2, 18, 18, ShulkerPreview.mouseX, ShulkerPreview.mouseY)) {
                                itemStack.getItem().getFontRenderer(itemStack);
                                GuiUtils.preItemToolTip(itemStack);
                                GuiUtils.postItemToolTip();
                            }
                        }
                        RenderHelper.disableStandardItemLighting();
                        ShulkerPreview.mc.getRenderItem().zLevel = 0.0f;
                        ShulkerPreview.active = false;
                    }
                }
                catch (Exception ex) {}
                ShulkerPreview.mc.getTextureManager().bindTexture(Gui.ICONS);
            }
        }
    }
}
