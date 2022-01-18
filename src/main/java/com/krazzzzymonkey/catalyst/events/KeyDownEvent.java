/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class KeyDownEvent extends Event
{
    public int keyId;
    
    public int getKeyId() {
        return this.keyId;
    }
    
    public KeyDownEvent(final int keyId) {
        this.keyId = keyId;
    }
}
