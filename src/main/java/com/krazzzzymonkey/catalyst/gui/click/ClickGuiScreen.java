/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.command.Command;
import com.krazzzzymonkey.catalyst.managers.CommandManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import com.krazzzzymonkey.catalyst.managers.FileManager;
import org.lwjgl.input.Keyboard;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.gui.Tooltip;
import net.minecraft.client.gui.GuiScreen;

public class ClickGuiScreen extends GuiScreen
{
    public GuiScreen prevScreen;
    public static Tooltip tooltip;
    public static ClickGui clickGui;
    public ArrayList cmds;
    public static int[] mouse;
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.mc.gameSettings.guiScale = com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui.Scale;
        ClickGuiScreen.tooltip = null;
        ClickGuiScreen.clickGui.render();
        if (ClickGuiScreen.tooltip != null) {
            ClickGuiScreen.tooltip.render();
        }
        super.drawScreen(n, n2, n3);
    }
    
    public void handleInput() {
        if (Keyboard.isCreated()) {
            Keyboard.enableRepeatEvents(true);
            while (Keyboard.next()) {
                if (Keyboard.getEventKeyState()) {
                    if (Keyboard.getEventKey() == 1) {
                        this.mc.displayGuiScreen((GuiScreen)null);
                        FileManager.saveHacks();
                        FileManager.saveClickGui();
                    }
                    else {
                        ClickGuiScreen.clickGui.onkeyPressed(Keyboard.getEventKey(), Keyboard.getEventCharacter());
                    }
                }
                else {
                    ClickGuiScreen.clickGui.onKeyRelease(Keyboard.getEventKey(), Keyboard.getEventCharacter());
                }
            }
        }
        if (Mouse.isCreated()) {
            while (Mouse.next()) {
                final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
                final int n = Mouse.getEventX() / Minecraft.getMinecraft().gameSettings.guiScale;
                final int n2 = scaledResolution.getScaledHeight() - Mouse.getEventY() / Minecraft.getMinecraft().gameSettings.guiScale;
                if (Mouse.getEventButton() == -1) {
                    if (Mouse.getEventDWheel() != 0) {
                        ClickGuiScreen.clickGui.onMouseScroll(Mouse.getEventDWheel() / 100 * 3);
                    }
                    ClickGuiScreen.clickGui.onMouseUpdate(n, n2);
                    ClickGuiScreen.mouse[0] = n;
                    ClickGuiScreen.mouse[1] = n2;
                }
                else if (Mouse.getEventButtonState()) {
                    ClickGuiScreen.clickGui.onMouseClick(n, n2, Mouse.getEventButton());
                }
                else {
                    ClickGuiScreen.clickGui.onMouseRelease(n, n2);
                }
            }
        }
        super.handleInput();
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (Minecraft.getMinecraft().entityRenderer.getShaderGroup() != null) {
            Minecraft.getMinecraft().entityRenderer.getShaderGroup().deleteShaderGroup();
        }
        ModuleManager.getModule("ClickGui").toggle();
        super.onGuiClosed();
    }
    
    public void updateScreen() {
        ClickGui.onUpdate();
        if (Minecraft.getMinecraft().entityRenderer.getShaderGroup() != null && !(boolean)com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui.blur.getValue()) {
            Minecraft.getMinecraft().entityRenderer.getShaderGroup().deleteShaderGroup();
        }
        if (Minecraft.getMinecraft().entityRenderer.getShaderGroup() == null && (boolean)com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui.blur.getValue()) {
            try {
                Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
            }
            catch (Exception ex) {}
        }
        if (ClickGuiScreen.tooltip != null) {
            ClickGuiScreen.tooltip.render();
        }
        super.updateScreen();
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
    }
    
    static {
        ClickGuiScreen.mouse = new int[2];
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        Label_0199: {
            if (OpenGlHelper.shadersSupported && (boolean)com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui.blur.getValue()) {
                if (Minecraft.getMinecraft().entityRenderer.getShaderGroup() != null) {
                    Minecraft.getMinecraft().entityRenderer.getShaderGroup().deleteShaderGroup();
                }
                Exception ex;
                try {
                    Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
                    break Label_0199;
                }
                catch (Exception ex2) {
                    ex = ex2;
                }
                ex.printStackTrace();
            }
        }
        super.initGui();
    }
    
    public ClickGuiScreen() {
        (this.cmds = new ArrayList()).clear();
        for (final Command command : CommandManager.commands) {
            this.cmds.add(command.getCommand() + " - " + command.getDescription());
        }
    }
    
    public ClickGuiScreen(final GuiScreen prevScreen) {
        this.cmds = new ArrayList();
        this.prevScreen = prevScreen;
    }
}
