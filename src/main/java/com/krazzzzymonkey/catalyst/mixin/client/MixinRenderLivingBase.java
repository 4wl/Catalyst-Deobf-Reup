/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.injection.Redirect;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import com.krazzzzymonkey.catalyst.module.modules.render.ESP;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import com.krazzzzymonkey.catalyst.events.RenderModelEntityLivingEvent;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBase;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import com.krazzzzymonkey.catalyst.module.modules.render.RenderChams;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.entity.EntityLivingBase;

@Mixin(value = { RenderLivingBase.class }, priority = 9998)
public abstract class MixinRenderLivingBase<T extends EntityLivingBase> extends MixinRender<T>
{
    @Inject(method = { "doRender" }, at = { @At("HEAD") })
    public void doRender(final T entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo callback) {
        if (ModuleManager.getModule("Chams").isToggled() && RenderChams.Mode.getMode("Basic").isToggled() && entity instanceof EntityLivingBase) {
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0f, -4000000.0f);
        }
        if (ModuleManager.getModule("Chams").isToggled() && RenderChams.Mode.getMode("Color").isToggled() && entity instanceof EntityLivingBase) {
            GL11.glEnable(32823);
            GlStateManager.disableLighting();
            if (!(boolean)RenderChams.raindbowColor.getValue()) {
                GL11.glColor4f(RenderChams.singleColor.getColor().getRed() / 255.0f, RenderChams.singleColor.getColor().getGreen() / 255.0f, RenderChams.singleColor.getColor().getBlue() / 255.0f, 1.0f);
            }
            else {
                GL11.glColor4f(ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 1.0f);
            }
            GL11.glPolygonOffset(1.0f, -4000000.0f);
        }
    }
    
    @Redirect(method = { "renderModel" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    private void renderModelHook(final ModelBase modelBase, final Entity entityIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        final RenderModelEntityLivingEvent event = new RenderModelEntityLivingEvent((EntityLivingBase)entityIn, modelBase, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return;
        }
        event.getModelBase().render(entityIn, event.getLimbSwing(), event.getLimbSwingAmount(), event.getAgeInTicks(), event.getNetHeadYaw(), event.getHeadPitch(), event.getScaleFactor());
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
                    GL11.glColor4f(ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 1.0f);
                }
                else {
                    GL11.glColor4f(ESP.colorStart.getColor().getRed() / 255.0f, ESP.colorStart.getColor().getGreen() / 255.0f, ESP.colorStart.getColor().getBlue() / 255.0f, 255.0f);
                }
                GL11.glLineWidth(ESP.lineWidth.getValue().floatValue());
                modelBase.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GL11.glDisable(2929);
                modelBase.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
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
            modelBase.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glColor4f(visibleColor.getRed() / 255.0f, visibleColor.getGreen() / 255.0f, visibleColor.getBlue() / 255.0f, 1.0f);
            modelBase.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GL11.glEnable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(3553);
            GL11.glEnable(3008);
            GL11.glPopAttrib();
        }
        else {
            modelBase.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
        GlStateManager.disableOutlineMode();
    }
    
    @Inject(method = { "doRender" }, at = { @At("RETURN") })
    public void doRenderlast(final T entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo callback) {
        if (ModuleManager.getModule("Chams").isToggled() && RenderChams.Mode.getMode("Basic").isToggled() && entity instanceof EntityLivingBase) {
            GL11.glDisable(32823);
            GL11.glPolygonOffset(1.0f, 4000000.0f);
        }
        if (ModuleManager.getModule("Chams").isToggled() && RenderChams.Mode.getMode("Color").isToggled() && entity instanceof EntityLivingBase) {
            GL11.glDisable(32823);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPolygonOffset(1.0f, 4000000.0f);
        }
    }
}
