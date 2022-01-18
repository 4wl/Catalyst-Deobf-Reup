/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.lwjgl.opengl.GL11;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.client.renderer.entity.Render;

@Mixin({ RenderEntityItem.class })
public abstract class MixinRenderEntityItem<T> extends Render<EntityItem>
{
    protected MixinRenderEntityItem() {
        super((RenderManager)null);
    }
    
    @Inject(method = { "doRender" }, at = { @At("HEAD") }, cancellable = true)
    public void doRenderHead(final EntityItem entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo callbackInfo) {
        if (ModuleManager.getModule("ItemChams").isToggled()) {
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0f, -1100000.0f);
        }
    }
    
    @Inject(method = { "doRender" }, at = { @At("RETURN") }, cancellable = true)
    public void doRenderReturn(final EntityItem entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo callbackInfo) {
        if (ModuleManager.getModule("ItemChams").isToggled()) {
            GL11.glDisable(32823);
            GL11.glPolygonOffset(1.0f, 1100000.0f);
        }
    }
}
