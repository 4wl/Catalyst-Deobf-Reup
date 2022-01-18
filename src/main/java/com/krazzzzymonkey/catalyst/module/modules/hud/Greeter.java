/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.lwjgl.opengl.GL11;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import java.util.Calendar;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import com.krazzzzymonkey.catalyst.value.types.Number;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Greeter extends Modules
{
    public BooleanValue clientName;
    public ColorValue colorValue;
    public int finalMouseX;
    public boolean isAlreadyDragging;
    public Number yOffset;
    public Number xOffset;
    public BooleanValue smiley;
    public boolean isDragging;
    public int finalMouseY;
    public BooleanValue rainbow;
    public String time;
    public int color;
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        final int value = Calendar.getInstance().get(11);
        Label_0248: {
            if (value >= 0 && value < 12) {
                this.time = "Morning ";
            }
            else {
                if (value >= 12) {
                    if (value < 16) {
                        this.time = "Afternoon ";
                        break Label_0248;
                    }
                }
                if (value >= 16 && value < 24) {
                    this.time = "Evening ";
                }
                else if (value >= 20 && value < 24) {
                    this.time = "Night ";
                }
            }
        }
        if (!(boolean)this.rainbow.getValue()) {
            this.color = this.colorValue.getColor().getRGB();
        }
        else {
            this.color = ColorUtils.rainbow().getRGB();
        }
        final int intValue = (int)this.xOffset.getValue();
        final int intValue2 = (int)this.yOffset.getValue();
        String s = "Good " + this.time + Minecraft.getMinecraft().player.getName() + ", Welcome to Catalyst!";
        if (!(boolean)this.clientName.getValue()) {
            s = "Good " + this.time + Minecraft.getMinecraft().player.getName() + "!";
        }
        if (this.smiley.getValue()) {
            s = s.concat(" :^)");
        }
        while (true) {
            switch (192131494 - 692405633 + 1) {
                case 385724257: {
                    continue;
                }
                default: {
                    GL11.glPushMatrix();
                    if (ModuleManager.getModule("CustomFont").isToggled()) {
                        Main.fontRenderer.drawStringWithShadow(s, intValue, intValue2, this.color);
                    }
                    else {
                        Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(s, (float)intValue, (float)intValue2, this.color);
                    }
                    if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
                        if (ModuleManager.getModule("CustomFont").isToggled()) {
                            RenderUtils.drawRect((float)intValue, (float)intValue2, (float)(intValue + Main.fontRenderer.getStringWidth(s)), (float)(intValue2 + 14), ColorUtils.color(0, 0, 0, 100));
                        }
                        else {
                            RenderUtils.drawRect((float)intValue, (float)intValue2, (float)(intValue + Wrapper.INSTANCE.fontRenderer().getStringWidth(s)), (float)(intValue2 + 14), ColorUtils.color(0, 0, 0, 100));
                        }
                        if (MouseUtils.isLeftClicked()) {
                            final int n = intValue;
                            final int n2 = intValue;
                            final int stringWidth = Main.fontRenderer.getStringWidth(s);
                            if (!MouseUtils.isMouseOver(n, (n2 | stringWidth) * 2 + (n2 ^ stringWidth) + 1, intValue2, intValue2 + 14)) {
                                this.isAlreadyDragging = true;
                            }
                        }
                        if (!MouseUtils.isLeftClicked() && this.isAlreadyDragging) {
                            this.isAlreadyDragging = false;
                        }
                        if (!this.isAlreadyDragging || this.isDragging) {
                            if (MouseUtils.isMouseOver(intValue, intValue + Main.fontRenderer.getStringWidth(s), intValue2, intValue2 + 14)) {
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
                case -2099638371: {
                    throw null;
                }
            }
        }
    }
    
    public Greeter() {
        super("Greeter", ModuleCategory.HUD, "Displays greeter on hud", true);
        this.finalMouseX = 0;
        this.finalMouseY = 0;
        this.isDragging = false;
        this.isAlreadyDragging = false;
        this.clientName = new BooleanValue("Client Name", true);
        this.smiley = new BooleanValue("Smiley Face", false);
        this.colorValue = new ColorValue("Color", Color.CYAN);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.xOffset = new Number("X Offset", 300);
        this.yOffset = new Number("Y Offset", 0);
        this.addValue(this.clientName, this.smiley, this.colorValue, this.rainbow, this.xOffset, this.yOffset);
    }
}
