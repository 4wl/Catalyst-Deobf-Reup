/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import joptsimple.internal.Strings;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import net.minecraft.network.play.server.SPacketTabComplete;
import com.krazzzzymonkey.catalyst.events.PacketEvent;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.play.client.CPacketTabComplete;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class PluginsGetter extends Modules
{
    public ModeValue SortBy;
    
    @Override
    public void onEnable() {
        if (Wrapper.INSTANCE.player() == null) {
            return;
        }
        Wrapper.INSTANCE.sendPacket((Packet)new CPacketTabComplete("/", (BlockPos)null, false));
        super.onEnable();
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        final Packet<?> packet = packetEvent.packet;
        if (packet instanceof SPacketTabComplete) {
            final SPacketTabComplete sPacketTabComplete = (SPacketTabComplete)packet;
            final ArrayList<Object> list = new ArrayList<Object>();
            final String[] matches = sPacketTabComplete.getMatches();
            for (int i = 0; i < matches.length; ++i) {
                final String[] split = matches[i].split(":");
                if (split.length > 1) {
                    final String replace = split[0].replace("/", "");
                    if (!list.contains(replace)) {
                        list.add(replace);
                    }
                }
            }
            if (!list.isEmpty()) {
                if (this.SortBy.getMode("Length").isToggled()) {
                    Collections.sort(list, Comparator.comparingInt(String::length));
                }
                else {
                    Collections.sort(list, (Comparator<? super Object>)String.CASE_INSENSITIVE_ORDER);
                }
                ChatUtils.message("Plugins §7(§8" + list.size() + "§7): §9" + Strings.join((String[])list.toArray(new String[0]), "§7, §9"));
            }
            else {
                ChatUtils.error("No plugins found.");
            }
            this.setToggled(false);
        }
    }
    
    public PluginsGetter() {
        super("PluginsGetter", ModuleCategory.MISC, "Gets server plugins");
        this.SortBy = new ModeValue("Sort by", new Mode[] { new Mode("Length", false), new Mode("AlphaNumerical", true) });
        this.addValue(this.SortBy);
    }
}
