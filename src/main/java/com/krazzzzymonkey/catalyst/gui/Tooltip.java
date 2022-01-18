/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui;

import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.utils.font.CFontRenderer;

public class Tooltip
{
    public CFontRenderer fontRenderer;
    public int x;
    public int y;
    public String text;
    
    public String getText() {
        return this.text;
    }
    
    public int getX() {
        return this.x;
    }
    
    public Tooltip(final String text, final int x, final int y, final CFontRenderer fontRenderer) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontRenderer = fontRenderer;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void render() {
        final int n = -1;
        final int color = ColorUtils.color(0, 0, 0, 180);
        final int n2 = 8;
        this.fontRenderer.getStringWidth(this.text);
        RenderUtils.drawCustomStringWithRect(this.getText(), this.getX() + 2, this.getY() - n2 + 3, n, ClickGui.getColor(), color);
    }
}
