/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.entity.EntityLiving;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.managers.EnemyManager;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import net.minecraft.entity.player.EntityPlayer;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import net.minecraft.entity.EntityLivingBase;

public class ValidUtils
{
    public static boolean isInvisibleKillAura(final EntityLivingBase entityLivingBase) {
        return ModuleManager.getModule("KillAura").isToggledValue("Invisibles") || !entityLivingBase.isInvisible();
    }
    
    public static boolean isFriendEnemyKillAura(final EntityLivingBase entityLivingBase) {
        if (entityLivingBase instanceof EntityPlayer) {
            final String playerName = Utils.getPlayerName((EntityPlayer)entityLivingBase);
            if (FriendManager.friendsList.contains(playerName)) {
                return false;
            }
            final Modules module = ModuleManager.getModule("KillAura");
            if (module.isToggled() && module.isToggledValue("Only Enemies")) {
                return EnemyManager.enemysList.contains(playerName);
            }
        }
        return true;
    }
    
    public static boolean isValidEntity(final EntityLivingBase entityLivingBase) {
        final Modules module = ModuleManager.getModule("Nametags");
        return !module.isToggled() || ((!module.isToggledValue("Players") || !(entityLivingBase instanceof EntityPlayer)) && (!module.isToggledValue("Mobs") || !(entityLivingBase instanceof EntityLiving)));
    }
    
    public static boolean isValidEntityKillAura(final EntityLivingBase entityLivingBase) {
        final Modules module = ModuleManager.getModule("KillAura");
        return !module.isToggled() || ((!module.isToggledValue("Players") || !(entityLivingBase instanceof EntityPlayer)) && (!module.isToggledValue("Mobs") || !(entityLivingBase instanceof EntityLiving)));
    }
    
    public static boolean isValidEntityESP(final EntityLivingBase entityLivingBase) {
        final Modules module = ModuleManager.getModule("ESP");
        return !module.isToggled() || ((!module.isToggledValue("Players") || !(entityLivingBase instanceof EntityPlayer)) && (!module.isToggledValue("Mobs") || !(entityLivingBase instanceof EntityLiving)));
    }
    
    public static boolean isNoScreen() {
        return !ModuleManager.getModule("NoScreenEvents").isToggled() || Utils.checkScreen();
    }
    
    public static boolean isValidEntityTracers(final EntityLivingBase entityLivingBase) {
        final Modules module = ModuleManager.getModule("Tracers");
        if (!module.isToggled()) {
            return true;
        }
        if (module.isToggledValue("Players") && entityLivingBase instanceof EntityPlayer) {
            return false;
        }
        if (module.isToggledValue("Mobs")) {
            if (entityLivingBase instanceof EntityLiving) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isTeam(final EntityLivingBase entityLivingBase) {
        final Modules module = ModuleManager.getModule("Teams");
        if (module.isToggled() && entityLivingBase instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            if (module.isToggledMode("Base") && entityPlayer.getTeam() != null && Wrapper.INSTANCE.player().getTeam() != null && entityPlayer.getTeam().isSameTeam(Wrapper.INSTANCE.player().getTeam())) {
                return false;
            }
            if (module.isToggledMode("ArmorColor") && !Utils.checkEnemyColor(entityPlayer)) {
                return false;
            }
            if (module.isToggledMode("NameColor")) {
                return Utils.checkEnemyNameColor((EntityLivingBase)entityPlayer);
            }
        }
        return true;
    }
    
    public static boolean isInvisible(final EntityLivingBase entityLivingBase) {
        return ModuleManager.getModule("Targets").isToggledValue("Invisibles") || !entityLivingBase.isInvisible();
    }
    
    public static boolean isFriendEnemy(final EntityLivingBase entityLivingBase) {
        if (entityLivingBase instanceof EntityPlayer) {
            final String playerName = Utils.getPlayerName((EntityPlayer)entityLivingBase);
            if (FriendManager.friendsList.contains(playerName)) {
                return false;
            }
            if (ModuleManager.getModule("Enemies").isToggled()) {
                return EnemyManager.enemysList.contains(playerName);
            }
        }
        return true;
    }
}
