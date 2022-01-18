/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction$Action;
import com.krazzzzymonkey.catalyst.utils.BlockUtils;
import net.minecraft.util.EnumHand;
import net.minecraft.network.play.client.CPacketPlayer$Position;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Burrow extends Modules
{
    public static BooleanValue rotate;
    public int slot;
    public static IntegerValue delay;
    public static BooleanValue onSneak;
    public static ModeValue modes;
    public static long timeSinceLastBurrow;
    public static DoubleValue offset;
    
    public static int getSlotWithBlock(final Class clazz) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stackInSlot = Burrow.mc.player.inventory.getStackInSlot(i);
            if (stackInSlot != ItemStack.EMPTY) {
                if (clazz.isInstance(stackInSlot.getItem())) {
                    return i;
                }
                if (stackInSlot.getItem() instanceof ItemBlock && clazz.isInstance(((ItemBlock)stackInSlot.getItem()).getBlock())) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Burrow.mc.player == null) {
            return;
        }
        ++Burrow.timeSinceLastBurrow;
        if (Burrow.onSneak.getValue()) {
            if (Burrow.mc.player.isSneaking()) {
                if (Burrow.timeSinceLastBurrow > Burrow.delay.getValue()) {
                    final BlockPos blockPos = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ);
                    this.slot = (Burrow.modes.getMode("EChest").isToggled() ? getSlotWithBlock(BlockEnderChest.class) : getSlotWithBlock(BlockObsidian.class));
                    if (this.slot < 0) {
                        return;
                    }
                    Burrow.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.slot));
                    Burrow.mc.player.inventory.currentItem = this.slot;
                    Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.41999998688698, Burrow.mc.player.posZ, true));
                    Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.7531999805211997, Burrow.mc.player.posZ, true));
                    Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.00133597911214, Burrow.mc.player.posZ, true));
                    Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.16610926093821, Burrow.mc.player.posZ, true));
                    BlockUtils.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)Burrow.rotate.getValue(), true, false);
                    Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Burrow.mc.player.posX, Burrow.mc.player.posY + Burrow.offset.getValue(), Burrow.mc.player.posZ, false));
                    Burrow.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Burrow.mc.player, CPacketEntityAction$Action.STOP_SNEAKING));
                    Burrow.mc.player.setSneaking(false);
                    Burrow.timeSinceLastBurrow = 0L;
                }
            }
        }
        if (!(boolean)Burrow.onSneak.getValue()) {
            final BlockPos blockPos2 = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ);
            this.slot = (Burrow.modes.getMode("EChest").isToggled() ? getSlotWithBlock(BlockEnderChest.class) : getSlotWithBlock(BlockObsidian.class));
            if (this.slot < 0) {
                return;
            }
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.slot));
            Burrow.mc.player.inventory.currentItem = this.slot;
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.41999998688698, Burrow.mc.player.posZ, true));
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.7531999805211997, Burrow.mc.player.posZ, true));
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.00133597911214, Burrow.mc.player.posZ, true));
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.16610926093821, Burrow.mc.player.posZ, true));
            BlockUtils.placeBlock(blockPos2, EnumHand.MAIN_HAND, (boolean)Burrow.rotate.getValue(), true, false);
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(Burrow.mc.player.posX, Burrow.mc.player.posY + Burrow.offset.getValue(), Burrow.mc.player.posZ, false));
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Burrow.mc.player, CPacketEntityAction$Action.STOP_SNEAKING));
            Burrow.mc.player.setSneaking(false);
            Burrow.timeSinceLastBurrow = 0L;
            this.onDisable();
            this.setToggled(false);
        }
    }
    
    static {
        Burrow.timeSinceLastBurrow = 0L;
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        Burrow.timeSinceLastBurrow = 0L;
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        Burrow.timeSinceLastBurrow = 0L;
    }
    
    public Burrow() {
        super("Burrow", ModuleCategory.COMBAT, "Places a Block in your feet");
        this.slot = 0;
        Burrow.onSneak = new BooleanValue("OnSneak", true);
        Burrow.delay = new IntegerValue("Delay", 10, 0, 200);
        Burrow.modes = new ModeValue("Mode", new Mode[] { new Mode("Obsidian", true), new Mode("EChest", false) });
        Burrow.offset = new DoubleValue("Offset", 3.0, -5.0, 5.0);
        Burrow.rotate = new BooleanValue("Rotate", true);
        this.addValue(Burrow.modes, Burrow.onSneak, Burrow.rotate, Burrow.delay, Burrow.offset);
    }
}
