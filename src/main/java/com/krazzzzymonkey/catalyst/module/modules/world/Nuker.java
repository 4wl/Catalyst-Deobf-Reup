/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.world;

import net.minecraftforge.event.entity.player.PlayerInteractEvent$LeftClickBlock;
import net.minecraft.block.Block;
import net.minecraft.util.math.Vec3i;
import java.util.Iterator;
import java.util.stream.Stream;
import net.minecraft.util.math.Vec3d;
import com.krazzzzymonkey.catalyst.utils.PlayerControllerUtils;
import java.util.Collection;
import java.util.HashSet;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;
import com.krazzzzymonkey.catalyst.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.krazzzzymonkey.catalyst.utils.BlockUtils;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import java.util.Set;
import java.util.ArrayDeque;
import net.minecraft.util.math.BlockPos;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Nuker extends Modules
{
    public ModeValue mode;
    public float prevProgress;
    public BlockPos currentBlock;
    public float progress;
    public ArrayDeque<Set<BlockPos>> prevBlocks;
    public DoubleValue distance;
    public int id;
    
    public Nuker() {
        super("Nuker", ModuleCategory.WORLD, "Automatically breaks specified blocks in players reach");
        this.prevBlocks = new ArrayDeque<Set<BlockPos>>();
        this.mode = new ModeValue("Mode", new Mode[] { new Mode("ID", true), new Mode("All", false) });
        this.distance = new DoubleValue("Distance", 6.0, 0.1, 6.0);
        this.addValue(this.mode, this.distance);
    }
    
    public static boolean lambda$onClientTick$1(final BlockPos blockPos) {
        return BlockUtils.canBeClicked(blockPos);
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        if (this.currentBlock == null) {
            return;
        }
        if (this.mode.getMode("All").isToggled()) {
            RenderUtils.drawBlockESP(this.currentBlock, 1.0f, 0.0f, 0.0f, 1.0);
        }
        else if (this.mode.getMode("ID").isToggled()) {
            RenderUtils.drawBlockESP(this.currentBlock, 0.0f, 0.0f, 1.0f, 1.0);
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null) {
                if (Minecraft.getMinecraft().player.getUniqueID() != null) {
                    this.currentBlock = null;
                    final Vec3d subtract = Utils.getEyesPos().subtract(0.5, 0.5, 0.5);
                    final BlockPos blockPos = new BlockPos(Utils.getEyesPos());
                    final double pow = Math.pow(this.distance.getValue(), 2.0);
                    final int n = (int)Math.ceil(this.distance.getValue());
                    Stream<Object> stream = StreamSupport.stream(BlockPos.getAllInBox(blockPos.add(n, n, n), blockPos.add(-n, -n, -n)).spliterator(), true).filter(Nuker::lambda$onClientTick$0).filter((Predicate<? super Object>)Nuker::lambda$onClientTick$1).sorted(Comparator.comparingDouble(Nuker::lambda$onClientTick$2));
                    if (this.mode.getMode("ID").isToggled()) {
                        stream = stream.filter((Predicate<? super Object>)this::lambda$onClientTick$3);
                    }
                    else if (this.mode.getMode("All").isToggled()) {}
                    final List<Object> list = stream.collect((Collector<? super Object, ?, List<Object>>)Collectors.toList());
                    if (Wrapper.INSTANCE.player().capabilities.isCreativeMode) {
                        Stream<Object> stream2 = list.parallelStream();
                        final Iterator<Set<BlockPos>> iterator = this.prevBlocks.iterator();
                        while (iterator.hasNext()) {
                            stream2 = stream2.filter((Predicate<? super Object>)Nuker::lambda$onClientTick$4);
                        }
                        final List<Object> c = stream2.collect((Collector<? super Object, ?, List<Object>>)Collectors.toList());
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
                }
            }
        }
    }
    
    public static double lambda$onClientTick$2(final Vec3d vec3d, final BlockPos blockPos) {
        return vec3d.squareDistanceTo(new Vec3d((Vec3i)blockPos));
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
    
    public boolean lambda$onClientTick$3(final BlockPos blockPos) {
        return Block.getIdFromBlock(Wrapper.INSTANCE.world().getBlockState(blockPos).getBlock()) == this.id;
    }
    
    @SubscribeEvent
    public void onLeftClickBlock(final PlayerInteractEvent$LeftClickBlock playerInteractEvent$LeftClickBlock) {
        if (this.mode.getMode("ID").isToggled() && Wrapper.INSTANCE.world().isRemote) {
            this.id = Block.getIdFromBlock(Wrapper.INSTANCE.world().getBlockState(playerInteractEvent$LeftClickBlock.getPos()).getBlock());
        }
    }
    
    public static boolean lambda$onClientTick$0(final Vec3d vec3d, final double n, final BlockPos blockPos) {
        return vec3d.squareDistanceTo(new Vec3d((Vec3i)blockPos)) <= n;
    }
    
    public static boolean lambda$onClientTick$4(final Set set, final BlockPos blockPos) {
        return !set.contains(blockPos);
    }
}
