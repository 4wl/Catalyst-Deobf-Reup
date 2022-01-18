/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockObsidian;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class NoEntityTrace extends Modules
{
    public static BooleanValue pickaxeOnly;
    public static boolean shouldEnable;
    public static BooleanValue obsidian;
    public static BooleanValue enderChests;
    
    static {
        NoEntityTrace.shouldEnable = false;
    }
    
    public NoEntityTrace() {
        super("NoEntityTrace", ModuleCategory.MISC, "Allows you to mine blocks through entities");
        NoEntityTrace.pickaxeOnly = new BooleanValue("Pickaxe", true);
        NoEntityTrace.obsidian = new BooleanValue("Obsidian", true);
        NoEntityTrace.enderChests = new BooleanValue("EnderChest", true);
        this.addValue(NoEntityTrace.pickaxeOnly, NoEntityTrace.obsidian, NoEntityTrace.enderChests);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        NoEntityTrace.shouldEnable = (((boolean)NoEntityTrace.pickaxeOnly.getValue() && Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) || ((boolean)NoEntityTrace.obsidian.getValue() && Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemBlock && ((ItemBlock)Minecraft.getMinecraft().player.getHeldItemMainhand().getItem()).getBlock() instanceof BlockObsidian) || ((boolean)NoEntityTrace.enderChests.getValue() && Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemBlock && ((ItemBlock)Minecraft.getMinecraft().player.getHeldItemMainhand().getItem()).getBlock() instanceof BlockEnderChest));
    }
}
