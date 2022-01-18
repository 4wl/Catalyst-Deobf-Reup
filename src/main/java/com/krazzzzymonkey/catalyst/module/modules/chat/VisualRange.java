/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.entity.player.EntityPlayer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import net.minecraft.entity.Entity;
import java.util.List;
import com.krazzzzymonkey.catalyst.module.Modules;

public class VisualRange extends Modules
{
    public List<Entity> players;
    public List<Entity> knownPlayers;
    public BooleanValue clientSide;
    
    @Override
    public void onDisable() {
        this.knownPlayers.clear();
        super.onDisable();
    }
    
    public VisualRange() {
        super("VisualRange", ModuleCategory.CHAT, "Tells you when someone enters your render distance");
        this.knownPlayers = new ArrayList<Entity>();
        this.clientSide = new BooleanValue("ClientSide", true);
        this.addValue(this.clientSide);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        this.players = (List<Entity>)Minecraft.getMinecraft().world.loadedEntityList.stream().filter(VisualRange::lambda$onClientTick$0).collect(Collectors.toList());
        try {
            for (final Entity entity : this.players) {
                if (entity instanceof EntityPlayer && !entity.getName().equalsIgnoreCase(Minecraft.getMinecraft().player.getName()) && !this.knownPlayers.contains(entity)) {
                    this.knownPlayers.add(entity);
                    if (this.clientSide.getValue()) {
                        ChatUtils.message(entity.getName() + " Entered Render Distance.");
                    }
                    else {
                        Minecraft.getMinecraft().player.sendChatMessage(entity.getName() + " Entered My Render Distance.");
                    }
                }
            }
        }
        catch (Exception ex) {}
        try {
            for (final Entity entity2 : this.knownPlayers) {
                if (entity2 instanceof EntityPlayer && !entity2.getName().equalsIgnoreCase(Minecraft.getMinecraft().player.getName()) && !this.players.contains(entity2)) {
                    this.knownPlayers.remove(entity2);
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    public static boolean lambda$onClientTick$0(final Entity entity) {
        return entity instanceof EntityPlayer;
    }
}
