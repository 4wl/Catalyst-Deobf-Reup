/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils.visual;

import net.minecraft.client.Minecraft;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.krazzzzymonkey.catalyst.utils.ChatColor;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class ChatUtils
{
    public static void normalChat(final String s) {
        component((ITextComponent)new TextComponentTranslation(s, new Object[0]));
    }
    
    public static void component(final ITextComponent textComponent) {
        if (Wrapper.INSTANCE.player() != null && Wrapper.INSTANCE.mc().ingameGUI.getChatGUI() != null) {
            Wrapper.INSTANCE.mc().ingameGUI.getChatGUI().printChatMessage(new TextComponentTranslation("", new Object[0]).appendSibling(textComponent));
        }
    }
    
    public static void normalMessage(final String str) {
        component((ITextComponent)new TextComponentTranslation("§8[" + ChatColor.AQUA + "Catalyst" + "§8]§7 " + str, new Object[0]));
    }
    
    public static void error(final String str) {
        message("§8[§4ERROR§8]§c " + str);
    }
    
    public static void warning(final String str) {
        message("§8[§eWARNING§8]§e " + str);
    }
    
    public static void timeStampedChat(final String str) {
        component((ITextComponent)new TextComponentTranslation("§d<" + new SimpleDateFormat("HH:mm").format(new Date()) + ">§r" + str, new Object[0]));
    }
    
    public static void message(final String str) {
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentTranslation("§8[" + ChatColor.AQUA + "Catalyst" + "§8]§7 " + str, new Object[0]), 582956);
    }
}
