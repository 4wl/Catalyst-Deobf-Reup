/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.lwjgl.opengl.GL11;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.gui.ScaledResolution;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.Number;
import com.krazzzzymonkey.catalyst.module.Modules;

public class PlayerRadar extends Modules
{
    public int finalMouseY;
    public int finalMouseX;
    public boolean isDragging;
    public boolean isAlreadyDragging;
    public Number xOffset;
    public Number yOffset;
    public IntegerValue amount;
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        int intValue = (int)this.yOffset.getValue();
        int intValue2 = (int)this.xOffset.getValue();
        final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
        int n = 0;
        for (final EntityPlayerSP next : Wrapper.INSTANCE.world().loadedEntityList) {
            if (next instanceof EntityPlayer) {
                if (next == Minecraft.getMinecraft().player) {
                    continue;
                }
                if (++n > this.amount.getValue()) {
                    return;
                }
                final EntityPlayer entityPlayer = (EntityPlayer)next;
                final float distance = Wrapper.INSTANCE.player().getDistance((Entity)entityPlayer);
                final float n2 = entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount();
                new StringBuilder().append(" §2[").append(RenderUtils.DF(n2, 0)).append("] ").toString();
                String str;
                if (n2 >= 12.0) {
                    str = " §2[" + RenderUtils.DF(n2, 0) + "] ";
                }
                else if (n2 >= 4.0) {
                    str = " §6[" + RenderUtils.DF(n2, 0) + "] ";
                }
                else {
                    str = " §4[" + RenderUtils.DF(n2, 0) + "] ";
                }
                final String string = entityPlayer.getGameProfile().getName() + str + "§7[" + RenderUtils.DF(distance, 0) + "]";
                int n3;
                if (entityPlayer.isInvisible()) {
                    n3 = ColorUtils.color(155, 155, 155, 255);
                }
                else {
                    n3 = ColorUtils.color(255, 255, 255, 255);
                }
                GL11.glPushMatrix();
                if (ModuleManager.getModule("CustomFont").isToggled()) {
                    if (intValue2 > scaledResolution.getScaledWidth() / 2) {
                        intValue2 = (int)this.xOffset.getValue() - Main.fontRenderer.getStringWidth(string);
                    }
                    Main.fontRenderer.drawStringWithShadow(string, intValue2, intValue, n3);
                }
                else {
                    if (intValue2 > scaledResolution.getScaledWidth() / 2) {
                        intValue2 = (int)this.xOffset.getValue() - Wrapper.INSTANCE.fontRenderer().getStringWidth(string);
                    }
                    Wrapper.INSTANCE.fontRenderer().drawString(string, intValue2, intValue, n3);
                }
                if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
                    if (ModuleManager.getModule("CustomFont").isToggled()) {
                        RenderUtils.drawRect((float)intValue2, (float)intValue, (float)(intValue2 + Main.fontRenderer.getStringWidth(string)), (float)(intValue + 12), ColorUtils.color(0, 0, 0, 100));
                    }
                    else {
                        RenderUtils.drawRect((float)intValue2, (float)intValue, (float)(intValue2 + Wrapper.INSTANCE.fontRenderer().getStringWidth(string)), (float)(intValue + 12), ColorUtils.color(0, 0, 0, 100));
                    }
                    if (MouseUtils.isLeftClicked()) {
                        if (!MouseUtils.isMouseOver(intValue2, intValue2 + Main.fontRenderer.getStringWidth(string), intValue, intValue + 12)) {
                            this.isAlreadyDragging = true;
                        }
                    }
                    if (!MouseUtils.isLeftClicked() && this.isAlreadyDragging) {
                        this.isAlreadyDragging = false;
                    }
                    if (!this.isAlreadyDragging || this.isDragging) {
                        if (MouseUtils.isMouseOver(intValue2, intValue2 + Main.fontRenderer.getStringWidth(string), intValue, intValue + 12)) {
                            this.isDragging = true;
                        }
                        if (MouseUtils.isLeftClicked() && this.isDragging) {
                            this.finalMouseX = MouseUtils.getMouseX();
                            this.finalMouseY = MouseUtils.getMouseY();
                            this.xOffset.value = this.finalMouseX;
                            this.yOffset.value = this.finalMouseY;
                            MouseUtils.isDragging = true;
                        }
                        else {
                            this.isDragging = false;
                        }
                    }
                }
                if ((int)this.yOffset.getValue() > scaledResolution.getScaledHeight() / 2) {
                    intValue -= 12;
                }
                else {
                    intValue += 12;
                }
                GL11.glPopMatrix();
            }
        }
    }
    
    public PlayerRadar() {
        super("PlayerRadar", ModuleCategory.HUD, "Shows players in render distance on hud", true);
        this.finalMouseX = 0;
        this.finalMouseY = 0;
        this.isDragging = false;
        this.isAlreadyDragging = false;
        this.xOffset = new Number("X Offset", 100);
        this.yOffset = new Number("Y Offset", 30);
        this.amount = new IntegerValue("Players", 30, 1, 100);
        this.addValue(this.amount, this.xOffset, this.yOffset);
    }
}
