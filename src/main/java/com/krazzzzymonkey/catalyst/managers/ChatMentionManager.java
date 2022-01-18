/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers;

import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import java.util.ArrayList;

public class ChatMentionManager
{
    public static ArrayList<String> mentionList;
    
    public static void addMention(final String str) {
        if (!ChatMentionManager.mentionList.contains(str)) {
            ChatMentionManager.mentionList.add(str);
            FileManager.saveChatMention();
            ChatUtils.message(str + " Added to §bmention §7list.");
        }
    }
    
    public static void removeMention(final String str) {
        if (ChatMentionManager.mentionList.contains(str)) {
            ChatMentionManager.mentionList.remove(str + " ");
            FileManager.saveChatMention();
            ChatUtils.message(str + " Removed from §bmention §7list.");
        }
    }
    
    public static void clear() {
        if (!ChatMentionManager.mentionList.isEmpty()) {
            ChatMentionManager.mentionList.clear();
            FileManager.saveChatMention();
            ChatUtils.message("§bMention §7list cleared.");
        }
    }
    
    static {
        ChatMentionManager.mentionList = new ArrayList<String>();
    }
}
