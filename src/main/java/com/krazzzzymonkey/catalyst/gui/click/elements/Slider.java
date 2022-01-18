/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.elements;

import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import org.lwjgl.input.Mouse;
import com.krazzzzymonkey.catalyst.gui.click.ClickGui;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.listener.SliderChangeListener;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;

public class Slider extends Component
{
    public boolean dragging;
    public double min;
    public double max;
    public double value;
    public double percent;
    public Number type;
    private ArrayList<SliderChangeListener> listeners;
    
    public Slider(final Number min, final Number max, final Number value, final Component component, final String text) {
        super(0, 0, 99, 14, ComponentType.SLIDER, component, text);
        this.dragging = false;
        this.percent = 0.0;
        this.listeners = new ArrayList<SliderChangeListener>();
        this.type = value;
        this.min = min.doubleValue();
        this.max = max.doubleValue();
        this.value = value.doubleValue();
        this.percent = this.value / (this.max - this.min);
    }
    
    public void addListener(final SliderChangeListener listener) {
        this.listeners.add(listener);
    }
    
    @Override
    public void onMousePress(int x, final int y, final int buttonID) {
        x -= this.getX();
        final int x2 = (int)this.getDimension().getWidth();
        this.percent = x / (double)x2;
        this.value = this.round((this.max - this.min) * this.percent + this.min, 2);
        this.dragging = true;
        this.fireListeners();
    }
    
    private void fireListeners() {
        for (final SliderChangeListener listener : this.listeners) {
            listener.onSliderChange(this);
        }
    }
    
    @Override
    public void onMouseRelease(final int x, final int y, final int buttonID) {
        this.dragging = false;
    }
    
    @Override
    public void onUpdate() {
        if (Frame.isDragging) {
            return;
        }
        final int[] mouse = ClickGui.mouse;
        this.dragging = false;
        if (this.dragging && !this.isMouseOver(mouse[0], mouse[1])) {
            if (mouse[0] <= this.getX()) {
                this.percent = 0.0;
                this.value = this.min;
                this.fireListeners();
            }
            else {
                this.percent = 1.0;
                this.value = this.max;
                this.fireListeners();
            }
        }
        if (Mouse.isButtonDown(0) && this.isMouseOver(mouse[0], mouse[1])) {
            this.dragging = true;
        }
    }
    
    @Override
    public void onMouseDrag(int x, final int y) {
        if (Frame.isDragging) {
            return;
        }
        if (this.dragging) {
            x -= this.getX();
            final int x2 = (int)this.getDimension().getWidth();
            this.percent = x / (double)x2;
            this.value = this.round((this.max - this.min) * this.percent + this.min, 2);
            this.fireListeners();
        }
    }
    
    public ArrayList<SliderChangeListener> getListeners() {
        return this.listeners;
    }
    
    public boolean isDragging() {
        return this.dragging;
    }
    
    public void setDragging(final boolean dragging) {
        this.dragging = dragging;
    }
    
    public double getMin() {
        return this.min;
    }
    
    public void setMin(final double min) {
        this.min = min;
    }
    
    public double getMax() {
        return this.max;
    }
    
    public void setMax(final double max) {
        this.max = max;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public String getRenderValue() {
        if (this.type instanceof Integer) {
            return RenderUtils.DF(this.value, 0);
        }
        return RenderUtils.DF(this.value, 1);
    }
    
    public void setValue(final double value) {
        this.value = value;
    }
    
    public double getPercent() {
        return this.percent;
    }
    
    public void setPercent(final double percent) {
        this.percent = percent;
    }
    
    private double round(final double valueToRound, final int numberOfDecimalPlaces) {
        final double multipicationFactor = Math.pow(10.0, numberOfDecimalPlaces);
        final double interestedInZeroDPs = valueToRound * multipicationFactor;
        return Math.round(interestedInZeroDPs) / multipicationFactor;
    }
}
