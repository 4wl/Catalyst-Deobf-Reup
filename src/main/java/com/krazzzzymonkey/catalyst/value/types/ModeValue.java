/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.value.types;

import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.value.Value;

public class ModeValue extends Value
{
    public Mode[] modes;
    public String modeName;
    
    public Mode[] getModes() {
        return this.modes;
    }
    
    public Mode getMode(final String anObject) {
        Mode mode = null;
        for (final Mode mode2 : this.modes) {
            if (mode2.getName().equals(anObject)) {
                mode = mode2;
            }
        }
        return mode;
    }
    
    public String getModeName() {
        return this.modeName;
    }
    
    public ModeValue(final String modeName, final Mode... modes) {
        super(modeName, null);
        this.modeName = modeName;
        this.modes = modes;
    }
}
