/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.inventory.Slot;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.client.gui.inventory.GuiChest;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraftforge.fml.common.gameevent.TickEvent$Phase;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import com.krazzzzymonkey.catalyst.events.PacketEvent$In;
import net.minecraft.inventory.Container;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import net.minecraft.network.play.server.SPacketWindowItems;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ChestStealer extends Modules
{
    public int ticks;
    public SPacketWindowItems packet;
    public IntegerValue delay;
    
    public boolean isContainerEmpty(final Container container) {
        boolean b = true;
        for (int i = 0; i < ((container.inventorySlots.size() == 90) ? 54 : 35); ++i) {
            if (container.getSlot(i).getHasStack()) {
                b = false;
            }
        }
        return b;
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$In packetEvent$In) {
        final Packet<?> packet = packetEvent$In.packet;
        if (packet instanceof SPacketWindowItems) {
            this.packet = (SPacketWindowItems)packet;
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (tickEvent$ClientTickEvent.phase != TickEvent$Phase.START) {
            return;
        }
        final EntityPlayerSP player = Wrapper.INSTANCE.player();
        if (!Wrapper.INSTANCE.mc().inGameHasFocus) {
            if (this.packet != null) {
                if (player.openContainer.windowId == this.packet.getWindowId() && Wrapper.INSTANCE.mc().currentScreen instanceof GuiChest) {
                    if (!this.isContainerEmpty(player.openContainer)) {
                        for (int i = 0; i < player.openContainer.inventorySlots.size() - 36; ++i) {
                            final Slot slot = player.openContainer.getSlot(i);
                            if (slot.getHasStack() && slot.getStack() != null && this.ticks >= this.delay.getValue()) {
                                Wrapper.INSTANCE.mc().playerController.windowClick(player.openContainer.windowId, i, 1, ClickType.QUICK_MOVE, (EntityPlayer)player);
                                this.ticks = 0;
                            }
                        }
                        ++this.ticks;
                    }
                    else {
                        player.closeScreen();
                        this.packet = null;
                    }
                }
            }
        }
    }
    
    public ChestStealer() {
        super("ChestStealer", ModuleCategory.MISC, "Automatically steals items from a chest");
        this.delay = new IntegerValue("Delay", 4, 0, 20);
        this.addValue(this.delay);
        this.ticks = 0;
    }
}
