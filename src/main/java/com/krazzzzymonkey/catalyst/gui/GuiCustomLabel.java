/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui;

import com.krazzzzymonkey.catalyst.lib.ANCHOR;
import com.krazzzzymonkey.catalyst.lib.StringReplacer;
import net.minecraft.client.renderer.GlStateManager;
import java.util.List;
import com.krazzzzymonkey.catalyst.lib.texts.TextString;
import com.google.common.base.Strings;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.ISound$AttenuationType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Loader;
import java.lang.reflect.Field;
import com.krazzzzymonkey.catalyst.configuration.elements.Label;
import net.minecraft.client.gui.FontRenderer;
import java.text.SimpleDateFormat;
import net.minecraft.client.gui.Gui;

public class GuiCustomLabel extends Gui
{
    public static String TIME_FORMAT;
    public boolean hovered;
    public int posY;
    public int width;
    public static SimpleDateFormat dateFormat;
    public int height;
    public FontRenderer fontRendererObj;
    public int posX;
    public Label text;
    public GuiCustom parent;
    public static String mcpversion;
    public static Field mcpversionField;
    
    static {
        GuiCustomLabel.TIME_FORMAT = "HH:mm";
        GuiCustomLabel.dateFormat = new SimpleDateFormat("HH:mm");
        try {
            (GuiCustomLabel.mcpversionField = Loader.class.getDeclaredField("mcpversion")).setAccessible(true);
            GuiCustomLabel.mcpversion = (String)GuiCustomLabel.mcpversionField.get(null);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        if (this.isMouseAboveLabel(n, n2) && this.text.action != null) {
            if (this.text.pressSound != null) {
                Minecraft.getMinecraft().getSoundHandler().playSound((ISound)new PositionedSoundRecord(new ResourceLocation(this.text.pressSound), SoundCategory.MASTER, 1.0f, 1.0f, false, 0, ISound$AttenuationType.NONE, 0.0f, 0.0f, 0.0f));
            }
            else {
                Minecraft.getMinecraft().getSoundHandler().playSound((ISound)new PositionedSoundRecord(new ResourceLocation("ui.button.click"), SoundCategory.MASTER, 1.0f, 1.0f, false, 0, ISound$AttenuationType.NONE, 0.0f, 0.0f, 0.0f));
            }
            this.text.action.perform(this.text, this.parent);
        }
    }
    
    public GuiCustomLabel(final GuiCustom parent, final Label text, final int posX, final int posY) {
        this.text = text;
        this.posX = posX;
        this.posY = posY;
        this.parent = parent;
        this.fontRendererObj = Minecraft.getMinecraft().fontRenderer;
        this.hovered = false;
        this.width = this.fontRendererObj.getStringWidth(text.text.get());
        this.height = this.fontRendererObj.FONT_HEIGHT;
        if (text.name.equals("fml")) {
            String string = "";
            final List brandings = FMLCommonHandler.instance().getBrandings(true);
            for (int i = 0; i < brandings.size(); ++i) {
                final String str = brandings.get(i);
                if (!Strings.isNullOrEmpty(str)) {
                    string = string + str + ((i < brandings.size() - 1) ? "\n" : "");
                }
            }
            final Label text2 = this.text;
            final Label text3 = this.text;
            final TextString textString = new TextString(string);
            text3.hoverText = textString;
            text2.text = textString;
        }
    }
    
    public void drawLabel(final Minecraft minecraft, final int n, final int n2) {
        if (this.text.fontSize != 1.0f) {
            GlStateManager.translate((float)this.posX, (float)this.posY, 0.0f);
            GlStateManager.scale(this.text.fontSize, this.text.fontSize, 1.0f);
            GlStateManager.translate((float)(-this.posX), (float)(-this.posY), 0.0f);
        }
        final String source = this.hovered ? this.text.hoverText.get() : this.text.text.get();
        final boolean mouseAboveLabel = this.isMouseAboveLabel(n, n2);
        if (mouseAboveLabel && !this.hovered && this.text.hoverSound != null) {
            Minecraft.getMinecraft().getSoundHandler().playSound((ISound)new PositionedSoundRecord(new ResourceLocation(this.text.hoverSound), SoundCategory.MASTER, 1.0f, 1.0f, false, 0, ISound$AttenuationType.NONE, 0.0f, 0.0f, 0.0f));
        }
        this.hovered = mouseAboveLabel;
        if (source.contains("\n")) {
            int n3 = 0;
            final String[] split = source.split("\n");
            for (int length = split.length, i = 0; i < length; ++i) {
                final String replacePlaceholders = StringReplacer.replacePlaceholders(split[i]);
                final int stringWidth = this.fontRendererObj.getStringWidth(replacePlaceholders);
                final int n4 = (this.text.anchor == ANCHOR.START) ? 0 : ((this.text.anchor == ANCHOR.MIDDLE) ? (-(stringWidth / 2)) : (-stringWidth));
                if (this.hovered) {
                    this.drawString(this.fontRendererObj, replacePlaceholders, this.posX + n4, this.posY + n3, this.text.hoverColor);
                }
                else {
                    this.drawString(this.fontRendererObj, replacePlaceholders, this.posX + n4, this.posY + n3, this.text.color);
                }
                n3 += this.fontRendererObj.FONT_HEIGHT;
            }
        }
        else {
            final String replacePlaceholders2 = StringReplacer.replacePlaceholders(source);
            final int stringWidth2 = this.fontRendererObj.getStringWidth(replacePlaceholders2);
            final int n5 = (this.text.anchor == ANCHOR.START) ? 0 : ((this.text.anchor == ANCHOR.MIDDLE) ? (-(stringWidth2 / 2)) : (-stringWidth2));
            if (this.hovered) {
                this.drawString(this.fontRendererObj, replacePlaceholders2, this.posX + n5, this.posY, this.text.hoverColor);
            }
            else {
                this.drawString(this.fontRendererObj, replacePlaceholders2, this.posX + n5, this.posY, this.text.color);
            }
        }
        if (this.text.fontSize != 1.0f) {
            GlStateManager.translate((float)this.posX, (float)this.posY, 0.0f);
            GlStateManager.scale(1.0f / this.text.fontSize, 1.0f / this.text.fontSize, 1.0f);
            GlStateManager.translate((float)(-(-this.posX) - 1), (float)(-this.posY), 0.0f);
        }
    }
    
    public boolean isMouseAboveLabel(final int n, final int n2) {
        final String value = this.text.text.get();
        if (value.contains("\n")) {
            final String[] split = value.split("\n");
            for (int i = 0; i < split.length; ++i) {
                final int stringWidth = this.fontRendererObj.getStringWidth(split[i]);
                final int font_HEIGHT = this.fontRendererObj.FONT_HEIGHT;
                int n3 = 0;
                switch (GuiCustomLabel$1.$SwitchMap$com$krazzzzymonkey$catalyst$lib$ANCHOR[this.text.anchor.ordinal()]) {
                    case 1: {
                        n3 = -stringWidth;
                        break;
                    }
                    case 2: {
                        n3 = -stringWidth / 2;
                    }
                }
                if (n >= this.posX + n3 && n2 >= this.posY + this.fontRendererObj.FONT_HEIGHT * i && n < this.posX + stringWidth + n3 && n2 < this.posY + this.fontRendererObj.FONT_HEIGHT * i + font_HEIGHT) {
                    return true;
                }
            }
            return false;
        }
        final int stringWidth2 = this.fontRendererObj.getStringWidth(value);
        final int font_HEIGHT2 = this.fontRendererObj.FONT_HEIGHT;
        int n4 = 0;
        switch (GuiCustomLabel$1.$SwitchMap$com$krazzzzymonkey$catalyst$lib$ANCHOR[this.text.anchor.ordinal()]) {
            case 1: {
                n4 = -stringWidth2;
                break;
            }
            case 2: {
                n4 = -stringWidth2 / 2;
            }
        }
        if (n >= this.posX + n4) {
            if (n2 >= this.posY) {
                if (n < this.posX + stringWidth2 + n4) {
                    if (n2 < this.posY + font_HEIGHT2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
