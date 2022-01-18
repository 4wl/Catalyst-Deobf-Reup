/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import java.util.Iterator;
import org.lwjgl.input.Keyboard;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;

public class Bind extends Command
{
    @Override
    public String getDescription() {
        return "Change keybind for specific module.";
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        if (array[0] == null || array[1] == null) {
            ChatUtils.error("Usage: " + this.getSyntax());
            return;
        }
        String string;
        try {
            for (final Modules modules : ModuleManager.getModules()) {
                if (modules.getName().equalsIgnoreCase(array[0])) {
                    modules.setKey(Keyboard.getKeyIndex(array[1].toUpperCase()));
                    ChatUtils.normalMessage(modules.getName() + " keybind changed to §a" + Keyboard.getKeyName(modules.getKey()));
                }
            }
            return;
        }
        catch (Exception ex) {
            string = "Usage: " + this.getSyntax();
        }
        ChatUtils.error(string);
    }
    
    public Bind() {
        super("bind");
    }
    
    @Override
    public String getSyntax() {
        return "bind <module> <key>";
    }
}
