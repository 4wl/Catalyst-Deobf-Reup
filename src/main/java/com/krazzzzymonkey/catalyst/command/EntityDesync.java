/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class EntityDesync extends Command
{
    public static Entity getRidingEntity;
    
    public EntityDesync() {
        super("entitydesync");
    }
    
    @Override
    public String getSyntax() {
        return "entitydesync <dismount/remount>";
    }
    
    @Override
    public String getDescription() {
        return "Dismounts from a rideable entity clientside";
    }
    
    static {
        EntityDesync.getRidingEntity = null;
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        String string;
        try {
            if (array.length > 1 || array[0].equals("")) {
                throw new Exception();
            }
            if (array[0].equalsIgnoreCase("dismount")) {
                if (!Minecraft.getMinecraft().player.isRiding()) {
                    ChatUtils.error("You are not currently riding an entity!");
                    EntityDesync.getRidingEntity = null;
                    return;
                }
                EntityDesync.getRidingEntity = Minecraft.getMinecraft().player.getRidingEntity();
                Minecraft.getMinecraft().player.dismountRidingEntity();
                Minecraft.getMinecraft().world.removeEntity(EntityDesync.getRidingEntity);
                ChatUtils.message("Successfully dismounted from the " + EntityDesync.getRidingEntity.getName());
            }
            else {
                if (!array[0].equalsIgnoreCase("remount")) {
                    throw new Exception();
                }
                if (EntityDesync.getRidingEntity != null) {
                    EntityDesync.getRidingEntity.isDead = false;
                    if (!Minecraft.getMinecraft().player.isRiding()) {
                        Minecraft.getMinecraft().world.spawnEntity(EntityDesync.getRidingEntity);
                        Minecraft.getMinecraft().player.startRiding(EntityDesync.getRidingEntity, true);
                    }
                    ChatUtils.message("Force remounted you to the " + EntityDesync.getRidingEntity.getName());
                    EntityDesync.getRidingEntity = null;
                }
            }
            return;
        }
        catch (Exception ex) {
            string = "Usage: " + this.getSyntax();
        }
        ChatUtils.error(string);
    }
}
