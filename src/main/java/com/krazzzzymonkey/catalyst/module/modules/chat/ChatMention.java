/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.managers.ChatMentionManager;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ChatMention extends Modules
{
    public BooleanValue booleanName;
    public static int word;
    public ModeValue nameTextColor;
    public String formatting;
    
    public ChatMention() {
        super("ChatMention", ModuleCategory.CHAT, "Highlights your name and other words in chat");
        this.formatting = "";
        this.booleanName = new BooleanValue("Name", true);
        this.nameTextColor = new ModeValue("MentionColor", new Mode[] { new Mode("Dark Red", true), new Mode("Red", false), new Mode("Gold", false), new Mode("Yellow", false), new Mode("Dark Green", false), new Mode("Green", false), new Mode("Aqua", false), new Mode("Dark Aqua", false), new Mode("Dark Blue", false), new Mode("Blue", false), new Mode("Light Purple", false), new Mode("Dark Purple", false), new Mode("Dark Gray", false), new Mode("Gray", false), new Mode("Black", false) });
        this.addValue(this.booleanName, this.nameTextColor);
    }
    
    public static boolean stringContainsWordFromList(final String s, final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (s.contains(array[i])) {
                ChatMention.word = i;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        ChatUtils.message("To add words to mention list, use command \"chatmention add <word>\" in the click gui text box.");
    }
    
    @SubscribeEvent
    public void onClientChatReceived(final ClientChatReceivedEvent clientChatReceivedEvent) {
        if (this.nameTextColor.getMode("Dark Red").isToggled()) {
            this.formatting = "§4";
        }
        if (this.nameTextColor.getMode("Red").isToggled()) {
            this.formatting = "§c";
        }
        if (this.nameTextColor.getMode("Gold").isToggled()) {
            this.formatting = "§6";
        }
        if (this.nameTextColor.getMode("Yellow").isToggled()) {
            this.formatting = "§e";
        }
        if (this.nameTextColor.getMode("Dark Green").isToggled()) {
            this.formatting = "§2";
        }
        if (this.nameTextColor.getMode("Green").isToggled()) {
            this.formatting = "§a";
        }
        if (this.nameTextColor.getMode("Aqua").isToggled()) {
            this.formatting = "§b";
        }
        if (this.nameTextColor.getMode("Dark Aqua").isToggled()) {
            this.formatting = "§3";
        }
        if (this.nameTextColor.getMode("Dark Blue").isToggled()) {
            this.formatting = "§1";
        }
        if (this.nameTextColor.getMode("Blue").isToggled()) {
            this.formatting = "§9";
        }
        if (this.nameTextColor.getMode("Light Purple").isToggled()) {
            this.formatting = "§d";
        }
        if (this.nameTextColor.getMode("Dark Purple").isToggled()) {
            this.formatting = "§5";
        }
        if (this.nameTextColor.getMode("Gray").isToggled()) {
            this.formatting = "§7";
        }
        if (this.nameTextColor.getMode("Dark Gray").isToggled()) {
            this.formatting = "§8";
        }
        if (this.nameTextColor.getMode("Black").isToggled()) {
            this.formatting = "§0";
        }
        String s = clientChatReceivedEvent.getMessage().getUnformattedText();
        final String[] array = ChatMentionManager.mentionList.toArray(new String[ChatMentionManager.mentionList.size()]);
        final String lowerCase = Minecraft.getMinecraft().player.getName().toLowerCase();
        if (this.booleanName.getValue()) {
            new StringBuilder().append("§6").append(Minecraft.getMinecraft().player.getName()).append("§r").toString();
            clientChatReceivedEvent.getMessage().getStyle();
            if (!clientChatReceivedEvent.getMessage().getUnformattedText().contains("<" + Minecraft.getMinecraft().player.getName() + ">") && clientChatReceivedEvent.getMessage().getUnformattedText().toLowerCase().contains(lowerCase)) {
                clientChatReceivedEvent.setCanceled(true);
                s = s.replace(Minecraft.getMinecraft().player.getName(), this.formatting + Minecraft.getMinecraft().player.getName() + "§r");
                ChatUtils.normalChat(s);
            }
        }
        if (!clientChatReceivedEvent.getMessage().getUnformattedText().contains("<" + Minecraft.getMinecraft().player.getName() + ">")) {
            if ((boolean)this.booleanName.getValue() && s.contains(lowerCase)) {
                return;
            }
            if (stringContainsWordFromList(s, array)) {
                final String s2 = array[ChatMention.word];
                clientChatReceivedEvent.setCanceled(true);
                ChatUtils.normalChat(clientChatReceivedEvent.getMessage().getFormattedText().replace(s2, this.formatting + s2 + "§r"));
            }
        }
    }
}
