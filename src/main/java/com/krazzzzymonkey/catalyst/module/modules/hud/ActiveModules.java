/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.value.Mode;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.modules.combat.AutoTrap;
import com.krazzzzymonkey.catalyst.module.modules.combat.KillAura;
import com.krazzzzymonkey.catalyst.module.modules.combat.AutoCrystalRewrite;
import com.krazzzzymonkey.catalyst.module.modules.player.Blink;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.ScaledResolution;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.Number;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ActiveModules extends Modules
{
    public Number yOffset;
    public Number xOffset;
    public int finalMouseY;
    public IntegerValue rainbowSpeed;
    public BooleanValue rainbow;
    public boolean isDragging;
    public boolean isAlreadyDragging;
    public ColorValue colorValue;
    public int finalMouseX;
    public BooleanValue toggleModules;
    public int color;
    public DoubleValue hueOffset;
    
    public ActiveModules() {
        super("Active Modules", ModuleCategory.HUD, "Displays active modules on hud", true);
        this.finalMouseX = 0;
        this.finalMouseY = 0;
        this.isDragging = false;
        this.isAlreadyDragging = false;
        this.colorValue = new ColorValue("Color", Color.CYAN);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.rainbowSpeed = new IntegerValue("RainbowSpeed", 100, 0, 100);
        this.hueOffset = new DoubleValue("HueOffset", 0.999, 0.1, 1.0);
        this.xOffset = new Number("X Offset", 0);
        this.yOffset = new Number("y Offset", 15);
        this.toggleModules = new BooleanValue("ToggleModule", true);
        this.addValue(this.hueOffset, this.rainbowSpeed, this.colorValue, this.rainbow, this.toggleModules, this.xOffset, this.yOffset);
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        float n = 0.0f;
        final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
        this.color = this.colorValue.getColor().getRGB();
        int intValue = (int)this.yOffset.getValue();
        int intValue2 = (int)this.xOffset.getValue();
        GL11.glPushMatrix();
    Label_0250:
        while (true) {
            for (final Modules modules : ModuleManager.getSortedHacks()) {
                String str = "";
                final String name = modules.getName();
                while (true) {
                    switch (1136349121 - 907687331 + 1) {
                        case -966028792: {
                            continue;
                        }
                        case -1973516900: {
                            if (name.equals("Blink")) {
                                str = " §7" + Blink.INSTANCE.packets.size();
                            }
                            else if (modules.getName().equals("AutoCrystalRewrite")) {
                                if (AutoCrystalRewrite.INSTANCE.ezTarget == null) {
                                    str = "";
                                }
                                else {
                                    str = " §7" + AutoCrystalRewrite.INSTANCE.ezTarget.getName();
                                }
                            }
                            else if (modules.getName().equals("KillAura")) {
                                if (KillAura.target == null || !ActiveModules.mc.world.getLoadedEntityList().contains(KillAura.target)) {
                                    str = "";
                                }
                                else {
                                    str = " §7" + KillAura.target.getName();
                                }
                            }
                            else if (modules.getName().equals("AutoTrap")) {
                                if (AutoTrap.target == null) {
                                    str = "";
                                }
                                else {
                                    str = " §7" + AutoTrap.target.getName();
                                }
                            }
                            else {
                                for (final Value value : modules.getValues()) {
                                    if (value instanceof ModeValue) {
                                        final ModeValue modeValue = (ModeValue)value;
                                        if (modeValue.getModeName().equals("Priority")) {
                                            continue;
                                        }
                                        for (final Mode mode : modeValue.getModes()) {
                                            if (mode.isToggled()) {
                                                str = str + " §7" + mode.getName();
                                            }
                                        }
                                    }
                                }
                            }
                            if (ModuleManager.getModule("CustomFont").isToggled()) {
                                if (intValue2 > scaledResolution.getScaledWidth() / 2) {
                                    intValue2 = (int)this.xOffset.getValue() - ~Main.fontRenderer.getStringWidth(str + modules.getName()) + 1;
                                }
                                Main.fontRenderer.drawStringWithShadow(modules.getName() + str, intValue2, intValue, ((boolean)this.rainbow.getValue()) ? ColorUtils.rainbow(n, this.rainbowSpeed.getValue()).getRGB() : this.color);
                            }
                            else {
                                if (intValue2 > scaledResolution.getScaledWidth() / 2) {
                                    intValue2 = (int)this.xOffset.getValue() - Wrapper.INSTANCE.fontRenderer().getStringWidth(str + modules.getName());
                                }
                                Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(modules.getName() + str, (float)intValue2, (float)intValue, ((boolean)this.rainbow.getValue()) ? ColorUtils.HUDRainbow(n, this.rainbowSpeed.getValue()).getRGB() : this.color);
                            }
                            n += (float)(this.hueOffset.getValue() / 10.0);
                            Label_2874: {
                                if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
                                    if (ModuleManager.getModule("CustomFont").isToggled()) {
                                        RenderUtils.drawRect((float)intValue2, (float)intValue, (float)(intValue2 + Main.fontRenderer.getStringWidth(modules.getName() + str)), (float)(intValue + 12), ColorUtils.color(0, 0, 0, 100));
                                    }
                                    else {
                                        RenderUtils.drawRect((float)intValue2, (float)intValue, (float)(intValue2 + Wrapper.INSTANCE.fontRenderer().getStringWidth(modules.getName() + str)), (float)(intValue + 12), ColorUtils.color(0, 0, 0, 100));
                                    }
                                    if (MouseUtils.isLeftClicked() && !MouseUtils.isMouseOver(intValue2, intValue2 + Main.fontRenderer.getStringWidth(modules.getName() + str), intValue, intValue + 12)) {
                                        this.isAlreadyDragging = true;
                                    }
                                    if (!MouseUtils.isLeftClicked() && this.isAlreadyDragging) {
                                        this.isAlreadyDragging = false;
                                    }
                                    if (!this.isAlreadyDragging || this.isDragging) {
                                        if (MouseUtils.isMouseOver(intValue2, intValue2 + Main.fontRenderer.getStringWidth(modules.getName() + str), intValue, intValue + 12)) {
                                            this.isDragging = true;
                                        }
                                        if (MouseUtils.isLeftClicked()) {
                                            if (this.isDragging) {
                                                this.finalMouseX = MouseUtils.getMouseX();
                                                this.finalMouseY = MouseUtils.getMouseY();
                                                this.xOffset.value = this.finalMouseX;
                                                this.yOffset.value = this.finalMouseY;
                                                MouseUtils.isDragging = true;
                                                break Label_2874;
                                            }
                                        }
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
                            continue Label_0250;
                        }
                        default: {
                            throw null;
                        }
                    }
                }
            }
            break;
        }
        GL11.glPopMatrix();
        if (this.toggleModules.getValue()) {
            final Modules toggledModules = ModuleManager.getToggledModules();
            if (toggledModules != null) {
                GL11.glPushMatrix();
                RenderUtils.drawToggleModule(toggledModules.isToggled() ? (toggledModules.getName() + " - Enabled") : ("§7" + toggledModules.getName() + " - Disabled"));
                GL11.glPopMatrix();
            }
        }
    }
}
