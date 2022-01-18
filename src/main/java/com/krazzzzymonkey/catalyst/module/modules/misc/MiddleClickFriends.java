/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.RayTraceResult;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult$Type;
import org.lwjgl.input.Mouse;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;

public class MiddleClickFriends extends Modules
{
    public boolean Clicked;
    
    public MiddleClickFriends() {
        super("MiddleClickFriends", ModuleCategory.MISC, "Allows you to  add or remove a player from friend list");
        this.Clicked = false;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        Label_0136: {
            if (Minecraft.getMinecraft().world != null) {
                final Minecraft minecraft = Minecraft.getMinecraft();
                while (true) {
                    switch (2107396078 + 273128 + 1) {
                        case -1347527327: {
                            continue;
                        }
                        case 2107139334: {
                            if (minecraft.player == null) {
                                break Label_0136;
                            }
                            if (Minecraft.getMinecraft().player.getUniqueID() == null) {
                                break Label_0136;
                            }
                            if (Minecraft.getMinecraft().currentScreen != null) {
                                return;
                            }
                            if (!Mouse.isButtonDown(2)) {
                                this.Clicked = false;
                                return;
                            }
                            if (!this.Clicked) {
                                this.Clicked = true;
                                final RayTraceResult objectMouseOver = Minecraft.getMinecraft().objectMouseOver;
                                if (objectMouseOver == null || objectMouseOver.typeOfHit != RayTraceResult$Type.ENTITY) {
                                    return;
                                }
                                final Entity entityHit = objectMouseOver.entityHit;
                                if (entityHit == null || !(entityHit instanceof EntityPlayer)) {
                                    return;
                                }
                                if (FriendManager.friendsList.contains(entityHit.getName())) {
                                    FriendManager.removeFriend(entityHit.getName());
                                }
                                else {
                                    FriendManager.addFriend(entityHit.getName());
                                }
                            }
                            return;
                        }
                        default: {
                            throw null;
                        }
                    }
                }
            }
        }
    }
}
