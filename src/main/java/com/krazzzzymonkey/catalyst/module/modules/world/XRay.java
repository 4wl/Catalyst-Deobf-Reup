/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.world;

import java.util.Iterator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.EntityLivingBase;
import com.krazzzzymonkey.catalyst.utils.BlockUtils;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.xray.XRayData;
import com.krazzzzymonkey.catalyst.managers.XRayManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.xray.XRayBlock;
import java.util.LinkedList;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.utils.TimerUtils;
import com.krazzzzymonkey.catalyst.module.Modules;

public class XRay extends Modules
{
    public TimerUtils timer;
    public IntegerValue delay;
    public LinkedList<XRayBlock> blocks;
    public DoubleValue distance;
    
    public XRay() {
        super("XRay", ModuleCategory.WORLD, "Allows you to see specific blocks");
        this.blocks = new LinkedList<XRayBlock>();
        this.distance = new DoubleValue("Distance", 50.0, 4.0, 100.0);
        this.delay = new IntegerValue("UpdateDelay", 100, 0, 300);
        this.timer = new TimerUtils();
        this.addValue(this.distance, this.delay);
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        RenderUtils.drawXRayBlocks(this.blocks, renderWorldLastEvent.getPartialTicks());
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        final int intValue = this.distance.getValue().intValue();
        if (!this.timer.isDelay(this.delay.getValue() * 10)) {
            return;
        }
        this.blocks.clear();
        for (final XRayData xRayData : XRayManager.xrayList) {
            final Iterator iterator2 = BlockUtils.findBlocksNearEntity((EntityLivingBase)Wrapper.INSTANCE.player(), xRayData.getId(), xRayData.getMeta(), intValue).iterator();
            while (iterator2.hasNext()) {
                this.blocks.add(new XRayBlock(iterator2.next(), xRayData));
            }
        }
        this.timer.setLastMS();
    }
    
    @Override
    public void onEnable() {
        this.blocks.clear();
        super.onEnable();
    }
}
