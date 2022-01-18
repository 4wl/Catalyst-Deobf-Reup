/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import com.krazzzzymonkey.catalyst.module.modules.movement.AutoSprint;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import com.krazzzzymonkey.catalyst.module.modules.player.Velocity;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.krazzzzymonkey.catalyst.events.MotionEvent$PREWALK;
import com.krazzzzymonkey.catalyst.events.MotionEvent$POST;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import com.krazzzzymonkey.catalyst.events.MotionEvent$PRE;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.mojang.authlib.GameProfile;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.entity.AbstractClientPlayer;

@Mixin({ EntityPlayerSP.class })
public class MixinEntityPlayerSP extends AbstractClientPlayer
{
    @Shadow
    protected Minecraft mc;
    
    public MixinEntityPlayerSP(final World worldIn, final GameProfile playerProfile) {
        super(worldIn, playerProfile);
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") })
    public void onMovePre(final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new MotionEvent$PRE());
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") })
    public void onMovePost(final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new MotionEvent$POST());
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") }, cancellable = true)
    private void preMotion(final CallbackInfo info) {
        MinecraftForge.EVENT_BUS.post((Event)new MotionEvent$PREWALK());
    }
    
    @Inject(method = { "pushOutOfBlocks" }, at = { @At("HEAD") }, cancellable = true)
    public void noPushOutOfBlocks(final double x, final double y, final double z, final CallbackInfoReturnable<Boolean> cir) {
        if (ModuleManager.getModule("Velocity").isToggled() && (boolean)Velocity.push.getValue()) {
            cir.cancel();
        }
    }
    
    @ModifyArg(method = { "setSprinting" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;setSprinting(Z)V"), index = 0)
    public boolean modifySprinting(final boolean sprinting) {
        return (ModuleManager.getModule("AutoSprint").isToggled() && this.mc.player != null && this.shouldSprint(this.mc.player)) || sprinting;
    }
    
    public boolean shouldSprint(final EntityPlayerSP player) {
        if (!this.mc.gameSettings.keyBindSneak.isKeyDown() && player.getFoodStats().getFoodLevel() > 6 && !player.isElytraFlying() && !this.mc.player.capabilities.isFlying) {
            if (AutoSprint.multiDirection.getValue()) {
                if (player.moveForward == 0.0f) {
                    if (player.moveStrafing == 0.0f) {
                        return false;
                    }
                }
            }
            else if (player.moveForward <= 0.0f) {
                return false;
            }
            return true;
        }
        return false;
    }
}
