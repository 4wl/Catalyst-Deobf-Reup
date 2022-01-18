/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import java.util.concurrent.ConcurrentLinkedQueue;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketBlockChange;
import com.krazzzzymonkey.catalyst.events.PacketEvent;
import net.minecraft.block.BlockObsidian;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import java.util.function.BiFunction;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction$Action;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockAir;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.Minecraft;
import java.util.Queue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ObsidianReplace extends Modules
{
    public Queue<ObsidianReplace$PlacementRequest> placementRequests;
    public static int[][] BLOCK_DIRECTION_OFFSET;
    
    public void buildPlacementRequest(final Minecraft minecraft, final BlockPos blockPos) {
        for (final int[] array : ObsidianReplace.BLOCK_DIRECTION_OFFSET) {
            final BlockPos add = blockPos.add(array[0], array[1], array[2]);
            final Block block = minecraft.world.getBlockState(add).getBlock();
            if (!(block instanceof BlockAir)) {
                if (!(block instanceof BlockLiquid)) {
                    final EnumFacing calculateFaceForPlacement = this.calculateFaceForPlacement(add, blockPos);
                    if (calculateFaceForPlacement != null && this.placementRequests.offer(new ObsidianReplace$PlacementRequest(add, calculateFaceForPlacement))) {
                        return;
                    }
                }
            }
        }
    }
    
    public static Integer lambda$calculateFaceForPlacement$0(final Integer n, final String s) {
        if (n < -1 || n > 1) {
            throw new IllegalArgumentException(String.format("Difference in %s is illegal, positions are too far apart.", s));
        }
        return n;
    }
    
    public int findObsidianInHotbar(final EntityPlayerSP entityPlayerSP) {
        for (int n = 0; InventoryPlayer.isHotbar(n); ++n) {
            if (this.isItemStackObsidian(entityPlayerSP.inventory.getStackInSlot(n))) {
                return n;
            }
        }
        return -1;
    }
    
    public double getReachDistance(final Minecraft minecraft) {
        return minecraft.playerController.getBlockReachDistance();
    }
    
    public void handlePlaceRequest(final Minecraft minecraft, final ObsidianReplace$PlacementRequest obsidianReplace$PlacementRequest) {
        final BlockPos structurePosition = obsidianReplace$PlacementRequest.getStructurePosition();
        final IBlockState blockState = minecraft.world.getBlockState(structurePosition);
        final boolean onBlockActivated = blockState.getBlock().onBlockActivated((World)minecraft.world, structurePosition, blockState, (EntityPlayer)minecraft.player, EnumHand.MAIN_HAND, EnumFacing.UP, 0.0f, 0.0f, 0.0f);
        if (onBlockActivated) {
            minecraft.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)minecraft.player, CPacketEntityAction$Action.START_SNEAKING));
        }
        if (minecraft.playerController.processRightClickBlock(minecraft.player, minecraft.world, structurePosition, obsidianReplace$PlacementRequest.getPlaceDirection(), Vec3d.ZERO, EnumHand.MAIN_HAND) != EnumActionResult.FAIL) {
            minecraft.player.swingArm(EnumHand.MAIN_HAND);
        }
        if (onBlockActivated) {
            minecraft.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)minecraft.player, CPacketEntityAction$Action.STOP_SNEAKING));
        }
    }
    
    public void handleHandSwap(final ObsidianReplace$HandSwapContext obsidianReplace$HandSwapContext, final boolean b, final Minecraft minecraft) {
        minecraft.player.inventory.currentItem = (b ? obsidianReplace$HandSwapContext.getOldSlot() : obsidianReplace$HandSwapContext.getNewSlot());
        minecraft.playerController.updateController();
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.placementRequests.clear();
    }
    
    public EnumFacing calculateFaceForPlacement(final BlockPos blockPos, final BlockPos blockPos2) {
        final BiFunction<Integer, String, Integer> biFunction = ObsidianReplace::lambda$calculateFaceForPlacement$0;
        switch (biFunction.apply(blockPos.getX() - blockPos2.getX(), "x-axis")) {
            case 1: {
                return EnumFacing.WEST;
            }
            case -1: {
                return EnumFacing.EAST;
            }
            default: {
                switch (biFunction.apply(blockPos.getY() - blockPos2.getY(), "y-axis")) {
                    case 1: {
                        return EnumFacing.DOWN;
                    }
                    case -1: {
                        return EnumFacing.UP;
                    }
                    default: {
                        switch (biFunction.apply(blockPos.getZ() - blockPos2.getZ(), "z-axis")) {
                            case 1: {
                                return EnumFacing.NORTH;
                            }
                            case -1: {
                                return EnumFacing.SOUTH;
                            }
                            default: {
                                return null;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    
    public double calculateVecDistance(final Vec3d vec3d, final BlockPos blockPos) {
        final double n = blockPos.getX() - vec3d.x;
        final double n2 = blockPos.getY() - vec3d.y;
        final double n3 = blockPos.getZ() - vec3d.z;
        return MathHelper.sqrt(n * n + n2 * n2 + n3 * n3);
    }
    
    public boolean isItemStackObsidian(final ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemBlock && ((ItemBlock)itemStack.getItem()).getBlock() instanceof BlockObsidian;
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        final Packet<?> packet = packetEvent.packet;
        if (packet instanceof SPacketBlockChange) {
            final SPacketBlockChange sPacketBlockChange = (SPacketBlockChange)packet;
            if (sPacketBlockChange.getBlockState().getBlock() instanceof BlockAir) {
                final BlockPos blockPosition = sPacketBlockChange.getBlockPosition();
                if (this.calculateVecDistance(ObsidianReplace.mc.player.getPositionEyes(1.0f), blockPosition) <= this.getReachDistance(ObsidianReplace.mc)) {
                    this.buildPlacementRequest(ObsidianReplace.mc, blockPosition);
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (this.placementRequests.isEmpty()) {
            return;
        }
        final Minecraft minecraft = Minecraft.getMinecraft();
        final EntityPlayerSP player = minecraft.player;
        final ObsidianReplace$HandSwapContext obsidianReplace$HandSwapContext = new ObsidianReplace$HandSwapContext(player.inventory.currentItem, this.findObsidianInHotbar(player));
        if (obsidianReplace$HandSwapContext.getNewSlot() == -1) {
            return;
        }
        this.handleHandSwap(obsidianReplace$HandSwapContext, false, minecraft);
        final ObsidianReplace$PlacementRequest obsidianReplace$PlacementRequest = this.placementRequests.poll();
        if (this.calculateVecDistance(player.getPositionEyes(1.0f), obsidianReplace$PlacementRequest.getStructurePosition()) <= this.getReachDistance(minecraft)) {
            this.handlePlaceRequest(minecraft, obsidianReplace$PlacementRequest);
        }
        this.handleHandSwap(obsidianReplace$HandSwapContext, true, minecraft);
    }
    
    static {
        ObsidianReplace.BLOCK_DIRECTION_OFFSET = new int[][] { { 1, 0, 0 }, { -1, 0, 0 }, { 0, 1, 0 }, { 0, -1, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };
    }
    
    public ObsidianReplace() {
        super("ObsidianReplace", ModuleCategory.COMBAT, "Replaces all obsidian that breaks in the players reach");
        this.placementRequests = new ConcurrentLinkedQueue<ObsidianReplace$PlacementRequest>();
    }
}
