/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import java.util.UUID;
import com.krazzzzymonkey.catalyst.utils.CapeUtils;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import javax.annotation.Nullable;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.entity.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ AbstractClientPlayer.class })
public abstract class MixinAbstractClientPlayer
{
    @Shadow
    @Nullable
    protected abstract NetworkPlayerInfo getPlayerInfo();
    
    @Inject(method = { "getLocationCape" }, at = { @At("HEAD") }, cancellable = true)
    public void getLocationCape(final CallbackInfoReturnable<ResourceLocation> callbackInfoReturnable) {
        if (ModuleManager.getModule("Capes").isToggled()) {
            final NetworkPlayerInfo info = this.getPlayerInfo();
            UUID uuid = null;
            if (info != null) {
                uuid = this.getPlayerInfo().getGameProfile().getId();
            }
            if (uuid != null && CapeUtils.isCapeUser(uuid)) {
                if (CapeUtils.isOGCapeUser(uuid)) {
                    callbackInfoReturnable.setReturnValue(CapeUtils.capeResourceLocation.get(2));
                }
                if (CapeUtils.isDeveloper(uuid)) {
                    callbackInfoReturnable.setReturnValue(CapeUtils.capeResourceLocation.get(1));
                }
                if (CapeUtils.isUser(uuid)) {
                    callbackInfoReturnable.setReturnValue(CapeUtils.capeResourceLocation.get(0));
                }
            }
        }
    }
}
