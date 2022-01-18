/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.module.modules.render.Breadcrumbs;

public class BreadcrumbsCommand extends Command
{
    @Override
    public String getSyntax() {
        return "breadcrumbs <<add/remove> <player>>/<clear>";
    }
    
    public BreadcrumbsCommand() {
        super("breadcrumbs");
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        if (array[0].equalsIgnoreCase("add")) {
            Breadcrumbs.names.add(array[1].toLowerCase());
            ChatUtils.message("Added " + array[1] + " to the breadcrumb list");
        }
        if (array[0].equalsIgnoreCase("remove")) {
            if (Breadcrumbs.names.contains(array[1].toLowerCase())) {
                ChatUtils.message("Removed " + array[1] + " from the breadcrumb list");
                Breadcrumbs.names.remove(array[1].toLowerCase());
            }
            else {
                ChatUtils.message(array[1] + " is not in the list");
            }
        }
        if (array[0].equalsIgnoreCase("clear")) {
            ChatUtils.message("Cleared the breadcrumb list");
            Breadcrumbs.names.clear();
        }
    }
    
    @Override
    public String getDescription() {
        return "Add/Remove players you want to be traced by Breadcrumbs";
    }
}
