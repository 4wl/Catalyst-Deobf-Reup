/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class LowOffHand extends Modules
{
    public DoubleValue Height;
    
    public LowOffHand() {
        super("LowOffhand", ModuleCategory.RENDER, "Lowers your offhand");
        this.Height = new DoubleValue("Height", 0.5, 0.0, 1.0);
        this.addValue(this.Height);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        Minecraft.getMinecraft().entityRenderer.itemRenderer.equippedProgressOffHand = this.Height.getValue().floatValue();
    }
}
