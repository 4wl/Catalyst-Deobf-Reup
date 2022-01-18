/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.init.MobEffects;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoSprint extends Modules
{
    public static BooleanValue multiDirection;
    
    public AutoSprint() {
        super("AutoSprint", ModuleCategory.MOVEMENT, "Automatically sprints for you");
        AutoSprint.multiDirection = new BooleanValue("AllDirections", true);
        this.addValue(AutoSprint.multiDirection);
    }
    
    public boolean canSprint() {
        return Wrapper.INSTANCE.player().onGround && !Wrapper.INSTANCE.player().isSprinting() && !Wrapper.INSTANCE.player().isOnLadder() && !Wrapper.INSTANCE.player().isInWater() && !Wrapper.INSTANCE.player().isInLava() && !Wrapper.INSTANCE.player().collidedHorizontally && Wrapper.INSTANCE.player().moveForward >= 0.1f && !Wrapper.INSTANCE.player().isSneaking() && Wrapper.INSTANCE.player().getFoodStats().getFoodLevel() >= 6 && !Wrapper.INSTANCE.player().isRiding() && !Wrapper.INSTANCE.player().isPotionActive(MobEffects.BLINDNESS);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (this.canSprint()) {
            Wrapper.INSTANCE.player().setSprinting(Utils.isMoving((Entity)Wrapper.INSTANCE.player()));
        }
    }
}
