/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.CatalystRPC;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.module.Modules;

public class RPC extends Modules
{
    public boolean firstRun;
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (!this.firstRun) {
            CatalystRPC.init();
            this.firstRun = true;
        }
    }
    
    @Override
    public void onDisable() {
        this.firstRun = false;
        super.onDisable();
    }
    
    public RPC() {
        super("CatalystRPC", ModuleCategory.MISC, "Discord Rich Presence");
        this.firstRun = false;
    }
}
