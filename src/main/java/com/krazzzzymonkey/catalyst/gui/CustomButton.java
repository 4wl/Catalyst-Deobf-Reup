/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.client.audio.SoundHandler;
import com.krazzzzymonkey.catalyst.utils.font.CFontRenderer;
import net.minecraft.client.renderer.GlStateManager$DestFactor;
import net.minecraft.client.renderer.GlStateManager$SourceFactor;
import net.minecraft.client.renderer.GlStateManager;
import com.krazzzzymonkey.catalyst.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.gui.Gui;

@SideOnly(Side.CLIENT)
public class CustomButton extends Gui
{
    public int packedFGColour;
    public boolean visible;
    public boolean enabled;
    public int width;
    public boolean hovered;
    public int x;
    public int height;
    public static ResourceLocation BUTTON_TEXTURES;
    public String displayString;
    public int y;
    public int id;
    
    public int getHoverState(final boolean b) {
        int n = 1;
        if (!this.enabled) {
            n = 0;
        }
        else if (b) {
            n = 2;
        }
        return n;
    }
    
    public CustomButton(final int id, final int x, final int y, final int width, final int height, final String displayString) {
        this.width = 200;
        this.height = 20;
        this.enabled = true;
        this.visible = true;
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.displayString = displayString;
    }
    
    public boolean isMouseOver() {
        return this.hovered;
    }
    
    public void mouseReleased(final int n, final int n2) {
    }
    
    public void mouseDragged(final Minecraft minecraft, final int n, final int n2) {
    }
    
    public int getButtonWidth() {
        return this.width;
    }
    
    static {
        CustomButton.BUTTON_TEXTURES = new ResourceLocation("textures/gui/widgets.png");
    }
    
    public boolean mousePressed(final Minecraft minecraft, final int n, final int n2) {
        if (this.enabled) {
            if (this.visible) {
                if (n >= this.x && n2 >= this.y && n < this.x + this.width && n2 < this.y + this.height) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public CustomButton(final int n, final int n2, final int n3, final String s) {
        this(n, n2, n3, 200, 20, s);
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public void drawButtonForegroundLayer(final int n, final int n2) {
    }
    
    public void drawCustomButton(final Minecraft minecraft, final int n, final int n2, final float n3) {
        if (this.visible) {
            final CFontRenderer fontRenderer = Main.fontRenderer;
            minecraft.getTextureManager().bindTexture(CustomButton.BUTTON_TEXTURES);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            this.hovered = (n >= this.x && n2 >= this.y && n < this.x + this.width && n2 < this.y + this.height);
            final int hoverState = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager$SourceFactor.SRC_ALPHA, GlStateManager$DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager$SourceFactor.ONE, GlStateManager$DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager$SourceFactor.SRC_ALPHA, GlStateManager$DestFactor.ONE_MINUS_SRC_ALPHA);
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
            Main.fontRenderer.drawCenteredString(this.displayString, (float)(this.x + this.width / 2), (float)(this.y + (this.height - 8) / 2), packedFGColour);
        }
    }
    
    public void playPressSound(final SoundHandler soundHandler) {
        soundHandler.playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
    }
}
