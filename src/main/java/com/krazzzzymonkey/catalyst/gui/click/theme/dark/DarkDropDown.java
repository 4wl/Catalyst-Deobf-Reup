/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.theme.dark;

import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.gui.click.elements.Dropdown;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;

public class DarkDropDown extends ComponentRenderer
{
    @Override
    public void drawComponent(final Component component, final int n, final int n2) {
        ColorUtils.color(0, 0, 0, 150);
        final Dropdown dropdown = (Dropdown)component;
        final String text = dropdown.getText();
        RenderUtils.drawBorderedRect(dropdown.getX() + 1, dropdown.getY() - 1, dropdown.getX() + dropdown.getDimension().width - 2, dropdown.getY() + 13, 2.0f, ColorUtils.color(0.0f, 0.0f, 0.0f, 1.0f), ColorUtils.color(20, 20, 20, 255));
        Main.smallFontRenderer.drawString(text, (float)(dropdown.getX() + 5), (float)(dropdown.getY() - 2 + (dropdown.getDropdownHeight() / 2 - Main.smallFontRenderer.getHeight() / 4)), -1);
        if (dropdown.isMaximized()) {
            Main.smallFontRenderer.drawString("-", (float)(dropdown.getX() + dropdown.getDimension().width - 9), (float)(dropdown.getY() - 2 + (dropdown.getDropdownHeight() / 2 - Main.smallFontRenderer.getHeight() / 4) - 1), -1);
            dropdown.renderChildren(n, n2);
        }
        else {
            Main.smallFontRenderer.drawString("+", (float)(dropdown.getX() + dropdown.getDimension().width - 10), (float)(dropdown.getY() - 2 + (dropdown.getDropdownHeight() / 2 - Main.smallFontRenderer.getHeight() / 4)), -1);
        }
    }
    
    @Override
    public void doInteractions(final Component component, final int n, final int n2) {
    }
    
    public DarkDropDown(final Theme theme) {
        super(ComponentType.DROPDOWN, theme);
    }
}
