/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import com.krazzzzymonkey.catalyst.events.PacketEvent;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;

public class NoRotate extends Modules
{
    public NoRotate() {
        super("NoRotate", ModuleCategory.MISC, "Stops server side rotation packets");
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        final Packet<?> packet = packetEvent.packet;
        if (packet instanceof SPacketPlayerPosLook) {
            final SPacketPlayerPosLook sPacketPlayerPosLook = (SPacketPlayerPosLook)packet;
            if (Minecraft.getMinecraft().player != null) {
                sPacketPlayerPosLook.yaw = Minecraft.getMinecraft().player.rotationYaw;
                sPacketPlayerPosLook.pitch = Minecraft.getMinecraft().player.rotationPitch;
            }
        }
    }
}
