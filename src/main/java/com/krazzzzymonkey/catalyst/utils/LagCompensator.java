/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import com.krazzzzymonkey.catalyst.events.PacketEvent$In;
import java.util.Arrays;
import net.minecraft.util.math.MathHelper;

public class LagCompensator
{
    public int nextIndex;
    public long timeLastTimeUpdate;
    public static LagCompensator INSTANCE;
    public float[] tickRates;
    
    public void onTimeUpdate() {
        if (this.timeLastTimeUpdate != -1L) {
            this.tickRates[this.nextIndex % this.tickRates.length] = MathHelper.clamp(20.0f / ((System.currentTimeMillis() - this.timeLastTimeUpdate) / 1000.0f), 0.0f, 20.0f);
            ++this.nextIndex;
        }
        this.timeLastTimeUpdate = System.currentTimeMillis();
    }
    
    public void reset() {
        this.nextIndex = 0;
        this.timeLastTimeUpdate = -1L;
        Arrays.fill(this.tickRates, 0.0f);
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$In packetEvent$In) {
        if (packetEvent$In.packet instanceof SPacketTimeUpdate) {
            LagCompensator.INSTANCE.onTimeUpdate();
        }
    }
    
    public float getTickRate() {
        float n = 0.0f;
        float n2 = 0.0f;
        for (final float n3 : this.tickRates) {
            if (n3 > 0.0f) {
                n2 += n3;
                ++n;
            }
        }
        return MathHelper.clamp(n2 / n, 0.0f, 20.0f);
    }
    
    public LagCompensator() {
        this.tickRates = new float[20];
        this.nextIndex = 0;
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
        this.reset();
    }
}
