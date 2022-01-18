/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.Minecraft;

public class MovementUtil
{
    public static Minecraft mc;
    
    static {
        MovementUtil.mc = Minecraft.getMinecraft();
    }
    
    public static boolean isMoving() {
        return MovementUtil.mc.player != null && (MovementUtil.mc.player.movementInput.moveForward != 0.0f || MovementUtil.mc.player.movementInput.moveStrafe != 0.0f);
    }
    
    public static void portForward(final double n) {
        final double radians = Math.toRadians(MovementUtil.mc.player.rotationYaw);
        final EntityPlayerSP player = MovementUtil.mc.player;
        final double n2 = MovementUtil.mc.player.posX + Math.sin(radians) * n;
        final double posY = MovementUtil.mc.player.posY;
        final double posZ = MovementUtil.mc.player.posZ;
        final double cos = Math.cos(radians);
        while (true) {
            switch (-1078778713 + 1766316490 + 1) {
                case -430442310: {
                    continue;
                }
                case -688587411: {
                    player.setPosition(n2, posY, posZ + cos * n);
                }
                default: {
                    throw null;
                }
            }
        }
    }
    
    public static double[] directionSpeed(final double n) {
        final Minecraft minecraft = Minecraft.getMinecraft();
        float moveForward = minecraft.player.movementInput.moveForward;
        float moveStrafe = minecraft.player.movementInput.moveStrafe;
        float n2 = minecraft.player.prevRotationYaw + (minecraft.player.rotationYaw - minecraft.player.prevRotationYaw) * minecraft.getRenderPartialTicks();
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
    
    public static boolean hasMotion() {
        return MovementUtil.mc.player.motionX != 0.0 && MovementUtil.mc.player.motionZ != 0.0 && MovementUtil.mc.player.motionY != 0.0;
    }
    
    public static double getDirection() {
        float rotationYaw = MovementUtil.mc.player.rotationYaw;
        if (MovementUtil.mc.player.rotationYaw < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (MovementUtil.mc.player.moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (MovementUtil.mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (MovementUtil.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (MovementUtil.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return Math.toRadians(rotationYaw);
    }
    
    public static float getPlayerSpeedNoY() {
        return (float)Math.sqrt(MovementUtil.mc.player.motionX * MovementUtil.mc.player.motionX + MovementUtil.mc.player.motionZ * MovementUtil.mc.player.motionZ);
    }
    
    public static void strafe() {
        if (!isMoving()) {
            return;
        }
        final double direction = getDirection();
        MovementUtil.mc.player.motionX = -Math.sin(direction) * getPlayerSpeedNoY();
        MovementUtil.mc.player.motionZ = Math.cos(direction) * getPlayerSpeedNoY();
    }
}
