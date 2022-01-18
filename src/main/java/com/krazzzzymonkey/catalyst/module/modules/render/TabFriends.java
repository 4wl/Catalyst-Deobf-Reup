/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class TabFriends extends Modules
{
    public static BooleanValue prefix;
    public ModeValue friendColor;
    public static String color;
    
    public TabFriends() {
        super("TabFriends", ModuleCategory.RENDER, "Renders your friends differently in the tablist");
        TabFriends.prefix = new BooleanValue("Prefix", true);
        this.friendColor = new ModeValue("FriendColor", new Mode[] { new Mode("DarkRed", false), new Mode("Red", false), new Mode("Gold", false), new Mode("Yellow", false), new Mode("DarkGreen", false), new Mode("Green", false), new Mode("Aqua", true), new Mode("DarkAqua", false), new Mode("DarkBlue", false), new Mode("Blue", false), new Mode("LightPurple", false), new Mode("DarkPurple", false), new Mode("DarkGray", false), new Mode("Gray", false), new Mode("Black", false) });
        this.addValue(TabFriends.prefix, this.friendColor);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (this.friendColor.getMode("DarkRed").isToggled()) {
            TabFriends.color = "§4";
        }
        else if (this.friendColor.getMode("Red").isToggled()) {
            TabFriends.color = "§c";
        }
        else if (this.friendColor.getMode("Gold").isToggled()) {
            TabFriends.color = "§6";
        }
        else if (this.friendColor.getMode("Yellow").isToggled()) {
            TabFriends.color = "§e";
        }
        else if (this.friendColor.getMode("DarkGreen").isToggled()) {
            TabFriends.color = "§2";
        }
        else if (this.friendColor.getMode("Green").isToggled()) {
            TabFriends.color = "§a";
        }
        else if (this.friendColor.getMode("Aqua").isToggled()) {
            TabFriends.color = "§b";
        }
        else if (this.friendColor.getMode("DarkAqua").isToggled()) {
            TabFriends.color = "§3";
        }
        else if (this.friendColor.getMode("DarkBlue").isToggled()) {
            TabFriends.color = "§1";
        }
        else if (this.friendColor.getMode("Blue").isToggled()) {
            TabFriends.color = "§9";
        }
        else if (this.friendColor.getMode("LightPurple").isToggled()) {
            TabFriends.color = "§d";
        }
        else if (this.friendColor.getMode("DarkPurple").isToggled()) {
            TabFriends.color = "§5";
        }
        else if (this.friendColor.getMode("Gray").isToggled()) {
            TabFriends.color = "§7";
        }
        else if (this.friendColor.getMode("DarkGray").isToggled()) {
            TabFriends.color = "§8";
        }
        else if (this.friendColor.getMode("Black").isToggled()) {
            TabFriends.color = "§0";
        }
    }
    
    static {
        TabFriends.color = "";
    }
}
