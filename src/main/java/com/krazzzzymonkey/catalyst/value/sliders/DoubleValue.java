/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.value.sliders;

import com.krazzzzymonkey.catalyst.value.Value;

public class DoubleValue extends Value
{
    public double min;
    public double max;
    
    @Override
    public Double getValue() {
        return ((Number)super.getValue()).doubleValue();
    }
    
    @Override
    public Object getValue() {
        return this.getValue();
    }
    
    public double getMax() {
        return this.max;
    }
    
    public DoubleValue(final String s, final double d, final double min, final double max) {
        super(s, d);
        this.min = min;
        this.max = max;
    }
    
    public double getMin() {
        return this.min;
    }
}
