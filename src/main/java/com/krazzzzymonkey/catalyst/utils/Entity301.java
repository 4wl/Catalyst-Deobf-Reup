/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import com.mojang.authlib.GameProfile;
import net.minecraft.world.World;
import net.minecraft.entity.MoverType;
import net.minecraft.util.MovementInput;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public class Entity301 extends EntityOtherPlayerMP
{
    public static MovementInput movementInput;
    
    public void move(final MoverType moverType, final double n, final double n2, final double n3) {
        this.onGround = true;
        super.move(moverType, n, n2, n3);
        this.onGround = true;
    }
    
    public void setMovementInput(final MovementInput movementInput) {
        Entity301.movementInput = movementInput;
        if (movementInput.jump) {
            if (this.onGround) {
                super.jump();
            }
        }
        super.moveRelative(movementInput.moveStrafe, this.moveVertical, movementInput.moveForward, this.movedDistance);
    }
    
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.noClip = true;
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        this.noClip = false;
    }
    
    public boolean isSneaking() {
        return false;
    }
    
    static {
        Entity301.movementInput = null;
    }
    
    public Entity301(final World world, final GameProfile gameProfile) {
        super(world, gameProfile);
    }
}
