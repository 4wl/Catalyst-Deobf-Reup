/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import com.google.common.collect.Lists;
import java.util.Collection;
import com.krazzzzymonkey.catalyst.Main;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.client.network.NetworkPlayerInfo;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.utils.LogOutSpot;
import java.util.List;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class LogoutSpots extends Modules
{
    public static BooleanValue friendRainbow;
    public static int cachePlayerCount;
    public ColorValue friendColor;
    public static BooleanValue rainbow;
    public static List<LogOutSpot> logoutPositions;
    public ColorValue color;
    public static BooleanValue coordinates;
    public static ArrayList j;
    public static ArrayList<NetworkPlayerInfo> playerMap;
    public boolean isOnServer;
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        for (final LogOutSpot logOutSpot : LogoutSpots.logoutPositions) {
            if (FriendManager.friendsList.contains(logOutSpot.name)) {
                this.renderLogoutSpot(logOutSpot, LogoutSpots.friendRainbow, this.friendColor);
            }
            else {
                this.renderLogoutSpot(logOutSpot, LogoutSpots.rainbow, this.color);
            }
        }
    }
    
    public static void drawNametag(final double n, final double n2, final double n3, final String s, final int n4) {
        final double distance = Minecraft.getMinecraft().player.getDistance(n, n2, n3);
        final double n5 = 0.0;
        double n6 = -(int)distance / 6.0;
        if (n6 < 1.0) {
            n6 = 1.0;
        }
        final double n7 = n6 * 0.02666666666666667;
        GlStateManager.pushMatrix();
        GlStateManager.translate(n - Minecraft.getMinecraft().getRenderManager().viewerPosX, n2 + n5 - Minecraft.getMinecraft().getRenderManager().viewerPosY, n3 - Minecraft.getMinecraft().getRenderManager().viewerPosZ);
        GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, (Minecraft.getMinecraft().gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n7, -n7, n7);
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(s + "'s Logout Spot", -Main.fontRenderer.getStringWidth(s + "'s Logout Spot") / 2.0f, -40.0f, n4);
        if (LogoutSpots.coordinates.getValue()) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("X: " + n + " Y: " + n2 + " Z: " + n3, -Main.fontRenderer.getStringWidth("X: " + n + " Y: " + n2 + " Z: " + n3) / 2.0f, -30.0f, n4);
        }
        GlStateManager.enableDepth();
        while (true) {
            switch (-206180469 + 2033905543 + 1) {
                case -233117047: {
                    continue;
                }
                default: {
                    GlStateManager.disableTexture2D();
                    GlStateManager.popMatrix();
                }
                case -1601761547: {
                    throw null;
                }
            }
        }
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.onJoinServer();
        this.resetArraylist();
    }
    
    public void checkPlayers() {
        final ArrayList<Object> c = new ArrayList<Object>(Minecraft.getMinecraft().getConnection().getPlayerInfoMap());
        if (c.size() != LogoutSpots.cachePlayerCount) {
            final ArrayList list = (ArrayList)c.clone();
            list.removeAll(LogoutSpots.playerMap);
            if (list.size() > 5) {
                LogoutSpots.cachePlayerCount = LogoutSpots.playerMap.size();
                this.onJoinServer();
                return;
            }
            final ArrayList list2 = (ArrayList)LogoutSpots.playerMap.clone();
            list2.removeAll(c);
            final Iterator<NetworkPlayerInfo> iterator = list2.iterator();
            while (iterator.hasNext()) {
                this.playerLeft(iterator.next());
            }
            final Iterator<NetworkPlayerInfo> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                this.playerJoined(iterator2.next());
            }
            LogoutSpots.cachePlayerCount = LogoutSpots.playerMap.size();
            this.onJoinServer();
        }
    }
    
    static {
        LogoutSpots.playerMap = new ArrayList<NetworkPlayerInfo>();
        LogoutSpots.j = new ArrayList();
        LogoutSpots.logoutPositions = (List<LogOutSpot>)Lists.newArrayList();
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.resetArraylist();
    }
    
    public void playerJoined(final NetworkPlayerInfo networkPlayerInfo) {
        for (int i = 0; i < LogoutSpots.logoutPositions.size(); ++i) {
            if (LogoutSpots.logoutPositions.get(i).name.equals(networkPlayerInfo.getGameProfile().getName()) && !LogoutSpots.logoutPositions.get(i).name.equals(Minecraft.getMinecraft().player.getName())) {
                LogoutSpots.logoutPositions.remove(i);
                --i;
            }
        }
    }
    
    public void playerLeft(final NetworkPlayerInfo networkPlayerInfo) {
        for (final Entity entity : LogoutSpots.j) {
            if (entity instanceof EntityPlayer && entity.getName().equalsIgnoreCase(networkPlayerInfo.getGameProfile().getName()) && !entity.getName().equals(Minecraft.getMinecraft().player.getName())) {
                if (LogoutSpots.coordinates.getValue()) {
                    ChatUtils.message(networkPlayerInfo.getGameProfile().getName() + " has logged out at, x: " + entity.getPosition().getX() + " y: " + entity.getPosition().getY() + " z: " + entity.getPosition().getZ());
                }
                else {
                    ChatUtils.message(networkPlayerInfo.getGameProfile().getName() + " has logged out");
                }
                LogoutSpots.logoutPositions.add(new LogOutSpot(entity.posX, entity.posY, entity.posZ, entity.getName()));
            }
        }
    }
    
    public void resetArraylist() {
        LogoutSpots.j.clear();
        LogoutSpots.j.addAll(Minecraft.getMinecraft().world.getLoadedEntityList());
    }
    
    public LogoutSpots() {
        super("LogoutSpots", ModuleCategory.RENDER, "Shows you where a player has logged out");
        this.color = new ColorValue("Color", Color.RED);
        LogoutSpots.rainbow = new BooleanValue("Rainbow", false);
        this.friendColor = new ColorValue("FriendColor", Color.CYAN);
        LogoutSpots.friendRainbow = new BooleanValue("FriendRainbow", true);
        LogoutSpots.coordinates = new BooleanValue("Coordinates", true);
        this.addValue(this.color, LogoutSpots.rainbow, this.friendColor, LogoutSpots.friendRainbow, LogoutSpots.coordinates);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null) {
                if (Minecraft.getMinecraft().player.getUniqueID() != null) {
                    if (Minecraft.getMinecraft().isSingleplayer()) {
                        return;
                    }
                    if (Minecraft.getMinecraft().player.ticksExisted % 10 == 0) {
                        this.checkPlayers();
                        this.resetArraylist();
                    }
                }
            }
        }
    }
    
    public void renderLogoutSpot(final LogOutSpot logOutSpot, final BooleanValue booleanValue, final ColorValue colorValue) {
        if (booleanValue.getValue()) {
            drawNametag(logOutSpot.x + 0.5, logOutSpot.y + 1.0 + 0.5, logOutSpot.z + 0.5, logOutSpot.name, new Color(ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 1.0f).getRGB());
            RenderUtils.drawLogoutSpot(logOutSpot.name, logOutSpot.x, logOutSpot.y, logOutSpot.z, ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 2.0);
        }
        else {
            drawNametag(logOutSpot.x + 0.5, logOutSpot.y + 1.0 + 0.5, logOutSpot.z + 0.5, logOutSpot.name, new Color(colorValue.getColor().getRed() / 255.0f, colorValue.getColor().getGreen() / 255.0f, colorValue.getColor().getBlue() / 255.0f, 1.0f).getRGB());
            RenderUtils.drawLogoutSpot(logOutSpot.name, logOutSpot.x, logOutSpot.y, logOutSpot.z, colorValue.getColor().getRed() / 255.0f, colorValue.getColor().getGreen() / 255.0f, colorValue.getColor().getBlue() / 255.0f, 2.0);
        }
    }
    
    public void onJoinServer() {
        LogoutSpots.playerMap = new ArrayList<NetworkPlayerInfo>(Minecraft.getMinecraft().getConnection().getPlayerInfoMap());
        LogoutSpots.cachePlayerCount = LogoutSpots.playerMap.size();
        this.isOnServer = true;
    }
}
