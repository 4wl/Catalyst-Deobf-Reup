/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import com.krazzzzymonkey.catalyst.module.modules.world.Scaffold;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.module.modules.player.Velocity;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Entity.class })
public class MixinEntity
{
    @Redirect(method = { "applyEntityCollision" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void velocity(final Entity entity, final double x, final double y, final double z) {
        if (!ModuleManager.getModule("Velocity").isToggled() || !(boolean)Velocity.push.getValue() || !entity.equals((Object)Wrapper.INSTANCE.player())) {
            entity.motionX += x;
            entity.motionY += y;
            entity.motionZ += z;
            entity.isAirBorne = true;
        }
    }
    
    @Redirect(method = { "move" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isSneaking()Z"))
    public boolean isSneaking(final Entity entity) {
        return ModuleManager.getModule("SafeWalk").isToggled() || ((!ModuleManager.getModule("Scaffold").isToggled() || !Scaffold.cancelSneak) && entity.isSneaking());
    }
}
