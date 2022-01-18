/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.xray;

import net.minecraft.util.math.BlockPos;

public class XRayBlock
{
    public XRayData xRayData;
    public BlockPos blockPos;
    
    public XRayBlock(final BlockPos blockPos, final XRayData xRayData) {
        this.blockPos = blockPos;
        this.xRayData = xRayData;
    }
    
    public BlockPos getBlockPos() {
        return this.blockPos;
    }
    
    public XRayData getxRayData() {
        return this.xRayData;
    }
}
