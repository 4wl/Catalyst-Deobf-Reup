/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.configuration.elements;

import com.krazzzzymonkey.catalyst.configuration.GuiConfig;
import com.krazzzzymonkey.catalyst.lib.textures.ITexture;

public class Panorama extends Element
{
    public boolean blur;
    public boolean gradient;
    public boolean synced;
    public String images;
    public ITexture[] locations;
    public int position;
    public boolean animate;
    public int animationSpeed;
    
    public void setAnimate(final boolean animate) {
        this.animate = animate;
    }
    
    public void setPosition(final int position) {
        this.position = position;
    }
    
    public void setAnimationSpeed(final int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }
    
    public Panorama(final GuiConfig guiConfig, final String images, final boolean blur, final boolean gradient) {
        super(guiConfig);
        this.images = images;
        this.blur = blur;
        this.gradient = gradient;
        this.animate = true;
        this.animationSpeed = 1;
        this.synced = false;
        this.locations = new ITexture[6];
        for (int i = 0; i < 6; ++i) {
            this.locations[i] = GuiConfig.getWantedTexture(images.replace("%c", i + ""));
        }
    }
}
