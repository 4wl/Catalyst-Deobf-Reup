/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction$Action;
import net.minecraft.entity.passive.AbstractHorse;
import java.awt.Robot;
import com.krazzzzymonkey.catalyst.module.Modules;

public class DonkeyDrop extends Modules
{
    public Robot r;
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.runThread();
    }
    
    public void lambda$runThread$0() {
        Exception ex;
        try {
            if (DonkeyDrop.mc.player.getRidingEntity() instanceof AbstractHorse) {
                DonkeyDrop.mc.getConnection().sendPacket((Packet)new CPacketEntityAction(DonkeyDrop.mc.player.getRidingEntity(), CPacketEntityAction$Action.OPEN_INVENTORY));
                Thread.sleep(175L);
                for (int i = 1; i < 17; ++i) {
                    Thread.sleep(75L);
                    final ItemStack itemStack = (ItemStack)DonkeyDrop.mc.player.openContainer.getInventory().get(i);
                    if (!itemStack.isEmpty() && itemStack.getItem() != Items.AIR) {
                        DonkeyDrop.mc.playerController.windowClick(DonkeyDrop.mc.player.openContainer.windowId, i, 0, ClickType.PICKUP, (EntityPlayer)DonkeyDrop.mc.player);
                        DonkeyDrop.mc.playerController.windowClick(DonkeyDrop.mc.player.openContainer.windowId, -999, 0, ClickType.PICKUP, (EntityPlayer)DonkeyDrop.mc.player);
                    }
                }
            }
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
            this.toggle();
            return;
        }
        catch (Exception ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public DonkeyDrop() {
        super("DonkeyDrop", ModuleCategory.MISC, "Automatically drops all items in a donkeys inventory");
    }
    
    public void runThread() {
        new Thread(this::lambda$runThread$0).start();
    }
}
