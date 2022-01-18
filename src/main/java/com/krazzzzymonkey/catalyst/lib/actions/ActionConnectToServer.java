/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.actions;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraft.client.multiplayer.ServerData;
import com.krazzzzymonkey.catalyst.gui.GuiCustom;

public class ActionConnectToServer implements IAction
{
    String ip;
    String serverName;
    
    public ActionConnectToServer(final String ip, final String serverName) {
        this.ip = ip;
        this.serverName = serverName;
    }
    
    @Override
    public void perform(final Object source, final GuiCustom menu) {
        final ServerData serverData = new ServerData(this.serverName, this.ip, false);
        FMLClientHandler.instance().setupServerList();
        FMLClientHandler.instance().connectToServer((GuiScreen)menu, serverData);
    }
}
