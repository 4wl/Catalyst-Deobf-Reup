/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraft.util.EnumFacing;
import java.util.HashMap;

public class GeometryMasks
{
    public static HashMap<EnumFacing, Integer> FACEMAP;
    
    static {
        (GeometryMasks.FACEMAP = new HashMap<EnumFacing, Integer>()).put(EnumFacing.DOWN, 1);
        GeometryMasks.FACEMAP.put(EnumFacing.WEST, 16);
        GeometryMasks.FACEMAP.put(EnumFacing.NORTH, 4);
        GeometryMasks.FACEMAP.put(EnumFacing.SOUTH, 8);
        GeometryMasks.FACEMAP.put(EnumFacing.EAST, 32);
        GeometryMasks.FACEMAP.put(EnumFacing.UP, 2);
    }
}
