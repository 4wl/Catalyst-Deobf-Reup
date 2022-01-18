/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.model.ModelPlayer;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.client.renderer.GlStateManager;
import com.krazzzzymonkey.catalyst.module.modules.render.Viewmodel;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RenderPlayer.class })
public abstract class MixinRenderPlayer
{
    @Inject(method = { "renderEntityName" }, at = { @At("HEAD") }, cancellable = true)
    private void renderLivingLabel(final AbstractClientPlayer entity, final double x, final double y, final double z, final String name, final double distanceSq, final CallbackInfo callbackInfo) {
        if (ModuleManager.getModule("Nametags").isToggled()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "renderRightArm" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderPlayer;getMainModel()Lnet/minecraft/client/model/ModelPlayer;") })
    public void renderRightArmHook(final AbstractClientPlayer clientPlayer, final CallbackInfo callbackInfo) {
        if (ModuleManager.getModule("Viewmodel").isToggled()) {
            if (!(boolean)Viewmodel.rainbowMain.getValue()) {
                GlStateManager.color(Viewmodel.mainColor.getColor().getRed() / 255.0f, Viewmodel.mainColor.getColor().getGreen() / 255.0f, Viewmodel.mainColor.getColor().getBlue() / 255.0f);
            }
            else {
                GlStateManager.color(ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f);
            }
        }
    }
    
    @Shadow
    public ModelPlayer getMainModel() {
        throw new AbstractMethodError("Shadow");
    }
    
    @Shadow
    private void setModelVisibilities(final AbstractClientPlayer clientPlayer) {
        throw new AbstractMethodError("Shadow");
    }
}
