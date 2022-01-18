/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.account;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonWithImage extends GuiButton
{
    public static ResourceLocation customButtonTextures;
    
    static {
        GuiButtonWithImage.customButtonTextures = new ResourceLocation("accswitcher:textures/gui/custombutton.png");
    }
    
    public void drawButton(final Minecraft minecraft, final int n, final int n2, final float n3) {
        Label_0595: {
            if (this.visible) {
                final FontRenderer fontRenderer = minecraft.fontRenderer;
                minecraft.getTextureManager().bindTexture(GuiButtonWithImage.customButtonTextures);
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                boolean hovered = false;
                Label_0167: {
                    if (n >= this.x) {
                        if (n2 >= this.y && n < this.x + this.width && n2 < this.y + this.height) {
                            hovered = true;
                            break Label_0167;
                        }
                    }
                    hovered = false;
                }
                this.hovered = hovered;
                final int hoverState = this.getHoverState(this.hovered);
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                GlStateManager.blendFunc(770, 771);
                this.drawTexturedModalRect(this.x, this.y, 0, 46 + hoverState * 20, this.width / 2, this.height);
                this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + hoverState * 20, this.width / 2, this.height);
                this.mouseDragged(minecraft, n, n2);
                int packedFGColour = 14737632;
                if (this.packedFGColour != 0) {
                    packedFGColour = this.packedFGColour;
                }
                else if (!this.enabled) {
                    packedFGColour = 10526880;
                }
                else if (this.hovered) {
                    packedFGColour = 16777120;
                }
                final FontRenderer fontRenderer2 = fontRenderer;
                final String displayString = this.displayString;
                final int x = this.x;
                while (true) {
                    switch (1955890319 + 1645484530 + 1) {
                        case 416895753: {
                            continue;
                        }
                        default: {
                            this.drawCenteredString(fontRenderer2, displayString, x + this.width / 2, this.y + (this.height - 8) / 2, packedFGColour);
                            break Label_0595;
                        }
                        case -322979965: {
                            throw null;
                        }
                    }
                }
            }
        }
    }
    
    public GuiButtonWithImage(final int n, final int n2, final int n3, final int n4, final int n5, final String s) {
        super(n, n2, n3, n4, n5, s);
    }
}
