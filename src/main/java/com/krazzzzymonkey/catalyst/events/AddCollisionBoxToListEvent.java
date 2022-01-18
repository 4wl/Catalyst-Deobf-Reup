/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.events;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class AddCollisionBoxToListEvent extends Event
{
    public World world;
    public boolean bool;
    public AxisAlignedBB entityBox;
    public IBlockState state;
    public Block block;
    public BlockPos pos;
    public Entity entity;
    public List<AxisAlignedBB> collidingBoxes;
    
    public boolean isBool() {
        return this.bool;
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public IBlockState getState() {
        return this.state;
    }
    
    public AddCollisionBoxToListEvent(final Block block, final IBlockState state, final World world, final BlockPos pos, final AxisAlignedBB entityBox, final List collidingBoxes, final Entity entity, final boolean bool) {
        this.block = block;
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.entityBox = entityBox;
        this.collidingBoxes = (List<AxisAlignedBB>)collidingBoxes;
        this.entity = entity;
        this.bool = bool;
    }
    
    public AxisAlignedBB getEntityBox() {
        return this.entityBox;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public Block getBlock() {
        return this.block;
    }
    
    public List getCollidingBoxes() {
        return this.collidingBoxes;
    }
    
    public BlockPos getPos() {
        return this.pos;
    }
}
