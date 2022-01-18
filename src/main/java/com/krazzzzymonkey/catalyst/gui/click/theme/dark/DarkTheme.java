/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.theme.dark;

import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;

public class DarkTheme extends Theme
{
    public DarkTheme() {
        super("CatalystDark");
        this.fontRenderer = Wrapper.INSTANCE.fontRenderer();
        this.addRenderer(ComponentType.FRAME, new DarkFrame(this));
        this.addRenderer(ComponentType.BUTTON, new DarkButton(this));
        this.addRenderer(ComponentType.SLIDER, new DarkSlider(this));
        this.addRenderer(ComponentType.CHECK_BUTTON, new DarkCheckButton(this));
        this.addRenderer(ComponentType.COLOR_PICKER, new DarkColorPicker(this));
        this.addRenderer(ComponentType.EXPANDING_BUTTON, new DarkExpandingButton(this));
        this.addRenderer(ComponentType.TEXT, new DarkText(this));
        this.addRenderer(ComponentType.KEYBIND, new DarkKeybinds(this));
        this.addRenderer(ComponentType.DROPDOWN, new DarkDropDown(this));
        this.addRenderer(ComponentType.COMBO_BOX, new DarkComboBox(this));
    }
}
