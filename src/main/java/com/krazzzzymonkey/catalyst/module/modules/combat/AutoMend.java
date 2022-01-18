/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import java.util.List;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.init.Items;
import java.util.Iterator;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraft.network.play.client.CPacketPlayer$Rotation;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoMend extends Modules
{
    public BooleanValue itemSpoof;
    public IntegerValue maxDurability;
    public BooleanValue activeOnSneak;
    public IntegerValue mendWhen;
    public int toMend;
    
    public AutoMend() {
        super("AutoMend", ModuleCategory.COMBAT, "Mend your armor automatically");
        this.toMend = 0;
        this.activeOnSneak = new BooleanValue("ActiveOnSneak", true);
        this.itemSpoof = new BooleanValue("ItemSpoof", true);
        this.maxDurability = new IntegerValue("MendTo", 80, 1, 100);
        this.mendWhen = new IntegerValue("MendWhen", 20, 1, 100);
        this.addValue(this.activeOnSneak, this.itemSpoof, this.maxDurability, this.mendWhen);
    }
    
    public void mendArmor(final int currentItem) {
        for (final EntityPlayer entityPlayer : AutoMend.mc.world.playerEntities) {
            if (entityPlayer.getDistance((Entity)AutoMend.mc.player) < 1.0f && entityPlayer != AutoMend.mc.player) {
                return;
            }
        }
        if ((boolean)this.activeOnSneak.getValue() && !AutoMend.mc.player.isSneaking()) {
            return;
        }
        final int xpSlot = this.findXPSlot();
        if (xpSlot == -1) {
            return;
        }
        if (currentItem != xpSlot) {
            if (this.itemSpoof.getValue()) {
                AutoMend.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(xpSlot));
            }
            else {
                AutoMend.mc.player.inventory.currentItem = xpSlot;
            }
            AutoMend.mc.playerController.syncCurrentPlayItem();
        }
        AutoMend.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Rotation(0.0f, 90.0f, true));
        AutoMend.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        if (this.itemSpoof.getValue()) {
            AutoMend.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
        }
        else {
            AutoMend.mc.player.inventory.currentItem = currentItem;
        }
        AutoMend.mc.playerController.syncCurrentPlayItem();
    }
    
    public int findXPSlot() {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (AutoMend.mc.player.inventory.getStackInSlot(i).getItem() == Items.EXPERIENCE_BOTTLE) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    @SubscribeEvent
    public void onClientTickEvent(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (AutoMend.mc.player == null || AutoMend.mc.world == null || AutoMend.mc.player.ticksExisted < 10) {
            return;
        }
        final NonNullList armorInventory = AutoMend.mc.player.inventory.armorInventory;
        for (int i = 0; i < ((List)armorInventory).size(); ++i) {
            final ItemStack itemStack = ((List<ItemStack>)armorInventory).get(i);
            if (!itemStack.isEmpty) {
                final float n = 100.0f - 100.0f * (1.0f - (itemStack.getMaxDamage() - ~itemStack.getItemDamage() + 1) / (float)itemStack.getMaxDamage());
                if (n <= this.maxDurability.getValue()) {
                    if (n <= this.mendWhen.getValue()) {
                        this.toMend |= 1 << i;
                    }
                }
                else {
                    this.toMend &= ~(1 << i);
                }
            }
        }
        if (this.toMend > 0) {
            this.mendArmor(AutoMend.mc.player.inventory.currentItem);
        }
    }
}
