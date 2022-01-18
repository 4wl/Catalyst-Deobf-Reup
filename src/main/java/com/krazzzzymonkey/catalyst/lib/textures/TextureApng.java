/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.textures;

import com.krazzzzymonkey.catalyst.ellerton.japng.argb8888.Argb8888Bitmap;
import java.awt.Graphics2D;
import com.krazzzzymonkey.catalyst.ellerton.japng.argb8888.Argb8888BitmapSequence;
import java.io.InputStream;
import org.lwjgl.opengl.GL11;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Collections;
import com.krazzzzymonkey.catalyst.ellerton.japng.Png;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import com.krazzzzymonkey.catalyst.ellerton.japng.chunks.PngAnimationControl;
import java.util.List;
import com.krazzzzymonkey.catalyst.ellerton.japng.argb8888.Argb8888BitmapSequence$Frame;
import java.util.HashMap;
import net.minecraft.util.ResourceLocation;

public class TextureApng implements ITexture
{
    ResourceLocation rl;
    HashMap<Argb8888BitmapSequence$Frame, Integer> frameTextureID;
    List<Argb8888BitmapSequence$Frame> frames;
    PngAnimationControl animationControl;
    int currentFrame;
    boolean loaded;
    boolean errored;
    long lastTimeStamp;
    int currentFrameDelay;
    
    public TextureApng(final String textureString) {
        this.rl = new ResourceLocation(textureString);
        this.errored = false;
    }
    
    @Override
    public void bind() {
        if (!this.loaded) {
            this.load();
            this.loaded = true;
        }
        if (this.errored) {
            GlStateManager.bindTexture(TextureUtil.MISSING_TEXTURE.getGlTextureId());
            return;
        }
        while (System.currentTimeMillis() - this.lastTimeStamp >= this.currentFrameDelay) {
            ++this.currentFrame;
            if (this.currentFrame > this.animationControl.numFrames - 1) {
                this.currentFrame = 0;
            }
            final Argb8888BitmapSequence$Frame f = this.frames.get(this.currentFrame);
            final float numerator = f.control.delayNumerator;
            final float denominator = (f.control.delayDenominator > 0) ? f.control.delayDenominator : 100.0f;
            this.lastTimeStamp += this.currentFrameDelay;
            this.currentFrameDelay = (int)(numerator / denominator * 1000.0f);
        }
        GlStateManager.bindTexture((int)this.frameTextureID.get(this.frames.get(this.currentFrame)));
    }
    
    private void load() {
        this.frameTextureID = new HashMap<Argb8888BitmapSequence$Frame, Integer>();
        try {
            final InputStream inputStream = Minecraft.getMinecraft().getResourceManager().getResource(this.rl).getInputStream();
            final Argb8888BitmapSequence pngContainer = Png.readArgb8888BitmapSequence(inputStream);
            this.animationControl = pngContainer.getAnimationControl();
            this.frames = Collections.synchronizedList((List<Argb8888BitmapSequence$Frame>)pngContainer.getAnimationFrames());
            final BufferedImage canvas = new BufferedImage(pngContainer.defaultImage.width, pngContainer.defaultImage.height, 2);
            final Graphics2D graphics = canvas.createGraphics();
            graphics.setBackground(new Color(0, 0, 0, 0));
            final BufferedImage frameBackup = new BufferedImage(pngContainer.defaultImage.width, pngContainer.defaultImage.height, 2);
            final Graphics2D graphicsBackup = frameBackup.createGraphics();
            graphicsBackup.setBackground(new Color(0, 0, 0, 0));
            for (int i = 0; i < this.frames.size(); ++i) {
                final Argb8888BitmapSequence$Frame f = this.frames.get(i);
                switch (f.control.blendOp) {
                    case 0: {
                        graphics.setComposite(AlphaComposite.getInstance(2));
                        break;
                    }
                    case 1: {
                        graphics.setComposite(AlphaComposite.getInstance(3));
                        break;
                    }
                }
                final Argb8888Bitmap bitmap = f.bitmap;
                final BufferedImage buffered = new BufferedImage(bitmap.width, bitmap.height, 2);
                buffered.setRGB(0, 0, bitmap.width, bitmap.height, bitmap.getPixelArray(), 0, bitmap.width);
                graphicsBackup.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                graphicsBackup.drawImage(canvas, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
                graphics.drawImage(buffered, f.control.xOffset, f.control.yOffset, f.control.xOffset + f.control.width, f.control.yOffset + f.control.height, 0, 0, buffered.getWidth(), buffered.getHeight(), null);
                this.frameTextureID.put(f, TextureUtil.uploadTextureImageAllocate(GL11.glGenTextures(), canvas, false, false));
                switch (f.control.disposeOp) {
                    case 1: {
                        graphics.clearRect(f.control.xOffset, f.control.yOffset, f.control.width, f.control.height);
                        break;
                    }
                    case 2: {
                        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        graphics.drawImage(frameBackup, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
                        break;
                    }
                }
            }
            graphics.dispose();
            graphicsBackup.dispose();
        }
        catch (Exception e) {
            e.printStackTrace();
            this.errored = true;
            return;
        }
        final Argb8888BitmapSequence$Frame frame = this.frames.get(0);
        final float numerator = frame.control.delayNumerator;
        final float denominator = (frame.control.delayDenominator > 0) ? frame.control.delayDenominator : 100.0f;
        this.currentFrameDelay = (int)(numerator / denominator * 1000.0f);
        this.lastTimeStamp = System.currentTimeMillis();
    }
}
