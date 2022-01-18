/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.player;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import java.util.LinkedList;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import net.minecraft.network.Packet;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.play.client.CPacketPlayer;
import java.util.Queue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Blink extends Modules
{
    public Queue<CPacketPlayer> packets;
    public static Blink INSTANCE;
    public EntityOtherPlayerMP clonedPlayer;
    
    @Override
    public void onDisable() {
        super.onDisable();
        while (!this.packets.isEmpty()) {
            Blink.mc.player.connection.sendPacket((Packet)this.packets.poll());
        }
        if (Blink.mc.player != null) {
            Blink.mc.world.removeEntityFromWorld(-100);
            this.clonedPlayer = null;
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent$Out packetEvent$Out) {
        if (packetEvent$Out.packet instanceof CPacketPlayer) {
            packetEvent$Out.setCanceled(true);
            this.packets.add((CPacketPlayer)packetEvent$Out.packet);
            System.out.println(this.packets.size());
        }
    }
    
    public Blink() {
        super("Blink", ModuleCategory.PLAYER, "Cancels movement packets and allows you to teleport short distances");
        this.packets = new LinkedList<CPacketPlayer>();
        Blink.INSTANCE = this;
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        if (Blink.mc.player != null) {
            (this.clonedPlayer = new EntityOtherPlayerMP((World)Blink.mc.world, Blink.mc.getSession().getProfile())).copyLocationAndAnglesFrom((Entity)Blink.mc.player);
            this.clonedPlayer.rotationYawHead = Blink.mc.player.rotationYawHead;
            Blink.mc.world.addEntityToWorld(-100, (Entity)this.clonedPlayer);
        }
    }
}
