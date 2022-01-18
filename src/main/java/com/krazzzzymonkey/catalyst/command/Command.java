/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

public abstract class Command
{
    public String command;
    
    public String getCommand() {
        return this.command;
    }
    
    public Command(final String command) {
        this.command = command;
    }
    
    public abstract String getDescription();
    
    public abstract String getSyntax();
    
    public abstract void runCommand(final String p0, final String[] p1);
}
