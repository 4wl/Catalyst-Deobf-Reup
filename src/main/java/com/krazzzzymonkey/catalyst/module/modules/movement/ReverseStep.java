/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.movement;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ReverseStep extends Modules
{
    public IntegerValue blocks;
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (Wrapper.INSTANCE.player() != null && Wrapper.INSTANCE.world() != null && !Wrapper.INSTANCE.player().isInWater() && !Wrapper.INSTANCE.player().isInLava()) {
            if (ReverseStep.mc.player != null && ReverseStep.mc.player.onGround && !ReverseStep.mc.player.isInWater() && !ReverseStep.mc.player.isOnLadder()) {
                for (double n = 0.0; n < this.blocks.getValue() + 0.5; n += 0.01) {
                    if (!ReverseStep.mc.world.getCollisionBoxes((Entity)ReverseStep.mc.player, ReverseStep.mc.player.getEntityBoundingBox().offset(0.0, -n, 0.0)).isEmpty()) {
                        ReverseStep.mc.player.motionY = -10.0;
                        break;
                    }
                }
            }
        }
    }
    
    public ReverseStep() {
        super("ReverseStep", ModuleCategory.MOVEMENT, "Allows you to instantly step down specified height of blocks");
        this.blocks = new IntegerValue("Height", 3, 1, 100);
        this.addValue(this.blocks);
    }
}
