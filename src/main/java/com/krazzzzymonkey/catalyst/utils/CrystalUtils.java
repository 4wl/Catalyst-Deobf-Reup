/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import java.util.Arrays;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.CombatRules;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import net.minecraft.world.Explosion;
import net.minecraft.entity.EntityLivingBase;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.Collection;
import net.minecraft.util.NonNullList;
import java.util.List;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.init.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import java.util.Iterator;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;

public class CrystalUtils
{
    public static Boolean getArmourFucker(final EntityPlayer entityPlayer, final float n) {
        for (final ItemStack itemStack : entityPlayer.getArmorInventoryList()) {
            if (itemStack == null || itemStack.getItem() == Items.AIR) {
                return true;
            }
            if (n >= (itemStack.getMaxDamage() - itemStack.getItemDamage()) / (float)itemStack.getMaxDamage() * 100.0f && itemStack.stackSize < 2) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean lambda$possiblePlacePositions$1(final boolean b, final boolean b2, final BlockPos blockPos) {
        return canPlaceCrystal(blockPos, b, b2);
    }
    
    public static boolean rayTraceSolidCheck(Vec3d vec3d, final Vec3d vec3d2, final boolean b) {
        if (!Double.isNaN(vec3d.x) && !Double.isNaN(vec3d.y) && !Double.isNaN(vec3d.z) && !Double.isNaN(vec3d2.x) && !Double.isNaN(vec3d2.y) && !Double.isNaN(vec3d2.z)) {
            int floor = MathHelper.floor(vec3d.x);
            int floor2 = MathHelper.floor(vec3d.y);
            int floor3 = MathHelper.floor(vec3d.z);
            final int floor4 = MathHelper.floor(vec3d2.x);
            final int floor5 = MathHelper.floor(vec3d2.y);
            final int floor6 = MathHelper.floor(vec3d2.z);
            final BlockPos blockPos = new BlockPos(floor, floor2, floor3);
            final IBlockState blockState = Minecraft.getMinecraft().world.getBlockState(blockPos);
            final Block block = blockState.getBlock();
            if (blockState.getCollisionBoundingBox((IBlockAccess)Minecraft.getMinecraft().world, blockPos) != Block.NULL_AABB) {
                if (block.canCollideCheck(blockState, false) && (getBlocks().contains(block) || !b)) {
                    return true;
                }
            }
            final double n = vec3d2.x - vec3d.x;
            final double n2 = vec3d2.y - vec3d.y;
            final double n3 = vec3d2.z - vec3d.z;
            int n4 = 200;
            while (n4-- >= 0) {
                if (!Double.isNaN(vec3d.x)) {
                    if (!Double.isNaN(vec3d.y) && !Double.isNaN(vec3d.z)) {
                        if (floor == floor4) {
                            if (floor2 == floor5) {
                                if (floor3 == floor6) {
                                    return false;
                                }
                            }
                        }
                        boolean b2 = true;
                        boolean b3 = true;
                        boolean b4 = true;
                        double n5 = 999.0;
                        double n6 = 999.0;
                        double n7 = 999.0;
                        double n8 = 999.0;
                        double n9 = 999.0;
                        double n10 = 999.0;
                        if (floor4 > floor) {
                            n5 = floor + 1.0;
                        }
                        else if (floor4 < floor) {
                            n5 = floor;
                        }
                        else {
                            b2 = false;
                        }
                        if (floor5 > floor2) {
                            n6 = floor2 + 1.0;
                        }
                        else if (floor5 < floor2) {
                            n6 = floor2;
                        }
                        else {
                            b3 = false;
                        }
                        if (floor6 > floor3) {
                            n7 = floor3 + 1.0;
                        }
                        else if (floor6 < floor3) {
                            n7 = floor3;
                        }
                        else {
                            b4 = false;
                        }
                        if (b2) {
                            n8 = (n5 - vec3d.x) / n;
                        }
                        if (b3) {
                            n9 = (n6 - vec3d.y) / n2;
                        }
                        if (b4) {
                            n10 = (n7 - vec3d.z) / n3;
                        }
                        if (n8 == 0.0) {
                            n8 = -1.0E-4;
                        }
                        if (n9 == 0.0) {
                            n9 = -1.0E-4;
                        }
                        if (n10 == 0.0) {
                            n10 = -1.0E-4;
                        }
                        EnumFacing enumFacing;
                        if (n8 < n9 && n8 < n10) {
                            enumFacing = ((floor4 > floor) ? EnumFacing.WEST : EnumFacing.EAST);
                            vec3d = new Vec3d(n5, vec3d.y + n2 * n8, vec3d.z + n3 * n8);
                        }
                        else if (n9 < n10) {
                            enumFacing = ((floor5 > floor2) ? EnumFacing.DOWN : EnumFacing.UP);
                            vec3d = new Vec3d(vec3d.x + n * n9, n6, vec3d.z + n3 * n9);
                        }
                        else {
                            enumFacing = ((floor6 > floor3) ? EnumFacing.NORTH : EnumFacing.SOUTH);
                            vec3d = new Vec3d(vec3d.x + n * n10, vec3d.y + n2 * n10, n7);
                        }
                        floor = MathHelper.floor(vec3d.x) - ((enumFacing == EnumFacing.EAST) ? 1 : 0);
                        floor2 = MathHelper.floor(vec3d.y) - ((enumFacing == EnumFacing.UP) ? 1 : 0);
                        floor3 = MathHelper.floor(vec3d.z) - ((enumFacing == EnumFacing.SOUTH) ? 1 : 0);
                        final IBlockState blockState2 = Minecraft.getMinecraft().world.getBlockState(new BlockPos(floor, floor2, floor3));
                        final Block block2 = blockState2.getBlock();
                        if (!block2.canCollideCheck(blockState2, false)) {
                            continue;
                        }
                        if (getBlocks().contains(block2) || !b) {
                            return true;
                        }
                        continue;
                        continue;
                    }
                }
                return false;
            }
        }
        return false;
    }
    
    public static float calculateDamage(final BlockPos blockPos, final Entity entity, final boolean b) {
        return getExplosionDamage(entity, new Vec3d(blockPos.getX() + 0.5, (double)(blockPos.getY() + 1), blockPos.getZ() + 0.5), 6.0f, b);
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final boolean b, final boolean b2) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 2, 0);
        try {
            if (!b2) {
                if (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (Minecraft.getMinecraft().world.getBlockState(add).getBlock() != Blocks.AIR || Minecraft.getMinecraft().world.getBlockState(add2).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (!b) {
                    return Minecraft.getMinecraft().world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).isEmpty() && Minecraft.getMinecraft().world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2)).isEmpty();
                }
                final Iterator iterator = Minecraft.getMinecraft().world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).iterator();
                while (iterator.hasNext()) {
                    if (iterator.next() instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
                final Iterator iterator2 = Minecraft.getMinecraft().world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2)).iterator();
                while (iterator2.hasNext()) {
                    if (iterator2.next() instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
            }
            else {
                if (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (Minecraft.getMinecraft().world.getBlockState(add).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (!b) {
                    return Minecraft.getMinecraft().world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).isEmpty();
                }
                final Iterator iterator3 = Minecraft.getMinecraft().world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).iterator();
                while (iterator3.hasNext()) {
                    if (iterator3.next() instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static Entity getPredictedPosition(final Entity entity, final double n) {
        if (n == 0.0) {
            return entity;
        }
        EntityPlayer entityPlayer = null;
        final double a = entity.posX - entity.lastTickPosX;
        double a2 = entity.posY - entity.lastTickPosY;
        final double a3 = entity.posZ - entity.lastTickPosZ;
        boolean b = false;
        boolean b2 = false;
        final double sqrt = Math.sqrt(Math.pow(a, 2.0) + Math.pow(a3, 2.0) + Math.pow(a2, 2.0));
        if (sqrt > 0.1) {
            b = true;
        }
        if (!b) {
            return entity;
        }
        if (sqrt > 0.31) {
            b2 = true;
        }
        for (int n2 = 0; n2 < n; ++n2) {
            if (entityPlayer == null) {
                if (isOnGround(0.0, 0.0, 0.0, entity)) {
                    a2 = (b2 ? 0.4 : -0.07840015258789);
                }
                else {
                    a2 = (a2 - 0.08) * 0.9800000190734863;
                }
                entityPlayer = placeValue(a, a2, a3, (EntityPlayer)entity);
            }
            else {
                if (isOnGround(0.0, 0.0, 0.0, (Entity)entityPlayer)) {
                    a2 = (b2 ? 0.4 : -0.07840015258789);
                }
                else {
                    a2 = (a2 - 0.08) * 0.9800000190734863;
                }
                entityPlayer = placeValue(a, a2, a3, entityPlayer);
            }
        }
        return (Entity)entityPlayer;
    }
    
    public static List possiblePlacePositions(final float n, final boolean b, final boolean b2) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(EntityUtils.getPlayerPos(), n, (int)n, false, true, 0).stream().filter(CrystalUtils::lambda$possiblePlacePositions$0).filter(CrystalUtils::lambda$possiblePlacePositions$1).collect(Collectors.toList()));
        return (List)create;
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
    
    public static float getExplosionDamage(final Entity entity, final Vec3d vec3d, float n, final boolean b) {
        final Vec3d vec3d2 = new Vec3d(entity.posX, entity.posY, entity.posZ);
        if (entity.isImmuneToExplosions()) {
            return 0.0f;
        }
        n *= 2.0f;
        final double n2 = vec3d2.distanceTo(vec3d) / n;
        double n3 = 0.0;
        final AxisAlignedBB offset = entity.getEntityBoundingBox().offset(entity.getPositionVector().subtract(vec3d2));
        final Vec3d vec3d3 = new Vec3d(1.0 / ((offset.maxX - offset.minX) * 2.0 + 1.0), 1.0 / ((offset.maxY - offset.minY) * 2.0 + 1.0), 1.0 / ((offset.maxZ - offset.minZ) * 2.0 + 1.0));
        final double n4 = (1.0 - Math.floor(1.0 / vec3d3.x) * vec3d3.x) / 2.0;
        final double n5 = (1.0 - Math.floor(1.0 / vec3d3.z) * vec3d3.z) / 2.0;
        if (vec3d3.x >= 0.0 && vec3d3.y >= 0.0 && vec3d3.z >= 0.0) {
            int n6 = 0;
            int n7 = 0;
            for (double n8 = 0.0; n8 <= 1.0; n8 += vec3d3.x) {
                for (double n9 = 0.0; n9 <= 1.0; n9 += vec3d3.y) {
                    for (double n10 = 0.0; n10 <= 1.0; n10 += vec3d3.z) {
                        if (!rayTraceSolidCheck(new Vec3d(n4 + offset.minX + (offset.maxX - offset.minX) * n8, offset.minY + (offset.maxY - offset.minY) * n9, n5 + offset.minZ + (offset.maxZ - offset.minZ) * n10), vec3d, b)) {
                            ++n6;
                        }
                        ++n7;
                    }
                }
            }
            n3 = n6 / (double)n7;
        }
        final double n11 = (1.0 - n2) * n3;
        float blastReduction = (float)(int)((n11 * n11 + n11) / 2.0 * 7.0 * n + 1.0);
        if (entity instanceof EntityLivingBase) {
            blastReduction = getBlastReduction((EntityLivingBase)entity, getDamageFromDifficulty(blastReduction), new Explosion((World)Minecraft.getMinecraft().world, (Entity)null, vec3d.x, vec3d.y, vec3d.z, n / 2.0f, false, true));
        }
        return blastReduction;
    }
    
    public static float calculateDamage(final Entity entity, final Entity entity2, final boolean b) {
        return getExplosionDamage(entity2, new Vec3d(entity.posX, entity.posY, entity.posZ), 6.0f, b);
    }
    
    public static float getDamageFromDifficulty(final float b) {
        switch (CrystalUtils$1.$SwitchMap$net$minecraft$world$EnumDifficulty[Minecraft.getMinecraft().world.getDifficulty().ordinal()]) {
            case 1: {
                return 0.0f;
            }
            case 2: {
                return Math.min(b / 2.0f + 1.0f, b);
            }
            case 3: {
                return b * 3.0f / 2.0f;
            }
            default: {
                return b;
            }
        }
    }
    
    public static double calculateXOffset(final AxisAlignedBB axisAlignedBB, double n, final AxisAlignedBB axisAlignedBB2) {
        if (axisAlignedBB.maxY > axisAlignedBB2.minY) {
            if (axisAlignedBB.minY < axisAlignedBB2.maxY) {
                if (axisAlignedBB.maxZ > axisAlignedBB2.minZ && axisAlignedBB.minZ < axisAlignedBB2.maxZ) {
                    if (n > 0.0 && axisAlignedBB.maxX <= axisAlignedBB2.minX) {
                        final double n2 = axisAlignedBB2.minX - 0.3 - axisAlignedBB.maxX;
                        if (n2 < n) {
                            n = n2;
                        }
                    }
                    else if (n < 0.0 && axisAlignedBB.minX >= axisAlignedBB2.maxX) {
                        final double n3 = axisAlignedBB2.maxX + 0.3 - axisAlignedBB.minX;
                        if (n3 > n) {
                            n = n3;
                        }
                    }
                }
            }
        }
        return n;
    }
    
    public static float getBlastReduction(final EntityLivingBase entityLivingBase, float a, final Explosion explosion) {
        a = CombatRules.getDamageAfterAbsorb(a, (float)entityLivingBase.getTotalArmorValue(), (float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        float n = 0.0f;
        try {
            n = (float)EnchantmentHelper.getEnchantmentModifierDamage(entityLivingBase.getArmorInventoryList(), DamageSource.causeExplosionDamage(explosion));
        }
        catch (Exception ex) {}
        a *= 1.0f - MathHelper.clamp(n, 0.0f, 20.0f) / 25.0f;
        final PotionEffect activePotionEffect = entityLivingBase.getActivePotionEffect(MobEffects.RESISTANCE);
        if (entityLivingBase.isPotionActive(MobEffects.RESISTANCE) && activePotionEffect != null) {
            a = a * (25.0f - (activePotionEffect.getAmplifier() + 1) * 5.0f) / 25.0f;
        }
        a = Math.max(a, 0.0f);
        return a;
    }
    
    public static boolean lambda$possiblePlacePositions$0(final BlockPos blockPos) {
        return Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() != Blocks.AIR;
    }
    
    public static List getBlocks() {
        return Arrays.asList(Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.COMMAND_BLOCK, Blocks.BARRIER, Blocks.ENCHANTING_TABLE, Blocks.ENDER_CHEST, Blocks.END_PORTAL_FRAME, (Block)Blocks.BEACON, Blocks.ANVIL);
    }
    
    public static EntityPlayer placeValue(double calculateXOffset, double calculateYOffset, double calculateZOffset, final EntityPlayer entityPlayer) {
        final List collisionBoxes = Minecraft.getMinecraft().world.getCollisionBoxes((Entity)entityPlayer, entityPlayer.getEntityBoundingBox().expand(calculateXOffset, calculateYOffset, calculateZOffset));
        if (calculateYOffset != 0.0) {
            for (int i = 0; i < collisionBoxes.size(); ++i) {
                calculateYOffset = collisionBoxes.get(i).calculateYOffset(entityPlayer.getEntityBoundingBox(), calculateYOffset);
            }
            if (calculateYOffset != 0.0) {
                entityPlayer.setEntityBoundingBox(entityPlayer.getEntityBoundingBox().offset(0.0, calculateYOffset, 0.0));
            }
        }
        if (calculateXOffset != 0.0) {
            for (int j = 0; j < collisionBoxes.size(); ++j) {
                calculateXOffset = calculateXOffset(entityPlayer.getEntityBoundingBox(), calculateXOffset, collisionBoxes.get(j));
            }
            if (calculateXOffset != 0.0) {
                entityPlayer.setEntityBoundingBox(entityPlayer.getEntityBoundingBox().offset(calculateXOffset, 0.0, 0.0));
            }
        }
        if (calculateZOffset != 0.0) {
            for (int k = 0; k < collisionBoxes.size(); ++k) {
                calculateZOffset = calculateZOffset(entityPlayer.getEntityBoundingBox(), calculateZOffset, collisionBoxes.get(k));
            }
            if (calculateZOffset != 0.0) {
                entityPlayer.setEntityBoundingBox(entityPlayer.getEntityBoundingBox().offset(0.0, 0.0, calculateZOffset));
            }
        }
        return entityPlayer;
    }
    
    public static float getDamageMultiplied(final float n) {
        final int difficultyId = Minecraft.getMinecraft().world.getDifficulty().getDifficultyId();
        return n * ((difficultyId == 0) ? 0.0f : ((difficultyId == 2) ? 1.0f : ((difficultyId == 1) ? 0.5f : 1.5f)));
    }
    
    public static boolean canSeePos(final BlockPos blockPos) {
        return Minecraft.getMinecraft().world.rayTraceBlocks(new Vec3d(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight(), Minecraft.getMinecraft().player.posZ), new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()), false, true, false) == null;
    }
    
    public static boolean isOnGround(final double n, double calculateYOffset, final double n2, final Entity entity) {
        try {
            final double n3 = calculateYOffset;
            final List collisionBoxes = Minecraft.getMinecraft().world.getCollisionBoxes(entity, entity.getEntityBoundingBox().expand(n, calculateYOffset, n2));
            if (calculateYOffset != 0.0) {
                for (int i = 0; i < collisionBoxes.size(); ++i) {
                    calculateYOffset = collisionBoxes.get(i).calculateYOffset(entity.getEntityBoundingBox(), calculateYOffset);
                }
            }
            return n3 != calculateYOffset && n3 < 0.0;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static double calculateZOffset(final AxisAlignedBB axisAlignedBB, double n, final AxisAlignedBB axisAlignedBB2) {
        if (axisAlignedBB.maxX > axisAlignedBB2.minX && axisAlignedBB.minX < axisAlignedBB2.maxX && axisAlignedBB.maxY > axisAlignedBB2.minY && axisAlignedBB.minY < axisAlignedBB2.maxY) {
            if (n > 0.0 && axisAlignedBB.maxZ <= axisAlignedBB2.minZ) {
                final double n2 = axisAlignedBB2.minZ - 0.3 - axisAlignedBB.maxZ;
                if (n2 < n) {
                    n = n2;
                }
            }
            else if (n < 0.0 && axisAlignedBB.minZ >= axisAlignedBB2.maxZ) {
                final double n3 = axisAlignedBB2.maxZ + 0.3 - axisAlignedBB.minZ;
                if (n3 > n) {
                    n = n3;
                }
            }
        }
        return n;
    }
}
