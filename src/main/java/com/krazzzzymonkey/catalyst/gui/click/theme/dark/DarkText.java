/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.theme.dark;

import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.gui.click.elements.Text;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;

public class DarkText extends ComponentRenderer
{
    @Override
    public void drawComponent(final Component component, final int n, final int n2) {
        final Text text = (Text)component;
        final String[] message = text.getMessage();
        int y = text.getY();
        final String[] array = message;
        for (int length = array.length, i = 0; i < length; ++i) {
            Main.fontRenderer.drawString(array[i], (float)(text.getX() - 4), (float)(y - 4), -1);
            y += 10;
        }
    }
    
    public DarkText(final Theme theme) {
        super(ComponentType.TEXT, theme);
    }
    
    @Override
    public void doInteractions(final Component component, final int n, final int n2) {
    }
}
