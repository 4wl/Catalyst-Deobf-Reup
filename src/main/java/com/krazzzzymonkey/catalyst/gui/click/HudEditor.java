/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click;

import com.krazzzzymonkey.catalyst.gui.click.base.Container;
import com.krazzzzymonkey.catalyst.gui.click.elements.Slider;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.Minecraft;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.gui.click.elements.Frame;
import javax.vecmath.Vector2f;

public class HudEditor extends HudGuiScreen
{
    public boolean dragging;
    public Vector2f draggingOffset;
    public Frame currentFrame;
    public static ArrayList<Frame> frames;
    public static Theme theme;
    
    public static Theme getTheme() {
        return HudEditor.theme;
    }
    
    public static void onUpdate() {
        final Iterator<Frame> iterator = HudEditor.frames.iterator();
        while (iterator.hasNext()) {
            iterator.next().updateComponents();
        }
    }
    
    public ArrayList getFrames() {
        return HudEditor.frames;
    }
    
    public void onKeyRelease(final int n, final char c) {
        final Iterator<Frame> iterator = HudEditor.frames.iterator();
    Label_0040_Outer:
        while (true) {
            while (true) {
                int n2 = 0;
                Label_0047: {
                    if (!iterator.hasNext()) {
                        n2 = -356040101;
                        break Label_0047;
                    }
                    n2 = -356040100;
                }
                switch (n2 - 817142097 + 1) {
                    default: {
                        continue;
                    }
                    case 629941491: {
                        iterator.next().onKeyReleased(n, c);
                        continue Label_0040_Outer;
                    }
                    case 629941492: {
                        break Label_0040_Outer;
                    }
                }
                break;
            }
        }
    }
    
    static {
        HudEditor.frames = new ArrayList<Frame>();
    }
    
    public void onMouseClick(final int n, final int n2, final int n3) {
        for (final Frame currentFrame : HudEditor.frames) {
            if (currentFrame.isMouseOver(n, n2)) {
                this.currentFrame = currentFrame;
                if (currentFrame.isMouseOverBar(n, n2)) {
                    this.dragging = true;
                    this.draggingOffset = new Vector2f((float)(n - currentFrame.getX()), (float)(n2 - currentFrame.getY()));
                }
                currentFrame.onMousePress(n, n2, n3);
            }
        }
    }
    
    public void setTheme(final Theme theme) {
        HudEditor.theme = theme;
    }
    
    public void addFrame(final Frame e) {
        HudEditor.frames.add(e);
    }
    
    public static void renderPinned() {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        final float n = scaledResolution.getScaleFactor() / (float)Math.pow(scaledResolution.getScaleFactor(), 2.0);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0f, 0.0f, 1000.0f);
        GlStateManager.scale(n * 2.0f, n * 2.0f, n * 2.0f);
        for (final Frame frame : HudEditor.frames) {
            if (frame.isPinned()) {
                frame.render(HudEditor.mouse[0], HudEditor.mouse[1]);
            }
        }
        GlStateManager.popMatrix();
    }
    
    public void onMouseScroll(final int n) {
        for (final Frame frame : HudEditor.frames) {
            if (frame.isMouseOver(HudEditor.mouse[0], HudEditor.mouse[1])) {
                frame.scrollFrame(n * 4);
            }
            frame.onMouseScroll(n * 4);
        }
    }
    
    public void onkeyPressed(final int n, final char c) {
        final Iterator<Frame> iterator = HudEditor.frames.iterator();
        while (iterator.hasNext()) {
            iterator.next().onKeyPressed(n, c);
        }
    }
    
    public void onMouseRelease(final int n, final int n2) {
        for (final Frame currentFrame : HudEditor.frames) {
            if (currentFrame.isMouseOver(n, n2)) {
                this.currentFrame = currentFrame;
                if (currentFrame.isMouseOverBar(n, n2)) {
                    this.dragging = false;
                }
                currentFrame.onMouseRelease(n, n2, 0);
            }
        }
    }
    
    public HudEditor() {
        this.dragging = false;
    }
    
    public void onMouseUpdate(final int n, final int n2) {
        final Iterator<Frame> iterator = HudEditor.frames.iterator();
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
    
    public void render() {
        final Iterator<Frame> iterator = HudEditor.frames.iterator();
        while (iterator.hasNext()) {
            iterator.next().render(HudEditor.mouse[0], HudEditor.mouse[1]);
        }
    }
}
