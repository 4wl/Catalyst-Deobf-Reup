/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import net.minecraft.util.math.BlockPos;

public class CrystalPos
{
    public Double damage;
    public BlockPos pos;
    
    public CrystalPos(final BlockPos pos, final Double damage) {
        this.pos = pos;
        this.damage = damage;
    }
    
    public Double getDamage() {
        return this.damage;
    }
    
    public BlockPos getPos() {
        return this.pos;
    }
}
