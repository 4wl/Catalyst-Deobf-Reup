/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketCloseWindow;
import com.krazzzzymonkey.catalyst.events.PacketEvent;
import com.krazzzzymonkey.catalyst.module.Modules;

public class XCarry extends Modules
{
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        final Packet<?> packet = packetEvent.packet;
        if (packet instanceof CPacketCloseWindow && ((CPacketCloseWindow)packet).windowId == Minecraft.getMinecraft().player.inventoryContainer.windowId) {
            packetEvent.setCanceled(true);
        }
    }
    
    public XCarry() {
        super("XCarry", ModuleCategory.MISC, "Allows you to use you crafting slots as inventory space");
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        if (Minecraft.getMinecraft().world != null) {
            Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketCloseWindow(Minecraft.getMinecraft().player.inventoryContainer.windowId));
        }
    }
}
