/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.chat;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import java.util.Random;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ChatSuffix extends Modules
{
    public BooleanValue addChars;
    public static String[] deco;
    public BooleanValue blueText;
    
    public String getRandomStringFromArray(final String[] array) {
        return array[new Random().nextInt(array.length)];
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        final Packet<?> packet = packetEvent$Out.packet;
        if (packet instanceof CPacketChatMessage) {
            final String randomStringFromArray = this.getRandomStringFromArray(ChatSuffix.deco);
            final CPacketChatMessage cPacketChatMessage = (CPacketChatMessage)packet;
            if (!cPacketChatMessage.getMessage().startsWith("/")) {
                String message;
                if (this.addChars.getValue()) {
                    message = cPacketChatMessage.getMessage().concat("   " + randomStringFromArray + " \u1d04\u1d00\u1d1b\u1d00\u029f\u028f\ua731\u1d1b " + randomStringFromArray);
                }
                else {
                    message = cPacketChatMessage.getMessage().concat(" \u1d04\u1d00\u1d1b\u1d00\u029f\u028f\ua731\u1d1b ");
                }
                Label_0571: {
                    if (this.blueText.getValue()) {
                        if (this.addChars.getValue()) {
                            final String message2 = cPacketChatMessage.getMessage();
                            final StringBuilder append = new StringBuilder().append("   `").append(randomStringFromArray).append(" \u1d04\u1d00\u1d1b\u1d00\u029f\u028f\ua731\u1d1b ");
                            while (true) {
                                switch (-1532495045 - 1739096232 + 1) {
                                    case -1131871549: {
                                        continue;
                                    }
                                    default: {
                                        message = message2.concat(append.append(randomStringFromArray).toString());
                                        break Label_0571;
                                    }
                                    case -197291450: {
                                        throw null;
                                    }
                                }
                            }
                        }
                        else {
                            message = cPacketChatMessage.getMessage().concat("` \u1d04\u1d00\u1d1b\u1d00\u029f\u028f\ua731\u1d1b ");
                        }
                    }
                }
                cPacketChatMessage.message = message;
            }
        }
    }
    
    static {
        ChatSuffix.deco = new String[] { "\u2622", "\u2623", "\u2620", "\u26a0", "\u2624", "\u269a", "\u2020", "\u262f", "\u262e", "\u2698", "\u271e", "\u271f", "\u2727", "\u2661", "\u2665", "\u2764", "\u266b", "\u266a", "\u2655", "\u265b", "\u2656", "\u265c", "\u2601", "\u2708" };
    }
    
    public ChatSuffix() {
        super("ChatSuffix", ModuleCategory.CHAT, "Adds client name at the end of a chat message");
        this.addChars = new BooleanValue("AddChars", true);
        this.blueText = new BooleanValue("BlueName", false);
        this.addValue(this.addChars, this.blueText);
    }
}
