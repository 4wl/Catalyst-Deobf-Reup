/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.events;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class DamageBlockEvent extends Event
{
    public BlockPos pos;
    public EnumFacing facing;
    
    public void setFacing(final EnumFacing facing) {
        this.facing = facing;
    }
    
    public BlockPos getPos() {
        return this.pos;
    }
    
    public void setPos(final BlockPos pos) {
        this.pos = pos;
    }
    
    public EnumFacing getFacing() {
        return this.facing;
    }
    
    public DamageBlockEvent(final BlockPos pos, final EnumFacing facing) {
        this.facing = facing;
        this.pos = pos;
    }
}
