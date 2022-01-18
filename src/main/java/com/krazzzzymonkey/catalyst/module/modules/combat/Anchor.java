/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.block.Block;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.util.math.BlockPos;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Anchor extends Modules
{
    public DoubleValue height;
    public static BlockPos[] surroundOffset;
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        final double floor = Math.floor(Anchor.mc.player.posX);
        final double floor2 = Math.floor(Anchor.mc.player.posZ);
        final double abs = Math.abs(Anchor.mc.player.posX - floor);
        final double abs2 = Math.abs(Anchor.mc.player.posZ - floor2);
        if (abs >= 0.30000001192092896) {
            if (abs <= 0.699999988079071) {
                if (abs2 >= 0.30000001192092896 && abs2 <= 0.699999988079071) {
                    final BlockPos blockPos = new BlockPos(floor, Anchor.mc.player.posY, floor2);
                    if (Anchor.mc.world.getBlockState(blockPos).getBlock() != Blocks.AIR) {
                        return;
                    }
                    final BlockPos blockPos2 = blockPos;
                    while (true) {
                        switch (1026290920 + 1059995322 + 1) {
                            case -1774795756: {
                                continue;
                            }
                            default: {
                                BlockPos blockPos3 = blockPos2.down();
                                for (int n = 0; n <= this.height.getValue(); ++n) {
                                    blockPos3 = blockPos3.down();
                                    if (Anchor.mc.world.getBlockState(blockPos3).getBlock() != Blocks.AIR && getUnsafeSides(blockPos3.up())) {
                                        Anchor.mc.player.motionX = 0.0;
                                        Anchor.mc.player.motionZ = 0.0;
                                    }
                                }
                            }
                            case -1029428793: {
                                throw null;
                            }
                        }
                    }
                }
            }
        }
    }
    
    static {
        Anchor.surroundOffset = new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) };
    }
    
    public static boolean getUnsafeSides(final BlockPos blockPos) {
        if (!Anchor.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.AIR)) {
            return false;
        }
        if (!Anchor.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
            return false;
        }
        if (!Anchor.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
            return false;
        }
        boolean b = true;
        final BlockPos[] surroundOffset = Anchor.surroundOffset;
        for (int length = surroundOffset.length, i = 0; i < length; ++i) {
            final Block block = Anchor.mc.world.getBlockState(blockPos.add((Vec3i)surroundOffset[i])).getBlock();
            if (block != Blocks.BEDROCK) {}
            if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN) {
                b = false;
                break;
            }
        }
        return b;
    }
    
    public Anchor() {
        super("Anchor", ModuleCategory.COMBAT, "Stops all movement when above a hole");
        this.height = new DoubleValue("Height", 2.0, 1.0, 8.0);
        this.addValue(this.height);
    }
}
