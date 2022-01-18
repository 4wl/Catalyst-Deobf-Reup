/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers;

import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import java.util.ArrayList;

public class EnemyManager
{
    public static ArrayList<String> detects;
    public static ArrayList<String> enemysList;
    public static ArrayList<String> murders;
    
    static {
        EnemyManager.enemysList = new ArrayList<String>();
        EnemyManager.murders = new ArrayList<String>();
        EnemyManager.detects = new ArrayList<String>();
    }
    
    public static void clear() {
        if (!EnemyManager.enemysList.isEmpty()) {
            EnemyManager.enemysList.clear();
            FileManager.saveEnemys();
            ChatUtils.message("§cEnemys §7list clear.");
        }
    }
    
    public static void addEnemy(final String str) {
        if (!EnemyManager.enemysList.contains(str)) {
            EnemyManager.enemysList.add(str);
            FileManager.saveEnemys();
            ChatUtils.message(str + " Added to §cenemys §7list.");
        }
    }
    
    public static void removeEnemy(final String str) {
        if (EnemyManager.enemysList.contains(str)) {
            EnemyManager.enemysList.remove(str);
            FileManager.saveEnemys();
            ChatUtils.message(str + " Removed from §cenemys §7list.");
        }
    }
}
