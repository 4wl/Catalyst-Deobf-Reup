/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.player;

import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.client.CPacketAnimation;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import com.krazzzzymonkey.catalyst.module.Modules;

public class NoSwing extends Modules
{
    @SubscribeEvent
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        if (packetEvent$Out.packet instanceof CPacketAnimation) {
            packetEvent$Out.setCanceled(true);
        }
    }
    
    public NoSwing() {
        super("NoSwing", ModuleCategory.PLAYER, "Prevents sending CPacketAnimation to server");
    }
}
