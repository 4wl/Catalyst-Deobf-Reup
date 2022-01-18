/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.events.PacketEvent;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class PacketCanceller extends Modules
{
    public BooleanValue vehicleMove;
    public BooleanValue input;
    public BooleanValue useEntity;
    public BooleanValue positionRotation;
    public BooleanValue animation;
    public BooleanValue position;
    public BooleanValue rotation;
    public BooleanValue entityAction;
    
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        final String simpleName = packetEvent.packet.getClass().getSimpleName();
        switch (simpleName) {
            case "CPacketAnimation": {
                if (this.animation.getValue()) {
                    packetEvent.setCanceled(true);
                    break;
                }
                break;
            }
            case "CPacketInput": {
                if (this.input.getValue()) {
                    packetEvent.setCanceled(true);
                    break;
                }
                break;
            }
            case "CPacketEntityAction": {
                if (this.entityAction.getValue()) {
                    packetEvent.setCanceled(true);
                    break;
                }
                break;
            }
            case "Position": {
                if (this.position.getValue()) {
                    packetEvent.setCanceled(true);
                    break;
                }
                break;
            }
            case "Rotation": {
                if (this.rotation.getValue()) {
                    packetEvent.setCanceled(true);
                    break;
                }
                break;
            }
            case "PositionRotation": {
                if (this.positionRotation.getValue()) {
                    packetEvent.setCanceled(true);
                    break;
                }
                break;
            }
            case "CPacketUseEntity": {
                if (this.useEntity.getValue()) {
                    packetEvent.setCanceled(true);
                    break;
                }
                break;
            }
            case "CPacketVehicleMove": {
                if (this.vehicleMove.getValue()) {
                    packetEvent.setCanceled(true);
                    break;
                }
                break;
            }
        }
    }
    
    public PacketCanceller() {
        super("PacketCanceller", ModuleCategory.MISC, "Allows you to cancel certain packets from being sent to the server");
        this.addValue(this.animation = new BooleanValue("Animation", false), this.input = new BooleanValue("Input", false), this.entityAction = new BooleanValue("EntityAction", false), this.position = new BooleanValue("Position", false), this.rotation = new BooleanValue("Rotation", false), this.positionRotation = new BooleanValue("PositionRotation", false), this.useEntity = new BooleanValue("UseEntity", false), this.vehicleMove = new BooleanValue("VechileMove", false));
    }
}
