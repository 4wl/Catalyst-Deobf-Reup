/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.init.Items;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Offhand extends Modules
{
    public DoubleValue minFallHeight;
    public BooleanValue soft;
    public ModeValue mode;
    public BooleanValue swordGap;
    public ModeValue fallback;
    public Minecraft mc;
    public DoubleValue minItemHealth;
    
    public int getItemSlot() {
        Item item = Items.TOTEM_OF_UNDYING;
        Item item2 = Items.TOTEM_OF_UNDYING;
        if (this.mc.player.fallDistance >= this.minFallHeight.getValue()) {
            int i = 0;
            while (i < 45) {
                if (this.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                    if (this.mc.player.getHeldItemOffhand().getItem() == item2) {
                        return -1;
                    }
                    return (i < 9) ? (i + 36) : i;
                }
                else {
                    ++i;
                }
            }
        }
        if (this.mc.player.getHealth() + this.mc.player.getAbsorptionAmount() > (double)this.minItemHealth.value && (!(boolean)this.soft.value || this.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING)) {
            if (this.mode.getMode("Crystal").isToggled()) {
                item = Items.END_CRYSTAL;
            }
            else if (this.mode.getMode("Gapple").isToggled()) {
                item = Items.GOLDEN_APPLE;
            }
            if ((boolean)this.swordGap.value && this.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) {
                item = Items.GOLDEN_APPLE;
            }
        }
        if (this.fallback.getMode("Crystal").isToggled()) {
            item2 = Items.END_CRYSTAL;
        }
        else if (this.fallback.getMode("Gapple").isToggled()) {
            item2 = Items.GOLDEN_APPLE;
        }
        if (this.mc.player.getHeldItemOffhand().getItem() == item) {
            return -1;
        }
        for (int j = 0; j < 45; ++j) {
            if (this.mc.player.inventory.getStackInSlot(j).getItem() == item) {
                return (j < 9) ? (j + 36) : j;
            }
        }
        if (this.mc.player.getHeldItemOffhand().getItem() == item2) {
            return -1;
        }
        for (int k = 0; k < 45; ++k) {
            if (this.mc.player.inventory.getStackInSlot(k).getItem() == item2) {
                return (k < 9) ? (k + 36) : k;
            }
        }
        return -1;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        final int itemSlot = this.getItemSlot();
        if (itemSlot == -1) {
            return;
        }
        this.mc.playerController.windowClick(this.mc.player.inventoryContainer.windowId, itemSlot, 0, ClickType.PICKUP, (EntityPlayer)this.mc.player);
        this.mc.playerController.windowClick(this.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)this.mc.player);
        this.mc.playerController.windowClick(this.mc.player.inventoryContainer.windowId, itemSlot, 0, ClickType.PICKUP, (EntityPlayer)this.mc.player);
    }
    
    public Offhand() {
        super("Offhand", ModuleCategory.COMBAT, "Automatically places certain items in your offhand");
        this.mc = Minecraft.getMinecraft();
        this.minItemHealth = new DoubleValue("Health", 16.0, 0.0, 36.0);
        this.minFallHeight = new DoubleValue("FallHeight", 16.0, 1.0, 100.0);
        this.soft = new BooleanValue("Soft", false);
        this.swordGap = new BooleanValue("SwordGap", false);
        this.mode = new ModeValue("Mode", new Mode[] { new Mode("Crystal", true), new Mode("Gapple", false), new Mode("Totem", false) });
        this.fallback = new ModeValue("FallbackMode", new Mode[] { new Mode("Crystal", false), new Mode("Gapple", true), new Mode("Totem", false) });
        this.addValue(this.minItemHealth, this.minFallHeight, this.soft, this.swordGap, this.mode, this.fallback);
    }
}
