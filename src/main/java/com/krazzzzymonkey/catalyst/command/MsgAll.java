/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import java.util.stream.Stream;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import java.util.Collection;
import net.minecraft.client.network.NetHandlerPlayClient;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import net.minecraft.client.Minecraft;
import java.nio.charset.Charset;
import java.util.Random;

public class MsgAll extends Command
{
    public void runThread(final String s, final String[] array) {
        new Thread(MsgAll::lambda$runThread$0).start();
    }
    
    @Override
    public String getDescription() {
        return "Messages all players on the server";
    }
    
    public static String getAlphaNumericString(int n) {
        final byte[] array = new byte[256];
        new Random().nextBytes(array);
        final String s = new String(array, Charset.forName("UTF-8"));
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z') || (char1 >= '0' && char1 <= '9')) && n > 0) {
                sb.append(char1);
                --n;
            }
        }
        return sb.toString();
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        String string;
        try {
            final NetHandlerPlayClient connection = Minecraft.getMinecraft().getConnection();
            if (connection != null) {
                final Collection playerInfoMap = connection.getPlayerInfoMap();
                final GuiPlayerTabOverlay tabList = Minecraft.getMinecraft().ingameGUI.getTabList();
                final Stream<Object> stream = playerInfoMap.stream();
                final GuiPlayerTabOverlay guiPlayerTabOverlay = tabList;
                guiPlayerTabOverlay.getClass();
                final String[] split = stream.map((Function<? super Object, ?>)guiPlayerTabOverlay::func_175243_a).collect((Collector<? super Object, ?, String>)Collectors.joining(", ")).split(",");
                final StringBuilder sb = new StringBuilder();
                for (int length = array.length, i = 0; i < length; ++i) {
                    sb.append(" ").append(array[i]);
                }
                this.runThread(sb.toString(), split);
            }
            return;
        }
        catch (Exception ex) {
            string = "Usage: " + this.getSyntax();
        }
        ChatUtils.error(string);
    }
    
    @Override
    public String getSyntax() {
        return "msgall <message>";
    }
    
    public MsgAll() {
        super("msgall");
    }
    
    public static void lambda$runThread$0(final String[] array, final String s) {
        try {
            for (final String s2 : array) {
                Minecraft.getMinecraft().player.sendChatMessage("/msg " + s2 + " " + s + " " + getAlphaNumericString(5));
                ChatUtils.normalMessage(" Sent: /msg " + s2 + " " + s + " " + getAlphaNumericString(5));
                Thread.sleep(2000L);
            }
        }
        catch (Exception ex) {}
    }
}
