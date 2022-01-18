/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import org.lwjgl.opengl.GL11;

public class RenderUtil
{
    public static void drawPartialImage(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final double n9 = GL11.glGetTexLevelParameteri(3553, 0, 4096);
        final double n10 = GL11.glGetTexLevelParameteri(3553, 0, 4097);
        final double n11 = 1.0 / n9;
        final double n12 = n11 * n7;
        final double n13 = n11 * n3;
        final double n14 = 1.0 / n10;
        final double n15 = n14 * n8;
        final double n16 = n14 * n4;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)n, (float)n2, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2d(n13, n16);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2d(n13, n16 + n15);
        GL11.glVertex3f(0.0f, (float)n6, 0.0f);
        GL11.glTexCoord2d(n13 + n12, n16 + n15);
        GL11.glVertex3f((float)n5, (float)n6, 0.0f);
        GL11.glTexCoord2d(n13 + n12, n16);
        GL11.glVertex3f((float)n5, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    public static void drawCompleteImage(final int n, final int n2, final int n3, final int n4) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)n, (float)n2, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, (float)n4, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f((float)n3, (float)n4, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f((float)n3, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
}
