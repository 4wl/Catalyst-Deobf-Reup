/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.HashMap;
import net.minecraft.util.math.AxisAlignedBB;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import java.util.ArrayList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.Minecraft;

public class HoleCheck implements Runnable
{
    public static Minecraft mc;
    
    public static void lambda$run$1(final BlockPos blockPos) {
        final ArrayList<EnumFacing> list = new ArrayList<EnumFacing>();
        if (HoleCheck.mc.world.getBlockState(blockPos.west()).getBlock().equals(Blocks.AIR) && HoleCheck.mc.world.getBlockState(blockPos.west().up()).getBlock().equals(Blocks.AIR)) {
            list.add(EnumFacing.WEST);
        }
        if (HoleCheck.mc.world.getBlockState(blockPos.east()).getBlock().equals(Blocks.AIR) && HoleCheck.mc.world.getBlockState(blockPos.east().up()).getBlock().equals(Blocks.AIR)) {
            list.add(EnumFacing.EAST);
        }
        if (HoleCheck.mc.world.getBlockState(blockPos.north()).getBlock().equals(Blocks.AIR) && HoleCheck.mc.world.getBlockState(blockPos.north().up()).getBlock().equals(Blocks.AIR)) {
            list.add(EnumFacing.NORTH);
        }
        if (HoleCheck.mc.world.getBlockState(blockPos.south()).getBlock().equals(Blocks.AIR)) {
            if (HoleCheck.mc.world.getBlockState(blockPos.south().up()).getBlock().equals(Blocks.AIR)) {
                list.add(EnumFacing.SOUTH);
            }
        }
        if (list.size() == 1) {
            final EnumFacing enumFacing = list.get(0);
            final ArrayList<EnumFacing> list2 = new ArrayList<EnumFacing>();
            if (HoleCheck.mc.world.getBlockState(blockPos.offset((EnumFacing)list.get(0)).west()).getBlock().equals(Blocks.AIR)) {
                if (HoleCheck.mc.world.getBlockState(blockPos.offset((EnumFacing)list.get(0)).west().up()).getBlock().equals(Blocks.AIR)) {
                    list2.add(EnumFacing.WEST);
                }
            }
            if (HoleCheck.mc.world.getBlockState(blockPos.offset((EnumFacing)list.get(0)).east()).getBlock().equals(Blocks.AIR) && HoleCheck.mc.world.getBlockState(blockPos.offset((EnumFacing)list.get(0)).east().up()).getBlock().equals(Blocks.AIR)) {
                list2.add(EnumFacing.EAST);
            }
            if (HoleCheck.mc.world.getBlockState(blockPos.offset((EnumFacing)list.get(0)).north()).getBlock().equals(Blocks.AIR)) {
                if (HoleCheck.mc.world.getBlockState(blockPos.offset((EnumFacing)list.get(0)).north().up()).getBlock().equals(Blocks.AIR)) {
                    list2.add(EnumFacing.NORTH);
                }
            }
            if (HoleCheck.mc.world.getBlockState(blockPos.offset((EnumFacing)list.get(0)).south()).getBlock().equals(Blocks.AIR) && HoleCheck.mc.world.getBlockState(blockPos.offset((EnumFacing)list.get(0)).south().up()).getBlock().equals(Blocks.AIR)) {
                list2.add(EnumFacing.SOUTH);
            }
            if (list2.size() == 1) {
                ChatUtils.message("Found Double Hole at: " + blockPos.getX() + " " + blockPos.getY() + " " + blockPos.getZ() + " facing: " + list.get(0));
                HoleESP.doubleHoles.add(new AxisAlignedBB((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)blockPos.offset((EnumFacing)list.get(0)).getX(), (double)blockPos.offset((EnumFacing)list.get(0)).getY(), (double)blockPos.offset((EnumFacing)list.get(0)).getZ()));
            }
        }
    }
    
    @Override
    public void run() {
        final int n = (int)Math.ceil(HoleESP.sphereRange.getValue());
        final List sphere = HoleESP.getSphere(HoleESP.getPlayerPos(), (float)n, n, false, true, 0);
        final HashMap hashMap = new HashMap();
        sphere.stream().filter(HoleCheck::lambda$run$0).forEach(HoleCheck::lambda$run$1);
    }
    
    static {
        HoleCheck.mc = Minecraft.getMinecraft();
    }
    
    public static boolean lambda$run$0(final BlockPos blockPos) {
        if (HoleCheck.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR) {
            if (HoleCheck.mc.world.getBlockState(blockPos.up()).getBlock() == Blocks.AIR) {
                return true;
            }
        }
        return false;
    }
}
