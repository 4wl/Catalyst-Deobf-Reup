/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.configuration.elements;

import com.krazzzzymonkey.catalyst.lib.texts.TextResourceLocation;
import com.krazzzzymonkey.catalyst.configuration.GuiConfig;
import com.krazzzzymonkey.catalyst.configuration.Alignment;
import com.krazzzzymonkey.catalyst.lib.texts.IText;

public class SplashText extends Element
{
    public int color;
    public IText texts;
    public int posX;
    public boolean synced;
    public Alignment alignment;
    public int posY;
    
    public SplashText(final GuiConfig guiConfig, final int posX, final int posY, final int color, final Alignment alignment) {
        super(guiConfig);
        this.posX = posX;
        this.posY = posY;
        this.alignment = alignment;
        this.color = color;
        this.texts = new TextResourceLocation("texts/splashes.txt");
        this.synced = false;
        if (this.alignment == null) {
            this.alignment = guiConfig.getAlignment("button");
        }
    }
    
    public void setSplashTexts(final IText texts) {
        this.texts = texts;
    }
    
    public SplashText(final GuiConfig guiConfig, final int n, final int n2, final String s) {
        this(guiConfig, n, n2, -256, guiConfig.getAlignment(s));
    }
    
    public SplashText(final GuiConfig guiConfig, final int n, final int n2, final int n3, final String s) {
        this(guiConfig, n, n2, n3, guiConfig.getAlignment(s));
    }
}
