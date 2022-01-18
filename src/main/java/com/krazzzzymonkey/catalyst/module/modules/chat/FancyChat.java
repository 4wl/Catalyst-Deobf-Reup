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

public class FancyChat extends Modules
{
    public ModeValue mode;
    
    public String toSmoothUnicode(final String s) {
        return s.replace("a", "\uff41").replace("b", "\uff42").replace("c", "\uff43").replace("d", "\uff44").replace("e", "\uff45").replace("f", "\uff46").replace("g", "\uff47").replace("h", "\uff48").replace("i", "\uff49").replace("j", "\uff4a").replace("k", "\uff4b").replace("l", "\uff4c").replace("m", "\uff4d").replace("n", "\uff4e").replace("o", "\uff4f").replace("p", "\uff50").replace("q", "\uff51").replace("r", "\uff52").replace("s", "\uff53").replace("t", "\uff54").replace("u", "\uff55").replace("v", "\uff56").replace("w", "\uff57").replace("x", "\uff58").replace("y", "\uff59").replace("z", "\uff5a").replace("A", "\uff21").replace("B", "\uff22").replace("C", "\uff23").replace("D", "\uff24").replace("E", "\uff25").replace("F", "\uff26").replace("G", "\uff27").replace("H", "\uff28").replace("I", "\uff29").replace("J", "\uff2a").replace("K", "\uff2b").replace("L", "\uff2c").replace("M", "\uff2d").replace("N", "\uff2e").replace("O", "\uff2f").replace("P", "\uff30").replace("Q", "\uff31").replace("R", "\uff32").replace("S", "\uff33").replace("T", "\uff34").replace("U", "\uff35").replace("V", "\uff36").replace("W", "\uff37").replace("X", "\uff38").replace("Y", "\uff39").replace("Z", "\uff3a").replace("1", "\uff11").replace("2", "\uff12").replace("3", "\uff13").replace("4", "\uff14").replace("5", "\uff15").replace("6", "\uff16").replace("7", "\uff17").replace("8", "\uff18").replace("9", "\uff19").replace("0", "\uff10");
    }
    
    public FancyChat() {
        super("FancyChat", ModuleCategory.CHAT, "Converts chat messages into unicode fonts");
        this.mode = new ModeValue("Mode", new Mode[] { new Mode("Smooth", true), new Mode("Circle", false) });
        this.addValue(this.mode);
    }
    
    public String toCircle(final String s) {
        return s.replace("a", "\u24d0").replace("b", "\u24d1").replace("c", "\u24d2").replace("d", "\u24d3").replace("e", "\u24d4").replace("f", "\u24d5").replace("g", "\u24d6").replace("h", "\u24d7").replace("i", "\u24d8").replace("j", "\u24d9").replace("k", "\u24da").replace("l", "\u24db").replace("m", "\u24dc").replace("n", "\u24dd").replace("o", "\u24de").replace("p", "\u24df").replace("q", "\u24e0").replace("r", "\u24e1").replace("s", "\u24e2").replace("t", "\u24e3").replace("u", "\u24e4").replace("v", "\u24e5").replace("w", "\u24e6").replace("x", "\u24e7").replace("y", "\u24e8").replace("z", "\u24e9").replace("A", "\u24b6").replace("B", "\u24b7").replace("C", "\u24b8").replace("D", "\u24b9").replace("E", "\u24ba").replace("F", "\u24bb").replace("G", "\u24bc").replace("H", "\u24bd").replace("I", "\u24be").replace("J", "\u24bf").replace("K", "\u24da").replace("L", "\u24db").replace("M", "\u24dc").replace("N", "\u24dd").replace("O", "\u24de").replace("P", "\u24df").replace("Q", "\u24c6").replace("R", "\u24c7").replace("S", "\u24c8").replace("T", "\u24c9").replace("U", "\u24ca").replace("V", "\u24cb").replace("W", "\u24cc").replace("X", "\u24cd").replace("Y", "\u24ce").replace("Z", "\u24cf");
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        final Packet<?> packet = packetEvent$Out.packet;
        if (packet instanceof CPacketChatMessage) {
            final CPacketChatMessage cPacketChatMessage = (CPacketChatMessage)packet;
            if (!cPacketChatMessage.getMessage().startsWith("/")) {
                final String message = cPacketChatMessage.getMessage();
                if (this.mode.getMode("Smooth").isToggled()) {
                    cPacketChatMessage.message = this.toSmoothUnicode(message);
                }
                if (this.mode.getMode("Circle").isToggled()) {
                    cPacketChatMessage.message = this.toCircle(message);
                }
            }
        }
    }
}
