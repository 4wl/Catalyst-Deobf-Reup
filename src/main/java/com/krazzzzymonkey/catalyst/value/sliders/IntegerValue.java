/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.value.sliders;

import com.krazzzzymonkey.catalyst.value.Value;

public class IntegerValue extends Value
{
    public int min;
    public int max;
    
    public IntegerValue(final String s, final int i, final int min, final int max) {
        super(s, i);
        this.min = min;
        this.max = max;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public int getMin() {
        return this.min;
    }
    
    @Override
    public Object getValue() {
        return this.getValue();
    }
    
    @Override
    public Integer getValue() {
        return ((Number)super.getValue()).intValue();
    }
}
