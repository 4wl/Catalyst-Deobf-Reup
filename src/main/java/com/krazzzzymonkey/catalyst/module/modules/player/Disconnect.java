/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.player;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.WorldClient;
import java.util.function.Predicate;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Disconnect extends Modules
{
    public BooleanValue onPlayerVisible;
    public DoubleValue leaveHealth;
    
    public Disconnect() {
        super("AutoDisconnect", ModuleCategory.COMBAT, "Automatically disconnects from server on specific health");
        this.onPlayerVisible = new BooleanValue("On Player Visible", false);
        this.leaveHealth = new DoubleValue("LeaveHealth", 4.0, 0.0, 19.0);
        this.addValue(this.leaveHealth);
    }
    
    public static boolean lambda$onClientTick$1(final Entity entity) {
        return entity instanceof EntityPlayer;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (Wrapper.INSTANCE.world().loadedEntityList.stream().filter(Disconnect::lambda$onClientTick$0).anyMatch(Disconnect::lambda$onClientTick$1) || Wrapper.INSTANCE.player().getHealth() <= this.leaveHealth.getValue().floatValue()) {
            final boolean integratedServerRunning = Wrapper.INSTANCE.mc().isIntegratedServerRunning();
            Wrapper.INSTANCE.world().sendQuittingDisconnectingPacket();
            Wrapper.INSTANCE.mc().loadWorld((WorldClient)null);
            if (integratedServerRunning) {
                Wrapper.INSTANCE.mc().displayGuiScreen((GuiScreen)new GuiMainMenu());
            }
            else {
                Wrapper.INSTANCE.mc().displayGuiScreen((GuiScreen)new GuiMultiplayer((GuiScreen)new GuiMainMenu()));
            }
            this.setToggled(false);
        }
    }
    
    public static boolean lambda$onClientTick$0(final Entity entity) {
        return entity.getName() != Disconnect.mc.player.getName();
    }
}
