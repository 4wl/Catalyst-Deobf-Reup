/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.lwjgl.opengl.GL11;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.client.gui.ScaledResolution;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity$Action;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.network.play.client.CPacketUseEntity;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.utils.CrystalClickCounter;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.Number;
import com.krazzzzymonkey.catalyst.module.Modules;

public class CrystalPlaceSpeed extends Modules
{
    public boolean isDragging;
    public int finalMouseX;
    public Number xOffset;
    public ColorValue colorValue;
    public CrystalClickCounter placeSpeed;
    public static int color;
    public CrystalClickCounter breakSpeed;
    public BooleanValue rainbow;
    public boolean isAlreadyDragging;
    public int finalMouseY;
    public Number yOffset;
    
    public CrystalPlaceSpeed() {
        super("CrystalsPerSecond", ModuleCategory.HUD, "Shows you how many crystals you explode every second", true);
        this.placeSpeed = new CrystalClickCounter();
        this.breakSpeed = new CrystalClickCounter();
        this.finalMouseX = 0;
        this.finalMouseY = 0;
        this.isDragging = false;
        this.isAlreadyDragging = false;
        this.colorValue = new ColorValue("Color", Color.CYAN);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.xOffset = new Number("X Offset", 0);
        this.yOffset = new Number("Y Offset", 0);
        this.addValue(this.colorValue, this.rainbow, this.xOffset, this.yOffset);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        final Packet<?> packet = packetEvent$Out.packet;
        if (packet instanceof CPacketUseEntity) {
            final CPacketUseEntity cPacketUseEntity = (CPacketUseEntity)packet;
            if (cPacketUseEntity.getEntityFromWorld(CrystalPlaceSpeed.mc.player.world) instanceof EntityEnderCrystal && cPacketUseEntity.getAction() == CPacketUseEntity$Action.ATTACK) {
                this.breakSpeed.onBreak();
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
        if (!(boolean)this.rainbow.getValue()) {
            CrystalPlaceSpeed.color = this.colorValue.getColor().getRGB();
        }
        else {
            CrystalPlaceSpeed.color = ColorUtils.rainbow().getRGB();
        }
        GL11.glPushMatrix();
        final String string = "Crystals Per Second:§f " + this.breakSpeed.getCps();
        final int intValue = (int)this.yOffset.getValue();
        final int intValue2 = (int)this.xOffset.getValue();
        if (ModuleManager.getModule("CustomFont").isToggled()) {
            Main.fontRenderer.drawStringWithShadow(string, (float)intValue2, (float)intValue, CrystalPlaceSpeed.color);
        }
        else {
            Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(string, (float)intValue2, (float)intValue, CrystalPlaceSpeed.color);
        }
        if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
            try {
                if (ModuleManager.getModule("CustomFont").isToggled()) {
                    RenderUtils.drawRect((float)intValue2, (float)intValue, (float)(intValue2 + Main.fontRenderer.getStringWidth(string)), (float)(intValue + 14), ColorUtils.color(0, 0, 0, 100));
                }
                else {
                    RenderUtils.drawRect((float)intValue2, (float)intValue, (float)(intValue2 + Wrapper.INSTANCE.fontRenderer().getStringWidth(string)), (float)(intValue + 14), ColorUtils.color(0, 0, 0, 100));
                }
                if (MouseUtils.isLeftClicked() && !MouseUtils.isMouseOver(intValue2, intValue2 + Main.fontRenderer.getStringWidth(string), intValue, intValue + 14)) {
                    this.isAlreadyDragging = true;
                }
                if (!MouseUtils.isLeftClicked() && this.isAlreadyDragging) {
                    this.isAlreadyDragging = false;
                }
                if (!this.isAlreadyDragging || this.isDragging) {
                    if (MouseUtils.isMouseOver(intValue2, intValue2 + Main.fontRenderer.getStringWidth(string), intValue, intValue + 14)) {
                        this.isDragging = true;
                    }
                    if (MouseUtils.isLeftClicked() && this.isDragging) {
                        this.finalMouseX = MouseUtils.getMouseX();
                        this.finalMouseY = MouseUtils.getMouseY();
                        this.xOffset.value = this.finalMouseX - 30;
                        this.yOffset.value = this.finalMouseY;
                    }
                    else {
                        this.isDragging = false;
                    }
                }
            }
            catch (NullPointerException ex) {}
        }
        GL11.glPopMatrix();
    }
}
