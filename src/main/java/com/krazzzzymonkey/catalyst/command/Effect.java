/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.potion.Potion;
import java.util.Iterator;
import net.minecraft.potion.PotionEffect;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;

public class Effect extends Command
{
    @Override
    public String getDescription() {
        return "Effect manager.";
    }
    
    public void clearEffects() {
        final Iterator<PotionEffect> iterator = Wrapper.INSTANCE.player().getActivePotionEffects().iterator();
        while (iterator.hasNext()) {
            Wrapper.INSTANCE.player().removePotionEffect(iterator.next().getPotion());
        }
    }
    
    public void addEffect(final int n, final int n2, final int n3) {
        Wrapper.INSTANCE.player().addPotionEffect(new PotionEffect(Potion.getPotionById(n), n2, n3));
    }
    
    @Override
    public String getSyntax() {
        return "effect <add/remove/clear> <id> <duration> <amplifier>";
    }
    
    public void removeEffect(final int n) {
        Wrapper.INSTANCE.player().removePotionEffect(Potion.getPotionById(n));
    }
    
    public Effect() {
        super("effect");
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        String string;
        try {
            if (array[0].equalsIgnoreCase("add")) {
                final int int1 = Integer.parseInt(array[1]);
                final int int2 = Integer.parseInt(array[2]);
                final int int3 = Integer.parseInt(array[3]);
                if (Potion.getPotionById(int1) == null) {
                    ChatUtils.error("Potion is null");
                    return;
                }
                this.addEffect(int1, int2, int3);
            }
            else if (array[0].equalsIgnoreCase("remove")) {
                final int int4 = Integer.parseInt(array[1]);
                if (Potion.getPotionById(int4) == null) {
                    ChatUtils.error("Potion is null");
                    return;
                }
                this.removeEffect(int4);
            }
            else if (array[0].equalsIgnoreCase("clear")) {
                this.clearEffects();
            }
            return;
        }
        catch (Exception ex) {
            string = "Usage: " + this.getSyntax();
        }
        ChatUtils.error(string);
    }
}
