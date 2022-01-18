/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import java.util.LinkedList;
import java.util.Queue;

public class CrystalClickCounter
{
    public Queue<Long> crystals;
    
    public CrystalClickCounter() {
        this.crystals = new LinkedList<Long>();
    }
    
    public int getCps() {
        final long currentTimeMillis = System.currentTimeMillis();
        try {
            while (!this.crystals.isEmpty() && this.crystals.peek() < currentTimeMillis) {
                this.crystals.remove();
            }
        }
        catch (Exception ex) {}
        return this.crystals.size();
    }
    
    public void onBreak() {
        this.crystals.add(System.currentTimeMillis() + 1000L);
    }
}
