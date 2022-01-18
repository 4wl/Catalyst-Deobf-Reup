/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

public class TimerUtils
{
    public long prevMS;
    public long lastMS;
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public int convertToMS(final int n) {
        return 1000 / n;
    }
    
    public void setLastMS(final long lastMS) {
        this.lastMS = lastMS;
    }
    
    public TimerUtils() {
        this.lastMS = 0L;
        this.prevMS = 0L;
    }
    
    public void setLastMS() {
        this.lastMS = System.currentTimeMillis();
    }
    
    public boolean hasReached(final float n) {
        return this.getCurrentMS() - this.lastMS >= n;
    }
    
    public boolean isDelay(final long n) {
        return System.currentTimeMillis() - this.lastMS >= n;
    }
    
    public boolean delay(final float n) {
        return this.getTime() - this.prevMS >= n;
    }
    
    public void reset() {
        this.lastMS = this.getCurrentMS();
    }
    
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
}
