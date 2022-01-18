/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;

public class Say extends Command
{
    public Say() {
        super("say");
    }
    
    @Override
    public String getSyntax() {
        return "say <message>";
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        String string;
        try {
            final StringBuilder sb = new StringBuilder();
            for (int length = array.length, i = 0; i < length; ++i) {
                sb.append(" ").append(array[i]);
            }
            Wrapper.INSTANCE.sendPacket((Packet)new CPacketChatMessage(sb.toString()));
            return;
        }
        catch (Exception ex) {
            string = "Usage: " + this.getSyntax();
        }
        ChatUtils.error(string);
    }
    
    @Override
    public String getDescription() {
        return "Send message to chat.";
    }
}
