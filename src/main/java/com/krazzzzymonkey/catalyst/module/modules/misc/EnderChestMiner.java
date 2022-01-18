/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import net.minecraft.util.math.MathHelper;
import java.util.Iterator;
import java.util.stream.Stream;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.krazzzzymonkey.catalyst.utils.Utils;
import net.minecraft.init.Items;
import java.util.function.Function;
import java.util.Comparator;
import java.util.function.Predicate;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import java.util.ArrayList;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import java.util.Collections;
import net.minecraft.init.Blocks;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.utils.PlayerControllerUtils;
import com.krazzzzymonkey.catalyst.utils.BlockUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.block.Block;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import java.util.Set;
import java.util.ArrayDeque;
import com.krazzzzymonkey.catalyst.module.Modules;

public class EnderChestMiner extends Modules
{
    public float progress;
    public ArrayDeque<Set<BlockPos>> prevBlocks;
    public int id;
    public float prevProgress;
    public List<Block> whiteList;
    public BlockPos currentBlock;
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        if (this.currentBlock == null) {
            return;
        }
        RenderUtils.drawBlockESP(this.currentBlock, 1.0f, 0.0f, 0.0f, 1.0);
    }
    
    public static boolean lambda$onClientTick$6(final Set set, final BlockPos blockPos) {
        return !set.contains(blockPos);
    }
    
    public static BlockPos GetLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(Minecraft.getMinecraft().player.posX), Math.floor(Minecraft.getMinecraft().player.posY), Math.floor(Minecraft.getMinecraft().player.posZ));
    }
    
    public static boolean lambda$onClientTick$3(final BlockPos blockPos) {
        return BlockUtils.canBeClicked(blockPos);
    }
    
    @Override
    public void onDisable() {
        if (this.currentBlock != null) {
            PlayerControllerUtils.setIsHittingBlock(true);
            Wrapper.INSTANCE.mc().playerController.resetBlockRemoving();
            this.currentBlock = null;
        }
        this.prevBlocks.clear();
        this.id = 0;
        super.onDisable();
    }
    
    public EnderChestMiner() {
        super("EnderChestMiner", ModuleCategory.MISC, "Automatically places and mines ender chests for you");
        this.prevBlocks = new ArrayDeque<Set<BlockPos>>();
        this.whiteList = Collections.singletonList(Blocks.ENDER_CHEST);
    }
    
    public static Double lambda$onClientTick$1(final BlockPos blockPos) {
        return GetDistanceOfEntityToBlock((Entity)Minecraft.getMinecraft().player, blockPos);
    }
    
    public static double GetDistanceOfEntityToBlock(final Entity entity, final BlockPos blockPos) {
        return GetDistance(entity.posX, entity.posY, entity.posZ, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }
    
    public static double lambda$onClientTick$4(final Vec3d vec3d, final BlockPos blockPos) {
        return vec3d.squareDistanceTo(new Vec3d((Vec3i)blockPos));
    }
    
    public boolean lambda$onClientTick$0(final BlockPos blockPos) {
        return this.IsValidBlockPos(blockPos);
    }
    
    public static boolean lambda$onClientTick$5(final BlockPos blockPos) {
        return Block.getIdFromBlock(Wrapper.INSTANCE.world().getBlockState(blockPos).getBlock()) == Block.getIdFromBlock(Block.getBlockById(130));
    }
    
    public static boolean lambda$onClientTick$2(final Vec3d vec3d, final double n, final BlockPos blockPos) {
        return vec3d.squareDistanceTo(new Vec3d((Vec3i)blockPos)) <= n;
    }
    
    public List getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int x = blockPos.getX();
        final int y = blockPos.getY();
        final int z = blockPos.getZ();
        for (int n4 = x - (int)n; n4 <= x + n; ++n4) {
            for (int n5 = z - (int)n; n5 <= z + n; ++n5) {
                for (int n6 = b2 ? (y - (int)n) : y; n6 < (b2 ? (y + n) : ((float)(y + n2))); ++n6) {
                    final double n7 = (x - n4) * (x - n4) + (z - n5) * (z - n5) + ~(b2 ? ((y - n6) * (y - n6)) : 0) - 1;
                    if (n7 < n * n && (!b || n7 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                }
            }
        }
        return list;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        final BlockPos blockPos = (BlockPos)this.getSphere(GetLocalPlayerPosFloored(), 4.0f, 4, false, true, 0).stream().filter(this::lambda$onClientTick$0).min(Comparator.comparing((Function<? super T, ? extends Comparable>)EnderChestMiner::lambda$onClientTick$1)).orElse(null);
        if (Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() != Items.DIAMOND_PICKAXE) {
            for (int i = 0; i < 9; ++i) {
                final ItemStack stackInSlot = Minecraft.getMinecraft().player.inventory.getStackInSlot(i);
                if (!stackInSlot.isEmpty()) {
                    if (stackInSlot.getItem() == Items.DIAMOND_PICKAXE) {
                        Minecraft.getMinecraft().player.inventory.currentItem = i;
                        Minecraft.getMinecraft().playerController.updateController();
                        break;
                    }
                }
            }
        }
        this.currentBlock = null;
        final Vec3d subtract = Utils.getEyesPos().subtract(0.5, 0.5, 0.5);
        final BlockPos blockPos2 = new BlockPos(Utils.getEyesPos());
        final double pow = Math.pow(6.0, 2.0);
        final int n = (int)Math.ceil(6.0);
        final List<Object> list = StreamSupport.stream(BlockPos.getAllInBox(blockPos2.add(n, n, n), blockPos2.add(-n, -n, -n)).spliterator(), true).filter(EnderChestMiner::lambda$onClientTick$2).filter((Predicate<? super Object>)EnderChestMiner::lambda$onClientTick$3).sorted(Comparator.comparingDouble(EnderChestMiner::lambda$onClientTick$4)).filter((Predicate<? super Object>)EnderChestMiner::lambda$onClientTick$5).collect((Collector<? super Object, ?, List<Object>>)Collectors.toList());
        if (Wrapper.INSTANCE.player().capabilities.isCreativeMode) {
            Stream<Object> stream = list.parallelStream();
            final Iterator<Set<BlockPos>> iterator = this.prevBlocks.iterator();
            while (iterator.hasNext()) {
                stream = stream.filter((Predicate<? super Object>)EnderChestMiner::lambda$onClientTick$6);
            }
            final List<Object> c = stream.collect((Collector<? super Object, ?, List<Object>>)Collectors.toList());
            this.prevBlocks.addLast(new HashSet<BlockPos>((Collection<? extends BlockPos>)c));
            while (this.prevBlocks.size() > 5) {
                this.prevBlocks.removeFirst();
            }
            if (!c.isEmpty()) {
                this.currentBlock = (BlockPos)c.get(0);
            }
            Wrapper.INSTANCE.mc().playerController.resetBlockRemoving();
            this.progress = 1.0f;
            this.prevProgress = 1.0f;
            BlockUtils.breakBlocksPacketSpam(c);
            return;
        }
        for (final BlockPos currentBlock : list) {
            if (BlockUtils.breakBlockSimple(currentBlock)) {
                this.currentBlock = currentBlock;
                break;
            }
        }
        if (this.currentBlock == null) {
            Wrapper.INSTANCE.mc().playerController.resetBlockRemoving();
        }
        if (this.currentBlock != null && BlockUtils.getHardness(this.currentBlock) < 1.0f) {
            this.prevProgress = this.progress;
        }
        this.progress = PlayerControllerUtils.getCurBlockDamageMP();
        if (this.progress < this.prevProgress) {
            this.prevProgress = this.progress;
        }
        else {
            this.progress = 1.0f;
            this.prevProgress = 1.0f;
        }
        int currentItem = -1;
        for (int j = 0; j < 9; ++j) {
            final ItemStack stackInSlot2 = Minecraft.getMinecraft().player.inventory.getStackInSlot(j);
            if (stackInSlot2 != ItemStack.EMPTY) {
                if (stackInSlot2.getItem() instanceof ItemBlock) {
                    if (this.whiteList.contains(((ItemBlock)stackInSlot2.getItem()).getBlock())) {
                        currentItem = j;
                        break;
                    }
                }
            }
        }
        if (currentItem == -1) {
            return;
        }
        Minecraft.getMinecraft().player.inventory.currentItem = currentItem;
        if (this.currentBlock == null && blockPos != null) {
            BlockUtils.placeBlockScaffold(blockPos, true);
        }
    }
    
    public boolean IsValidBlockPos(final BlockPos blockPos) {
        Minecraft.getMinecraft().world.getBlockState(blockPos);
        return true;
    }
    
    public static double GetDistance(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        final double n7 = n - n4;
        final double n8 = n2 - n5;
        final double n9 = n3 - n6;
        return MathHelper.sqrt(n7 * n7 + n8 * n8 + n9 * n9);
    }
}
