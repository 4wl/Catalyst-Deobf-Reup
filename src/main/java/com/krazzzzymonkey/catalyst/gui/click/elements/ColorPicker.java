/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.elements;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.gui.click.listener.ColorChangeListener;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;

public class ColorPicker extends Component
{
    public ArrayList<ColorChangeListener> listeners;
    public boolean isRainbow;
    public int color;
    public boolean enabled;
    
    @Override
    public void onUpdate() {
        this.fireListeners();
    }
    
    public Color getColor() {
        return new Color(this.color);
    }
    
    public boolean isRainbow() {
        return this.isRainbow;
    }
    
    public void setRainbow(final boolean isRainbow) {
        this.isRainbow = isRainbow;
    }
    
    @Override
    public void onMousePress(final int n, final int n2, final int n3) {
        this.enabled = !this.enabled;
        this.fireListeners();
    }
    
    public ArrayList getListeners() {
        return this.listeners;
    }
    
    @Override
    public void onMouseRelease(final int n, final int n2, final int n3) {
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public ColorPicker(final int n, final int n2, final int n3, final int n4, final int color, final boolean isRainbow, final Component component, final String s, final ColorValue colorValue) {
        super(n, n2, n3, n4, ComponentType.COLOR_PICKER, component, s);
        this.enabled = false;
        this.listeners = new ArrayList<ColorChangeListener>();
        this.color = color;
        this.isRainbow = isRainbow;
        this.enabled = this.enabled;
    }
    
    public void setColor(final Color color) {
        this.color = color.getRGB();
    }
    
    public void fireListeners() {
        final Iterator<ColorChangeListener> iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onColorChangeClick(this);
        }
    }
    
    public void addListener(final ColorChangeListener e) {
        this.listeners.add(e);
    }
    
    @Override
    public void onMouseDrag(final int n, final int n2) {
    }
}
