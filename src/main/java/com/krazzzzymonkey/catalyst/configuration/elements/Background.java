/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.configuration.elements;

import java.util.Locale;
import com.krazzzzymonkey.catalyst.configuration.GuiConfig;
import com.krazzzzymonkey.catalyst.lib.MODE;
import com.krazzzzymonkey.catalyst.lib.textures.ITexture;

public class Background extends Element
{
    public Slideshow slideShow;
    public ITexture image;
    public static Background OPTIONS_BACKGROUND;
    public MODE mode;
    public boolean ichBinEineSlideshow;
    
    static {
        Background.OPTIONS_BACKGROUND = new Background(null, null);
    }
    
    public Background(final GuiConfig guiConfig, final ITexture image) {
        super(guiConfig);
        this.image = image;
        this.mode = MODE.FILL;
        this.ichBinEineSlideshow = false;
        this.slideShow = null;
    }
    
    public void setMode(final String s) {
        this.mode = MODE.valueOf(s.toUpperCase(Locale.US));
    }
}
