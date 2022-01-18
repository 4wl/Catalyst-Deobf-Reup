/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.textures;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class TextureResourceLocation extends ResourceLocation implements ITexture
{
    public TextureResourceLocation(final String resourceString) {
        super(resourceString);
    }
    
    public void bind() {
        Minecraft.getMinecraft().renderEngine.bindTexture((ResourceLocation)this);
    }
}
