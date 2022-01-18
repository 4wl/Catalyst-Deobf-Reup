/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager$DestFactor;
import net.minecraft.client.renderer.GlStateManager$SourceFactor;
import net.minecraft.client.renderer.GlStateManager;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import net.minecraft.client.Minecraft;

public class NetherSky$SkyboxSpaceRenderer implements NetherSky$ISpaceRenderer
{
    public Minecraft mc;
    
    public NetherSky$SkyboxSpaceRenderer(final Minecraft mc) {
        this.mc = mc;
    }
    
    @Override
    public void render(final ModeValue modeValue) {
        GlStateManager.disableFog();
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager$SourceFactor.SRC_ALPHA, GlStateManager$DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager$SourceFactor.ONE, GlStateManager$DestFactor.ZERO);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.depthMask(false);
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        for (int i = 0; i < 6; ++i) {
            if (modeValue.getMode("Dickbutt").isToggled()) {
                this.mc.getTextureManager().bindTexture(new ResourceLocation("catalyst/skybox/dickbutt_army.png"));
            }
            else if (modeValue.getMode("Cow").isToggled()) {
                this.mc.getTextureManager().bindTexture(new ResourceLocation("catalyst/skybox/melon_cow_bg.png"));
            }
            else if (modeValue.getMode("Grin").isToggled()) {
                this.mc.getTextureManager().bindTexture(new ResourceLocation("catalyst/skybox/evil_grin.png"));
            }
            else if (modeValue.getMode("0b0t").isToggled()) {
                this.mc.getTextureManager().bindTexture(new ResourceLocation("catalyst/skybox/0b0t.png"));
            }
            else if (modeValue.getMode("Arthur").isToggled()) {
                this.mc.getTextureManager().bindTexture(new ResourceLocation("catalyst/skybox/arthur.png"));
            }
            else if (modeValue.getMode("Impact").isToggled()) {
                this.mc.getTextureManager().bindTexture(new ResourceLocation("catalyst/skybox/impact.png"));
            }
            else {
                this.mc.getTextureManager().bindTexture(new ResourceLocation("catalyst/skybox/melon_cow_bg.png"));
            }
            GlStateManager.pushMatrix();
            if (i == 1) {
                GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
            }
            if (i == 2) {
                GlStateManager.rotate(-90.0f, 1.0f, 0.0f, 0.0f);
                GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
            }
            if (i == 3) {
                GlStateManager.rotate(180.0f, 1.0f, 0.0f, 0.0f);
            }
            if (i == 4) {
                GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
                GlStateManager.rotate(-90.0f, 0.0f, 1.0f, 0.0f);
            }
            if (i == 5) {
                GlStateManager.rotate(-90.0f, 0.0f, 0.0f, 1.0f);
                GlStateManager.rotate(90.0f, 0.0f, 1.0f, 0.0f);
            }
            buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            final double n = 100.0;
            buffer.pos(-n, -n, -n).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
            buffer.pos(-n, -n, n).tex(0.0, 1.0).color(255, 255, 255, 255).endVertex();
            buffer.pos(n, -n, n).tex(1.0, 1.0).color(255, 255, 255, 255).endVertex();
            buffer.pos(n, -n, -n).tex(1.0, 0.0).color(255, 255, 255, 255).endVertex();
            instance.draw();
            GlStateManager.popMatrix();
        }
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.enableAlpha();
    }
}
