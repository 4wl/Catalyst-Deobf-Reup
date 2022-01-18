/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.passive.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ AbstractHorse.class })
public abstract class MixinAbstractHorse
{
    @Inject(method = { "isHorseSaddled" }, at = { @At("HEAD") }, cancellable = true)
    private void isHorseSaddled(final CallbackInfoReturnable<Boolean> callback) {
        if (ModuleManager.getModule("EntityControl").isToggled()) {
            callback.setReturnValue(true);
        }
    }
}
