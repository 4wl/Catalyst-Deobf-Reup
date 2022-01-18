/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraft.block.Block;
import java.util.Iterator;
import net.minecraft.util.math.Vec3i;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.HashSet;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import net.minecraft.util.math.BlockPos;
import java.util.concurrent.ConcurrentHashMap;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class HoleESP extends Modules
{
    public ModeValue modes;
    public static DoubleValue sphereRange;
    public static IntegerValue opacityStart;
    public BooleanValue obsidianRainbowColor;
    public ConcurrentHashMap<BlockPos, Boolean> safeHoles;
    public BooleanValue doubleHole;
    public ColorValue bedrockColorValue;
    public ColorValue obsidianColorValue;
    public static HashSet<AxisAlignedBB> doubleHoles;
    public static BooleanValue groundPlate;
    public BooleanValue bedrockRainbowColor;
    public static DoubleValue startOffset;
    public BlockPos[] surroundOffset;
    public Thread f;
    public static IntegerValue groundPlateOpacity;
    public static DoubleValue height;
    public static IntegerValue opacityEnd;
    
    public void lambda$onRenderWorldLast$3(final BlockPos blockPos, final Boolean b) {
        Label_1527: {
            if (b) {
                if (this.bedrockRainbowColor.getValue()) {
                    RenderUtils.drawHaloESP(new AxisAlignedBB((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)), ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 0.1f, 0.0f, HoleESP.height.getValue().floatValue(), (boolean)HoleESP.groundPlate.getValue(), HoleESP.groundPlateOpacity.getValue(), HoleESP.opacityStart.getValue(), HoleESP.opacityEnd.getValue());
                }
                else {
                    final AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1));
                    final float n = this.bedrockColorValue.getColor().getRed() / 255.0f;
                    final float n2 = this.bedrockColorValue.getColor().getGreen() / 255.0f;
                    while (true) {
                        switch (-2009096492 + 1581129144 + 1) {
                            case 117621008: {
                                continue;
                            }
                            default: {
                                RenderUtils.drawHaloESP(axisAlignedBB, n, n2, this.bedrockColorValue.getColor().getBlue() / 255.0f, 0.1f, 0.0f, HoleESP.height.getValue().floatValue(), (boolean)HoleESP.groundPlate.getValue(), HoleESP.groundPlateOpacity.getValue(), HoleESP.opacityStart.getValue(), HoleESP.opacityEnd.getValue());
                                break Label_1527;
                            }
                            case -137212094: {
                                throw null;
                            }
                        }
                    }
                }
            }
            else if (this.obsidianRainbowColor.getValue()) {
                RenderUtils.drawHaloESP(new AxisAlignedBB((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)), ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 0.1f, 0.0f, HoleESP.height.getValue().floatValue(), (boolean)HoleESP.groundPlate.getValue(), HoleESP.groundPlateOpacity.getValue(), HoleESP.opacityStart.getValue(), HoleESP.opacityEnd.getValue());
            }
            else {
                RenderUtils.drawHaloESP(new AxisAlignedBB((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)), this.obsidianColorValue.getColor().getRed() / 255.0f, this.obsidianColorValue.getColor().getGreen() / 255.0f, this.obsidianColorValue.getColor().getBlue() / 255.0f, 0.1f, 0.0f, HoleESP.height.getValue().floatValue(), (boolean)HoleESP.groundPlate.getValue(), HoleESP.groundPlateOpacity.getValue(), HoleESP.opacityStart.getValue(), HoleESP.opacityEnd.getValue());
            }
        }
    }
    
    public HoleESP() {
        super("HoleESP", ModuleCategory.RENDER, "Shows you where a safe holes are when crystal pvping");
        this.modes = new ModeValue("RenderMode", new Mode[] { new Mode("Box", true), new Mode("Outline", false), new Mode("Halo", false) });
        this.doubleHole = new BooleanValue("DoubleHole", false);
        this.bedrockColorValue = new ColorValue("BedrockColor", Color.GREEN);
        this.bedrockRainbowColor = new BooleanValue("BedrockRainbow", false);
        this.obsidianColorValue = new ColorValue("ObsidianColor", Color.RED);
        this.obsidianRainbowColor = new BooleanValue("ObsidianRainbow", false);
        this.surroundOffset = new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) };
        this.f = new Thread(new HoleCheck());
        this.addValue(this.modes, HoleESP.sphereRange, HoleESP.height, HoleESP.opacityStart, HoleESP.opacityEnd, HoleESP.groundPlate, HoleESP.groundPlateOpacity, this.bedrockColorValue, this.bedrockRainbowColor, this.obsidianColorValue, this.obsidianRainbowColor);
    }
    
    public void lambda$onRenderWorldLast$2(final BlockPos blockPos, final Boolean b) {
        if (b) {
            if (this.bedrockRainbowColor.getValue()) {
                RenderUtils.drawOutlinedBox(AxisAligned(blockPos), ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 1.0f, HoleESP.height.getValue());
            }
            else {
                RenderUtils.drawOutlinedBox(AxisAligned(blockPos), this.bedrockColorValue.getColor().getRed() / 255.0f, this.bedrockColorValue.getColor().getGreen() / 255.0f, this.bedrockColorValue.getColor().getBlue() / 255.0f, 1.0f, HoleESP.height.getValue());
            }
        }
        else if (this.obsidianRainbowColor.getValue()) {
            RenderUtils.drawOutlinedBox(AxisAligned(blockPos), ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 1.0f, HoleESP.height.getValue());
        }
        else {
            RenderUtils.drawOutlinedBox(AxisAligned(blockPos), this.obsidianColorValue.getColor().getRed() / 255.0f, this.obsidianColorValue.getColor().getGreen() / 255.0f, this.obsidianColorValue.getColor().getBlue() / 255.0f, 1.0f, HoleESP.height.getValue());
        }
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(HoleESP.mc.player.posX), Math.floor(HoleESP.mc.player.posY), Math.floor(HoleESP.mc.player.posZ));
    }
    
    public static AxisAlignedBB AxisAligned(final BlockPos blockPos) {
        return new AxisAlignedBB(blockPos.getX() - Minecraft.getMinecraft().getRenderManager().viewerPosX, blockPos.getY() - Minecraft.getMinecraft().getRenderManager().viewerPosY, blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().viewerPosZ, blockPos.getX() + 1 - Minecraft.getMinecraft().getRenderManager().viewerPosX, blockPos.getY() + 1 - Minecraft.getMinecraft().getRenderManager().viewerPosY, blockPos.getZ() + 1 - Minecraft.getMinecraft().getRenderManager().viewerPosZ);
    }
    
    public static List getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int x = blockPos.getX();
        final int y = blockPos.getY();
        final int z = blockPos.getZ();
        final int n4 = x;
        final int n5 = (int)n;
        for (int n6 = (n4 & ~n5) + (n5 & ~n4); n6 <= x + n; ++n6) {
            for (int n7 = z - (int)n; n7 <= z + n; ++n7) {
                for (int n8 = b2 ? (y - (int)n) : y; n8 < (b2 ? (y + n) : ((float)(y + n2))); ++n8) {
                    final double n9 = (x - n6) * (x - n6) + (z - n7) * (z - n7) + (b2 ? ((y - n8) * (y - n8)) : 0);
                    if (n9 < n * n && (!b || n9 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n6, n8 + n3, n7));
                    }
                }
            }
        }
        return list;
    }
    
    public void lambda$onRenderWorldLast$1(final BlockPos blockPos, final Boolean b) {
        if (b) {
            if (this.bedrockRainbowColor.getValue()) {
                RenderUtils.drawBlockESP(blockPos, ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, HoleESP.height.getValue());
            }
            else {
                RenderUtils.drawBlockESP(blockPos, this.bedrockColorValue.getColor().getRed() / 255.0f, this.bedrockColorValue.getColor().getGreen() / 255.0f, this.bedrockColorValue.getColor().getBlue() / 255.0f, HoleESP.height.getValue());
            }
        }
        else if (this.obsidianRainbowColor.getValue()) {
            RenderUtils.drawBlockESP(blockPos, ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, HoleESP.height.getValue());
        }
        else {
            RenderUtils.drawBlockESP(blockPos, this.obsidianColorValue.getColor().getRed() / 255.0f, this.obsidianColorValue.getColor().getGreen() / 255.0f, this.obsidianColorValue.getColor().getBlue() / 255.0f, HoleESP.height.getValue());
        }
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        if (HoleESP.mc.player == null || this.safeHoles == null) {
            return;
        }
        HoleESP.doubleHoles.forEach(HoleESP::lambda$onRenderWorldLast$0);
        if (this.safeHoles.isEmpty()) {
            return;
        }
        if (this.modes.getMode("Box").isToggled()) {
            this.safeHoles.forEach(this::lambda$onRenderWorldLast$1);
        }
        if (this.modes.getMode("Outline").isToggled()) {
            this.safeHoles.forEach(this::lambda$onRenderWorldLast$2);
        }
        if (this.modes.getMode("Halo").isToggled()) {
            this.safeHoles.forEach(this::lambda$onRenderWorldLast$3);
        }
    }
    
    public static void lambda$onRenderWorldLast$0(final AxisAlignedBB axisAlignedBB) {
        RenderUtils.drawHaloESP(axisAlignedBB, ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 0.1f, 0.0f, HoleESP.height.getValue().floatValue(), (boolean)HoleESP.groundPlate.getValue(), HoleESP.groundPlateOpacity.getValue(), HoleESP.opacityStart.getValue(), HoleESP.opacityEnd.getValue());
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (this.safeHoles == null) {
            this.safeHoles = new ConcurrentHashMap<BlockPos, Boolean>();
        }
        else {
            this.safeHoles.clear();
        }
        final int n = (int)Math.ceil(HoleESP.sphereRange.getValue());
        final List sphere = getSphere(getPlayerPos(), (float)n, n, false, true, 0);
        if (!this.f.isAlive() && (boolean)this.doubleHole.getValue()) {
            HoleESP.doubleHoles.clear();
            this.f.run();
        }
        for (final BlockPos key : sphere) {
            if (!HoleESP.mc.world.getBlockState(key).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!HoleESP.mc.world.getBlockState(key.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!HoleESP.mc.world.getBlockState(key.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            boolean b = true;
            boolean b2 = true;
            final BlockPos[] surroundOffset = this.surroundOffset;
            for (int length = surroundOffset.length, i = 0; i < length; ++i) {
                final Block block = HoleESP.mc.world.getBlockState(key.add((Vec3i)surroundOffset[i])).getBlock();
                if (block != Blocks.BEDROCK) {
                    b2 = false;
                }
                if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN) {
                    b = false;
                    break;
                }
            }
            if (!b) {
                continue;
            }
            this.safeHoles.put(key, b2);
        }
    }
    
    static {
        HoleESP.sphereRange = new DoubleValue("RenderRange", 10.0, 0.0, 40.0);
        HoleESP.opacityStart = new IntegerValue("StartOpacity", 100, 0, 100);
        HoleESP.opacityEnd = new IntegerValue("EndOpacity", 0, 0, 100);
        HoleESP.startOffset = new DoubleValue("StartOffset", 0.0, -1.0, 2.0);
        HoleESP.height = new DoubleValue("Height", 0.0, 0.0, 2.0);
        HoleESP.groundPlate = new BooleanValue("DrawBottomQuad", true);
        HoleESP.groundPlateOpacity = new IntegerValue("BottomQuadOpacity", 50, 0, 100);
        HoleESP.doubleHoles = new HashSet<AxisAlignedBB>();
    }
}
