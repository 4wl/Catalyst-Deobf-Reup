/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils.font;

import java.awt.geom.Rectangle2D;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.lwjgl.opengl.GL11;
import java.awt.Font;
import net.minecraft.client.renderer.texture.DynamicTexture;

public class CFont
{
    public int fontHeight;
    public CFont$CharData[] charData;
    public DynamicTexture tex;
    public boolean fractionalMetrics;
    public float imgSize;
    public Font font;
    public int charOffset;
    public boolean antiAlias;
    
    public boolean isAntiAlias() {
        return this.antiAlias;
    }
    
    public void drawChar(final CFont$CharData[] array, final char c, final float n, final float n2) {
        Exception ex;
        try {
            this.drawQuad(n, n2, (float)array[c].width, (float)array[c].height, (float)array[c].storedX, (float)array[c].storedY, (float)array[c].width, (float)array[c].height);
            return;
        }
        catch (Exception ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public void drawQuad(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        final float n9 = n5 / this.imgSize;
        final float n10 = n6 / this.imgSize;
        final float n11 = n7 / this.imgSize;
        final float n12 = n8 / this.imgSize;
        GL11.glTexCoord2f(n9 + n11, n10);
        GL11.glVertex2d((double)(n + n3), (double)n2);
        GL11.glTexCoord2f(n9, n10);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glTexCoord2f(n9, n10 + n12);
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glTexCoord2f(n9, n10 + n12);
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glTexCoord2f(n9 + n11, n10 + n12);
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4));
        GL11.glTexCoord2f(n9 + n11, n10);
        GL11.glVertex2d((double)(n + n3), (double)n2);
    }
    
    public void setFont(final Font font) {
        this.font = font;
        this.tex = this.setupTexture(font, this.antiAlias, this.fractionalMetrics, this.charData);
    }
    
    public DynamicTexture setupTexture(final Font font, final boolean b, final boolean b2, final CFont$CharData[] array) {
        final BufferedImage generateFontImage = this.generateFontImage(font, b, b2, array);
        try {
            return new DynamicTexture(generateFontImage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void setAntiAlias(final boolean antiAlias) {
        if (this.antiAlias != antiAlias) {
            this.antiAlias = antiAlias;
            this.tex = this.setupTexture(this.font, antiAlias, this.fractionalMetrics, this.charData);
        }
    }
    
    public int getStringWidth(final String s) {
        int n = 0;
        for (final char c : s.toCharArray()) {
            if (c < this.charData.length) {
                if (c >= '\0') {
                    n += this.charData[c].width - 8 + this.charOffset;
                }
            }
        }
        return n / 2;
    }
    
    public boolean isFractionalMetrics() {
        return this.fractionalMetrics;
    }
    
    public int getHeight() {
        return (this.fontHeight - 8) / 2;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public CFont(final Font font, final boolean antiAlias, final boolean fractionalMetrics) {
        this.imgSize = 512.0f;
        this.charData = new CFont$CharData[256];
        this.fontHeight = -1;
        this.charOffset = 0;
        this.font = font;
        this.antiAlias = antiAlias;
        this.fractionalMetrics = fractionalMetrics;
        this.tex = this.setupTexture(font, antiAlias, fractionalMetrics, this.charData);
    }
    
    public void setFractionalMetrics(final boolean fractionalMetrics) {
        if (this.fractionalMetrics != fractionalMetrics) {
            this.fractionalMetrics = fractionalMetrics;
            this.tex = this.setupTexture(this.font, this.antiAlias, fractionalMetrics, this.charData);
        }
    }
    
    public BufferedImage generateFontImage(final Font font, final boolean b, final boolean b2, final CFont$CharData[] array) {
        final int n = (int)this.imgSize;
        final BufferedImage bufferedImage = new BufferedImage(n, n, 2);
        final Graphics2D context = (Graphics2D)bufferedImage.getGraphics();
        context.setFont(font);
        context.setColor(new Color(255, 255, 255, 0));
        context.fillRect(0, 0, n, n);
        context.setColor(Color.WHITE);
        context.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, b2 ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        context.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, b ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        context.setRenderingHint(RenderingHints.KEY_ANTIALIASING, b ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        final FontMetrics fontMetrics = context.getFontMetrics();
        int height = 0;
        int storedX = 0;
        int storedY = 1;
        for (int i = 0; i < array.length; ++i) {
            final char c = (char)i;
            final CFont$CharData cFont$CharData = new CFont$CharData(this);
            final Rectangle2D stringBounds = fontMetrics.getStringBounds(String.valueOf(c), context);
            cFont$CharData.width = stringBounds.getBounds().width + 8;
            cFont$CharData.height = stringBounds.getBounds().height;
            if (storedX + cFont$CharData.width >= n) {
                storedX = 0;
                storedY += height;
                height = 0;
            }
            if (cFont$CharData.height > height) {
                height = cFont$CharData.height;
            }
            cFont$CharData.storedX = storedX;
            cFont$CharData.storedY = storedY;
            if (cFont$CharData.height > this.fontHeight) {
                this.fontHeight = cFont$CharData.height;
            }
            array[i] = cFont$CharData;
            context.drawString(String.valueOf(c), storedX + 2, storedY + fontMetrics.getAscent());
            storedX += cFont$CharData.width;
        }
        return bufferedImage;
    }
    
    public int getStringHeight(final String s) {
        return this.getHeight();
    }
}
