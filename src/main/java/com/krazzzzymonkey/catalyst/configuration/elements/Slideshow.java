/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.configuration.elements;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import com.krazzzzymonkey.catalyst.configuration.GuiConfig;
import com.krazzzzymonkey.catalyst.lib.textures.ITexture;

public class Slideshow extends Element
{
    public boolean fading;
    public ITexture[] ressources;
    public int counter;
    public int displayDuration;
    public int fadeDuration;
    
    public Slideshow(final GuiConfig guiConfig, final String[] array) {
        super(guiConfig);
        this.fading = false;
        this.ressources = new ITexture[array.length];
        this.displayDuration = 60;
        this.counter = 0;
        this.fadeDuration = 20;
        for (int i = 0; i < array.length; ++i) {
            this.ressources[i] = GuiConfig.getWantedTexture(array[i]);
        }
    }
    
    public ITexture getCurrentResource1() {
        return this.ressources[this.counter / (this.displayDuration + this.fadeDuration) % this.ressources.length];
    }
    
    public void shuffle() {
        final List<ITexture> list = Arrays.asList(this.ressources);
        Collections.shuffle(list);
        this.ressources = (ITexture[])list.toArray();
    }
    
    public float getAlphaFade(final float n) {
        return 1.0f / this.fadeDuration * ((this.counter + n) % (this.displayDuration + this.fadeDuration) - this.displayDuration);
    }
    
    public ITexture getCurrentResource2() {
        if (this.fading) {
            return this.ressources[(this.counter + this.fadeDuration) / (this.displayDuration + this.fadeDuration) % this.ressources.length];
        }
        return null;
    }
    
    public void update() {
        ++this.counter;
        this.fading = (this.counter % (this.displayDuration + this.fadeDuration) > this.displayDuration);
    }
    
    public boolean fading() {
        return this.fading;
    }
}
