/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.lwjgl.opengl.GL11;
import java.text.DecimalFormat;
import net.minecraft.client.gui.ScaledResolution;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.types.Number;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Coordinates extends Modules
{
    public int finalMouseY;
    public boolean isDragging;
    public ModeValue alignment;
    public boolean isAlreadyDragging;
    public Number yOffset;
    public Number xOffset;
    public int finalMouseX;
    public BooleanValue nether;
    
    public Coordinates() {
        super("Coordinates", ModuleCategory.HUD, "Displays coordinates on hud", true);
        this.finalMouseX = 0;
        this.finalMouseY = 0;
        this.isDragging = false;
        this.isAlreadyDragging = false;
        this.nether = new BooleanValue("Nether", true);
        this.xOffset = new Number("X Offset", 0);
        this.yOffset = new Number("Y Offset", 275);
        this.alignment = new ModeValue("Align", new Mode[] { new Mode("One Line", true), new Mode("Multiple Lines", false) });
        this.addValue(this.nether, this.alignment, this.xOffset, this.yOffset);
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
        final DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String str = decimalFormat.format(Wrapper.INSTANCE.player().posX);
        String str2 = decimalFormat.format(Wrapper.INSTANCE.player().posY);
        String str3 = decimalFormat.format(Wrapper.INSTANCE.player().posZ);
        if (Minecraft.getMinecraft().world.getBiome(Minecraft.getMinecraft().player.getPosition()).getBiomeName().equals("Hell")) {
            str = decimalFormat.format(Wrapper.INSTANCE.player().posX * 8.0);
            str2 = decimalFormat.format(Wrapper.INSTANCE.player().posY);
            str3 = decimalFormat.format(Wrapper.INSTANCE.player().posZ * 8.0);
        }
        final String string = "§7X: §f" + str;
        final String string2 = "§7Y: §f" + str2;
        final String string3 = "§7Z: §f" + str3;
        String str4;
        String str5;
        String str6;
        if (!Minecraft.getMinecraft().world.getBiome(Minecraft.getMinecraft().player.getPosition()).getBiomeName().equals("Hell")) {
            str4 = decimalFormat.format(Wrapper.INSTANCE.player().posX / 8.0);
            str5 = decimalFormat.format(Wrapper.INSTANCE.player().posY);
            str6 = decimalFormat.format(Wrapper.INSTANCE.player().posZ / 8.0);
        }
        else {
            str4 = decimalFormat.format(Wrapper.INSTANCE.player().posX);
            str5 = decimalFormat.format(Wrapper.INSTANCE.player().posY);
            str6 = decimalFormat.format(Wrapper.INSTANCE.player().posZ);
        }
        final String string4 = "§cX: §f" + str4;
        final String string5 = "§cY: §f" + str5;
        final String string6 = "§cZ: §f" + str6;
        String s = string + " " + string2 + " " + string3 + "";
        final String string7 = "§c[ " + string4 + " " + string5 + " " + string6 + " §c] ";
        final int intValue = (int)this.yOffset.getValue();
        final int intValue2 = (int)this.xOffset.getValue();
        GL11.glPushMatrix();
        if (ModuleManager.getModule("CustomFont").isToggled()) {
            if (this.alignment.getMode("Multiple Lines").isToggled()) {
                Main.fontRenderer.drawStringWithShadow(s, intValue2, intValue, ClickGui.getColor());
                if (this.nether.getValue()) {
                    Main.fontRenderer.drawStringWithShadow(string7, intValue2, intValue + 14, ClickGui.getColor());
                }
            }
            else {
                if (this.nether.getValue()) {
                    s = s + " " + string7;
                }
                Main.fontRenderer.drawStringWithShadow(s, intValue2, intValue, ClickGui.getColor());
            }
        }
        else if (this.alignment.getMode("Multiple Lines").isToggled()) {
            Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(s, (float)intValue2, (float)intValue, ClickGui.getColor());
            if (this.nether.getValue()) {
                Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(string7, (float)intValue2, (float)(intValue + 14), ClickGui.getColor());
            }
        }
        else {
            if (this.nether.getValue()) {
                s = s + " " + string7;
            }
            Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(s, (float)intValue2, (float)intValue, ClickGui.getColor());
        }
        if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
            if (ModuleManager.getModule("CustomFont").isToggled()) {
                RenderUtils.drawRect((float)intValue2, (float)intValue, (float)(intValue2 + Main.fontRenderer.getStringWidth(s)), (float)(intValue + 14), ColorUtils.color(0, 0, 0, 100));
            }
            else {
                RenderUtils.drawRect((float)intValue2, (float)intValue, (float)(intValue2 + Wrapper.INSTANCE.fontRenderer().getStringWidth(s)), (float)(intValue + 14), ColorUtils.color(0, 0, 0, 100));
            }
            if (MouseUtils.isLeftClicked() && !MouseUtils.isMouseOver(intValue2, intValue2 + Main.fontRenderer.getStringWidth(s), intValue, intValue + 14)) {
                this.isAlreadyDragging = true;
            }
            if (!MouseUtils.isLeftClicked() && this.isAlreadyDragging) {
                this.isAlreadyDragging = false;
            }
            if (!this.isAlreadyDragging || this.isDragging) {
                if (MouseUtils.isMouseOver(intValue2, intValue2 + Main.fontRenderer.getStringWidth(s), intValue, intValue + 14)) {
                    this.isDragging = true;
                }
                if (MouseUtils.isLeftClicked() && this.isDragging) {
                    this.finalMouseX = MouseUtils.getMouseX();
                    this.finalMouseY = MouseUtils.getMouseY();
                    this.xOffset.value = this.finalMouseX - Main.fontRenderer.getStringWidth(s) / 2;
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
