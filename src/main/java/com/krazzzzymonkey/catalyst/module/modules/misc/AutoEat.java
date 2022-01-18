/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import net.minecraft.item.EnumAction;
import net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.FoodStats;
import net.minecraft.util.EnumHand;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoEat extends Modules
{
    public static BooleanValue noInteract;
    public boolean eating;
    public DoubleValue minHealth;
    public int lastSlot;
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (this.eating && !Minecraft.getMinecraft().player.isHandActive()) {
            if (this.lastSlot != -1) {
                Minecraft.getMinecraft().player.inventory.currentItem = this.lastSlot;
                this.lastSlot = -1;
            }
            this.eating = false;
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode(), false);
            return;
        }
        if (this.eating) {
            return;
        }
        final FoodStats foodStats = Minecraft.getMinecraft().player.getFoodStats();
        if (this.isValid(Minecraft.getMinecraft().player.getHeldItemOffhand(), foodStats.getFoodLevel())) {
            Minecraft.getMinecraft().player.setActiveHand(EnumHand.OFF_HAND);
            this.eating = true;
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode(), true);
            Minecraft.getMinecraft().rightClickMouse();
        }
        else {
            for (int i = 0; i < 9; ++i) {
                if (this.isValid(Minecraft.getMinecraft().player.inventory.getStackInSlot(i), foodStats.getFoodLevel())) {
                    this.lastSlot = Minecraft.getMinecraft().player.inventory.currentItem;
                    Minecraft.getMinecraft().player.inventory.currentItem = i;
                    this.eating = true;
                    KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode(), true);
                    Minecraft.getMinecraft().rightClickMouse();
                    return;
                }
            }
        }
    }
    
    public AutoEat() {
        super("AutoEat", ModuleCategory.MISC, "Automatically eats for you");
        this.lastSlot = -1;
        this.eating = false;
        this.minHealth = new DoubleValue("Heath", 15.0, 1.0, 19.0);
        AutoEat.noInteract = new BooleanValue("NoInteract", true);
        this.addValue(this.minHealth, AutoEat.noInteract);
    }
    
    public boolean isValid(final ItemStack itemStack, final int n) {
        return AutoEat.mc.player.getHealth() <= this.minHealth.getValue() || (itemStack.getItem() instanceof ItemFood && 20 - n >= ((ItemFood)itemStack.getItem()).getHealAmount(itemStack));
    }
    
    @SubscribeEvent
    public void onBlockInteract(final PlayerInteractEvent$RightClickBlock playerInteractEvent$RightClickBlock) {
        if ((boolean)AutoEat.noInteract.getValue() && playerInteractEvent$RightClickBlock.getItemStack().getItemUseAction() == EnumAction.EAT && this.eating) {
            playerInteractEvent$RightClickBlock.setCanceled(true);
        }
    }
}
