/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.utils.ChatColor;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;

public class Drawn extends Command
{
    public Drawn() {
        super("drawn");
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        String string2;
        try {
            if (array.length > 1 || array[0].equals("")) {
                throw new Exception();
            }
            String string;
            try {
                ModuleManager.getModule(array[0]).setDrawn(!ModuleManager.getModule(array[0]).isDrawn());
                ChatUtils.message("Drawn is now " + (ModuleManager.getModule(array[0]).isDrawn() ? (ChatColor.GREEN + "enabled") : (ChatColor.RED + "disabled")) + ChatColor.GRAY + " for " + ChatColor.AQUA + ModuleManager.getModule(array[0]).getName() + ChatColor.GRAY + ".");
                return;
            }
            catch (Exception ex) {
                string = "Unknown Module: " + array[0];
            }
            ChatUtils.error(string);
            return;
        }
        catch (Exception ex2) {
            string2 = "Usage: " + this.getSyntax();
        }
        ChatUtils.error(string2);
    }
    
    @Override
    public String getSyntax() {
        return "drawn <module>";
    }
    
    @Override
    public String getDescription() {
        return "Toggles the drawn setting in a specified module";
    }
}
