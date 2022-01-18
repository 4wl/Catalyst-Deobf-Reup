/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.actions;

import net.minecraftforge.fml.client.IModGuiFactory;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.Loader;
import com.krazzzzymonkey.catalyst.gui.GuiCustom;

public class ActionOpenModConfig implements IAction
{
    String modid;
    
    public ActionOpenModConfig(final String modid) {
        this.modid = modid;
    }
    
    @Override
    public void perform(final Object source, final GuiCustom parent) {
        for (final ModContainer mod : Loader.instance().getModList()) {
            if (mod.getModId().equals(this.modid)) {
                final IModGuiFactory guiFactory = FMLClientHandler.instance().getGuiFactoryFor(mod);
                if (guiFactory == null) {
                    continue;
                }
                final GuiScreen newScreen = guiFactory.createConfigGui((GuiScreen)parent);
                Minecraft.getMinecraft().displayGuiScreen(newScreen);
            }
        }
    }
}
