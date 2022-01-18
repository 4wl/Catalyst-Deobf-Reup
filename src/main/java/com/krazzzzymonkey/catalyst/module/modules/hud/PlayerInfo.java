/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.lwjgl.opengl.GL11;
import com.krazzzzymonkey.catalyst.utils.LagCompensator;
import net.minecraft.util.math.MathHelper;
import java.text.DecimalFormat;
import net.minecraft.client.gui.ScaledResolution;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import com.krazzzzymonkey.catalyst.utils.CrystalClickCounter;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.types.Number;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class PlayerInfo extends Modules
{
    public ColorValue colorValue;
    public Number yOffset;
    public Number xOffset;
    public BooleanValue rainbow;
    public BooleanValue booleanTPS;
    public boolean isAlreadyDragging;
    public BooleanValue booleanPing;
    public CrystalClickCounter fps;
    public BooleanValue booleanFPS;
    public int finalMouseX;
    public BooleanValue booleanBPS;
    public boolean isDragging;
    public int finalMouseY;
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.getUniqueID() != null) {
            Color color = this.colorValue.getColor();
            if (this.rainbow.getValue()) {
                color = ColorUtils.rainbow();
            }
            final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
            final int intValue = (int)this.yOffset.getValue();
            final int intValue2 = (int)this.xOffset.getValue();
            final DecimalFormat decimalFormat = new DecimalFormat("0.0");
            final double n = Minecraft.getMinecraft().player.posX - Minecraft.getMinecraft().player.prevPosX;
            final double n2 = Minecraft.getMinecraft().player.posZ - Minecraft.getMinecraft().player.prevPosZ;
            final String format = decimalFormat.format(MathHelper.sqrt(n * n + n2 * n2) / (double)(Minecraft.getMinecraft().timer.tickLength / 1000.0f));
            final String format2 = decimalFormat.format(LagCompensator.INSTANCE.getTickRate());
            String string = "";
            String string2 = "";
            String string3 = "";
            String string4 = "";
            if (this.booleanFPS.getValue()) {
                this.fps.onBreak();
                string = "§rFPS: §f" + this.fps.getCps() + " ";
            }
            if (this.booleanTPS.getValue()) {
                string3 = "§rTPS: §f" + format2 + " ";
            }
            if (this.booleanBPS.getValue()) {
                string4 = "§rBPS: §f" + format + " ";
            }
            try {
                if (this.booleanPing.getValue()) {
                    string2 = "§rPING: §f" + String.valueOf(Minecraft.getMinecraft().player.connection.getPlayerInfo(Minecraft.getMinecraft().player.getUniqueID()).getResponseTime()) + " ";
                }
            }
            catch (NullPointerException ex) {}
            GL11.glPushMatrix();
            if (ModuleManager.getModule("CustomFont").isToggled()) {
                Main.fontRenderer.drawStringWithShadow(string + string2 + string3 + string4, intValue2, intValue, color.getRGB());
            }
            else {
                Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(string + string2 + string3 + string4, (float)intValue2, (float)intValue, color.getRGB());
            }
            if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
                if (ModuleManager.getModule("CustomFont").isToggled()) {
                    RenderUtils.drawRect((float)intValue2, (float)intValue, (float)(intValue2 + ~Main.fontRenderer.getStringWidth(string + string4 + string2 + string3) - 1), (float)(intValue + 14), ColorUtils.color(0, 0, 0, 100));
                }
                else {
                    RenderUtils.drawRect((float)intValue2, (float)intValue, (float)(intValue2 + Wrapper.INSTANCE.fontRenderer().getStringWidth(string + string4 + string2 + string3)), (float)(intValue + 14), ColorUtils.color(0, 0, 0, 100));
                }
                Label_2034: {
                    if (MouseUtils.isLeftClicked()) {
                        final int n3 = intValue2;
                        final int n4 = (intValue2 ^ Main.fontRenderer.getStringWidth(string + string4 + string2 + string3)) - 1;
                        final int n5 = intValue;
                        while (true) {
                            switch (-1823177232 + 656725187 + 1) {
                                case -1029217426: {
                                    continue;
                                }
                                default: {
                                    if (!MouseUtils.isMouseOver(n3, n4, n5, intValue + 14)) {
                                        this.isAlreadyDragging = true;
                                        break Label_2034;
                                    }
                                    break Label_2034;
                                }
                                case 563158460: {
                                    throw null;
                                }
                            }
                        }
                    }
                }
                if (!MouseUtils.isLeftClicked()) {
                    if (this.isAlreadyDragging) {
                        this.isAlreadyDragging = false;
                    }
                }
                if (!this.isAlreadyDragging || this.isDragging) {
                    if (MouseUtils.isMouseOver(intValue2, intValue2 + Main.fontRenderer.getStringWidth(string + string4 + string2 + string3), intValue, intValue + 14)) {
                        this.isDragging = true;
                    }
                    if (MouseUtils.isLeftClicked() && this.isDragging) {
                        this.finalMouseX = MouseUtils.getMouseX();
                        this.finalMouseY = MouseUtils.getMouseY();
                        this.xOffset.value = this.finalMouseX - Main.fontRenderer.getStringWidth(string + string4 + string2 + string3) / 2;
                        this.yOffset.value = this.finalMouseY;
                        MouseUtils.isDragging = true;
                    }
                    else {
                        this.isDragging = false;
                    }
                }
            }
            GL11.glPopMatrix();
        }
    }
    
    public PlayerInfo() {
        super("PlayerInfo", ModuleCategory.HUD, "Displays various player info on hud", true);
        this.finalMouseX = 0;
        this.finalMouseY = 0;
        this.isDragging = false;
        this.isAlreadyDragging = false;
        this.fps = new CrystalClickCounter();
        this.booleanBPS = new BooleanValue("BPS", true);
        this.booleanFPS = new BooleanValue("FPS", true);
        this.booleanPing = new BooleanValue("Ping", true);
        this.booleanTPS = new BooleanValue("TPS", true);
        this.xOffset = new Number("X Offset", 100);
        this.yOffset = new Number("Y Offset", 0);
        this.colorValue = new ColorValue("Color", Color.CYAN);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.addValue(this.booleanBPS, this.booleanFPS, this.booleanTPS, this.booleanPing, this.colorValue, this.rainbow, this.xOffset, this.yOffset);
    }
}
