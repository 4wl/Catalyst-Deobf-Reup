/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import com.krazzzzymonkey.catalyst.module.modules.render.NoArmor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.client.renderer.GlStateManager;
import com.krazzzzymonkey.catalyst.module.modules.render.EnchantColor;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import org.spongepowered.asm.mixin.Final;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.util.ResourceLocation;
import java.awt.Color;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.model.ModelBase;

@Mixin({ LayerArmorBase.class })
public class MixinLayerArmorBase<T extends ModelBase>
{
    private static Color color;
    @Shadow
    protected static final ResourceLocation ENCHANTED_ITEM_GLINT_RES;
    @Shadow
    @Final
    private RenderLivingBase<?> renderer;
    @Shadow
    private float alpha;
    @Shadow
    private float colorR;
    @Shadow
    private float colorG;
    @Shadow
    private float colorB;
    @Shadow
    private boolean skipRenderGlint;
    
    public MixinLayerArmorBase() {
        this.alpha = 1.0f;
        this.colorR = 1.0f;
        this.colorG = 1.0f;
        this.colorB = 1.0f;
    }
    
    @Shadow
    private ResourceLocation getArmorResource(final ItemArmor armor, final boolean p_177178_2_, final String p_177178_3_) {
        throw new AbstractMethodError("Shadow");
    }
    
    @Shadow
    public ResourceLocation getArmorResource(final Entity entity, final ItemStack stack, final EntityEquipmentSlot slot, final String type) {
        throw new AbstractMethodError("Shadow");
    }
    
    @Shadow
    private boolean isLegSlot(final EntityEquipmentSlot slotIn) {
        throw new AbstractMethodError("Shadow");
    }
    
    @Shadow
    public T getModelFromSlot(final EntityEquipmentSlot slotIn) {
        throw new AbstractMethodError("Shadow");
    }
    
    @Shadow
    protected T getArmorModelHook(final EntityLivingBase entity, final ItemStack itemStack, final EntityEquipmentSlot slot, final T model) {
        throw new AbstractMethodError("Shadow");
    }
    
    @Shadow
    protected void setModelSlotVisible(final T p_188359_1_, final EntityEquipmentSlot slotIn) {
        throw new AbstractMethodError("Shadow");
    }
    
    @Inject(method = { "renderEnchantedGlint" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;matrixMode(I)V") })
    private static void renderEnchantedGlint(final RenderLivingBase<?> p_188364_0_, final EntityLivingBase p_188364_1_, final ModelBase model, final float p_188364_3_, final float p_188364_4_, final float p_188364_5_, final float p_188364_6_, final float p_188364_7_, final float p_188364_8_, final float p_188364_9_, final CallbackInfo callbackInfo) {
        if (ModuleManager.getModule("EnchantColor").isToggled()) {
            if (EnchantColor.modes.getMode("Color").isToggled()) {
                GlStateManager.color(EnchantColor.colorValue.getColor().getRed() / 255.0f, EnchantColor.colorValue.getColor().getGreen() / 255.0f, EnchantColor.colorValue.getColor().getBlue() / 255.0f);
            }
            if (EnchantColor.modes.getMode("Rainbow").isToggled()) {
                GlStateManager.color(ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f);
            }
        }
    }
    
    @Inject(method = { "renderArmorLayer" }, at = { @At("HEAD") }, cancellable = true)
    private void renderArmorLayer(final EntityLivingBase entityLivingBaseIn, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale, final EntityEquipmentSlot slotIn, final CallbackInfo callbackInfo) {
        boolean doRender = false;
        switch (slotIn) {
            case CHEST: {
                if ((!(boolean)NoArmor.chest.getValue() && ModuleManager.getModule("NoArmor").isToggled()) || !ModuleManager.getModule("NoArmor").isToggled()) {
                    doRender = true;
                    break;
                }
                break;
            }
            case LEGS: {
                if ((!(boolean)NoArmor.legs.getValue() && ModuleManager.getModule("NoArmor").isToggled()) || !ModuleManager.getModule("NoArmor").isToggled()) {
                    doRender = true;
                    break;
                }
                break;
            }
            case FEET: {
                if ((!(boolean)NoArmor.feet.getValue() && ModuleManager.getModule("NoArmor").isToggled()) || !ModuleManager.getModule("NoArmor").isToggled()) {
                    doRender = true;
                    break;
                }
                break;
            }
            case HEAD: {
                if ((!(boolean)NoArmor.head.getValue() && ModuleManager.getModule("NoArmor").isToggled()) || !ModuleManager.getModule("NoArmor").isToggled()) {
                    doRender = true;
                    break;
                }
                break;
            }
        }
        if (!doRender) {
            callbackInfo.cancel();
        }
    }
    
    static {
        MixinLayerArmorBase.color = Color.green;
        ENCHANTED_ITEM_GLINT_RES = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    }
}
