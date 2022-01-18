/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.elements;

import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.gui.click.listener.ComponentClickListener;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.gui.click.base.Container;

public class ExpandingButton extends Container
{
    public boolean maximized;
    public Modules mod;
    public Component component;
    public boolean enabled;
    public ArrayList<ComponentClickListener> listeners;
    public int buttonHeight;
    
    public void setMaximized(final boolean maximized) {
        this.maximized = maximized;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean isMaximized() {
        return this.maximized;
    }
    
    public void setButtonHeight(final int buttonHeight) {
        this.buttonHeight = buttonHeight;
    }
    
    @Override
    public void render(final int n, final int n2) {
        int buttonHeight = this.buttonHeight;
        if (this.maximized) {
            for (final Component component : this.getComponents()) {
                component.setxPos(this.getX());
                component.setyPos(this.getY() + buttonHeight + 1);
                buttonHeight += component.getDimension().height;
                component.getDimension().setSize(this.getDimension().width, component.getDimension().height);
            }
        }
        this.getDimension().setSize(this.getDimension().width, buttonHeight);
        super.render(n, n2);
    }
    
    public ExpandingButton(final int n, final int n2, final int n3, final int buttonHeight, final Component component, final String s) {
        super(n, n2, n3, 0, ComponentType.EXPANDING_BUTTON, component, s);
        this.listeners = new ArrayList<ComponentClickListener>();
        this.enabled = false;
        this.maximized = false;
        this.buttonHeight = buttonHeight;
        this.component = component;
    }
    
    @Override
    public void onMouseDrag(final int n, final int n2) {
        if (this.isMouseOver(n, n2)) {
            for (final Component component : this.getComponents()) {
                if (component.isMouseOver(n, n2)) {
                    component.onMouseDrag(n, n2);
                }
            }
        }
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    @Override
    public void onMousePress(final int n, final int n2, final int n3) {
        if (this.isMouseOverButton(n, n2)) {
            if (n3 == 0) {
                this.enabled = !this.enabled;
                final Iterator<ComponentClickListener> iterator = this.listeners.iterator();
                while (iterator.hasNext()) {
                    iterator.next().onComponenetClick(this, n3);
                }
            }
            else if (n3 == 1) {
                this.maximized = !this.maximized;
            }
        }
        else if (this.isMouseOver(n, n2)) {
            for (final Component component : this.getComponents()) {
                if (component.isMouseOver(n, n2)) {
                    component.onMousePress(n, n2, n3);
                }
            }
        }
    }
    
    @Override
    public void onUpdate() {
        int buttonHeight = this.buttonHeight;
        if (this.maximized) {
            for (final Component component : this.getComponents()) {
                component.setyPos(this.getY() + buttonHeight + 1);
                buttonHeight += component.getDimension().height;
                component.getDimension().setSize(this.getDimension().width, component.getDimension().height);
            }
        }
        this.getDimension().setSize(this.getDimension().width, buttonHeight);
    }
    
    @Override
    public void onKeyPressed(final int n, final char c) {
        final Iterator<Component> iterator = this.getComponents().iterator();
        while (iterator.hasNext()) {
            iterator.next().onKeyPressed(n, c);
        }
    }
    
    public int getButtonHeight() {
        return this.buttonHeight;
    }
    
    public ArrayList getListeners() {
        return this.listeners;
    }
    
    public void addListner(final ComponentClickListener e) {
        this.listeners.add(e);
    }
    
    public boolean isMouseOverButton(final int n, final int n2) {
        return n >= this.getX() && n2 >= this.getY() && n <= this.getX() + this.getDimension().width && n2 <= this.getY() + this.buttonHeight - 1;
    }
    
    public ExpandingButton(final int n, final int n2, final int n3, final int buttonHeight, final Component component, final String s, final Modules mod) {
        super(n, n2, n3, 0, ComponentType.EXPANDING_BUTTON, component, s);
        this.listeners = new ArrayList<ComponentClickListener>();
        this.enabled = false;
        this.maximized = false;
        this.buttonHeight = buttonHeight;
        this.component = component;
        this.mod = mod;
    }
    
    @Override
    public void onKeyReleased(final int n, final char c) {
        final Iterator<Component> iterator = this.getComponents().iterator();
        while (iterator.hasNext()) {
            iterator.next().onKeyReleased(n, c);
        }
    }
    
    public Modules getMod() {
        return this.mod;
    }
    
    @Override
    public void onMouseRelease(final int n, final int n2, final int n3) {
        if (this.isMouseOver(n, n2)) {
            for (final Component component : this.getComponents()) {
                if (component.isMouseOver(n, n2)) {
                    component.onMouseRelease(n, n2, n3);
                }
            }
        }
    }
}
