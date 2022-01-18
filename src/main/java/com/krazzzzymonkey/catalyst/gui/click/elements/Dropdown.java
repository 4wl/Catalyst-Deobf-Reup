/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.elements;

import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import com.krazzzzymonkey.catalyst.gui.click.base.Container;

public class Dropdown extends Container
{
    public boolean maximized;
    public int dropdownHeight;
    
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
    
    public boolean isMaximized() {
        return this.maximized;
    }
    
    @Override
    public void onMousePress(final int n, final int n2, final int n3) {
        if (n >= this.getX() && n2 >= this.getY() && n <= this.getX() + this.getDimension().width && n2 <= (this.getY() ^ this.dropdownHeight) - 1) {
            if (n3 == 1 || n3 == 0) {
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
        int dropdownHeight = this.dropdownHeight;
        if (this.maximized) {
            for (final Component component : this.getComponents()) {
                component.setyPos(this.getY() + dropdownHeight + 1);
                dropdownHeight += component.getDimension().height;
                component.getDimension().setSize(this.getDimension().width, component.getDimension().height);
            }
        }
        this.getDimension().setSize(this.getDimension().width, dropdownHeight);
    }
    
    public void setMaximized(final boolean maximized) {
        this.maximized = maximized;
    }
    
    @Override
    public void render(final int n, final int n2) {
        int dropdownHeight = this.dropdownHeight;
        if (this.maximized) {
            for (final Component component : this.getComponents()) {
                component.setxPos(this.getX());
                component.setyPos(this.getY() + dropdownHeight + 1);
                dropdownHeight += component.getDimension().height;
                component.getDimension().setSize(this.getDimension().width, component.getDimension().height);
            }
        }
        this.getDimension().setSize(this.getDimension().width, dropdownHeight);
        super.render(n, n2);
    }
    
    public void setDropdownHeight(final int dropdownHeight) {
        this.dropdownHeight = dropdownHeight;
    }
    
    public Dropdown(final int n, final int n2, final int n3, final int dropdownHeight, final Component component, final String s) {
        super(n, n2, n3, 0, ComponentType.DROPDOWN, component, s);
        this.maximized = false;
        this.dropdownHeight = dropdownHeight;
    }
    
    public int getDropdownHeight() {
        return this.dropdownHeight;
    }
}
