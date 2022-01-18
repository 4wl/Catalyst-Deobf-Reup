/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class ObsidianReplace$PlacementRequest
{
    public BlockPos structurePosition;
    public EnumFacing placeDirection;
    
    public EnumFacing getPlaceDirection() {
        return this.placeDirection;
    }
    
    public ObsidianReplace$PlacementRequest(final BlockPos structurePosition, final EnumFacing placeDirection) {
        this.structurePosition = structurePosition;
        this.placeDirection = placeDirection;
    }
    
    public BlockPos getStructurePosition() {
        return this.structurePosition;
    }
}
