/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.theme;

import net.minecraftforge.client.model.obj.OBJModel$Texture;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import java.util.HashMap;
import net.minecraft.client.gui.FontRenderer;

public class Theme
{
    public FontRenderer fontRenderer;
    public HashMap<ComponentType, ComponentRenderer> rendererHashMap;
    public OBJModel$Texture icons;
    public String themeName;
    public int frameHeight;
    
    public Theme(final String themeName) {
        this.rendererHashMap = new HashMap<ComponentType, ComponentRenderer>();
        this.frameHeight = 15;
        this.themeName = themeName;
    }
    
    public FontRenderer getFontRenderer() {
        return this.fontRenderer;
    }
    
    public String getThemeName() {
        return this.themeName;
    }
    
    public int getFrameHeight() {
        return this.frameHeight;
    }
    
    public HashMap getRenderer() {
        return this.rendererHashMap;
    }
    
    public void addRenderer(final ComponentType key, final ComponentRenderer value) {
        this.rendererHashMap.put(key, value);
    }
}
