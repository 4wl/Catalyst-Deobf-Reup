/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.textures;

import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import com.krazzzzymonkey.catalyst.handler.LoadTextureURL;
import java.net.MalformedURLException;
import org.apache.logging.log4j.Level;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.lib.StringReplacer;
import java.awt.image.BufferedImage;
import java.net.URL;

public class TextureURL implements ITexture
{
    URL url;
    int textureID;
    private BufferedImage bi;
    
    public TextureURL(final String url) {
        this.textureID = -1;
        try {
            this.url = new URL(StringReplacer.replacePlaceholders(url));
        }
        catch (MalformedURLException e) {
            Main.logger.log(Level.ERROR, "Invalid URL: " + url);
            e.printStackTrace();
        }
        new LoadTextureURL(this).start();
    }
    
    @Override
    public void bind() {
        if (this.textureID != -1) {
            GlStateManager.bindTexture(this.textureID);
        }
        else {
            if (this.bi != null) {
                this.setTextureID(TextureUtil.uploadTextureImageAllocate(GL11.glGenTextures(), this.bi, false, false));
                this.bind();
                return;
            }
            Main.bindTransparent();
        }
    }
    
    public void finishLoading(final BufferedImage bi) {
        this.bi = bi;
    }
    
    public URL getURL() {
        return this.url;
    }
    
    public void setTextureID(final int textureID) {
        this.textureID = textureID;
    }
}
