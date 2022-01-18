/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.text.TextComponentString;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Date;
import java.text.SimpleDateFormat;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ChatTimeStamps extends Modules
{
    public ModeValue bracketColor;
    public String timeFormatting;
    public ModeValue timeColor;
    public String bracketFormatting;
    
    public ChatTimeStamps() {
        super("ChatTimeStamps", ModuleCategory.CHAT, "Adds timestamps to messages that were sent in chat");
        this.bracketColor = new ModeValue("BracketColor", new Mode[] { new Mode("Dark Red", true), new Mode("Red", false), new Mode("Gold", false), new Mode("Yellow", false), new Mode("Dark Green", false), new Mode("Green", false), new Mode("Aqua", false), new Mode("Dark Aqua", false), new Mode("Dark Blue", false), new Mode("Blue", false), new Mode("Light Purple", false), new Mode("Dark Purple", false), new Mode("Dark Gray", false), new Mode("Gray", false), new Mode("Black", false) });
        this.timeColor = new ModeValue("TimeColor", new Mode[] { new Mode("Dark Red", true), new Mode("Red", false), new Mode("Gold", false), new Mode("Yellow", false), new Mode("Dark Green", false), new Mode("Green", false), new Mode("Aqua", false), new Mode("Dark Aqua", false), new Mode("Dark Blue", false), new Mode("Blue", false), new Mode("Light Purple", false), new Mode("Dark Purple", false), new Mode("Dark Gray", false), new Mode("Gray", false), new Mode("Black", false) });
        this.addValue(this.bracketColor, this.timeColor);
    }
    
    @SubscribeEvent
    public void onClientChatReceived(final ClientChatReceivedEvent clientChatReceivedEvent) {
        if (this.bracketColor.getMode("Dark Red").isToggled()) {
            this.bracketFormatting = "§4";
        }
        if (this.bracketColor.getMode("Red").isToggled()) {
            this.bracketFormatting = "§c";
        }
        if (this.bracketColor.getMode("Gold").isToggled()) {
            this.bracketFormatting = "§6";
        }
        if (this.bracketColor.getMode("Yellow").isToggled()) {
            this.bracketFormatting = "§e";
        }
        if (this.bracketColor.getMode("Dark Green").isToggled()) {
            this.bracketFormatting = "§2";
        }
        if (this.bracketColor.getMode("Green").isToggled()) {
            this.bracketFormatting = "§a";
        }
        if (this.bracketColor.getMode("Aqua").isToggled()) {
            this.bracketFormatting = "§b";
        }
        if (this.bracketColor.getMode("Dark Aqua").isToggled()) {
            this.bracketFormatting = "§3";
        }
        if (this.bracketColor.getMode("Dark Blue").isToggled()) {
            this.bracketFormatting = "§1";
        }
        if (this.bracketColor.getMode("Blue").isToggled()) {
            this.bracketFormatting = "§9";
        }
        if (this.bracketColor.getMode("Light Purple").isToggled()) {
            this.bracketFormatting = "§d";
        }
        if (this.bracketColor.getMode("Dark Purple").isToggled()) {
            this.bracketFormatting = "§5";
        }
        if (this.bracketColor.getMode("Gray").isToggled()) {
            this.bracketFormatting = "§7";
        }
        if (this.bracketColor.getMode("Dark Gray").isToggled()) {
            this.bracketFormatting = "§8";
        }
        if (this.bracketColor.getMode("Black").isToggled()) {
            this.bracketFormatting = "§0";
        }
        if (this.timeColor.getMode("Dark Red").isToggled()) {
            this.timeFormatting = "§4";
        }
        if (this.timeColor.getMode("Red").isToggled()) {
            this.timeFormatting = "§c";
        }
        if (this.timeColor.getMode("Gold").isToggled()) {
            this.timeFormatting = "§6";
        }
        if (this.timeColor.getMode("Yellow").isToggled()) {
            this.timeFormatting = "§e";
        }
        if (this.timeColor.getMode("Dark Green").isToggled()) {
            this.timeFormatting = "§2";
        }
        if (this.timeColor.getMode("Green").isToggled()) {
            this.timeFormatting = "§a";
        }
        if (this.timeColor.getMode("Aqua").isToggled()) {
            this.timeFormatting = "§b";
        }
        if (this.timeColor.getMode("Dark Aqua").isToggled()) {
            this.timeFormatting = "§3";
        }
        if (this.timeColor.getMode("Dark Blue").isToggled()) {
            this.timeFormatting = "§1";
        }
        if (this.timeColor.getMode("Blue").isToggled()) {
            this.timeFormatting = "§9";
        }
        if (this.timeColor.getMode("Light Purple").isToggled()) {
            this.timeFormatting = "§d";
        }
        if (this.timeColor.getMode("Dark Purple").isToggled()) {
            this.timeFormatting = "§5";
        }
        if (this.timeColor.getMode("Gray").isToggled()) {
            this.timeFormatting = "§7";
        }
        if (this.timeColor.getMode("Dark Gray").isToggled()) {
            this.timeFormatting = "§8";
        }
        if (this.timeColor.getMode("Black").isToggled()) {
            this.timeFormatting = "§0";
        }
        clientChatReceivedEvent.setMessage(new TextComponentString(this.bracketFormatting + "<" + this.timeFormatting + new SimpleDateFormat("HH:mm").format(new Date()) + this.bracketFormatting + ">" + ChatFormatting.RESET).appendSibling(clientChatReceivedEvent.getMessage()));
    }
}
