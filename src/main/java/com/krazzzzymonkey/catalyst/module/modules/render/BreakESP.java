/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.function.BiConsumer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.util.math.BlockPos;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.init.Blocks;
import net.minecraft.client.renderer.DestroyBlockProgress;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class BreakESP extends Modules
{
    public DoubleValue range;
    public BooleanValue rainbow;
    public ColorValue colorValue;
    
    public void lambda$onWorldRender$0(final Integer n, final DestroyBlockProgress destroyBlockProgress) {
        if (destroyBlockProgress != null) {
            final BlockPos position = destroyBlockProgress.getPosition();
            if (BreakESP.mc.world.getBlockState(position).getBlock() == Blocks.AIR || BreakESP.mc.world.getBlockState(position).getBlock() == Blocks.BEDROCK) {
                return;
            }
            if (position.getDistance((int)BreakESP.mc.player.posX, (int)BreakESP.mc.player.posY, (int)BreakESP.mc.player.posZ) <= this.range.getValue()) {
                final float n2 = destroyBlockProgress.getPartialBlockDamage() / 8.0f;
                if (this.rainbow.getValue()) {
                    RenderUtils.drawBlockESP(position, ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 1.0f - n2);
                }
                else {
                    RenderUtils.drawBlockESP(position, this.colorValue.getColor().getRed() / 255.0f, this.colorValue.getColor().getGreen() / 255.0f, this.colorValue.getColor().getBlue() / 255.0f, 1.0f - n2);
                }
            }
        }
    }
    
    public BreakESP() {
        super("BreakESP", ModuleCategory.RENDER, "Highlights what blocks are being broken by players");
        this.range = new DoubleValue("Range", 80.0, 1.0, 100.0);
        this.colorValue = new ColorValue("Color", Color.CYAN);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.addValue(this.range, this.colorValue, this.rainbow);
    }
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent renderWorldLastEvent) {
        if (BreakESP.mc.player == null || BreakESP.mc.world == null) {
            return;
        }
        BreakESP.mc.renderGlobal.damagedBlocks.forEach(this::lambda$onWorldRender$0);
    }
}
