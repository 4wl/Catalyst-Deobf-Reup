/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager$LogicOp;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import com.google.common.base.Predicates;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.math.MathHelper;
import com.google.common.base.Predicate;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiPageButtonList$GuiResponder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.gui.Gui;

@SideOnly(Side.CLIENT)
public class GuiTextField extends Gui
{
    public String text;
    public int disabledColor;
    public boolean enableBackgroundDrawing;
    public int id;
    public boolean isFocused;
    public int cursorCounter;
    public int cursorPosition;
    public GuiPageButtonList$GuiResponder guiResponder;
    public int y;
    public boolean canLoseFocus;
    public boolean isEnabled;
    public FontRenderer fontRenderer;
    public int maxStringLength;
    public int enabledColor;
    public int lineScrollOffset;
    public int height;
    public Predicate<String> validator;
    public int width;
    public int selectionEnd;
    public int x;
    public boolean visible;
    
    public int getNthWordFromPosWS(final int a, final int n, final boolean b) {
        int index = n;
        final boolean b2 = a < 0;
        for (int abs = Math.abs(a), i = 0; i < abs; ++i) {
            if (!b2) {
                final int length = this.text.length();
                index = this.text.indexOf(32, index);
                if (index == -1) {
                    index = length;
                }
                else {
                    while (b && index < length && this.text.charAt(index) == ' ') {
                        ++index;
                    }
                }
            }
            else {
                while (b && index > 0 && this.text.charAt(index - 1) == ' ') {
                    --index;
                }
                while (index > 0 && this.text.charAt(index - 1) != ' ') {
                    --index;
                }
            }
        }
        return index;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void moveCursorBy(final int n) {
        this.setCursorPosition(this.selectionEnd + n);
    }
    
    public void setText(final String text) {
        if (this.validator.apply((Object)text)) {
            if (text.length() > this.maxStringLength) {
                this.text = text.substring(0, this.maxStringLength);
            }
            else {
                this.text = text;
            }
            this.setCursorPositionEnd();
        }
    }
    
    public void setSelectionPos(int selectionEnd) {
        final int length = this.text.length();
        if (selectionEnd > length) {
            selectionEnd = length;
        }
        if (selectionEnd < 0) {
            selectionEnd = 0;
        }
        this.selectionEnd = selectionEnd;
        if (this.fontRenderer != null) {
            if (this.lineScrollOffset > length) {
                this.lineScrollOffset = length;
            }
            final int width = this.getWidth();
            final int n = (this.fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), width).length() ^ this.lineScrollOffset) - 1;
            if (selectionEnd == this.lineScrollOffset) {
                this.lineScrollOffset -= this.fontRenderer.trimStringToWidth(this.text, width, true).length();
            }
            if (selectionEnd > n) {
                this.lineScrollOffset += selectionEnd - n;
            }
            else if (selectionEnd <= this.lineScrollOffset) {
                this.lineScrollOffset -= this.lineScrollOffset - selectionEnd;
            }
            this.lineScrollOffset = MathHelper.clamp(this.lineScrollOffset, 0, length);
        }
    }
    
    public void setMaxStringLength(final int n) {
        this.maxStringLength = n;
        if (this.text.length() > n) {
            this.text = this.text.substring(0, n);
        }
    }
    
    public int getNthWordFromCursor(final int n) {
        return this.getNthWordFromPos(n, this.getCursorPosition());
    }
    
    public int getCursorPosition() {
        return this.cursorPosition;
    }
    
    public void writeText(final String s) {
        String string = "";
        final String filterAllowedCharacters = ChatAllowedCharacters.filterAllowedCharacters(s);
        final int endIndex = (this.cursorPosition < this.selectionEnd) ? this.cursorPosition : this.selectionEnd;
        final int beginIndex = (this.cursorPosition < this.selectionEnd) ? this.selectionEnd : this.cursorPosition;
        final int endIndex2 = this.maxStringLength - ~this.text.length() + 1 - (endIndex - beginIndex);
        if (!this.text.isEmpty()) {
            string += this.text.substring(0, endIndex);
        }
        String s2;
        int length;
        if (endIndex2 < filterAllowedCharacters.length()) {
            s2 = string + filterAllowedCharacters.substring(0, endIndex2);
            length = endIndex2;
        }
        else {
            s2 = string + filterAllowedCharacters;
            length = filterAllowedCharacters.length();
        }
        if (!this.text.isEmpty() && beginIndex < this.text.length()) {
            s2 += this.text.substring(beginIndex);
        }
        if (this.validator.apply((Object)s2)) {
            this.text = s2;
            this.moveCursorBy(endIndex - this.selectionEnd + length);
            this.setResponderEntryValue(this.id, this.text);
        }
    }
    
    public GuiTextField(final int id, final FontRenderer fontRenderer, final int x, final int y, final int width, final int height) {
        this.text = "";
        this.maxStringLength = 32;
        this.enableBackgroundDrawing = true;
        this.canLoseFocus = true;
        this.isEnabled = true;
        this.enabledColor = 14737632;
        this.disabledColor = 7368816;
        this.visible = true;
        this.validator = (Predicate<String>)Predicates.alwaysTrue();
        this.id = id;
        this.fontRenderer = fontRenderer;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void setCursorPositionZero() {
        this.setCursorPosition(0);
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getNthWordFromPos(final int n, final int n2) {
        return this.getNthWordFromPosWS(n, n2, true);
    }
    
    public boolean getVisible() {
        return this.visible;
    }
    
    public void setEnabled(final boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    
    public void setGuiResponder(final GuiPageButtonList$GuiResponder guiResponder) {
        this.guiResponder = guiResponder;
    }
    
    public void setValidator(final Predicate validator) {
        this.validator = (Predicate<String>)validator;
    }
    
    public int getMaxStringLength() {
        return this.maxStringLength;
    }
    
    public boolean mouseClicked(final int n, final int n2, final int n3) {
        while (true) {
            switch (1166207952 + 576830265 + 1) {
                case -1083656981: {
                    continue;
                }
                case 1742950633: {
                    boolean b = false;
                    Label_0132: {
                        Label_0131: {
                            if (n >= this.x && n < this.x + this.width && n2 >= this.y) {
                                final int y = this.y;
                                while (true) {
                                    switch (-457399739 - 84760989 + 1) {
                                        case 796323008: {
                                            continue;
                                        }
                                        default: {
                                            if (n2 < y + this.height) {
                                                b = true;
                                                break Label_0132;
                                            }
                                            break Label_0131;
                                        }
                                        case -1348546405: {
                                            throw null;
                                        }
                                    }
                                }
                            }
                        }
                        b = false;
                    }
                    final boolean focused = b;
                    if (this.canLoseFocus) {
                        this.setFocused(focused);
                    }
                    if (this.isFocused && focused && n3 == 0) {
                        int n4 = n - this.x;
                        if (this.enableBackgroundDrawing) {
                            n4 -= 4;
                        }
                        this.setCursorPosition(this.fontRenderer.trimStringToWidth(this.fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth()), n4).length() + this.lineScrollOffset);
                        return true;
                    }
                    return false;
                }
                default: {
                    throw null;
                }
            }
        }
    }
    
    public void updateCursorCounter() {
        ++this.cursorCounter;
    }
    
    public void drawTextBox(final int n, final int n2) {
        if (this.getVisible()) {
            if (this.getEnableBackgroundDrawing()) {
                drawRect(this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, n);
                drawRect(this.x, this.y, this.x + this.width, this.y + this.height, n2);
            }
            final int n3 = this.isEnabled ? this.enabledColor : this.disabledColor;
            final int n4 = this.cursorPosition - this.lineScrollOffset;
            final int selectionEnd = this.selectionEnd;
            final int lineScrollOffset = this.lineScrollOffset;
            int endIndex = (selectionEnd & ~lineScrollOffset) + (lineScrollOffset & ~selectionEnd);
            final String trimStringToWidth = this.fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
            final boolean b = n4 >= 0 && n4 <= trimStringToWidth.length();
            final boolean b2 = this.isFocused && this.cursorCounter / 6 % 2 == 0 && b;
            final int n5 = this.enableBackgroundDrawing ? (this.x + 4) : this.x;
            final int n6 = this.enableBackgroundDrawing ? (this.y + (this.height - 8) / 2) : this.y;
            int drawStringWithShadow = n5;
            Label_0486: {
                if (endIndex > trimStringToWidth.length()) {
                    final int length = trimStringToWidth.length();
                    while (true) {
                        switch (689199592 - 1330922068 + 1) {
                            case -467374668: {
                                continue;
                            }
                            default: {
                                endIndex = length;
                                break Label_0486;
                            }
                            case 139163082: {
                                throw null;
                            }
                        }
                    }
                }
            }
            if (!trimStringToWidth.isEmpty()) {
                drawStringWithShadow = this.fontRenderer.drawStringWithShadow(b ? trimStringToWidth.substring(0, n4) : trimStringToWidth, (float)n5, (float)n6, n3);
            }
            final boolean b3 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
            int n7 = drawStringWithShadow;
            if (!b) {
                n7 = ((n4 > 0) ? (n5 + this.width) : n5);
            }
            else if (b3) {
                n7 = drawStringWithShadow - 1;
                --drawStringWithShadow;
            }
            if (!trimStringToWidth.isEmpty() && b && n4 < trimStringToWidth.length()) {
                this.fontRenderer.drawStringWithShadow(trimStringToWidth.substring(n4), (float)drawStringWithShadow, (float)n6, n3);
            }
            if (b2) {
                if (b3) {
                    Gui.drawRect(n7, n6 - 1, n7 + 1, n6 + 1 + this.fontRenderer.FONT_HEIGHT, -3092272);
                }
                else {
                    this.fontRenderer.drawStringWithShadow("_", (float)n7, (float)n6, n3);
                }
            }
            if (endIndex != n4) {
                this.drawSelectionBox(n7, n6 - 1, n5 + this.fontRenderer.getStringWidth(trimStringToWidth.substring(0, endIndex)) - 1, n6 + 1 + this.fontRenderer.FONT_HEIGHT);
            }
        }
    }
    
    public boolean isFocused() {
        return this.isFocused;
    }
    
    public void deleteWords(final int n) {
        if (!this.text.isEmpty()) {
            if (this.selectionEnd != this.cursorPosition) {
                this.writeText("");
            }
            else {
                this.deleteFromCursor(this.getNthWordFromCursor(n) - this.cursorPosition);
            }
        }
    }
    
    public int getSelectionEnd() {
        return this.selectionEnd;
    }
    
    public boolean textboxKeyTyped(final char c, final int n) {
        if (!this.isFocused) {
            return false;
        }
        if (GuiScreen.isKeyComboCtrlA(n)) {
            this.setCursorPositionEnd();
            this.setSelectionPos(0);
            return true;
        }
        if (GuiScreen.isKeyComboCtrlC(n)) {
            GuiScreen.setClipboardString(this.getSelectedText());
            return true;
        }
        if (GuiScreen.isKeyComboCtrlV(n)) {
            if (this.isEnabled) {
                this.writeText(GuiScreen.getClipboardString());
            }
            return true;
        }
        if (GuiScreen.isKeyComboCtrlX(n)) {
            GuiScreen.setClipboardString(this.getSelectedText());
            if (this.isEnabled) {
                this.writeText("");
            }
            return true;
        }
        switch (n) {
            case 14: {
                if (GuiScreen.isCtrlKeyDown()) {
                    if (this.isEnabled) {
                        this.deleteWords(-1);
                    }
                }
                else if (this.isEnabled) {
                    this.deleteFromCursor(-1);
                }
                return true;
            }
            case 199: {
                if (GuiScreen.isShiftKeyDown()) {
                    this.setSelectionPos(0);
                }
                else {
                    this.setCursorPositionZero();
                }
                return true;
            }
            case 203: {
                if (GuiScreen.isShiftKeyDown()) {
                    if (GuiScreen.isCtrlKeyDown()) {
                        this.setSelectionPos(this.getNthWordFromPos(-1, this.getSelectionEnd()));
                    }
                    else {
                        this.setSelectionPos(this.getSelectionEnd() - 1);
                    }
                }
                else if (GuiScreen.isCtrlKeyDown()) {
                    this.setCursorPosition(this.getNthWordFromCursor(-1));
                }
                else {
                    this.moveCursorBy(-1);
                }
                return true;
            }
            case 205: {
                if (GuiScreen.isShiftKeyDown()) {
                    if (GuiScreen.isCtrlKeyDown()) {
                        this.setSelectionPos(this.getNthWordFromPos(1, this.getSelectionEnd()));
                    }
                    else {
                        this.setSelectionPos(this.getSelectionEnd() + 1);
                    }
                }
                else if (GuiScreen.isCtrlKeyDown()) {
                    this.setCursorPosition(this.getNthWordFromCursor(1));
                }
                else {
                    this.moveCursorBy(1);
                }
                return true;
            }
            case 207: {
                if (GuiScreen.isShiftKeyDown()) {
                    this.setSelectionPos(this.text.length());
                }
                else {
                    this.setCursorPositionEnd();
                }
                return true;
            }
            case 211: {
                if (GuiScreen.isCtrlKeyDown()) {
                    if (this.isEnabled) {
                        this.deleteWords(1);
                    }
                }
                else if (this.isEnabled) {
                    this.deleteFromCursor(1);
                }
                return true;
            }
            default: {
                if (ChatAllowedCharacters.isAllowedCharacter(c)) {
                    if (this.isEnabled) {
                        this.writeText(Character.toString(c));
                    }
                    return true;
                }
                return false;
            }
        }
    }
    
    public void setResponderEntryValue(final int n, final String s) {
        if (this.guiResponder != null) {
            this.guiResponder.setEntryValue(n, s);
        }
    }
    
    public void setCursorPosition(final int cursorPosition) {
        this.cursorPosition = cursorPosition;
        this.setSelectionPos(this.cursorPosition = MathHelper.clamp(this.cursorPosition, 0, this.text.length()));
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public void setFocused(final boolean b) {
        if (b) {
            if (!this.isFocused) {
                this.cursorCounter = 0;
            }
        }
        this.isFocused = b;
        if (Minecraft.getMinecraft().currentScreen != null) {
            Minecraft.getMinecraft().currentScreen.setFocused(b);
        }
    }
    
    public void deleteFromCursor(final int n) {
        if (!this.text.isEmpty()) {
            if (this.selectionEnd != this.cursorPosition) {
                this.writeText("");
            }
            else {
                final boolean b = n < 0;
                final int endIndex = b ? (this.cursorPosition + n) : this.cursorPosition;
                final int beginIndex = b ? this.cursorPosition : (this.cursorPosition + n);
                String s = "";
                if (endIndex >= 0) {
                    s = this.text.substring(0, endIndex);
                }
                if (beginIndex < this.text.length()) {
                    s += this.text.substring(beginIndex);
                }
                if (this.validator.apply((Object)s)) {
                    this.text = s;
                    if (b) {
                        this.moveCursorBy(n);
                    }
                    this.setResponderEntryValue(this.id, this.text);
                }
            }
        }
    }
    
    public void setTextColor(final int enabledColor) {
        this.enabledColor = enabledColor;
    }
    
    public void setDisabledTextColour(final int disabledColor) {
        this.disabledColor = disabledColor;
    }
    
    public void setEnableBackgroundDrawing(final boolean enableBackgroundDrawing) {
        this.enableBackgroundDrawing = enableBackgroundDrawing;
    }
    
    public void setCursorPositionEnd() {
        this.setCursorPosition(this.text.length());
    }
    
    public boolean getEnableBackgroundDrawing() {
        return this.enableBackgroundDrawing;
    }
    
    public void drawSelectionBox(int n, int n2, int n3, int n4) {
        if (n < n3) {
            final int n5 = n;
            n = n3;
            n3 = n5;
        }
        if (n2 < n4) {
            final int n6 = n2;
            n2 = n4;
            n4 = n6;
        }
        if (n3 > this.x + this.width) {
            n3 = this.x + this.width;
        }
        final int n7 = n;
        final int x = this.x;
        final int width = this.width;
        if (n7 > (x | width) * 2 + (x ^ width) + 1) {
            n = this.x + this.width;
        }
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        GlStateManager.color(0.0f, 0.0f, 255.0f, 255.0f);
        GlStateManager.disableTexture2D();
        GlStateManager.enableColorLogic();
        GlStateManager.colorLogicOp(GlStateManager$LogicOp.OR_REVERSE);
        buffer.begin(7, DefaultVertexFormats.POSITION);
        buffer.pos((double)n, (double)n4, 0.0).endVertex();
        buffer.pos((double)n3, (double)n4, 0.0).endVertex();
        buffer.pos((double)n3, (double)n2, 0.0).endVertex();
        buffer.pos((double)n, (double)n2, 0.0).endVertex();
        instance.draw();
        GlStateManager.disableColorLogic();
        GlStateManager.enableTexture2D();
    }
    
    public int getWidth() {
        return this.getEnableBackgroundDrawing() ? (this.width - 8) : this.width;
    }
    
    public String getSelectedText() {
        return this.text.substring((this.cursorPosition < this.selectionEnd) ? this.cursorPosition : this.selectionEnd, (this.cursorPosition < this.selectionEnd) ? this.selectionEnd : this.cursorPosition);
    }
    
    public void setCanLoseFocus(final boolean canLoseFocus) {
        this.canLoseFocus = canLoseFocus;
    }
}
