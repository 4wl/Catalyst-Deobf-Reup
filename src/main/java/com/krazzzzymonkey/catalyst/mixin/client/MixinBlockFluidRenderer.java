/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.krazzzzymonkey.catalyst.module.modules.render.XRay;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.IBlockAccess;
import net.minecraft.client.renderer.BlockFluidRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ BlockFluidRenderer.class })
public class MixinBlockFluidRenderer
{
    @Inject(method = { "renderFluid" }, at = { @At("HEAD") }, cancellable = true)
    public void renderFluid(final IBlockAccess blockAccess, final IBlockState blockStateIn, final BlockPos blockPosIn, final BufferBuilder bufferBuilderIn, final CallbackInfoReturnable<Boolean> cir) {
        if (ModuleManager.getModule("XRay").isToggled() && !XRay.isInList(blockStateIn.getBlock())) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
