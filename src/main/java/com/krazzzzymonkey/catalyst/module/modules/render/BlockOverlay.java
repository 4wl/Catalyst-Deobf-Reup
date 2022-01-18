/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.block.Block;
import net.minecraft.util.math.RayTraceResult$Type;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.util.math.BlockPos;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class BlockOverlay extends Modules
{
    public ColorValue colorValue;
    public BooleanValue rainbow;
    public double y;
    public BlockPos blockPos;
    public double x;
    public double z;
    
    public BlockOverlay() {
        super("BlockOverlay", ModuleCategory.RENDER, "Highlights block you are looking at");
        this.colorValue = new ColorValue("Color", Color.CYAN);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.addValue(this.colorValue, this.rainbow);
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        if (Wrapper.INSTANCE.mc().objectMouseOver == null) {
            return;
        }
        if (Wrapper.INSTANCE.mc().objectMouseOver.typeOfHit == RayTraceResult$Type.BLOCK) {
            final Block block = Wrapper.INSTANCE.world().getBlockState(Wrapper.INSTANCE.mc().objectMouseOver.getBlockPos()).getBlock();
            this.blockPos = Wrapper.INSTANCE.mc().objectMouseOver.getBlockPos();
            if (Block.getIdFromBlock(block) == 0) {
                return;
            }
            if (this.rainbow.getValue()) {
                RenderUtils.drawBlockESP(this.blockPos, ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 1.0);
            }
            else {
                RenderUtils.drawBlockESP(this.blockPos, this.colorValue.getColor().getRed() / 255.0f, this.colorValue.getColor().getGreen() / 255.0f, this.colorValue.getColor().getBlue() / 255.0f, 1.0);
            }
        }
        this.x = Wrapper.INSTANCE.mc().getRenderManager().viewerPosX;
        this.y = Wrapper.INSTANCE.mc().getRenderManager().viewerPosY;
        this.z = Wrapper.INSTANCE.mc().getRenderManager().viewerPosZ;
    }
}
