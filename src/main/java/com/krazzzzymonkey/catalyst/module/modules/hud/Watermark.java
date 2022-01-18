/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.client.gui.ScaledResolution;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.Number;
import net.minecraft.util.ResourceLocation;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Watermark extends Modules
{
    public boolean isAlreadyDragging;
    public ColorValue colorValue;
    public static int color;
    public IntegerValue scale;
    public BooleanValue version;
    public boolean isDragging;
    public ResourceLocation resource;
    public int finalMouseY;
    public Number xOffset;
    public int finalMouseX;
    public Number yOffset;
    public BooleanValue rainbow;
    
    public Watermark() {
        super("Watermark", ModuleCategory.HUD, "Displays client name on hud", true);
        this.resource = new ResourceLocation("catalyst/gui/watermark.png");
        this.finalMouseX = 0;
        this.finalMouseY = 0;
        this.isDragging = false;
        this.isAlreadyDragging = false;
        this.version = new BooleanValue("Mod Version", true);
        this.colorValue = new ColorValue("Color", Color.CYAN);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.xOffset = new Number("X Offset", 0);
        this.yOffset = new Number("Y Offset", 0);
        this.scale = new IntegerValue("Size", 50, 0, 100);
        this.addValue(this.xOffset, this.yOffset);
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
        if (!(boolean)this.rainbow.getValue()) {
            Watermark.color = this.colorValue.getColor().getRGB();
        }
        else {
            Watermark.color = ColorUtils.rainbow().getRGB();
        }
        GL11.glPushMatrix();
        if (this.version.getValue()) {}
        final int intValue = (int)this.yOffset.getValue();
        final int intValue2 = (int)this.xOffset.getValue();
        final float n = this.scale.getValue() / 100.0f;
        GlStateManager.enableAlpha();
        Minecraft.getMinecraft().renderEngine.bindTexture(this.resource);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.scale(n, n, n);
        Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(intValue2 * 2, intValue * 2, 0, 0, 256, 75);
        GlStateManager.disableAlpha();
        GlStateManager.scale(1.0f, 1.0f, 1.0f);
        GlStateManager.clear(256);
        GlStateManager.scale(1.0f, 1.0f, 1.0f);
        Label_0971: {
            if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
                RenderUtils.drawRect((float)(intValue2 * 2), (float)(intValue * 2), (float)(intValue2 * 2 + 256), (float)(intValue * 2 + 75), ColorUtils.color(0, 0, 0, 100));
                if (MouseUtils.isLeftClicked() && !MouseUtils.isMouseOver(intValue2, intValue2 + 128, intValue, intValue + 37)) {
                    this.isAlreadyDragging = true;
                }
                if (!MouseUtils.isLeftClicked() && this.isAlreadyDragging) {
                    this.isAlreadyDragging = false;
                }
                if (this.isAlreadyDragging) {
                    if (!this.isDragging) {
                        break Label_0971;
                    }
                }
                if (MouseUtils.isMouseOver(intValue2, intValue2 + 256, intValue, intValue + 75)) {
                    this.isDragging = true;
                }
                if (MouseUtils.isLeftClicked() && this.isDragging) {
                    this.finalMouseX = MouseUtils.getMouseX();
                    this.finalMouseY = MouseUtils.getMouseY();
                    this.xOffset.setValue(this.finalMouseX - 64);
                    this.yOffset.setValue(this.finalMouseY);
                }
                else {
                    this.isDragging = false;
                }
            }
        }
        GL11.glPopMatrix();
    }
}
