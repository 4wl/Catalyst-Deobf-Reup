/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.elements;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.gui.click.listener.ComponentClickListener;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;

public class Button extends Component
{
    public ArrayList<ComponentClickListener> listeners;
    public Modules mod;
    public boolean enabled;
    
    public Button(final int n, final int n2, final int n3, final int n4, final Component component, final String s) {
        super(n, n2, n3, n4, ComponentType.BUTTON, component, s);
        this.listeners = new ArrayList<ComponentClickListener>();
        this.enabled = false;
    }
    
    public Modules getMod() {
        return this.mod;
    }
    
    public ArrayList getListeners() {
        return this.listeners;
    }
    
    public void addListeners(final ComponentClickListener e) {
        this.listeners.add(e);
    }
    
    @Override
    public void onMousePress(final int n, final int n2, final int n3) {
        if (n3 != 0) {
            return;
        }
        this.enabled = !this.enabled;
        final Iterator<ComponentClickListener> iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onComponenetClick(this, n3);
        }
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public Button(final int n, final int n2, final int n3, final int n4, final Component component, final String s, final Modules mod) {
        super(n, n2, n3, n4, ComponentType.BUTTON, component, s);
        this.listeners = new ArrayList<ComponentClickListener>();
        this.enabled = false;
        this.mod = mod;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
}
