/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.injection.ModifyArg;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ WorldClient.class })
public class MixinWorldClient
{
    @Inject(method = { "doVoidFogParticles" }, at = { @At("INVOKE") })
    public void aaa(final CallbackInfo callbackInfo) {
    }
    
    @ModifyArg(method = { "doVoidFogParticles" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;showBarrierParticles(IIIILjava/util/Random;ZLnet/minecraft/util/math/BlockPos$MutableBlockPos;)V"), index = 5)
    private boolean adjustYCoord(final boolean holdingBarrier) {
        return ModuleManager.getModule("BarrierView").isToggled();
    }
}
