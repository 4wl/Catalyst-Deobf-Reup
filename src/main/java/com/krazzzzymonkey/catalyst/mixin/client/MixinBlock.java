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
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Block.class })
public class MixinBlock
{
    @Inject(method = { "isFullCube" }, at = { @At("HEAD") }, cancellable = true)
    public void isFullCube(final IBlockState state, final CallbackInfoReturnable<Boolean> cir) {
        try {
            if (ModuleManager.getModule("XRay").isToggled()) {
                cir.setReturnValue(XRay.isInList(Block.class.cast(this)));
                cir.cancel();
            }
        }
        catch (Exception ex) {}
    }
}
