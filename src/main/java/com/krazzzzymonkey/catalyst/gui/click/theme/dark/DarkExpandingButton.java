/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.theme.dark;

import com.krazzzzymonkey.catalyst.gui.click.ClickGuiScreen;
import com.krazzzzymonkey.catalyst.gui.Tooltip;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.GLUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.gui.click.elements.ExpandingButton;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import java.awt.Font;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import com.krazzzzymonkey.catalyst.utils.font.CFontRenderer;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;

public class DarkExpandingButton extends ComponentRenderer
{
    public CFontRenderer fontRenderer;
    
    public DarkExpandingButton(final Theme theme) {
        super(ComponentType.EXPANDING_BUTTON, theme);
        this.fontRenderer = new CFontRenderer(new Font("Arial", 0, 16), true, true);
    }
    
    @Override
    public void doInteractions(final Component component, final int n, final int n2) {
    }
    
    @Override
    public void drawComponent(final Component component, final int n, final int n2) {
        final ExpandingButton expandingButton = (ExpandingButton)component;
        final String text = expandingButton.getText();
        final int color = ColorUtils.color(0, 0, 0, 130);
        final int color2 = ColorUtils.color(200, 200, 200, 255);
        if (GLUtils.isHovered(expandingButton.getX(), expandingButton.getY(), expandingButton.getDimension().width, 14, n, n2)) {
            RenderUtils.drawRect((float)expandingButton.getX(), (float)expandingButton.getY(), (float)(expandingButton.getX() + expandingButton.getDimension().width), (float)(expandingButton.getY() + expandingButton.getButtonHeight()), ColorUtils.color(20, 20, 20, 100));
        }
        if (expandingButton.isEnabled()) {
            RenderUtils.drawBorderedRect(expandingButton.getX() + 1, expandingButton.getY(), expandingButton.getX() + expandingButton.getDimension().width - 1, expandingButton.getY() + 14, 2.0f, ColorUtils.color(20, 20, 20, 100), color);
            if (ClickGui.rainbow.getValue()) {
                RenderUtils.drawBorderedRect(expandingButton.getX() + 1, expandingButton.getY(), expandingButton.getX() + expandingButton.getDimension().width - 1, expandingButton.getY() + 14, 2.0f, ColorUtils.color(20, 20, 20, 100), ClickGui.rainbowMode.getMode("RainbowFlow").isToggled() ? ColorUtils.color(expandingButton.getButtonColor().getRed(), expandingButton.getButtonColor().getGreen(), expandingButton.getButtonColor().getBlue(), 100) : ColorUtils.color(ColorUtils.rainbow().getRed(), ColorUtils.rainbow().getGreen(), ColorUtils.rainbow().getBlue(), 100));
            }
            else {
                RenderUtils.drawBorderedRect(expandingButton.getX() + 1, expandingButton.getY(), expandingButton.getX() + expandingButton.getDimension().width - 1, expandingButton.getY() + 14, 2.0f, ColorUtils.color(20, 20, 20, 100), ColorUtils.color(ClickGui.clickGuiToggledColor.getColor().getRed(), ClickGui.clickGuiToggledColor.getColor().getGreen(), ClickGui.clickGuiToggledColor.getColor().getBlue(), 100));
            }
            this.fontRenderer.drawString(text, (float)(expandingButton.getX() + 6), (float)(expandingButton.getY() + (expandingButton.getButtonHeight() / 2 - this.fontRenderer.getHeight() / 4) - 2), -1);
        }
        else {
            RenderUtils.drawBorderedRect(expandingButton.getX() + 1, expandingButton.getY(), expandingButton.getX() + expandingButton.getDimension().width - 1, expandingButton.getY() + 14, 2.0f, ColorUtils.color(20, 20, 20, 100), color);
            if (ClickGui.rainbow.getValue()) {
                RenderUtils.drawBorderedRect(expandingButton.getX() + 1, expandingButton.getY(), expandingButton.getX() + expandingButton.getDimension().width - 1, expandingButton.getY() + 14, 2.0f, ColorUtils.color(20, 20, 20, 100), ClickGui.rainbowMode.getMode("RainbowFlow").isToggled() ? ColorUtils.color(expandingButton.getButtonColor().getRed(), expandingButton.getButtonColor().getGreen(), expandingButton.getButtonColor().getBlue(), 50) : ColorUtils.color(ColorUtils.rainbow().getRed(), ColorUtils.rainbow().getGreen(), ColorUtils.rainbow().getBlue(), 50));
            }
            else {
                RenderUtils.drawBorderedRect(expandingButton.getX() + 1, expandingButton.getY(), expandingButton.getX() + expandingButton.getDimension().width - 1, expandingButton.getY() + 14, 2.0f, ColorUtils.color(20, 20, 20, 100), ColorUtils.color(ClickGui.clickGuiBackGroundColor.getColor().getRed(), ClickGui.clickGuiBackGroundColor.getColor().getGreen(), ClickGui.clickGuiBackGroundColor.getColor().getBlue(), 50));
            }
            this.fontRenderer.drawString(text, (float)(expandingButton.getX() + 6), (float)(expandingButton.getY() + (expandingButton.getButtonHeight() / 2 - this.fontRenderer.getHeight() / 4) - 2), color2);
        }
        if (ClickGui.rainbow.getValue()) {
            RenderUtils.drawRect((float)expandingButton.getX(), (float)(expandingButton.getY() - 1), (float)(expandingButton.getX() + 2), (float)(expandingButton.getY() + 15), ClickGui.rainbowMode.getMode("RainbowFlow").isToggled() ? expandingButton.getButtonColor().getRGB() : ColorUtils.rainbow().getRGB());
            RenderUtils.drawRect((float)(expandingButton.getX() + expandingButton.getDimension().width - 2), (float)(expandingButton.getY() - 1), (float)(expandingButton.getX() + expandingButton.getDimension().width), (float)(expandingButton.getY() + 15), ClickGui.rainbowMode.getMode("RainbowFlow").isToggled() ? expandingButton.getButtonColor().getRGB() : ColorUtils.rainbow().getRGB());
        }
        else {
            RenderUtils.drawRect((float)expandingButton.getX(), (float)(expandingButton.getY() - 1), (float)(expandingButton.getX() + 2), (float)(expandingButton.getY() + 15), ClickGui.getColor());
            final int n3 = expandingButton.getX() + expandingButton.getDimension().width;
            final int n4 = 2;
            RenderUtils.drawRect((float)((n3 & ~n4) * 2 + (n3 ^ n4) + 1), (float)(expandingButton.getY() - 1), (float)(expandingButton.getX() + expandingButton.getDimension().width), (float)(expandingButton.getY() + 15), ClickGui.getColor());
        }
        if (expandingButton.isMaximized()) {
            RenderUtils.drawRect((float)expandingButton.getX(), (float)(expandingButton.getY() + expandingButton.getButtonHeight() - 1), (float)(expandingButton.getX() + expandingButton.getDimension().width), (float)(expandingButton.getY() + expandingButton.getButtonHeight()), ((boolean)ClickGui.rainbow.getValue()) ? (ClickGui.rainbowMode.getMode("RainbowFlow").isToggled() ? expandingButton.getButtonColor().getRGB() : ColorUtils.rainbow().getRGB()) : ClickGui.getColor());
            RenderUtils.drawRect((float)expandingButton.getX(), (float)(expandingButton.getY() + expandingButton.getDimension().height - 1), (float)(expandingButton.getX() + expandingButton.getDimension().width), (float)(expandingButton.getY() + expandingButton.getDimension().height), ((boolean)ClickGui.rainbow.getValue()) ? (ClickGui.rainbowMode.getMode("RainbowFlow").isToggled() ? expandingButton.getButtonColor().getRGB() : ColorUtils.rainbow().getRGB()) : ClickGui.getColor());
        }
        if (!expandingButton.isMaximized()) {
            this.drawExpanded(expandingButton.getX() + expandingButton.getDimension().width - 15, expandingButton.getY() + 3, 13, false, new Color(255, 255, 255, 100).hashCode());
        }
        else {
            this.drawExpanded(expandingButton.getX() + expandingButton.getDimension().width - 15, expandingButton.getY() + 3, 13, true, new Color(255, 255, 255, 100).hashCode());
        }
        if (expandingButton.isMaximized()) {
            expandingButton.renderChildren(n, n2);
        }
        final String description = expandingButton.getMod().getDescription();
        if (description != null && expandingButton.isMouseOver(n, n2) && !expandingButton.isMaximized() && ModuleManager.getModule("ClickGui").isToggledValue("Tooltip")) {
            ClickGuiScreen.tooltip = new Tooltip(description, n, n2, this.fontRenderer);
        }
    }
}
