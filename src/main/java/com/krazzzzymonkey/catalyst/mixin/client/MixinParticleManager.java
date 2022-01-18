/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.krazzzzymonkey.catalyst.module.modules.render.NoParticle;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ParticleManager.class })
public class MixinParticleManager
{
    @Inject(method = { "addEffect" }, at = { @At("HEAD") }, cancellable = true)
    public void spawnEffectParticle(final Particle particle, final CallbackInfo info) {
        if (ModuleManager.getModule("NoParticle").isToggled()) {
            if (particle.toString().contains("ParticleExplosionHuge") || particle.toString().contains("ParticleExplosionLarge") || particle.toString().contains("ParticleSmokeNormal") || (particle.toString().contains("ParticleExplosion") && (boolean)NoParticle.explosions.getValue())) {
                info.cancel();
            }
            if (particle.toString().contains("ParticleTotem") && (boolean)NoParticle.totems.getValue()) {
                info.cancel();
            }
            if (particle.toString().contains("ParticleCrit") && (boolean)NoParticle.criticals.getValue()) {
                info.cancel();
            }
            if (NoParticle.all.getValue()) {
                info.cancel();
            }
        }
    }
}
