/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.value.types;

import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.value.Value;

public class ColorValue extends Value
{
    public Double max;
    public Double min;
    public int colorInt;
    public boolean rainbow;
    public Color color;
    
    public ColorValue(final String s, final int rgb) {
        super(s, rgb);
        this.colorInt = ColorUtils.ColorToInt(Color.CYAN);
        this.colorInt = rgb;
        this.color = new Color(rgb);
    }
    
    public void setColorInt(final int colorInt) {
        this.colorInt = colorInt;
        this.color = ColorUtils.IntToColor(colorInt);
    }
    
    public boolean getRainbow() {
        return this.rainbow;
    }
    
    public ColorValue(final String s, final Color color) {
        super(s, ColorUtils.ColorToInt(color));
        this.colorInt = ColorUtils.ColorToInt(Color.CYAN);
        this.colorInt = color.getRGB();
        this.color = color;
    }
    
    public Double getMax() {
        return this.max;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public int getColorInt() {
        return this.colorInt;
    }
    
    public Double getMin() {
        return this.min;
    }
    
    public void setColor(final Color color) {
        this.color = color;
        this.colorInt = ColorUtils.ColorToInt(color);
    }
    
    public void setRainbow(final boolean rainbow) {
        this.rainbow = rainbow;
    }
}
