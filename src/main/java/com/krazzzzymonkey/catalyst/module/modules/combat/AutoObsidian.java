/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import java.util.Collections;
import net.minecraft.init.Blocks;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.network.play.client.CPacketPlayer$Position;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction$Action;
import net.minecraft.util.math.Vec3i;
import net.minecraft.network.play.client.CPacketPlayer$Rotation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import com.krazzzzymonkey.catalyst.utils.MovementUtil;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerDigging$Action;
import net.minecraft.world.GameType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.EnumFacing;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import net.minecraft.block.Block;
import java.util.List;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoObsidian extends Modules
{
    public BooleanValue center;
    public BooleanValue stopOnMotion;
    public int i;
    public int notMovingTicks;
    public BooleanValue rotate;
    public ModeValue modes;
    public static BooleanValue noGhostBlock;
    public BooleanValue onlyOnSneak;
    public static Minecraft mc;
    public BooleanValue bottomBlock;
    public List<Block> whiteList;
    public IntegerValue stationaryTicks;
    public IntegerValue tick;
    public boolean isMoving;
    
    @Override
    public void onDisable() {
        this.i = 0;
        this.isMoving = false;
        this.notMovingTicks = 0;
        super.onDisable();
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return getBlock(blockPos).canCollideCheck(getState(blockPos), false);
    }
    
    public static boolean lambda$isEntitiesEmpty$1(final Entity entity) {
        return !(entity instanceof EntityXPOrb);
    }
    
    public boolean isEntitiesEmpty(final BlockPos blockPos) {
        return ((List)AutoObsidian.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(blockPos)).stream().filter(AutoObsidian::lambda$isEntitiesEmpty$0).filter(AutoObsidian::lambda$isEntitiesEmpty$1).collect(Collectors.toList())).isEmpty();
    }
    
    public static void processRightClickBlock(final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d) {
        getPlayerController().processRightClickBlock(AutoObsidian.mc.player, AutoObsidian.mc.world, blockPos, enumFacing, vec3d, EnumHand.MAIN_HAND);
        if ((boolean)AutoObsidian.noGhostBlock.getValue() && !AutoObsidian.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
            AutoObsidian.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.START_DESTROY_BLOCK, blockPos, enumFacing));
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (!MovementUtil.isMoving() || !(boolean)this.stopOnMotion.getValue()) {
            ++this.notMovingTicks;
            if ((boolean)this.stopOnMotion.getValue() && this.notMovingTicks < this.stationaryTicks.getValue()) {
                return;
            }
            ++this.i;
            if ((boolean)this.onlyOnSneak.getValue() && !AutoObsidian.mc.gameSettings.keyBindSneak.isKeyDown()) {
                return;
            }
            if (!this.isToggled() || AutoObsidian.mc.player == null) {
                return;
            }
            final Vec3d interpolatedPos = getInterpolatedPos((Entity)AutoObsidian.mc.player, 0.0f);
            BlockPos north = new BlockPos(interpolatedPos).north();
            BlockPos south = new BlockPos(interpolatedPos).south();
            BlockPos east = new BlockPos(interpolatedPos).east();
            BlockPos west = new BlockPos(interpolatedPos).west();
            BlockPos down = new BlockPos((Entity)AutoObsidian.mc.player).down();
            final BlockPos add = north.add(0, 0, -1);
            final BlockPos add2 = south.add(0, 0, 1);
            final BlockPos add3 = east.add(1, 0, 0);
            final BlockPos add4 = west.add(-1, 0, 0);
            final BlockPos add5 = north.add(0, 1, -1);
            final BlockPos add6 = south.add(0, 1, 1);
            final BlockPos add7 = east.add(1, 1, 0);
            final BlockPos add8 = west.add(-1, 1, 0);
            int n = 0;
            int currentItem = -1;
            for (int i = 0; i < 9; ++i) {
                final ItemStack stackInSlot = AutoObsidian.mc.player.inventory.getStackInSlot(i);
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
            final int currentItem2 = AutoObsidian.mc.player.inventory.currentItem;
            AutoObsidian.mc.player.inventory.currentItem = currentItem;
            Label_0923: {
                if (!hasNeighbour(north)) {
                    final EnumFacing[] values = EnumFacing.values();
                    for (int length = values.length, j = 0; j < length; ++j) {
                        final BlockPos offset = north.offset(values[j]);
                        if (hasNeighbour(offset)) {
                            north = offset;
                            break Label_0923;
                        }
                    }
                    return;
                }
            }
            Label_1033: {
                if (!hasNeighbour(south)) {
                    final EnumFacing[] values2 = EnumFacing.values();
                    for (int length2 = values2.length, k = 0; k < length2; ++k) {
                        final BlockPos offset2 = south.offset(values2[k]);
                        if (hasNeighbour(offset2)) {
                            south = offset2;
                            break Label_1033;
                        }
                    }
                    return;
                }
            }
            Label_1194: {
                if (!hasNeighbour(east)) {
                    final EnumFacing[] values3 = EnumFacing.values();
                    for (int length3 = values3.length, l = 0; l < length3; ++l) {
                        final BlockPos offset3 = east.offset(values3[l]);
                        if (hasNeighbour(offset3)) {
                            east = offset3;
                            break Label_1194;
                        }
                    }
                    return;
                }
            }
            Label_1336: {
                if (!hasNeighbour(west)) {
                    final EnumFacing[] values4 = EnumFacing.values();
                    final int length4 = values4.length;
                    int n2 = 0;
                Label_1237:
                    while (true) {
                        while (n2 < length4) {
                            final BlockPos offset4 = west.offset(values4[n2]);
                            while (true) {
                                switch (452379453 - 194476862 + 1) {
                                    case -2096743300: {
                                        continue;
                                    }
                                    case -291616769: {
                                        if (hasNeighbour(offset4)) {
                                            west = offset4;
                                            break Label_1336;
                                        }
                                        ++n2;
                                        continue Label_1237;
                                    }
                                    default: {
                                        throw null;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    return;
                }
            }
            Label_1472: {
                if ((boolean)this.bottomBlock.getValue() && !hasNeighbour(down)) {
                    final EnumFacing[] values5 = EnumFacing.values();
                    for (int length5 = values5.length, n3 = 0; n3 < length5; ++n3) {
                        final BlockPos offset5 = down.offset(values5[n3]);
                        if (hasNeighbour(offset5)) {
                            down = offset5;
                            break Label_1472;
                        }
                    }
                    return;
                }
            }
            if ((boolean)this.center.getValue() && (boolean)this.onlyOnSneak.getValue()) {
                this.centerPlayer();
            }
            if ((boolean)this.center.getValue() && !(boolean)this.onlyOnSneak.getValue() && (this.i < 10 || this.notMovingTicks - this.stationaryTicks.getValue() < 5)) {
                this.centerPlayer();
            }
            if (this.bottomBlock.getValue()) {
                if (AutoObsidian.mc.world.getBlockState(down).getMaterial().isReplaceable()) {
                    if (this.isEntitiesEmpty(down)) {
                        placeBlockScaffold(down, (boolean)this.rotate.getValue());
                        ++n;
                    }
                    else if (this.isEntitiesEmpty(down)) {
                        if (AutoObsidian.mc.world.getBlockState(down).getMaterial().isReplaceable()) {
                            placeBlockScaffold(down, (boolean)this.rotate.getValue());
                            ++n;
                        }
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
            }
            if (AutoObsidian.mc.world.getBlockState(north).getMaterial().isReplaceable()) {
                if (this.isEntitiesEmpty(north)) {
                    placeBlockScaffold(north, (boolean)this.rotate.getValue());
                    ++n;
                }
                else if (this.isEntitiesEmpty(north.north())) {
                    if (AutoObsidian.mc.world.getBlockState(north).getMaterial().isReplaceable()) {
                        placeBlockScaffold(north.north(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
            }
            if (n >= this.tick.getValue()) {
                AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                return;
            }
            if (AutoObsidian.mc.world.getBlockState(south).getMaterial().isReplaceable()) {
                if (this.isEntitiesEmpty(south)) {
                    placeBlockScaffold(south, (boolean)this.rotate.getValue());
                    ++n;
                }
                else if (this.isEntitiesEmpty(south.south())) {
                    if (AutoObsidian.mc.world.getBlockState(south.south()).getMaterial().isReplaceable()) {
                        placeBlockScaffold(south.south(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
            }
            if (n >= this.tick.getValue()) {
                AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                return;
            }
            if (AutoObsidian.mc.world.getBlockState(east).getMaterial().isReplaceable()) {
                if (this.isEntitiesEmpty(east)) {
                    placeBlockScaffold(east, (boolean)this.rotate.getValue());
                    ++n;
                }
                else if (this.isEntitiesEmpty(east.east()) && AutoObsidian.mc.world.getBlockState(east.east()).getMaterial().isReplaceable()) {
                    placeBlockScaffold(east.east(), (boolean)this.rotate.getValue());
                    ++n;
                }
            }
            if (n >= this.tick.getValue()) {
                AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                return;
            }
            if (AutoObsidian.mc.world.getBlockState(west).getMaterial().isReplaceable()) {
                if (this.isEntitiesEmpty(west)) {
                    placeBlockScaffold(west, (boolean)this.rotate.getValue());
                    ++n;
                }
                else if (this.isEntitiesEmpty(west.west()) && AutoObsidian.mc.world.getBlockState(west.west()).getMaterial().isReplaceable()) {
                    placeBlockScaffold(west.west(), (boolean)this.rotate.getValue());
                    ++n;
                }
            }
            if (n >= this.tick.getValue()) {
                AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                return;
            }
            if (this.modes.getMode("Square").isToggled()) {
                if (AutoObsidian.mc.world.getBlockState(north.add(1, 0, 0)).getMaterial().isReplaceable()) {
                    if (this.isEntitiesEmpty(north.add(1, 0, 0))) {
                        placeBlockScaffold(north.add(1, 0, 0), (boolean)this.rotate.getValue());
                        ++n;
                    }
                    else if (this.isEntitiesEmpty(north.add(1, 0, 0)) && AutoObsidian.mc.world.getBlockState(north.add(1, 0, 0)).getMaterial().isReplaceable()) {
                        placeBlockScaffold(north.add(1, 0, 0).north(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
                if (AutoObsidian.mc.world.getBlockState(south.add(-1, 0, 0)).getMaterial().isReplaceable()) {
                    if (this.isEntitiesEmpty(south.add(-1, 0, 0))) {
                        placeBlockScaffold(south.add(-1, 0, 0), (boolean)this.rotate.getValue());
                        ++n;
                    }
                    else if (this.isEntitiesEmpty(south.add(-1, 0, 0).south()) && AutoObsidian.mc.world.getBlockState(south.add(-1, 0, 0).south()).getMaterial().isReplaceable()) {
                        placeBlockScaffold(south.add(-1, 0, 0).south(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
                if (AutoObsidian.mc.world.getBlockState(east.add(0, 0, 1)).getMaterial().isReplaceable()) {
                    if (this.isEntitiesEmpty(east.add(0, 0, 1))) {
                        placeBlockScaffold(east.add(0, 0, 1), (boolean)this.rotate.getValue());
                        ++n;
                    }
                    else if (this.isEntitiesEmpty(east.add(0, 0, 1).east()) && AutoObsidian.mc.world.getBlockState(east.add(0, 0, 1).east()).getMaterial().isReplaceable()) {
                        placeBlockScaffold(east.add(0, 0, 1).east(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
                if (AutoObsidian.mc.world.getBlockState(west.add(0, 0, -1)).getMaterial().isReplaceable()) {
                    if (this.isEntitiesEmpty(west.add(0, 0, -1))) {
                        placeBlockScaffold(west.add(0, 0, -1), (boolean)this.rotate.getValue());
                        ++n;
                    }
                    else if (this.isEntitiesEmpty(west.add(0, 0, -1).west()) && AutoObsidian.mc.world.getBlockState(west.add(0, 0, -1).west()).getMaterial().isReplaceable()) {
                        placeBlockScaffold(west.add(0, 0, -1).west(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
            }
            if (this.modes.getMode("Long").isToggled() || this.modes.getMode("AntiPiston").isToggled()) {
                if (AutoObsidian.mc.world.getBlockState(add).getMaterial().isReplaceable()) {
                    if (this.isEntitiesEmpty(add)) {
                        placeBlockScaffold(add, (boolean)this.rotate.getValue());
                        ++n;
                    }
                    else if (this.isEntitiesEmpty(add.north()) && AutoObsidian.mc.world.getBlockState(add.north()).getMaterial().isReplaceable()) {
                        placeBlockScaffold(add.north(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
                if (AutoObsidian.mc.world.getBlockState(add2).getMaterial().isReplaceable()) {
                    if (this.isEntitiesEmpty(add2)) {
                        placeBlockScaffold(add2, (boolean)this.rotate.getValue());
                        ++n;
                    }
                    else if (this.isEntitiesEmpty(add2.south()) && AutoObsidian.mc.world.getBlockState(add2.south()).getMaterial().isReplaceable()) {
                        placeBlockScaffold(add2.south(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
                if (AutoObsidian.mc.world.getBlockState(add3).getMaterial().isReplaceable()) {
                    if (this.isEntitiesEmpty(add3)) {
                        placeBlockScaffold(add3, (boolean)this.rotate.getValue());
                        ++n;
                    }
                    else if (this.isEntitiesEmpty(add3.east()) && AutoObsidian.mc.world.getBlockState(add3.east()).getMaterial().isReplaceable()) {
                        placeBlockScaffold(add3.east(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
                if (AutoObsidian.mc.world.getBlockState(add4).getMaterial().isReplaceable()) {
                    if (this.isEntitiesEmpty(add4)) {
                        placeBlockScaffold(add4, (boolean)this.rotate.getValue());
                        ++n;
                    }
                    else if (this.isEntitiesEmpty(add4.west()) && AutoObsidian.mc.world.getBlockState(add4.west()).getMaterial().isReplaceable()) {
                        placeBlockScaffold(add4.west(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
            }
            if (this.modes.getMode("AntiPiston").isToggled()) {
                Label_6990: {
                    if (AutoObsidian.mc.world.getBlockState(add5).getMaterial().isReplaceable()) {
                        if (this.isEntitiesEmpty(add5)) {
                            placeBlockScaffold(add5, (boolean)this.rotate.getValue());
                            ++n;
                        }
                        else if (this.isEntitiesEmpty(add5.north()) && AutoObsidian.mc.world.getBlockState(add5.north()).getMaterial().isReplaceable()) {
                            final BlockPos north2 = add5.north();
                            while (true) {
                                switch (663519902 - 352080568 + 1) {
                                    case 1263190830: {
                                        continue;
                                    }
                                    default: {
                                        placeBlockScaffold(north2, (boolean)this.rotate.getValue());
                                        ++n;
                                        break Label_6990;
                                    }
                                    case -968381684: {
                                        throw null;
                                    }
                                }
                            }
                        }
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
                if (AutoObsidian.mc.world.getBlockState(add6).getMaterial().isReplaceable()) {
                    if (this.isEntitiesEmpty(add6)) {
                        placeBlockScaffold(add6, (boolean)this.rotate.getValue());
                        ++n;
                    }
                    else if (this.isEntitiesEmpty(add6.south()) && AutoObsidian.mc.world.getBlockState(add6.south()).getMaterial().isReplaceable()) {
                        placeBlockScaffold(add6.south(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
                if (AutoObsidian.mc.world.getBlockState(add7).getMaterial().isReplaceable()) {
                    if (this.isEntitiesEmpty(add7)) {
                        placeBlockScaffold(add7, (boolean)this.rotate.getValue());
                        ++n;
                    }
                    else if (this.isEntitiesEmpty(add7.east()) && AutoObsidian.mc.world.getBlockState(add7.east()).getMaterial().isReplaceable()) {
                        placeBlockScaffold(add7.east(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
                if (AutoObsidian.mc.world.getBlockState(add8).getMaterial().isReplaceable()) {
                    if (this.isEntitiesEmpty(add8)) {
                        placeBlockScaffold(add8, (boolean)this.rotate.getValue());
                        ++n;
                    }
                    else if (this.isEntitiesEmpty(add8.west()) && AutoObsidian.mc.world.getBlockState(add8.west()).getMaterial().isReplaceable()) {
                        placeBlockScaffold(add8.west(), (boolean)this.rotate.getValue());
                        ++n;
                    }
                }
                if (n >= this.tick.getValue()) {
                    AutoObsidian.mc.player.inventory.currentItem = currentItem2;
                    return;
                }
            }
            AutoObsidian.mc.player.inventory.currentItem = currentItem2;
        }
        else {
            this.notMovingTicks = 0;
        }
    }
    
    public static IBlockState getState(final BlockPos blockPos) {
        return AutoObsidian.mc.world.getBlockState(blockPos);
    }
    
    public static float[] getNeededRotations2(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { AutoObsidian.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - AutoObsidian.mc.player.rotationYaw), AutoObsidian.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - AutoObsidian.mc.player.rotationPitch) };
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(AutoObsidian.mc.player.posX, AutoObsidian.mc.player.posY + AutoObsidian.mc.player.getEyeHeight(), AutoObsidian.mc.player.posZ);
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec3d) {
        final float[] neededRotations2 = getNeededRotations2(vec3d);
        AutoObsidian.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Rotation(neededRotations2[0], neededRotations2[1], AutoObsidian.mc.player.onGround));
    }
    
    public static Block getBlock(final BlockPos blockPos) {
        return getState(blockPos).getBlock();
    }
    
    public static void placeBlockScaffold(final BlockPos blockPos, final boolean b) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            final EnumFacing opposite = enumFacing.getOpposite();
            if (canBeClicked(offset)) {
                final Vec3d add = new Vec3d((Vec3i)offset).addVector(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
                if (b) {
                    faceVectorPacketInstant(add);
                }
                AutoObsidian.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoObsidian.mc.player, CPacketEntityAction$Action.START_SNEAKING));
                processRightClickBlock(offset, opposite, add);
                AutoObsidian.mc.player.swingArm(EnumHand.MAIN_HAND);
                AutoObsidian.mc.rightClickDelayTimer = 0;
                AutoObsidian.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoObsidian.mc.player, CPacketEntityAction$Action.STOP_SNEAKING));
                return;
            }
        }
    }
    
    public static boolean lambda$isEntitiesEmpty$0(final Entity entity) {
        return !(entity instanceof EntityItem);
    }
    
    public static PlayerControllerMP getPlayerController() {
        return AutoObsidian.mc.playerController;
    }
    
    static {
        AutoObsidian.mc = Minecraft.getMinecraft();
    }
    
    public void centerPlayer() {
        AutoObsidian.mc.player.connection.sendPacket((Packet)new CPacketPlayer$Position(roundHalf(Wrapper.INSTANCE.player().posX), Wrapper.INSTANCE.player().posY, roundHalf(Wrapper.INSTANCE.player().posZ), true));
        Wrapper.INSTANCE.player().setPosition(roundHalf(Wrapper.INSTANCE.player().posX), Wrapper.INSTANCE.player().posY, roundHalf(Wrapper.INSTANCE.player().posZ));
    }
    
    public static double roundHalf(final double n) {
        if (n < 0.0) {
            return (int)n - 0.5;
        }
        return (int)n + 0.5;
    }
    
    public static boolean hasNeighbour(final BlockPos blockPos) {
        final EnumFacing[] values = EnumFacing.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (!AutoObsidian.mc.world.getBlockState(blockPos.offset(values[i])).getMaterial().isReplaceable()) {
                return true;
            }
        }
        return false;
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n, final double n2, final double n3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n2, (entity.posZ - entity.lastTickPosZ) * n3);
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, n));
    }
    
    public AutoObsidian() {
        super("AutoObsidian", ModuleCategory.COMBAT, "Places obsidian around your feet");
        this.whiteList = Collections.singletonList(Blocks.OBSIDIAN);
        final int i = 0;
        while (true) {
            switch (20307926 - 1649903084 + 1) {
                case 537060768: {
                    continue;
                }
                case -1667390014: {
                    this.i = i;
                    this.isMoving = false;
                    this.notMovingTicks = 0;
                    this.modes = new ModeValue("Mode", new Mode[] { new Mode("Normal", true), new Mode("Long", false), new Mode("Square", false), new Mode("AntiPiston", false) });
                    this.stopOnMotion = new BooleanValue("StopOnMotion", true);
                    this.stationaryTicks = new IntegerValue("StationaryTicks", 10, 1, 40);
                    this.onlyOnSneak = new BooleanValue("ActiveOnSneak", false);
                    this.rotate = new BooleanValue("Rotate", false);
                    this.center = new BooleanValue("CenterInBlock", true);
                    this.bottomBlock = new BooleanValue("BottomBlock", true);
                    this.tick = new IntegerValue("PlaceSpeed", 4, 1, 12);
                    AutoObsidian.noGhostBlock = new BooleanValue("NoGhostBlock", false);
                    this.addValue(this.modes, this.stopOnMotion, this.stationaryTicks, this.onlyOnSneak, this.rotate, this.center, this.bottomBlock, this.tick, AutoObsidian.noGhostBlock);
                }
                default: {
                    throw null;
                }
            }
        }
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n) {
        return getInterpolatedAmount(entity, n, n, n);
    }
}
