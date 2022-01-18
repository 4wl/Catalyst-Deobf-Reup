/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.configuration.elements;

import com.krazzzzymonkey.catalyst.configuration.GuiConfig;
import com.krazzzzymonkey.catalyst.configuration.Alignment;
import com.krazzzzymonkey.catalyst.lib.actions.IAction;
import com.krazzzzymonkey.catalyst.lib.texts.IText;
import com.krazzzzymonkey.catalyst.lib.textures.ITexture;

public class Button extends Element
{
    public int height;
    public ITexture texture;
    public String pressSound;
    public IText text;
    public boolean shadow;
    public int posY;
    public IText hoverText;
    public int posX;
    public int normalTextColor;
    public IAction action;
    public int textOffsetX;
    public int imageWidth;
    public int width;
    public String name;
    public int textOffsetY;
    public int hoverTextColor;
    public int imageHeight;
    public IText tooltip;
    public String hoverSound;
    public Alignment alignment;
    public int wrappedButtonID;
    
    public Button setTexture(final ITexture texture) {
        this.texture = texture;
        return this;
    }
    
    public Button(final GuiConfig guiConfig, final IText text, final int n, final int n2, final int n3, final int n4) {
        this(guiConfig, text, n, n2, n3, n4, guiConfig.getAlignment("button"));
    }
    
    public void setHoverSound(final String hoverSound) {
        this.hoverSound = hoverSound;
    }
    
    public void setWrappedButton(final int wrappedButtonID) {
        this.wrappedButtonID = wrappedButtonID;
    }
    
    public void setPressSound(final String pressSound) {
        this.pressSound = pressSound;
    }
    
    public void setStringAlignment(final String s) {
        this.alignment = this.parent.getAlignment(s);
    }
    
    public Button(final GuiConfig guiConfig, final IText text, final int posX, final int posY, final int n, final int n2, final Alignment alignment) {
        super(guiConfig);
        this.text = text;
        this.posX = posX;
        this.posY = posY;
        this.imageWidth = n;
        this.width = n;
        this.imageHeight = n2;
        this.height = n2;
        this.alignment = alignment;
        this.texture = null;
        this.normalTextColor = 14737632;
        this.hoverTextColor = 16777120;
        this.shadow = true;
        this.wrappedButtonID = -1;
        this.action = null;
        this.tooltip = null;
        this.hoverText = text;
        this.textOffsetX = 0;
        this.textOffsetY = 0;
        if (this.alignment == null) {
            this.alignment = guiConfig.getAlignment("button");
        }
    }
    
    public void setShadow(final boolean shadow) {
        this.shadow = shadow;
    }
}
