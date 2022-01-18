/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import java.util.HashMap;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.utils.EntityUtils;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import java.awt.Color;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import net.minecraft.client.model.ModelBiped;
import com.krazzzzymonkey.catalyst.events.RenderModelEntityLivingEvent;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import net.minecraft.entity.player.EntityPlayer;
import java.util.Map;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class SkeletonESP extends Modules
{
    public ColorValue friendColorValue;
    public BooleanValue friendRainbow;
    public ColorValue colorValue;
    public Map<EntityPlayer, float[][]> rotationList;
    public BooleanValue rainbow;
    public DoubleValue lineWidth;
    
    @Override
    public void onDisable() {
        this.rotationList.clear();
        super.onDisable();
    }
    
    @SubscribeEvent
    public void onRenderModel(final RenderModelEntityLivingEvent renderModelEntityLivingEvent) {
        if (renderModelEntityLivingEvent.getEntityLivingBase() instanceof EntityPlayer) {
            if (renderModelEntityLivingEvent.getModelBase() instanceof ModelBiped) {
                this.rotationList.put((EntityPlayer)renderModelEntityLivingEvent.getEntityLivingBase(), RenderUtils.getBipedRotations((ModelBiped)renderModelEntityLivingEvent.getModelBase()));
            }
        }
    }
    
    public void renderSkeleton(final EntityPlayer entityPlayer, final float[][] array, final Color color) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.pushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(this.lineWidth.getValue().floatValue());
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        final Vec3d interpolatedLinearVec = EntityUtils.getInterpolatedLinearVec((Entity)entityPlayer, SkeletonESP.mc.getRenderPartialTicks());
        GlStateManager.translate(interpolatedLinearVec.x - SkeletonESP.mc.getRenderManager().renderPosX, interpolatedLinearVec.y - SkeletonESP.mc.getRenderManager().renderPosY, interpolatedLinearVec.z - SkeletonESP.mc.getRenderManager().renderPosZ);
        GlStateManager.rotate(-entityPlayer.renderYawOffset, 0.0f, 1.0f, 0.0f);
        GlStateManager.translate(0.0, 0.0, entityPlayer.isSneaking() ? -0.235 : 0.0);
        final float n = entityPlayer.isSneaking() ? 0.6f : 0.75f;
        GlStateManager.pushMatrix();
        GlStateManager.translate(-0.125, (double)n, 0.0);
        if (array[3][0] != 0.0f) {
            GlStateManager.rotate(array[3][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if (array[3][1] != 0.0f) {
            GlStateManager.rotate(array[3][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (array[3][2] != 0.0f) {
            GlStateManager.rotate(array[3][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, (double)(-n), 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.125, (double)n, 0.0);
        if (array[4][0] != 0.0f) {
            GlStateManager.rotate(array[4][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if (array[4][1] != 0.0f) {
            GlStateManager.rotate(array[4][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (array[4][2] != 0.0f) {
            GlStateManager.rotate(array[4][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, (double)(-n), 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.translate(0.0, 0.0, entityPlayer.isSneaking() ? 0.25 : 0.0);
        GlStateManager.pushMatrix();
        double n2 = 0.0;
        if (entityPlayer.isSneaking()) {
            n2 = -0.05;
        }
        GlStateManager.translate(0.0, n2, entityPlayer.isSneaking() ? -0.01725 : 0.0);
        GlStateManager.pushMatrix();
        GlStateManager.translate(-0.375, n + 0.55, 0.0);
        if (array[1][0] != 0.0f) {
            GlStateManager.rotate(array[1][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if (array[1][1] != 0.0f) {
            GlStateManager.rotate(array[1][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (array[1][2] != 0.0f) {
            GlStateManager.rotate(-array[1][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, -0.5, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.375, n + 0.55, 0.0);
        if (array[2][0] != 0.0f) {
            GlStateManager.rotate(array[2][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if (array[2][1] != 0.0f) {
            GlStateManager.rotate(array[2][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (array[2][2] != 0.0f) {
            GlStateManager.rotate(-array[2][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, -0.5, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, n + 0.55, 0.0);
        if (array[0][0] != 0.0f) {
            GlStateManager.rotate(array[0][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, 0.3, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
        GlStateManager.rotate(entityPlayer.isSneaking() ? 25.0f : 0.0f, 1.0f, 0.0f, 0.0f);
        if (entityPlayer.isSneaking()) {
            n2 = -0.16175;
        }
        GlStateManager.translate(0.0, n2, entityPlayer.isSneaking() ? -0.48025 : 0.0);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, (double)n, 0.0);
        GlStateManager.glBegin(3);
        GL11.glVertex3d(-0.125, 0.0, 0.0);
        GL11.glVertex3d(0.125, 0.0, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, (double)n, 0.0);
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, 0.55, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, n + 0.55, 0.0);
        GlStateManager.glBegin(3);
        GL11.glVertex3d(-0.375, 0.0, 0.0);
        GL11.glVertex3d(0.375, 0.0, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GlStateManager.popMatrix();
    }
    
    public SkeletonESP() {
        super("SkeletonESP", ModuleCategory.RENDER, "Renders a skeleton for players");
        this.rotationList = new HashMap<EntityPlayer, float[][]>();
        this.lineWidth = new DoubleValue("LineWidth", 3.0, 1.0, 10.0);
        this.colorValue = new ColorValue("Color", Color.RED);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.friendColorValue = new ColorValue("FriendColor", Color.CYAN);
        this.friendRainbow = new BooleanValue("FriendRainbow", false);
        this.addValue(this.lineWidth, this.colorValue, this.rainbow, this.friendColorValue, this.friendRainbow);
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        Color color = this.colorValue.getColor();
        Color color2 = this.friendColorValue.getColor();
        if (this.rainbow.getValue()) {
            color = ColorUtils.rainbow();
        }
        if (this.friendRainbow.getValue()) {
            color2 = ColorUtils.rainbow();
        }
        for (final EntityPlayer entityPlayer : SkeletonESP.mc.world.playerEntities) {
            if (entityPlayer != null) {
                if (entityPlayer == SkeletonESP.mc.getRenderViewEntity()) {
                    continue;
                }
                if (!entityPlayer.isEntityAlive() || entityPlayer.isPlayerSleeping() || entityPlayer.isElytraFlying()) {
                    continue;
                }
                if (this.rotationList.get(entityPlayer) == null) {
                    continue;
                }
                if (SkeletonESP.mc.player.getDistanceSq((Entity)entityPlayer) >= 2500.0) {
                    continue;
                }
                this.renderSkeleton(entityPlayer, this.rotationList.get(entityPlayer), FriendManager.friendsList.contains(entityPlayer.getName()) ? color2 : color);
            }
        }
    }
}
