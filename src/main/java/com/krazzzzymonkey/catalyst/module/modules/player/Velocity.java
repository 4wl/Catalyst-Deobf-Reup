/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.player;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.world.World;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import com.krazzzzymonkey.catalyst.events.PacketEvent$In;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Velocity extends Modules
{
    public static BooleanValue push;
    public BooleanValue fishingBob;
    public static BooleanValue flowingWater;
    public BooleanValue explosions;
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$In packetEvent$In) {
        final Packet<?> packet = packetEvent$In.packet;
        if (packet instanceof SPacketEntityVelocity && ((SPacketEntityVelocity)packet).getEntityID() == Minecraft.getMinecraft().player.getEntityId()) {
            packetEvent$In.setCanceled(true);
        }
        if (packet instanceof SPacketExplosion && (boolean)this.explosions.getValue()) {
            final SPacketExplosion sPacketExplosion = (SPacketExplosion)packet;
            sPacketExplosion.motionX = 0.0f;
            sPacketExplosion.motionY = 0.0f;
            sPacketExplosion.motionZ = 0.0f;
        }
        if (packet instanceof SPacketEntityStatus && (boolean)this.fishingBob.getValue()) {
            final SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)packet;
            if (sPacketEntityStatus.getOpCode() == 31) {
                final Entity entity = sPacketEntityStatus.getEntity((World)Minecraft.getMinecraft().world);
                if (entity != null && entity instanceof EntityFishHook && ((EntityFishHook)entity).caughtEntity == Minecraft.getMinecraft().player) {
                    packetEvent$In.setCanceled(true);
                }
            }
        }
    }
    
    public Velocity() {
        super("Velocity", ModuleCategory.PLAYER, "Cancels various knockback packets");
        this.explosions = new BooleanValue("Explosions", true);
        this.fishingBob = new BooleanValue("FishingBob", true);
        Velocity.push = new BooleanValue("NoPush", true);
        Velocity.flowingWater = new BooleanValue("FlowingWater", true);
        this.addValue(this.explosions, Velocity.push, this.fishingBob, Velocity.flowingWater);
    }
}
