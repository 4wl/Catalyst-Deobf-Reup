/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import com.krazzzzymonkey.catalyst.utils.system.Connection$Side;

public class EventsHandler
{
    public boolean onPacket(final Object o, final Connection$Side connection$Side) {
        boolean b = true;
        for (final Modules modules : ModuleManager.getModules()) {
            if (modules.isToggled()) {
                if (Wrapper.INSTANCE.world() == null) {
                    continue;
                }
                b &= modules.onPacket(o, connection$Side);
            }
        }
        return b;
    }
}
