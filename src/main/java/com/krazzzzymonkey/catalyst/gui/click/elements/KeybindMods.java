/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.elements;

import org.lwjgl.input.Keyboard;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;

public class KeybindMods extends Component
{
    public Modules mod;
    public boolean editing;
    
    public boolean isEditing() {
        return this.editing;
    }
    
    public KeybindMods(final int n, final int n2, final int n3, final int n4, final Component component, final Modules mod) {
        super(n, n2, n3, n4, ComponentType.KEYBIND, component, "");
        this.mod = mod;
    }
    
    public void setEditing(final boolean editing) {
        this.editing = editing;
    }
    
    @Override
    public void onMousePress(final int n, final int n2, final int n3) {
        if (n > this.getX() + Wrapper.INSTANCE.fontRenderer().getStringWidth("Key") + 6) {
            if (n < this.getX() + this.getDimension().width) {
                if (n2 > this.getY() && n2 < this.getY() + this.getDimension().height) {
                    this.editing = !this.editing;
                }
            }
        }
    }
    
    public Modules getMod() {
        return this.mod;
    }
    
    @Override
    public void onUpdate() {
        if (Keyboard.getEventKeyState() && this.editing) {
            if (Keyboard.getEventKey() == 211) {
                this.mod.setKey(-1);
            }
            else if (Keyboard.getEventKey() == 14) {
                this.mod.setKey(-1);
            }
            else {
                this.mod.setKey(Keyboard.getEventKey());
            }
            this.editing = false;
        }
    }
}
