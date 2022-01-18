/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraft.init.Blocks;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;

public class BlockData
{
    public EnumFacing face;
    public BlockPos position;
    
    public BlockData(final BlockPos position, final EnumFacing face) {
        this.position = position;
        this.face = face;
    }
    
    public static BlockData getBlockData(final BlockPos blockPos) {
        if (Wrapper.INSTANCE.world().getBlockState(blockPos.add(0, -1, 0)).getBlock() != Blocks.AIR) {
            return new BlockData(blockPos.add(0, -1, 0), EnumFacing.UP);
        }
        if (Wrapper.INSTANCE.world().getBlockState(blockPos.add(-1, 0, 0)).getBlock() != Blocks.AIR) {
            return new BlockData(blockPos.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (Wrapper.INSTANCE.world().getBlockState(blockPos.add(1, 0, 0)).getBlock() != Blocks.AIR) {
            return new BlockData(blockPos.add(1, 0, 0), EnumFacing.WEST);
        }
        if (Wrapper.INSTANCE.world().getBlockState(blockPos.add(0, 0, -1)).getBlock() != Blocks.AIR) {
            return new BlockData(blockPos.add(0, 0, -1), EnumFacing.SOUTH);
        }
        if (Wrapper.INSTANCE.world().getBlockState(blockPos.add(0, 0, 1)).getBlock() != Blocks.AIR) {
            return new BlockData(blockPos.add(0, 0, 1), EnumFacing.NORTH);
        }
        return null;
    }
}
