/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.movement;

import com.krazzzzymonkey.catalyst.events.MotionEvent$PRE;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import com.krazzzzymonkey.catalyst.utils.MovementUtil;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.module.modules.world.Timer;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.client.entity.EntityPlayerSP;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Speed extends Modules
{
    public ModeValue Mode;
    public DoubleValue onGroundSpeedGroundSpeed;
    public static boolean move;
    public static boolean hop;
    public static Double prevY;
    public double defaultTimer;
    public DoubleValue onGroundSpeedAirSpeed;
    public static float mul;
    public int delay;
    
    public static void doYPortSpeed() {
        if (Speed.hop && Speed.mc.player.posY >= Speed.prevY + 0.399994) {
            Speed.mc.player.motionY = -10000.0;
            Speed.mc.player.posY = Speed.prevY;
            Speed.hop = false;
        }
        if (Speed.mc.player.moveForward != 0.0f && !Speed.mc.player.collidedHorizontally) {
            if (Speed.mc.player.moveForward == 0.0f && Speed.mc.player.moveStrafing == 0.0f) {
                Speed.mc.player.motionX = 0.0;
                Speed.mc.player.motionZ = 0.0;
                if (Speed.mc.player.collidedVertically) {
                    Speed.mc.player.jump();
                    Speed.move = true;
                }
                if (Speed.move && Speed.mc.player.collidedVertically) {
                    Speed.move = false;
                }
            }
            if (Speed.mc.player.collidedVertically) {
                final EntityPlayerSP player = Speed.mc.player;
                player.motionX *= 0.5079;
                final EntityPlayerSP player2 = Speed.mc.player;
                player2.motionZ *= 0.5079;
                doMiniHop();
            }
            if (Speed.hop && !Speed.move && Speed.mc.player.posY >= Speed.prevY + 0.399994) {
                Speed.mc.player.motionY = -100.0;
                Speed.mc.player.posY = Speed.prevY;
                Speed.hop = false;
            }
        }
    }
    
    public Speed() {
        super("Speed", ModuleCategory.MOVEMENT, "Makes you BHop");
        this.delay = 0;
        this.onGroundSpeedGroundSpeed = new DoubleValue("GroundSpeed", 1.199, 0.1, 2.0);
        this.onGroundSpeedAirSpeed = new DoubleValue("AirSpeed", 1.199, 0.1, 2.0);
        this.Mode = new ModeValue("Mode", new Mode[] { new Mode("Strafe", false), new Mode("StrafeStrict", true), new Mode("NCPBHop", false), new Mode("NCPBHopAlt", false), new Mode("OnGround", false), new Mode("LowHop", false), new Mode("YPort", false) });
        this.addValue(this.Mode, this.onGroundSpeedGroundSpeed, this.onGroundSpeedAirSpeed);
    }
    
    @Override
    public void onEnable() {
        this.defaultTimer = Timer.multiplier.getValue();
        super.onEnable();
    }
    
    public static void doMiniHop() {
        Speed.hop = true;
        Speed.prevY = Speed.mc.player.posY;
        Speed.mc.player.jump();
    }
    
    public void a(final double motionY, final float n, final double n2) {
        if (Minecraft.getMinecraft().player.moveForward != 0.0f || Minecraft.getMinecraft().player.moveForward > 0.0f || Minecraft.getMinecraft().player.moveStrafing != 0.0f) {
            Minecraft.getMinecraft().player.setSprinting(true);
            if (Minecraft.getMinecraft().player.onGround) {
                Minecraft.getMinecraft().player.motionY = motionY;
                final float ny = ny();
                final EntityPlayerSP player = Minecraft.getMinecraft().player;
                player.motionX -= MathHelper.sin(ny) * n;
                final EntityPlayerSP player2 = Minecraft.getMinecraft().player;
                player2.motionZ += MathHelper.cos(ny) * n;
            }
            else {
                final double sqrt = Math.sqrt(Minecraft.getMinecraft().player.motionX * Minecraft.getMinecraft().player.motionX + Minecraft.getMinecraft().player.motionZ * Minecraft.getMinecraft().player.motionZ);
                final double n3 = ny();
                Minecraft.getMinecraft().player.motionX = -Math.sin(n3) * n2 * sqrt;
                Minecraft.getMinecraft().player.motionZ = Math.cos(n3) * n2 * sqrt;
            }
        }
    }
    
    public void a(final double n, final double n2, final EntityPlayerSP entityPlayerSP) {
        final MovementInput movementInput = Minecraft.getMinecraft().player.movementInput;
        float moveForward = movementInput.moveForward;
        float moveStrafe = movementInput.moveStrafe;
        float rotationYaw = Minecraft.getMinecraft().player.rotationYaw;
        if (moveForward != 0.0) {
            if (moveStrafe > 0.0) {
                rotationYaw += ((moveForward > 0.0) ? -45 : 45);
            }
            else if (moveStrafe < 0.0) {
                rotationYaw += ((moveForward > 0.0) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0) {
                moveForward = -1.0f;
            }
        }
        if (moveStrafe > 0.0) {
            moveStrafe = 1.0f;
        }
        else if (moveStrafe < 0.0) {
            moveStrafe = -1.0f;
        }
        entityPlayerSP.motionX = n + (moveForward * 0.2 * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + moveStrafe * 0.2 * Math.sin(Math.toRadians(rotationYaw + 90.0f)));
        entityPlayerSP.motionZ = n2 + (moveForward * 0.2 * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - moveStrafe * 0.2 * Math.cos(Math.toRadians(rotationYaw + 90.0f)));
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (this.Mode.getMode("NCPBHop").isToggled()) {
            Timer.multiplier.value = 1.0865;
            if (Speed.mc.player == null) {
                return;
            }
            if (MovementUtil.isMoving()) {
                if (Speed.mc.player.onGround) {
                    Speed.mc.player.jump();
                    Speed.mc.player.moveForward = 0.0223f;
                }
                MovementUtil.strafe();
            }
            else {
                Speed.mc.player.motionX = 0.0;
                Speed.mc.player.motionX = 0.0;
            }
        }
        if (this.Mode.getMode("NCPBHopAlt").isToggled()) {
            if (Speed.mc.player == null) {
                return;
            }
            if (MovementUtil.isMoving()) {
                if (Speed.mc.player.onGround) {
                    Speed.mc.player.jump();
                    final EntityPlayerSP player = Speed.mc.player;
                    player.motionX *= 1.01;
                    final EntityPlayerSP player2 = Speed.mc.player;
                    player2.motionZ *= 1.01;
                    Speed.mc.player.moveForward = 0.0223f;
                }
                final EntityPlayerSP player3 = Speed.mc.player;
                player3.motionY -= 9.9999E-4;
                MovementUtil.strafe();
            }
            else {
                Speed.mc.player.motionX = 0.0;
                Speed.mc.player.motionZ = 0.0;
            }
        }
        if (this.Mode.getMode("Strafe").isToggled()) {
            if (Speed.mc.player == null) {
                return;
            }
            Timer.multiplier.setValue(1.17);
            if (!ModuleManager.getModule("Timer").isToggled()) {
                ModuleManager.getModule("Timer").toggle();
            }
            ++this.delay;
            if (this.delay >= 2) {
                this.a(0.405, 0.22f, 1.0063999891281128);
                this.delay = 0;
            }
        }
        if (this.Mode.getMode("StrafeStrict").isToggled()) {
            if (Speed.mc.player == null) {
                return;
            }
            ++this.delay;
            if (this.delay >= 2) {
                this.a(0.405, 0.22f, 1.0063999891281128);
                this.delay = 0;
            }
        }
    }
    
    @SubscribeEvent
    public void onMotion(final MotionEvent$PRE motionEvent$PRE) {
        if (this.Mode.getMode("OnGround").isToggled()) {
            ModuleManager.getModule("Timer").setToggled(true);
            if (Speed.mc.player == null || !MovementUtil.isMoving()) {
                return;
            }
            if (Speed.mc.player.fallDistance > 3.994) {
                return;
            }
            if (Speed.mc.player.isInWater() || Speed.mc.player.isOnLadder() || Speed.mc.player.collidedHorizontally) {
                return;
            }
            final EntityPlayerSP player = Speed.mc.player;
            player.posY -= 0.3993000090122223;
            Speed.mc.player.motionY = -1000.0;
            Speed.mc.player.cameraPitch = 0.3f;
            Speed.mc.player.distanceWalkedModified = 44.0f;
            Timer.multiplier.value = this.onGroundSpeedAirSpeed.getValue();
            if (Speed.mc.player.onGround) {
                final EntityPlayerSP player2 = Speed.mc.player;
                player2.posY += 0.3993000090122223;
                Speed.mc.player.motionY = 0.3993000090122223;
                Speed.mc.player.distanceWalkedOnStepModified = 44.0f;
                final EntityPlayerSP player3 = Speed.mc.player;
                player3.motionX *= 1.590000033378601;
                final EntityPlayerSP player4 = Speed.mc.player;
                player4.motionZ *= 1.590000033378601;
                Speed.mc.player.cameraPitch = 0.0f;
                Timer.multiplier.setValue(this.onGroundSpeedGroundSpeed.getValue());
            }
        }
        if (this.Mode.getMode("YPort").isToggled()) {
            if (MovementUtil.isMoving()) {
                ModuleManager.getModule("Timer").setToggled(true);
                Timer.multiplier.setValue((double)(1.0f + Speed.mul));
                Speed.mul += 0.01f;
                if (Speed.mul > 0.5f) {
                    Speed.mul = -0.1f;
                }
            }
            doYPortSpeed();
        }
        if (this.Mode.getMode("LowHop").isToggled()) {
            if (MovementUtil.isMoving()) {
                ModuleManager.getModule("Timer").setToggled(true);
                if (Speed.mc.player.onGround) {
                    Timer.multiplier.setValue(1.09);
                    Speed.mc.player.onGround = false;
                    Speed.mc.player.motionY = 0.20000000298023224;
                }
                else {
                    Speed.mc.player.motionY = Math.max(Speed.mc.player.motionY, -0.08);
                    Timer.multiplier.setValue(1.5);
                }
            }
            else {
                ModuleManager.getModule("Timer").setToggled(false);
            }
        }
    }
    
    public static float ny() {
        float rotationYaw = Minecraft.getMinecraft().player.rotationYaw;
        if (Minecraft.getMinecraft().player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (Minecraft.getMinecraft().player.moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (Minecraft.getMinecraft().player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (Minecraft.getMinecraft().player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (Minecraft.getMinecraft().player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }
    
    static {
        Speed.mul = 0.0f;
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        if (ModuleManager.getModule("Timer").isToggled()) {
            ModuleManager.getModule("Timer").toggle();
        }
    }
}
