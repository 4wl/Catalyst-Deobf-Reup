/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class FastFall extends Modules
{
    public IntegerValue MinFallDistance;
    public DoubleValue Speed;
    
    public FastFall() {
        super("FastFall", ModuleCategory.MOVEMENT, "Makes player fall with more speed");
        this.Speed = new DoubleValue("Speed", 1.0, 1.0, 5.0);
        this.MinFallDistance = new IntegerValue("Min Fall Distance", 0, 0, 255);
        this.addValue(this.Speed, this.MinFallDistance);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.getUniqueID() != null) {
                if (Wrapper.INSTANCE.player().fallDistance > this.MinFallDistance.getValue()) {
                    Wrapper.INSTANCE.player().motionY = -this.Speed.getValue();
                }
            }
        }
    }
}
