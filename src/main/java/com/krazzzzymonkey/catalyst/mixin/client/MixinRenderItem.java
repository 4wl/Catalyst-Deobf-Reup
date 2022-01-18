/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.module.modules.render.EnchantColor;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.block.model.IBakedModel;
import org.spongepowered.asm.mixin.Final;
import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.util.ResourceLocation;
import java.awt.Color;
import net.minecraft.client.renderer.RenderItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RenderItem.class })
public class MixinRenderItem
{
    private Color color;
    @Shadow
    public static ResourceLocation RES_ITEM_GLINT;
    @Shadow
    @Final
    private TextureManager textureManager;
    
    public MixinRenderItem() {
        this.color = Color.GREEN;
    }
    
    @Shadow
    private void renderModel(final IBakedModel model, final int color) {
        throw new AbstractMethodError("Shadow");
    }
    
    @Shadow
    private void renderModel(final IBakedModel model, final ItemStack stack) {
        throw new AbstractMethodError("Shadow");
    }
    
    @ModifyArg(method = { "renderEffect" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;I)V"), index = 1)
    public int getColor(final int color) {
        if (ModuleManager.getModule("EnchantColor").isToggled()) {
            if (EnchantColor.modes.getMode("Color").isToggled()) {
                return EnchantColor.colorValue.getColor().getRGB();
            }
            if (EnchantColor.modes.getMode("Rainbow").isToggled()) {
                return ColorUtils.rainbow().getRGB();
            }
        }
        return -8372020;
    }
    
    static {
        MixinRenderItem.RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    }
}
