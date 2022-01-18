/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.AWTException;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.init.Items;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import java.awt.Robot;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoDupe extends Modules
{
    public int prevAmount;
    public int r;
    public int i;
    public IntegerValue y;
    public Robot robot;
    public int p;
    public IntegerValue x;
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null) {
            this.toggle();
        }
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (Minecraft.getMinecraft().currentScreen instanceof GuiGameOver) {
            return;
        }
        final ItemStack stackInSlot = Minecraft.getMinecraft().player.inventory.getStackInSlot(0);
        ++this.i;
        if (this.i == 3) {
            if (stackInSlot.getItem() == Items.AIR) {
                ChatUtils.warning("No Item in hotbar slot 1!");
                this.i = 0;
                return;
            }
            Minecraft.getMinecraft().player.rotationPitch = 90.0f;
            this.prevAmount = stackInSlot.getCount();
        }
        if (this.i > 15 && this.i < 18) {
            Minecraft.getMinecraft().playerController.windowClick(0, 36, 1, ClickType.THROW, (EntityPlayer)Minecraft.getMinecraft().player);
        }
        if (this.i == 30) {
            Minecraft.getMinecraft().player.rotationPitch = 0.0f;
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiInventory((EntityPlayer)Minecraft.getMinecraft().player));
        }
        if (this.i >= 40 && this.i < 100) {
            this.robot.mouseMove(this.x.getValue(), this.y.getValue());
            this.robot.mousePress(1024);
            this.robot.mouseRelease(1024);
        }
        if (this.i == 100) {
            Minecraft.getMinecraft().getConnection().sendPacket((Packet)new CPacketCloseWindow());
        }
        if (this.i >= 101) {
            Minecraft.getMinecraft().player.inventory.currentItem = 0;
            if (stackInSlot.getCount() > 1) {
                Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
                ChatUtils.message("Successfully duped " + stackInSlot.getDisplayName());
            }
            if (this.i % 2 == 0) {
                ++this.r;
                if (this.r % 2 == 0) {
                    ++this.p;
                    if (this.p % 2 == 0) {
                        if (stackInSlot.getCount() > 2) {
                            Minecraft.getMinecraft().player.dropItem(false);
                        }
                        if (stackInSlot.getCount() == 2) {
                            this.r = 0;
                            this.i = 0;
                            this.p = 0;
                        }
                        if (stackInSlot.getCount() == 0) {
                            ChatUtils.warning("Could not check if dupe has been successful!");
                        }
                        if (this.p == 160) {
                            ChatUtils.warning("Restarting dupe, Something failed!");
                            this.r = 0;
                            this.i = 0;
                            this.p = 0;
                        }
                    }
                }
            }
        }
    }
    
    public AutoDupe() {
        super("AutoDupe", ModuleCategory.MISC, "Does the Crafting Dupe automatically");
        this.i = 0;
        this.r = 0;
        this.p = 0;
        try {
            this.robot = new Robot();
        }
        catch (AWTException ex) {
            ex.printStackTrace();
        }
        this.x = new IntegerValue("x", 50, 500, 2000);
        this.y = new IntegerValue("y", 50, 500, 2000);
        this.addValue(this.x, this.y);
    }
    
    @Override
    public void onDisable() {
        this.i = 0;
        this.r = 0;
        this.p = 0;
        super.onDisable();
    }
    
    @Override
    public void onEnable() {
        this.i = 0;
        this.r = 0;
        this.p = 0;
        super.onEnable();
    }
}
