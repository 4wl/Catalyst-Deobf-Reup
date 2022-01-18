/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.configuration.elements;

import com.krazzzzymonkey.catalyst.configuration.GuiConfig;

public abstract class Element
{
    public GuiConfig parent;
    
    public Element(final GuiConfig parent) {
        this.parent = parent;
    }
}
