/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.base;

import com.krazzzzymonkey.catalyst.gui.click.ClickGui;

public class Component extends Interactable
{
    public ComponentType componentType;
    public String text;
    public Component component;
    public int height;
    
    public ComponentType getComponentType() {
        return this.componentType;
    }
    
    public Component getComponent() {
        return this.component;
    }
    
    public void setComponentType(final ComponentType componentType) {
        this.componentType = componentType;
    }
    
    public void onUpdate() {
    }
    
    public void render(final int n, final int n2) {
        ClickGui.getTheme().getRenderer().get(this.componentType).drawComponent(this, n, n2);
    }
    
    public void setComponent(final Component component) {
        this.component = component;
    }
    
    public Component(final int n, final int n2, final int n3, final int n4, final ComponentType componentType, final Component component, final String text) {
        super(n, n2, n3, n4);
        this.height = 0;
        this.componentType = componentType;
        this.component = component;
        this.text = text;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public String getText() {
        return this.text;
    }
}
