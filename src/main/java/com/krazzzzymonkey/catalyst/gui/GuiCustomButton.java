/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui;

import net.minecraft.client.audio.SoundHandler;
import com.krazzzzymonkey.catalyst.utils.LogicUtil;
import com.krazzzzymonkey.catalyst.lib.StringReplacer;
import com.krazzzzymonkey.catalyst.utils.RenderUtil;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.ISound$AttenuationType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import com.krazzzzymonkey.catalyst.Main;
import net.minecraft.client.resources.I18n;
import java.util.Iterator;
import net.minecraft.client.renderer.GlStateManager;
import com.krazzzzymonkey.catalyst.utils.font.CFontRenderer;
import java.util.List;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.configuration.elements.Button;
import com.krazzzzymonkey.catalyst.lib.textures.ITexture;
import net.minecraft.client.gui.GuiButton;

public class GuiCustomButton extends GuiButton
{
    public ITexture texture;
    public int hoverText;
    public Button b;
    public int normalText;
    
    public void drawHoveringText(final Minecraft minecraft, final List list, final int n, final int n2, final CFontRenderer cFontRenderer) {
        if (!list.isEmpty()) {
            final int width = minecraft.currentScreen.width;
            final int height = minecraft.currentScreen.height;
            GlStateManager.disableDepth();
            int n3 = 0;
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                final int stringWidth = cFontRenderer.getStringWidth(iterator.next());
                if (stringWidth > n3) {
                    n3 = stringWidth;
                }
            }
            int n4 = n + 12;
            int n5 = n2 - 12;
            int n6 = 8;
            if (list.size() > 1) {
                n6 += 2 + (list.size() - 1) * 10;
            }
            if (n4 + n3 > width) {
                n4 -= 28 + n3;
            }
            if (n5 + n6 + 6 > height) {
                n5 = this.height - n6 - 6;
            }
            this.zLevel = 300.0f;
            final int n7 = -267386864;
            final int n8 = n4 - 3;
            final int n9 = n5;
            final int n10 = 4;
            this.drawGradientRect(n8, (n9 & ~n10) + (n10 & ~n9) + 1, n4 + n3 + 3, n5 - 3, n7, n7);
            this.drawGradientRect(n4 - 3, n5 + n6 + 3, n4 + n3 + 3, n5 + n6 + 4, n7, n7);
            this.drawGradientRect(n4 - 3, n5 - 3, n4 + n3 + 3, n5 + n6 + 3, n7, n7);
            this.drawGradientRect(n4 - 4, n5 - 3, n4 - 3, n5 + n6 + 3, n7, n7);
            this.drawGradientRect(n4 + n3 + 3, n5 - 3, n4 + n3 + 4, n5 + n6 + 3, n7, n7);
            final int n11 = 1347420415;
            final int n12 = (n11 & 0xFEFEFE) >> 1 | (n11 & 0xFF000000);
            this.drawGradientRect(n4 - 3, n5 - 3 + 1, n4 - 3 + 1, n5 + n6 + 3 - 1, n11, n12);
            this.drawGradientRect(n4 + n3 + 2, n5 - 3 + 1, n4 + n3 + 3, n5 + n6 + 3 - 1, n11, n12);
            this.drawGradientRect(n4 - 3, n5 - 3, n4 + n3 + 3, n5 - 3 + 1, n11, n11);
            this.drawGradientRect(n4 - 3, n5 + n6 + 2, n4 + n3 + 3, n5 + n6 + 3, n12, n12);
            for (int i = 0; i < list.size(); ++i) {
                cFontRenderer.drawStringWithShadow(list.get(i), n4, n5, -1);
                if (i == 0) {
                    n5 += 2;
                }
                n5 += 10;
            }
            this.zLevel = 0.0f;
            GlStateManager.enableDepth();
        }
    }
    
    public GuiCustomButton(final int n, final Button b) {
        super(n, b.posX, b.posY, b.width, b.height, I18n.format(b.text.get(), new Object[0]));
        this.texture = b.texture;
        this.normalText = b.normalTextColor;
        this.hoverText = b.hoverTextColor;
        this.b = b;
    }
    
    public void drawButton(final Minecraft minecraft, final int n, final int n2, final float n3) {
        if (this.visible) {
            if (this.b.name.equals("language") && this.texture == null) {
                minecraft.getTextureManager().bindTexture(GuiButton.BUTTON_TEXTURES);
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                final boolean b = n >= this.x && n2 >= this.y && n < this.x + this.width && n2 < this.y + this.height;
                int n4 = 106;
                if (b) {
                    n4 += this.height;
                }
                this.drawTexturedModalRect(this.x, this.y, 0, n4, this.width, this.height);
                return;
            }
            final CFontRenderer fontRenderer = Main.fontRenderer;
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            final boolean hovered = n >= this.x && n2 >= this.y && n < this.x + this.width && n2 < this.y + this.height;
            if (hovered && !this.hovered && this.b.hoverSound != null) {
                Minecraft.getMinecraft().getSoundHandler().playSound((ISound)new PositionedSoundRecord(new ResourceLocation(this.b.hoverSound), SoundCategory.MASTER, 1.0f, 1.0f, false, 0, ISound$AttenuationType.NONE, 0.0f, 0.0f, 0.0f));
            }
            this.hovered = hovered;
            final int hoverState = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            Label_0778: {
                if (this.texture != null) {
                    this.texture.bind();
                    final int x = this.x;
                    final int y = this.y;
                    final int n5 = 0;
                    final int n6 = (hoverState - 1) * this.b.imageHeight;
                    final Button b2 = this.b;
                    while (true) {
                        switch (1062261256 + 2118582039 + 1) {
                            case 614859064: {
                                continue;
                            }
                            case 1091972383: {
                                RenderUtil.drawPartialImage(x, y, n5, n6, b2.width, this.b.height, this.b.imageWidth, this.b.imageHeight);
                                break Label_0778;
                            }
                            default: {
                                throw null;
                            }
                        }
                    }
                }
                else {
                    minecraft.getTextureManager().bindTexture(GuiCustomButton.BUTTON_TEXTURES);
                    this.drawTexturedModalRect(this.x, this.y, 0, 46 + hoverState * 20, this.width / 2, this.height);
                    this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + hoverState * 20, this.width / 2, this.height);
                }
            }
            this.mouseDragged(minecraft, n, n2);
            int n7 = this.normalText;
            if (this.packedFGColour != 0) {
                n7 = this.packedFGColour;
            }
            else if (!this.enabled) {
                n7 = 10526880;
            }
            else if (this.hovered) {
                n7 = this.hoverText;
            }
            this.drawCenteredString(fontRenderer, this.hovered ? I18n.format(StringReplacer.replacePlaceholders(this.b.hoverText.get()), new Object[0]) : I18n.format(StringReplacer.replacePlaceholders(this.b.text.get()), new Object[0]), this.x + this.width / 2 + this.b.textOffsetX, this.y + (this.height - 8) / 2 + this.b.textOffsetY, n7, this.b.shadow);
        }
    }
    
    public void drawCenteredString(final CFontRenderer cFontRenderer, final String s, final int n, final int n2, final int n3, final boolean b) {
        if (b) {
            cFontRenderer.drawStringWithShadow(s, (float)(n - cFontRenderer.getStringWidth(s) / 2), (float)n2, n3);
        }
        else {
            cFontRenderer.drawString(s, (float)(n - cFontRenderer.getStringWidth(s) / 2), (float)n2, n3, false);
        }
    }
    
    public void drawTooltip(final Minecraft minecraft, final int n, final int n2) {
        final CFontRenderer fontRenderer = Main.fontRenderer;
        if (this.hovered) {
            if (this.b.tooltip != null) {
                this.drawHoveringText(minecraft, LogicUtil.getTooltip(this.b.tooltip.get()), n, n2, fontRenderer);
            }
        }
    }
    
    public void playPressSound(final SoundHandler soundHandler) {
        if (this.b.pressSound != null) {
            soundHandler.playSound((ISound)new PositionedSoundRecord(new ResourceLocation(this.b.pressSound), SoundCategory.MASTER, 1.0f, 1.0f, false, 0, ISound$AttenuationType.NONE, 0.0f, 0.0f, 0.0f));
        }
        else {
            super.playPressSound(soundHandler);
        }
    }
}
