/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import net.minecraft.block.Block;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;
import java.util.List;
import com.krazzzzymonkey.catalyst.utils.Utils;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.network.Packet;
import com.krazzzzymonkey.catalyst.module.modules.player.AutoTool;
import net.minecraft.network.play.client.CPacketPlayerDigging$Action;
import net.minecraft.world.GameType;
import net.minecraft.block.BlockHopper;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.utils.PlayerControllerUtils;
import com.krazzzzymonkey.catalyst.utils.BlockUtils;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import java.util.Set;
import java.util.ArrayDeque;
import com.krazzzzymonkey.catalyst.module.Modules;

public class HopperNuker extends Modules
{
    public ArrayDeque<Set<BlockPos>> prevBlocks;
    public float prevProgress;
    public BlockPos currentBlock;
    public float progress;
    public int id;
    public int currentSlot;
    public int slot;
    
    public static double lambda$onClientTick$2(final Vec3d vec3d, final BlockPos blockPos) {
        return vec3d.squareDistanceTo(new Vec3d((Vec3i)blockPos));
    }
    
    public static boolean lambda$onClientTick$1(final BlockPos blockPos) {
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
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        if (this.currentBlock == null) {
            return;
        }
        if (this.currentSlot != -1 && this.currentBlock == null) {
            Minecraft.getMinecraft().player.inventory.currentItem = this.currentSlot;
            this.currentSlot = -1;
        }
        RenderUtils.drawBlockESP(this.currentBlock, 1.0f, 0.0f, 0.0f, 1.0);
    }
    
    public static boolean lambda$onClientTick$4(final Set set, final BlockPos blockPos) {
        return !set.contains(blockPos);
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        final Packet<?> packet = packetEvent$Out.packet;
        if (packet instanceof CPacketPlayerDigging) {
            final CPacketPlayerDigging cPacketPlayerDigging = (CPacketPlayerDigging)packet;
            if (Minecraft.getMinecraft().world.getBlockState(cPacketPlayerDigging.getPosition()).getBlock() instanceof BlockHopper && Minecraft.getMinecraft().playerController.getCurrentGameType() != GameType.CREATIVE) {
                if (cPacketPlayerDigging.getAction() == CPacketPlayerDigging$Action.START_DESTROY_BLOCK) {
                    if (this.currentSlot == -1) {
                        this.currentSlot = Minecraft.getMinecraft().player.inventory.currentItem;
                    }
                    final int tool = AutoTool.findTool(Minecraft.getMinecraft().world.getBlockState(cPacketPlayerDigging.getPosition()).getBlock());
                    if (tool != -1) {
                        if (this.slot == -1) {
                            this.slot = Minecraft.getMinecraft().player.inventory.currentItem;
                        }
                        Minecraft.getMinecraft().player.inventory.currentItem = tool;
                    }
                }
            }
        }
    }
    
    public static boolean lambda$onClientTick$0(final Vec3d vec3d, final double n, final BlockPos blockPos) {
        return vec3d.squareDistanceTo(new Vec3d((Vec3i)blockPos)) <= n;
    }
    
    public HopperNuker() {
        super("HopperNuker", ModuleCategory.COMBAT, "Automatically breaks hoppers within players reach");
        this.prevBlocks = new ArrayDeque<Set<BlockPos>>();
        this.slot = -1;
        this.currentSlot = -1;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        this.currentBlock = null;
        final Vec3d subtract = Utils.getEyesPos().subtract(0.5, 0.5, 0.5);
        final BlockPos blockPos = new BlockPos(Utils.getEyesPos());
        final double pow = Math.pow(6.0, 2.0);
        final int n = (int)Math.ceil(6.0);
        final List<Object> list = StreamSupport.stream(BlockPos.getAllInBox(blockPos.add(n, n, n), blockPos.add(-n, -n, -n)).spliterator(), true).filter(HopperNuker::lambda$onClientTick$0).filter((Predicate<? super Object>)HopperNuker::lambda$onClientTick$1).sorted(Comparator.comparingDouble(HopperNuker::lambda$onClientTick$2)).filter((Predicate<? super Object>)HopperNuker::lambda$onClientTick$3).collect((Collector<? super Object, ?, List<Object>>)Collectors.toList());
        if (Wrapper.INSTANCE.player().capabilities.isCreativeMode) {
            Stream<Object> stream = list.parallelStream();
            final Iterator<Set<BlockPos>> iterator = this.prevBlocks.iterator();
            while (iterator.hasNext()) {
                stream = stream.filter((Predicate<? super Object>)HopperNuker::lambda$onClientTick$4);
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
        while (true) {
            for (final BlockPos currentBlock : list) {
                if (BlockUtils.breakBlockSimple(currentBlock)) {
                    this.currentBlock = currentBlock;
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
                    return;
                }
            }
            continue;
        }
    }
    
    public static boolean lambda$onClientTick$3(final BlockPos blockPos) {
        return Block.getIdFromBlock(Wrapper.INSTANCE.world().getBlockState(blockPos).getBlock()) == Block.getIdFromBlock(Block.getBlockById(154));
    }
}
