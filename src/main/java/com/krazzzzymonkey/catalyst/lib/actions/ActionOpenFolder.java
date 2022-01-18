/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.actions;

import java.io.IOException;
import java.awt.Desktop;
import java.io.File;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.gui.GuiCustom;

public class ActionOpenFolder implements IAction
{
    String folderName;
    
    public ActionOpenFolder(final String folderName) {
        this.folderName = folderName;
    }
    
    @Override
    public void perform(final Object source, final GuiCustom parent) {
        final File toOpen = new File(Minecraft.getMinecraft().mcDataDir, this.folderName);
        boolean isInMinecraftFolder = false;
        try {
            File parentFile = toOpen.getCanonicalFile();
            while ((parentFile = parentFile.getParentFile()) != null) {
                if (parentFile.getCanonicalPath().equals(Minecraft.getMinecraft().mcDataDir.getCanonicalPath())) {
                    isInMinecraftFolder = true;
                }
            }
            if (isInMinecraftFolder && toOpen.isDirectory() && Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(toOpen);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException ex) {}
    }
}
