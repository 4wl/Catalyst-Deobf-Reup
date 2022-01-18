/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer$Position;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Step extends Modules
{
    public float tempHeight;
    public DoubleValue height;
    public ModeValue mode;
    public int ticks;
    
    public Step() {
        super("Step", ModuleCategory.MOVEMENT, "Allows you to walk up blocks greater than one");
        this.ticks = 0;
        final String s = "Mode";
        final Mode[] array = { new Mode("NCP", true), new Mode("AAC", false), new Mode("Simple", false) };
        while (true) {
            switch (-322717748 + 347578454 + 1) {
                case 1269879712: {
                    continue;
                }
                case -126609510: {
                    this.mode = new ModeValue(s, array);
                    this.height = new DoubleValue("Height", 2.0, 0.0, 10.0);
                    this.addValue(this.mode, this.height);
                }
                default: {
                    throw null;
                }
            }
        }
    }
    
    @Override
    public void onEnable() {
        this.ticks = 0;
        super.onEnable();
    }
    
    public static double[] forward(final double n) {
        float moveForward = Minecraft.getMinecraft().player.movementInput.moveForward;
        float moveStrafe = Minecraft.getMinecraft().player.movementInput.moveStrafe;
        float n2 = Minecraft.getMinecraft().player.prevRotationYaw + (Minecraft.getMinecraft().player.rotationYaw - Minecraft.getMinecraft().player.prevRotationYaw) * Minecraft.getMinecraft().getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    @Override
    public void onDisable() {
        Wrapper.INSTANCE.player().stepHeight = 0.5f;
        super.onDisable();
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (this.mode.getMode("AAC").isToggled()) {
            final EntityPlayerSP player = Wrapper.INSTANCE.player();
            if (player.collidedHorizontally) {
                switch (this.ticks) {
                    case 0: {
                        if (player.onGround) {
                            player.jump();
                            break;
                        }
                        break;
                    }
                    case 7: {
                        player.motionY = 0.0;
                        break;
                    }
                    case 8: {
                        if (!player.onGround) {
                            player.setPosition(player.posX, player.posY + 1.0, player.posZ);
                            break;
                        }
                        break;
                    }
                }
                ++this.ticks;
            }
            else {
                this.ticks = 0;
            }
        }
        else if (this.mode.getMode("NCP").isToggled()) {
            final double[] forward = forward(0.1);
            boolean b = false;
            boolean b2 = false;
            boolean b3 = false;
            boolean b4 = false;
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 2.6, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 2.4, forward[1])).isEmpty()) {
                b = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 2.1, forward[1])).isEmpty()) {
                if (!Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.9, forward[1])).isEmpty()) {
                    b2 = true;
                }
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.6, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.4, forward[1])).isEmpty()) {
                b3 = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.0, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 0.6, forward[1])).isEmpty()) {
                b4 = true;
            }
            if (Step.mc.player.collidedHorizontally && (Step.mc.player.moveForward != 0.0f || Step.mc.player.moveStrafing != 0.0f)) {
                if (Step.mc.player.onGround) {
                    if (b4 && this.height.getValue() >= 1.0) {
                        final double[] array = { 0.42, 0.753 };
                        for (int length = array.length, i = 0; i < length; ++i) {
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Step.mc.player.posX, Step.mc.player.posY + array[i], Step.mc.player.posZ, Step.mc.player.onGround));
                        }
                        Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 1.0, Step.mc.player.posZ);
                        this.ticks = 1;
                    }
                    if (b3 && this.height.getValue() >= 1.5) {
                        final double[] array2 = { 0.42, 0.75, 1.0, 1.16, 1.23, 1.2 };
                        for (int j = 0; j < array2.length; ++j) {
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Step.mc.player.posX, Step.mc.player.posY + array2[j], Step.mc.player.posZ, Step.mc.player.onGround));
                        }
                        Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 1.5, Step.mc.player.posZ);
                        this.ticks = 1;
                    }
                    if (b2 && this.height.getValue() >= 2.0) {
                        final double[] array3 = { 0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43 };
                        for (int k = 0; k < array3.length; ++k) {
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Step.mc.player.posX, Step.mc.player.posY + array3[k], Step.mc.player.posZ, Step.mc.player.onGround));
                        }
                        Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 2.0, Step.mc.player.posZ);
                        this.ticks = 2;
                    }
                    if (b && this.height.getValue() >= 2.5) {
                        final double[] array4 = { 0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907 };
                        for (int l = 0; l < array4.length; ++l) {
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Step.mc.player.posX, Step.mc.player.posY + array4[l], Step.mc.player.posZ, Step.mc.player.onGround));
                        }
                        Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 2.5, Step.mc.player.posZ);
                        this.ticks = 2;
                    }
                }
            }
        }
        else if (this.mode.getMode("Simple").isToggled()) {
            Wrapper.INSTANCE.player().stepHeight = this.height.getValue().floatValue();
        }
    }
}
