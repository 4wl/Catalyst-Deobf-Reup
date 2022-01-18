/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.client.entity.EntityPlayerSP;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoHotbarRefill extends Modules
{
    public BooleanValue offHand;
    public IntegerValue stackPercentage;
    public IntegerValue delay;
    public int Timer;
    
    public AutoHotbarRefill() {
        super("AutoHotbarRefill", ModuleCategory.MISC, "Automatically refills items in your hotbar");
        this.Timer = 0;
        this.delay = new IntegerValue("Delay Time", 500, 20, 1000);
        this.stackPercentage = new IntegerValue("Refill Percent", 50, 0, 99);
        this.offHand = new BooleanValue("Refill Offhand", false);
        this.addValue(this.offHand, this.delay, this.stackPercentage);
    }
    
    public int getSmallestStack(final EntityPlayerSP entityPlayerSP, final ItemStack itemStack) {
        if (itemStack == null) {
            return -1;
        }
        int count = itemStack.getMaxStackSize() + 1;
        int n = -1;
        for (int i = 9; i < entityPlayerSP.inventory.mainInventory.size(); ++i) {
            final ItemStack itemStack2 = (ItemStack)entityPlayerSP.inventory.mainInventory.get(i);
            if (itemStack2.getItem() != Items.AIR && itemStack2.getItem() == itemStack.getItem() && itemStack2.getCount() < count) {
                count = itemStack2.getCount();
                n = i;
            }
        }
        return n;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        ++this.Timer;
        if (this.Timer > this.delay.getValue()) {
            final Minecraft minecraft = Minecraft.getMinecraft();
            if (minecraft.currentScreen instanceof GuiInventory) {
                return;
            }
            final int refillable = this.getRefillable(minecraft.player);
            if (refillable != -1) {
                this.refillHotbarSlot(minecraft, refillable);
            }
            this.Timer = 0;
        }
    }
    
    public void refillHotbarSlot(final Minecraft minecraft, final int n) {
        ItemStack heldItemOffhand;
        if (n == 45) {
            heldItemOffhand = minecraft.player.getHeldItemOffhand();
        }
        else {
            heldItemOffhand = (ItemStack)minecraft.player.inventory.mainInventory.get(n);
        }
        if (heldItemOffhand.getItem() == Items.AIR) {
            return;
        }
        final int smallestStack = this.getSmallestStack(minecraft.player, heldItemOffhand);
        if (smallestStack == -1) {
            return;
        }
        if (n == 45) {
            minecraft.playerController.windowClick(minecraft.player.inventoryContainer.windowId, smallestStack, 0, ClickType.PICKUP, (EntityPlayer)minecraft.player);
            minecraft.playerController.windowClick(minecraft.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)minecraft.player);
            minecraft.playerController.windowClick(minecraft.player.inventoryContainer.windowId, smallestStack, 0, ClickType.PICKUP, (EntityPlayer)minecraft.player);
            return;
        }
        int n2 = -1;
        while (true) {
            for (int i = 0; i < 9; ++i) {
                if (n2 != -1) {
                    minecraft.playerController.windowClick(minecraft.player.inventoryContainer.windowId, smallestStack, 0, ClickType.QUICK_MOVE, (EntityPlayer)minecraft.player);
                    if (n2 != -1 && ((ItemStack)minecraft.player.inventory.mainInventory.get(n2)).getItem() != Items.AIR) {
                        minecraft.playerController.windowClick(minecraft.player.inventoryContainer.windowId, smallestStack, n2, ClickType.SWAP, (EntityPlayer)minecraft.player);
                    }
                    return;
                }
                if (((ItemStack)minecraft.player.inventory.mainInventory.get(i)).getItem() == Items.AIR) {
                    n2 = i;
                }
            }
            continue;
        }
    }
    
    public int getRefillable(final EntityPlayerSP entityPlayerSP) {
        if ((boolean)this.offHand.getValue() && entityPlayerSP.getHeldItemOffhand().getItem() != Items.AIR && entityPlayerSP.getHeldItemOffhand().getCount() < entityPlayerSP.getHeldItemOffhand().getMaxStackSize() && entityPlayerSP.getHeldItemOffhand().getCount() / (double)entityPlayerSP.getHeldItemOffhand().getMaxStackSize() <= this.stackPercentage.getValue() / 100.0) {
            return 45;
        }
        for (int i = 0; i < 9; ++i) {
            final ItemStack itemStack = (ItemStack)entityPlayerSP.inventory.mainInventory.get(i);
            if (itemStack.getItem() != Items.AIR && itemStack.getCount() < itemStack.getMaxStackSize() && itemStack.getCount() / (double)itemStack.getMaxStackSize() <= this.stackPercentage.getValue() / 100.0) {
                return i;
            }
        }
        return -1;
    }
}
