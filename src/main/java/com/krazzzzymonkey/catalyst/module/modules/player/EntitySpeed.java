/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.player;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.MovementInput;
import net.minecraft.entity.item.EntityMinecart;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class EntitySpeed extends Modules
{
    public DoubleValue speed;
    
    public EntitySpeed() {
        super("EntitySpeed", ModuleCategory.MOVEMENT, "Allows you to go faster on entities");
        this.speed = new DoubleValue("EntitySpeed", 1.0, 0.0, 5.0);
        this.addValue(this.speed);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (Wrapper.INSTANCE.player().getRidingEntity() != null) {
            final MovementInput movementInput = Wrapper.INSTANCE.player().movementInput;
            double n = movementInput.moveForward;
            double n2 = movementInput.moveStrafe;
            float rotationYaw = Wrapper.INSTANCE.player().rotationYaw;
            if (n == 0.0 && n2 == 0.0) {
                Wrapper.INSTANCE.player().getRidingEntity().motionX = 0.0;
                Wrapper.INSTANCE.player().getRidingEntity().motionZ = 0.0;
            }
            else {
                if (n != 0.0) {
                    if (n2 > 0.0) {
                        rotationYaw += ((n > 0.0) ? -45 : 45);
                    }
                    else if (n2 < 0.0) {
                        rotationYaw += ((n > 0.0) ? 45 : -45);
                    }
                    n2 = 0.0;
                    if (n > 0.0) {
                        n = 1.0;
                    }
                    else if (n < 0.0) {
                        n = -1.0;
                    }
                }
                final double cos = Math.cos(Math.toRadians(rotationYaw + 90.0f));
                final double sin = Math.sin(Math.toRadians(rotationYaw + 90.0f));
                Wrapper.INSTANCE.player().getRidingEntity().motionX = n * this.speed.getValue() * cos + n2 * this.speed.getValue() * sin;
                Wrapper.INSTANCE.player().getRidingEntity().motionZ = n * this.speed.getValue() * sin - n2 * this.speed.getValue() * cos;
                if (Wrapper.INSTANCE.player().getRidingEntity() instanceof EntityMinecart) {
                    ((EntityMinecart)Wrapper.INSTANCE.player().getRidingEntity()).setVelocity(n * this.speed.getValue() * cos + n2 * this.speed.getValue() * sin, ((EntityMinecart)Wrapper.INSTANCE.player().getRidingEntity()).motionY, n * this.speed.getValue() * sin - n2 * this.speed.getValue() * cos);
                }
            }
        }
    }
}
