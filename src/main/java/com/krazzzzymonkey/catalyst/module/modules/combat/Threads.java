/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.math.BlockPos;

public class Threads extends Thread
{
    public BlockPos bestBlock;
    public ThreadType type;
    public EntityEnderCrystal bestCrystal;
    
    public Threads(final ThreadType type) {
        this.type = type;
    }
    
    @Override
    public void run() {
        if (this.type == ThreadType.BLOCK) {
            this.bestBlock = AutoCrystalRewrite.INSTANCE.getBestBlock();
            AutoCrystalRewrite.INSTANCE.staticPos = this.bestBlock;
        }
        else if (this.type == ThreadType.CRYSTAL) {
            this.bestCrystal = AutoCrystalRewrite.INSTANCE.getBestCrystal();
            AutoCrystalRewrite.INSTANCE.staticEnderCrystal = this.bestCrystal;
        }
    }
}
