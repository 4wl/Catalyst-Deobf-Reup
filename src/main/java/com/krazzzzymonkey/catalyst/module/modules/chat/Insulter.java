/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.chat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.stream.Stream;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import java.util.Collection;
import net.minecraft.client.network.NetHandlerPlayClient;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import java.util.Random;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Insulter extends Modules
{
    public static String[] insults;
    public IntegerValue settingDelay;
    public int delay;
    public String prefix;
    public BooleanValue greenText;
    
    public Insulter() {
        super("Insulter", ModuleCategory.CHAT, "Insults random players in chat");
        this.delay = 0;
        this.prefix = "";
        this.settingDelay = new IntegerValue("Delay", 50, 1, 500);
        this.greenText = new BooleanValue("GreenText", true);
        this.addValue(this.settingDelay, this.greenText);
    }
    
    public String getRandomStringFromArray(final String[] array) {
        return array[new Random().nextInt(array.length)];
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        final int n = 1 + this.settingDelay.getValue();
        ++this.delay;
        if (this.greenText.getValue()) {
            this.prefix = "> ";
        }
        if (!(boolean)this.greenText.getValue()) {
            this.prefix = "";
        }
        final NetHandlerPlayClient connection = Minecraft.getMinecraft().getConnection();
        if (connection != null) {
            final Collection playerInfoMap = connection.getPlayerInfoMap();
            final GuiPlayerTabOverlay tabList = Minecraft.getMinecraft().ingameGUI.getTabList();
            final Stream<Object> stream = playerInfoMap.stream();
            final GuiPlayerTabOverlay guiPlayerTabOverlay = tabList;
            guiPlayerTabOverlay.getClass();
            final String[] split = stream.map((Function<? super Object, ?>)guiPlayerTabOverlay::func_175243_a).collect((Collector<? super Object, ?, String>)Collectors.joining(", ")).split(",");
            if (this.delay > n) {
                final String replacement = split[new Random().nextInt(split.length)];
                if (replacement.equals(Minecraft.getMinecraft().player.getName())) {
                    return;
                }
                Minecraft.getMinecraft().player.sendChatMessage(this.prefix + this.getRandomStringFromArray(Insulter.insults).replace("{name}", replacement));
                this.delay = 0;
            }
        }
    }
    
    static {
        Insulter.insults = new String[] { "{name} you're a fat virgin.", "{name} you're a retarded newfag.", "{name} you're such a bad pvper.", "Stop being british {name}.", "Why do you eat beans and toast for breakfast {name}?", "You're really fucking ez {name}.", "Imagine being like {name} and not having Catalyst xD", "You pvp like a quadriplegic {name}", "Your totems pop like bubblerap {name}", "What client do you use? rusherhack? {name}", "People like {name} are the reason we have middle fingers." };
    }
}
