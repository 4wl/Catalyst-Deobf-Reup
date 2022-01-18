/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils.system;

import io.netty.channel.ChannelPromise;
import io.netty.channel.ChannelHandlerContext;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import io.netty.channel.ChannelHandler;
import com.krazzzzymonkey.catalyst.EventsHandler;
import io.netty.channel.ChannelDuplexHandler;

public class Connection extends ChannelDuplexHandler
{
    public EventsHandler eventHandler;
    
    public Connection(final EventsHandler eventHandler) {
        this.eventHandler = eventHandler;
        try {
            Wrapper.INSTANCE.mc().getConnection().getNetworkManager().channel().pipeline().addBefore("packet_handler", "PacketHandler", (ChannelHandler)this);
        }
        catch (Exception ex) {
            ChatUtils.error("Connection: Error on attaching");
            ex.printStackTrace();
        }
    }
    
    public void channelRead(final ChannelHandlerContext channelHandlerContext, final Object o) {
        if (!this.eventHandler.onPacket(o, Connection$Side.IN)) {
            return;
        }
        super.channelRead(channelHandlerContext, o);
    }
    
    public void write(final ChannelHandlerContext channelHandlerContext, final Object o, final ChannelPromise channelPromise) {
        if (!this.eventHandler.onPacket(o, Connection$Side.OUT)) {
            return;
        }
        super.write(channelHandlerContext, o, channelPromise);
    }
}
