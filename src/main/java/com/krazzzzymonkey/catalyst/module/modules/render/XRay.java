/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.block.Block;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.module.Modules;

public class XRay extends Modules
{
    public static ArrayList<Block> blocks;
    
    @Override
    public void onEnable() {
        super.onEnable();
        XRay.mc.renderGlobal.loadRenderers();
    }
    
    public static boolean isInList(final Block o) {
        return XRay.blocks.contains(o);
    }
    
    public XRay() {
        super("XRay", ModuleCategory.RENDER, "Allows you to see ores and blocks underground");
        XRay.blocks.add(Block.getBlockFromName("coal_ore"));
        XRay.blocks.add(Block.getBlockFromName("iron_ore"));
        XRay.blocks.add(Block.getBlockFromName("gold_ore"));
        XRay.blocks.add(Block.getBlockFromName("redstone_ore"));
        XRay.blocks.add(Block.getBlockById(74));
        XRay.blocks.add(Block.getBlockFromName("lapis_ore"));
        XRay.blocks.add(Block.getBlockFromName("diamond_ore"));
        XRay.blocks.add(Block.getBlockFromName("emerald_ore"));
        XRay.blocks.add(Block.getBlockFromName("quartz_ore"));
        XRay.blocks.add(Block.getBlockFromName("clay"));
        XRay.blocks.add(Block.getBlockFromName("glowstone"));
        XRay.blocks.add(Block.getBlockById(8));
        XRay.blocks.add(Block.getBlockById(9));
        XRay.blocks.add(Block.getBlockById(10));
        XRay.blocks.add(Block.getBlockById(11));
        XRay.blocks.add(Block.getBlockFromName("crafting_table"));
        XRay.blocks.add(Block.getBlockById(61));
        XRay.blocks.add(Block.getBlockById(62));
        XRay.blocks.add(Block.getBlockFromName("torch"));
        XRay.blocks.add(Block.getBlockFromName("ladder"));
        XRay.blocks.add(Block.getBlockFromName("tnt"));
        XRay.blocks.add(Block.getBlockFromName("coal_block"));
        XRay.blocks.add(Block.getBlockFromName("iron_block"));
        XRay.blocks.add(Block.getBlockFromName("gold_block"));
        XRay.blocks.add(Block.getBlockFromName("diamond_block"));
        XRay.blocks.add(Block.getBlockFromName("emerald_block"));
        XRay.blocks.add(Block.getBlockFromName("redstone_block"));
        XRay.blocks.add(Block.getBlockFromName("lapis_block"));
        XRay.blocks.add(Block.getBlockFromName("fire"));
        XRay.blocks.add(Block.getBlockFromName("mossy_cobblestone"));
        XRay.blocks.add(Block.getBlockFromName("mob_spawner"));
        XRay.blocks.add(Block.getBlockFromName("end_portal_frame"));
        XRay.blocks.add(Block.getBlockFromName("enchanting_table"));
        XRay.blocks.add(Block.getBlockFromName("bookshelf"));
        XRay.blocks.add(Block.getBlockFromName("command_block"));
        XRay.blocks.add(Block.getBlockFromName("bone_block"));
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        XRay.mc.renderGlobal.loadRenderers();
    }
    
    static {
        XRay.blocks = new ArrayList<Block>();
    }
}
