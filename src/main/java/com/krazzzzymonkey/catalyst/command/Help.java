/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.managers.CommandManager;

public class Help extends Command
{
    @Override
    public String getDescription() {
        return "Lists all commands.";
    }
    
    @Override
    public String getSyntax() {
        return "help";
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        for (final Command command : CommandManager.commands) {
            if (command != this) {
                ChatUtils.normalMessage(command.getSyntax().replace("<", "<§a").replace(">", "§7>") + " - " + command.getDescription());
            }
        }
    }
    
    public Help() {
        super("help");
    }
}
