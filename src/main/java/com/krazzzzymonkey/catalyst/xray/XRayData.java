/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.xray;

public class XRayData
{
    public int meta;
    public int id;
    public int red;
    public int blue;
    public int green;
    
    public int getBlue() {
        return this.blue;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setGreen(final int green) {
        this.green = green;
    }
    
    public XRayData(final int id, final int meta, final int red, final int green, final int blue) {
        this.id = id;
        this.meta = meta;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public void setRed(final int red) {
        this.red = red;
    }
    
    public void setBlue(final int blue) {
        this.blue = blue;
    }
    
    public int getRed() {
        return this.red;
    }
    
    public int getMeta() {
        return this.meta;
    }
    
    public int getGreen() {
        return this.green;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
}
