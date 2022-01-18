/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.world;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemBlock;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;

public class FastPlace extends Modules
{
    public FastPlace() {
        super("FastPlace", ModuleCategory.WORLD, "Allows you to place blocks faster");
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null) {
                if (Minecraft.getMinecraft().player.getUniqueID() != null) {
                    if (Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemBlock) {
                        Minecraft.getMinecraft().rightClickDelayTimer = 0;
                    }
                }
            }
        }
    }
}
