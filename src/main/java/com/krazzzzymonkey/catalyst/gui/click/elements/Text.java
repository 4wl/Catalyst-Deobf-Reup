/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.elements;

import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;

public class Text extends Component
{
    public String[] text;
    
    public String[] getMessage() {
        return this.text;
    }
    
    public Text(final int n, final int n2, final int n3, final int n4, final Component component, final String[] text) {
        super(n, n2, n3, n4, ComponentType.TEXT, component, "");
        this.text = text;
    }
}
