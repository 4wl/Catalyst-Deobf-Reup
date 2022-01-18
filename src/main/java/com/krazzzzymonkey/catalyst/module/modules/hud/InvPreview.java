/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderHelper;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import net.minecraft.client.renderer.GlStateManager;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import org.lwjgl.opengl.GL11;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.types.Number;
import net.minecraft.util.ResourceLocation;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class InvPreview extends Modules
{
    public ColorValue boarderColorValue;
    public int finalMouseX;
    public IntegerValue mainOpacity;
    public boolean isAlreadyDragging;
    public BooleanValue drawBoarder;
    public BooleanValue boarderColorRainbow;
    public ColorValue mainColorValue;
    public ResourceLocation resource;
    public boolean isDragging;
    public Number xOffset;
    public Number yOffset;
    public int color;
    public ModeValue inventory;
    public BooleanValue mainColorRainbow;
    public int finalMouseY;
    
    public InvPreview() {
        super("InvPreview", ModuleCategory.HUD, "Renders inventory on hud", true);
        this.finalMouseX = 0;
        this.finalMouseY = 0;
        this.isDragging = false;
        this.isAlreadyDragging = false;
        this.xOffset = new Number("X Offset", 0);
        this.yOffset = new Number("Y Offset", 300);
        this.mainColorValue = new ColorValue("MainColor", Color.BLACK);
        this.mainColorRainbow = new BooleanValue("MainRainbow", false);
        this.mainOpacity = new IntegerValue("Opacity", 140, 0, 255);
        this.drawBoarder = new BooleanValue("Boarder", false);
        this.boarderColorValue = new ColorValue("BoarderColor", Color.CYAN);
        this.boarderColorRainbow = new BooleanValue("BoarderRainbow", false);
        this.boarderColorValue = new ColorValue("BoarderColorValue", Color.CYAN);
        this.boarderColorRainbow = new BooleanValue("BoarderRainbow", false);
        this.addValue(this.mainColorValue, this.mainColorRainbow, this.mainOpacity, this.drawBoarder, this.boarderColorValue, this.boarderColorRainbow, this.xOffset, this.yOffset);
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        final int intValue = (int)this.yOffset.getValue();
        final int intValue2 = (int)this.xOffset.getValue();
        GL11.glPushMatrix();
        if (this.isToggled()) {
            this.drawInventory(intValue2, intValue);
        }
        if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
            if (MouseUtils.isLeftClicked() && !MouseUtils.isMouseOver(intValue2, intValue2 + 180, intValue, intValue + 90)) {
                this.isAlreadyDragging = true;
            }
            if (!MouseUtils.isLeftClicked()) {
                if (this.isAlreadyDragging) {
                    this.isAlreadyDragging = false;
                }
            }
            if (!this.isAlreadyDragging || this.isDragging) {
                if (MouseUtils.isMouseOver(intValue2, intValue2 + 180, intValue, intValue + 90)) {
                    this.isDragging = true;
                }
                if (MouseUtils.isLeftClicked() && this.isDragging) {
                    this.finalMouseX = MouseUtils.getMouseX();
                    this.finalMouseY = MouseUtils.getMouseY();
                    this.xOffset.value = this.finalMouseX - 90;
                    final Number yOffset = this.yOffset;
                    final int finalMouseY = this.finalMouseY;
                    final int n = 10;
                    yOffset.value = (finalMouseY & ~n) + (n & ~finalMouseY);
                }
                else {
                    this.isDragging = false;
                }
            }
        }
        GL11.glPopMatrix();
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null) {
            return;
        }
        Minecraft.getMinecraft().player.getUniqueID();
        this.color = this.boarderColorValue.getColor().getRGB();
        if (this.boarderColorRainbow.getValue()) {
            this.color = ColorUtils.rainbow().getRGB();
        }
    }
    
    public void drawInventory(final int n, final int n2) {
        final NonNullList mainInventory = Minecraft.getMinecraft().player.inventory.mainInventory;
        GlStateManager.enableRescaleNormal();
        if (this.drawBoarder.getValue()) {
            if (this.mainColorRainbow.getValue()) {
                RenderUtils.drawBorderedRect(n, n2, n + 160, n2 + 54, 1.0f, this.color, new Color(ColorUtils.rainbow().getRed(), ColorUtils.rainbow().getGreen(), ColorUtils.rainbow().getBlue(), this.mainOpacity.getValue()).getRGB());
            }
            else {
                RenderUtils.drawBorderedRect(n, n2, n + 160, n2 + 54, 1.0f, this.color, new Color(this.mainColorValue.getColor().getRed(), this.mainColorValue.getColor().getGreen(), this.mainColorValue.getColor().getBlue(), this.mainOpacity.getValue()).getRGB());
            }
        }
        else if (this.mainColorRainbow.getValue()) {
            RenderUtils.drawRect((float)n, (float)n2, (float)(n + 160), (float)(n2 + 54), new Color(ColorUtils.rainbow().getRed(), ColorUtils.rainbow().getGreen(), ColorUtils.rainbow().getBlue(), this.mainOpacity.getValue()).getRGB());
        }
        else {
            RenderUtils.drawRect((float)n, (float)n2, (float)(n + 160), (float)(n2 + 54), new Color(this.mainColorValue.getColor().getRed(), this.mainColorValue.getColor().getGreen(), this.mainColorValue.getColor().getBlue(), this.mainOpacity.getValue()).getRGB());
        }
        for (int size = mainInventory.size(), i = 9; i < size; ++i) {
            final int n3 = n + i % 9 * 18;
            final int n4 = n2 + (i / 9 - 1) * 18;
            RenderHelper.enableGUIStandardItemLighting();
            Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI((ItemStack)mainInventory.get(i), n3, n4);
            Minecraft.getMinecraft().getRenderItem().renderItemOverlays(Minecraft.getMinecraft().fontRenderer, (ItemStack)mainInventory.get(i), n3, n4);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
        }
    }
}
