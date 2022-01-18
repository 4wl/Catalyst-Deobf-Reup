/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraft.network.play.client.CPacketPlayer$Position;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction$Action;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.entity.projectile.EntityEgg;
import java.lang.reflect.Field;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.EnumFacing;
import net.minecraft.item.ItemArmor$ArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.BlockAir;
import java.math.RoundingMode;
import java.math.BigDecimal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;
import java.util.Iterator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import com.mojang.authlib.GameProfile;
import net.minecraft.init.Items;
import com.krazzzzymonkey.catalyst.managers.EnemyManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.lang.reflect.Method;
import net.minecraft.client.entity.EntityPlayerSP;
import com.krazzzzymonkey.catalyst.utils.system.Mapping;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import java.util.Random;

public class Utils
{
    public static float[] rotationsToBlock;
    public static boolean lookChanged;
    public static Random RANDOM;
    
    public static float updateRotation(final float n, final float n2, final float n3) {
        float wrapDegrees = MathHelper.wrapDegrees(n2 - n);
        if (wrapDegrees > n3) {
            wrapDegrees = n3;
        }
        if (wrapDegrees < -n3) {
            wrapDegrees = -n3;
        }
        return n + wrapDegrees;
    }
    
    public static void faceEntity(final EntityLivingBase entityLivingBase) {
        if (entityLivingBase == null) {
            return;
        }
        final double x = entityLivingBase.posX - Wrapper.INSTANCE.player().posX;
        final double n = entityLivingBase.posY - Wrapper.INSTANCE.player().posY;
        final double y = entityLivingBase.posZ - Wrapper.INSTANCE.player().posZ;
        final double y2 = Wrapper.INSTANCE.player().posY + Wrapper.INSTANCE.player().getEyeHeight() - (entityLivingBase.posY + entityLivingBase.getEyeHeight());
        final double x2 = MathHelper.sqrt(x * x + y * y);
        final float rotationYaw = (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float rotationPitch = (float)(-(Math.atan2(y2, x2) * 180.0 / 3.141592653589793));
        Wrapper.INSTANCE.player().rotationYaw = rotationYaw;
        Wrapper.INSTANCE.player().rotationPitch = rotationPitch;
    }
    
    public static void faceVectorPacket(final Vec3d vec3d) {
        final float[] neededRotations = getNeededRotations(vec3d);
        final EntityPlayerSP player = Minecraft.getMinecraft().player;
        final float rotationYaw = player.rotationYaw;
        final float rotationPitch = player.rotationPitch;
        player.rotationYaw = neededRotations[0];
        player.rotationPitch = neededRotations[1];
        try {
            final Method declaredMethod = player.getClass().getDeclaredMethod(Mapping.onUpdateWalkingPlayer, (Class<?>[])new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(player, new Object[0]);
        }
        catch (Exception ex) {}
        player.rotationYaw = rotationYaw;
        player.rotationPitch = rotationPitch;
    }
    
    public static void copy(final String data) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(data), null);
    }
    
    static {
        Utils.rotationsToBlock = null;
        Utils.RANDOM = new Random();
    }
    
    public static float getPitch(final Entity entity) {
        final double n = entity.posX - Wrapper.INSTANCE.player().posX;
        final double n2 = entity.posY - Wrapper.INSTANCE.player().posY;
        final double n3 = entity.posZ - Wrapper.INSTANCE.player().posZ;
        return (float)(-(Math.asin(n2 / Wrapper.INSTANCE.player().getDistance(entity)) * 57.29577951308232));
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec3d) {
        Utils.rotationsToBlock = getNeededRotations(vec3d);
    }
    
    public static String getPlayerName(final EntityPlayer entityPlayer) {
        return (entityPlayer.getGameProfile() != null) ? entityPlayer.getGameProfile().getName() : entityPlayer.getName();
    }
    
    public static Vec3d getCenter(final AxisAlignedBB axisAlignedBB) {
        return new Vec3d(axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX), axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY), axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ));
    }
    
    public static void mysteryFind(final EntityLivingBase entityLivingBase, final int n) {
        if (n == 0) {
            if (!EnemyManager.murders.isEmpty()) {
                for (int i = 0; i < EnemyManager.murders.size(); ++i) {
                    if (getWorldEntityByName(EnemyManager.murders.get(i)) == null) {
                        EnemyManager.murders.remove(i);
                    }
                }
            }
        }
        else if (n == 1) {
            if (!EnemyManager.detects.isEmpty()) {
                for (int j = 0; j < EnemyManager.detects.size(); ++j) {
                    if (getWorldEntityByName(EnemyManager.detects.get(j)) == null) {
                        EnemyManager.detects.remove(j);
                    }
                }
            }
        }
        if (entityLivingBase instanceof EntityPlayerSP) {
            return;
        }
        if (!(entityLivingBase instanceof EntityPlayer)) {
            return;
        }
        final EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
        if (entityPlayer.getGameProfile() == null) {
            return;
        }
        final GameProfile gameProfile = entityPlayer.getGameProfile();
        if (gameProfile.getName() == null) {
            return;
        }
        if (EnemyManager.murders.contains(gameProfile.getName()) || EnemyManager.detects.contains(gameProfile.getName())) {
            return;
        }
        if (entityPlayer.inventory == null) {
            return;
        }
    Label_1032:
        for (int k = 0; k < 36; ++k) {
            final ItemStack stackInSlot = entityPlayer.inventory.getStackInSlot(k);
            if (stackInSlot != null) {
                final Item item = stackInSlot.getItem();
                if (item != null) {
                    while (true) {
                        switch (1974556128 + 2043058712 + 1) {
                            case 1537103769: {
                                continue;
                            }
                            default: {
                                if (n == 0) {
                                    if (item != Items.IRON_SWORD && item != Items.DIAMOND_SWORD && item != Items.GOLDEN_SWORD && item != Items.STONE_SWORD && item != Items.WOODEN_SWORD) {
                                        if (item != Items.IRON_SHOVEL) {
                                            if (item != Items.DIAMOND_SHOVEL && item != Items.GOLDEN_SHOVEL && item != Items.STONE_SHOVEL && item != Items.WOODEN_SHOVEL && item != Items.IRON_AXE && item != Items.DIAMOND_AXE && item != Items.GOLDEN_AXE && item != Items.STONE_AXE && item != Items.WOODEN_AXE && item != Items.IRON_PICKAXE && item != Items.DIAMOND_PICKAXE && item != Items.GOLDEN_PICKAXE && item != Items.STONE_PICKAXE && item != Items.WOODEN_PICKAXE && item != Items.IRON_HOE && item != Items.DIAMOND_HOE && item != Items.GOLDEN_HOE && item != Items.STONE_HOE && item != Items.WOODEN_HOE && item != Items.STICK && item != Items.BLAZE_ROD && item != Items.FISHING_ROD && item != Items.CARROT && item != Items.GOLDEN_CARROT && item != Items.BONE && item != Items.COOKIE && item != Items.FEATHER && item != Items.PUMPKIN_PIE && item != Items.COOKED_FISH && item != Items.FISH && item != Items.SHEARS && item != Items.CARROT_ON_A_STICK) {
                                                continue Label_1032;
                                            }
                                        }
                                    }
                                    EnemyManager.murders.add(entityPlayer.getGameProfile().getName());
                                    continue Label_1032;
                                }
                                if (n == 1 && item == Items.BOW) {
                                    EnemyManager.detects.add(entityPlayer.getGameProfile().getName());
                                    continue Label_1032;
                                }
                                continue Label_1032;
                            }
                            case -1151101058: {
                                throw null;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static float[] getDirectionToEntity(final Entity entity) {
        return new float[] { getYaw(entity) + Wrapper.INSTANCE.player().rotationYaw, getPitch(entity) + Wrapper.INSTANCE.player().rotationPitch };
    }
    
    public static EntityLivingBase getWorldEntityByName(final String s) {
        EntityLivingBase entityLivingBase = null;
        for (final EntityLivingBase next : Wrapper.INSTANCE.world().loadedEntityList) {
            if (next instanceof EntityLivingBase) {
                final EntityLivingBase entityLivingBase2 = next;
                if (!entityLivingBase2.getName().contains(s)) {
                    continue;
                }
                entityLivingBase = entityLivingBase2;
            }
        }
        return entityLivingBase;
    }
    
    public static float getYaw(final Entity entity) {
        final double y = entity.posX - Wrapper.INSTANCE.player().posX;
        final double n = entity.posY - Wrapper.INSTANCE.player().posY;
        return (float)(-(Math.atan2(y, entity.posZ - Wrapper.INSTANCE.player().posZ) * 57.29577951308232));
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY + Wrapper.INSTANCE.player().getEyeHeight(), Wrapper.INSTANCE.player().posZ);
    }
    
    public static boolean checkScreen() {
        if (!(Wrapper.INSTANCE.mc().currentScreen instanceof GuiContainer)) {
            if (!(Wrapper.INSTANCE.mc().currentScreen instanceof GuiChat)) {
                if (!(Wrapper.INSTANCE.mc().currentScreen instanceof GuiScreen)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isDetect(final EntityLivingBase entityLivingBase) {
        mysteryFind(entityLivingBase, 1);
        if (!EnemyManager.detects.isEmpty() && entityLivingBase instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            final Iterator<String> iterator = EnemyManager.detects.iterator();
            while (iterator.hasNext()) {
                if (entityPlayer.getGameProfile().getName().equals(iterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return Wrapper.INSTANCE.world().getBlockState(blockPos).getBlock().canCollideCheck(Wrapper.INSTANCE.world().getBlockState(blockPos), false);
    }
    
    public static boolean isPlayer(final Entity entity) {
        return entity instanceof EntityPlayer && getPlayerName((EntityPlayer)entity).equals(getPlayerName((EntityPlayer)Wrapper.INSTANCE.player()));
    }
    
    public static float[] getRotationsNeeded(final Entity entity) {
        if (entity == null) {
            return null;
        }
        final double x = entity.posX - Wrapper.INSTANCE.mc().player.posX;
        final double y = entity.posZ - Wrapper.INSTANCE.mc().player.posZ;
        double y2;
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            y2 = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Wrapper.INSTANCE.mc().player.posY + Wrapper.INSTANCE.mc().player.getEyeHeight());
        }
        else {
            y2 = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0 - (Wrapper.INSTANCE.mc().player.posY + Wrapper.INSTANCE.mc().player.getEyeHeight());
        }
        return new float[] { Wrapper.INSTANCE.mc().player.rotationYaw + MathHelper.wrapDegrees((float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f - Wrapper.INSTANCE.mc().player.rotationYaw), Wrapper.INSTANCE.mc().player.rotationPitch + MathHelper.wrapDegrees((float)(-(Math.atan2(y2, MathHelper.sqrt(x * x + y * y)) * 180.0 / 3.141592653589793)) - Wrapper.INSTANCE.mc().player.rotationPitch) };
    }
    
    public static float[] getSmoothNeededRotations(final Vec3d vec3d, final float n, final float n2) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { updateRotation(Wrapper.INSTANCE.player().rotationYaw, (float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f, n / 4.0f), updateRotation(Wrapper.INSTANCE.player().rotationPitch, (float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))), n2 / 4.0f) };
    }
    
    public static double round(final double val, final int newScale) {
        if (newScale < 0) {
            throw new IllegalArgumentException();
        }
        return new BigDecimal(val).setScale(newScale, RoundingMode.HALF_UP).doubleValue();
    }
    
    public static boolean isInsideBlock(final EntityLivingBase entityLivingBase) {
        for (int i = MathHelper.floor(entityLivingBase.getEntityBoundingBox().minX); i < MathHelper.floor(entityLivingBase.getEntityBoundingBox().maxX) + 1; ++i) {
            for (int j = MathHelper.floor(entityLivingBase.getEntityBoundingBox().minY); j < MathHelper.floor(entityLivingBase.getEntityBoundingBox().maxY) + 1; ++j) {
                for (int k = MathHelper.floor(entityLivingBase.getEntityBoundingBox().minZ); k < MathHelper.floor(entityLivingBase.getEntityBoundingBox().maxZ) + 1; ++k) {
                    final Block block = Wrapper.INSTANCE.world().getBlockState(new BlockPos(i, j, k)).getBlock();
                    final AxisAlignedBB collisionBoundingBox;
                    if (block != null && !(block instanceof BlockAir) && (collisionBoundingBox = block.getCollisionBoundingBox(Wrapper.INSTANCE.world().getBlockState(new BlockPos(i, j, k)), (IBlockAccess)Wrapper.INSTANCE.world(), new BlockPos(i, j, k))) != null && entityLivingBase.getEntityBoundingBox().intersects(collisionBoundingBox)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static String getEntityNameColor(final EntityLivingBase entityLivingBase) {
        final String formattedText = entityLivingBase.getDisplayName().getFormattedText();
        if (formattedText.contains("§")) {
            if (formattedText.contains("§1")) {
                return "§1";
            }
            if (formattedText.contains("§2")) {
                return "§2";
            }
            if (formattedText.contains("§3")) {
                return "§3";
            }
            if (formattedText.contains("§4")) {
                return "§4";
            }
            if (formattedText.contains("§5")) {
                return "§5";
            }
            if (formattedText.contains("§6")) {
                return "§6";
            }
            if (formattedText.contains("§7")) {
                return "§7";
            }
            if (formattedText.contains("§8")) {
                return "§8";
            }
            if (formattedText.contains("§9")) {
                return "§9";
            }
            if (formattedText.contains("§0")) {
                return "§0";
            }
            if (formattedText.contains("§e")) {
                return "§e";
            }
            if (formattedText.contains("§d")) {
                return "§d";
            }
            if (formattedText.contains("§a")) {
                return "§a";
            }
            if (formattedText.contains("§b")) {
                return "§b";
            }
            if (formattedText.contains("§c")) {
                return "§c";
            }
            if (formattedText.contains("§f")) {
                return "§f";
            }
        }
        return "null";
    }
    
    public static float getDirection() {
        float rotationYaw = Wrapper.INSTANCE.player().rotationYaw;
        if (Wrapper.INSTANCE.player().moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (Wrapper.INSTANCE.player().moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (Wrapper.INSTANCE.player().moveForward > 0.0f) {
            n = 0.5f;
        }
        if (Wrapper.INSTANCE.player().moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (Wrapper.INSTANCE.player().moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }
    
    public static boolean isMoving(final Entity entity) {
        if (entity.motionX != 0.0 && entity.motionZ != 0.0) {
            if (entity.motionY == 0.0) {
                if (entity.motionY <= 0.0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean checkEnemyNameColor(final EntityLivingBase entityLivingBase) {
        entityLivingBase.getDisplayName().getFormattedText();
        return !getEntityNameColor((EntityLivingBase)Wrapper.INSTANCE.player()).equals(getEntityNameColor(entityLivingBase));
    }
    
    public static boolean isBlockMaterial(final BlockPos blockPos, final Material material) {
        return Wrapper.INSTANCE.world().getBlockState(blockPos).getMaterial() == material;
    }
    
    public static float[] getNeededRotations(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { Wrapper.INSTANCE.player().rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - Wrapper.INSTANCE.player().rotationYaw), Wrapper.INSTANCE.player().rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - Wrapper.INSTANCE.player().rotationPitch) };
    }
    
    public static int random(final int n, final int n2) {
        return Utils.RANDOM.nextInt(n2 - n) + n;
    }
    
    public static int getPlayerArmorColor(final EntityPlayer entityPlayer, final ItemStack itemStack) {
        Label_0093: {
            if (entityPlayer != null && itemStack != null && itemStack.getItem() != null) {
                while (true) {
                    switch (-428178689 - 781077963 + 1) {
                        case -1051800516: {
                            continue;
                        }
                        case 923480266: {
                            if (!(itemStack.getItem() instanceof ItemArmor)) {
                                break Label_0093;
                            }
                            final ItemArmor itemArmor = (ItemArmor)itemStack.getItem();
                            if (itemArmor == null || itemArmor.getArmorMaterial() != ItemArmor$ArmorMaterial.LEATHER) {
                                return -1;
                            }
                            return itemArmor.getColor(itemStack);
                        }
                        default: {
                            throw null;
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    public static boolean placeBlockScaffold(final BlockPos blockPos) {
        final Vec3d vec3d = new Vec3d(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY + Wrapper.INSTANCE.player().getEyeHeight(), Wrapper.INSTANCE.player().posZ);
        EnumFacing[] values;
        for (int length = (values = EnumFacing.values()).length, i = 0; i < length; ++i) {
            final EnumFacing enumFacing = values[i];
            final BlockPos offset = blockPos.offset(enumFacing);
            final EnumFacing opposite = enumFacing.getOpposite();
            if (vec3d.squareDistanceTo(new Vec3d((Vec3i)blockPos).addVector(0.5, 0.5, 0.5)) < vec3d.squareDistanceTo(new Vec3d((Vec3i)offset).addVector(0.5, 0.5, 0.5)) && canBeClicked(offset)) {
                final Vec3d add = new Vec3d((Vec3i)offset).addVector(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
                if (vec3d.squareDistanceTo(add) <= 18.0625) {
                    faceVectorPacketInstant(add);
                    Wrapper.INSTANCE.player().swingArm(EnumHand.MAIN_HAND);
                    Wrapper.INSTANCE.mc().playerController.processRightClickBlock(Wrapper.INSTANCE.player(), Wrapper.INSTANCE.world(), offset, opposite, add, EnumHand.MAIN_HAND);
                    Exception ex;
                    try {
                        final Field declaredField = Minecraft.class.getDeclaredField("rightClickDelayTimer");
                        declaredField.setAccessible(true);
                        declaredField.set(Wrapper.INSTANCE.mc(), 4);
                        return true;
                    }
                    catch (Exception ex2) {
                        ex = ex2;
                    }
                    ex.printStackTrace();
                    return true;
                }
            }
        }
        return false;
    }
    
    public static float[] getDirectionToBlock(final int n, final int n2, final int n3, final EnumFacing enumFacing) {
        final EntityEgg entityEgg = new EntityEgg((World)Wrapper.INSTANCE.world());
        entityEgg.posX = n + 0.5;
        entityEgg.posY = n2 + 0.5;
        entityEgg.posZ = n3 + 0.5;
        final EntityEgg entityEgg2 = entityEgg;
        entityEgg2.posX += enumFacing.getDirectionVec().getX() * 0.25;
        final EntityEgg entityEgg3 = entityEgg;
        entityEgg3.posY += enumFacing.getDirectionVec().getY() * 0.25;
        final EntityEgg entityEgg4 = entityEgg;
        entityEgg4.posZ += enumFacing.getDirectionVec().getZ() * 0.25;
        return getDirectionToEntity((Entity)entityEgg);
    }
    
    public static boolean isBlockMaterial(final BlockPos blockPos, final Block block) {
        return Wrapper.INSTANCE.world().getBlockState(blockPos).getBlock() == Blocks.AIR;
    }
    
    public static double[] teleportToPosition(final double[] array, final double[] array2, final double n, final double n2, final boolean b, final boolean b2) {
        boolean b3 = false;
        if (Wrapper.INSTANCE.player().isSneaking()) {
            b3 = true;
        }
        double n3 = array[0];
        double n4 = array[1];
        double n5 = array[2];
        final double n6 = array2[0];
        final double n7 = array2[1];
        final double n8 = array2[2];
        double n9 = Math.abs(n3 - n4) + Math.abs(n4 - n7) + Math.abs(n5 - n8);
        int n10 = 0;
        while (n9 > n2) {
            n9 = Math.abs(n3 - n6) + Math.abs(n4 - n7) + Math.abs(n5 - n8);
            if (n10 > 120) {
                break;
            }
            double n11 = 0.0;
            Label_0280: {
                if (b) {
                    if ((n10 & 0x1) == 0x0) {
                        n11 = n + 0.15;
                        break Label_0280;
                    }
                }
                n11 = n;
            }
            final double n12 = n11;
            final double n13 = n3 - n6;
            final double n14 = n4 - n7;
            final double n15 = n5 - n8;
            if (n13 < 0.0) {
                if (Math.abs(n13) > n12) {
                    n3 += n12;
                }
                else {
                    n3 += Math.abs(n13);
                }
            }
            if (n13 > 0.0) {
                if (Math.abs(n13) > n12) {
                    n3 -= n12;
                }
                else {
                    n3 -= Math.abs(n13);
                }
            }
            if (n14 < 0.0) {
                if (Math.abs(n14) > n12) {
                    n4 += n12;
                }
                else {
                    n4 += Math.abs(n14);
                }
            }
            if (n14 > 0.0) {
                if (Math.abs(n14) > n12) {
                    n4 -= n12;
                }
                else {
                    n4 -= Math.abs(n14);
                }
            }
            Label_0747: {
                if (n15 < 0.0) {
                    while (true) {
                        switch (463406090 + 56869074 + 1) {
                            case -2061864988: {
                                continue;
                            }
                            case 419218648: {
                                if (Math.abs(n15) > n12) {
                                    n5 += n12;
                                    break Label_0747;
                                }
                                n5 += Math.abs(n15);
                                break Label_0747;
                            }
                            default: {
                                throw null;
                            }
                        }
                    }
                }
            }
            if (n15 > 0.0) {
                if (Math.abs(n15) > n12) {
                    n5 -= n12;
                }
                else {
                    n5 -= Math.abs(n15);
                }
            }
            if (b3) {
                Wrapper.INSTANCE.sendPacket((Packet)new CPacketEntityAction((Entity)Wrapper.INSTANCE.player(), CPacketEntityAction$Action.STOP_SNEAKING));
            }
            Wrapper.INSTANCE.mc().getConnection().getNetworkManager().sendPacket((Packet)new CPacketPlayer$Position(n3, n4, n5, b2));
            ++n10;
        }
        if (b3) {
            Wrapper.INSTANCE.sendPacket((Packet)new CPacketEntityAction((Entity)Wrapper.INSTANCE.player(), CPacketEntityAction$Action.START_SNEAKING));
        }
        return new double[] { n3, n4, n5 };
    }
    
    public static int getDistanceFromMouse(final EntityLivingBase entityLivingBase) {
        final float[] rotationsNeeded = getRotationsNeeded((Entity)entityLivingBase);
        if (rotationsNeeded != null) {
            final float n = Wrapper.INSTANCE.player().rotationYaw - rotationsNeeded[0];
            final float n2 = Wrapper.INSTANCE.player().rotationPitch - rotationsNeeded[1];
            return (int)MathHelper.sqrt(n * n + n2 * n2 * 2.0f);
        }
        return -1;
    }
    
    public static boolean checkEnemyColor(final EntityPlayer entityPlayer) {
        final int playerArmorColor = getPlayerArmorColor(entityPlayer, entityPlayer.inventory.armorItemInSlot(0));
        final int playerArmorColor2 = getPlayerArmorColor(entityPlayer, entityPlayer.inventory.armorItemInSlot(1));
        final int playerArmorColor3 = getPlayerArmorColor(entityPlayer, entityPlayer.inventory.armorItemInSlot(2));
        final int playerArmorColor4 = getPlayerArmorColor(entityPlayer, entityPlayer.inventory.armorItemInSlot(3));
        final int playerArmorColor5 = getPlayerArmorColor((EntityPlayer)Wrapper.INSTANCE.player(), Wrapper.INSTANCE.player().inventory.armorItemInSlot(0));
        final int playerArmorColor6 = getPlayerArmorColor((EntityPlayer)Wrapper.INSTANCE.player(), Wrapper.INSTANCE.player().inventory.armorItemInSlot(1));
        final int playerArmorColor7 = getPlayerArmorColor((EntityPlayer)Wrapper.INSTANCE.player(), Wrapper.INSTANCE.player().inventory.armorItemInSlot(2));
        final int playerArmorColor8 = getPlayerArmorColor((EntityPlayer)Wrapper.INSTANCE.player(), Wrapper.INSTANCE.player().inventory.armorItemInSlot(3));
        return (playerArmorColor != playerArmorColor5 || playerArmorColor5 == -1 || playerArmorColor == 1) && (playerArmorColor2 != playerArmorColor6 || playerArmorColor6 == -1 || playerArmorColor2 == 1) && (playerArmorColor3 != playerArmorColor7 || playerArmorColor7 == -1 || playerArmorColor3 == 1) && (playerArmorColor4 != playerArmorColor8 || playerArmorColor8 == -1 || playerArmorColor4 == 1);
    }
    
    public static void assistFaceEntity(final Entity entity, final float n, final float n2) {
        if (entity == null) {
            return;
        }
        final double x = entity.posX - Wrapper.INSTANCE.player().posX;
        final double y = entity.posZ - Wrapper.INSTANCE.player().posZ;
        double y2;
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            y2 = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Wrapper.INSTANCE.player().posY + Wrapper.INSTANCE.player().getEyeHeight());
        }
        else {
            y2 = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0 - (Wrapper.INSTANCE.player().posY + Wrapper.INSTANCE.player().getEyeHeight());
        }
        final double x2 = MathHelper.sqrt(x * x + y * y);
        final float n3 = (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float n4 = (float)(-(Math.atan2(y2, x2) * 180.0 / 3.141592653589793));
        if (n > 0.0f) {
            Wrapper.INSTANCE.player().rotationYaw = updateRotation(Wrapper.INSTANCE.player().rotationYaw, n3, n / 4.0f);
        }
        if (n2 > 0.0f) {
            Wrapper.INSTANCE.player().rotationPitch = updateRotation(Wrapper.INSTANCE.player().rotationPitch, n4, n2 / 4.0f);
        }
    }
    
    public static boolean isMurder(final EntityLivingBase entityLivingBase) {
        mysteryFind(entityLivingBase, 0);
        if (!EnemyManager.murders.isEmpty() && entityLivingBase instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            final Iterator<String> iterator = EnemyManager.murders.iterator();
            while (iterator.hasNext()) {
                if (entityPlayer.getGameProfile().getName().equals(iterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean nullCheck() {
        return Wrapper.INSTANCE.player() == null || Wrapper.INSTANCE.world() == null;
    }
}
