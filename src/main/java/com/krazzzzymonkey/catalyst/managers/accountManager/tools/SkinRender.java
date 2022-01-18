/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers.accountManager.tools;

import net.minecraft.client.gui.Gui;
import com.krazzzzymonkey.catalyst.Main;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.texture.DynamicTexture;
import java.io.File;

public class SkinRender
{
    private final File file;
    private DynamicTexture previewTexture;
    private ResourceLocation resourceLocation;
    private final TextureManager textureManager;
    
    public SkinRender(final TextureManager textureManager, final File file) {
        this.textureManager = textureManager;
        this.file = file;
    }
    
    private boolean loadPreview() {
        try {
            final BufferedImage image = ImageIO.read(this.file);
            this.previewTexture = new DynamicTexture(image);
            this.resourceLocation = this.textureManager.getDynamicTextureLocation("catalyst", this.previewTexture);
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void drawImage(final int xPos, final int yPos, final int width, final int height) {
        if (this.previewTexture == null) {
            final boolean successful = this.loadPreview();
            if (!successful) {
                Main.logger.error("Failure to load preview.");
                return;
            }
        }
        this.previewTexture.updateDynamicTexture();
        this.textureManager.bindTexture(this.resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture(xPos, yPos, 0.0f, 0.0f, width, height, 64.0f, 128.0f);
    }
}
