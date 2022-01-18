/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.elements;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.listener.CheckButtonClickListener;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;

public class CheckButton extends Component
{
    public ModeValue modeValue;
    public boolean enabled;
    public ArrayList<CheckButtonClickListener> listeners;
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public ModeValue getModeValue() {
        return this.modeValue;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public CheckButton(final int n, final int n2, final int n3, final int n4, final Component component, final String s, final boolean enabled, final ModeValue modeValue) {
        super(n, n2, n3, n4, ComponentType.CHECK_BUTTON, component, s);
        this.listeners = new ArrayList<CheckButtonClickListener>();
        this.enabled = false;
        this.modeValue = null;
        this.enabled = enabled;
        this.modeValue = modeValue;
    }
    
    @Override
    public void onMousePress(final int n, final int n2, final int n3) {
        if (this.modeValue != null) {
            final Mode[] modes = this.modeValue.getModes();
            for (int length = modes.length, i = 0; i < length; ++i) {
                modes[i].setToggled(false);
            }
            this.enabled = true;
        }
        else {
            this.enabled = !this.enabled;
        }
        final Iterator<CheckButtonClickListener> iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onCheckButtonClick(this);
        }
    }
    
    public ArrayList getListeners() {
        return this.listeners;
    }
    
    public void addListeners(final CheckButtonClickListener e) {
        this.listeners.add(e);
    }
}
