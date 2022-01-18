/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import com.krazzzzymonkey.catalyst.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;

public class Friend extends Command
{
    @Override
    public String getDescription() {
        return "Friend manager.";
    }
    
    public Friend() {
        super("friend");
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        String string;
        try {
            if (array[0].equalsIgnoreCase("add")) {
                if (array[1].equalsIgnoreCase("all")) {
                    for (final EntityPlayer next : Wrapper.INSTANCE.world().loadedEntityList) {
                        if (next instanceof EntityPlayer) {
                            final EntityPlayer entityPlayer = next;
                            if (entityPlayer.isInvisible()) {
                                continue;
                            }
                            FriendManager.addFriend(Utils.getPlayerName(entityPlayer));
                        }
                    }
                }
                else {
                    FriendManager.addFriend(array[1]);
                }
            }
            else if (array[0].equalsIgnoreCase("remove")) {
                FriendManager.removeFriend(array[1]);
            }
            else if (array[0].equalsIgnoreCase("clear")) {
                FriendManager.clear();
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
        return "friend <add/remove/clear> <nick>";
    }
}
