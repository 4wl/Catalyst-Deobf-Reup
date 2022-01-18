/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.value;

public class Mode
{
    public String name;
    public boolean toggled;
    
    public Mode(final String name, final boolean toggled) {
        this.name = name;
        this.toggled = toggled;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setToggled(final boolean toggled) {
        this.toggled = toggled;
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
}
