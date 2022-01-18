/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.world;

import com.krazzzzymonkey.catalyst.utils.LagCompensator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Timer extends Modules
{
    public static Timer INSTANCE;
    public static DoubleValue multiplier;
    public BooleanValue tpsSync;
    
    @Override
    public void onDisable() {
        Minecraft.getMinecraft().timer.tickLength = 50.0f;
        super.onDisable();
    }
    
    public Timer() {
        super("Timer", ModuleCategory.WORLD, "Speeds up game ticks");
        this.tpsSync = new BooleanValue("TPS Sync", false);
        Timer.multiplier = new DoubleValue("Multiplier", 1.0, 0.1, 10.0);
        this.addValue(Timer.multiplier, this.tpsSync);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        Minecraft.getMinecraft().timer.tickLength = 50.0f / this.getMultiplier();
    }
    
    public float getMultiplier() {
        final double doubleValue = Timer.multiplier.getValue();
        if (this.tpsSync.getValue()) {
            float n = LagCompensator.INSTANCE.getTickRate() / 20.0f * (float)doubleValue;
            if (n < 0.3f) {
                n = 0.3f;
            }
            return n;
        }
        return (float)doubleValue;
    }
}
