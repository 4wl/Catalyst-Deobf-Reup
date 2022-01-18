/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import java.math.RoundingMode;
import java.math.BigDecimal;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import javax.vecmath.Tuple2f;
import javax.vecmath.Vector2f;

public class MathUtils
{
    public static float getAngleDifference(final float n, final float n2) {
        final float n3 = Math.abs(n2 - n) % 360.0f;
        return (n3 > 180.0f) ? (360.0f - n3) : n3;
    }
    
    public static double getMiddleDouble(final int n, final int n2) {
        return (n + (double)n2) / 2.0;
    }
    
    public static float getTriangleArea(final Vector2f vector2f, final Vector2f vector2f2, final Vector2f vector2f3) {
        final Vector2f vector2f4 = (Vector2f)vector2f2.clone();
        vector2f4.sub((Tuple2f)vector2f);
        final float length = vector2f4.length();
        final Vector2f vector2f5 = (Vector2f)vector2f3.clone();
        vector2f5.sub((Tuple2f)vector2f2);
        final float length2 = vector2f5.length();
        final Vector2f vector2f6 = (Vector2f)vector2f.clone();
        vector2f6.sub((Tuple2f)vector2f3);
        final float length3 = vector2f6.length();
        final float n = (length + length2 + length3) / 2.0f;
        return (float)Math.sqrt(n * (n - length) * (n - length2) * (n - length3));
    }
    
    public static float[] calcAngle(final Vec3d vec3d, final Vec3d vec3d2) {
        final double x = vec3d2.x - vec3d.x;
        final double y = (vec3d2.y - vec3d.y) * -1.0;
        final double y2 = vec3d2.z - vec3d.z;
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y2, x)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y, MathHelper.sqrt(x * x + y2 * y2)))) };
    }
    
    public static float getQuadArea(final Vector2f vector2f, final Vector2f vector2f2, final Vector2f vector2f3) {
        final Vector2f vector2f4 = (Vector2f)vector2f2.clone();
        vector2f4.sub((Tuple2f)vector2f);
        final float length = vector2f4.length();
        final Vector2f vector2f5 = (Vector2f)vector2f3.clone();
        vector2f5.sub((Tuple2f)vector2f2);
        return length * vector2f5.length();
    }
    
    public static Vec3d roundVec(final Vec3d vec3d, final int n) {
        return new Vec3d(round(vec3d.x, n), round(vec3d.y, n), round(vec3d.z, n));
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
    
    public static int getMiddle(final int n, final int n2) {
        return (n + n2) / 2;
    }
    
    public static double square(final float n) {
        return n * n;
    }
    
    public static double round(final double val, final int newScale) {
        if (newScale < 0) {
            throw new IllegalArgumentException();
        }
        return new BigDecimal(val).setScale(newScale, RoundingMode.HALF_UP).doubleValue();
    }
    
    public static boolean isPointInQuad(final Vector2f vector2f, final Vector2f vector2f2, final Vector2f vector2f3, final Vector2f vector2f4, final Vector2f vector2f5) {
        return getTriangleArea(vector2f2, vector2f, vector2f5) + getTriangleArea(vector2f5, vector2f, vector2f4) + getTriangleArea(vector2f4, vector2f, vector2f3) + getTriangleArea(vector2f, vector2f3, vector2f2) <= getQuadArea(vector2f2, vector2f3, vector2f4);
    }
    
    public static float clamp(float n, final float n2, final float n3) {
        if (n <= n2) {
            n = n2;
        }
        if (n >= n3) {
            n = n3;
        }
        return n;
    }
}
