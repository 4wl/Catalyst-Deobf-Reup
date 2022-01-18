/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.value;

public class Value
{
    public defaultValue;
    public value;
    public String name;
    
    public void setValue(final Object value) {
        this.value = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public Object getDefaultValue() {
        return this.defaultValue;
    }
    
    public Value(final String name, final Object o) {
        this.name = name;
        this.defaultValue = o;
        this.value = o;
    }
}
