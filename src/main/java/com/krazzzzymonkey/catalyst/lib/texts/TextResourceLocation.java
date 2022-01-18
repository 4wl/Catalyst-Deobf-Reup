/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.texts;

import net.minecraft.client.resources.IResource;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class TextResourceLocation implements IText
{
    String string;
    ResourceLocation resourceLocation;
    
    public TextResourceLocation(final String resourceString) {
        this.resourceLocation = new ResourceLocation(resourceString);
        this.string = "";
    }
    
    @Override
    public String get() {
        if (this.string == null) {
            return "";
        }
        if (this.string.equals("")) {
            IResource resource = null;
            try {
                resource = Minecraft.getMinecraft().getResourceManager().getResource(this.resourceLocation);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            if (resource == null) {
                this.string = null;
                return "";
            }
            BufferedReader in = null;
            in = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            final StringBuilder builder = new StringBuilder();
            String inputLine = null;
            try {
                inputLine = in.readLine();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
            do {
                builder.append(inputLine);
                try {
                    inputLine = in.readLine();
                }
                catch (IOException e3) {
                    e3.printStackTrace();
                    break;
                }
                if (inputLine != null) {
                    builder.append("\n");
                }
            } while (inputLine != null);
            try {
                in.close();
            }
            catch (IOException e3) {
                e3.printStackTrace();
            }
            this.string = builder.toString();
        }
        return this.string;
    }
}
