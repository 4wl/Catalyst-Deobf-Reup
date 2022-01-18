/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click;

import com.krazzzzymonkey.catalyst.gui.click.base.Container;
import com.krazzzzymonkey.catalyst.gui.click.elements.Slider;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import java.util.Iterator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import javax.vecmath.Vector2f;
import com.krazzzzymonkey.catalyst.gui.click.elements.Frame;
import java.util.ArrayList;

public class ClickGui extends ClickGuiScreen
{
    public boolean dragging;
    public static ArrayList<Frame> frames;
    public Frame currentFrame;
    public Vector2f draggingOffset;
    public static Theme theme;
    
    public static void renderPinned() {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        final float n = scaledResolution.getScaleFactor() / (float)Math.pow(scaledResolution.getScaleFactor(), 2.0);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0f, 0.0f, 1000.0f);
        GlStateManager.scale(n * 2.0f, n * 2.0f, n * 2.0f);
        for (final Frame frame : ClickGui.frames) {
            if (frame.isPinned()) {
                frame.render(ClickGui.mouse[0], ClickGui.mouse[1]);
            }
        }
        GlStateManager.popMatrix();
    }
    
    public void onMouseScroll(final int n) {
        int n2 = 0;
        final Iterator<Frame> iterator = ClickGui.frames.iterator();
        while (iterator.hasNext()) {
            n2 += iterator.next().getY();
        }
        for (final Frame frame : ClickGui.frames) {
            frame.setyPos(frame.getY() + n * 4);
        }
    }
    
    public static Theme getTheme() {
        return ClickGui.theme;
    }
    
    public ArrayList getFrames() {
        return ClickGui.frames;
    }
    
    public void render() {
        final Iterator<Frame> iterator = ClickGui.frames.iterator();
        while (iterator.hasNext()) {
            iterator.next().render(ClickGui.mouse[0], ClickGui.mouse[1]);
        }
    }
    
    public void onMouseClick(final int n, final int n2, final int n3) {
        for (final Frame currentFrame : ClickGui.frames) {
            if (currentFrame.isMouseOver(n, n2)) {
                this.currentFrame = currentFrame;
                if (currentFrame.isMouseOverBar(n, n2)) {
                    this.dragging = true;
                    this.draggingOffset = new Vector2f((float)(n - ~currentFrame.getX() + 1), (float)(n2 - currentFrame.getY()));
                }
                currentFrame.onMousePress(n, n2, n3);
            }
        }
    }
    
    static {
        ClickGui.frames = new ArrayList<Frame>();
    }
    
    public ClickGui() {
        this.dragging = false;
    }
    
    public void onKeyRelease(final int n, final char c) {
        final Iterator<Frame> iterator = ClickGui.frames.iterator();
        while (iterator.hasNext()) {
            iterator.next().onKeyReleased(n, c);
        }
    }
    
    public void addFrame(final Frame e) {
        ClickGui.frames.add(e);
    }
    
    public void onkeyPressed(final int n, final char c) {
        final Iterator<Frame> iterator = ClickGui.frames.iterator();
        while (iterator.hasNext()) {
            iterator.next().onKeyPressed(n, c);
        }
    }
    
    public void onMouseUpdate(final int n, final int n2) {
        final Iterator<Frame> iterator = ClickGui.frames.iterator();
        while (iterator.hasNext()) {
            for (final Component component : iterator.next().getComponents()) {
                if (component.isMouseOver(n, n2)) {
                    component.onMouseDrag(n, n2);
                }
                else {
                    if (!(component instanceof Slider)) {
                        continue;
                    }
                    ((Slider)component).dragging = false;
                }
            }
        }
        if (this.dragging && this.currentFrame != null) {
            final int n3 = (int)(n2 - this.draggingOffset.getY() - this.currentFrame.getY());
            this.currentFrame.setxPos((int)(n - this.draggingOffset.getX()));
            this.currentFrame.setyPos((int)(n2 - this.draggingOffset.getY()));
            for (final Component component2 : this.currentFrame.getComponents()) {
                component2.setyBase(component2.getyBase() + n3);
                if (component2 instanceof Container) {
                    final Container container = (Container)component2;
                    int n4 = 0;
                    for (final Component component3 : container.getComponents()) {
                        component3.setxPos(component2.getX());
                        component3.setyPos(component2.getY());
                        n4 += component3.getDimension().height;
                    }
                }
            }
        }
    }
    
    public static void onUpdate() {
        final Iterator<Frame> iterator = ClickGui.frames.iterator();
        while (iterator.hasNext()) {
            iterator.next().updateComponents();
        }
    }
    
    public void onMouseRelease(final int n, final int n2) {
        for (final Frame currentFrame : ClickGui.frames) {
            if (currentFrame.isMouseOver(n, n2)) {
                this.currentFrame = currentFrame;
                if (currentFrame.isMouseOverBar(n, n2)) {
                    this.dragging = false;
                }
                currentFrame.onMouseRelease(n, n2, 0);
            }
        }
    }
    
    public void setTheme(final Theme theme) {
        ClickGui.theme = theme;
    }
}
