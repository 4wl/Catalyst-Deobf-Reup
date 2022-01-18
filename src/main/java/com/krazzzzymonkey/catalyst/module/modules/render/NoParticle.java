/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class NoParticle extends Modules
{
    public static BooleanValue criticals;
    public static BooleanValue explosions;
    public static BooleanValue totems;
    public static BooleanValue all;
    
    public NoParticle() {
        super("NoParticle", ModuleCategory.RENDER, "Stops rendering particles");
        NoParticle.explosions = new BooleanValue("Explosions", true);
        NoParticle.totems = new BooleanValue("Totems", false);
        NoParticle.criticals = new BooleanValue("Criticals", false);
        NoParticle.all = new BooleanValue("All", false);
        this.addValue(NoParticle.explosions, NoParticle.totems, NoParticle.criticals, NoParticle.all);
    }
}
