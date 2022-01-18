/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui;

import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.configuration.elements.Button;
import net.minecraft.client.gui.GuiButton;

public class GuiCustomWrappedButton extends GuiCustomButton
{
    public GuiButton wrappedButton;
    public int wrappedButtonID;
    
    public GuiCustomWrappedButton(final int n, final int wrappedButtonID, final Button button) {
        super(n, button);
        this.wrappedButtonID = wrappedButtonID;
    }
    
    public void init(final GuiButton wrappedButton) {
        this.wrappedButton = wrappedButton;
        if (wrappedButton == null) {
            final boolean b = false;
            this.enabled = b;
            this.visible = b;
        }
    }
    
    public GuiButton getWrappedButton() {
        return this.wrappedButton;
    }
    
    @Override
    public void drawButton(final Minecraft minecraft, final int n, final int n2, final float n3) {
        if (this.wrappedButton != null) {
            boolean enabled = false;
            boolean visible = false;
            Label_0075: {
                if (this.wrappedButton.visible) {
                    if (this.wrappedButton.enabled) {
                        visible = (enabled = true);
                        break Label_0075;
                    }
                }
                visible = (enabled = false);
            }
            this.enabled = enabled;
            this.visible = visible;
        }
        else {
            final boolean b = false;
            this.enabled = b;
            this.visible = b;
        }
        super.drawButton(minecraft, n, n2, n3);
    }
}
