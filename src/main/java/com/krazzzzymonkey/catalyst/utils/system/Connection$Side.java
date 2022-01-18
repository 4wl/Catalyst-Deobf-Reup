/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils.system;

public enum Connection$Side
{
    OUT("OUT", 1), 
    IN("IN", 0);
    
    public static Connection$Side[] $VALUES;
    
    static {
        Connection$Side.$VALUES = new Connection$Side[] { Connection$Side.IN, Connection$Side.OUT };
    }
    
    public Connection$Side(final String name, final int ordinal) {
    }
}
