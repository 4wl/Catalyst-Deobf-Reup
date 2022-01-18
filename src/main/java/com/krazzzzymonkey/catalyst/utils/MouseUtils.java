/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import net.minecraft.client.Minecraft;

public class MouseUtils
{
    public static boolean isDragging;
    
    static {
        MouseUtils.isDragging = false;
    }
    
    public static int getMouseX() {
        if (Minecraft.getMinecraft().gameSettings.guiScale == 0) {
            Minecraft.getMinecraft().gameSettings.guiScale = 3;
        }
        return Mouse.getEventX() / Minecraft.getMinecraft().gameSettings.guiScale;
    }
    
    public static boolean isLeftClicked() {
        return Mouse.isButtonDown(0);
    }
    
    public static boolean isRightClicked() {
        return Mouse.isButtonDown(1);
    }
    
    public static int getMouseY() {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        if (Minecraft.getMinecraft().gameSettings.guiScale == 0) {
            Minecraft.getMinecraft().gameSettings.guiScale = 3;
        }
        return scaledResolution.getScaledHeight() - Mouse.getEventY() / Minecraft.getMinecraft().gameSettings.guiScale;
    }
    
    public static boolean isMouseOver(final int n, final int n2, final int n3, final int n4) {
        return getMouseX() >= n && getMouseX() <= n2 && getMouseY() >= n3 && getMouseY() <= n4;
    }
    
    public static boolean isMiddleClicked() {
        return Mouse.isButtonDown(2);
    }
}
