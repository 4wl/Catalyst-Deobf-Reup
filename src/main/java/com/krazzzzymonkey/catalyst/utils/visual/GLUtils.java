/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils.visual;

import net.minecraft.client.renderer.GlStateManager$DestFactor;
import net.minecraft.client.renderer.GlStateManager$SourceFactor;
import org.lwjgl.BufferUtils;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.lwjgl.opengl.GL15;
import java.nio.ByteBuffer;
import java.awt.Color;
import org.lwjgl.input.Mouse;
import net.minecraft.client.Minecraft;
import java.awt.Toolkit;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.Tessellator;
import java.util.List;
import java.util.Random;

public class GLUtils
{
    public static Random random;
    public static List<Integer> textures;
    public static Tessellator tessellator;
    public static List<Integer> vbos;
    
    public static void drawBorder(final float n, final float n2, final float n3, final float n4, final float n5) {
        GL11.glLineWidth(n);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        final BufferBuilder buffer = GLUtils.tessellator.getBuffer();
        buffer.begin(2, DefaultVertexFormats.POSITION);
        buffer.pos((double)n2, (double)n3, 0.0).endVertex();
        buffer.pos((double)n2, (double)n5, 0.0).endVertex();
        buffer.pos((double)n4, (double)n5, 0.0).endVertex();
        buffer.pos((double)n4, (double)n3, 0.0).endVertex();
        GLUtils.tessellator.draw();
        GlStateManager.enableTexture2D();
    }
    
    public static int getFullScreenWidth() {
        return (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }
    
    public static int getScreenHeight() {
        return Minecraft.getMinecraft().displayHeight / getScaleFactor();
    }
    
    public static void glScissor(final float n, final float n2, final float n3, final float n4) {
        final int scaleFactor = getScaleFactor();
        GL11.glScissor((int)(n * scaleFactor), (int)(Minecraft.getMinecraft().displayHeight - n4 * scaleFactor), (int)((n3 - n) * scaleFactor), (int)((n4 - n2) * scaleFactor));
    }
    
    public static int getMouseY() {
        return getScreenHeight() - Mouse.getY() * getScreenHeight() / Minecraft.getMinecraft().displayWidth - 1;
    }
    
    public static void glColor(final Color color) {
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public static void glScissor(final int[] array) {
        glScissor((float)array[0], (float)array[1], (float)(array[0] + array[2]), (float)(array[1] + array[3]));
    }
    
    public static int getScaleFactor() {
        int i = 1;
        final boolean unicode = Minecraft.getMinecraft().isUnicode();
        int guiScale = Minecraft.getMinecraft().gameSettings.guiScale;
        if (guiScale == 0) {
            guiScale = 1000;
        }
        while (i < guiScale) {
            if (Minecraft.getMinecraft().displayWidth / (i + 1) < 320) {
                break;
            }
            if (Minecraft.getMinecraft().displayHeight / (i + 1) < 240) {
                break;
            }
            ++i;
        }
        if (unicode && i % 2 != 0 && i != 1) {
            --i;
        }
        return i;
    }
    
    public static void glColor(final float n, final float n2, final float n3, final float n4) {
        GlStateManager.color(n, n2, n3, n4);
    }
    
    public static void drawBorderRect(final float n, final float n2, final float n3, final float n4, final float n5) {
        drawBorder(n5, n, n2, n3, n4);
        drawRect(n, n2, n3, n4);
    }
    
    public static int applyTexture(final int n, final int n2, final int n3, final ByteBuffer byteBuffer, final int n4, final int n5) {
        GL11.glBindTexture(3553, n);
        GL11.glTexParameteri(3553, 10241, n4);
        GL11.glTexParameteri(3553, 10240, n4);
        GL11.glTexParameteri(3553, 10242, n5);
        GL11.glTexParameteri(3553, 10243, n5);
        GL11.glPixelStorei(3317, 1);
        GL11.glTexImage2D(3553, 0, 32856, n2, n3, 0, 6408, 5121, byteBuffer);
        GL11.glBindTexture(3553, 0);
        return n;
    }
    
    public static void drawRect(final float n, final float n2, final float n3, final float n4) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        final BufferBuilder buffer = GLUtils.tessellator.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION);
        buffer.pos((double)n, (double)n4, 0.0).endVertex();
        buffer.pos((double)n3, (double)n4, 0.0).endVertex();
        buffer.pos((double)n3, (double)n2, 0.0).endVertex();
        buffer.pos((double)n, (double)n2, 0.0).endVertex();
        GLUtils.tessellator.draw();
        GlStateManager.enableTexture2D();
    }
    
    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public static void cleanup() {
        GL15.glBindBuffer(34962, 0);
        GL11.glBindTexture(3553, 0);
        final Iterator<Integer> iterator = GLUtils.vbos.iterator();
        while (iterator.hasNext()) {
            GL15.glDeleteBuffers((int)iterator.next());
        }
        final Iterator<Integer> iterator2 = GLUtils.textures.iterator();
        while (iterator2.hasNext()) {
            GL11.glDeleteTextures((int)iterator2.next());
        }
    }
    
    static {
        GLUtils.random = new Random();
        GLUtils.tessellator = Tessellator.getInstance();
        GLUtils.vbos = new ArrayList<Integer>();
        GLUtils.textures = new ArrayList<Integer>();
    }
    
    public static int getTexture() {
        final int glGenTextures = GL11.glGenTextures();
        GLUtils.textures.add(glGenTextures);
        return glGenTextures;
    }
    
    public static Color getRandomColor() {
        return getRandomColor(1000, 0.6f);
    }
    
    public static int getScreenWidth() {
        return Minecraft.getMinecraft().displayWidth / getScaleFactor();
    }
    
    public static void glColor(final int n) {
        GlStateManager.color((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n >> 24 & 0xFF) / 255.0f);
    }
    
    public static int getMouseX() {
        return Mouse.getX() * getScreenWidth() / Minecraft.getMinecraft().displayWidth;
    }
    
    public static int genVBO() {
        final int glGenBuffers = GL15.glGenBuffers();
        GLUtils.vbos.add(glGenBuffers);
        GL15.glBindBuffer(34962, glGenBuffers);
        return glGenBuffers;
    }
    
    public static Color getHSBColor(final float h, final float s, final float b) {
        return Color.getHSBColor(h, s, b);
    }
    
    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }
    
    public static int applyTexture(final int n, final File input, final int n2, final int n3) {
        applyTexture(n, ImageIO.read(input), n2, n3);
        return n;
    }
    
    public static int applyTexture(final int n, final BufferedImage bufferedImage, final int n2, final int n3) {
        final int[] rgbArray = new int[bufferedImage.getWidth() * bufferedImage.getHeight()];
        bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), rgbArray, 0, bufferedImage.getWidth());
        final ByteBuffer byteBuffer = BufferUtils.createByteBuffer(bufferedImage.getWidth() * bufferedImage.getHeight() * 4);
        for (int i = 0; i < bufferedImage.getHeight(); ++i) {
            for (int j = 0; j < bufferedImage.getWidth(); ++j) {
                final int n4 = rgbArray[i * bufferedImage.getWidth() + j];
                byteBuffer.put((byte)(n4 >> 16 & 0xFF));
                byteBuffer.put((byte)(n4 >> 8 & 0xFF));
                byteBuffer.put((byte)(n4 & 0xFF));
                byteBuffer.put((byte)(n4 >> 24 & 0xFF));
            }
        }
        byteBuffer.flip();
        applyTexture(n, bufferedImage.getWidth(), bufferedImage.getHeight(), byteBuffer, n2, n3);
        return n;
    }
    
    public static boolean isHovered(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return n5 >= n && n5 <= n + n3 && n6 >= n2 && n6 < (n2 ^ n4) - 1;
    }
    
    public static Color getRandomColor(final int bound, final float n) {
        return getHSBColor(GLUtils.random.nextFloat(), (GLUtils.random.nextInt(bound) + (float)bound) / bound + bound, n);
    }
    
    public static void drawGradientRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager$SourceFactor.SRC_ALPHA, GlStateManager$DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager$SourceFactor.ONE, GlStateManager$DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(n + (double)n3, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        buffer.pos((double)n, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        buffer.pos((double)n, n2 + (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        buffer.pos(n + (double)n3, n2 + (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        instance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
}
