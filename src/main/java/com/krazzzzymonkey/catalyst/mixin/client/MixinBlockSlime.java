/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.krazzzzymonkey.catalyst.module.modules.movement.NoSlow;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.block.BlockSlime;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ BlockSlime.class })
public class MixinBlockSlime
{
    @Inject(method = { "onEntityWalk" }, at = { @At("HEAD") }, cancellable = true)
    public void onEntityWalk(final CallbackInfo info) {
        if (ModuleManager.getModule("NoSlow").isToggled() && (boolean)NoSlow.slimeBlocks.getValue()) {
            info.cancel();
        }
    }
}
