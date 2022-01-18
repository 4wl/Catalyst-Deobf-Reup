/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.krazzzzymonkey.catalyst.module.modules.player.Velocity;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Shadow;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPlayer.class })
public abstract class MixinEntityPlayer
{
    @Shadow
    public abstract GameProfile getGameProfile();
    
    @Inject(method = { "isPushedByWater" }, at = { @At("HEAD") }, cancellable = true)
    private void isPushedByWater(final CallbackInfoReturnable<Boolean> callbackInfo) {
        if (ModuleManager.getModule("Velocity").isToggled() && (boolean)Velocity.flowingWater.getValue()) {
            callbackInfo.setReturnValue(false);
        }
    }
    
    @ModifyConstant(method = { "attackTargetEntityWithCurrentItem" }, constant = { @Constant(doubleValue = 0.6) })
    private double multiplyMotion(final double original) {
        try {
            if (ModuleManager.getModule("AutoSprint").isToggled()) {
                return 1.0;
            }
        }
        catch (Exception ex) {}
        return original;
    }
}
