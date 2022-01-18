/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.List;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Comparator;
import java.util.function.Predicate;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.network.play.client.CPacketPlayer$Rotation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction$Action;
import net.minecraft.util.math.Vec3i;
import net.minecraft.block.Block;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerDigging$Action;
import net.minecraft.world.GameType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.block.BlockObsidian;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.utils.BlockUtils;
import net.minecraft.util.math.BlockPos;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class SelfTrap extends Modules
{
    public IntegerValue blocksPerTick;
    public static Minecraft mc;
    public int blocksPlaced;
    public BooleanValue rotate;
    public static BooleanValue noGhostBlock;
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return getBlock(blockPos).canCollideCheck(BlockUtils.getState(blockPos), false);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n, final double n2, final double n3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n2, (entity.posZ - entity.lastTickPosZ) * n3);
    }
    
    public void lambda$onClientTick$3(final Entity entity) {
        final BlockPos blockPos = new BlockPos(getInterpolatedPos(entity, SelfTrap.mc.getRenderPartialTicks()));
        final BlockPos add = blockPos.add(1, 0, 0);
        final BlockPos add2 = blockPos.add(-1, 0, 0);
        final BlockPos add3 = blockPos.add(0, 0, 1);
        final BlockPos add4 = blockPos.add(0, 0, -1);
        final BlockPos add5 = blockPos.add(0, 2, 0);
        final BlockPos up = add.up();
        final BlockPos up2 = add2.up();
        final BlockPos up3 = add3.up();
        final BlockPos up4 = add4.up();
        final BlockPos up5 = up.up();
        final BlockPos up6 = up2.up();
        final BlockPos up7 = up3.up();
        final BlockPos up8 = up4.up();
        int currentItem = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stackInSlot = SelfTrap.mc.player.inventory.getStackInSlot(i);
            if (stackInSlot != ItemStack.EMPTY) {
                if (stackInSlot.getItem() instanceof ItemBlock) {
                    if (((ItemBlock)stackInSlot.getItem()).getBlock() instanceof BlockObsidian) {
                        currentItem = i;
                        break;
                    }
                }
            }
        }
        if (currentItem == -1) {
            return;
        }
        final int currentItem2 = SelfTrap.mc.player.inventory.currentItem;
        SelfTrap.mc.player.inventory.currentItem = currentItem;
        this.blocksPlaced = 0;
        if (this.blocksPlaced > this.blocksPerTick.getValue()) {
            this.blocksPlaced = 0;
            return;
        }
        if (this.shouldPlace(add)) {
            placeBlockScaffold(add, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        if (this.shouldPlace(add2)) {
            placeBlockScaffold(add2, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        if (this.shouldPlace(add3)) {
            placeBlockScaffold(add3, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        if (this.shouldPlace(add4)) {
            placeBlockScaffold(add4, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        if (this.shouldPlace(up)) {
            placeBlockScaffold(up, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        if (this.shouldPlace(up2)) {
            placeBlockScaffold(up2, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        if (this.shouldPlace(up3)) {
            placeBlockScaffold(up3, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        if (this.shouldPlace(up4)) {
            placeBlockScaffold(up4, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        if (this.shouldPlace(up5)) {
            placeBlockScaffold(up5, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        if (this.shouldPlace(up6)) {
            placeBlockScaffold(up6, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        if (this.shouldPlace(up7)) {
            placeBlockScaffold(up7, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        if (this.shouldPlace(up8)) {
            placeBlockScaffold(up8, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        if (this.shouldPlace(add5)) {
            placeBlockScaffold(add5, (boolean)this.rotate.getValue());
            ++this.blocksPlaced;
        }
        SelfTrap.mc.player.inventory.currentItem = currentItem2;
    }
    
    public static boolean lambda$shouldPlace$5(final Entity entity) {
        return !(entity instanceof EntityXPOrb);
    }
    
    static {
        SelfTrap.mc = Minecraft.getMinecraft();
    }
    
    public SelfTrap() {
        super("SelfTrap", ModuleCategory.COMBAT, "Automatically incases you in obsidian, safely allowing you to mend your armor");
        this.rotate = new BooleanValue("Rotate", true);
        this.blocksPerTick = new IntegerValue("Blocks Per Tick", 8, 1, 15);
        SelfTrap.noGhostBlock = new BooleanValue("NoGhostBlock", false);
        this.addValue(this.rotate, this.blocksPerTick, SelfTrap.noGhostBlock);
    }
    
    public static Float lambda$onClientTick$2(final Entity entity) {
        return SelfTrap.mc.player.getDistance(entity);
    }
    
    public static void processRightClickBlock(final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d) {
        getPlayerController().processRightClickBlock(SelfTrap.mc.player, SelfTrap.mc.world, blockPos, enumFacing, vec3d, EnumHand.MAIN_HAND);
        if ((boolean)SelfTrap.noGhostBlock.getValue() && !SelfTrap.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
            SelfTrap.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.START_DESTROY_BLOCK, blockPos, enumFacing));
        }
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, n));
    }
    
    public static Block getBlock(final BlockPos blockPos) {
        return BlockUtils.getState(blockPos).getBlock();
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(SelfTrap.mc.player.posX, SelfTrap.mc.player.posY + SelfTrap.mc.player.getEyeHeight(), SelfTrap.mc.player.posZ);
    }
    
    public static boolean placeBlockScaffold(final BlockPos blockPos, final boolean b) {
        final Vec3d vec3d = new Vec3d(SelfTrap.mc.player.posX, SelfTrap.mc.player.posY + SelfTrap.mc.player.getEyeHeight(), SelfTrap.mc.player.posZ);
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            final EnumFacing opposite = enumFacing.getOpposite();
            if (canBeClicked(offset)) {
                final Vec3d add = new Vec3d((Vec3i)offset).addVector(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
                if (b) {
                    faceVectorPacketInstant(add);
                }
                SelfTrap.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)SelfTrap.mc.player, CPacketEntityAction$Action.START_SNEAKING));
                processRightClickBlock(offset, opposite, add);
                SelfTrap.mc.player.swingArm(EnumHand.MAIN_HAND);
                SelfTrap.mc.rightClickDelayTimer = 0;
                SelfTrap.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)SelfTrap.mc.player, CPacketEntityAction$Action.STOP_SNEAKING));
                return true;
            }
        }
        return false;
    }
    
    public static boolean lambda$onClientTick$0(final Entity entity) {
        return entity instanceof EntityPlayer;
    }
    
    public static float[] getNeededRotations2(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { SelfTrap.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - SelfTrap.mc.player.rotationYaw), SelfTrap.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - SelfTrap.mc.player.rotationPitch) };
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n) {
        return getInterpolatedAmount(entity, n, n, n);
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec3d) {
        final float[] neededRotations2 = getNeededRotations2(vec3d);
        SelfTrap.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Rotation(neededRotations2[0], neededRotations2[1], SelfTrap.mc.player.onGround));
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        SelfTrap.mc.world.loadedEntityList.stream().filter(SelfTrap::lambda$onClientTick$0).filter(SelfTrap::lambda$onClientTick$1).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)SelfTrap::lambda$onClientTick$2)).forEach(this::lambda$onClientTick$3);
    }
    
    public static boolean lambda$shouldPlace$4(final Entity entity) {
        return !(entity instanceof EntityItem);
    }
    
    public static boolean lambda$onClientTick$1(final Entity entity) {
        return entity == SelfTrap.mc.player;
    }
    
    public static PlayerControllerMP getPlayerController() {
        return SelfTrap.mc.playerController;
    }
    
    public boolean shouldPlace(final BlockPos blockPos) {
        final boolean empty = ((List)SelfTrap.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(blockPos)).stream().filter(SelfTrap::lambda$shouldPlace$4).filter(SelfTrap::lambda$shouldPlace$5).collect(Collectors.toList())).isEmpty();
        final boolean replaceable = SelfTrap.mc.world.getBlockState(blockPos).getMaterial().isReplaceable();
        final boolean b = this.blocksPlaced < this.blocksPerTick.getValue();
        return empty && replaceable && b;
    }
}
