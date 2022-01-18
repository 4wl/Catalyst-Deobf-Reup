/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.managers.ChatMentionManager;

public class ChatMention extends Command
{
    @Override
    public String getDescription() {
        return "Chat Mention Manager.";
    }
    
    @Override
    public String getSyntax() {
        return "chatmention <add/remove/clear> <word>";
    }
    
    public ChatMention() {
        super("chatmention");
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        String string;
        try {
            if (array[0].equalsIgnoreCase("add")) {
                ChatMentionManager.addMention(array[1]);
            }
            else if (array[0].equalsIgnoreCase("remove")) {
                ChatMentionManager.removeMention(array[1]);
            }
            else if (array[0].equalsIgnoreCase("clear")) {
                ChatMentionManager.clear();
            }
            return;
        }
        catch (Exception ex) {
            string = "Usage: " + this.getSyntax();
        }
        ChatUtils.error(string);
    }
}
