/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.block.Block;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.block.BlockAir;
import java.util.ArrayList;
import java.util.List;
import com.krazzzzymonkey.catalyst.managers.EnemyManager;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import net.minecraft.entity.player.EntityPlayer;
import java.awt.Color;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction$Action;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.Entity;

public class EntityUtils
{
    public static boolean isHostileMob(final Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false) && !isNeutralMob(entity);
    }
    
    public static boolean isPassive(final Entity entity) {
        return (!(entity instanceof EntityWolf) || !((EntityWolf)entity).isAngry()) && (entity instanceof EntityAnimal || entity instanceof EntityAgeable || entity instanceof EntityTameable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid || (entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getRevengeTarget() == null));
    }
    
    public static boolean stopSneaking(final boolean b) {
        if (b && Minecraft.getMinecraft().player != null) {
            Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Minecraft.getMinecraft().player, CPacketEntityAction$Action.STOP_SNEAKING));
        }
        return false;
    }
    
    public static boolean isBlockValid(final BlockPos blockPos) {
        if (!isBedrockHole(blockPos)) {
            if (!isObbyHole(blockPos)) {
                if (!isBothHole(blockPos)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isInWater(final Entity entity) {
        if (entity == null) {
            return false;
        }
        final double n = entity.posY + 0.01;
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (Minecraft.getMinecraft().world.getBlockState(new BlockPos(i, (int)n, j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isInHole(final Entity entity) {
        return isBlockValid(new BlockPos(entity.posX, entity.posY, entity.posZ));
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n, final double n2, final double n3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n2, (entity.posZ - entity.lastTickPosZ) * n3);
    }
    
    public static double sigmoid(final double n) {
        return 1.0 / (1.0 + Math.pow(2.718281828459045, -1.0 * n));
    }
    
    public static boolean is32k(final ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.getTagCompound() == null) {
            return false;
        }
        final NBTTagList list = (NBTTagList)itemStack.getTagCompound().getTag("ench");
        if (list == null) {
            return false;
        }
        int i = 0;
        while (i < list.tagCount()) {
            final NBTTagCompound compoundTag = list.getCompoundTagAt(i);
            if (compoundTag.getInteger("id") == 16) {
                if (compoundTag.getInteger("lvl") >= 42) {
                    return true;
                }
                return false;
            }
            else {
                ++i;
            }
        }
        return false;
    }
    
    public static boolean isFriendlyMob(final Entity entity) {
        return (entity.isCreatureType(EnumCreatureType.CREATURE, false) && !isNeutralMob(entity)) || entity.isCreatureType(EnumCreatureType.AMBIENT, false) || entity instanceof EntityVillager || entity instanceof EntityIronGolem || (isNeutralMob(entity) && !isMobAggressive(entity));
    }
    
    public static boolean isAboveWater(final Entity entity, final boolean b) {
        if (entity == null) {
            return false;
        }
        final double n = entity.posY - (b ? 0.03 : (isPlayer(entity) ? 0.2 : 0.5));
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (Minecraft.getMinecraft().world.getBlockState(new BlockPos(i, MathHelper.floor(n), j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static Color getColor(final Entity entity, final int n, final int n2, final int n3, final int n4, final boolean b) {
        Color color = new Color(n / 255.0f, n2 / 255.0f, n3 / 255.0f, n4 / 255.0f);
        if (entity instanceof EntityPlayer) {
            if (b && FriendManager.friendsList.contains(entity.getName())) {
                color = new Color(0.33f, 1.0f, 1.0f, n4 / 255.0f);
            }
            if (b && EnemyManager.enemysList.contains(entity.getName())) {
                color = new Color(1.0f, 0.33f, 1.0f, n4 / 255.0f);
            }
        }
        return color;
    }
    
    public static boolean holding32k(final EntityPlayer entityPlayer) {
        return is32k(entityPlayer.getHeldItemMainhand());
    }
    
    public static double predictPos(final double a, final double n) {
        return sigmoid(Math.abs(a) - n);
    }
    
    public static List getUnsafeBlocks(final Entity entity, final int n, final boolean b) {
        return getUnsafeBlocksFromVec3d(entity.getPositionVector(), n, b);
    }
    
    public static BlockPos getFlooredPos(final Entity entity) {
        return new BlockPos(Math.floor(entity.posX), Math.floor(entity.posY), Math.floor(entity.posZ));
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, n));
    }
    
    public static List getOffsetList(final int n, final boolean b) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        list.add(new Vec3d(-1.0, (double)n, 0.0));
        list.add(new Vec3d(1.0, (double)n, 0.0));
        list.add(new Vec3d(0.0, (double)n, -1.0));
        list.add(new Vec3d(0.0, (double)n, 1.0));
        if (b) {
            final ArrayList<Vec3d> list2 = list;
            final double n2 = 0.0;
            final int n3 = 1;
            list2.add(new Vec3d(n2, (double)((n & ~n3) + (n3 & ~n)), 0.0));
        }
        return list;
    }
    
    public static boolean isntValid(final Entity entity, final double n) {
        return entity == null || !entity.isEntityAlive() || entity.equals((Object)Minecraft.getMinecraft().player) || (entity instanceof EntityPlayer && FriendManager.friendsList.contains(entity.getName())) || Minecraft.getMinecraft().player.getDistanceSq(entity) > n * n;
    }
    
    public static Vec3d[] getOffsets(final int n, final boolean b) {
        final List offsetList = getOffsetList(n, b);
        return offsetList.toArray(new Vec3d[offsetList.size()]);
    }
    
    public static boolean isInLiquid() {
        if (Minecraft.getMinecraft().player == null) {
            return false;
        }
        if (Minecraft.getMinecraft().player.fallDistance >= 3.0f) {
            return false;
        }
        boolean b = false;
        final AxisAlignedBB axisAlignedBB = (Minecraft.getMinecraft().player.getRidingEntity() != null) ? Minecraft.getMinecraft().player.getRidingEntity().getEntityBoundingBox() : Minecraft.getMinecraft().player.getEntityBoundingBox();
        final int n = (int)axisAlignedBB.minY;
        for (int i = MathHelper.floor(axisAlignedBB.minX); i < MathHelper.floor(axisAlignedBB.maxX) + 1; ++i) {
            for (int j = MathHelper.floor(axisAlignedBB.minZ); j < MathHelper.floor(axisAlignedBB.maxZ) + 1; ++j) {
                final Block block = Minecraft.getMinecraft().world.getBlockState(new BlockPos(i, n, j)).getBlock();
                if (!(block instanceof BlockAir)) {
                    if (!(block instanceof BlockLiquid)) {
                        return false;
                    }
                    b = true;
                }
            }
        }
        return b;
    }
    
    public static double[] calculateLookAt(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double n4 = entityPlayer.posX - n;
        final double n5 = entityPlayer.posY - n2;
        final double n6 = entityPlayer.posZ - n3;
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
        return new double[] { Math.atan2(n6 / sqrt, n4 / sqrt) * 180.0 / 3.141592653589793 + 90.0, Math.asin(n5 / sqrt) * 180.0 / 3.141592653589793 };
    }
    
    public static double getRelativeX(final float n) {
        return MathHelper.sin(-n * 0.017453292f);
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float n) {
        return getInterpolatedPos(entity, n).subtract(Minecraft.getMinecraft().getRenderManager().renderPosX, Minecraft.getMinecraft().getRenderManager().renderPosY, Minecraft.getMinecraft().getRenderManager().renderPosZ);
    }
    
    public static void attackEntity(final Entity entity, final boolean b) {
        if (b) {
            Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            Minecraft.getMinecraft().playerController.attackEntity((EntityPlayer)Minecraft.getMinecraft().player, entity);
        }
    }
    
    public static Vec3d[] getUnsafeBlockArrayFromVec3d(final Vec3d vec3d, final int n, final boolean b) {
        while (true) {
            switch (-241007227 - 1345894606 + 1) {
                case 1158972917: {
                    continue;
                }
                default: {
                    final List unsafeBlocksFromVec3d = getUnsafeBlocksFromVec3d(vec3d, n, b);
                    return unsafeBlocksFromVec3d.toArray(new Vec3d[unsafeBlocksFromVec3d.size()]);
                }
                case -719422044: {
                    throw null;
                }
            }
        }
    }
    
    public static double getRelativeZ(final float n) {
        return MathHelper.cos(n * 0.017453292f);
    }
    
    public static boolean isBothHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState blockState = Minecraft.getMinecraft().world.getBlockState(array[i]);
            if (blockState.getBlock() == Blocks.AIR || (blockState.getBlock() != Blocks.BEDROCK && blockState.getBlock() != Blocks.OBSIDIAN)) {
                return false;
            }
        }
        return true;
    }
    
    public static void setTimer(final float n) {
        Minecraft.getMinecraft().timer.tickLength = 50.0f / n;
    }
    
    public static Vec3d getInterpolatedLinearVec(final Entity entity, final float n) {
        return new Vec3d(lerp(entity.lastTickPosX, entity.posX, n), lerp(entity.lastTickPosY, entity.posY, n), lerp(entity.lastTickPosZ, entity.posZ, n));
    }
    
    public static void resetTimer() {
        Minecraft.getMinecraft().timer.tickLength = 50.0f;
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final Vec3d vec3d) {
        return getInterpolatedAmount(entity, vec3d.x, vec3d.y, vec3d.z);
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(Minecraft.getMinecraft().player.posX), Math.floor(Minecraft.getMinecraft().player.posY), Math.floor(Minecraft.getMinecraft().player.posZ));
    }
    
    public static boolean isAboveBlock(final Entity entity, final BlockPos blockPos) {
        return entity.posY >= blockPos.getY();
    }
    
    public static boolean isPlayer(final Entity entity) {
        return entity instanceof EntityPlayer;
    }
    
    public static BlockPos getPlayerPosWithEntity() {
        return new BlockPos((Minecraft.getMinecraft().player.getRidingEntity() != null) ? Minecraft.getMinecraft().player.getRidingEntity().posX : Minecraft.getMinecraft().player.posX, (Minecraft.getMinecraft().player.getRidingEntity() != null) ? Minecraft.getMinecraft().player.getRidingEntity().posY : Minecraft.getMinecraft().player.posY, (Minecraft.getMinecraft().player.getRidingEntity() != null) ? Minecraft.getMinecraft().player.getRidingEntity().posZ : Minecraft.getMinecraft().player.posZ);
    }
    
    public static boolean canEntityFeetBeSeen(final Entity entity) {
        return Minecraft.getMinecraft().world.rayTraceBlocks(new Vec3d(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posX + Minecraft.getMinecraft().player.getEyeHeight(), Minecraft.getMinecraft().player.posZ), new Vec3d(entity.posX, entity.posY, entity.posZ), false, true, false) == null;
    }
    
    public static void attackEntity(final Entity entity, final boolean b, final boolean b2) {
        if (b) {
            Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            Minecraft.getMinecraft().playerController.attackEntity((EntityPlayer)Minecraft.getMinecraft().player, entity);
        }
        if (b2) {
            Minecraft.getMinecraft().player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public static boolean isFakeLocalPlayer(final Entity entity) {
        return entity != null && entity.getEntityId() == -100 && Minecraft.getMinecraft().player != entity;
    }
    
    public static Vec3d[] getUnsafeBlockArray(final Entity entity, final int n, final boolean b) {
        final List unsafeBlocks = getUnsafeBlocks(entity, n, b);
        return unsafeBlocks.toArray(new Vec3d[unsafeBlocks.size()]);
    }
    
    public static boolean checkForLiquid(final Entity entity, final boolean b) {
        if (entity == null) {
            return false;
        }
        final double posY = entity.posY;
        double n;
        if (b) {
            n = 0.03;
        }
        else if (entity instanceof EntityPlayer) {
            n = 0.2;
        }
        else {
            n = 0.5;
        }
        final double n2 = posY - n;
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (Minecraft.getMinecraft().world.getBlockState(new BlockPos(i, MathHelper.floor(n2), j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static float getHealth(final Entity entity) {
        if (entity.isEntityAlive()) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + entityLivingBase.getAbsorptionAmount();
        }
        return 0.0f;
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n) {
        return getInterpolatedAmount(entity, n, n, n);
    }
    
    public static boolean isMobAggressive(final Entity entity) {
        if (entity instanceof EntityPigZombie) {
            if (((EntityPigZombie)entity).isArmsRaised() || ((EntityPigZombie)entity).isAngry()) {
                return true;
            }
        }
        else {
            if (entity instanceof EntityWolf) {
                return ((EntityWolf)entity).isAngry() && !Minecraft.getMinecraft().player.equals((Object)((EntityWolf)entity).getOwner());
            }
            if (entity instanceof EntityEnderman) {
                return ((EntityEnderman)entity).isScreaming();
            }
        }
        return isHostileMob(entity);
    }
    
    public static boolean isLiving(final Entity entity) {
        return entity instanceof EntityLivingBase;
    }
    
    public static Block isColliding(final double n, final double n2, final double n3) {
        Block block = null;
        if (Minecraft.getMinecraft().player != null) {
            final AxisAlignedBB axisAlignedBB = (Minecraft.getMinecraft().player.getRidingEntity() != null) ? Minecraft.getMinecraft().player.getRidingEntity().getEntityBoundingBox().contract(0.0, 0.0, 0.0).offset(n, n2, n3) : Minecraft.getMinecraft().player.getEntityBoundingBox().contract(0.0, 0.0, 0.0).offset(n, n2, n3);
            final int n4 = (int)axisAlignedBB.minY;
            for (int i = MathHelper.floor(axisAlignedBB.minX); i < MathHelper.floor(axisAlignedBB.maxX) + 1; ++i) {
                for (int j = MathHelper.floor(axisAlignedBB.minZ); j < MathHelper.floor(axisAlignedBB.maxZ) + 1; ++j) {
                    block = Minecraft.getMinecraft().world.getBlockState(new BlockPos(i, n4, j)).getBlock();
                }
            }
        }
        return block;
    }
    
    public static boolean isSafe(final Entity entity, final int n, final boolean b) {
        return getUnsafeBlocks(entity, n, b).size() == 0;
    }
    
    public static double lerp(final double n, final double n2, final float n3) {
        return n + (n2 - n) * n3;
    }
    
    public static List getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int x = blockPos.getX();
        final int y = blockPos.getY();
        final int z = blockPos.getZ();
        for (int n4 = x - (int)n; n4 <= x + n; ++n4) {
            for (int n5 = z - (int)n; n5 <= z + n; ++n5) {
                for (int n6 = b2 ? (y - (int)n) : y; n6 < (b2 ? (y + n) : ((float)(y + n2))); ++n6) {
                    final double n7 = (x - n4) * (x - n4) + (z - n5) * (z - n5) + (b2 ? ((y - n6) * (y - n6)) : 0);
                    if (n7 < n * n && (!b || n7 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                }
            }
        }
        return list;
    }
    
    public static boolean isBedrockHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState blockState = Minecraft.getMinecraft().world.getBlockState(array[i]);
            if (blockState.getBlock() == Blocks.AIR || blockState.getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
    
    public static Vec3d interpolateEntity(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n);
    }
    
    public static boolean isObbyHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        final int length = array.length;
        int i = 0;
        while (i < length) {
            final IBlockState blockState = Minecraft.getMinecraft().world.getBlockState(array[i]);
            if (blockState.getBlock() != Blocks.AIR) {
                if (blockState.getBlock() == Blocks.OBSIDIAN) {
                    ++i;
                    continue;
                }
            }
            return false;
        }
        return true;
    }
    
    public static BlockPos getRoundedBlockPos(final Entity entity) {
        return new BlockPos(MathUtils.roundVec(entity.getPositionVector(), 0));
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final float n) {
        return getInterpolatedAmount(entity, n, n, n);
    }
    
    public static boolean isNeutralMob(final Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }
    
    public static boolean isAboveLiquid(final Entity entity) {
        if (entity == null) {
            return false;
        }
        final double n = entity.posY + 0.01;
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (Minecraft.getMinecraft().world.getBlockState(new BlockPos(i, (int)n, j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static List getUnsafeBlocksFromVec3d(final Vec3d vec3d, final int n, final boolean b) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        for (final Vec3d e : getOffsets(n, b)) {
            final Block block = Minecraft.getMinecraft().world.getBlockState(new BlockPos(vec3d).add(e.x, e.y, e.z)).getBlock();
            Label_0346: {
                if (!(block instanceof BlockAir)) {
                    if (!(block instanceof BlockLiquid) && !(block instanceof BlockTallGrass)) {
                        if (!(block instanceof BlockFire)) {
                            if (!(block instanceof BlockDeadBush)) {
                                if (!(block instanceof BlockSnow)) {
                                    break Label_0346;
                                }
                            }
                        }
                    }
                }
                list.add(e);
            }
        }
        return list;
    }
    
    public static boolean isOnLiquid(final double n) {
        if (Minecraft.getMinecraft().player.fallDistance >= 3.0f) {
            return false;
        }
        final AxisAlignedBB axisAlignedBB = (Minecraft.getMinecraft().player.getRidingEntity() != null) ? Minecraft.getMinecraft().player.getRidingEntity().getEntityBoundingBox().contract(0.0, 0.0, 0.0).offset(0.0, -n, 0.0) : Minecraft.getMinecraft().player.getEntityBoundingBox().contract(0.0, 0.0, 0.0).offset(0.0, -n, 0.0);
        boolean b = false;
        final int n2 = (int)axisAlignedBB.minY;
        for (int i = MathHelper.floor(axisAlignedBB.minX); i < MathHelper.floor(axisAlignedBB.maxX + 1.0); ++i) {
            for (int j = MathHelper.floor(axisAlignedBB.minZ); j < MathHelper.floor(axisAlignedBB.maxZ + 1.0); ++j) {
                final Block block = Minecraft.getMinecraft().world.getBlockState(new BlockPos(i, n2, j)).getBlock();
                if (block != Blocks.AIR) {
                    if (!(block instanceof BlockLiquid)) {
                        return false;
                    }
                    b = true;
                }
            }
        }
        return b;
    }
    
    public static boolean isDrivenByPlayer(final Entity entity) {
        return Minecraft.getMinecraft().player != null && entity != null && entity.equals((Object)Minecraft.getMinecraft().player.getRidingEntity());
    }
    
    public static boolean checkCollide() {
        return !Minecraft.getMinecraft().player.isSneaking() && (Minecraft.getMinecraft().player.getRidingEntity() == null || Minecraft.getMinecraft().player.getRidingEntity().fallDistance < 3.0f) && Minecraft.getMinecraft().player.fallDistance < 3.0f;
    }
}
