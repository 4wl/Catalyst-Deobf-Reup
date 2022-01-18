/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils.visual;

import com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui;
import java.awt.Color;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;

public class ColorUtils
{
    public static double HUDRainbowSpeed;
    public static double rainbowSpeed;
    public static double rainbowSpeedTicks;
    public static double HUDRainbowSpeedTicks;
    
    @SubscribeEvent
    public void onUpdateRainbow(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        ColorUtils.rainbowSpeed += ColorUtils.rainbowSpeedTicks;
    }
    
    @SubscribeEvent
    public void onUpdateHUDRainbow(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        ColorUtils.HUDRainbowSpeed += ColorUtils.HUDRainbowSpeedTicks;
    }
    
    public static Color getColorFromDurability(final ItemStack itemStack) {
        final Color green = Color.GREEN;
        final Color red = Color.RED;
        if (!itemStack.isItemDamaged()) {
            return Color.GREEN;
        }
        if (!itemStack.isItemEnchanted()) {
            return new Color(96.9f, 48.45f, 155.04f, 255.0f);
        }
        final float n = (float)(itemStack.getMaxDamage() - itemStack.getItemDamage());
    Label_0304:
        while (true) {
            switch (365059380 - 1590577878 + 1) {
                case -132524748: {
                    continue;
                }
                default: {
                    final float n2 = n / itemStack.getMaxDamage();
                    final int r = (int)(green.getRed() * n2 + red.getRed() * (1.0f - n2));
                    final int g = (int)(green.getGreen() * n2 + red.getGreen() * (1.0f - n2));
                    final int b = (int)(green.getBlue() * n2 + red.getBlue() * (1.0f - n2));
                    try {
                        return new Color(r, g, b);
                    }
                    catch (IllegalArgumentException ex) {
                        return Color.green;
                    }
                    break Label_0304;
                }
                case -1843150520: {
                    break Label_0304;
                }
            }
        }
        throw null;
    }
    
    static {
        ColorUtils.rainbowSpeed = 0.0;
        ColorUtils.rainbowSpeedTicks = 0.0;
        ColorUtils.HUDRainbowSpeed = 0.0;
        ColorUtils.HUDRainbowSpeedTicks = 0.0;
    }
    
    public static int getColor(final int n, final int n2, final int n3, final int n4) {
        return n << 24 | n2 << 16 | n3 << 8 | n4;
    }
    
    public static Color rainbow(final float n, final double n2) {
        ColorUtils.rainbowSpeedTicks = ClickGui.rainbowSpeed.getValue();
        final float n3 = 1.0f;
        final Color color = new Color((int)Long.parseLong(Integer.toHexString(Color.HSBtoRGB((float)(ColorUtils.rainbowSpeed * 1000000.0 + 9.99999999999E11) / 1.0E10f % 1.0f + n, 1.0f, 1.0f)), 16));
        return new Color(color.getRed() / 255.0f * n3, color.getGreen() / 255.0f * n3, color.getBlue() / 255.0f * n3, color.getAlpha() / 255.0f);
    }
    
    public static int getColor(final int n, final int n2, final int n3) {
        return 0xFF000000 | n << 16 | n2 << 8 | n3;
    }
    
    public static Color IntToColor(final int n) {
        return new Color(n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF);
    }
    
    public static int ColorSlider(final float n, final float saturation) {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { n % 11520.0f / 11520.0f })[0], saturation, 1.0f);
        return new Color(hsBtoRGB >> 16 & 0xFF, hsBtoRGB >> 8 & 0xFF, hsBtoRGB & 0xFF, 255).getRGB();
    }
    
    public static int ColorToInt(final Color color) {
        return ((color.getRed() << 8) + color.getGreen() << 8) + color.getBlue();
    }
    
    public static Color rainbow() {
        final long n = 999999999999L;
        final float n2 = 1.0f;
        final Color color = new Color((int)Long.parseLong(Integer.toHexString(Color.HSBtoRGB((System.nanoTime() + n) / 1.0E10f % 1.0f, 1.0f, 1.0f)), 16));
        return new Color(color.getRed() / 255.0f * n2, color.getGreen() / 255.0f * n2, color.getBlue() / 255.0f * n2, color.getAlpha() / 255.0f);
    }
    
    public static float[] ColorToGLFloatColor(final Color color) {
        return new float[] { color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f };
    }
    
    public static Color HUDRainbow(final float n, final double hudRainbowSpeedTicks) {
        ColorUtils.HUDRainbowSpeedTicks = hudRainbowSpeedTicks;
        final float n2 = 1.0f;
        final Color color = new Color((int)Long.parseLong(Integer.toHexString(Color.HSBtoRGB((float)(ColorUtils.HUDRainbowSpeed * 1000000.0 + 9.99999999999E11) / 1.0E10f % 1.0f + n, 1.0f, 1.0f)), 16));
        return new Color(color.getRed() / 255.0f * n2, color.getGreen() / 255.0f * n2, color.getBlue() / 255.0f * n2, color.getAlpha() / 255.0f);
    }
    
    public static int color(final int r, final int g, final int b, final int a) {
        return new Color(r, g, b, a).getRGB();
    }
    
    public static int color(final float r, final float g, final float b, final float a) {
        return new Color(r, g, b, a).getRGB();
    }
}
