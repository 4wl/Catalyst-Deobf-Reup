/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketEffect;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.network.play.server.SPacketSoundEffect;
import com.krazzzzymonkey.catalyst.events.PacketEvent;
import com.krazzzzymonkey.catalyst.module.Modules;

public class NoGlobalSounds extends Modules
{
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        final Packet<?> packet = packetEvent.packet;
        if (packet instanceof SPacketSoundEffect) {
            final SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)packet;
            if (sPacketSoundEffect.getCategory() == SoundCategory.WEATHER && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_LIGHTNING_THUNDER) {
                packetEvent.setCanceled(true);
            }
        }
        if (packet instanceof SPacketEffect) {
            final SPacketEffect sPacketEffect = (SPacketEffect)packet;
            if (sPacketEffect.getSoundType() == 1038 || sPacketEffect.getSoundType() == 1023 || sPacketEffect.getSoundType() == 1028) {
                packetEvent.setCanceled(true);
            }
        }
    }
    
    public NoGlobalSounds() {
        super("NoGlobalSounds", ModuleCategory.MISC, "Disables lightning and wither spawn sounds");
    }
}
