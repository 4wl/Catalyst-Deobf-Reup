/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class NoArmor extends Modules
{
    public static BooleanValue head;
    public static BooleanValue legs;
    public static BooleanValue chest;
    public static BooleanValue feet;
    
    public NoArmor() {
        super("NoArmor", ModuleCategory.RENDER, "Wont Render the Armor of other players");
        NoArmor.feet = new BooleanValue("HideBoots", true);
        NoArmor.legs = new BooleanValue("HideLeggings", true);
        NoArmor.chest = new BooleanValue("HideChestplate", true);
        NoArmor.head = new BooleanValue("HideHelmet", true);
        this.addValue(NoArmor.head, NoArmor.chest, NoArmor.legs, NoArmor.feet);
    }
}
