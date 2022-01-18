/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.handler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.krazzzzymonkey.catalyst.lib.textures.TextureURL;

public class LoadTextureURL extends Thread
{
    public TextureURL texture;
    
    public LoadTextureURL(final TextureURL texture) {
        this.texture = texture;
        this.setDaemon(true);
    }
    
    @Override
    public void run() {
        BufferedImage read = null;
        try {
            read = ImageIO.read(this.texture.getURL());
        }
        catch (IOException ex) {}
        if (read != null) {
            this.texture.finishLoading(read);
        }
    }
}
