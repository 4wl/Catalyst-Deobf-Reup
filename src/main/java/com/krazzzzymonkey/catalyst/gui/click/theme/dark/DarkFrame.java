/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.theme.dark;

import com.krazzzzymonkey.catalyst.Main;
import java.awt.Color;
import java.awt.Font;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import java.awt.Dimension;
import com.krazzzzymonkey.catalyst.gui.click.elements.Frame;
import com.krazzzzymonkey.catalyst.utils.font.CFontRenderer;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;

public class DarkFrame extends ComponentRenderer
{
    public CFontRenderer fontRenderer;
    
    public void isMaximized(final Frame frame, final Dimension dimension, final int n, final int n2) {
        float n3 = 0.0f;
        for (final Component component : frame.getComponents()) {
            n3 += (float)(ClickGui.rainbowHue.getValue() / 10.0);
            component.setxPos(frame.getX());
            component.setButtonColor(n3, ClickGui.rainbowSpeed.getValue());
        }
        float n4 = 0.0f;
        final float n5 = (float)(dimension.height - 16);
        final Iterator<Component> iterator2 = frame.getComponents().iterator();
        while (iterator2.hasNext()) {
            n4 += iterator2.next().getDimension().height;
        }
        final float n6 = n5 * (n5 / n4);
        final double n7 = (frame.getDimension().getHeight() - 16.0 - n6) * (frame.getScrollAmmount() / (double)frame.getMaxScroll()) + (frame.getY() + 16);
        frame.renderChildren(n, n2);
        if (n6 < n5) {
            if (ClickGui.rainbow.getValue()) {
                RenderUtils.drawRect((float)(int)(frame.getX() + dimension.getWidth() - 1.0), (float)(int)n7, (float)(int)(frame.getX() + frame.getDimension().getWidth()), (float)(int)(n7 + n6), ClickGui.rainbowMode.getMode("RainbowFlow").isToggled() ? frame.getButtonColor().getRGB() : ColorUtils.rainbow().getRGB());
            }
            else {
                RenderUtils.drawRect((float)(int)(frame.getX() + dimension.getWidth() - 1.0), (float)(int)n7, (float)(int)(frame.getX() + frame.getDimension().getWidth()), (float)(int)(n7 + n6), ClickGui.getColor());
            }
        }
    }
    
    public void isPinnable(final Frame frame, final Dimension dimension, final int n, final int n2) {
    }
    
    public DarkFrame(final Theme theme) {
        super(ComponentType.FRAME, theme);
        this.fontRenderer = new CFontRenderer(new Font("Arial", 0, 20), true, true);
    }
    
    @Override
    public void doInteractions(final Component component, final int n, final int n2) {
        final Frame frame = (Frame)component;
        final Dimension dimension = frame.getDimension();
        if (n >= frame.getX() + dimension.width - 16 && frame.isMaximizible() && n2 >= frame.getY() && n2 <= frame.getY() + 16 && n <= frame.getX() + dimension.width) {
            frame.setMaximized(!frame.isMaximized());
        }
        if (n >= frame.getX() + dimension.width - 38 && n2 >= frame.getY() && n2 <= frame.getY() + 16) {
            if (n <= frame.getX() + dimension.width - 16) {
                frame.setPinned(!frame.isPinned());
            }
        }
    }
    
    public void isMaximizible(final Frame frame, final Dimension dimension, final int n, final int n2) {
        Color color;
        if (n >= frame.getX() + dimension.width - 19 && n2 >= frame.getY() && n2 <= frame.getY() + 19 && n <= frame.getX() + dimension.width) {
            color = new Color(255, 255, 255, 255);
        }
        else {
            color = new Color(155, 155, 155, 255);
        }
        this.theme.fontRenderer.drawStringWithShadow(frame.isMaximized() ? "-" : "+", (float)(frame.getX() + dimension.width - 12), (float)(frame.getY() + 3), color.getRGB());
    }
    
    @Override
    public void drawComponent(final Component component, final int n, final int n2) {
        final Frame frame = (Frame)component;
        final Dimension dimension = frame.getDimension();
        if (frame.isMaximized()) {
            this.isMaximized(frame, dimension, n, n2);
        }
        if (ClickGui.rainbow.getValue()) {
            RenderUtils.drawRect((float)frame.getX(), (float)((frame.getY() ^ 0x3) - 1), (float)(frame.getX() + dimension.width), (float)(frame.getY() + 15), ClickGui.rainbowMode.getMode("RainbowFlow").isToggled() ? frame.getButtonColor().getRGB() : ColorUtils.rainbow().getRGB());
        }
        else {
            RenderUtils.drawRect((float)frame.getX(), (float)(frame.getY() + 3), (float)(frame.getX() + dimension.width), (float)(frame.getY() + 15), ClickGui.getColor());
        }
        if (frame.isMaximizible()) {
            this.isMaximizible(frame, dimension, n, n2);
        }
        this.fontRenderer.drawStringWithShadow(frame.getText(), frame.getX() + frame.getDimension().width / 2 - Main.fontRenderer.getStringWidth(frame.getText()) / 2, frame.getY() + 4, ColorUtils.color(1.0f, 1.0f, 1.0f, 1.0f));
    }
}
