/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.configuration.elements;

import com.krazzzzymonkey.catalyst.configuration.GuiConfig;
import com.krazzzzymonkey.catalyst.lib.actions.ActionOpenLink;
import com.krazzzzymonkey.catalyst.lib.actions.IAction;
import com.krazzzzymonkey.catalyst.lib.ANCHOR;
import com.krazzzzymonkey.catalyst.lib.texts.IText;
import com.krazzzzymonkey.catalyst.configuration.Alignment;

public class Label extends Element
{
    public String pressSound;
    public int posY;
    public int color;
    public Alignment alignment;
    public int hoverColor;
    public String hoverSound;
    public int posX;
    public IText text;
    public ANCHOR anchor;
    public String name;
    public IAction action;
    public IText hoverText;
    public float fontSize;
    
    public void setLink(final String link) {
        this.action = new ActionOpenLink(link);
    }
    
    public Label(final GuiConfig guiConfig, final String name, final IText text, final int posX, final int posY, final Alignment alignment, final int n) {
        super(guiConfig);
        this.name = name;
        this.hoverText = text;
        this.text = text;
        this.posX = posX;
        this.posY = posY;
        this.hoverColor = n;
        this.color = n;
        this.alignment = alignment;
        if (this.alignment == null) {
            this.alignment = guiConfig.getAlignment("top_left");
        }
        this.fontSize = 1.0f;
        this.anchor = ANCHOR.START;
    }
    
    public void setHoverColor(final int hoverColor) {
        this.hoverColor = hoverColor;
    }
    
    public Label(final GuiConfig guiConfig, final String s, final IText text, final int n, final int n2) {
        this(guiConfig, s, text, n, n2, guiConfig.getAlignment("top_left"), -1);
    }
    
    public void setAlignment(final String s) {
        this.alignment = this.parent.getAlignment(s);
    }
    
    public void setColor(final int color) {
        this.color = color;
    }
}
