/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import com.krazzzzymonkey.catalyst.utils.ChatColor;
import com.krazzzzymonkey.catalyst.module.modules.render.TabFriends;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import net.minecraft.scoreboard.Team;
import net.minecraft.scoreboard.ScorePlayerTeam;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiPlayerTabOverlay.class })
public class MixinGuiPlayerTabOverlay
{
    @Inject(method = { "getPlayerName" }, at = { @At("HEAD") }, cancellable = true)
    public void getPlayerNameHead(final NetworkPlayerInfo networkPlayerInfoIn, final CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(this.getPlayerNameGS(networkPlayerInfoIn));
    }
    
    private String getPlayerNameGS(final NetworkPlayerInfo networkPlayerInfoIn) {
        final String displayName = (networkPlayerInfoIn.getDisplayName() != null) ? networkPlayerInfoIn.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName((Team)networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
        if (FriendManager.friendsList.contains(displayName) && ModuleManager.getModule("TabFriends").isToggled()) {
            return (TabFriends.prefix.getValue() ? (ChatColor.GRAY + "[" + TabFriends.color + "F" + ChatColor.GRAY + "] ") : "") + TabFriends.color + displayName;
        }
        return displayName;
    }
}
