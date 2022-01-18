/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.chat;

import net.minecraft.network.play.client.CPacketChatMessage;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import java.util.Objects;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import java.util.Iterator;
import java.util.function.BiConsumer;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.network.play.client.CPacketUseEntity$Action;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.events.PacketEvent;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoGG extends Modules
{
    public BooleanValue greenText;
    public static List<String> AutoGgMessages;
    public BooleanValue toxicMode;
    public static ConcurrentHashMap targetedPlayers;
    public int index;
    
    @SubscribeEvent
    public boolean onPacket(final PacketEvent packetEvent) {
        final Packet<?> packet = packetEvent.packet;
        Label_0205: {
            if (Minecraft.getMinecraft().player != null) {
                while (true) {
                    switch (876037457 - 1398541144 + 1) {
                        case 1365553547: {
                            continue;
                        }
                        case -1735085575: {
                            if (AutoGG.targetedPlayers == null) {
                                AutoGG.targetedPlayers = new ConcurrentHashMap();
                            }
                            if (!(packet instanceof CPacketUseEntity)) {
                                break Label_0205;
                            }
                            final CPacketUseEntity cPacketUseEntity = (CPacketUseEntity)packet;
                            if (!cPacketUseEntity.getAction().equals((Object)CPacketUseEntity$Action.ATTACK)) {
                                break Label_0205;
                            }
                            final Entity entityFromWorld = cPacketUseEntity.getEntityFromWorld((World)Minecraft.getMinecraft().world);
                            if (entityFromWorld instanceof EntityPlayer) {
                                addTargetedPlayer(entityFromWorld.getName());
                                break Label_0205;
                            }
                            break Label_0205;
                        }
                        default: {
                            throw null;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (AutoGG.targetedPlayers == null) {
            AutoGG.targetedPlayers = new ConcurrentHashMap();
        }
        for (final Entity entity : Minecraft.getMinecraft().world.getLoadedEntityList()) {
            if (entity instanceof EntityPlayer) {
                final EntityPlayer entityPlayer = (EntityPlayer)entity;
                if (entityPlayer.getHealth() > 0.0f) {
                    continue;
                }
                final String name = entityPlayer.getName();
                if (this.shouldAnnounce(name)) {
                    this.doAnnounce(name);
                    break;
                }
                continue;
            }
        }
        AutoGG.targetedPlayers.forEach(this::lambda$onClientTick$0);
    }
    
    public AutoGG() {
        super("AutoGG", ModuleCategory.CHAT, "Sends a chat message after killing someone");
        this.index = -1;
        this.toxicMode = new BooleanValue("ToxicMode", true);
        this.greenText = new BooleanValue("GreenText", false);
        this.addValue(this.toxicMode);
    }
    
    public static void addTargetedPlayer(final String s) {
        if (!Objects.equals(s, Minecraft.getMinecraft().player.getName())) {
            if (AutoGG.targetedPlayers == null) {
                AutoGG.targetedPlayers = new ConcurrentHashMap();
            }
            AutoGG.targetedPlayers.put(s, 20);
        }
    }
    
    @SubscribeEvent
    public void onLivingDeath(final LivingDeathEvent livingDeathEvent) {
        if (Minecraft.getMinecraft().player != null) {
            if (AutoGG.targetedPlayers == null) {
                AutoGG.targetedPlayers = new ConcurrentHashMap();
            }
            final EntityLivingBase entityLiving = livingDeathEvent.getEntityLiving();
            if (entityLiving != null) {
                if (entityLiving instanceof EntityPlayer) {
                    final EntityPlayer entityPlayer = (EntityPlayer)entityLiving;
                    if (entityPlayer.getHealth() <= 0.0f) {
                        final String name = entityPlayer.getName();
                        if (this.shouldAnnounce(name)) {
                            this.doAnnounce(name);
                        }
                    }
                }
            }
        }
    }
    
    public boolean shouldAnnounce(final String key) {
        return AutoGG.targetedPlayers.containsKey(key);
    }
    
    static {
        AutoGG.AutoGgMessages = new ArrayList<String>();
        AutoGG.targetedPlayers = null;
    }
    
    public void doAnnounce(final String s) {
        AutoGG.targetedPlayers.remove(s);
        if (this.index >= AutoGG.AutoGgMessages.size() - 1) {
            this.index = -1;
        }
        ++this.index;
        String s2;
        if (this.toxicMode.getValue()) {
            s2 = "RIP, {name}, he got put in a spliff by Catalyst. :^)";
        }
        else {
            s2 = "gg {name} Catalyst ontop";
        }
        String s3 = s2.replace("{name}", s);
        if (s3.length() > 255) {
            s3 = s3.substring(0, 255);
        }
        Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketChatMessage(s3));
    }
    
    @Override
    public void onDisable() {
        AutoGG.targetedPlayers = null;
        super.onDisable();
    }
    
    @Override
    public void onEnable() {
        AutoGG.targetedPlayers = new ConcurrentHashMap();
        super.onEnable();
    }
    
    public void lambda$onClientTick$0(final Object o, final Object o2) {
        if ((int)o2 <= 0) {
            AutoGG.targetedPlayers.remove(o);
        }
        else {
            AutoGG.targetedPlayers.put(o, (int)o2 - 1);
        }
    }
}
