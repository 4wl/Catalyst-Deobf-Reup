/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.events;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.command.EntityDesync;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.managers.CommandManager;
import net.minecraft.network.play.client.CPacketTabComplete;

public class CommandEvent
{
    public static String inputField;
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTabComplete(final PacketEvent packetEvent) {
        if (packetEvent.packet instanceof CPacketTabComplete && CommandEvent.inputField != null && CommandEvent.inputField.startsWith(CommandManager.prefix) && !CommandEvent.inputField.contains(" ")) {
            packetEvent.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (EntityDesync.getRidingEntity == null || Minecraft.getMinecraft().player.isRiding()) {
            return;
        }
        Minecraft.getMinecraft().player.onGround = true;
        EntityDesync.getRidingEntity.setPosition(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ);
        Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketVehicleMove(EntityDesync.getRidingEntity));
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        final Packet<?> packet = packetEvent.packet;
        if (packet instanceof SPacketSetPassengers) {
            if (EntityDesync.getRidingEntity == null) {
                return;
            }
            final SPacketSetPassengers sPacketSetPassengers = (SPacketSetPassengers)packet;
            if (Minecraft.getMinecraft().world.getEntityByID(sPacketSetPassengers.getEntityId()) == EntityDesync.getRidingEntity) {
                final int[] passengerIds = sPacketSetPassengers.getPassengerIds();
                for (int length = passengerIds.length, i = 0; i < length; ++i) {
                    if (Minecraft.getMinecraft().world.getEntityByID(passengerIds[i]) == Minecraft.getMinecraft().player) {
                        return;
                    }
                }
                ChatUtils.warning("Server sent dismount packet! Remounting client side.");
                if (EntityDesync.getRidingEntity != null) {
                    EntityDesync.getRidingEntity.isDead = false;
                    if (!Minecraft.getMinecraft().player.isRiding()) {
                        Minecraft.getMinecraft().world.spawnEntity(EntityDesync.getRidingEntity);
                        Minecraft.getMinecraft().player.startRiding(EntityDesync.getRidingEntity, true);
                    }
                    EntityDesync.getRidingEntity = null;
                }
            }
        }
        else if (packet instanceof SPacketDestroyEntities) {
            final SPacketDestroyEntities sPacketDestroyEntities = (SPacketDestroyEntities)packet;
            if (EntityDesync.getRidingEntity == null) {
                return;
            }
            final int[] entityIDs = sPacketDestroyEntities.getEntityIDs();
            for (int length2 = entityIDs.length, j = 0; j < length2; ++j) {
                if (entityIDs[j] == EntityDesync.getRidingEntity.getEntityId()) {
                    ChatUtils.normalChat(EntityDesync.getRidingEntity.getName() + " is now out of render distance!");
                    return;
                }
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatSent(final ClientChatEvent clientChatEvent) {
        if (clientChatEvent.getMessage().startsWith(CommandManager.prefix)) {
            clientChatEvent.setCanceled(true);
            String string;
            try {
                Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(clientChatEvent.getMessage());
                CommandManager.getInstance().runCommands(clientChatEvent.getMessage().substring(1));
                return;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                string = ChatFormatting.DARK_RED + "Error: " + ex.getMessage();
            }
            ChatUtils.message(string);
        }
    }
}
