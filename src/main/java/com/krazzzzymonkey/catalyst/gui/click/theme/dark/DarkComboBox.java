/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.theme.dark;

import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import org.lwjgl.input.Mouse;
import java.awt.Point;
import com.krazzzzymonkey.catalyst.utils.visual.GLUtils;
import java.awt.Color;
import java.awt.Dimension;
import org.lwjgl.opengl.GL11;
import com.krazzzzymonkey.catalyst.gui.click.elements.ComboBox;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;

public class DarkComboBox extends ComponentRenderer
{
    @Override
    public void drawComponent(final Component component, final int x, final int y) {
        final ComboBox comboBox = (ComboBox)component;
        final Dimension dimension = comboBox.getDimension();
        GL11.glEnable(3042);
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glTranslated((double)(1 * comboBox.getX()), (double)(1 * comboBox.getY()), 0.0);
        int max = 0;
        final String[] elements = comboBox.getElements();
        for (int length = elements.length, i = 0; i < length; ++i) {
            max = Math.max(max, this.theme.getFontRenderer().getStringWidth(elements[i]));
        }
        int n = 0;
        if (comboBox.isSelected()) {
            final String[] elements2 = comboBox.getElements();
            for (int j = 0; j < elements2.length - 1; ++j) {
                n += this.theme.getFontRenderer().FONT_HEIGHT + 2;
            }
            n += 2;
        }
        comboBox.setDimension(new Dimension(max + 8 + this.theme.getFontRenderer().FONT_HEIGHT, this.theme.getFontRenderer().FONT_HEIGHT));
        GLUtils.glColor(new Color(2, 2, 2, 40));
        GL11.glBegin(7);
        GL11.glVertex2d(0.0, 0.0);
        GL11.glVertex2d((double)dimension.width, 0.0);
        GL11.glVertex2d((double)dimension.width, (double)(dimension.height + n));
        GL11.glVertex2d(0.0, (double)(dimension.height + n));
        GL11.glEnd();
        final Point point = new Point(x, y);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, Mouse.isButtonDown(0) ? 0.5f : 0.3f);
        if (GLUtils.isHovered(comboBox.getX(), comboBox.getY(), dimension.width, dimension.height, x, y)) {
            GL11.glBegin(7);
            GL11.glVertex2d(0.0, 0.0);
            GL11.glVertex2d((double)dimension.width, 0.0);
            GL11.glVertex2d((double)dimension.width, (double)dimension.height);
            GL11.glVertex2d(0.0, (double)dimension.height);
            GL11.glEnd();
        }
        else if (comboBox.isSelected() && point.x >= comboBox.getX() && point.x <= comboBox.getX() + dimension.width) {
            int height = dimension.height;
            final String[] elements3 = comboBox.getElements();
            for (int k = 0; k < elements3.length; ++k) {
                if (k != comboBox.getSelectedIndex()) {
                    int n2 = this.theme.getFontRenderer().FONT_HEIGHT + 2;
                    Label_1084: {
                        Label_1081: {
                            if (comboBox.getSelectedIndex() == 0) {
                                if (k == 1) {
                                    break Label_1081;
                                }
                            }
                            else if (k == 0) {
                                break Label_1081;
                            }
                            if (comboBox.getSelectedIndex() == elements3.length - 1) {
                                if (k != elements3.length - 2) {
                                    break Label_1084;
                                }
                            }
                            else if (k != elements3.length - 1) {
                                break Label_1084;
                            }
                        }
                        ++n2;
                    }
                    if (point.y >= comboBox.getY() + height && point.y <= comboBox.getY() + height + n2) {
                        GL11.glBegin(7);
                        GL11.glVertex2d(0.0, (double)height);
                        GL11.glVertex2d(0.0, (double)(height + n2));
                        GL11.glVertex2d((double)dimension.width, (double)(height + n2));
                        GL11.glVertex2d((double)dimension.width, (double)height);
                        GL11.glEnd();
                        break;
                    }
                    height += n2;
                }
            }
        }
        final int n3 = this.theme.getFontRenderer().FONT_HEIGHT + 4;
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.3f);
        GL11.glBegin(4);
        if (comboBox.isSelected()) {
            GL11.glVertex2d(max + 4 + n3 / 2.0, n3 / 3.0);
            GL11.glVertex2d(max + 4 + n3 / 3.0, 2.0 * n3 / 3.0);
            GL11.glVertex2d(max + 4 + 2.0 * n3 / 3.0, 2.0 * n3 / 3.0);
        }
        else {
            GL11.glVertex2d(max + 4 + n3 / 3.0, n3 / 3.0);
            GL11.glVertex2d(max + 4 + 2.0 * n3 / 3.0, n3 / 3.0);
            GL11.glVertex2d(max + 4 + n3 / 2.0, 2.0 * n3 / 3.0);
        }
        GL11.glEnd();
        GL11.glLineWidth(1.0f);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        if (comboBox.isSelected()) {
            GL11.glBegin(1);
            GL11.glVertex2d(2.0, (double)dimension.height);
            GL11.glVertex2d((double)(dimension.width - 2), (double)dimension.height);
            GL11.glEnd();
        }
        GL11.glBegin(1);
        GL11.glVertex2d((double)(max + 4), 2.0);
        GL11.glVertex2d((double)(max + 4), (double)(dimension.height - 2));
        GL11.glEnd();
        GL11.glBegin(2);
        if (comboBox.isSelected()) {
            GL11.glVertex2d(max + 4 + n3 / 2.0, n3 / 3.0);
            GL11.glVertex2d(max + 4 + n3 / 3.0, 2.0 * n3 / 3.0);
            GL11.glVertex2d(max + 4 + 2.0 * n3 / 3.0, 2.0 * n3 / 3.0);
        }
        else {
            GL11.glVertex2d(max + 4 + n3 / 3.0, n3 / 3.0);
            GL11.glVertex2d(max + 4 + 2.0 * n3 / 3.0, n3 / 3.0);
            final int n4 = max;
            final int n5 = 4;
            GL11.glVertex2d((n4 | n5) * 2 + (n4 ^ n5) + 1 + n3 / 2.0, 2.0 * n3 / 3.0);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        this.theme.getFontRenderer().drawString(comboBox.getSelectedElement(), 2, dimension.height / 2 - this.theme.getFontRenderer().FONT_HEIGHT / 4, -1);
        if (comboBox.isSelected()) {
            int n6 = dimension.height + 2;
            final String[] elements4 = comboBox.getElements();
            for (int l = 0; l < elements4.length; ++l) {
                if (l != comboBox.getSelectedIndex()) {
                    this.theme.getFontRenderer().drawString(elements4[l], 2, n6, -1);
                    n6 += this.theme.getFontRenderer().FONT_HEIGHT + 2;
                }
            }
        }
        GL11.glEnable(2884);
        GL11.glDisable(3042);
        GL11.glTranslated((double)(-1 * comboBox.getX()), (double)(-1 * comboBox.getY()), 0.0);
    }
    
    public DarkComboBox(final Theme theme) {
        super(ComponentType.COMBO_BOX, theme);
    }
    
    @Override
    public void doInteractions(final Component component, final int n, final int n2) {
        final ComboBox comboBox = (ComboBox)component;
    }
}
