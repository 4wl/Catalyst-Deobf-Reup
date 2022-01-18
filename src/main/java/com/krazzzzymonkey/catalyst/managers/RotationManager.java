/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayer$PositionRotation;
import net.minecraft.network.play.client.CPacketPlayer$Rotation;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import java.util.concurrent.locks.ReentrantLock;

public class RotationManager
{
    public static float spoofPitch;
    public static float spoofYaw;
    public static int currentPrio;
    public static ReentrantLock mutex;
    
    public static boolean set(final int currentPrio, final float spoofPitch, final float spoofYaw) {
        try {
            RotationManager.mutex.lock();
            if (RotationManager.currentPrio >= currentPrio) {
                final boolean b = false;
                RotationManager.mutex.unlock();
                return b;
            }
            RotationManager.currentPrio = currentPrio;
            RotationManager.spoofPitch = spoofPitch;
            RotationManager.spoofYaw = spoofYaw;
            final boolean b2 = true;
            RotationManager.mutex.unlock();
            return b2;
        }
        finally {
            RotationManager.mutex.unlock();
        }
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        if (RotationManager.currentPrio == Integer.MIN_VALUE) {
            return;
        }
        if (packetEvent$Out.packet instanceof CPacketPlayer$Rotation || packetEvent$Out.packet instanceof CPacketPlayer$PositionRotation) {
            ((CPacketPlayer)packetEvent$Out.packet).pitch = RotationManager.spoofPitch;
            ((CPacketPlayer)packetEvent$Out.packet).yaw = RotationManager.spoofYaw;
            RotationManager.currentPrio = Integer.MIN_VALUE;
        }
    }
    
    static {
        RotationManager.mutex = new ReentrantLock();
        RotationManager.spoofPitch = 0.0f;
        RotationManager.spoofYaw = 0.0f;
        RotationManager.currentPrio = Integer.MIN_VALUE;
    }
}
