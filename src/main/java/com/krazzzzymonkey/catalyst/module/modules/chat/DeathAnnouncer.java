/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.chat;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class DeathAnnouncer extends Modules
{
    public BooleanValue clientSide;
    
    @SubscribeEvent
    public void onDeath(final LivingDeathEvent livingDeathEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null) {
                if (Minecraft.getMinecraft().player.getUniqueID() != null) {
                    if (Minecraft.getMinecraft().player == null) {
                        return;
                    }
                    if (livingDeathEvent.getEntity() instanceof EntityPlayer) {
                        if (this.clientSide.getValue()) {
                            ChatUtils.message(livingDeathEvent.getEntity().getName() + " Just died in your render distance!");
                        }
                        else {
                            Minecraft.getMinecraft().player.sendChatMessage(livingDeathEvent.getEntity().getName() + " Just died in my render distance!");
                        }
                    }
                }
            }
        }
    }
    
    public DeathAnnouncer() {
        super("DeathAnnouncer", ModuleCategory.CHAT, "Announces who dies in render distance (ClientSide)");
        this.clientSide = new BooleanValue("ClientSide", true);
        this.addValue(this.clientSide);
    }
}
