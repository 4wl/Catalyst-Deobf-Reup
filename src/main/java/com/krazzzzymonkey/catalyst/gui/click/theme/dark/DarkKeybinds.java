/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.theme.dark;

import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import org.lwjgl.input.Keyboard;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.gui.click.elements.KeybindMods;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;

public class DarkKeybinds extends ComponentRenderer
{
    @Override
    public void drawComponent(final Component component, final int n, final int n2) {
        final KeybindMods keybindMods = (KeybindMods)component;
        final int n3 = Main.smallFontRenderer.getStringWidth("Bind") + 7;
        RenderUtils.drawBorderedRect(keybindMods.getX() - 24 + n3, keybindMods.getY() - 1, keybindMods.getX() + keybindMods.getDimension().width - 2, keybindMods.getY() + 12, 2.0f, ColorUtils.color(0.0f, 0.0f, 0.0f, 1.0f), ColorUtils.color(20, 20, 20, 255));
        Main.smallFontRenderer.drawString("Bind", (float)(keybindMods.getX() + 5), (float)(keybindMods.getY() + 3), -1);
        if (keybindMods.getMod().getKey() == -1) {
            Main.smallFontRenderer.drawString(keybindMods.isEditing() ? "|" : "NONE", (float)(keybindMods.getX() + keybindMods.getDimension().width / 2 + n3 / 2 - this.theme.fontRenderer.getStringWidth("NONE") / 2), (float)(keybindMods.getY() + 3), keybindMods.isEditing() ? -1 : ColorUtils.color(0.6f, 0.6f, 0.6f, 1.0f));
        }
        else {
            Main.smallFontRenderer.drawString(keybindMods.isEditing() ? "|" : Keyboard.getKeyName(keybindMods.getMod().getKey()), (float)(keybindMods.getX() + keybindMods.getDimension().width / 2 + n3 / 2 - this.theme.fontRenderer.getStringWidth(Keyboard.getKeyName(keybindMods.getMod().getKey())) / 2), (float)(keybindMods.getY() + 3), -1);
        }
    }
    
    public DarkKeybinds(final Theme theme) {
        super(ComponentType.KEYBIND, theme);
    }
    
    @Override
    public void doInteractions(final Component component, final int n, final int n2) {
    }
}
