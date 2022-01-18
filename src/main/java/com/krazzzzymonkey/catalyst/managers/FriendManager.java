/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers;

import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import java.util.ArrayList;

public class FriendManager
{
    public static ArrayList<String> friendsList;
    
    static {
        FriendManager.friendsList = new ArrayList<String>();
    }
    
    public static void clear() {
        if (!FriendManager.friendsList.isEmpty()) {
            FriendManager.friendsList.clear();
            FileManager.saveFriends();
            ChatUtils.message("§bFriends §7list cleared.");
        }
    }
    
    public static void addFriend(final String str) {
        if (!FriendManager.friendsList.contains(str)) {
            FriendManager.friendsList.add(str);
            FileManager.saveFriends();
            ChatUtils.message(str + " has been §aadded§7 to friend list!");
        }
    }
    
    public static void removeFriend(final String str) {
        if (FriendManager.friendsList.contains(str)) {
            FriendManager.friendsList.remove(str);
            FileManager.saveFriends();
            ChatUtils.message(str + " has been §cremoved§7 from friend list!");
        }
    }
}
