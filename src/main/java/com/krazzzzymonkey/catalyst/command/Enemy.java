/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.managers.EnemyManager;
import com.krazzzzymonkey.catalyst.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;

public class Enemy extends Command
{
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
                            EnemyManager.addEnemy(Utils.getPlayerName(entityPlayer));
                        }
                    }
                }
                else {
                    EnemyManager.addEnemy(array[1]);
                }
            }
            else if (array[0].equalsIgnoreCase("remove")) {
                EnemyManager.removeEnemy(array[1]);
            }
            else if (array[0].equalsIgnoreCase("clear")) {
                EnemyManager.clear();
            }
            return;
        }
        catch (Exception ex) {
            string = "Usage: " + this.getSyntax();
        }
        ChatUtils.error(string);
    }
    
    @Override
    public String getDescription() {
        return "Enemy manager.";
    }
    
    @Override
    public String getSyntax() {
        return "enemy <add/remove/clear> <nick>";
    }
    
    public Enemy() {
        super("enemy");
    }
}
