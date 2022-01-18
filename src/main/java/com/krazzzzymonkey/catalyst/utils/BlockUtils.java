/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraft.world.GameType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.network.play.client.CPacketAnimation;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerDigging$Action;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import java.util.Collection;
import java.util.Arrays;
import net.minecraft.init.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction$Action;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.EnumHand;
import net.minecraft.block.state.IBlockState;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import java.util.LinkedList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer$Rotation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;
import java.util.List;
import net.minecraft.block.Block;
import java.util.HashSet;

public class BlockUtils
{
    public static HashSet<Block> blackList;
    public static List<Block> rightclickableBlocks;
    public static List<Block> emptyBlocks;
    public static Minecraft mc;
    public static HashSet<Block> shulkerList;
    
    public static boolean lambda$isEntitiesEmpty$0(final Entity entity) {
        return !(entity instanceof EntityItem);
    }
    
    public static void faceBlockPacket(final BlockPos blockPos) {
        final double x = blockPos.getX() + 0.5 - BlockUtils.mc.player.posX;
        final double y = blockPos.getY() + 0.0 - (BlockUtils.mc.player.posY + BlockUtils.mc.player.getEyeHeight());
        final double y2 = blockPos.getZ() + 0.5 - BlockUtils.mc.player.posZ;
        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Rotation(BlockUtils.mc.player.rotationYaw + MathHelper.wrapDegrees((float)(Math.atan2(y2, x) * 180.0 / 3.141592653589793) - 90.0f - BlockUtils.mc.player.rotationYaw), BlockUtils.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-(Math.atan2(y, MathHelper.sqrt(x * x + y2 * y2)) * 180.0 / 3.141592653589793)) - BlockUtils.mc.player.rotationPitch), BlockUtils.mc.player.onGround));
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, n));
    }
    
    public static LinkedList findBlocksNearEntity(final EntityLivingBase entityLivingBase, final int n, final int n2, final int n3) {
        final LinkedList<BlockPos> list = new LinkedList<BlockPos>();
        for (int i = (int)Wrapper.INSTANCE.player().posX - n3; i <= (int)Wrapper.INSTANCE.player().posX + n3; ++i) {
            for (int j = (int)Wrapper.INSTANCE.player().posZ - n3; j <= (int)Wrapper.INSTANCE.player().posZ + n3; ++j) {
                for (int height = Wrapper.INSTANCE.world().getHeight(i, j), k = 0; k <= height; ++k) {
                    final BlockPos blockPos = new BlockPos(i, k, j);
                    final IBlockState blockState = Wrapper.INSTANCE.world().getBlockState(blockPos);
                    if (n == -1 || n2 == -1) {
                        list.add(blockPos);
                    }
                    else {
                        final int idFromBlock = Block.getIdFromBlock(blockState.getBlock());
                        final int metaFromState = blockState.getBlock().getMetaFromState(blockState);
                        if (idFromBlock == n && metaFromState == n2) {
                            list.add(blockPos);
                        }
                    }
                }
            }
        }
        return list;
    }
    
    public static boolean placeBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = false;
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        if (firstFacing == null) {
            return b3;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing opposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).addVector(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
        final Block block = BlockUtils.mc.world.getBlockState(offset).getBlock();
        if (!BlockUtils.mc.player.isSneaking() && (BlockUtils.blackList.contains(block) || BlockUtils.shulkerList.contains(block))) {
            BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtils.mc.player, CPacketEntityAction$Action.START_SNEAKING));
            BlockUtils.mc.player.setSneaking(true);
            b4 = true;
        }
        if (b) {
            faceVector(add, true);
        }
        rightClickBlock(offset, add, enumHand, opposite, b2);
        BlockUtils.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtils.mc.rightClickDelayTimer = 4;
        if (!b4) {
            if (!b3) {
                return false;
            }
        }
        return true;
    }
    
    public static Material getMaterial(final BlockPos blockPos) {
        return getState(blockPos).getMaterial();
    }
    
    public static void processRightClickBlock(final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d) {
        getPlayerController().processRightClickBlock(BlockUtils.mc.player, BlockUtils.mc.world, blockPos, enumFacing, vec3d, EnumHand.MAIN_HAND);
    }
    
    public static EnumFacing getPlaceableSide(final BlockPos blockPos) {
        final Minecraft minecraft = Minecraft.getMinecraft();
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (!minecraft.world.getBlockState(blockPos.offset(enumFacing)).getMaterial().isReplaceable()) {
                return enumFacing;
            }
        }
        return null;
    }
    
    static {
        BlockUtils.mc = Wrapper.INSTANCE.mc();
        BlockUtils.blackList = new HashSet<Block>(Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.ENCHANTING_TABLE));
        BlockUtils.shulkerList = new HashSet<Block>(Arrays.asList(Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX));
        BlockUtils.emptyBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, (Block)Blocks.TALLGRASS, (Block)Blocks.FIRE);
        BlockUtils.rightclickableBlocks = Arrays.asList((Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, (Block)Blocks.UNPOWERED_COMPARATOR, (Block)Blocks.UNPOWERED_REPEATER, (Block)Blocks.POWERED_REPEATER, (Block)Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, (Block)Blocks.BEACON, Blocks.BED, Blocks.FURNACE, (Block)Blocks.OAK_DOOR, (Block)Blocks.SPRUCE_DOOR, (Block)Blocks.BIRCH_DOOR, (Block)Blocks.JUNGLE_DOOR, (Block)Blocks.ACACIA_DOOR, (Block)Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, (Block)Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE);
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return getBlock(blockPos).canCollideCheck(getState(blockPos), false);
    }
    
    public static float[] getNeededRotations2(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { BlockUtils.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - BlockUtils.mc.player.rotationYaw), BlockUtils.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - BlockUtils.mc.player.rotationPitch) };
    }
    
    public static boolean placeBlockScaffold(final BlockPos blockPos, final boolean b) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            final EnumFacing opposite = enumFacing.getOpposite();
            if (canBeClicked(offset)) {
                final Vec3d add = new Vec3d((Vec3i)offset).addVector(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
                if (b) {
                    faceVectorPacketInstant(add);
                }
                BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtils.mc.player, CPacketEntityAction$Action.START_SNEAKING));
                processRightClickBlock(offset, opposite, add);
                BlockUtils.mc.player.swingArm(EnumHand.MAIN_HAND);
                BlockUtils.mc.rightClickDelayTimer = 0;
                BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtils.mc.player, CPacketEntityAction$Action.STOP_SNEAKING));
                return true;
            }
        }
        return false;
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n) {
        return getInterpolatedAmount(entity, n, n, n);
    }
    
    public static PlayerControllerMP getPlayerController() {
        return BlockUtils.mc.playerController;
    }
    
    public static void faceVector(final Vec3d vec3d, final boolean b) {
        final float[] legitRotations = getLegitRotations(vec3d);
        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Rotation(legitRotations[0], b ? ((float)MathHelper.normalizeAngle((int)legitRotations[1], 360)) : legitRotations[1], BlockUtils.mc.player.onGround));
    }
    
    public static void faceBlockClientHorizontally(final BlockPos blockPos) {
        BlockUtils.mc.player.rotationYaw += MathHelper.wrapDegrees((float)(Math.atan2(blockPos.getZ() + 0.5 - BlockUtils.mc.player.posZ, blockPos.getX() + 0.5 - BlockUtils.mc.player.posX) * 180.0 / 3.141592653589793) - 90.0f - BlockUtils.mc.player.rotationYaw);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n, final double n2, final double n3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n2, (entity.posZ - entity.lastTickPosZ) * n3);
    }
    
    public static Block getBlock(final BlockPos blockPos) {
        return getState(blockPos).getBlock();
    }
    
    public static float getPlayerBlockDistance(final double n, final double n2, final double n3) {
        return getBlockDistance((float)(BlockUtils.mc.player.posX - n), (float)(BlockUtils.mc.player.posY - n2), (float)(BlockUtils.mc.player.posZ - n3));
    }
    
    public static void breakBlocksPacketSpam(final Iterable iterable) {
        final Vec3d eyesPos = Utils.getEyesPos();
        final NetHandlerPlayClient connection = Wrapper.INSTANCE.player().connection;
        for (final BlockPos blockPos : iterable) {
            final Vec3d addVector = new Vec3d((Vec3i)blockPos).addVector(0.5, 0.5, 0.5);
            final double squareDistanceTo = eyesPos.squareDistanceTo(addVector);
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                if (eyesPos.squareDistanceTo(addVector.add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5))) < squareDistanceTo) {
                    connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.START_DESTROY_BLOCK, blockPos, enumFacing));
                    connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.STOP_DESTROY_BLOCK, blockPos, enumFacing));
                    break;
                }
            }
        }
    }
    
    public static List getPossibleSides(final BlockPos blockPos) {
        final ArrayList<EnumFacing> list = new ArrayList<EnumFacing>();
        for (final EnumFacing e : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(e);
            if (BlockUtils.mc.world.getBlockState(offset).getBlock().canCollideCheck(BlockUtils.mc.world.getBlockState(offset), false) && !BlockUtils.mc.world.getBlockState(offset).getMaterial().isReplaceable()) {
                list.add(e);
            }
        }
        return list;
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(BlockUtils.mc.player.posX, BlockUtils.mc.player.posY + BlockUtils.mc.player.getEyeHeight(), BlockUtils.mc.player.posZ);
    }
    
    public static IBlockState getState(final BlockPos blockPos) {
        return BlockUtils.mc.world.getBlockState(blockPos);
    }
    
    public static void faceBlockClient(final BlockPos blockPos) {
        final double x = blockPos.getX() + 0.5 - BlockUtils.mc.player.posX;
        final double y = blockPos.getY() + 0.0 - (BlockUtils.mc.player.posY + BlockUtils.mc.player.getEyeHeight());
        final double y2 = blockPos.getZ() + 0.5 - BlockUtils.mc.player.posZ;
        final double x2 = MathHelper.sqrt(x * x + y2 * y2);
        final float n = (float)(Math.atan2(y2, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float n2 = (float)(-(Math.atan2(y, x2) * 180.0 / 3.141592653589793));
        BlockUtils.mc.player.rotationYaw += MathHelper.wrapDegrees(n - BlockUtils.mc.player.rotationYaw);
        BlockUtils.mc.player.rotationPitch += MathHelper.wrapDegrees(n2 - BlockUtils.mc.player.rotationPitch);
    }
    
    public static float getHorizontalPlayerBlockDistance(final BlockPos blockPos) {
        final float n = (float)(BlockUtils.mc.player.posX - blockPos.getX());
        final float n2 = (float)(BlockUtils.mc.player.posZ - blockPos.getZ());
        return MathHelper.sqrt((n - 0.5f) * (n - 0.5f) + (n2 - 0.5f) * (n2 - 0.5f));
    }
    
    public static float[] getLegitRotations(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { BlockUtils.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - BlockUtils.mc.player.rotationYaw), BlockUtils.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - BlockUtils.mc.player.rotationPitch) };
    }
    
    public static float getHardness(final BlockPos blockPos) {
        return getState(blockPos).getPlayerRelativeBlockHardness((EntityPlayer)Wrapper.INSTANCE.player(), (World)Wrapper.INSTANCE.world(), blockPos);
    }
    
    public static boolean placeBlockSimple(final BlockPos blockPos) {
        final Vec3d vec3d = new Vec3d(BlockUtils.mc.player.posX, BlockUtils.mc.player.posY + BlockUtils.mc.player.getEyeHeight(), BlockUtils.mc.player.posZ);
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            final EnumFacing opposite = enumFacing.getOpposite();
            if (getBlock(offset).canCollideCheck(BlockUtils.mc.world.getBlockState(offset), false)) {
                final Vec3d add = new Vec3d((Vec3i)offset).addVector(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
                if (vec3d.squareDistanceTo(add) <= 36.0) {
                    BlockUtils.mc.playerController.processRightClickBlock(BlockUtils.mc.player, BlockUtils.mc.world, offset, opposite, add, EnumHand.MAIN_HAND);
                    return true;
                }
            }
        }
        return false;
    }
    
    public static EnumFacing getFirstFacing(final BlockPos blockPos) {
        final Iterator<EnumFacing> iterator = getPossibleSides(blockPos).iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
    
    public static void rightClickBlock(final BlockPos blockPos, final Vec3d vec3d, final EnumHand enumHand, final EnumFacing enumFacing, final boolean b) {
        if (b) {
            BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, (float)(vec3d.x - blockPos.getX()), (float)(vec3d.y - blockPos.getY()), (float)(vec3d.z - blockPos.getZ())));
        }
        else {
            BlockUtils.mc.playerController.processRightClickBlock(BlockUtils.mc.player, BlockUtils.mc.world, blockPos, enumFacing, vec3d, enumHand);
        }
        BlockUtils.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtils.mc.rightClickDelayTimer = 4;
    }
    
    public static boolean isEntitiesEmpty(final BlockPos blockPos) {
        return ((List)BlockUtils.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(blockPos)).stream().filter(BlockUtils::lambda$isEntitiesEmpty$0).filter(BlockUtils::lambda$isEntitiesEmpty$1).collect(Collectors.toList())).isEmpty();
    }
    
    public static void swingArm(final ModeValue modeValue) {
        if (modeValue.getMode("Mainhand").isToggled()) {
            BlockUtils.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        if (modeValue.getMode("Offhand").isToggled()) {
            BlockUtils.mc.player.swingArm(EnumHand.OFF_HAND);
        }
    }
    
    public static boolean isBlockEmpty(final BlockPos blockPos) {
        try {
            if (BlockUtils.emptyBlocks.contains(BlockUtils.mc.world.getBlockState(blockPos).getBlock())) {
                final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
                for (final Entity entity : BlockUtils.mc.world.loadedEntityList) {
                    if (entity instanceof EntityLivingBase) {
                        if (axisAlignedBB.intersects(entity.getEntityBoundingBox())) {
                            return false;
                        }
                        continue;
                    }
                }
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static void rotatePacket(final double n, final double n2, final double n3) {
        final double x = n - BlockUtils.mc.player.posX;
        final double y = n2 - (BlockUtils.mc.player.posY + BlockUtils.mc.player.getEyeHeight());
        final double y2 = n3 - BlockUtils.mc.player.posZ;
        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Rotation((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f, (float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))), BlockUtils.mc.player.onGround));
    }
    
    public static void placeCrystalOnBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b) {
        final RayTraceResult rayTraceBlocks = BlockUtils.mc.world.rayTraceBlocks(new Vec3d(BlockUtils.mc.player.posX, BlockUtils.mc.player.posY + BlockUtils.mc.player.getEyeHeight(), BlockUtils.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit, enumHand, 0.0f, 0.0f, 0.0f));
        if (b) {
            BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketAnimation(enumHand));
        }
    }
    
    public static void faceVectorPacket(final Vec3d vec3d) {
        final double x = vec3d.x - BlockUtils.mc.player.posX;
        final double y = vec3d.y - (BlockUtils.mc.player.posY + BlockUtils.mc.player.getEyeHeight());
        final double y2 = vec3d.z - BlockUtils.mc.player.posZ;
        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Rotation(BlockUtils.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - BlockUtils.mc.player.rotationYaw), BlockUtils.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, MathHelper.sqrt(x * x + y2 * y2)))) - BlockUtils.mc.player.rotationPitch), BlockUtils.mc.player.onGround));
    }
    
    public static boolean placeBlockLegit(final BlockPos blockPos) {
        final Vec3d vec3d = new Vec3d(BlockUtils.mc.player.posX, BlockUtils.mc.player.posY + BlockUtils.mc.player.getEyeHeight(), BlockUtils.mc.player.posZ);
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            final EnumFacing opposite = enumFacing.getOpposite();
            if (vec3d.squareDistanceTo(new Vec3d((Vec3i)blockPos).addVector(0.5, 0.5, 0.5)) < vec3d.squareDistanceTo(new Vec3d((Vec3i)offset).addVector(0.5, 0.5, 0.5))) {
                if (getBlock(offset).canCollideCheck(BlockUtils.mc.world.getBlockState(offset), false)) {
                    final Vec3d add = new Vec3d((Vec3i)offset).addVector(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
                    if (vec3d.squareDistanceTo(add) <= 18.0625) {
                        faceVectorPacket(add);
                        BlockUtils.mc.playerController.processRightClickBlock(BlockUtils.mc.player, BlockUtils.mc.world, offset, opposite, add, EnumHand.MAIN_HAND);
                        BlockUtils.mc.player.swingArm(EnumHand.MAIN_HAND);
                        BlockUtils.mc.rightClickDelayTimer = 4;
                    }
                }
            }
        }
        Wrapper.INSTANCE.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        BlockUtils.mc.player.swingArm(EnumHand.MAIN_HAND);
        return true;
    }
    
    public static float getPlayerBlockDistance(final BlockPos blockPos) {
        return getPlayerBlockDistance(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec3d) {
        final float[] neededRotations2 = getNeededRotations2(vec3d);
        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Rotation(neededRotations2[0], neededRotations2[1], BlockUtils.mc.player.onGround));
    }
    
    public static boolean lambda$isEntitiesEmpty$1(final Entity entity) {
        return !(entity instanceof EntityXPOrb);
    }
    
    public static float getBlockDistance(final float n, final float n2, final float n3) {
        return MathHelper.sqrt((n - 0.5f) * (n - 0.5f) + (n2 - 0.5f) * (n2 - 0.5f) + (n3 - 0.5f) * (n3 - 0.5f));
    }
    
    public static boolean breakBlockSimple(final BlockPos blockPos) {
        EnumFacing enumFacing = null;
        final EnumFacing[] values = EnumFacing.values();
        final Vec3d eyesPos = Utils.getEyesPos();
        final Vec3d center = getState(blockPos).getBoundingBox((IBlockAccess)Wrapper.INSTANCE.world(), blockPos).getCenter();
        final Vec3d add = new Vec3d((Vec3i)blockPos).add(center);
        final Vec3d[] array = new Vec3d[values.length];
        for (int i = 0; i < values.length; ++i) {
            final Vec3i directionVec = values[i].getDirectionVec();
            array[i] = add.add(new Vec3d(center.x * directionVec.getX(), center.y * directionVec.getY(), center.z * directionVec.getZ()));
        }
        for (int j = 0; j < values.length; ++j) {
            if (Wrapper.INSTANCE.world().rayTraceBlocks(eyesPos, array[j], false, true, false) == null) {
                enumFacing = values[j];
                break;
            }
        }
        if (enumFacing == null) {
            final double squareDistanceTo = eyesPos.squareDistanceTo(add);
            for (int k = 0; k < values.length; ++k) {
                if (eyesPos.squareDistanceTo(array[k]) < squareDistanceTo) {
                    enumFacing = values[k];
                    break;
                }
            }
        }
        if (enumFacing == null) {
            enumFacing = values[0];
        }
        Utils.faceVectorPacket(array[enumFacing.ordinal()]);
        if (!BlockUtils.mc.playerController.onPlayerDamageBlock(blockPos, enumFacing)) {
            return false;
        }
        Wrapper.INSTANCE.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
        return true;
    }
    
    public static boolean placeBlock(final BlockPos blockPos, final int currentItem, final boolean b, final boolean b2, final ModeValue modeValue, final boolean b3) {
        if (isBlockEmpty(blockPos)) {
            int currentItem2 = -1;
            if (currentItem != BlockUtils.mc.player.inventory.currentItem) {
                currentItem2 = BlockUtils.mc.player.inventory.currentItem;
                BlockUtils.mc.player.inventory.currentItem = currentItem;
            }
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                final Block block = BlockUtils.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                final Vec3d vec3d = new Vec3d(blockPos.getX() + 0.5 + enumFacing.getFrontOffsetX() * 0.5, blockPos.getY() + 0.5 + enumFacing.getFrontOffsetY() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getFrontOffsetZ() * 0.5);
                if (!BlockUtils.emptyBlocks.contains(block) && BlockUtils.mc.player.getPositionEyes(BlockUtils.mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25) {
                    final float[] array = { BlockUtils.mc.player.rotationYaw, BlockUtils.mc.player.rotationPitch };
                    if (b) {
                        rotatePacket(vec3d.x, vec3d.y, vec3d.z);
                    }
                    if (BlockUtils.rightclickableBlocks.contains(block)) {
                        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtils.mc.player, CPacketEntityAction$Action.START_SNEAKING));
                    }
                    BlockUtils.mc.playerController.processRightClickBlock(BlockUtils.mc.player, BlockUtils.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                    if (BlockUtils.rightclickableBlocks.contains(block)) {
                        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtils.mc.player, CPacketEntityAction$Action.STOP_SNEAKING));
                    }
                    if (b2) {
                        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Rotation(array[0], array[1], BlockUtils.mc.player.onGround));
                    }
                    swingArm(modeValue);
                    if (b3 && !BlockUtils.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
                        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.START_DESTROY_BLOCK, blockPos, enumFacing));
                    }
                    if (currentItem2 != -1) {
                        BlockUtils.mc.player.inventory.currentItem = currentItem2;
                    }
                    return true;
                }
            }
            if (currentItem2 != -1) {
                BlockUtils.mc.player.inventory.currentItem = currentItem2;
            }
        }
        return false;
    }
    
    public static boolean placeBlock(final BlockPos blockPos, final int currentItem, final boolean b, final boolean b2, final ModeValue modeValue) {
        if (isBlockEmpty(blockPos)) {
            int currentItem2 = -1;
            if (currentItem != BlockUtils.mc.player.inventory.currentItem) {
                currentItem2 = BlockUtils.mc.player.inventory.currentItem;
                BlockUtils.mc.player.inventory.currentItem = currentItem;
            }
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                final Block block = BlockUtils.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                final Vec3d vec3d = new Vec3d(blockPos.getX() + 0.5 + enumFacing.getFrontOffsetX() * 0.5, blockPos.getY() + 0.5 + enumFacing.getFrontOffsetY() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getFrontOffsetZ() * 0.5);
                if (!BlockUtils.emptyBlocks.contains(block) && BlockUtils.mc.player.getPositionEyes(BlockUtils.mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25) {
                    final float[] array = { BlockUtils.mc.player.rotationYaw, BlockUtils.mc.player.rotationPitch };
                    if (b) {
                        rotatePacket(vec3d.x, vec3d.y, vec3d.z);
                    }
                    if (BlockUtils.rightclickableBlocks.contains(block)) {
                        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtils.mc.player, CPacketEntityAction$Action.START_SNEAKING));
                    }
                    BlockUtils.mc.playerController.processRightClickBlock(BlockUtils.mc.player, BlockUtils.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                    if (BlockUtils.rightclickableBlocks.contains(block)) {
                        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtils.mc.player, CPacketEntityAction$Action.STOP_SNEAKING));
                    }
                    if (b2) {
                        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Rotation(array[0], array[1], BlockUtils.mc.player.onGround));
                    }
                    swingArm(modeValue);
                    if (currentItem2 != -1) {
                        BlockUtils.mc.player.inventory.currentItem = currentItem2;
                    }
                    return true;
                }
            }
            if (currentItem2 != -1) {
                BlockUtils.mc.player.inventory.currentItem = currentItem2;
            }
        }
        return false;
    }
}
