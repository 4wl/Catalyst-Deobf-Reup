/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.entity.passive.EntityLlama;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.google.common.collect.Lists;
import java.util.UUID;
import java.util.List;
import com.krazzzzymonkey.catalyst.module.Modules;

public class DonkeyFinder extends Modules
{
    public static List<UUID> knownEntities;
    
    @Override
    public void onEnable() {
        DonkeyFinder.knownEntities = (List<UUID>)Lists.newArrayList();
        super.onEnable();
    }
    
    @Override
    public void onDisable() {
        DonkeyFinder.knownEntities = (List<UUID>)Lists.newArrayList();
        super.onDisable();
    }
    
    public DonkeyFinder() {
        super("DonkeyFinder", ModuleCategory.MISC, "Tells you when a donkey is in render distance");
    }
    
    static {
        DonkeyFinder.knownEntities = (List<UUID>)Lists.newArrayList();
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null) {
                if (Minecraft.getMinecraft().player.getUniqueID() != null) {
                    for (final Entity entity : Minecraft.getMinecraft().world.getLoadedEntityList()) {
                        if (entity instanceof EntityDonkey) {
                            if (DonkeyFinder.knownEntities.contains(entity.getUniqueID())) {
                                return;
                            }
                            DonkeyFinder.knownEntities.add(entity.getUniqueID());
                            ChatUtils.message("There is a donkey at: " + Math.round(entity.posX) + " " + Math.round(entity.posY) + " " + Math.round(entity.posZ));
                        }
                    }
                    for (final Entity entity2 : Minecraft.getMinecraft().world.getLoadedEntityList()) {
                        if (entity2 instanceof EntityLlama) {
                            if (DonkeyFinder.knownEntities.contains(entity2.getUniqueID())) {
                                return;
                            }
                            DonkeyFinder.knownEntities.add(entity2.getUniqueID());
                            ChatUtils.message("There is a llama at: " + Math.round(entity2.posX) + " " + Math.round(entity2.posY) + " " + Math.round(entity2.posZ));
                        }
                    }
                }
            }
        }
    }
}
