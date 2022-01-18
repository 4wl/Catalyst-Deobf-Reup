/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.init.Blocks;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class IceSpeed extends Modules
{
    public DoubleValue slipperiness;
    
    public IceSpeed() {
        super("IceSpeed", ModuleCategory.MOVEMENT, "Set the speed you travel on ice");
        this.slipperiness = new DoubleValue("Slipperiness", 0.4, 0.0, 2.0);
        this.addValue(this.slipperiness);
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        Blocks.ICE.slipperiness = 0.98f;
        Blocks.PACKED_ICE.slipperiness = 0.98f;
        Blocks.FROSTED_ICE.slipperiness = 0.98f;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        Blocks.ICE.slipperiness = this.slipperiness.getValue().floatValue();
        Blocks.PACKED_ICE.slipperiness = this.slipperiness.getValue().floatValue();
        Blocks.FROSTED_ICE.slipperiness = this.slipperiness.getValue().floatValue();
    }
}
