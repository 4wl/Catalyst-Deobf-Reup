/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.gui;

import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.gui.click.ClickGuiScreen;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.managers.FileManager;
import net.minecraft.client.gui.GuiScreen;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ClickGui extends Modules
{
    public static ModeValue rainbowMode;
    public static ModeValue guiScale;
    public static ColorValue clickGuiToggledColor;
    public static DoubleValue rainbowHue;
    public static BooleanValue blur;
    public static IntegerValue rainbowSpeed;
    public static BooleanValue shadow;
    public static BooleanValue rainbow;
    public static BooleanValue tooltip;
    public static int color;
    public static int Scale;
    public static ColorValue clickGuiColor;
    public static ColorValue clickGuiBackGroundColor;
    public static int scale;
    
    static {
        ClickGui.scale = 2;
        ClickGui.Scale = 2;
    }
    
    @Override
    public void onEnable() {
        if (ClickGui.guiScale.getMode("Large").isToggled()) {
            ClickGui.Scale = 3;
        }
        else if (ClickGui.guiScale.getMode("Small").isToggled()) {
            ClickGui.Scale = 1;
        }
        else {
            ClickGui.Scale = 2;
        }
        ClickGui.scale = Minecraft.getMinecraft().gameSettings.guiScale;
        Wrapper.INSTANCE.mc().displayGuiScreen((GuiScreen)Main.moduleManager.getGui());
        FileManager.saveHacks();
        super.onEnable();
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        setColor();
        if (Minecraft.getMinecraft().currentScreen instanceof ClickGuiScreen) {
            return;
        }
        this.toggle();
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (Minecraft.getMinecraft().currentScreen instanceof GuiContainer) {
            return;
        }
        if (Minecraft.getMinecraft().currentScreen instanceof GuiChat) {
            return;
        }
        if (Minecraft.getMinecraft().currentScreen instanceof GuiIngameMenu) {
            return;
        }
        com.krazzzzymonkey.catalyst.gui.click.ClickGui.onUpdate();
        if (ClickGuiScreen.tooltip != null) {
            ClickGuiScreen.tooltip.render();
        }
        if (Minecraft.getMinecraft().entityRenderer.getShaderGroup() == null && (boolean)ClickGui.blur.getValue()) {
            try {
                Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
            }
            catch (Exception ex) {}
        }
        if (ClickGui.shadow.getValue()) {
            final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
            RenderUtils.drawRect(0.0f, 0.0f, (float)scaledResolution.getScaledWidth(), (float)scaledResolution.getScaledHeight(), ColorUtils.color(0.0f, 0.0f, 0.0f, 0.5f));
        }
    }
    
    public ClickGui() {
        final String s = "ClickGui";
        final ModuleCategory gui = ModuleCategory.GUI;
        final String s2 = "Settings for ClickGui";
        while (true) {
            switch (418452903 + 586991326 + 1) {
                case 990934936: {
                    continue;
                }
                case 973984633: {
                    super(s, gui, s2);
                    this.setKey(157);
                    ClickGui.guiScale = new ModeValue("ClickGuiScale", new Mode[] { new Mode("Small", false), new Mode("Normal", true), new Mode("Large", false) });
                    ClickGui.tooltip = new BooleanValue("Tooltip", true);
                    ClickGui.shadow = new BooleanValue("Shadow", true);
                    ClickGui.blur = new BooleanValue("Blur", true);
                    ClickGui.clickGuiColor = new ColorValue("HeaderColor", Color.CYAN);
                    ClickGui.clickGuiBackGroundColor = new ColorValue("BackgroundColor", Color.BLACK);
                    ClickGui.clickGuiToggledColor = new ColorValue("ToggledColor", Color.CYAN);
                    ClickGui.rainbow = new BooleanValue("Rainbow", true);
                    ClickGui.rainbowMode = new ModeValue("RainbowMode", new Mode[] { new Mode("RainbowFlow", true), new Mode("Static", false) });
                    ClickGui.rainbowSpeed = new IntegerValue("RainbowSpeed", 50, 0, 100);
                    ClickGui.rainbowHue = new DoubleValue("RainbowHue", 0.1, 0.1, 1.0);
                    this.addValue(ClickGui.guiScale, ClickGui.tooltip, ClickGui.shadow, ClickGui.blur, ClickGui.clickGuiColor, ClickGui.clickGuiBackGroundColor, ClickGui.clickGuiToggledColor, ClickGui.rainbow, ClickGui.rainbowMode, ClickGui.rainbowSpeed, ClickGui.rainbowHue);
                    setColor();
                }
                default: {
                    throw null;
                }
            }
        }
    }
    
    public static void setColor() {
        ClickGui.color = ClickGui.clickGuiColor.getColor().getRGB();
    }
    
    public static int getColor() {
        return ClickGui.rainbow.getValue() ? ColorUtils.rainbow().getRGB() : ClickGui.color;
    }
    
    @Override
    public void onDisable() {
        FileManager.saveHacks();
        Minecraft.getMinecraft().gameSettings.guiScale = ClickGui.scale;
        if (Minecraft.getMinecraft().entityRenderer.getShaderGroup() != null) {
            Minecraft.getMinecraft().entityRenderer.getShaderGroup().deleteShaderGroup();
        }
        ClickGui.mc.currentScreen = null;
        super.onDisable();
    }
}
