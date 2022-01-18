/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.theme.dark;

import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.io.IOException;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import java.awt.image.BufferedImage;
import net.minecraft.util.ResourceLocation;
import com.krazzzzymonkey.catalyst.gui.click.elements.ColorPicker;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;

public class DarkColorPicker extends ComponentRenderer
{
    public static ColorPicker colorPicker;
    public ResourceLocation resourceLocation;
    public static BufferedImage image;
    public boolean clicking;
    
    @Override
    public void drawComponent(final Component component, final int n, final int n2) {
        DarkColorPicker.colorPicker = (ColorPicker)component;
        RenderUtils.drawBorderedRect(DarkColorPicker.colorPicker.getX() + 1, DarkColorPicker.colorPicker.getY() - 1, DarkColorPicker.colorPicker.getX() + DarkColorPicker.colorPicker.getDimension().width - 2, DarkColorPicker.colorPicker.getY() + 15, 2.0f, ColorUtils.color(0.0f, 0.0f, 0.0f, 1.0f), ColorUtils.color(20, 20, 20, 255));
        RenderUtils.drawBorderedRect(DarkColorPicker.colorPicker.getX() + DarkColorPicker.colorPicker.getDimension().width - 18, DarkColorPicker.colorPicker.getY() + 1, DarkColorPicker.colorPicker.getX() + DarkColorPicker.colorPicker.getDimension().width - 8, DarkColorPicker.colorPicker.getY() + 11, 1.0f, ColorUtils.color(0.0f, 0.0f, 0.0f, 1.0f), DarkColorPicker.colorPicker.getColor().getRGB());
        Main.smallFontRenderer.drawString(DarkColorPicker.colorPicker.getText(), (float)(DarkColorPicker.colorPicker.getX() + 5), (float)(DarkColorPicker.colorPicker.getY() + 3), -1);
        GlStateManager.enableAlpha();
        Minecraft.getMinecraft().renderEngine.bindTexture(this.resourceLocation);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        final double n3 = 0.4;
        GlStateManager.scale(n3, n3, n3);
        Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect((int)((DarkColorPicker.colorPicker.getX() + DarkColorPicker.colorPicker.getDimension().width - 51) * 2.5 - 121.0), (int)((DarkColorPicker.colorPicker.getY() + DarkColorPicker.colorPicker.getDimension().getHeight() - 51.0) * 2.5), 5, 0, 244, 127);
        GlStateManager.disableAlpha();
        GlStateManager.scale(2.5, 2.5, 2.5);
        GlStateManager.clear(256);
        Label_1031: {
            IOException ex;
            try {
                DarkColorPicker.image = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(this.resourceLocation).getInputStream());
                try {
                    if (MouseUtils.isMouseOver(DarkColorPicker.colorPicker.getX(), DarkColorPicker.colorPicker.getX() + DarkColorPicker.colorPicker.getDimension().width, DarkColorPicker.colorPicker.getY() + 14, DarkColorPicker.colorPicker.getY() + DarkColorPicker.colorPicker.getDimension().height) && MouseUtils.isLeftClicked() && !DarkColorPicker.colorPicker.isRainbow) {
                        this.clicking = true;
                        DarkColorPicker.colorPicker.setColor(new Color(this.resizeImage(DarkColorPicker.image, 100, 100).getRGB(MouseUtils.getMouseX() - DarkColorPicker.colorPicker.getX(), MouseUtils.getMouseY() - DarkColorPicker.colorPicker.getY() - 14)));
                    }
                }
                catch (Exception ex3) {}
                break Label_1031;
            }
            catch (IOException ex2) {
                ex = ex2;
            }
            ex.printStackTrace();
        }
        if (!MouseUtils.isLeftClicked()) {
            this.clicking = false;
        }
    }
    
    @Override
    public void doInteractions(final Component component, final int n, final int n2) {
    }
    
    public BufferedImage resizeImage(final BufferedImage bufferedImage, final int width, final int height) {
        final BufferedImage bufferedImage2 = new BufferedImage(width, height, 1);
        final Graphics2D graphics = bufferedImage2.createGraphics();
        graphics.drawImage(bufferedImage, 0, 0, width, height, null);
        graphics.dispose();
        return bufferedImage2;
    }
    
    public DarkColorPicker(final Theme theme) {
        super(ComponentType.COLOR_PICKER, theme);
        this.clicking = false;
        this.resourceLocation = new ResourceLocation("catalyst/gui/colorPicker.png");
    }
}
