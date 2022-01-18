/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.player;

import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging$Action;
import net.minecraft.world.GameType;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import net.minecraft.item.ItemStack;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Block;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoTool extends Modules
{
    public int slot;
    public int currentSlot;
    
    public static int findTool(final Block block) {
        float n = -1.0f;
        int n2 = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stackInSlot = Minecraft.getMinecraft().player.inventory.getStackInSlot(i);
            if (stackInSlot != null) {
                final float destroySpeed = stackInSlot.getItem().getDestroySpeed(stackInSlot, block.getDefaultState());
                if (destroySpeed > n) {
                    n = destroySpeed;
                    n2 = i;
                }
            }
        }
        return n2;
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        final Packet<?> packet = packetEvent$Out.packet;
        if (packet instanceof CPacketPlayerDigging) {
            final CPacketPlayerDigging cPacketPlayerDigging = (CPacketPlayerDigging)packet;
            if (Minecraft.getMinecraft().playerController.getCurrentGameType() != GameType.CREATIVE && cPacketPlayerDigging.getAction() == CPacketPlayerDigging$Action.START_DESTROY_BLOCK) {
                if (this.currentSlot == -1) {
                    this.currentSlot = Minecraft.getMinecraft().player.inventory.currentItem;
                }
                final int tool = findTool(Minecraft.getMinecraft().world.getBlockState(cPacketPlayerDigging.getPosition()).getBlock());
                if (tool != -1) {
                    if (this.slot == -1) {
                        this.slot = Minecraft.getMinecraft().player.inventory.currentItem;
                    }
                    Minecraft.getMinecraft().player.inventory.currentItem = tool;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (!Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown() && this.currentSlot != -1) {
            Minecraft.getMinecraft().player.inventory.currentItem = this.currentSlot;
            this.currentSlot = -1;
        }
    }
    
    public AutoTool() {
        super("AutoTool", ModuleCategory.PLAYER, "Finds the best tool in hotbar when breaking blocks");
        this.slot = -1;
        this.currentSlot = -1;
    }
}
