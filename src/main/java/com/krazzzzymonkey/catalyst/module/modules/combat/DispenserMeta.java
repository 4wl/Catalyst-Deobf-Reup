/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import java.math.RoundingMode;
import java.util.Arrays;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Vec3d;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction$Action;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Block;
import java.util.List;
import com.krazzzzymonkey.catalyst.module.Modules;

public class DispenserMeta extends Modules
{
    public int redstoneSlot;
    public int obiSlot;
    public int dispenserSlot;
    public int stage;
    public int hopperSlot;
    public static List<Block> SHULKERS;
    public static Minecraft mc;
    public static DecimalFormat df;
    public BlockPos placeTarget;
    public boolean isSneaking;
    public int shulkerSlot;
    
    public void placeBlock(final BlockPos blockPos, final EnumFacing enumFacing) {
        final BlockPos offset = blockPos.offset(enumFacing);
        final EnumFacing opposite = enumFacing.getOpposite();
        if (!this.isSneaking) {
            DispenserMeta.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)DispenserMeta.mc.player, CPacketEntityAction$Action.START_SNEAKING));
            this.isSneaking = true;
        }
        DispenserMeta.mc.playerController.processRightClickBlock(DispenserMeta.mc.player, DispenserMeta.mc.world, offset, opposite, new Vec3d((Vec3i)offset).addVector(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5)), EnumHand.MAIN_HAND);
        DispenserMeta.mc.player.swingArm(EnumHand.MAIN_HAND);
    }
    
    static {
        DispenserMeta.SHULKERS = Arrays.asList(Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX);
        DispenserMeta.df = new DecimalFormat("#.#");
        DispenserMeta.mc = Minecraft.getMinecraft();
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        if (DispenserMeta.mc.player == null) {
            this.toggle();
            return;
        }
        DispenserMeta.df.setRoundingMode(RoundingMode.CEILING);
        this.stage = 0;
        this.placeTarget = null;
        this.obiSlot = -1;
        this.dispenserSlot = -1;
        this.shulkerSlot = -1;
        this.redstoneSlot = -1;
        this.hopperSlot = -1;
        this.isSneaking = false;
        for (int redstoneSlot = 0; redstoneSlot < 9 && (this.obiSlot == -1 || this.dispenserSlot == -1 || this.shulkerSlot == -1 || this.redstoneSlot == -1 || this.hopperSlot == -1); ++redstoneSlot) {
            final ItemStack stackInSlot = DispenserMeta.mc.player.inventory.getStackInSlot(redstoneSlot);
            if (stackInSlot != ItemStack.EMPTY) {
                if (stackInSlot.getItem() instanceof ItemBlock) {
                    final Block block = ((ItemBlock)stackInSlot.getItem()).getBlock();
                    if (block == Blocks.HOPPER) {
                        this.hopperSlot = redstoneSlot;
                    }
                    else if (DispenserMeta.SHULKERS.contains(block)) {
                        this.shulkerSlot = redstoneSlot;
                    }
                    else if (block == Blocks.OBSIDIAN) {
                        this.obiSlot = redstoneSlot;
                    }
                    else if (block == Blocks.DISPENSER) {
                        this.dispenserSlot = redstoneSlot;
                    }
                    else if (block == Blocks.REDSTONE_BLOCK) {
                        this.redstoneSlot = redstoneSlot;
                    }
                }
            }
        }
        if (this.obiSlot == -1 || this.dispenserSlot == -1 || this.shulkerSlot == -1 || this.redstoneSlot == -1 || this.hopperSlot == -1) {
            ChatUtils.message("[Auto32k] Items missing, disabling.");
            this.toggle();
            return;
        }
        if (DispenserMeta.mc.objectMouseOver == null || DispenserMeta.mc.objectMouseOver.getBlockPos() == null || DispenserMeta.mc.objectMouseOver.getBlockPos().up() == null) {
            ChatUtils.message("[Auto32k] Not a valid place target, disabling.");
            this.toggle();
            return;
        }
        this.placeTarget = DispenserMeta.mc.objectMouseOver.getBlockPos().up();
        ChatUtils.message("[Auto32k] Place Target: " + this.placeTarget.getX() + " " + this.placeTarget.getY() + " " + this.placeTarget.getZ() + " Distance: " + DispenserMeta.df.format(DispenserMeta.mc.player.getPositionVector().distanceTo(new Vec3d((Vec3i)this.placeTarget))));
    }
    
    public DispenserMeta() {
        super("DispenserMeta", ModuleCategory.COMBAT, "For all your auto 32k needs");
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (DispenserMeta.mc.player == null) {
            return;
        }
        if (this.stage == 0) {
            DispenserMeta.mc.player.inventory.currentItem = this.obiSlot;
            this.placeBlock(new BlockPos((Vec3i)this.placeTarget), EnumFacing.DOWN);
            DispenserMeta.mc.player.inventory.currentItem = this.dispenserSlot;
            this.placeBlock(new BlockPos((Vec3i)this.placeTarget.add(0, 1, 0)), EnumFacing.DOWN);
            DispenserMeta.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)DispenserMeta.mc.player, CPacketEntityAction$Action.STOP_SNEAKING));
            this.isSneaking = false;
            DispenserMeta.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.placeTarget.add(0, 1, 0), EnumFacing.DOWN, EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.stage = 1;
            return;
        }
        if (this.stage == 1) {
            if (!(DispenserMeta.mc.currentScreen instanceof GuiContainer)) {
                return;
            }
            DispenserMeta.mc.playerController.windowClick(DispenserMeta.mc.player.openContainer.windowId, 1, this.shulkerSlot, ClickType.SWAP, (EntityPlayer)DispenserMeta.mc.player);
            DispenserMeta.mc.player.closeScreen();
            DispenserMeta.mc.player.inventory.currentItem = this.redstoneSlot;
            this.placeBlock(new BlockPos((Vec3i)this.placeTarget.add(0, 2, 0)), EnumFacing.DOWN);
            this.stage = 2;
        }
        else {
            if (this.stage == 2) {
                final Block block = DispenserMeta.mc.world.getBlockState(this.placeTarget.offset(DispenserMeta.mc.player.getHorizontalFacing().getOpposite()).up()).getBlock();
                if (!(block instanceof BlockAir)) {
                    if (!(block instanceof BlockLiquid)) {
                        DispenserMeta.mc.player.inventory.currentItem = this.hopperSlot;
                        this.placeBlock(new BlockPos((Vec3i)this.placeTarget.offset(DispenserMeta.mc.player.getHorizontalFacing().getOpposite())), DispenserMeta.mc.player.getHorizontalFacing());
                        DispenserMeta.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)DispenserMeta.mc.player, CPacketEntityAction$Action.STOP_SNEAKING));
                        this.isSneaking = false;
                        DispenserMeta.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.placeTarget.offset(DispenserMeta.mc.player.getHorizontalFacing().getOpposite()), EnumFacing.DOWN, EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                        DispenserMeta.mc.player.inventory.currentItem = this.shulkerSlot;
                        this.stage = 3;
                    }
                }
                return;
            }
            if (this.stage == 3) {
                if (!(DispenserMeta.mc.currentScreen instanceof GuiContainer)) {
                    return;
                }
                if (((GuiContainer)DispenserMeta.mc.currentScreen).inventorySlots.getSlot(0).getStack().isEmpty) {
                    return;
                }
                DispenserMeta.mc.playerController.windowClick(DispenserMeta.mc.player.openContainer.windowId, 0, DispenserMeta.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)DispenserMeta.mc.player);
                this.toggle();
            }
        }
    }
}
