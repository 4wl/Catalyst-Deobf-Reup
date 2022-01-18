/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.event.world.WorldEvent$Unload;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent$Load;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.module.Modules;

public class NetherSky extends Modules
{
    public static Minecraft mc;
    public static NetherSky$ISpaceRenderer skyboxSpaceRenderer;
    public ModeValue mode;
    public boolean wasChanged;
    
    public void enableBackgroundRenderer(final World world) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: nop            
        //     4: nop            
        //     5: nop            
        //     6: athrow         
        //     7: athrow         
        //     8: aload_1        
        //     9: getfield        net/minecraft/world/World.provider:Lnet/minecraft/world/WorldProvider;
        //    12: goto            16
        //    15: athrow         
        //    16: invokevirtual   net/minecraft/world/WorldProvider.getDimensionType:()Lnet/minecraft/world/DimensionType;
        //    19: goto            23
        //    22: athrow         
        //    23: getstatic       net/minecraft/world/DimensionType.NETHER:Lnet/minecraft/world/DimensionType;
        //    26: if_acmpne       66
        //    29: aload_1        
        //    30: goto            33
        //    33: goto            36
        //    36: getfield        net/minecraft/world/World.provider:Lnet/minecraft/world/WorldProvider;
        //    39: new             Lcom/krazzzzymonkey/catalyst/module/modules/render/NetherSky$1;
        //    42: dup            
        //    43: aload_0        
        //    44: goto            48
        //    47: athrow         
        //    48: invokespecial   com/krazzzzymonkey/catalyst/module/modules/render/NetherSky$1.<init>:(Lcom/krazzzzymonkey/catalyst/module/modules/render/NetherSky;)V
        //    51: goto            55
        //    54: athrow         
        //    55: goto            59
        //    58: athrow         
        //    59: invokevirtual   net/minecraft/world/WorldProvider.setSkyRenderer:(Lnet/minecraftforge/client/IRenderHandler;)V
        //    62: goto            66
        //    65: athrow         
        //    66: return         
        //    67: nop            
        //    68: athrow         
        //    69: nop            
        //    70: athrow         
        //    StackMapTable: 00 13 FF 00 03 00 00 00 01 07 00 10 43 07 00 10 FD 00 00 07 00 02 07 00 12 FF 00 06 00 00 00 01 07 00 10 FF 00 00 00 02 07 00 02 07 00 12 00 01 07 00 18 FF 00 05 00 00 00 01 07 00 10 FF 00 00 00 02 07 00 02 07 00 12 00 01 07 00 1E 49 07 00 12 42 07 00 12 FF 00 0A 00 00 00 01 07 00 10 FF 00 00 00 02 07 00 02 07 00 12 00 04 07 00 18 08 00 27 08 00 27 07 00 02 FF 00 05 00 00 00 01 07 00 10 FF 00 00 00 02 07 00 02 07 00 12 00 02 07 00 18 07 00 24 FF 00 02 00 00 00 01 07 00 10 FF 00 00 00 02 07 00 02 07 00 12 00 02 07 00 18 07 00 24 FF 00 05 00 00 00 01 07 00 10 FD 00 00 07 00 02 07 00 12 FF 00 00 00 00 00 01 07 00 10 41 07 00 10
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:440)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent$Load worldEvent$Load) {
        this.wasChanged = false;
    }
    
    @SubscribeEvent
    public void onWorldUnload(final WorldEvent$Unload worldEvent$Unload) {
        this.wasChanged = false;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (this.wasChanged) {
            return;
        }
        this.enableBackgroundRenderer(NetherSky.mc.player.world);
        this.wasChanged = true;
    }
    
    @Override
    public void onDisable() {
        this.disableBackgroundRenderer(NetherSky.mc.player.world);
        super.onDisable();
    }
    
    public NetherSky() {
        super("NetherSky", ModuleCategory.RENDER, "Nether Sky");
        this.mode = new ModeValue("Mode", new Mode[] { new Mode("Dickbutt", true), new Mode("Cow", false), new Mode("Grin", false), new Mode("0b0t", false), new Mode("Arthur", false), new Mode("Impact", false) });
        this.addValue(this.mode);
        NetherSky.skyboxSpaceRenderer = new NetherSky$SkyboxSpaceRenderer(NetherSky.mc);
    }
    
    public void disableBackgroundRenderer(final World world) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: nop            
        //     4: nop            
        //     5: nop            
        //     6: athrow         
        //     7: athrow         
        //     8: aload_1        
        //     9: getfield        net/minecraft/world/World.provider:Lnet/minecraft/world/WorldProvider;
        //    12: goto            16
        //    15: athrow         
        //    16: invokevirtual   net/minecraft/world/WorldProvider.getDimensionType:()Lnet/minecraft/world/DimensionType;
        //    19: goto            23
        //    22: athrow         
        //    23: getstatic       net/minecraft/world/DimensionType.NETHER:Lnet/minecraft/world/DimensionType;
        //    26: if_acmpne       32
        //    29: goto            60
        //    32: goto            97
        //    35: nop            
        //    36: nop            
        //    37: nop            
        //    38: nop            
        //    39: nop            
        //    40: nop            
        //    41: nop            
        //    42: nop            
        //    43: nop            
        //    44: nop            
        //    45: nop            
        //    46: nop            
        //    47: nop            
        //    48: nop            
        //    49: nop            
        //    50: nop            
        //    51: nop            
        //    52: nop            
        //    53: nop            
        //    54: nop            
        //    55: nop            
        //    56: nop            
        //    57: nop            
        //    58: nop            
        //    59: athrow         
        //    60: goto            63
        //    63: goto            66
        //    66: aload_1        
        //    67: getfield        net/minecraft/world/World.provider:Lnet/minecraft/world/WorldProvider;
        //    70: new             Lcom/krazzzzymonkey/catalyst/module/modules/render/NetherSky$2;
        //    73: dup            
        //    74: aload_0        
        //    75: goto            79
        //    78: athrow         
        //    79: invokespecial   com/krazzzzymonkey/catalyst/module/modules/render/NetherSky$2.<init>:(Lcom/krazzzzymonkey/catalyst/module/modules/render/NetherSky;)V
        //    82: goto            86
        //    85: athrow         
        //    86: goto            90
        //    89: athrow         
        //    90: invokevirtual   net/minecraft/world/WorldProvider.setSkyRenderer:(Lnet/minecraftforge/client/IRenderHandler;)V
        //    93: goto            97
        //    96: athrow         
        //    97: return         
        //    98: nop            
        //    99: athrow         
        //   100: nop            
        //   101: athrow         
        //    StackMapTable: 00 17 FF 00 03 00 00 00 01 07 00 10 43 07 00 10 FD 00 00 07 00 02 07 00 12 FF 00 06 00 00 00 01 07 00 10 FF 00 00 00 02 07 00 02 07 00 12 00 01 07 00 18 FF 00 05 00 00 00 01 07 00 10 FF 00 00 00 02 07 00 02 07 00 12 00 01 07 00 1E 05 02 FF 00 02 00 00 00 01 07 00 10 FD 00 18 07 00 02 07 00 12 02 02 FF 00 0B 00 00 00 01 07 00 10 FF 00 00 00 02 07 00 02 07 00 12 00 04 07 00 18 08 00 46 08 00 46 07 00 02 FF 00 05 00 00 00 01 07 00 10 FF 00 00 00 02 07 00 02 07 00 12 00 02 07 00 18 07 00 95 FF 00 02 00 00 00 01 07 00 10 FF 00 00 00 02 07 00 02 07 00 12 00 02 07 00 18 07 00 95 FF 00 05 00 00 00 01 07 00 10 FD 00 00 07 00 02 07 00 12 FF 00 00 00 00 00 01 07 00 10 41 07 00 10
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:440)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static NetherSky$ISpaceRenderer access$100() {
        return NetherSky.skyboxSpaceRenderer;
    }
    
    static {
        NetherSky.mc = Minecraft.getMinecraft();
    }
    
    @Override
    public void onEnable() {
        this.wasChanged = false;
        super.onEnable();
    }
    
    public static ModeValue access$000(final NetherSky netherSky) {
        return netherSky.mode;
    }
}
