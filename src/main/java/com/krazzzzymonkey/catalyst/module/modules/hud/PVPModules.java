/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import com.krazzzzymonkey.catalyst.value.types.Number;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class PVPModules extends Modules
{
    public BooleanValue Short;
    public String HoleFill;
    public String ObsidianReplace;
    public BooleanValue bAutoObsidian;
    public String AutoCrystal;
    public BooleanValue bKillAura;
    public BooleanValue bSelfTrap;
    public String SelfTrap;
    public BooleanValue bAutoWeb;
    public BooleanValue bObsidianReplace;
    public String KillAura;
    public Number xOffset;
    public String autoWeb;
    public String AutoWeb;
    public Number yOffset;
    public String obsidianReplace;
    public int finalMouseX;
    public String autoTrap;
    public String killAura;
    public String autoObsidian;
    public String selfTrap;
    public String AutoObsidian;
    public BooleanValue bHoleFill;
    public String on;
    public String holeFill;
    public BooleanValue bAutoTrap;
    public boolean isAlreadyDragging;
    public BooleanValue bAutoCrystal;
    public boolean isDragging;
    public String off;
    public String AutoTrap;
    public String autoCrystal;
    public int finalMouseY;
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        if (ModuleManager.getModule("AutoCrystal").isToggled()) {
            this.autoCrystal = this.AutoCrystal.concat(this.on);
        }
        else {
            this.autoCrystal = this.AutoCrystal.concat(this.off);
        }
        if (ModuleManager.getModule("AutoObsidian").isToggled()) {
            this.autoObsidian = this.AutoObsidian.concat(this.on);
        }
        else {
            this.autoObsidian = this.AutoObsidian.concat(this.off);
        }
        if (ModuleManager.getModule("HoleFill").isToggled()) {
            this.holeFill = this.HoleFill.concat(this.on);
        }
        else {
            this.holeFill = this.HoleFill.concat(this.off);
        }
        if (ModuleManager.getModule("AutoTrap").isToggled()) {
            this.autoTrap = this.AutoTrap.concat(this.on);
        }
        else {
            this.autoTrap = this.AutoTrap.concat(this.off);
        }
        if (ModuleManager.getModule("SelfTrap").isToggled()) {
            this.selfTrap = this.SelfTrap.concat(this.on);
        }
        else {
            this.selfTrap = this.SelfTrap.concat(this.off);
        }
        if (ModuleManager.getModule("ObsidianReplace").isToggled()) {
            this.obsidianReplace = this.ObsidianReplace.concat(this.on);
        }
        else {
            this.obsidianReplace = this.ObsidianReplace.concat(this.off);
        }
        if (ModuleManager.getModule("KillAura").isToggled()) {
            this.killAura = this.KillAura.concat(this.on);
        }
        else {
            this.killAura = this.KillAura.concat(this.off);
        }
        if (ModuleManager.getModule("AutoWeb").isToggled()) {
            this.autoWeb = this.AutoWeb.concat(this.on);
        }
        else {
            this.autoWeb = this.AutoWeb.concat(this.off);
        }
        int intValue = (int)this.yOffset.getValue();
        final int intValue2 = (int)this.xOffset.getValue();
        Label_1237: {
            if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
                RenderUtils.drawRect((float)intValue2, (float)intValue, (float)(intValue2 + 50), (float)(intValue + 14), ColorUtils.color(0, 0, 0, 100));
                if (MouseUtils.isLeftClicked()) {
                    if (!MouseUtils.isMouseOver(intValue2, intValue2 + 50, intValue, intValue + 14)) {
                        this.isAlreadyDragging = true;
                    }
                }
                if (!MouseUtils.isLeftClicked() && this.isAlreadyDragging) {
                    this.isAlreadyDragging = false;
                }
                if (this.isAlreadyDragging) {
                    if (!this.isDragging) {
                        break Label_1237;
                    }
                }
                if (MouseUtils.isMouseOver(intValue2, intValue2 + 50, intValue, intValue + 14)) {
                    this.isDragging = true;
                }
                if (MouseUtils.isLeftClicked() && this.isDragging) {
                    this.finalMouseX = MouseUtils.getMouseX();
                    this.finalMouseY = MouseUtils.getMouseY();
                    this.xOffset.value = this.finalMouseX - 25;
                    this.yOffset.value = this.finalMouseY;
                    MouseUtils.isDragging = true;
                }
                else {
                    this.isDragging = false;
                }
            }
        }
        if (ModuleManager.getModule("CustomFont").isToggled()) {
            if (this.bHoleFill.getValue()) {
                Main.fontRenderer.drawStringWithShadow(this.holeFill, (int)this.xOffset.getValue(), intValue, -1);
                intValue += 12;
            }
            if (this.bKillAura.getValue()) {
                Main.fontRenderer.drawStringWithShadow(this.killAura, (int)this.xOffset.getValue(), intValue, -1);
                intValue += 12;
            }
            if (this.bSelfTrap.getValue()) {
                Main.fontRenderer.drawStringWithShadow(this.selfTrap, (int)this.xOffset.getValue(), intValue, -1);
                intValue += 12;
            }
            if (this.bAutoWeb.getValue()) {
                Main.fontRenderer.drawStringWithShadow(this.autoWeb, (int)this.xOffset.getValue(), intValue, -1);
                intValue += 12;
            }
            if (this.bAutoTrap.getValue()) {
                Main.fontRenderer.drawStringWithShadow(this.autoTrap, (int)this.xOffset.getValue(), intValue, -1);
                intValue += 12;
            }
            if (this.bAutoCrystal.getValue()) {
                Main.fontRenderer.drawStringWithShadow(this.autoCrystal, (int)this.xOffset.getValue(), intValue, -1);
                intValue += 12;
            }
            if (this.bAutoObsidian.getValue()) {
                Main.fontRenderer.drawStringWithShadow(this.autoObsidian, (int)this.xOffset.getValue(), intValue, -1);
                intValue += 12;
            }
            if (this.bObsidianReplace.getValue()) {
                Main.fontRenderer.drawStringWithShadow(this.obsidianReplace, (int)this.xOffset.getValue(), intValue, -1);
            }
        }
        else {
            if (this.bHoleFill.getValue()) {
                Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(this.holeFill, (float)(int)this.xOffset.getValue(), (float)intValue, -1);
                intValue += 12;
            }
            if (this.bKillAura.getValue()) {
                Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(this.killAura, (float)(int)this.xOffset.getValue(), (float)intValue, -1);
                intValue += 12;
            }
            if (this.bSelfTrap.getValue()) {
                Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(this.selfTrap, (float)(int)this.xOffset.getValue(), (float)intValue, -1);
                intValue += 12;
            }
            if (this.bAutoWeb.getValue()) {
                Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(this.autoWeb, (float)(int)this.xOffset.getValue(), (float)intValue, -1);
                intValue += 12;
            }
            if (this.bAutoTrap.getValue()) {
                Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(this.autoTrap, (float)(int)this.xOffset.getValue(), (float)intValue, -1);
                intValue += 12;
            }
            if (this.bAutoCrystal.getValue()) {
                Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(this.autoCrystal, (float)(int)this.xOffset.getValue(), (float)intValue, -1);
                intValue += 12;
            }
            if (this.bAutoObsidian.getValue()) {
                Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(this.autoObsidian, (float)(int)this.xOffset.getValue(), (float)intValue, -1);
                intValue += 12;
            }
            if (this.bObsidianReplace.getValue()) {
                Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(this.obsidianReplace, (float)(int)this.xOffset.getValue(), (float)intValue, -1);
            }
        }
    }
    
    public PVPModules() {
        super("PVPModules", ModuleCategory.HUD, "Displays if a pvp module is enabled or not", true);
        this.AutoCrystal = "AutoCrystal: ";
        this.AutoObsidian = "AutoObsidian: ";
        this.HoleFill = "HoleFill: ";
        this.AutoTrap = "AutoTrap: ";
        this.ObsidianReplace = "ObsidianReplace: ";
        this.KillAura = "KillAura: ";
        this.SelfTrap = "SelfTrap: ";
        this.AutoWeb = "AutoWeb: ";
        this.autoCrystal = "AutoCrystal: ";
        this.autoObsidian = "AutoObsidian: ";
        this.holeFill = "HoleFill: ";
        this.autoTrap = "AutoTrap: ";
        this.obsidianReplace = "ObsidianReplace: ";
        this.killAura = "KillAura: ";
        this.selfTrap = "SelfTrap: ";
        this.autoWeb = "AutoWeb: ";
        this.off = "§cOFF";
        this.on = "§aON";
        this.finalMouseX = 0;
        this.finalMouseY = 0;
        this.isDragging = false;
        this.isAlreadyDragging = false;
        this.Short = new BooleanValue("Abbreviated", true);
        this.xOffset = new Number("X Offset", 0);
        this.yOffset = new Number("Y Offset", 190);
        this.bAutoCrystal = new BooleanValue("AutoCrystal", true);
        this.bAutoObsidian = new BooleanValue("AutoObsidian", true);
        this.bHoleFill = new BooleanValue("HoleFill", true);
        this.bAutoTrap = new BooleanValue("AutoTrap", true);
        this.bSelfTrap = new BooleanValue("SelfTrap", true);
        this.bAutoWeb = new BooleanValue("AutoWeb", true);
        this.bObsidianReplace = new BooleanValue("ObsidianReplace", true);
        this.bKillAura = new BooleanValue("KillAura", true);
        this.addValue(this.Short, this.bAutoCrystal, this.bAutoObsidian, this.bHoleFill, this.bAutoTrap, this.bSelfTrap, this.bAutoWeb, this.bObsidianReplace, this.bKillAura, this.xOffset, this.yOffset);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (this.Short.getValue()) {
            this.AutoCrystal = "CA: ";
            this.AutoObsidian = "SU: ";
            this.HoleFill = "HF: ";
            this.AutoTrap = "AT: ";
            this.ObsidianReplace = "OR: ";
            this.KillAura = "KA: ";
            this.SelfTrap = "ST: ";
            this.AutoWeb = "AW: ";
        }
        else {
            this.AutoCrystal = "AutoCrystal: ";
            this.AutoObsidian = "AutoObsidian: ";
            this.HoleFill = "HoleFill: ";
            this.AutoTrap = "AutoTrap: ";
            this.ObsidianReplace = "ObsidianReplace: ";
            this.KillAura = "KillAura: ";
            this.SelfTrap = "SelfTrap: ";
            this.AutoWeb = "AutoWeb: ";
        }
    }
}
