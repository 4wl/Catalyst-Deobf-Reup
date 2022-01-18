/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui;

import java.util.Date;
import java.util.Calendar;
import org.apache.logging.log4j.Logger;
import java.net.URI;
import com.krazzzzymonkey.catalyst.configuration.elements.Label;
import com.krazzzzymonkey.catalyst.lib.actions.ActionOpenLink;
import org.lwjgl.util.glu.Project;
import com.krazzzzymonkey.catalyst.lib.MODE;
import com.krazzzzymonkey.catalyst.configuration.elements.SplashText;
import com.krazzzzymonkey.catalyst.configuration.elements.Panorama;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.utils.RenderUtil;
import com.krazzzzymonkey.catalyst.configuration.elements.Background;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager;
import com.krazzzzymonkey.catalyst.configuration.Alignment;
import java.util.Iterator;
import net.minecraft.client.gui.GuiMainMenu;
import com.krazzzzymonkey.catalyst.Main;
import org.lwjgl.input.Keyboard;
import com.krazzzzymonkey.catalyst.configuration.elements.Image;
import net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Post;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Pre;
import net.minecraft.client.gui.GuiButton;
import com.krazzzzymonkey.catalyst.configuration.elements.Button;
import java.util.List;
import com.krazzzzymonkey.catalyst.configuration.GuiConfig;
import com.krazzzzymonkey.catalyst.lib.textures.ITexture;
import java.util.Random;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import com.krazzzzymonkey.catalyst.configuration.Config;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.GuiScreen;

@SideOnly(Side.CLIENT)
public class GuiCustom extends GuiScreen implements GuiYesNoCallback
{
    public int openGLWarningY1;
    public static Config config;
    public ResourceLocation backgroundTexture;
    public String splashText;
    public DynamicTexture viewportTexture;
    public ArrayList<GuiCustomLabel> textLabels;
    public Random rand;
    public int openGLWarningX1;
    public boolean loadedSplashText;
    public float panoramaTimer;
    public ITexture[] titlePanoramaPaths;
    public int buttonCounter;
    public Object beingChecked;
    public int openGLWarning1Width;
    public int openGLWarning2Width;
    public int openGLWarningX2;
    public int openGLWarningY2;
    public GuiConfig guiConfig;
    
    public List getButtonList() {
        return this.buttonList;
    }
    
    public GuiCustomButton alignButton(final Button button, final GuiCustomButton guiCustomButton) {
        if (button != null) {
            guiCustomButton.x = this.modX(button.posX, button.alignment);
            guiCustomButton.y = this.modY(button.posY, button.alignment);
        }
        return guiCustomButton;
    }
    
    public void actionPerformed(final GuiButton guiButton) {
        if (guiButton instanceof GuiCustomWrappedButton && this.guiConfig.name.equals("mainmenu")) {
            final GuiCustomWrappedButton guiCustomWrappedButton = (GuiCustomWrappedButton)guiButton;
            if (guiCustomWrappedButton.wrappedButton != null) {
                final GuiScreenEvent$ActionPerformedEvent$Pre guiScreenEvent$ActionPerformedEvent$Pre = new GuiScreenEvent$ActionPerformedEvent$Pre((GuiScreen)new GuiFakeMain(), guiCustomWrappedButton.wrappedButton, (List)new ArrayList());
                if (MinecraftForge.EVENT_BUS.post((Event)guiScreenEvent$ActionPerformedEvent$Pre)) {
                    return;
                }
                guiScreenEvent$ActionPerformedEvent$Pre.getButton().playPressSound(this.mc.getSoundHandler());
                if (this.equals(this.mc.currentScreen)) {
                    MinecraftForge.EVENT_BUS.post((Event)new GuiScreenEvent$ActionPerformedEvent$Post((GuiScreen)new GuiFakeMain(), guiCustomWrappedButton.wrappedButton, (List)new ArrayList()));
                }
            }
        }
        else if (guiButton.id >= 6000 && guiButton instanceof GuiCustomButton) {
            final GuiCustomButton guiCustomButton = (GuiCustomButton)guiButton;
            if (guiCustomButton.b.action != null) {
                guiCustomButton.b.action.perform(guiCustomButton.b, this);
            }
        }
    }
    
    public void updateScreen() {
        for (final Image image : this.guiConfig.customImages) {
            if (image.ichBinEineSlideshow) {
                image.slideShow.update();
            }
        }
        if (this.guiConfig.background != null && this.guiConfig.background.ichBinEineSlideshow) {
            this.guiConfig.background.slideShow.update();
        }
        if (Keyboard.isKeyDown(29)) {
            if (Keyboard.isKeyDown(19)) {
                Main.INSTANCE.reload();
                if (Keyboard.isKeyDown(42)) {
                    this.mc.refreshResources();
                }
                this.mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
            }
        }
    }
    
    public int modY(final int n, final Alignment alignment) {
        return (int)(n + this.height * alignment.factorY);
    }
    
    public void tick() {
        this.guiConfig.tick();
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (n3 == 0) {
            final Iterator<GuiCustomLabel> iterator = this.textLabels.iterator();
            while (iterator.hasNext()) {
                iterator.next().mouseClicked(n, n2, n3);
            }
        }
    }
    
    public void renderSkybox(final int n, final int n2, final float n3) {
        this.mc.getFramebuffer().unbindFramebuffer();
        GlStateManager.viewport(0, 0, 256, 256);
        this.drawPanorama(n, n2, n3);
        this.rotateAndBlurSkybox(n3);
        this.rotateAndBlurSkybox(n3);
        this.rotateAndBlurSkybox(n3);
        this.rotateAndBlurSkybox(n3);
        this.rotateAndBlurSkybox(n3);
        this.rotateAndBlurSkybox(n3);
        this.rotateAndBlurSkybox(n3);
        this.mc.getFramebuffer().bindFramebuffer(true);
        GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        final float n4 = (this.width > this.height) ? (120.0f / this.width) : (120.0f / this.height);
        final float n5 = this.height * n4 / 256.0f;
        final float n6 = this.width * n4 / 256.0f;
        final int width = this.width;
        final int height = this.height;
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(0.0, (double)height, (double)this.zLevel).tex((double)(0.5f - n5), (double)(0.5f + n6)).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        buffer.pos((double)width, (double)height, (double)this.zLevel).tex((double)(0.5f - n5), (double)(0.5f - n6)).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        buffer.pos((double)width, 0.0, (double)this.zLevel).tex((double)(0.5f + n5), (double)(0.5f - n6)).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        buffer.pos(0.0, 0.0, (double)this.zLevel).tex((double)(0.5f + n5), (double)(0.5f + n6)).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        instance.draw();
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.blendFunc(770, 771);
        final Panorama panorama = this.guiConfig.panorama;
        Tessellator.getInstance().getBuffer();
        if (panorama != null) {
            if (this.guiConfig.panorama != null) {
                if (this.guiConfig.panorama.synced) {
                    final GuiCustom gui = Main.config.getGUI("mainmenu");
                    this.panoramaTimer = gui.panoramaTimer;
                    if (gui.guiConfig.panorama.animate) {
                        this.panoramaTimer += gui.guiConfig.panorama.animationSpeed * n3;
                    }
                    else {
                        this.panoramaTimer = (float)gui.guiConfig.panorama.position;
                    }
                    gui.panoramaTimer = this.panoramaTimer;
                }
                else if (this.guiConfig.panorama.animate) {
                    this.panoramaTimer += this.guiConfig.panorama.animationSpeed * n3;
                }
                else {
                    this.panoramaTimer = (float)this.guiConfig.panorama.position;
                }
            }
            this.titlePanoramaPaths = panorama.locations;
            GlStateManager.disableAlpha();
            this.renderSkybox(n, n2, n3);
            GlStateManager.enableAlpha();
            if (panorama.gradient) {
                this.drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
                this.drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
            }
        }
        else {
            GL11.glBegin(7);
            GL11.glColor3f(0.0f, 0.0f, 0.0f);
            GL11.glVertex3f(0.0f, 0.0f, 0.0f);
            GL11.glColor3f(0.0f, 0.0f, 0.0f);
            GL11.glVertex3f(0.0f, (float)this.height, 0.0f);
            GL11.glColor3f(0.0f, 0.0f, 0.0f);
            GL11.glVertex3f((float)this.width, (float)this.height, 0.0f);
            GL11.glColor3f(0.0f, 0.0f, 0.0f);
            GL11.glVertex3f((float)this.width, 0.0f, 0.0f);
            GL11.glEnd();
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        final Background background = this.guiConfig.background;
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        if (background != null) {
            if (background == Background.OPTIONS_BACKGROUND) {
                this.drawDefaultBackground();
            }
            else if (!background.ichBinEineSlideshow) {
                background.image.bind();
                this.drawBackground(background.mode);
            }
            else {
                background.slideShow.getCurrentResource1().bind();
                this.drawBackground(background.mode);
                if (background.slideShow.fading()) {
                    GlStateManager.enableBlend();
                    background.slideShow.getCurrentResource2().bind();
                    GlStateManager.color(1.0f, 1.0f, 1.0f, background.slideShow.getAlphaFade(n3));
                    this.drawBackground(background.mode);
                    GlStateManager.disableBlend();
                    GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                }
            }
        }
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        for (final Image image : this.guiConfig.customImages) {
            final int modX = this.modX(image.posX, image.alignment);
            final int modY = this.modY(image.posY, image.alignment);
            if (image.ichBinEineSlideshow) {
                image.slideShow.getCurrentResource1().bind();
                RenderUtil.drawCompleteImage(modX, modY, image.width, image.height);
                if (!image.slideShow.fading()) {
                    continue;
                }
                GlStateManager.enableBlend();
                image.slideShow.getCurrentResource2().bind();
                GlStateManager.color(1.0f, 1.0f, 1.0f, image.slideShow.getAlphaFade(n3));
                RenderUtil.drawCompleteImage(modX, modY, image.width, image.height);
                GlStateManager.disableBlend();
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            }
            else {
                if (image.hoverImage != null && n >= modX && n <= modX + image.width && n2 >= modY && n2 <= modY + image.height) {
                    image.hoverImage.bind();
                }
                else {
                    image.image.bind();
                }
                RenderUtil.drawCompleteImage(modX, modY, image.width, image.height);
            }
        }
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        final SplashText splashText = this.guiConfig.splashText;
        if (splashText != null) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)this.modX(splashText.posX, splashText.alignment), (float)this.modY(splashText.posY, splashText.alignment), 0.0f);
            GlStateManager.rotate(-20.0f, 0.0f, 0.0f, 1.0f);
            final float n4 = (1.8f - MathHelper.abs(MathHelper.sin(Minecraft.getSystemTime() % 1000L / 1000.0f * 3.1415927f * 2.0f) * 0.1f)) * 100.0f / (this.fontRenderer.getStringWidth(this.splashText) + 32);
            GlStateManager.scale(n4, n4, n4);
            this.drawCenteredString(this.fontRenderer, this.splashText, 0, -8, splashText.color);
            GlStateManager.popMatrix();
        }
        final Iterator<GuiCustomLabel> iterator2 = this.textLabels.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().drawLabel(this.mc, n, n2);
        }
        for (int i = 0; i < this.buttonList.size(); ++i) {
            ((GuiButton)this.buttonList.get(i)).drawButton(this.mc, n, n2, n3);
        }
        for (final GuiCustomButton next : this.buttonList) {
            if (next instanceof GuiCustomButton) {
                next.drawTooltip(this.mc, n, n2);
            }
        }
        for (int j = 0; j < this.labelList.size(); ++j) {
            ((GuiLabel)this.labelList.get(j)).drawLabel(this.mc, n, n2);
        }
    }
    
    public int modX(final int n, final Alignment alignment) {
        return (int)(n + this.width * alignment.factorX);
    }
    
    public void drawBackground(final MODE mode) {
        this.drawImageWithMode(mode, 0, 0, this.width, this.height);
    }
    
    public void drawPanorama(final int n, final int n2, final float n3) {
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        GlStateManager.matrixMode(5889);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        Project.gluPerspective(120.0f, 1.0f, 0.05f, 10.0f);
        GlStateManager.matrixMode(5888);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.rotate(180.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        final int n4 = 8;
        for (int i = 0; i < 64; ++i) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((i % n4 / (float)n4 - 0.5f) / 64.0f, (i / n4 / (float)n4 - 0.5f) / 64.0f, 0.0f);
            GlStateManager.rotate(MathHelper.sin(this.panoramaTimer / 400.0f) * 25.0f + 20.0f, 1.0f, 0.0f, 0.0f);
            GlStateManager.rotate(-this.panoramaTimer * 0.1f, 0.0f, 1.0f, 0.0f);
            for (int j = 0; j < 6; ++j) {
                GlStateManager.pushMatrix();
                if (j == 1) {
                    GlStateManager.rotate(90.0f, 0.0f, 1.0f, 0.0f);
                }
                if (j == 2) {
                    GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
                }
                if (j == 3) {
                    GlStateManager.rotate(-90.0f, 0.0f, 1.0f, 0.0f);
                }
                if (j == 4) {
                    GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
                }
                if (j == 5) {
                    GlStateManager.rotate(-90.0f, 1.0f, 0.0f, 0.0f);
                }
                this.titlePanoramaPaths[j].bind();
                buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                final int n5 = 255 / (i + 1);
                buffer.pos(-1.0, -1.0, 1.0).tex(0.0, 0.0).color(255, 255, 255, n5).endVertex();
                buffer.pos(1.0, -1.0, 1.0).tex(1.0, 0.0).color(255, 255, 255, n5).endVertex();
                buffer.pos(1.0, 1.0, 1.0).tex(1.0, 1.0).color(255, 255, 255, n5).endVertex();
                buffer.pos(-1.0, 1.0, 1.0).tex(0.0, 1.0).color(255, 255, 255, n5).endVertex();
                instance.draw();
                GlStateManager.popMatrix();
            }
            GlStateManager.popMatrix();
            GlStateManager.colorMask(true, true, true, false);
        }
        buffer.setTranslation(0.0, 0.0, 0.0);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.matrixMode(5889);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.enableCull();
        GlStateManager.enableDepth();
    }
    
    public void confirmClicked(final boolean b, final int n) {
        Label_0330: {
            if (b) {
                String str = null;
                if (this.beingChecked instanceof Button) {
                    final Button button = (Button)this.beingChecked;
                    if (button.action != null && button.action instanceof ActionOpenLink) {
                        str = ((ActionOpenLink)button.action).getLink();
                    }
                }
                else if (this.beingChecked instanceof Label) {
                    final Label label = (Label)this.beingChecked;
                    if (label.action != null && label.action instanceof ActionOpenLink) {
                        str = ((ActionOpenLink)label.action).getLink();
                    }
                }
                if (str != null) {
                    Logger logger;
                    String s;
                    Throwable t;
                    try {
                        final Class<?> forName = Class.forName("java.awt.Desktop");
                        forName.getMethod("browse", URI.class).invoke(forName.getMethod("getDesktop", (Class<?>[])new Class[0]).invoke(null, new Object[0]), new URI(str));
                        break Label_0330;
                    }
                    catch (Throwable t2) {
                        logger = Main.logger;
                        s = "Couldn't open link";
                        t = t2;
                    }
                    logger.error(s, t);
                }
            }
        }
        this.mc.displayGuiScreen((GuiScreen)this);
    }
    
    public void rotateAndBlurSkybox(final float n) {
        this.mc.getTextureManager().bindTexture(this.backgroundTexture);
        GL11.glTexParameteri(3553, 10241, 9729);
        GL11.glTexParameteri(3553, 10240, 9729);
        GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.colorMask(true, true, true, false);
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        GlStateManager.disableAlpha();
        final int n2 = 3;
        if (this.guiConfig.panorama.blur) {
            for (int i = 0; i < n2; ++i) {
                final float n3 = 1.0f / (i + 1);
                final int width = this.width;
                final int height = this.height;
                final float n4 = (i - n2 / 2) / 256.0f;
                buffer.pos((double)width, (double)height, (double)this.zLevel).tex((double)(0.0f + n4), 1.0).color(1.0f, 1.0f, 1.0f, n3).endVertex();
                buffer.pos((double)width, 0.0, (double)this.zLevel).tex((double)(1.0f + n4), 1.0).color(1.0f, 1.0f, 1.0f, n3).endVertex();
                buffer.pos(0.0, 0.0, (double)this.zLevel).tex((double)(1.0f + n4), 0.0).color(1.0f, 1.0f, 1.0f, n3).endVertex();
                buffer.pos(0.0, (double)height, (double)this.zLevel).tex((double)(0.0f + n4), 0.0).color(1.0f, 1.0f, 1.0f, n3).endVertex();
            }
        }
        instance.draw();
        GlStateManager.enableAlpha();
        GlStateManager.colorMask(true, true, true, true);
    }
    
    public void loadSplashTexts() {
        if (this.guiConfig.splashText != null) {
            final String[] split = this.guiConfig.splashText.texts.get().split("\n");
            this.splashText = split[this.rand.nextInt(split.length)];
        }
    }
    
    public GuiCustom(final GuiConfig guiConfig) {
        this.guiConfig = guiConfig;
        this.rand = new Random();
        this.loadedSplashText = false;
    }
    
    public void initGui() {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        if (!this.loadedSplashText && this.guiConfig.splashText != null) {
            if (this.guiConfig.splashText.synced) {
                this.splashText = Main.config.getGUI("mainmenu").splashText;
            }
            else {
                this.loadSplashTexts();
            }
            this.loadedSplashText = true;
        }
        this.textLabels = new ArrayList<GuiCustomLabel>();
        this.buttonCounter = 0;
        this.viewportTexture = new DynamicTexture(256, 256);
        this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
        final Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        if (instance.get(2) + 1 == 11 && instance.get(5) == 9) {
            this.splashText = "Happy birthday, ez!";
        }
        else if (instance.get(2) + 1 == 6 && instance.get(5) == 1) {
            this.splashText = "Happy birthday, Notch!";
        }
        else if (instance.get(2) + 1 == 12 && instance.get(5) == 24) {
            this.splashText = "Merry X-mas!";
        }
        else if (instance.get(2) + 1 == 1 && instance.get(5) == 1) {
            this.splashText = "Happy new year!";
        }
        else if (instance.get(2) + 1 == 10) {
            if (instance.get(5) == 31) {
                this.splashText = "OOoooOOOoooo! Spooky!";
            }
        }
        int n = 6000;
        for (final Button button : this.guiConfig.customButtons) {
            if (button.wrappedButtonID != -1) {
                this.buttonList.add(this.alignButton(button, new GuiCustomWrappedButton(button.wrappedButtonID, button.wrappedButtonID, button)));
            }
            else {
                this.buttonList.add(this.alignButton(button, new GuiCustomButton(n, button)));
                ++n;
            }
        }
        for (final Label label : this.guiConfig.customLabels) {
            this.textLabels.add(new GuiCustomLabel(this, label, this.modX(label.posX, label.alignment), this.modY(label.posY, label.alignment)));
        }
    }
    
    public void drawImageWithMode(final MODE mode, final int n, final int n2, final int n3, final int n4) {
        final int glGetTexLevelParameteri = GL11.glGetTexLevelParameteri(3553, 0, 4096);
        final int glGetTexLevelParameteri2 = GL11.glGetTexLevelParameteri(3553, 0, 4097);
        final float n5 = n3 / (float)glGetTexLevelParameteri;
        final float n6 = n4 / (float)glGetTexLevelParameteri2;
        switch (GuiCustom$1.$SwitchMap$com$krazzzzymonkey$catalyst$lib$MODE[mode.ordinal()]) {
            case 1: {
                int n7;
                int n8;
                if (n5 > n6) {
                    n7 = (int)(glGetTexLevelParameteri * n5);
                    n8 = (int)(glGetTexLevelParameteri2 * n5);
                }
                else {
                    n7 = (int)(glGetTexLevelParameteri * n6);
                    n8 = (int)(glGetTexLevelParameteri2 * n6);
                }
                RenderUtil.drawPartialImage(n, n2, 0, 0, n7, n8, glGetTexLevelParameteri, glGetTexLevelParameteri2);
                break;
            }
            case 2: {
                RenderUtil.drawCompleteImage(n, n2, n3, n4);
                break;
            }
            case 3: {
                RenderUtil.drawCompleteImage(n + (int)(n3 / 2.0f - glGetTexLevelParameteri / 2.0f), n2 + (int)(n4 / 2.0f - glGetTexLevelParameteri2 / 2.0f), glGetTexLevelParameteri, glGetTexLevelParameteri2);
                break;
            }
            case 4: {
                final int n9 = (int)Math.ceil(n3 / (float)glGetTexLevelParameteri);
                final int n10 = (int)Math.ceil(n4 / (float)glGetTexLevelParameteri2);
                for (int i = 0; i < n9; ++i) {
                    for (int j = 0; j < n10; ++j) {
                        RenderUtil.drawCompleteImage(n + i * glGetTexLevelParameteri, n2 + j * glGetTexLevelParameteri2, glGetTexLevelParameteri, glGetTexLevelParameteri2);
                    }
                }
                break;
            }
        }
    }
}
