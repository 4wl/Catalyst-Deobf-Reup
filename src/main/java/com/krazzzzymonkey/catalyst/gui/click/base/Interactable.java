/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.base;

import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import java.awt.Dimension;
import java.awt.Color;

public class Interactable
{
    public int yBase;
    public Color color;
    public Dimension dimension;
    public double colorSpeed;
    public int xPos;
    public float colorOffset;
    public int yPos;
    
    public void onMouseRelease(final int n, final int n2, final int n3) {
    }
    
    public boolean isMouseOver(final int n, final int n2) {
        return n >= this.xPos && n2 >= this.yPos && n <= this.xPos + this.dimension.width && n2 <= this.yPos + this.dimension.height;
    }
    
    public void onMouseDrag(final int n, final int n2) {
    }
    
    public void setyPos(final int yPos) {
        this.yPos = yPos;
    }
    
    public Color getButtonColor() {
        if (this.color != null) {
            return this.color;
        }
        return ColorUtils.rainbow(this.colorOffset, this.colorSpeed);
    }
    
    public void onMouseScroll(final int n) {
    }
    
    public void setyBase(final int yBase) {
        this.yBase = yBase;
    }
    
    public int getY() {
        return this.yPos;
    }
    
    public void onMousePress(final int n, final int n2, final int n3) {
    }
    
    public void onKeyPressed(final int n, final char c) {
    }
    
    public void setButtonColor(final float colorOffset, final double colorSpeed) {
        this.colorOffset = colorOffset;
        this.colorSpeed = colorSpeed;
        this.color = ColorUtils.rainbow(colorOffset, this.colorSpeed);
    }
    
    public void setDimension(final Dimension dimension) {
        this.dimension = dimension;
    }
    
    public int getyBase() {
        return this.yBase;
    }
    
    public void setxPos(final int xPos) {
        this.xPos = xPos;
    }
    
    public void onKeyReleased(final int n, final char c) {
    }
    
    public Interactable(final int xPos, final int yPos, final int width, final int height) {
        this.colorOffset = 0.01f;
        this.colorSpeed = 20.0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.dimension = new Dimension(width, height);
    }
    
    public Dimension getDimension() {
        return this.dimension;
    }
    
    public int getX() {
        return this.xPos;
    }
}
