/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.AxisAlignedBB;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.util.math.Vec3d;
import net.minecraft.item.ItemPotion;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.math.MathHelper;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemLingeringPotion;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemBow;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Trajectories extends Modules
{
    public ColorValue color;
    public BooleanValue rainbow;
    
    public Trajectories() {
        super("Trajectories", ModuleCategory.RENDER, "Shows where trajectories are going to land");
        this.color = new ColorValue("Color", Color.CYAN);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.addValue(this.color, this.rainbow);
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        final EntityPlayerSP player = Wrapper.INSTANCE.player();
        final float rotationYaw = Wrapper.INSTANCE.player().rotationYaw;
        final float rotationPitch = Wrapper.INSTANCE.player().rotationPitch;
        final ItemStack currentItem = player.inventory.getCurrentItem();
        if (currentItem == null) {
            return;
        }
        final Item item = currentItem.getItem();
        if (!(item instanceof ItemBow)) {
            if (!(item instanceof ItemSnowball)) {
                if (!(item instanceof ItemEgg) && !(item instanceof ItemEnderPearl) && !(item instanceof ItemSplashPotion) && !(item instanceof ItemLingeringPotion) && !(item instanceof ItemFishingRod) && !(item instanceof ItemExpBottle)) {
                    return;
                }
            }
        }
        final boolean b = player.inventory.getCurrentItem().getItem() instanceof ItemBow;
        double n = player.lastTickPosX + (player.posX - player.lastTickPosX) * renderWorldLastEvent.getPartialTicks() - MathHelper.cos((float)Math.toRadians(rotationYaw)) * 0.16f;
        double n2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * renderWorldLastEvent.getPartialTicks() + player.getEyeHeight() - 0.1;
        double n3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * renderWorldLastEvent.getPartialTicks() - MathHelper.sin((float)Math.toRadians(rotationYaw)) * 0.16f;
        final float n4 = b ? 1.0f : 0.4f;
        final float n5 = (float)Math.toRadians(rotationYaw);
        final float n6 = (float)Math.toRadians(rotationPitch);
        final float n7 = -MathHelper.sin(n5) * MathHelper.cos(n6) * n4;
        final float n8 = -MathHelper.sin(n6) * n4;
        final float n9 = MathHelper.cos(n5) * MathHelper.cos(n6) * n4;
        final double sqrt = Math.sqrt(n7 * n7 + n8 * n8 + n9 * n9);
        final float n10 = (float)(n7 / sqrt);
        final float n11 = (float)(n8 / sqrt);
        final float n12 = (float)(n9 / sqrt);
        float n16;
        float n17;
        float n18;
        if (b) {
            final float n13 = (72000 - player.getItemInUseCount()) / 20.0f;
            float n14 = (n13 * n13 + n13 * 2.0f) / 3.0f;
            if (n14 > 1.0f) {
                n14 = 1.0f;
            }
            if (n14 <= 0.1f) {
                n14 = 1.0f;
            }
            final float n15 = n14 * 3.0f;
            n16 = n10 * n15;
            n17 = n11 * n15;
            n18 = n12 * n15;
        }
        else {
            n16 = (float)(n10 * 1.5);
            n17 = (float)(n11 * 1.5);
            n18 = (float)(n12 * 1.5);
        }
        GL11.glPushMatrix();
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glEnable(32925);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1.8f);
        final RenderManager renderManager = Wrapper.INSTANCE.mc().getRenderManager();
        final double n19 = b ? 0.05 : ((item instanceof ItemPotion || item instanceof ItemExpBottle) ? 0.4 : ((item instanceof ItemFishingRod) ? 0.15 : 0.03));
        final Vec3d vec3d = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        if (this.rainbow.getValue()) {
            GL11.glColor3d((double)(ColorUtils.rainbow().getRed() / 255.0f), (double)(ColorUtils.rainbow().getGreen() / 255.0f), (double)(ColorUtils.rainbow().getBlue() / 255.0f));
        }
        else {
            GL11.glColor3d((double)(this.color.getColor().getRed() / 255.0f), (double)(this.color.getColor().getGreen() / 255.0f), (double)(this.color.getColor().getBlue() / 255.0f));
        }
        GL11.glBegin(3);
        for (int i = 0; i < 1000; ++i) {
            GL11.glVertex3d(n - renderManager.viewerPosX, n2 - renderManager.viewerPosY, n3 - renderManager.viewerPosZ);
            n += n16 * 0.1;
            n2 += n17 * 0.1;
            n3 += n18 * 0.1;
            n16 *= (float)0.999;
            final float n20 = (float)(n17 * 0.999);
            n18 *= (float)0.999;
            n17 = (float)(n20 - n19 * 0.1);
            if (Wrapper.INSTANCE.world().rayTraceBlocks(vec3d, new Vec3d(n, n2, n3)) != null) {
                break;
            }
        }
        GL11.glEnd();
        final double n21 = n - renderManager.viewerPosX;
        final double n22 = n2 - renderManager.viewerPosY;
        final double n23 = n3 - renderManager.viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n21 - 0.5, n22 - 0.5, n23 - 0.5, n21 + 0.5, n22 + 0.5, n23 + 0.5);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.15f);
        GL11.glColor4d(1.0, 1.0, 1.0, 0.5);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(32925);
        GL11.glDepthMask(true);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
}
