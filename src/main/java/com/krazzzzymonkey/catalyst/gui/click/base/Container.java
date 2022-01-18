/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.base;

import java.util.Iterator;
import java.util.ArrayList;

public class Container extends Component
{
    public ArrayList<Component> components;
    
    public ArrayList getComponents() {
        return this.components;
    }
    
    public Container(final int n, final int n2, final int n3, final int n4, final ComponentType componentType, final Component component, final String s) {
        super(n, n2, n3, n4, componentType, component, s);
        this.components = new ArrayList<Component>();
    }
    
    public void removeCompoent(final Component o) {
        this.components.remove(o);
    }
    
    public void renderChildren(final int n, final int n2) {
        final Iterator<Component> iterator = this.getComponents().iterator();
        while (iterator.hasNext()) {
            iterator.next().render(n, n2);
        }
    }
    
    public void addComponent(final Component e) {
        this.components.add(e);
    }
}
