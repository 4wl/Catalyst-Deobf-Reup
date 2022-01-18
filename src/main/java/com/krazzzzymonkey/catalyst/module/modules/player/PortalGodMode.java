/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.player;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;

public class PortalGodMode extends Modules
{
    public PortalGodMode() {
        super("PortalGodMode", ModuleCategory.PLAYER, "Allows you to be invincible when in a nether portal");
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        if (packetEvent$Out.packet instanceof CPacketConfirmTeleport) {
            packetEvent$Out.setCanceled(true);
        }
    }
}
