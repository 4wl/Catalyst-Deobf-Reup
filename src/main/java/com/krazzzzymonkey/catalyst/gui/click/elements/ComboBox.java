/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.elements;

import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.gui.click.listener.ComboBoxListener;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.gui.click.base.Container;

public class ComboBox extends Container
{
    public ArrayList<ComboBoxListener> listeners;
    public int selectedIndex;
    public String[] elements;
    public boolean selected;
    
    public String getSelectedElement() {
        return this.elements[this.selectedIndex];
    }
    
    public void setSelectedIndex(final int selectedIndex) {
        this.selectedIndex = selectedIndex;
        final Iterator<ComboBoxListener> iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onComboBoxSelectionChange(this);
        }
    }
    
    public ComboBox(final int n, final int n2, final int n3, final int n4, final Component component, final String s, final String... elements) {
        super(n, n2, n3, n4, ComponentType.COMBO_BOX, component, s);
        this.listeners = new ArrayList<ComboBoxListener>();
        this.elements = elements;
    }
    
    public void setSelected(final boolean selected) {
        this.selected = selected;
        final Iterator<ComboBoxListener> iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onComboBoxSelectionChange(this);
        }
    }
    
    public int getSelectedIndex() {
        return this.selectedIndex;
    }
    
    @Override
    public void onMousePress(final int n, final int n2, final int n3) {
        if (this.isMouseOver(n, n2)) {
            if (n3 == 1) {
                this.selected = !this.selected;
            }
            if (n3 == 0) {
                int n4 = this.getDimension().height + 2;
                final String[] elements = this.getElements();
                for (int i = 0; i < elements.length; ++i) {
                    if (i != this.getSelectedIndex()) {
                        if (n2 >= n4 && n2 <= n4 + Wrapper.INSTANCE.fontRenderer().FONT_HEIGHT) {
                            this.setSelectedIndex(i);
                            this.setSelected(false);
                            break;
                        }
                        n4 += Wrapper.INSTANCE.fontRenderer().FONT_HEIGHT + 2;
                    }
                }
            }
        }
    }
    
    public void setElements(final String[] elements) {
        this.elements = elements;
        this.selectedIndex = 0;
    }
    
    public ArrayList getListeners() {
        return this.listeners;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public void addListeners(final ComboBoxListener e) {
        this.listeners.add(e);
    }
    
    public String[] getElements() {
        return this.elements;
    }
}
