/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.actions;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.StartupQuery;
import net.minecraft.world.WorldSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.GuiOldSaveLoadConfirm;
import net.minecraftforge.fml.common.FMLLog;
import java.io.InputStream;
import net.minecraft.nbt.CompressedStreamTools;
import java.io.FileInputStream;
import java.io.File;
import net.minecraftforge.fml.client.FMLClientHandler;
import com.krazzzzymonkey.catalyst.gui.GuiCustom;

public class ActionLoadWorld implements IAction
{
    String dirName;
    String saveName;
    
    public ActionLoadWorld(final String dirName, final String saveName) {
        this.dirName = dirName;
        this.saveName = saveName;
    }
    
    @Override
    public void perform(final Object source, final GuiCustom menu) {
        final File dir = new File(FMLClientHandler.instance().getSavesDir(), this.dirName);
        NBTTagCompound leveldat;
        try {
            leveldat = CompressedStreamTools.readCompressed((InputStream)new FileInputStream(new File(dir, "level.dat")));
        }
        catch (Exception e) {
            try {
                leveldat = CompressedStreamTools.readCompressed((InputStream)new FileInputStream(new File(dir, "level.dat_old")));
            }
            catch (Exception e2) {
                FMLLog.warning("There appears to be a problem loading the save %s, both level files are unreadable.", new Object[] { this.dirName });
                return;
            }
        }
        final NBTTagCompound fmlData = leveldat.getCompoundTag("FML");
        if (fmlData.hasKey("ModItemData")) {
            FMLClientHandler.instance().showGuiScreen((Object)new GuiOldSaveLoadConfirm(this.dirName, this.saveName, (GuiScreen)menu));
        }
        else {
            try {
                Minecraft.getMinecraft().launchIntegratedServer(this.dirName, this.saveName, (WorldSettings)null);
            }
            catch (StartupQuery.AbortedException ex) {}
        }
    }
}
