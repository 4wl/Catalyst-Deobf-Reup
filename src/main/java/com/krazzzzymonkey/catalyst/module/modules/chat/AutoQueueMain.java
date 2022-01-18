/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.chat;

import net.minecraft.util.math.Vec3i;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoQueueMain extends Modules
{
    public IntegerValue delay;
    public int Delay;
    public BooleanValue showMessage;
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        Label_0120: {
            if (Minecraft.getMinecraft().world != null) {
                final Minecraft minecraft = Minecraft.getMinecraft();
                while (true) {
                    switch (-1892797855 + 1212900346 + 1) {
                        case -242451502: {
                            continue;
                        }
                        case -949661285: {
                            if (minecraft.player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
                                break Label_0120;
                            }
                            ++this.Delay;
                            if (this.Delay > this.delay.getValue()) {
                                if (this.shouldSendMessage((EntityPlayer)Minecraft.getMinecraft().player)) {
                                    Minecraft.getMinecraft().player.sendChatMessage("/queue main");
                                    if (this.showMessage.getValue()) {
                                        ChatUtils.message("Sent: /queue main");
                                    }
                                }
                                this.Delay = 0;
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
    
    public AutoQueueMain() {
        super("AutoQueueMain", ModuleCategory.CHAT, "Automatically sends \"/queue main\" while waiting in 2b2t queue");
        this.Delay = 0;
        this.showMessage = new BooleanValue("Show Message Sent", true);
        this.delay = new IntegerValue("Delay", 50, 1, 500);
        this.addValue(this.showMessage, this.delay);
    }
    
    public boolean shouldSendMessage(final EntityPlayer entityPlayer) {
        final boolean b = entityPlayer.dimension == 1;
        final boolean equals = entityPlayer.getPosition().equals((Object)new Vec3i(0, 240, 0));
        return b && equals;
    }
}
