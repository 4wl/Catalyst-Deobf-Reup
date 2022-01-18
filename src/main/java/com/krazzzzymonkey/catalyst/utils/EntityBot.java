/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraft.entity.player.EntityPlayer;
import java.util.UUID;

public class EntityBot
{
    public boolean ground;
    public String name;
    public UUID uuid;
    public int id;
    public boolean invisible;
    
    public String getName() {
        return this.name;
    }
    
    public EntityBot(final EntityPlayer entityPlayer) {
        this.name = String.valueOf(entityPlayer.getGameProfile().getName());
        this.id = entityPlayer.getEntityId();
        this.uuid = entityPlayer.getGameProfile().getId();
        this.invisible = entityPlayer.isInvisible();
        this.ground = entityPlayer.onGround;
    }
    
    public boolean isGround() {
        return this.ground;
    }
    
    public UUID getUuid() {
        return this.uuid;
    }
    
    public int getId() {
        return this.id;
    }
    
    public boolean isInvisible() {
        return this.invisible;
    }
}
