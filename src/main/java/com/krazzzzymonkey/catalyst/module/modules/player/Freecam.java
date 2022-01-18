/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.player;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer$PositionRotation;
import net.minecraft.network.play.client.CPacketPlayer$Rotation;
import net.minecraft.network.play.client.CPacketPlayer$Position;
import net.minecraft.network.play.client.CPacketPlayer;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Freecam extends Modules
{
    public double posZ;
    public BooleanValue cancelPackets;
    public double posX;
    public boolean isRidingEntity;
    public float pitch;
    public float yaw;
    public EntityOtherPlayerMP clonedPlayer;
    public IntegerValue speed;
    public Entity ridingEntity;
    public double posY;
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Freecam.mc.player == null) {
            this.toggle();
        }
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        Minecraft.getMinecraft().player.capabilities.isFlying = true;
        Minecraft.getMinecraft().player.capabilities.setFlySpeed(this.speed.getValue() / 100.0f);
        Minecraft.getMinecraft().player.noClip = true;
        Minecraft.getMinecraft().player.onGround = false;
        Minecraft.getMinecraft().player.fallDistance = 0.0f;
    }
    
    public Freecam() {
        super("Freecam", ModuleCategory.PLAYER, "Allows you to move the camera freely around the player");
        this.speed = new IntegerValue("Speed", 5, 1, 100);
        this.cancelPackets = new BooleanValue("CancelPackets", true);
        this.addValue(this.cancelPackets, this.speed);
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        final Packet<?> packet = packetEvent$Out.packet;
        if ((packet instanceof CPacketPlayer || packet instanceof CPacketPlayer$Position || packet instanceof CPacketPlayer$Rotation || packet instanceof CPacketPlayer$PositionRotation) && (boolean)this.cancelPackets.getValue()) {
            packetEvent$Out.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onLivingUpdate(final LivingEvent$LivingUpdateEvent livingEvent$LivingUpdateEvent) {
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.getUniqueID() != null) {
            Wrapper.INSTANCE.player().noClip = true;
        }
    }
    
    @Override
    public void onEnable() {
        if (Minecraft.getMinecraft().player != null) {
            Freecam.mc.player.motionY = 0.0;
            this.isRidingEntity = (Minecraft.getMinecraft().player.getRidingEntity() != null);
            if (Minecraft.getMinecraft().player.getRidingEntity() == null) {
                Minecraft.getMinecraft().player.noClip = true;
                this.posX = Minecraft.getMinecraft().player.posX;
                this.posY = Minecraft.getMinecraft().player.posY;
                this.posZ = Minecraft.getMinecraft().player.posZ;
            }
            else {
                this.ridingEntity = Minecraft.getMinecraft().player.getRidingEntity();
                Minecraft.getMinecraft().player.dismountRidingEntity();
            }
            this.pitch = Minecraft.getMinecraft().player.rotationPitch;
            this.yaw = Minecraft.getMinecraft().player.rotationYaw;
            Minecraft.getMinecraft().player.noClip = true;
            (this.clonedPlayer = new EntityOtherPlayerMP((World)Minecraft.getMinecraft().world, Minecraft.getMinecraft().getSession().getProfile())).copyLocationAndAnglesFrom((Entity)Minecraft.getMinecraft().player);
            this.clonedPlayer.rotationYawHead = Minecraft.getMinecraft().player.rotationYawHead;
            Minecraft.getMinecraft().world.addEntityToWorld(-100, (Entity)this.clonedPlayer);
            Minecraft.getMinecraft().player.capabilities.isFlying = true;
            Minecraft.getMinecraft().player.capabilities.setFlySpeed(this.speed.getValue() / 100.0f);
        }
        super.onEnable();
    }
    
    @Override
    public void onDisable() {
        if (Minecraft.getMinecraft().player != null) {
            Minecraft.getMinecraft().player.setPositionAndRotation(this.posX, this.posY, this.posZ, this.yaw, this.pitch);
            Minecraft.getMinecraft().world.removeEntityFromWorld(-100);
            final double posX = 0.0;
            this.posZ = posX;
            this.posY = posX;
            this.posX = posX;
            final float n = 0.0f;
            this.yaw = n;
            this.pitch = n;
            Minecraft.getMinecraft().player.capabilities.isFlying = false;
            Minecraft.getMinecraft().player.capabilities.setFlySpeed(0.05f);
            Minecraft.getMinecraft().player.noClip = false;
            final EntityPlayerSP player = Minecraft.getMinecraft().player;
            final EntityPlayerSP player2 = Minecraft.getMinecraft().player;
            final EntityPlayerSP player3 = Minecraft.getMinecraft().player;
            final double motionX = 0.0;
            player3.motionZ = motionX;
            player2.motionY = motionX;
            player.motionX = motionX;
            if (this.isRidingEntity) {
                Minecraft.getMinecraft().player.startRiding(this.ridingEntity, true);
            }
        }
        super.onDisable();
    }
}
