/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.theme.dark;

import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.GLUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.gui.click.elements.Button;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;

public class DarkButton extends ComponentRenderer
{
    @Override
    public void drawComponent(final Component component, final int n, final int n2) {
        final Button button = (Button)component;
        final String text = button.getText();
        int n3 = ColorUtils.color(50, 50, 50, 100);
        final int color = ColorUtils.color(255, 255, 255, 255);
        if (GLUtils.isHovered(button.getX(), button.getY(), button.getDimension().width, button.getDimension().height, n, n2)) {
            n3 = ColorUtils.color(70, 70, 70, 255);
        }
        if (button.isEnabled()) {
            RenderUtils.drawRect((float)button.getX(), (float)button.getY(), (float)(button.getX() + button.getDimension().width - 1), (float)(button.getY() + button.getDimension().height), color);
        }
        else {
            final float n4 = (float)button.getX();
            final float n5 = (float)button.getY();
            final int n6 = button.getX() + button.getDimension().width;
            final int n7 = 1;
            RenderUtils.drawRect(n4, n5, (float)((n6 & ~n7) + (n7 & ~n6)), (float)(button.getY() + button.getDimension().height), n3);
        }
        Main.fontRenderer.drawString(text, (float)(button.getX() + 5), (float)(button.getY() + (button.getDimension().height / 2 - Main.fontRenderer.getHeight() / 4)), ColorUtils.color(255, 255, 255, 255));
    }
    
    public DarkButton(final Theme theme) {
        super(ComponentType.BUTTON, theme);
    }
    
    @Override
    public void doInteractions(final Component component, final int n, final int n2) {
    }
}
