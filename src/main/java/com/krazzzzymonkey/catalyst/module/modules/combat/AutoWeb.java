/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.utils.BlockUtils;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.block.BlockWeb;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoWeb extends Modules
{
    public BooleanValue selfWeb;
    public DoubleValue antiSelfDistance;
    public BooleanValue doubleSelf;
    public DoubleValue range;
    public BooleanValue multi;
    public BooleanValue antiSelf;
    public ModeValue mode;
    public BooleanValue Double;
    public BooleanValue rotate;
    
    public static BlockPos lambda$onClientTick$4(final Entity entity) {
        return new BlockPos(entity.posX, entity.posY, entity.posZ);
    }
    
    public static boolean lambda$onClientTick$2(final Entity entity) {
        return !FriendManager.friendsList.contains(entity.getName());
    }
    
    public static boolean lambda$onClientTick$5(final BlockPos blockPos) {
        return AutoWeb.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR;
    }
    
    public static boolean lambda$onClientTick$0(final Entity entity) {
        return entity instanceof EntityPlayer;
    }
    
    public static boolean lambda$onClientTick$3(final Entity entity) {
        return entity != AutoWeb.mc.player;
    }
    
    public AutoWeb() {
        super("AutoWeb", ModuleCategory.COMBAT, "Places webs in your enemies");
        this.mode = new ModeValue("Mode", new Mode[] { new Mode("Always", true), new Mode("InHole", false) });
        this.rotate = new BooleanValue("Rotate", true);
        this.Double = new BooleanValue("DoubleHeight", true);
        this.multi = new BooleanValue("Multi", true);
        this.range = new DoubleValue("Range", 5.0, 0.0, 10.0);
        this.antiSelf = new BooleanValue("AntiSelf", true);
        this.antiSelfDistance = new DoubleValue("AntiSelfDistance", 1.5, 1.0, 5.0);
        this.selfWeb = new BooleanValue("SelfWeb", false);
        this.doubleSelf = new BooleanValue("DoubleSelfWeb", false);
        this.addValue(this.mode, this.rotate, this.range, this.Double, this.antiSelf, this.antiSelfDistance, this.selfWeb, this.doubleSelf);
    }
    
    public static int getSlotWithBlock(final Class clazz) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stackInSlot = AutoWeb.mc.player.inventory.getStackInSlot(i);
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
    
    public void lambda$onClientTick$6(final BlockPos blockPos) {
        if (this.mode.getMode("Always").isToggled()) {
            if (blockPos.getDistance((int)AutoWeb.mc.player.posX, (int)AutoWeb.mc.player.posY, (int)AutoWeb.mc.player.posZ) <= this.antiSelfDistance.getValue()) {
                return;
            }
            if (getSlotWithBlock(BlockWeb.class) == -1) {
                return;
            }
            final int currentItem = AutoWeb.mc.player.inventory.currentItem;
            final int slotWithBlock = getSlotWithBlock(BlockWeb.class);
            if (slotWithBlock < 0) {
                return;
            }
            AutoWeb.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slotWithBlock));
            AutoWeb.mc.player.inventory.currentItem = slotWithBlock;
            BlockUtils.placeBlockScaffold(blockPos, (boolean)this.rotate.getValue());
            if (this.Double.getValue()) {
                BlockUtils.placeBlockScaffold(blockPos.up(), (boolean)this.rotate.getValue());
            }
            AutoWeb.mc.player.inventory.currentItem = currentItem;
            if (!(boolean)this.multi.getValue()) {
                return;
            }
        }
        else if (this.mode.getMode("InHole").isToggled() && (AutoWeb.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.OBSIDIAN || AutoWeb.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.BEDROCK) && (AutoWeb.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.OBSIDIAN || AutoWeb.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.BEDROCK) && (AutoWeb.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.OBSIDIAN || AutoWeb.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.BEDROCK)) {
            if (AutoWeb.mc.world.getBlockState(blockPos.south()).getBlock() != Blocks.OBSIDIAN) {
                if (AutoWeb.mc.world.getBlockState(blockPos.west()).getBlock() != Blocks.BEDROCK) {
                    return;
                }
            }
            if (blockPos.getDistance((int)AutoWeb.mc.player.posX, (int)AutoWeb.mc.player.posY, (int)AutoWeb.mc.player.posZ) <= this.antiSelfDistance.getValue()) {
                return;
            }
            if (getSlotWithBlock(BlockWeb.class) == -1) {
                return;
            }
            final int currentItem2 = AutoWeb.mc.player.inventory.currentItem;
            final int slotWithBlock2 = getSlotWithBlock(BlockWeb.class);
            if (slotWithBlock2 < 0) {
                return;
            }
            AutoWeb.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slotWithBlock2));
            AutoWeb.mc.player.inventory.currentItem = slotWithBlock2;
            BlockUtils.placeBlockScaffold(blockPos, (boolean)this.rotate.getValue());
            if (this.Double.getValue()) {
                BlockUtils.placeBlockScaffold(blockPos.up(), (boolean)this.rotate.getValue());
            }
            AutoWeb.mc.player.inventory.currentItem = currentItem2;
            if (!(boolean)this.multi.getValue()) {
                return;
            }
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (AutoWeb.mc.world == null) {
            return;
        }
        if (this.selfWeb.getValue()) {
            final BlockPos blockPos = new BlockPos(AutoWeb.mc.player.posX, AutoWeb.mc.player.posY, AutoWeb.mc.player.posZ);
            if (AutoWeb.mc.world.getBlockState(blockPos).getBlock() != Blocks.AIR) {
                return;
            }
            if (getSlotWithBlock(BlockWeb.class) == -1) {
                return;
            }
            final int currentItem = AutoWeb.mc.player.inventory.currentItem;
            final int slotWithBlock = getSlotWithBlock(BlockWeb.class);
            if (slotWithBlock < 0) {
                return;
            }
            AutoWeb.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slotWithBlock));
            AutoWeb.mc.player.inventory.currentItem = slotWithBlock;
            BlockUtils.placeBlockScaffold(blockPos, (boolean)this.rotate.getValue());
            if (this.doubleSelf.getValue()) {
                BlockUtils.placeBlockScaffold(blockPos.up(), (boolean)this.rotate.getValue());
            }
            AutoWeb.mc.player.inventory.currentItem = currentItem;
        }
        AutoWeb.mc.world.loadedEntityList.stream().filter(AutoWeb::lambda$onClientTick$0).filter(this::lambda$onClientTick$1).filter(AutoWeb::lambda$onClientTick$2).filter(AutoWeb::lambda$onClientTick$3).map(AutoWeb::lambda$onClientTick$4).filter(AutoWeb::lambda$onClientTick$5).forEach(this::lambda$onClientTick$6);
    }
    
    public boolean lambda$onClientTick$1(final Entity entity) {
        return AutoWeb.mc.player.getDistance(entity) <= this.range.getValue();
    }
}
