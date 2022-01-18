/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ChatPrefix extends Modules
{
    public ModeValue mode;
    
    public ChatPrefix() {
        super("ChatPrefix", ModuleCategory.CHAT, "Adds prefixes before chat message, Designed for 0b0t.org");
        this.mode = new ModeValue("Mode", new Mode[] { new Mode("Green", true), new Mode("Red", false), new Mode("Orange", false), new Mode("Black", false), new Mode("Gray", false), new Mode("Blue", false), new Mode("Light Blue", false), new Mode("Yellow", false) });
        this.addValue(this.mode);
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        final Packet<?> packet = packetEvent$Out.packet;
        if (packet instanceof CPacketChatMessage) {
            final CPacketChatMessage cPacketChatMessage = (CPacketChatMessage)packet;
            if (!cPacketChatMessage.getMessage().startsWith("/")) {
                if (this.mode.getMode("Green").isToggled()) {
                    cPacketChatMessage.message = ">" + cPacketChatMessage.getMessage();
                }
                if (this.mode.getMode("Red").isToggled()) {
                    cPacketChatMessage.message = "<" + cPacketChatMessage.getMessage();
                }
                if (this.mode.getMode("Orange").isToggled()) {
                    cPacketChatMessage.message = "," + cPacketChatMessage.getMessage();
                }
                if (this.mode.getMode("Black").isToggled()) {
                    cPacketChatMessage.message = "]" + cPacketChatMessage.getMessage();
                }
                if (this.mode.getMode("Gray").isToggled()) {
                    cPacketChatMessage.message = "[" + cPacketChatMessage.getMessage();
                }
                if (this.mode.getMode("Blue").isToggled()) {
                    cPacketChatMessage.message = ";" + cPacketChatMessage.getMessage();
                }
                if (this.mode.getMode("Light Blue").isToggled()) {
                    cPacketChatMessage.message = ":" + cPacketChatMessage.getMessage();
                }
                if (this.mode.getMode("Yellow").isToggled()) {
                    cPacketChatMessage.message = "!" + cPacketChatMessage.getMessage();
                }
            }
        }
    }
}
