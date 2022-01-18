/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import java.util.function.BiConsumer;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.utils.ChatColor;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import java.util.concurrent.ConcurrentHashMap;
import com.krazzzzymonkey.catalyst.module.Modules;

public class DurabilityAlert extends Modules
{
    public ConcurrentHashMap<String, Integer> players;
    public BooleanValue yourself;
    public BooleanValue friends;
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        for (final EntityPlayer entityPlayer : DurabilityAlert.mc.world.playerEntities) {
            for (final ItemStack itemStack : entityPlayer.getArmorInventoryList()) {
                if (itemStack != null && itemStack.getItem().getDurabilityForDisplay(itemStack) > 0.75 && !this.players.containsKey(entityPlayer.getName())) {
                    if (entityPlayer.getName().equalsIgnoreCase(DurabilityAlert.mc.player.getName()) && (boolean)this.yourself.getValue()) {
                        ChatUtils.message(ChatColor.RED + "You have low durability on your armor!");
                    }
                    else if (FriendManager.friendsList.contains(entityPlayer.getName()) && !entityPlayer.getName().equalsIgnoreCase(DurabilityAlert.mc.player.getName()) && (boolean)this.friends.getValue()) {
                        ChatUtils.message(ChatColor.RED + "Your friend " + ChatColor.AQUA + entityPlayer.getName() + ChatColor.RED + " has low durability!");
                    }
                    else if (!entityPlayer.getName().equalsIgnoreCase(DurabilityAlert.mc.player.getName())) {
                        ChatUtils.message(ChatColor.GOLD + entityPlayer.getName() + ChatColor.RED + " has low durability!");
                    }
                    this.players.put(entityPlayer.getName(), 1500);
                }
            }
        }
        this.players.forEach(this::lambda$onClientTick$0);
    }
    
    public void lambda$onClientTick$0(final String s, final Integer n) {
        if (n <= 0) {
            this.players.remove(s);
        }
        else {
            this.players.put(s, n - 1);
        }
    }
    
    public DurabilityAlert() {
        super("DurabilityAlert", ModuleCategory.HUD, "Alerts you when someones armor durability is low");
        this.yourself = new BooleanValue("Yourself", true);
        this.friends = new BooleanValue("Friends", true);
        this.addValue(this.yourself, this.friends);
        this.players = new ConcurrentHashMap<String, Integer>();
    }
}
