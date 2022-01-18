/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import java.math.BigInteger;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;

public class VClip extends Command
{
    public VClip() {
        super("vclip");
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        String string;
        try {
            Wrapper.INSTANCE.player().setPosition(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY + new BigInteger(array[0]).longValue(), Wrapper.INSTANCE.player().posZ);
            ChatUtils.message("Height teleported to " + new BigInteger(array[0]).longValue());
            return;
        }
        catch (Exception ex) {
            string = "Usage: " + this.getSyntax();
        }
        ChatUtils.error(string);
    }
    
    @Override
    public String getDescription() {
        return "Teleports you up/down.";
    }
    
    @Override
    public String getSyntax() {
        return "vclip <height>";
    }
}
