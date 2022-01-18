/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.modules.render.RenderChams;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import com.krazzzzymonkey.catalyst.module.modules.render.ESP;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelEnderCrystal;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.model.ModelBase;

@Mixin({ ModelEnderCrystal.class })
public class MixinModelEnderCrystal extends ModelBase
{
    @Shadow
    private final ModelRenderer glass;
    @Shadow
    private final ModelRenderer cube;
    @Shadow
    private ModelRenderer base;
    
    public MixinModelEnderCrystal() {
        this.glass = new ModelRenderer((ModelBase)this, "glass");
        this.cube = new ModelRenderer((ModelBase)this, "cube");
    }
    
    @Inject(method = { "render" }, at = { @At("HEAD") }, cancellable = true)
    public void render(final Entity entityIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale, final CallbackInfo callbackInfo) {
        this.glass.setTextureOffset(0, 0).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        GlStateManager.pushMatrix();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GlStateManager.translate(0.0f, -0.5f, 0.0f);
        if (this.base != null) {
            this.base.render(scale);
        }
        if (ModuleManager.getModule("ESP").isToggled() && ESP.Mode.getMode("WireFrame").isToggled()) {
            boolean valid = true;
            if (entityIn instanceof EntityPlayer && !(boolean)ESP.Players.getValue()) {
                valid = false;
            }
            if (!(entityIn instanceof EntityPlayer) && !(boolean)ESP.Mobs.getValue()) {
                valid = false;
            }
            if (entityIn instanceof EntityPlayer && Minecraft.getMinecraft().player == entityIn && !(boolean)ESP.ThirdPerson.getValue()) {
                valid = false;
            }
            if (valid) {
                GL11.glPushMatrix();
                GL11.glPushAttrib(1048575);
                GL11.glPolygonMode(1032, 6913);
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                GL11.glEnable(2848);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                if (ESP.colorStartRainbow.getValue()) {
                    GL11.glColor4f(ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 255.0f);
                }
                else {
                    GL11.glColor4f(ESP.colorStart.getColor().getRed() / 255.0f, ESP.colorStart.getColor().getGreen() / 255.0f, ESP.colorStart.getColor().getBlue() / 255.0f, 255.0f);
                }
                GL11.glLineWidth(ESP.lineWidth.getValue().floatValue());
                GlStateManager.pushMatrix();
                GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
                GlStateManager.translate(0.0f, 0.8f + ageInTicks, 0.0f);
                GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
                this.glass.render(scale);
                GlStateManager.scale(0.875f, 0.875f, 0.875f);
                GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
                GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
                this.glass.render(scale);
                GlStateManager.scale(0.875f, 0.875f, 0.875f);
                GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
                GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
                this.cube.render(scale);
                GlStateManager.popMatrix();
                GL11.glDisable(2929);
                GlStateManager.pushMatrix();
                GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
                GlStateManager.translate(0.0f, 0.8f + ageInTicks, 0.0f);
                GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
                this.glass.render(scale);
                GlStateManager.scale(0.875f, 0.875f, 0.875f);
                GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
                GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
                this.glass.render(scale);
                GlStateManager.scale(0.875f, 0.875f, 0.875f);
                GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
                GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
                this.cube.render(scale);
                GlStateManager.popMatrix();
                GL11.glPopAttrib();
                GL11.glPopMatrix();
                callbackInfo.cancel();
            }
        }
        if (ModuleManager.getModule("Chams").isToggled() && RenderChams.Mode.getMode("TwoColor").isToggled()) {
            GL11.glPushAttrib(1048575);
            GL11.glDisable(3008);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glLineWidth(1.5f);
            GL11.glEnable(2960);
            final Color hiddenColor = RenderChams.hiddenColor.getColor();
            final Color visibleColor = RenderChams.visibleColor.getColor();
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glEnable(10754);
            GL11.glColor4f(hiddenColor.getRed() / 255.0f, hiddenColor.getGreen() / 255.0f, hiddenColor.getBlue() / 255.0f, 1.0f);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
            GlStateManager.translate(0.0f, 0.8f + ageInTicks, 0.0f);
            GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
            this.glass.render(scale);
            GlStateManager.scale(0.875f, 0.875f, 0.875f);
            GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
            GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
            this.glass.render(scale);
            GlStateManager.scale(0.875f, 0.875f, 0.875f);
            GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
            GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
            this.cube.render(scale);
            GlStateManager.popMatrix();
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glColor4f(visibleColor.getRed() / 255.0f, visibleColor.getGreen() / 255.0f, visibleColor.getBlue() / 255.0f, 1.0f);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
            GlStateManager.translate(0.0f, 0.8f + ageInTicks, 0.0f);
            GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
            this.glass.render(scale);
            GlStateManager.scale(0.875f, 0.875f, 0.875f);
            GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
            GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
            this.glass.render(scale);
            GlStateManager.scale(0.875f, 0.875f, 0.875f);
            GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
            GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
            this.cube.render(scale);
            GlStateManager.popMatrix();
            GL11.glEnable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(3553);
            GL11.glEnable(3008);
            GL11.glPopAttrib();
            callbackInfo.cancel();
        }
        else if (ModuleManager.getModule("Chams").isToggled() && RenderChams.Mode.getMode("Basic").isToggled()) {
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0f, -4000000.0f);
            GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
            GlStateManager.translate(0.0f, 0.8f + ageInTicks, 0.0f);
            GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
            this.glass.render(scale);
            GlStateManager.scale(0.875f, 0.875f, 0.875f);
            GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
            GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
            this.glass.render(scale);
            GlStateManager.scale(0.875f, 0.875f, 0.875f);
            GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
            GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
            this.cube.render(scale);
            GL11.glDisable(32823);
            GL11.glPolygonOffset(1.0f, 4000000.0f);
            callbackInfo.cancel();
        }
        else if (ModuleManager.getModule("Chams").isToggled() && RenderChams.Mode.getMode("Color").isToggled()) {
            GL11.glEnable(32823);
            if (!(boolean)RenderChams.raindbowColor.getValue()) {
                GL11.glColor4f(RenderChams.singleColor.getColor().getRed() / 255.0f, RenderChams.singleColor.getColor().getGreen() / 255.0f, RenderChams.singleColor.getColor().getBlue() / 255.0f, 1.0f);
            }
            else {
                GL11.glColor4f(ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 1.0f);
            }
            GL11.glPolygonOffset(1.0f, -4000000.0f);
            GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
            GlStateManager.translate(0.0f, 0.8f + ageInTicks, 0.0f);
            GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
            this.glass.render(scale);
            GlStateManager.scale(0.875f, 0.875f, 0.875f);
            GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
            GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
            this.glass.render(scale);
            GlStateManager.scale(0.875f, 0.875f, 0.875f);
            GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
            GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
            this.cube.render(scale);
            GL11.glDisable(32823);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPolygonOffset(1.0f, 4000000.0f);
            callbackInfo.cancel();
        }
        GL11.glPopMatrix();
    }
}
