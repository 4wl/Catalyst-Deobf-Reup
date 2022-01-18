/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.module.Modules;

public class FastXP extends Modules
{
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.getUniqueID() != null) {
            if (Minecraft.getMinecraft().player.inventory.getCurrentItem().getItem() instanceof ItemExpBottle) {
                Minecraft.getMinecraft().rightClickDelayTimer = 0;
            }
        }
    }
    
    public FastXP() {
        super("FastXP", ModuleCategory.COMBAT, "Allows you to throw XP faster");
    }
}
