/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.configuration.elements;

import com.krazzzzymonkey.catalyst.configuration.GuiConfig;
import com.krazzzzymonkey.catalyst.configuration.Alignment;
import com.krazzzzymonkey.catalyst.lib.textures.ITexture;

public class Image extends Element
{
    public ITexture hoverImage;
    public int posX;
    public ITexture image;
    public int posY;
    public int width;
    public Slideshow slideShow;
    public int height;
    public Alignment alignment;
    public boolean ichBinEineSlideshow;
    
    public Image(final GuiConfig guiConfig, final int posX, final int posY, final int width, final int height, final Alignment alignment) {
        super(guiConfig);
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.alignment = alignment;
    }
}
