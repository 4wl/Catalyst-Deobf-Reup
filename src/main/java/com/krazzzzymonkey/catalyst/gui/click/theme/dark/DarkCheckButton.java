/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.theme.dark;

import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.utils.MathUtils;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.gui.click.elements.CheckButton;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;

public class DarkCheckButton extends ComponentRenderer
{
    public DarkCheckButton(final Theme theme) {
        super(ComponentType.CHECK_BUTTON, theme);
    }
    
    @Override
    public void drawComponent(final Component component, final int n, final int n2) {
        final CheckButton checkButton = (CheckButton)component;
        final String text = checkButton.getText();
        if (checkButton.getModeValue() == null) {
            RenderUtils.drawBorderedRect(checkButton.getX() + 1, checkButton.getY() - 1, checkButton.getX() + checkButton.getDimension().width - 2, checkButton.getY() + 13, 2.0f, ColorUtils.color(0.0f, 0.0f, 0.0f, 1.0f), ColorUtils.color(20, 20, 20, 255));
            Main.smallFontRenderer.drawString(text, (float)(checkButton.getX() + 5), (float)(MathUtils.getMiddle(checkButton.getY() - 1, checkButton.getY() + checkButton.getDimension().height) - Main.smallFontRenderer.getHeight() / 3 - 1), checkButton.isEnabled() ? -1 : ColorUtils.color(0.5f, 0.5f, 0.5f, 1.0f));
            return;
        }
        for (final Mode mode : checkButton.getModeValue().getModes()) {
            if (mode.getName().equals(text)) {
                RenderUtils.drawBorderedRect(checkButton.getX() + 1, checkButton.getY() - 2, checkButton.getX() + checkButton.getDimension().width - 2, checkButton.getY() + 13, 2.0f, ColorUtils.color(0.0f, 0.0f, 0.0f, 1.0f), ColorUtils.color(8, 8, 8, 255));
                Main.smallFontRenderer.drawString(text, (float)(checkButton.getX() + 5), (float)(MathUtils.getMiddle(checkButton.getY() - 2, checkButton.getY() + checkButton.getDimension().height) - Main.smallFontRenderer.getHeight() / 3 - 1), mode.isToggled() ? -1 : ColorUtils.color(0.5f, 0.5f, 0.5f, 1.0f));
            }
        }
    }
    
    @Override
    public void doInteractions(final Component component, final int n, final int n2) {
    }
}
