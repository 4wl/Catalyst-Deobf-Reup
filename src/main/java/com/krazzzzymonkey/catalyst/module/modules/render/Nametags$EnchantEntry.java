/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraft.enchantment.Enchantment;

public class Nametags$EnchantEntry
{
    public Enchantment enchant;
    public String name;
    
    public String getName() {
        return this.name;
    }
    
    public Nametags$EnchantEntry(final Enchantment enchant, final String name) {
        this.enchant = enchant;
        this.name = name;
    }
    
    public Enchantment getEnchant() {
        return this.enchant;
    }
}
