/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.client.network.NetworkPlayerInfo;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import java.util.ArrayList;

public class PlayerFinder extends Command
{
    @Override
    public String getDescription() {
        return "Get list of players.";
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        String string;
        try {
            final ArrayList<String> list = new ArrayList<String>();
            if (array[0].equalsIgnoreCase("all")) {
                final Iterator<NetworkPlayerInfo> iterator = (Iterator<NetworkPlayerInfo>)Wrapper.INSTANCE.mc().getConnection().getPlayerInfoMap().iterator();
                while (iterator.hasNext()) {
                    list.add("\n" + iterator.next().getGameProfile().getName());
                }
            }
            else if (array[0].equalsIgnoreCase("creatives")) {
                for (final NetworkPlayerInfo networkPlayerInfo : Wrapper.INSTANCE.mc().getConnection().getPlayerInfoMap()) {
                    if (networkPlayerInfo.getGameType().isCreative()) {
                        list.add("\n" + networkPlayerInfo.getGameProfile().getName());
                    }
                }
            }
            if (list.isEmpty()) {
                ChatUtils.error("List is empty.");
            }
            else {
                Wrapper.INSTANCE.copy(list.toString());
                ChatUtils.message("List copied to clipboard.");
            }
            return;
        }
        catch (Exception ex) {
            string = "Usage: " + this.getSyntax();
        }
        ChatUtils.error(string);
    }
    
    @Override
    public String getSyntax() {
        return "pfind <all/creatives>";
    }
    
    public PlayerFinder() {
        super("pfind");
    }
}
