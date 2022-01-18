/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils.visual;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.block.state.IBlockState;
import com.krazzzzymonkey.catalyst.xray.XRayData;
import java.util.Iterator;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import com.krazzzzymonkey.catalyst.xray.XRayBlock;
import java.util.LinkedList;
import com.krazzzzymonkey.catalyst.module.modules.render.Tracers;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.GlStateManager$DestFactor;
import net.minecraft.client.renderer.GlStateManager$SourceFactor;
import net.minecraft.client.gui.FontRenderer;
import java.awt.Color;
import net.minecraft.util.math.BlockPos;
import com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui;
import net.minecraft.client.gui.ScaledResolution;
import com.krazzzzymonkey.catalyst.Main;
import net.minecraft.util.math.Vec3d;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import com.krazzzzymonkey.catalyst.utils.TimerUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.client.Minecraft;

public class RenderUtils
{
    public static Minecraft mc;
    public static AxisAlignedBB DEFAULT_AABB;
    public static int splashTickPos;
    public static boolean isSplash;
    public static TimerUtils splashTimer;
    
    public static void drawLine3D(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11) {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        GL11.glLineWidth(n7);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GlStateManager.disableDepth();
        GL11.glEnable(34383);
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        buffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(n - RenderUtils.mc.getRenderManager().viewerPosX, n2 - RenderUtils.mc.getRenderManager().viewerPosY, n3 - RenderUtils.mc.getRenderManager().viewerPosZ).color(n8, n9, n10, n11).endVertex();
        buffer.pos(n4 - RenderUtils.mc.getRenderManager().viewerPosX, n5 - RenderUtils.mc.getRenderManager().viewerPosY, n6 - RenderUtils.mc.getRenderManager().viewerPosZ).color(n8, n9, n10, n11).endVertex();
        instance.draw();
        GlStateManager.shadeModel(7424);
        GL11.glDisable(2848);
        GlStateManager.enableDepth();
        GL11.glDisable(34383);
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
    
    public static double lerp(final double n, final double n2, final float n3) {
        if (n3 == 1.0f || n3 == 5.0f) {
            return n2;
        }
        return n + (n2 - n) * n3;
    }
    
    public static void drawColorBox(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final float n3, final float n4) {
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        instance.draw();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        final BufferBuilder bufferBuilder = buffer;
        final double maxX = axisAlignedBB.maxX;
        final double minY = axisAlignedBB.minY;
        while (true) {
            switch (1669867603 - 1944050229 + 1) {
                case 459789882: {
                    continue;
                }
                case -274199144: {
                    bufferBuilder.pos(maxX, minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    instance.draw();
                    buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    instance.draw();
                    buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    instance.draw();
                    buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    instance.draw();
                    buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    instance.draw();
                }
                default: {
                    throw null;
                }
            }
        }
    }
    
    public static void drawSolidBox() {
        drawSolidBox(RenderUtils.DEFAULT_AABB);
    }
    
    public static void drawESPCSGO(final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        Exception ex;
        try {
            final double viewerPosX = Wrapper.INSTANCE.mc().getRenderManager().viewerPosX;
            final double viewerPosY = Wrapper.INSTANCE.mc().getRenderManager().viewerPosY;
            final double viewerPosZ = Wrapper.INSTANCE.mc().getRenderManager().viewerPosZ;
            final double n7 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n5 - viewerPosX;
            final double n8 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n5 + entity.height / 2.0f - viewerPosY;
            final double n9 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n5 - viewerPosZ;
            final float playerViewY = Wrapper.INSTANCE.mc().getRenderManager().playerViewY;
            final float n10 = 0.0f;
            final boolean b = Wrapper.INSTANCE.mc().getRenderManager().options.thirdPersonView == 2;
            GL11.glPushMatrix();
            GlStateManager.translate(n7, n8, n9);
            GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(-playerViewY, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate((b ? -1 : 1) * n10, 1.0f, 0.0f, 0.0f);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glLineWidth(n6);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glColor4f(n, n2, n3, n4);
            GL11.glBegin(1);
            GL11.glVertex3d(0.4, entity.height / 2.0, 0.0);
            GL11.glVertex3d(0.4, (entity.height - 0.2) / 2.0, 0.0);
            GL11.glVertex3d(-0.4, -entity.height / 2.0, 0.0);
            GL11.glVertex3d(-0.4, (-entity.height + 0.2) / 2.0, 0.0);
            GL11.glVertex3d(-0.4, -entity.height / 2.0, 0.0);
            GL11.glVertex3d(-0.2, -entity.height / 2.0, 0.0);
            GL11.glVertex3d(0.2, entity.height / 2.0, 0.0);
            GL11.glVertex3d(0.4, entity.height / 2.0, 0.0);
            GL11.glVertex3d(-0.4, entity.height / 2.0, 0.0);
            GL11.glVertex3d(-0.4, (entity.height - 0.2) / 2.0, 0.0);
            GL11.glVertex3d(-0.4, entity.height / 2.0, 0.0);
            GL11.glVertex3d(-0.2, entity.height / 2.0, 0.0);
            GL11.glVertex3d(0.4, -entity.height / 2.0, 0.0);
            GL11.glVertex3d(0.2, -entity.height / 2.0, 0.0);
            GL11.glVertex3d(0.4, (-entity.height + 0.2) / 2.0, 0.0);
            GL11.glVertex3d(0.4, -entity.height / 2.0, 0.0);
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glEnable(3553);
            GL11.glDisable(2848);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
            return;
        }
        catch (Exception ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static Vec3d getInterpolatedLinearVec(final Entity entity, final float n) {
        return new Vec3d(lerp(entity.lastTickPosX, entity.posX, n), lerp(entity.lastTickPosY, entity.posY, n), lerp(entity.lastTickPosZ, entity.posZ, n));
    }
    
    public static void drawVLine(final float n, float n2, float n3, final int n4) {
        if (n3 < n2) {
            final float n5 = n2;
            n2 = n3;
            n3 = n5;
        }
        drawRect(n, n2 + 1.0f, n + 1.0f, n3, n4);
    }
    
    public static void drawRect(float n, float n2, float n3, float n4, final int n5) {
        if (n < n3) {
            final float n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final float n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GLUtils.glColor(n5);
        GL11.glBegin(7);
        GL11.glVertex2d((double)n, (double)n4);
        GL11.glVertex2d((double)n3, (double)n4);
        GL11.glVertex2d((double)n3, (double)n2);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
    }
    
    public static void drawBorderedRect(final double n, final double n2, final double n3, final double n4, final float n5, final int n6, final int n7) {
        drawRect((float)(int)n, (float)(int)n2, (float)(int)n3, (float)(int)n4, n7);
        final float n8 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n6 & 0xFF) / 255.0f;
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(n9, n10, n11, n8);
        GL11.glLineWidth(n5);
        GL11.glBegin(1);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n, n4);
        GL11.glVertex2d(n3, n4);
        GL11.glVertex2d(n3, n2);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n3, n2);
        GL11.glVertex2d(n, n4);
        GL11.glVertex2d(n3, n4);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
    }
    
    public static void logoutSpots(final double n, final double n2, final double n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        Exception ex;
        try {
            final double viewerPosX = Wrapper.INSTANCE.mc().getRenderManager().viewerPosX;
            final double viewerPosY = Wrapper.INSTANCE.mc().getRenderManager().viewerPosY;
            final double viewerPosZ = Wrapper.INSTANCE.mc().getRenderManager().viewerPosZ;
            final float playerViewY = Wrapper.INSTANCE.mc().getRenderManager().playerViewY;
            final float playerViewX = Wrapper.INSTANCE.mc().getRenderManager().playerViewX;
            final boolean b = Wrapper.INSTANCE.mc().getRenderManager().options.thirdPersonView == 2;
            GL11.glPushMatrix();
            GlStateManager.translate(n, n2, n3);
            GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(-playerViewY, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate((b ? -1 : 1) * playerViewX, 1.0f, 0.0f, 0.0f);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glLineWidth(1.0f);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glColor4f(n4, n5, n6, n7);
            GL11.glBegin(1);
            GL11.glVertex3d(0.0, 1.0, 0.0);
            GL11.glVertex3d(-0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 1.0, 0.0);
            GL11.glVertex3d(0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(-0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.5, 0.5, 0.0);
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glEnable(3553);
            GL11.glEnable(2896);
            GL11.glDisable(2848);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
            return;
        }
        catch (Exception ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static void drawCustomStringWithRect(final String s, final int n, final int n2, final int n3, final int n4, final int n5) {
        drawBorderedRect(n - 2, n2 - 2, n + ~Main.smallFontRenderer.getStringWidth(s) - 1 + 2, (n2 ^ 0x8) - 1, 1.0f, n4, n5);
        Main.smallFontRenderer.drawString(s, (float)n, (float)n2, n3);
    }
    
    public static void drawToggleModule(final String s) {
        final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
        drawStringWithRect(s, scaledResolution.getScaledWidth() + 2 - RenderUtils.splashTickPos, scaledResolution.getScaledHeight() - 10, ClickGui.getColor(), ColorUtils.color(0.0f, 0.0f, 0.0f, 0.0f), ColorUtils.color(0.0f, 0.0f, 0.0f, 0.5f));
        if (RenderUtils.splashTimer.isDelay(10L)) {
            RenderUtils.splashTimer.setLastMS();
            if (RenderUtils.isSplash) {
                ++RenderUtils.splashTickPos;
                if (RenderUtils.splashTickPos == Wrapper.INSTANCE.fontRenderer().getStringWidth(s) + 10) {
                    RenderUtils.isSplash = false;
                }
            }
            else if (RenderUtils.splashTickPos > 0) {
                --RenderUtils.splashTickPos;
            }
        }
    }
    
    public static void drawBlockESP(final BlockPos blockPos, final float n, final float n2, final float n3, final double n4) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - Minecraft.getMinecraft().getRenderManager().viewerPosX, blockPos.getY() - Minecraft.getMinecraft().getRenderManager().viewerPosY, blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().viewerPosZ, blockPos.getX() + 1 - Minecraft.getMinecraft().getRenderManager().viewerPosX, blockPos.getY() + 1 - Minecraft.getMinecraft().getRenderManager().viewerPosY, blockPos.getZ() + 1 - Minecraft.getMinecraft().getRenderManager().viewerPosZ);
        drawOutlinedBox(axisAlignedBB, n, n2, n3, 1.0f, n4);
        drawFilledBox(axisAlignedBB, n, n2, n3, 0.3f, n4);
    }
    
    public static double interpolate(final double n, final double n2) {
        return n2 + (n - n2) * Minecraft.getMinecraft().getRenderPartialTicks();
    }
    
    public static void drawHaloESP(final Entity entity, final Color color, final Color color2, final float n, final float n2, final float n3, final boolean b, final float n4, final float n5, final float n6) {
        final AxisAlignedBB renderBoundingBox = entity.getRenderBoundingBox();
        final float n7 = (float)(renderBoundingBox.maxY - renderBoundingBox.minY);
        final float n8 = (float)(renderBoundingBox.maxX - renderBoundingBox.minX);
        final float n9 = (float)(renderBoundingBox.maxZ - renderBoundingBox.minZ);
        final float n10 = (float)renderBoundingBox.minX;
        final float n11 = (float)renderBoundingBox.minY;
        final float n12 = (float)renderBoundingBox.minZ;
        if (n > 0.0f) {
            drawLine3D(n10, n11 + n2, n12, n10 + n8, n11 + n2, n12, n, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, 1.0f);
            drawLine3D(n10, n11 + n2, n12, n10, n11 + n2, n12 + n9, n, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, 1.0f);
            drawLine3D(n10 + n8, n11 + n2, n12, n10 + n8, n11 + n2, n12 + n9, n, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, 1.0f);
            drawLine3D(n10, n11 + n2, n12 + n9, n10 + n8, n11 + n2, n12 + n9, n, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, 1.0f);
        }
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glDisable(2884);
        GL11.glDisable(3008);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        if (b) {
            buffer.pos(renderBoundingBox.minX - RenderUtils.mc.getRenderManager().viewerPosX, renderBoundingBox.minY + n2 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n4 / 100.0f).endVertex();
            buffer.pos(renderBoundingBox.maxX - RenderUtils.mc.getRenderManager().viewerPosX, renderBoundingBox.minY + n2 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n4 / 100.0f).endVertex();
            buffer.pos(renderBoundingBox.maxX - RenderUtils.mc.getRenderManager().viewerPosX, renderBoundingBox.minY + n2 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n4 / 100.0f).endVertex();
            buffer.pos(renderBoundingBox.minX - RenderUtils.mc.getRenderManager().viewerPosX, renderBoundingBox.minY + n2 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n4 / 100.0f).endVertex();
        }
        buffer.pos(renderBoundingBox.minX - RenderUtils.mc.getRenderManager().viewerPosX, renderBoundingBox.minY + n2 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n5 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.minX - RenderUtils.mc.getRenderManager().viewerPosX, (renderBoundingBox.minY + renderBoundingBox.maxY) / 2.0 + n3 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, n6 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.maxX - RenderUtils.mc.getRenderManager().viewerPosX, (renderBoundingBox.minY + renderBoundingBox.maxY) / 2.0 + n3 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, n6 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.maxX - RenderUtils.mc.getRenderManager().viewerPosX, renderBoundingBox.minY + n2 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n5 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.maxX - RenderUtils.mc.getRenderManager().viewerPosX, renderBoundingBox.minY + n2 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n5 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.maxX - RenderUtils.mc.getRenderManager().viewerPosX, (renderBoundingBox.minY + renderBoundingBox.maxY) / 2.0 + n3 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, n6 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.maxX - RenderUtils.mc.getRenderManager().viewerPosX, (renderBoundingBox.minY + renderBoundingBox.maxY) / 2.0 + n3 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, n6 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.maxX - RenderUtils.mc.getRenderManager().viewerPosX, renderBoundingBox.minY + n2 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n5 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.minX - RenderUtils.mc.getRenderManager().viewerPosX, renderBoundingBox.minY + n2 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n5 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.maxX - RenderUtils.mc.getRenderManager().viewerPosX, renderBoundingBox.minY + n2 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n5 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.maxX - RenderUtils.mc.getRenderManager().viewerPosX, (renderBoundingBox.minY + renderBoundingBox.maxY) / 2.0 + n3 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, n6 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.minX - RenderUtils.mc.getRenderManager().viewerPosX, (renderBoundingBox.minY + renderBoundingBox.maxY) / 2.0 + n3 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, n6 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.minX - RenderUtils.mc.getRenderManager().viewerPosX, renderBoundingBox.minY + n2 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n5 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.minX - RenderUtils.mc.getRenderManager().viewerPosX, renderBoundingBox.minY + n2 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n5 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.minX - RenderUtils.mc.getRenderManager().viewerPosX, (renderBoundingBox.minY + renderBoundingBox.maxY) / 2.0 + n3 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, n6 / 100.0f).endVertex();
        buffer.pos(renderBoundingBox.minX - RenderUtils.mc.getRenderManager().viewerPosX, (renderBoundingBox.minY + renderBoundingBox.maxY) / 2.0 + n3 - RenderUtils.mc.getRenderManager().viewerPosY, renderBoundingBox.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, n6 / 100.0f).endVertex();
        instance.draw();
        GL11.glShadeModel(7424);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static float getAnimatedOffset(final float n, final float n2) {
        return (float)Math.sin(System.currentTimeMillis() % 360L / n2) * n;
    }
    
    public static void drawOutlineBox(final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5) {
        final AxisAlignedBB renderBoundingBox = entity.getRenderBoundingBox();
        final float n6 = (float)(renderBoundingBox.maxY - renderBoundingBox.minY);
        final float n7 = (float)(renderBoundingBox.maxX - renderBoundingBox.minX);
        final float n8 = (float)(renderBoundingBox.maxZ - renderBoundingBox.minZ);
        final float n9 = (float)(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n4) - n7 / 2.0f;
        final float n10 = (float)(entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n4);
        final float n11 = (float)(entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n4) - n8 / 2.0f;
        drawLine3D(n9, n10, n11, n9, n10 + n6, n11, n5, n, n2, n3, 1.0f);
        drawLine3D(n9, n10, n11 + n8, n9, n10 + n6, n11 + n8, n5, n, n2, n3, 1.0f);
        drawLine3D(n9 + n7, n10, n11, n9 + n7, n10 + n6, n11, n5, n, n2, n3, 1.0f);
        drawLine3D(n9 + n7, n10, n11 + n8, n9 + n7, n10 + n6, n11 + n8, n5, n, n2, n3, 1.0f);
        drawLine3D(n9, n10, n11, n9 + n7, n10, n11, n5, n, n2, n3, 1.0f);
        drawLine3D(n9, n10, n11, n9, n10, n11 + n8, n5, n, n2, n3, 1.0f);
        drawLine3D(n9 + n7, n10, n11, n9 + n7, n10, n11 + n8, n5, n, n2, n3, 1.0f);
        drawLine3D(n9, n10, n11 + n8, n9 + n7, n10, n11 + n8, n5, n, n2, n3, 1.0f);
        drawLine3D(n9, n10 + n6, n11, n9 + n7, n10 + n6, n11, n5, n, n2, n3, 1.0f);
        drawLine3D(n9, n10 + n6, n11, n9, n10 + n6, n11 + n8, n5, n, n2, n3, 1.0f);
        drawLine3D(n9 + n7, n10 + n6, n11, n9 + n7, n10 + n6, n11 + n8, n5, n, n2, n3, 1.0f);
        drawLine3D(n9, n10 + n6, n11 + n8, n9 + n7, n10 + n6, n11 + n8, n5, n, n2, n3, 1.0f);
    }
    
    public static void drawPlayerESPRect(final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        Exception ex;
        try {
            final double n7 = entity.height;
            final double n8 = entity.width;
            final double viewerPosX = Wrapper.INSTANCE.mc().getRenderManager().viewerPosX;
            final double viewerPosY = Wrapper.INSTANCE.mc().getRenderManager().viewerPosY;
            final double viewerPosZ = Wrapper.INSTANCE.mc().getRenderManager().viewerPosZ;
            final double n9 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n5 - viewerPosX;
            final double n10 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n5 + entity.height / 2.0f - viewerPosY;
            final double n11 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n5 - viewerPosZ;
            final float playerViewY = Wrapper.INSTANCE.mc().getRenderManager().playerViewY;
            final float n12 = 0.0f;
            final boolean b = Wrapper.INSTANCE.mc().getRenderManager().options.thirdPersonView == 2;
            GL11.glPushMatrix();
            GlStateManager.translate(n9, n10, n11);
            GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(-playerViewY, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate((b ? -1 : 1) * n12, 1.0f, 0.0f, 0.0f);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glLineWidth(n6);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glColor4f(n, n2, n3, n4);
            GL11.glBegin(1);
            GL11.glVertex3d(n8 / 2.0, -n7 / 2.0, 0.0);
            GL11.glVertex3d(n8 / 2.0, n7 / 2.0, 0.0);
            GL11.glVertex3d(-n8 / 2.0, n7 / 2.0, 0.0);
            GL11.glVertex3d(-n8 / 2.0, -n7 / 2.0, 0.0);
            GL11.glVertex3d(-n8 / 2.0, n7 / 2.0, 0.0);
            GL11.glVertex3d(n8 / 2.0, n7 / 2.0, 0.0);
            GL11.glVertex3d(-n8 / 2.0, -n7 / 2.0, 0.0);
            GL11.glVertex3d(n8 / 2.0, -n7 / 2.0, 0.0);
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glEnable(3553);
            GL11.glDisable(2848);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
            return;
        }
        catch (Exception ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static void drawESP(final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5) {
        Exception ex;
        try {
            final double viewerPosX = Wrapper.INSTANCE.mc().getRenderManager().viewerPosX;
            final double viewerPosY = Wrapper.INSTANCE.mc().getRenderManager().viewerPosY;
            final double viewerPosZ = Wrapper.INSTANCE.mc().getRenderManager().viewerPosZ;
            final double n6 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n5 - viewerPosX;
            final double n7 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n5 + entity.height / 2.0f - viewerPosY;
            final double n8 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n5 - viewerPosZ;
            final float playerViewY = Wrapper.INSTANCE.mc().getRenderManager().playerViewY;
            final float playerViewX = Wrapper.INSTANCE.mc().getRenderManager().playerViewX;
            final boolean b = Wrapper.INSTANCE.mc().getRenderManager().options.thirdPersonView == 2;
            GL11.glPushMatrix();
            GlStateManager.translate(n6, n7, n8);
            GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(-playerViewY, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate((b ? -1 : 1) * playerViewX, 1.0f, 0.0f, 0.0f);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glLineWidth(1.0f);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glColor4f(n, n2, n3, n4);
            GL11.glBegin(1);
            GL11.glVertex3d(0.0, 1.0, 0.0);
            GL11.glVertex3d(-0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 1.0, 0.0);
            GL11.glVertex3d(0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(-0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.5, 0.5, 0.0);
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glEnable(3553);
            GL11.glDisable(2848);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
            return;
        }
        catch (Exception ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static void drawString2D(final FontRenderer fontRenderer, final String s, final Entity entity, final double n, final double n2, final double n3, final int n4, final float n5, final float n6, final float n7, final float n8, final int n9) {
        Exception ex;
        try {
            if (s != "") {
                final float distance = Wrapper.INSTANCE.player().getDistance(entity);
                final float playerViewY = Wrapper.INSTANCE.mc().getRenderManager().playerViewY;
                final float playerViewX = Wrapper.INSTANCE.mc().getRenderManager().playerViewX;
                final boolean b = Wrapper.INSTANCE.mc().getRenderManager().options.thirdPersonView == 2;
                final float n10 = entity.height + 0.5f;
                if (distance <= 50.0f) {
                    GlStateManager.pushMatrix();
                    GL11.glDisable(2896);
                    GL11.glDisable(2929);
                    GlStateManager.translate(n, n2, n3);
                    GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(-playerViewY, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate((b ? -1 : 1) * playerViewX, 1.0f, 0.0f, 0.0f);
                    if (distance <= 11.0f) {
                        GlStateManager.scale(-0.027f, -0.027f, 0.027f);
                    }
                    else {
                        GlStateManager.scale(-distance / 350.0f, -distance / 350.0f, distance / 350.0f);
                    }
                    GlStateManager.disableLighting();
                    GlStateManager.depthMask(false);
                    GlStateManager.enableBlend();
                    GlStateManager.tryBlendFuncSeparate(GlStateManager$SourceFactor.SRC_ALPHA, GlStateManager$DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager$SourceFactor.ONE, GlStateManager$DestFactor.ZERO);
                    final int n11 = fontRenderer.getStringWidth(s) / 2;
                    GlStateManager.disableTexture2D();
                    final Tessellator instance = Tessellator.getInstance();
                    final BufferBuilder buffer = instance.getBuffer();
                    buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                    buffer.pos((double)(-n11 - 1), (double)(-1 + n9), 0.0).color(n5, n6, n7, n8).endVertex();
                    buffer.pos((double)(-n11 - 1), (double)(8 + n9), 0.0).color(n5, n6, n7, n8).endVertex();
                    buffer.pos((double)(n11 + 1), (double)(8 + n9), 0.0).color(n5, n6, n7, n8).endVertex();
                    buffer.pos((double)(n11 + 1), (double)(-1 + n9), 0.0).color(n5, n6, n7, n8).endVertex();
                    instance.draw();
                    GlStateManager.enableTexture2D();
                    GlStateManager.depthMask(true);
                    fontRenderer.drawString(s, -fontRenderer.getStringWidth(s) / 2, n9, n4);
                    GL11.glEnable(2929);
                    GL11.glEnable(2896);
                    GlStateManager.enableLighting();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                }
            }
            return;
        }
        catch (Exception ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static void drawLine(final double n, final double n2, final double n3, final double n4, final float n5, final float n6, final float n7, final float n8) {
        final Vec3d rotateYaw = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationPitch)).rotateYaw(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationYaw));
        drawLineFromPosToPos(rotateYaw.x, rotateYaw.y + Minecraft.getMinecraft().player.getEyeHeight(), rotateYaw.z, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public static void drawHLine(float n, float n2, final float n3, final int n4) {
        if (n2 < n) {
            final float n5 = n;
            n = n2;
            n2 = n5;
        }
        drawRect(n, n3, n2 + 1.0f, n3 + 1.0f, n4);
    }
    
    public static void DrawRoundedBox(final float n, final float n2, final float n3, final float n4, final int n5) {
        GLUtils.glColor(n5);
        GL11.glEnable(3042);
        GL11.glBegin(7);
        GL11.glVertex2f(n + 10.0f, n2);
        GL11.glVertex2f(n + n3 - 10.0f, n2);
        GL11.glVertex2f(n + n3 - 10.0f, n2 + n4);
        GL11.glVertex2f(n + 10.0f, n2 + n4);
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3042);
        GL11.glBegin(7);
        GL11.glVertex2f(n, n2 + 10.0f);
        GL11.glVertex2f(n + n3, n2 + 10.0f);
        GL11.glVertex2f(n + n3, n2 + n4 - 10.0f);
        GL11.glVertex2f(n, n2 + n4 - 10.0f);
        GL11.glEnd();
        GL11.glDisable(3042);
        DrawFilledCircle(n + 9.7f, n2 + 9.7f, 10.0f, n5);
        DrawFilledCircle(n + n3 - 9.7f, n2 + 9.7f, 10.0f, n5);
        DrawFilledCircle(n + 9.7f, n2 + n4 - 9.7f, 10.0f, n5);
        DrawFilledCircle(n + n3 - 9.7f, n2 + n4 - 9.7f, 10.0f, n5);
    }
    
    public static void drawStringWithRect(final String s, final int n, final int n2, final int n3, final int n4, final int n5) {
        drawBorderedRect(n - 2, n2 - 2, n + Wrapper.INSTANCE.fontRenderer().getStringWidth(s) + 2, n2 + 10, 1.0f, n4, n5);
        Wrapper.INSTANCE.fontRenderer().drawString(s, n, n2, n3);
    }
    
    public static void drawHaloESP(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final boolean b, final float n7, final float n8, final float n9) {
        final float n10 = (float)(axisAlignedBB.maxY - axisAlignedBB.minY);
        final float n11 = (float)(axisAlignedBB.maxX - axisAlignedBB.minX);
        final float n12 = (float)(axisAlignedBB.maxZ - axisAlignedBB.minZ);
        final float n13 = (float)axisAlignedBB.minX;
        final float n14 = (float)axisAlignedBB.minY;
        final float n15 = (float)axisAlignedBB.minZ;
        drawLine3D(n13, n14 + n5, n15, n13 + n11, n14 + n5, n15, n4, n, n2, n3, 1.0f);
        drawLine3D(n13, n14 + n5, n15, n13, n14 + n5, n15 + n12, n4, n, n2, n3, 1.0f);
        drawLine3D(n13 + n11, n14 + n5, n15, n13 + n11, n14 + n5, n15 + n12, n4, n, n2, n3, 1.0f);
        drawLine3D(n13, n14 + n5, n15 + n12, n13 + n11, n14 + n5, n15 + n12, n4, n, n2, n3, 1.0f);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glDisable(2884);
        GL11.glDisable(3008);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        if (b) {
            buffer.pos(axisAlignedBB.minX - RenderUtils.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + n5 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n7 / 100.0f).endVertex();
            buffer.pos(axisAlignedBB.maxX - RenderUtils.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + n5 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n7 / 100.0f).endVertex();
            buffer.pos(axisAlignedBB.maxX - RenderUtils.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + n5 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n7 / 100.0f).endVertex();
            buffer.pos(axisAlignedBB.minX - RenderUtils.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + n5 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n7 / 100.0f).endVertex();
        }
        buffer.pos(axisAlignedBB.minX - RenderUtils.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + n5 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n8 / 100.0f).endVertex();
        buffer.pos(axisAlignedBB.minX - RenderUtils.mc.getRenderManager().viewerPosX, (axisAlignedBB.minY + axisAlignedBB.maxY) / 2.0 + n6 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n9 / 100.0f).endVertex();
        buffer.pos(axisAlignedBB.maxX - RenderUtils.mc.getRenderManager().viewerPosX, (axisAlignedBB.minY + axisAlignedBB.maxY) / 2.0 + n6 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n9 / 100.0f).endVertex();
        buffer.pos(axisAlignedBB.maxX - RenderUtils.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + n5 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n8 / 100.0f).endVertex();
        buffer.pos(axisAlignedBB.maxX - RenderUtils.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + n5 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n8 / 100.0f).endVertex();
        buffer.pos(axisAlignedBB.maxX - RenderUtils.mc.getRenderManager().viewerPosX, (axisAlignedBB.minY + axisAlignedBB.maxY) / 2.0 + n6 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n9 / 100.0f).endVertex();
        buffer.pos(axisAlignedBB.maxX - RenderUtils.mc.getRenderManager().viewerPosX, (axisAlignedBB.minY + axisAlignedBB.maxY) / 2.0 + n6 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n9 / 100.0f).endVertex();
        buffer.pos(axisAlignedBB.maxX - RenderUtils.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + n5 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n8 / 100.0f).endVertex();
        final BufferBuilder pos = buffer.pos(axisAlignedBB.minX - RenderUtils.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + n5 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ);
        while (true) {
            switch (-735237818 - 1036215989 + 1) {
                case 1827471600: {
                    continue;
                }
                case 370257933: {
                    pos.color(n, n2, n3, n8 / 100.0f).endVertex();
                    buffer.pos(axisAlignedBB.maxX - RenderUtils.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + n5 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n8 / 100.0f).endVertex();
                    buffer.pos(axisAlignedBB.maxX - RenderUtils.mc.getRenderManager().viewerPosX, (axisAlignedBB.minY + axisAlignedBB.maxY) / 2.0 + n6 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n9 / 100.0f).endVertex();
                    buffer.pos(axisAlignedBB.minX - RenderUtils.mc.getRenderManager().viewerPosX, (axisAlignedBB.minY + axisAlignedBB.maxY) / 2.0 + n6 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n9 / 100.0f).endVertex();
                    final BufferBuilder bufferBuilder = buffer;
                    final double n16 = axisAlignedBB.minX - RenderUtils.mc.getRenderManager().viewerPosX;
                    final double n17 = axisAlignedBB.minY + n5;
                    final RenderManager renderManager = RenderUtils.mc.getRenderManager();
                    while (true) {
                        switch (-1908103677 + 808427406 + 1) {
                            case 201891912: {
                                continue;
                            }
                            case -1100269171: {
                                bufferBuilder.pos(n16, n17 - renderManager.viewerPosY, axisAlignedBB.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n8 / 100.0f).endVertex();
                                buffer.pos(axisAlignedBB.minX - RenderUtils.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + n5 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n8 / 100.0f).endVertex();
                                buffer.pos(axisAlignedBB.minX - RenderUtils.mc.getRenderManager().viewerPosX, (axisAlignedBB.minY + axisAlignedBB.maxY) / 2.0 + n6 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n9 / 100.0f).endVertex();
                                buffer.pos(axisAlignedBB.minX - RenderUtils.mc.getRenderManager().viewerPosX, (axisAlignedBB.minY + axisAlignedBB.maxY) / 2.0 + n6 - RenderUtils.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtils.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n9 / 100.0f).endVertex();
                                instance.draw();
                                GL11.glShadeModel(7424);
                                GL11.glDisable(3042);
                                GL11.glEnable(3008);
                                GL11.glEnable(2884);
                                GL11.glEnable(3553);
                                GlStateManager.depthMask(true);
                                GlStateManager.enableDepth();
                                GlStateManager.enableTexture2D();
                                GlStateManager.disableBlend();
                                GlStateManager.popMatrix();
                                return;
                            }
                            default: {
                                throw null;
                            }
                        }
                    }
                    break;
                }
                default: {
                    throw null;
                }
            }
        }
    }
    
    public static void drawSolidBox(final AxisAlignedBB axisAlignedBB) {
        GL11.glBegin(7);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glEnd();
    }
    
    public static void drawLineToEntity(final Entity entity, final float n, final float n2, final float n3, final float n4) {
        final double[] interpolate = interpolate(entity);
        drawLine(interpolate[0], interpolate[1] + entity.height / 2.0f, interpolate[2], 0.0, n, n2, n3, n4);
    }
    
    public static void drawFilledBox(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final float n3, final float n4, final double n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        final BufferBuilder bufferBuilder = buffer;
        final double maxX = axisAlignedBB.maxX;
        while (true) {
            switch (1326003970 + 149794423 + 1) {
                case 1068921823: {
                    continue;
                }
                case 1206163829: {
                    bufferBuilder.pos(maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                    final BufferBuilder bufferBuilder2 = buffer;
                    while (true) {
                        switch (-2001749343 + 1752396402 + 1) {
                            case -1790377281: {
                                continue;
                            }
                            default: {
                                bufferBuilder2.pos(axisAlignedBB.minX, axisAlignedBB.minY + n5, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n5, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY + n5, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY + n5, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n5, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY + n5, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY + n5, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY + n5, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY + n5, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n5, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n5, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
                                buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n5, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
                                instance.draw();
                                GlStateManager.depthMask(true);
                                GlStateManager.enableDepth();
                                GlStateManager.enableTexture2D();
                                GlStateManager.disableBlend();
                                GlStateManager.popMatrix();
                                return;
                            }
                            case -39021563: {
                                throw null;
                            }
                        }
                    }
                    break;
                }
                default: {
                    throw null;
                }
            }
        }
    }
    
    public static void drawOutlinedBox(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final float n3, final float n4, final double n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(1.0f);
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, 0.0f).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n5, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY + n5, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY + n5, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n5, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n5, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n5, axisAlignedBB.maxZ).color(n, n2, n3, 0.0f).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY + n5, axisAlignedBB.maxZ).color(n, n2, n3, 0.0f).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY + n5, axisAlignedBB.minZ).color(n, n2, n3, 0.0f).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, 0.0f).endVertex();
        instance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static String DF(final Number obj, final int maximumFractionDigits) {
        final DecimalFormat decimalFormat = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        decimalFormat.setMaximumFractionDigits(maximumFractionDigits);
        return decimalFormat.format(obj);
    }
    
    public static void drawTri(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final Color color) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GLUtils.glColor(color);
        GL11.glLineWidth((float)n7);
        GL11.glBegin(3);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n3, n4);
        GL11.glVertex2d(n5, n6);
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
    }
    
    public static void drawLineFromPosToPos(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final float n8, final float n9, final float n10, final float n11) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glLineWidth(Tracers.lWidth);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(n8, n9, n10, n11);
        GL11.glLoadIdentity();
        Minecraft.getMinecraft().entityRenderer.orientCamera(Minecraft.getMinecraft().getRenderPartialTicks());
        GL11.glBegin(1);
        GL11.glVertex3d(n, n2, n3);
        GL11.glVertex3d(n4, n5, n6);
        GL11.glVertex3d(n4, n5, n6);
        GL11.glVertex3d(n4, n5 + n7, n6);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glColor3d(1.0, 1.0, 1.0);
    }
    
    public static void drawLogoutSpot(final String s, final double n, final double n2, final double n3, final float n4, final float n5, final float n6, final double n7) {
        drawOutlinedBox(new AxisAlignedBB(n - Minecraft.getMinecraft().getRenderManager().viewerPosX, n2 - Minecraft.getMinecraft().getRenderManager().viewerPosY, n3 - Minecraft.getMinecraft().getRenderManager().viewerPosZ, n + 1.0 - Minecraft.getMinecraft().getRenderManager().viewerPosX, n2 + 1.0 - Minecraft.getMinecraft().getRenderManager().viewerPosY, n3 + 1.0 - Minecraft.getMinecraft().getRenderManager().viewerPosZ), n4, n5, n6, 1.0f, n7);
    }
    
    public static void drawXRayBlocks(final LinkedList list, final float n) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        final WorldClient world = Wrapper.INSTANCE.world();
        final EntityPlayerSP player = Wrapper.INSTANCE.player();
        for (final XRayBlock xRayBlock : list) {
            final BlockPos blockPos = xRayBlock.getBlockPos();
            final XRayData getxRayData = xRayBlock.getxRayData();
            final IBlockState blockState = world.getBlockState(blockPos);
            final double n2 = player.lastTickPosX + (player.posX - player.lastTickPosX) * n;
            final double n3 = player.lastTickPosY + (player.posY - player.lastTickPosY) * n;
            final double n4 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * n;
            GLUtils.glColor(new Color(getxRayData.getRed(), getxRayData.getGreen(), getxRayData.getBlue(), 255).getRGB());
            drawSelectionBoundingBox(blockState.getSelectedBoundingBox((World)world, blockPos).grow(0.0020000000949949026).offset(-n2, -n3, -n4));
        }
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static double[] interpolate(final Entity entity) {
        return new double[] { interpolate(entity.posX, entity.lastTickPosX) - Minecraft.getMinecraft().getRenderManager().renderPosX, interpolate(entity.posY, entity.lastTickPosY) - Minecraft.getMinecraft().getRenderManager().renderPosY, interpolate(entity.posZ, entity.lastTickPosZ) - Minecraft.getMinecraft().getRenderManager().renderPosZ };
    }
    
    public static void drawNukerBlocks(final Iterable iterable, final float n, final float n2, final float n3, final double n4) {
        Wrapper.INSTANCE.world();
        Wrapper.INSTANCE.player();
        final Iterator<BlockPos> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            drawBlockESP(iterator.next(), n, n2, n3, n4);
        }
    }
    
    public static void drawOutlineESP(final BlockPos blockPos, final float n, final float n2, final float n3, final double n4) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - Minecraft.getMinecraft().getRenderManager().viewerPosX, blockPos.getY() - Minecraft.getMinecraft().getRenderManager().viewerPosY, blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().viewerPosZ, blockPos.getX() + 1 - Minecraft.getMinecraft().getRenderManager().viewerPosX, blockPos.getY() + 1 - Minecraft.getMinecraft().getRenderManager().viewerPosY, blockPos.getZ() + 1 - Minecraft.getMinecraft().getRenderManager().viewerPosZ);
        drawOutlinedBox(axisAlignedBB, n, n2, n3, 1.0f, n4);
        drawFilledBox(axisAlignedBB, n, n2, n3, 0.3f, n4);
    }
    
    public static float[][] getBipedRotations(final ModelBiped modelBiped) {
        return new float[][] { { modelBiped.bipedHead.rotateAngleX, modelBiped.bipedHead.rotateAngleY, modelBiped.bipedHead.rotateAngleZ }, { modelBiped.bipedRightArm.rotateAngleX, modelBiped.bipedRightArm.rotateAngleY, modelBiped.bipedRightArm.rotateAngleZ }, { modelBiped.bipedLeftArm.rotateAngleX, modelBiped.bipedLeftArm.rotateAngleY, modelBiped.bipedLeftArm.rotateAngleZ }, { modelBiped.bipedRightLeg.rotateAngleX, modelBiped.bipedRightLeg.rotateAngleY, modelBiped.bipedRightLeg.rotateAngleZ }, { modelBiped.bipedLeftLeg.rotateAngleX, modelBiped.bipedLeftLeg.rotateAngleY, modelBiped.bipedLeftLeg.rotateAngleZ } };
    }
    
    public static void drawESPDiamond(final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        Exception ex;
        try {
            final double viewerPosX = Wrapper.INSTANCE.mc().getRenderManager().viewerPosX;
            final double viewerPosY = Wrapper.INSTANCE.mc().getRenderManager().viewerPosY;
            final double viewerPosZ = Wrapper.INSTANCE.mc().getRenderManager().viewerPosZ;
            final double n7 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n5 - viewerPosX;
            final double n8 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n5 + entity.height / 2.0f - viewerPosY;
            final double n9 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n5 - viewerPosZ;
            final float playerViewY = Wrapper.INSTANCE.mc().getRenderManager().playerViewY;
            final float playerViewX = Wrapper.INSTANCE.mc().getRenderManager().playerViewX;
            final boolean b = Wrapper.INSTANCE.mc().getRenderManager().options.thirdPersonView == 2;
            GL11.glPushMatrix();
            GlStateManager.translate(n7, n8, n9);
            GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(-playerViewY, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate((b ? -1 : 1) * playerViewX, 1.0f, 0.0f, 0.0f);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glLineWidth(n6);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glColor4f(n, n2, n3, n4);
            GL11.glBegin(1);
            GL11.glVertex3d(0.0, 1.0, 0.0);
            GL11.glVertex3d(-0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 1.0, 0.0);
            GL11.glVertex3d(0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(-0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.5, 0.5, 0.0);
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glEnable(3553);
            GL11.glDisable(2848);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
            return;
        }
        catch (Exception ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static void DrawFilledCircle(final float n, final float n2, final float n3, final int n4) {
        GL11.glShadeModel(7425);
        GL11.glBegin(6);
        GLUtils.glColor(n4);
        float n5 = n2;
        float n6 = n;
        final double n7 = 3.141592653589793;
        for (int i = 0; i <= 360; ++i) {
            final float n8 = i / (180.0f / (float)n7);
            final float n9 = n + (float)Math.cos(n8) * n3;
            final float n10 = n2 + (float)Math.sin(n8) * n3;
            GL11.glVertex2f(n, n2);
            GL11.glVertex2f(n6, n5);
            GL11.glVertex2f(n9, n10);
            n5 = n10;
            n6 = n9;
        }
        GL11.glEnd();
    }
    
    public static void drawSelectionBoundingBox(final AxisAlignedBB axisAlignedBB) {
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        buffer.begin(3, DefaultVertexFormats.POSITION);
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        instance.draw();
        buffer.begin(3, DefaultVertexFormats.POSITION);
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        instance.draw();
        buffer.begin(1, DefaultVertexFormats.POSITION);
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        instance.draw();
    }
    
    static {
        RenderUtils.DEFAULT_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        RenderUtils.splashTimer = new TimerUtils();
        RenderUtils.splashTickPos = 0;
        RenderUtils.isSplash = false;
        RenderUtils.mc = Minecraft.getMinecraft();
    }
}
