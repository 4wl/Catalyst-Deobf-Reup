/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;

public class Module extends Command
{
    @Override
    public String getSyntax() {
        return "modules";
    }
    
    public Module() {
        super("modules");
    }
    
    @Override
    public String getDescription() {
        return "Lists all hacks.";
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        for (final Modules modules : ModuleManager.getModules()) {
            ChatUtils.normalMessage(String.format("%s §a| §f%s §a| §f%s §a| §f%s", modules.getName(), modules.getCategory(), modules.getKey(), modules.isToggled()));
        }
        ChatUtils.normalChat("Loaded " + ModuleManager.getModules().size() + " Modules.");
    }
}
