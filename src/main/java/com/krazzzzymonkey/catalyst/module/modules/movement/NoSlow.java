/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.movement;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.MovementInput;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputUpdateEvent;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class NoSlow extends Modules
{
    public static BooleanValue slimeBlocks;
    public BooleanValue items;
    public static BooleanValue soulSand;
    public static BooleanValue web;
    
    @SubscribeEvent
    public void onInputUpdate(final InputUpdateEvent inputUpdateEvent) {
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        if ((boolean)this.items.getValue() && Minecraft.getMinecraft().player.isHandActive() && !Minecraft.getMinecraft().player.isRiding()) {
            final MovementInput movementInput = Minecraft.getMinecraft().player.movementInput;
            movementInput.moveForward *= 5.0f;
            final MovementInput movementInput2 = Minecraft.getMinecraft().player.movementInput;
            movementInput2.moveStrafe *= 5.0f;
        }
    }
    
    public NoSlow() {
        super("NoSlow", ModuleCategory.MOVEMENT, "Makes you take no item slow down");
        this.items = new BooleanValue("Items", true);
        NoSlow.soulSand = new BooleanValue("Soul Sand", true);
        NoSlow.slimeBlocks = new BooleanValue("Slime Blocks", true);
        NoSlow.web = new BooleanValue("Webs", true);
        this.addValue(this.items, NoSlow.soulSand, NoSlow.slimeBlocks, NoSlow.web);
    }
}
