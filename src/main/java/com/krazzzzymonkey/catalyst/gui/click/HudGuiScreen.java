/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click;

import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import com.krazzzzymonkey.catalyst.managers.FileManager;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import net.minecraft.client.Minecraft;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.command.Command;
import com.krazzzzymonkey.catalyst.managers.CommandManager;
import org.lwjgl.input.Keyboard;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.gui.GuiTextField;
import net.minecraft.client.gui.GuiScreen;

public class HudGuiScreen extends GuiScreen
{
    public static GuiTextField console;
    public static HudEditor hudGui;
    public static int[] mouse;
    public ArrayList cmds;
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        super.initGui();
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        HudGuiScreen.hudGui.render();
        super.drawScreen(n, n2, n3);
    }
    
    public HudGuiScreen() {
        (this.cmds = new ArrayList()).clear();
        for (final Command command : CommandManager.commands) {
            this.cmds.add(command.getCommand() + " - " + command.getDescription());
        }
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (Minecraft.getMinecraft().entityRenderer.getShaderGroup() != null) {
            Minecraft.getMinecraft().entityRenderer.getShaderGroup().deleteShaderGroup();
        }
        ModuleManager.getModule("HudEditor").toggle();
        FileManager.saveHacks();
        super.onGuiClosed();
    }
    
    public void updateScreen() {
        HudEditor.onUpdate();
        super.updateScreen();
    }
    
    static {
        HudGuiScreen.mouse = new int[2];
    }
    
    public void handleInput() {
        final int guiScale = this.mc.gameSettings.guiScale;
        if (Mouse.isCreated()) {
            while (Mouse.next()) {
                final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
                final int n = Mouse.getEventX() * scaledResolution.getScaledWidth() / this.mc.displayWidth;
                final int n2 = scaledResolution.getScaledHeight() - Mouse.getEventY() * scaledResolution.getScaledHeight() / this.mc.displayHeight - 1;
                if (Mouse.getEventButton() == -1) {
                    if (Mouse.getEventDWheel() != 0) {
                        HudGuiScreen.hudGui.onMouseScroll(Mouse.getEventDWheel() / 100 * 3);
                    }
                    HudGuiScreen.hudGui.onMouseUpdate(n, n2);
                    HudGuiScreen.mouse[0] = n;
                    HudGuiScreen.mouse[1] = n2;
                }
                else if (Mouse.getEventButtonState()) {
                    HudGuiScreen.hudGui.onMouseClick(n, n2, Mouse.getEventButton());
                }
                else {
                    HudGuiScreen.hudGui.onMouseRelease(n, n2);
                }
            }
        }
        this.mc.gameSettings.guiScale = guiScale;
        super.handleInput();
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
    }
}
