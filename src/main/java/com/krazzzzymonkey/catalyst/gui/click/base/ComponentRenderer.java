/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.base;

import com.krazzzzymonkey.catalyst.utils.visual.GLUtils;
import org.lwjgl.opengl.GL11;
import java.awt.Point;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;

public abstract class ComponentRenderer
{
    public Theme theme;
    public ComponentType type;
    public static Color tooltipColor;
    
    public void renderToolTip(final Component component, final String s, final Point point) {
        GL11.glPushMatrix();
        GL11.glDisable(3089);
        final int n = 8;
        final int stringWidth = this.theme.getFontRenderer().getStringWidth(s);
        GLUtils.glColor(ComponentRenderer.tooltipColor.brighter());
        this.drawRect((float)(point.x - 1), (float)(point.y - n - 1), (float)(point.x + stringWidth + 5), (float)(point.y + this.theme.getFontRenderer().FONT_HEIGHT - n + 3), 1.0f);
        GLUtils.glColor(ComponentRenderer.tooltipColor);
        this.drawFilledRect((float)point.x, (float)(point.y - n), (float)(point.x + stringWidth + 4), (float)(point.y + this.theme.getFontRenderer().FONT_HEIGHT - n + 2));
        this.theme.getFontRenderer().drawStringWithShadow(s, (float)(point.x + 2), (float)(point.y - n + 2), 16777215);
        GL11.glEnable(3089);
        GL11.glPopMatrix();
    }
    
    public abstract void drawComponent(final Component p0, final int p1, final int p2);
    
    public abstract void doInteractions(final Component p0, final int p1, final int p2);
    
    public void drawArrow(final int n, final int n2, final int n3, final boolean b, final int n4) {
        GLUtils.glColor(n4);
    }
    
    public void drawArrow(final int n, final int n2, final int n3, final boolean b) {
        this.drawArrow(n, n2, n3, b, -1);
    }
    
    public void drawRect(final float n, final float n2, final float n3, final float n4, final float n5) {
        this.drawFilledRect(n + n5, n2, n3 - n5, n2 + n5);
        this.drawFilledRect(n, n2, n + n5, n4);
        this.drawFilledRect(n3 - n5, n2, n3, n4);
        this.drawFilledRect(n + n5, n4 - n5, n3 - n5, n4);
    }
    
    static {
        ComponentRenderer.tooltipColor = new Color(0.0f, 0.5f, 1.0f, 0.75f);
    }
    
    public void drawPin(final int n, final int n2, final int n3, final boolean b, final int n4) {
        GLUtils.glColor(n4);
    }
    
    public void drawFilledRect(final float n, final float n2, final float n3, final float n4) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glBegin(7);
        GL11.glVertex3f(n, n4, 1.0f);
        GL11.glVertex3f(n3, n4, 1.0f);
        GL11.glVertex3f(n3, n2, 1.0f);
        GL11.glVertex3f(n, n2, 1.0f);
        GL11.glEnd();
        GL11.glEnable(3553);
    }
    
    public ComponentRenderer(final ComponentType type, final Theme theme) {
        this.type = type;
        this.theme = theme;
    }
    
    public void drawExpanded(final int n, final int n2, final int n3, final boolean b, final int n4) {
        GLUtils.glColor(n4);
    }
}
