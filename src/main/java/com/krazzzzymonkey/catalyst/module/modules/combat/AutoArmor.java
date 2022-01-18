/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import java.util.Iterator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.item.ItemElytra;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.client.CPacketClickWindow;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoArmor extends Modules
{
    public BooleanValue elytraPriority;
    public IntegerValue delay;
    public int timer;
    public BooleanValue useEnchantments;
    public BooleanValue swapWhileMoving;
    
    @Override
    public void onEnable() {
        this.timer = 0;
        super.onEnable();
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        if (packetEvent$Out.packet instanceof CPacketClickWindow) {
            this.timer = this.delay.getValue();
        }
    }
    
    public static boolean isNullOrEmpty(final ItemStack itemStack) {
        return itemStack == null || itemStack.isEmpty();
    }
    
    public int getArmorValue(final ItemArmor itemArmor, final ItemStack itemStack) {
        final int damageReduceAmount = itemArmor.damageReduceAmount;
        int calcModifierDamage = 0;
        final int n = (int)itemArmor.toughness;
        final int damageReductionAmount = itemArmor.getArmorMaterial().getDamageReductionAmount(EntityEquipmentSlot.LEGS);
        if (this.useEnchantments.getValue()) {
            final Enchantment protection = Enchantments.PROTECTION;
            calcModifierDamage = protection.calcModifierDamage(EnchantmentHelper.getEnchantmentLevel(protection, itemStack), DamageSource.causePlayerDamage((EntityPlayer)Wrapper.INSTANCE.player()));
        }
        return damageReduceAmount * 5 + calcModifierDamage * 3 + n + damageReductionAmount;
    }
    
    public AutoArmor() {
        super("AutoArmor", ModuleCategory.COMBAT, "Automatically puts on armor or an elytra on for you");
        this.useEnchantments = new BooleanValue("Enchantments", true);
        this.swapWhileMoving = new BooleanValue("SwapWhileMoving", true);
        this.elytraPriority = new BooleanValue("ElytraPriority", false);
        this.delay = new IntegerValue("Delay", 2, 0, 20);
        this.addValue(this.useEnchantments, this.swapWhileMoving, this.elytraPriority, this.delay);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null) {
                if (Minecraft.getMinecraft().player.getUniqueID() != null) {
                    if (this.timer > 0) {
                        --this.timer;
                        return;
                    }
                    if (Wrapper.INSTANCE.mc().currentScreen instanceof GuiContainer && !(Wrapper.INSTANCE.mc().currentScreen instanceof InventoryEffectRenderer)) {
                        return;
                    }
                    final InventoryPlayer inventory = Wrapper.INSTANCE.player().inventory;
                    if (!(boolean)this.swapWhileMoving.getValue()) {
                        if (Wrapper.INSTANCE.player().movementInput.moveForward != 0.0f || Wrapper.INSTANCE.player().movementInput.moveStrafe != 0.0f) {
                            return;
                        }
                    }
                    final int[] array = new int[4];
                    final int[] array2 = new int[4];
                    for (int i = 0; i < 4; ++i) {
                        array[i] = -1;
                        final ItemStack armorItemInSlot = inventory.armorItemInSlot(i);
                        if (!isNullOrEmpty(armorItemInSlot)) {
                            if (armorItemInSlot.getItem() instanceof ItemArmor) {
                                array2[i] = this.getArmorValue((ItemArmor)armorItemInSlot.getItem(), armorItemInSlot);
                            }
                        }
                    }
                    boolean b = false;
                    for (int j = 0; j < 36; ++j) {
                        final ItemStack stackInSlot = inventory.getStackInSlot(j);
                        if ((boolean)this.elytraPriority.getValue() && !isNullOrEmpty(stackInSlot) && stackInSlot.getItem() instanceof ItemElytra) {
                            b = true;
                            array[2] = j;
                        }
                        if (!isNullOrEmpty(stackInSlot)) {
                            if (stackInSlot.getItem() instanceof ItemArmor) {
                                final ItemArmor itemArmor = (ItemArmor)stackInSlot.getItem();
                                final int index = itemArmor.armorType.getIndex();
                                final int armorValue = this.getArmorValue(itemArmor, stackInSlot);
                                if (!(boolean)this.elytraPriority.getValue() || !b || index != 2) {
                                    if (armorValue > array2[index]) {
                                        array[index] = j;
                                        array2[index] = armorValue;
                                    }
                                }
                            }
                        }
                    }
                    final ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3));
                    Collections.shuffle(list);
                    for (final int intValue : list) {
                        int n = array[intValue];
                        if (n == -1) {
                            continue;
                        }
                        final ItemStack armorItemInSlot2 = inventory.armorItemInSlot(intValue);
                        if (!isNullOrEmpty(armorItemInSlot2) && inventory.getFirstEmptyStack() == -1) {
                            continue;
                        }
                        if (n < 9) {
                            n += 36;
                        }
                        if (!isNullOrEmpty(armorItemInSlot2)) {
                            Wrapper.INSTANCE.mc().playerController.windowClick(0, 8 - intValue, 0, ClickType.QUICK_MOVE, (EntityPlayer)Wrapper.INSTANCE.player());
                        }
                        Wrapper.INSTANCE.mc().playerController.windowClick(0, n, 0, ClickType.QUICK_MOVE, (EntityPlayer)Wrapper.INSTANCE.player());
                        break;
                    }
                }
            }
        }
    }
}
