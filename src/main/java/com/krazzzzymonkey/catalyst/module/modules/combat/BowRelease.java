/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.play.client.CPacketPlayerDigging$Action;
import net.minecraft.item.ItemBow;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class BowRelease extends Modules
{
    public IntegerValue Timeout;
    
    public BowRelease() {
        super("AutoBowRelease", ModuleCategory.COMBAT, "Automatically releases bow in specified amount of ticks");
        this.Timeout = new IntegerValue("Ticks", 1, 1, 25);
        this.addValue(this.Timeout);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        final Double value = this.Timeout.getValue() + 1.15;
        value.longValue();
        final Minecraft minecraft = Minecraft.getMinecraft();
        if (minecraft.player.getHeldItemMainhand().getItem() instanceof ItemBow && minecraft.player.isHandActive() && minecraft.player.getItemInUseMaxCount() >= value) {
            minecraft.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, minecraft.player.getHorizontalFacing()));
            minecraft.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(minecraft.player.getActiveHand()));
            minecraft.player.stopActiveHand();
        }
    }
}
