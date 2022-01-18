/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import org.json.simple.parser.ParseException;
import java.io.IOException;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.apache.commons.io.IOUtils;
import java.net.URL;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import java.util.UUID;
import java.util.HashMap;
import com.krazzzzymonkey.catalyst.module.Modules;

public class MobOwner extends Modules
{
    public static HashMap<UUID, String> knownEntities;
    
    public MobOwner() {
        super("MobOwner", ModuleCategory.RENDER, "Renders the name of the owner above tamed mobs");
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (entity instanceof EntityTameable) {
                final EntityTameable entityTameable = (EntityTameable)entity;
                if (entityTameable.isTamed() && entityTameable.getOwner() != null) {
                    entityTameable.setAlwaysRenderNameTag(true);
                    entityTameable.setCustomNameTag("Owner: " + entityTameable.getOwner().getDisplayName().getFormattedText());
                }
            }
            if (entity instanceof AbstractHorse) {
                final AbstractHorse abstractHorse = (AbstractHorse)entity;
                if (!abstractHorse.isTame()) {
                    continue;
                }
                if (abstractHorse.getOwnerUniqueId() == null) {
                    continue;
                }
                if (!MobOwner.knownEntities.containsKey(abstractHorse.getOwnerUniqueId())) {
                    MobOwner.knownEntities.put(abstractHorse.getOwnerUniqueId(), this.getNameFromUUID(abstractHorse.getOwnerUniqueId()));
                }
                abstractHorse.setAlwaysRenderNameTag(true);
                try {
                    abstractHorse.setCustomNameTag("Owner: " + MobOwner.knownEntities.get(abstractHorse.getOwnerUniqueId()));
                }
                catch (Exception ex) {}
            }
        }
    }
    
    @Override
    public void onDisable() {
        for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (entity instanceof AbstractHorse || (entity instanceof EntityTameable && entity.getCustomNameTag().contains("Owner:"))) {
                entity.setAlwaysRenderNameTag(false);
                entity.setCustomNameTag("");
            }
        }
        super.onDisable();
    }
    
    public String getNameFromUUID(final UUID uuid) {
        final String url = "https://api.mojang.com/user/profiles/" + uuid.toString().replace("-", "") + "/names";
        try {
            final String nameJson = IOUtils.toString(new URL(url));
            final JSONArray nameValue = (JSONArray)JSONValue.parseWithException(nameJson);
            final String playerSlot = nameValue.get(nameValue.size() - 1).toString();
            final JSONObject nameObject = (JSONObject)JSONValue.parseWithException(playerSlot);
            return nameObject.get("name").toString();
        }
        catch (IOException | ParseException ex2) {
            final Exception ex;
            final Exception e = ex;
            ChatUtils.warning("Could not connect to Mojang API! Unable to fetch player name for mob owner");
            e.printStackTrace();
            return null;
        }
    }
    
    static {
        MobOwner.knownEntities = new HashMap<UUID, String>();
    }
}
