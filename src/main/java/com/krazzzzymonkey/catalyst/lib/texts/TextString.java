/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.texts;

public class TextString implements IText
{
    String string;
    
    public TextString(final String string) {
        this.string = string;
    }
    
    @Override
    public String get() {
        return this.string;
    }
}
