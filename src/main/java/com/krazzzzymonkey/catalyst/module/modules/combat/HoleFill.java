/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import net.minecraft.network.play.client.CPacketPlayer$Rotation;
import com.krazzzzymonkey.catalyst.value.Value;
import java.util.Collections;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction$Action;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.MathHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import java.util.function.Consumer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerDigging$Action;
import net.minecraft.world.GameType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.EnumFacing;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import net.minecraft.block.Block;
import java.util.List;
import com.krazzzzymonkey.catalyst.module.Modules;

public class HoleFill extends Modules
{
    public int waitCounter;
    public List<Block> whiteList;
    public ArrayList<BlockPos> holes;
    public BooleanValue rotate;
    public IntegerValue delay;
    public static BooleanValue noGhostBlock;
    public BlockPos pos;
    public DoubleValue range;
    public DoubleValue yRange;
    
    public static PlayerControllerMP getPlayerController() {
        return HoleFill.mc.playerController;
    }
    
    public static void processRightClickBlock(final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d) {
        getPlayerController().processRightClickBlock(HoleFill.mc.player, HoleFill.mc.world, blockPos, enumFacing, vec3d, EnumHand.MAIN_HAND);
        if (HoleFill.noGhostBlock.getValue()) {
            if (!HoleFill.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.START_DESTROY_BLOCK, blockPos, enumFacing));
            }
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null) {
                if (Minecraft.getMinecraft().player.getUniqueID() != null) {
                    this.holes = new ArrayList<BlockPos>();
                    for (final BlockPos e : BlockPos.getAllInBox(Minecraft.getMinecraft().player.getPosition().add(-this.range.getValue(), -this.yRange.getValue(), -this.range.getValue()), Minecraft.getMinecraft().player.getPosition().add((double)this.range.getValue(), (double)this.yRange.getValue(), (double)this.range.getValue()))) {
                        if (!Minecraft.getMinecraft().world.getBlockState(e).getMaterial().blocksMovement() && !Minecraft.getMinecraft().world.getBlockState(e.add(0, 1, 0)).getMaterial().blocksMovement()) {
                            boolean b2 = false;
                            Label_1413: {
                                final boolean b;
                                if (((b = (Minecraft.getMinecraft().world.getBlockState(e.add(1, 0, 0)).getBlock() == Blocks.OBSIDIAN)) ? 1 : 0) - (((Minecraft.getMinecraft().world.getBlockState(e.add(1, 0, 0)).getBlock() == Blocks.BEDROCK) ? 1 : 0) & ~(b ? 1 : 0)) != 0) {
                                    if ((Minecraft.getMinecraft().world.getBlockState(e.add(0, 0, 1)).getBlock() == Blocks.BEDROCK | Minecraft.getMinecraft().world.getBlockState(e.add(0, 0, 1)).getBlock() == Blocks.OBSIDIAN) && (Minecraft.getMinecraft().world.getBlockState(e.add(-1, 0, 0)).getBlock() == Blocks.BEDROCK | Minecraft.getMinecraft().world.getBlockState(e.add(-1, 0, 0)).getBlock() == Blocks.OBSIDIAN) && (Minecraft.getMinecraft().world.getBlockState(e.add(0, 0, -1)).getBlock() == Blocks.BEDROCK | Minecraft.getMinecraft().world.getBlockState(e.add(0, 0, -1)).getBlock() == Blocks.OBSIDIAN) && Minecraft.getMinecraft().world.getBlockState(e.add(0, 0, 0)).getMaterial() == Material.AIR && Minecraft.getMinecraft().world.getBlockState(e.add(0, 1, 0)).getMaterial() == Material.AIR && Minecraft.getMinecraft().world.getBlockState(e.add(0, 2, 0)).getMaterial() == Material.AIR) {
                                        b2 = true;
                                        break Label_1413;
                                    }
                                }
                                b2 = false;
                            }
                            if (!b2) {
                                continue;
                            }
                            this.holes.add(e);
                        }
                    }
                    int currentItem = -1;
                    for (int i = 0; i < 9; ++i) {
                        final ItemStack stackInSlot = Minecraft.getMinecraft().player.inventory.getStackInSlot(i);
                        if (stackInSlot != ItemStack.EMPTY) {
                            if (stackInSlot.getItem() instanceof ItemBlock) {
                                if (this.whiteList.contains(((ItemBlock)stackInSlot.getItem()).getBlock())) {
                                    currentItem = i;
                                    break;
                                }
                            }
                        }
                    }
                    if (currentItem == -1) {
                        return;
                    }
                    final int currentItem2 = Minecraft.getMinecraft().player.inventory.currentItem;
                    if (this.delay.getValue() > 0) {
                        if (this.waitCounter < this.delay.getValue()) {
                            Minecraft.getMinecraft().player.inventory.currentItem = currentItem;
                            this.holes.forEach(this::place);
                            Minecraft.getMinecraft().player.inventory.currentItem = currentItem2;
                            return;
                        }
                        this.waitCounter = 0;
                    }
                }
            }
        }
    }
    
    public void place(final BlockPos blockPos) {
        final Iterator<Entity> iterator = Minecraft.getMinecraft().world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(blockPos)).iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof EntityLivingBase) {
                return;
            }
        }
        placeBlockScaffold(blockPos, (boolean)this.rotate.getValue());
        ++this.waitCounter;
    }
    
    public static IBlockState getState(final BlockPos blockPos) {
        return HoleFill.mc.world.getBlockState(blockPos);
    }
    
    public static float[] getNeededRotations2(final Vec3d vec3d) {
        final Vec3d eyesPos = AutoObsidian.getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { HoleFill.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - HoleFill.mc.player.rotationYaw), HoleFill.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - HoleFill.mc.player.rotationPitch) };
    }
    
    public static Block getBlock(final BlockPos blockPos) {
        return getState(blockPos).getBlock();
    }
    
    public static void placeBlockScaffold(final BlockPos blockPos, final boolean b) {
        final Vec3d vec3d = new Vec3d(HoleFill.mc.player.posX, HoleFill.mc.player.posY + HoleFill.mc.player.getEyeHeight(), HoleFill.mc.player.posZ);
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            final EnumFacing opposite = enumFacing.getOpposite();
            if (canBeClicked(offset)) {
                final Vec3d add = new Vec3d((Vec3i)offset).addVector(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
                if (b) {
                    faceVectorPacketInstant(add);
                }
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleFill.mc.player, CPacketEntityAction$Action.START_SNEAKING));
                processRightClickBlock(offset, opposite, add);
                HoleFill.mc.player.swingArm(EnumHand.MAIN_HAND);
                HoleFill.mc.rightClickDelayTimer = 0;
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleFill.mc.player, CPacketEntityAction$Action.STOP_SNEAKING));
                return;
            }
        }
    }
    
    public HoleFill() {
        super("HoleFill", ModuleCategory.COMBAT, "Fills holes preventing an enemy from jumping in when crystal pvping");
        this.holes = new ArrayList<BlockPos>();
        this.whiteList = Collections.singletonList(Blocks.OBSIDIAN);
        this.range = new DoubleValue("Range", 5.0, 0.0, 10.0);
        this.yRange = new DoubleValue("Y Range", 2.0, 0.0, 10.0);
        this.delay = new IntegerValue("Delay", 1, 0, 20);
        this.rotate = new BooleanValue("Rotate", true);
        HoleFill.noGhostBlock = new BooleanValue("NoGhostBlock", false);
        this.addValue(this.range, this.yRange, this.delay, this.rotate, HoleFill.noGhostBlock);
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec3d) {
        final float[] neededRotations2 = getNeededRotations2(vec3d);
        HoleFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Rotation(neededRotations2[0], neededRotations2[1], HoleFill.mc.player.onGround));
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return getBlock(blockPos).canCollideCheck(getState(blockPos), false);
    }
}
