/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils.system;

import net.minecraft.client.Minecraft;

public class Mapping
{
    public static String blockHitDelay;
    public static String getPlayerInfo;
    public static String currentGameType;
    public static String connection;
    public static String yaw;
    public static String pitch;
    public static String session;
    public static String curBlockDamageMP;
    public static String isHittingBlock;
    public static String message;
    public static String onUpdateWalkingPlayer;
    public static String playerTextures;
    
    static {
        Mapping.getPlayerInfo = (isNotObfuscated() ? "getPlayerInfo" : "func_175155_b");
        Mapping.session = (isNotObfuscated() ? "session" : "field_71449_j");
        Mapping.yaw = (isNotObfuscated() ? "yaw" : "field_149476_e");
        Mapping.playerTextures = (isNotObfuscated() ? "playerTextures" : "field_187107_a");
        Mapping.pitch = (isNotObfuscated() ? "pitch" : "field_149473_f");
        Mapping.currentGameType = (isNotObfuscated() ? "currentGameType" : "field_78779_k");
        Mapping.connection = (isNotObfuscated() ? "connection" : "field_78774_b");
        Mapping.blockHitDelay = (isNotObfuscated() ? "blockHitDelay" : "field_78781_i");
        Mapping.message = (isNotObfuscated() ? "message" : "field_149440_a");
        Mapping.curBlockDamageMP = (isNotObfuscated() ? "curBlockDamageMP" : "field_78770_f");
        Mapping.isHittingBlock = (isNotObfuscated() ? "isHittingBlock" : "field_78778_j");
        Mapping.onUpdateWalkingPlayer = (isNotObfuscated() ? "onUpdateWalkingPlayer" : "func_175161_p");
    }
    
    public static boolean isNotObfuscated() {
        try {
            return Minecraft.class.getDeclaredField("instance") != null;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
