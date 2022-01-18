/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst;

import club.minnced.discord.rpc.DiscordEventHandlers;
import net.minecraft.client.Minecraft;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class CatalystRPC
{
    public static DiscordRichPresence presence;
    public static DiscordRPC rpc;
    public static Minecraft mc;
    public static String state;
    public static String details;
    public static String ClientId;
    
    static {
        CatalystRPC.ClientId = "714569481409527838";
        CatalystRPC.mc = Minecraft.getMinecraft();
        CatalystRPC.rpc = DiscordRPC.INSTANCE;
        CatalystRPC.presence = new DiscordRichPresence();
    }
    
    public static void lambda$init$1() {
        while (!Thread.currentThread().isInterrupted()) {
            Label_0438: {
                Exception ex;
                try {
                    CatalystRPC.rpc.Discord_RunCallbacks();
                    CatalystRPC.details = "Version 1.9.11";
                    CatalystRPC.state = "";
                    if (CatalystRPC.mc.isIntegratedServerRunning()) {
                        CatalystRPC.state = "Playing Singleplayer";
                    }
                    else if (CatalystRPC.mc.getCurrentServerData() != null) {
                        if (!CatalystRPC.mc.getCurrentServerData().serverIP.equals("")) {
                            CatalystRPC.state = "Playing " + CatalystRPC.mc.getCurrentServerData().serverIP;
                        }
                    }
                    else {
                        CatalystRPC.state = "Main Menu";
                    }
                    if (!CatalystRPC.details.equals(CatalystRPC.presence.details) || !CatalystRPC.state.equals(CatalystRPC.presence.state)) {
                        CatalystRPC.presence.startTimestamp = System.currentTimeMillis() / 1000L;
                    }
                    CatalystRPC.presence.details = CatalystRPC.details;
                    CatalystRPC.presence.state = CatalystRPC.state;
                    CatalystRPC.rpc.Discord_UpdatePresence(CatalystRPC.presence);
                    break Label_0438;
                }
                catch (Exception ex2) {
                    ex = ex2;
                }
                ex.printStackTrace();
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException ex3) {
                    ex3.printStackTrace();
                }
            }
        }
    }
    
    public static void init() {
        final DiscordEventHandlers discordEventHandlers = new DiscordEventHandlers();
        discordEventHandlers.disconnected = CatalystRPC::lambda$init$0;
        CatalystRPC.rpc.Discord_Initialize("714569481409527838", discordEventHandlers, true, "");
        CatalystRPC.presence.startTimestamp = System.currentTimeMillis() / 1000L;
        CatalystRPC.presence.details = "Version 1.9.11";
        CatalystRPC.presence.state = "Catalyst Main Menu";
        CatalystRPC.presence.largeImageKey = "logo_with_name";
        final DiscordRichPresence presence = CatalystRPC.presence;
        final String largeImageText = "Catalyst Client";
        while (true) {
            switch (955881635 - 979752798 + 1) {
                case -1239853182: {
                    continue;
                }
                case -43798527: {
                    presence.largeImageText = largeImageText;
                    CatalystRPC.rpc.Discord_UpdatePresence(CatalystRPC.presence);
                    new Thread(CatalystRPC::lambda$init$1, "Discord-RPC-Callback-Handler").start();
                }
                default: {
                    throw null;
                }
            }
        }
    }
    
    public static void lambda$init$0(final int i, final String str) {
        Main.logger.info("Discord RPC disconnected, var1: " + String.valueOf(i) + ", var2: " + str);
    }
}
