/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.theme.dark;

import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.gui.click.elements.Slider;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;

public class DarkSlider extends ComponentRenderer
{
    public DarkSlider(final Theme theme) {
        super(ComponentType.SLIDER, theme);
    }
    
    @Override
    public void drawComponent(final Component component, final int n, final int n2) {
        final Slider slider = (Slider)component;
        final int n3 = (int)(slider.getDimension().getWidth() * slider.getPercent());
        final int color = ColorUtils.color(255, 255, 255, 255);
        final String renderValue = slider.getRenderValue();
        RenderUtils.drawBorderedRect(slider.getX() + 1, slider.getY() - 1, slider.getX() + slider.getDimension().width - 2, slider.getY() + 14, 2.0f, ColorUtils.color(0.0f, 0.0f, 0.0f, 1.0f), ColorUtils.color(20, 20, 20, 255));
        if (ClickGui.rainbow.getValue()) {
            RenderUtils.drawRect((float)slider.getX(), (float)slider.getY(), (float)(slider.getX() + (n3 - 2)), (float)(slider.getY() + 14), ClickGui.rainbowMode.getMode("Static").isToggled() ? ColorUtils.rainbow().getRGB() : ColorUtils.color(slider.getButtonColor().getRed(), slider.getButtonColor().getGreen(), slider.getButtonColor().getBlue(), 100));
        }
        else {
            RenderUtils.drawRect((float)slider.getX(), (float)slider.getY(), (float)(slider.getX() + (n3 - 2)), (float)(slider.getY() + 14), ColorUtils.color(ClickGui.clickGuiColor.getColor().getRed(), ClickGui.clickGuiColor.getColor().getGreen(), ClickGui.clickGuiColor.getColor().getBlue(), 100));
        }
        Main.smallFontRenderer.drawString(slider.getText(), (float)(slider.getX() + 5), (float)(slider.getY() + 4), -1);
        Main.smallFontRenderer.drawString(renderValue + "", (float)(slider.getX() + slider.getDimension().width - this.theme.fontRenderer.getStringWidth(renderValue + "") - 2), (float)(slider.getY() + 4), color);
    }
    
    @Override
    public void doInteractions(final Component component, final int n, final int n2) {
    }
}
