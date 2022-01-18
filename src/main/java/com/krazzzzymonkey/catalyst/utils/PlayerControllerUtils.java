/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.client.Minecraft;
import java.lang.reflect.Field;
import com.krazzzzymonkey.catalyst.utils.system.Mapping;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.entity.Entity;

public class PlayerControllerUtils
{
    public static void setReach(final Entity entity, final double n) {
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
        //     8: getstatic       com/krazzzzymonkey/catalyst/utils/system/Wrapper.INSTANCE:Lcom/krazzzzymonkey/catalyst/utils/system/Wrapper;
        //    11: goto            15
        //    14: athrow         
        //    15: invokevirtual   com/krazzzzymonkey/catalyst/utils/system/Wrapper.mc:()Lnet/minecraft/client/Minecraft;
        //    18: goto            22
        //    21: athrow         
        //    22: goto            25
        //    25: goto            28
        //    28: astore_3       
        //    29: getstatic       com/krazzzzymonkey/catalyst/utils/system/Wrapper.INSTANCE:Lcom/krazzzzymonkey/catalyst/utils/system/Wrapper;
        //    32: goto            36
        //    35: athrow         
        //    36: invokevirtual   com/krazzzzymonkey/catalyst/utils/system/Wrapper.player:()Lnet/minecraft/client/entity/EntityPlayerSP;
        //    39: goto            43
        //    42: athrow         
        //    43: astore          4
        //    45: aload           4
        //    47: goto            50
        //    50: goto            53
        //    53: aload_0        
        //    54: if_acmpne       256
        //    57: aload_3        
        //    58: goto            61
        //    61: goto            64
        //    64: getfield        net/minecraft/client/Minecraft.playerController:Lnet/minecraft/client/multiplayer/PlayerControllerMP;
        //    67: instanceof      Lcom/krazzzzymonkey/catalyst/utils/PlayerControllerUtils$1RangePlayerController;
        //    70: ifne            236
        //    73: ldc             Lnet/minecraft/client/multiplayer/PlayerControllerMP;.class
        //    75: aload_3        
        //    76: getfield        net/minecraft/client/Minecraft.playerController:Lnet/minecraft/client/multiplayer/PlayerControllerMP;
        //    79: iconst_1       
        //    80: anewarray       Ljava/lang/String;
        //    83: dup            
        //    84: iconst_0       
        //    85: getstatic       com/krazzzzymonkey/catalyst/utils/system/Mapping.currentGameType:Ljava/lang/String;
        //    88: aastore        
        //    89: goto            93
        //    92: athrow         
        //    93: invokestatic    net/minecraftforge/fml/relauncher/ReflectionHelper.getPrivateValue:(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/String;)Ljava/lang/Object;
        //    96: goto            100
        //    99: athrow         
        //   100: checkcast       Lnet/minecraft/world/GameType;
        //   103: goto            106
        //   106: goto            109
        //   109: astore          5
        //   111: ldc             Lnet/minecraft/client/multiplayer/PlayerControllerMP;.class
        //   113: aload_3        
        //   114: getfield        net/minecraft/client/Minecraft.playerController:Lnet/minecraft/client/multiplayer/PlayerControllerMP;
        //   117: iconst_1       
        //   118: anewarray       Ljava/lang/String;
        //   121: dup            
        //   122: iconst_0       
        //   123: getstatic       com/krazzzzymonkey/catalyst/utils/system/Mapping.connection:Ljava/lang/String;
        //   126: aastore        
        //   127: goto            131
        //   130: athrow         
        //   131: invokestatic    net/minecraftforge/fml/relauncher/ReflectionHelper.getPrivateValue:(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/String;)Ljava/lang/Object;
        //   134: goto            138
        //   137: athrow         
        //   138: checkcast       Lnet/minecraft/client/network/NetHandlerPlayClient;
        //   141: astore          6
        //   143: new             Lcom/krazzzzymonkey/catalyst/utils/PlayerControllerUtils$1RangePlayerController;
        //   146: dup            
        //   147: aload_3        
        //   148: goto            151
        //   151: goto            154
        //   154: aload           6
        //   156: goto            160
        //   159: athrow         
        //   160: invokespecial   com/krazzzzymonkey/catalyst/utils/PlayerControllerUtils$1RangePlayerController.<init>:(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/network/NetHandlerPlayClient;)V
        //   163: goto            167
        //   166: athrow         
        //   167: astore          7
        //   169: aload           4
        //   171: getfield        net/minecraft/entity/player/EntityPlayer.capabilities:Lnet/minecraft/entity/player/PlayerCapabilities;
        //   174: getfield        net/minecraft/entity/player/PlayerCapabilities.isFlying:Z
        //   177: istore          8
        //   179: aload           4
        //   181: getfield        net/minecraft/entity/player/EntityPlayer.capabilities:Lnet/minecraft/entity/player/PlayerCapabilities;
        //   184: getfield        net/minecraft/entity/player/PlayerCapabilities.allowFlying:Z
        //   187: istore          9
        //   189: aload           7
        //   191: aload           5
        //   193: goto            197
        //   196: athrow         
        //   197: invokevirtual   com/krazzzzymonkey/catalyst/utils/PlayerControllerUtils$1RangePlayerController.setGameType:(Lnet/minecraft/world/GameType;)V
        //   200: goto            204
        //   203: athrow         
        //   204: aload           4
        //   206: getfield        net/minecraft/entity/player/EntityPlayer.capabilities:Lnet/minecraft/entity/player/PlayerCapabilities;
        //   209: iload           8
        //   211: putfield        net/minecraft/entity/player/PlayerCapabilities.isFlying:Z
        //   214: aload           4
        //   216: goto            219
        //   219: goto            222
        //   222: getfield        net/minecraft/entity/player/EntityPlayer.capabilities:Lnet/minecraft/entity/player/PlayerCapabilities;
        //   225: iload           9
        //   227: putfield        net/minecraft/entity/player/PlayerCapabilities.allowFlying:Z
        //   230: aload_3        
        //   231: aload           7
        //   233: putfield        net/minecraft/client/Minecraft.playerController:Lnet/minecraft/client/multiplayer/PlayerControllerMP;
        //   236: aload_3        
        //   237: getfield        net/minecraft/client/Minecraft.playerController:Lnet/minecraft/client/multiplayer/PlayerControllerMP;
        //   240: checkcast       Lcom/krazzzzymonkey/catalyst/utils/PlayerControllerUtils$1RangePlayerController;
        //   243: dload_1        
        //   244: d2f            
        //   245: goto            249
        //   248: athrow         
        //   249: invokevirtual   com/krazzzzymonkey/catalyst/utils/PlayerControllerUtils$1RangePlayerController.setBlockReachDistance:(F)V
        //   252: goto            256
        //   255: athrow         
        //   256: return         
        //   257: nop            
        //   258: athrow         
        //   259: nop            
        //   260: athrow         
        //   261: nop            
        //   262: athrow         
        //   263: nop            
        //   264: athrow         
        //   265: nop            
        //   266: athrow         
        //   267: nop            
        //   268: athrow         
        //   269: nop            
        //   270: athrow         
        //    StackMapTable: 00 33 FF 00 03 00 00 00 01 07 00 08 43 07 00 08 FD 00 00 07 00 0A 03 FF 00 05 00 00 00 01 07 00 08 FF 00 00 00 02 07 00 0A 03 00 01 07 00 0C FF 00 05 00 00 00 01 07 00 08 FF 00 00 00 02 07 00 0A 03 00 01 07 00 16 42 07 00 16 42 07 00 16 FF 00 06 00 00 00 01 07 00 08 FF 00 00 00 03 07 00 0A 03 07 00 16 00 01 07 00 0C FF 00 05 00 00 00 01 07 00 08 FF 00 00 00 03 07 00 0A 03 07 00 16 00 01 07 00 1C FF 00 06 00 04 07 00 0A 03 07 00 16 07 00 1C 00 01 07 00 1C 42 07 00 1C 47 07 00 16 42 07 00 16 FF 00 1B 00 00 00 01 07 00 08 FF 00 00 00 04 07 00 0A 03 07 00 16 07 00 1C 00 03 07 00 2E 07 00 24 07 00 30 FF 00 05 00 00 00 01 07 00 08 FF 00 00 00 04 07 00 0A 03 07 00 16 07 00 1C 00 01 07 00 04 45 07 00 38 42 07 00 38 FF 00 14 00 00 00 01 07 00 08 FF 00 00 00 05 07 00 0A 03 07 00 16 07 00 1C 07 00 38 00 03 07 00 2E 07 00 24 07 00 30 FF 00 05 00 00 00 01 07 00 08 FF 00 00 00 05 07 00 0A 03 07 00 16 07 00 1C 07 00 38 00 01 07 00 04 FF 00 0C 00 06 07 00 0A 03 07 00 16 07 00 1C 07 00 38 07 00 3D 00 03 08 00 8F 08 00 8F 07 00 16 FF 00 02 00 06 07 00 0A 03 07 00 16 07 00 1C 07 00 38 07 00 3D 00 03 08 00 8F 08 00 8F 07 00 16 FF 00 04 00 00 00 01 07 00 08 FF 00 00 00 06 07 00 0A 03 07 00 16 07 00 1C 07 00 38 07 00 3D 00 04 08 00 8F 08 00 8F 07 00 16 07 00 3D FF 00 05 00 00 00 01 07 00 08 FF 00 00 00 06 07 00 0A 03 07 00 16 07 00 1C 07 00 38 07 00 3D 00 01 07 00 22 FF 00 1C 00 00 00 01 07 00 08 FF 00 00 00 09 07 00 0A 03 07 00 16 07 00 1C 07 00 38 07 00 3D 07 00 22 01 01 00 02 07 00 22 07 00 38 FF 00 05 00 00 00 01 07 00 08 FF 00 00 00 09 07 00 0A 03 07 00 16 07 00 1C 07 00 38 07 00 3D 07 00 22 01 01 00 00 4E 07 00 1C 42 07 00 1C FF 00 0D 00 04 07 00 0A 03 07 00 16 07 00 1C 00 00 FF 00 0B 00 00 00 01 07 00 08 FF 00 00 00 04 07 00 0A 03 07 00 16 07 00 1C 00 02 07 00 22 02 FF 00 05 00 00 00 01 07 00 08 FF 00 00 00 04 07 00 0A 03 07 00 16 07 00 1C 00 00 FF 00 00 00 00 00 01 07 00 08 41 07 00 08 41 07 00 08 41 07 00 08 41 07 00 08 41 07 00 08 41 07 00 08
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:169)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:547)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:439)
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
    
    public static void setBlockHitDelay(final int i) {
        try {
            final Field declaredField = PlayerControllerMP.class.getDeclaredField(Mapping.blockHitDelay);
            declaredField.setAccessible(true);
            declaredField.setInt(Wrapper.INSTANCE.mc().playerController, i);
        }
        catch (Exception ex) {}
    }
    
    public static void setIsHittingBlock(final boolean z) {
        try {
            final Field declaredField = PlayerControllerMP.class.getDeclaredField(Mapping.isHittingBlock);
            declaredField.setAccessible(true);
            declaredField.setBoolean(Wrapper.INSTANCE.mc().playerController, z);
        }
        catch (Exception ex) {}
    }
    
    public static int findObiInHotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stackInSlot = Minecraft.getMinecraft().player.inventory.getStackInSlot(i);
            if (stackInSlot != ItemStack.EMPTY && stackInSlot.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock)stackInSlot.getItem()).getBlock();
                if (block instanceof BlockEnderChest) {
                    return i;
                }
                if (block instanceof BlockObsidian) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static float getCurBlockDamageMP() {
        float float1 = 0.0f;
        try {
            final Field declaredField = PlayerControllerMP.class.getDeclaredField(Mapping.curBlockDamageMP);
            declaredField.setAccessible(true);
            float1 = declaredField.getFloat(Wrapper.INSTANCE.mc().playerController);
        }
        catch (Exception ex) {}
        return float1;
    }
}
