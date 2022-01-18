/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.gui.click.elements;

import com.krazzzzymonkey.catalyst.gui.click.ClickGui;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentRenderer;
import com.krazzzzymonkey.catalyst.gui.click.base.ComponentType;
import java.awt.Dimension;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.gui.click.base.Component;
import com.krazzzzymonkey.catalyst.gui.click.base.Container;

public class Frame extends Container
{
    public boolean pinned;
    public int ticksSinceScroll;
    public boolean pinnable;
    public static boolean isDragging;
    public boolean visable;
    public int scrollAmmount;
    public boolean maximized;
    public boolean maximizible;
    
    @Override
    public void onKeyReleased(final int n, final char c) {
        final Iterator<Component> iterator = this.getComponents().iterator();
        while (iterator.hasNext()) {
            iterator.next().onKeyReleased(n, c);
        }
    }
    
    @Override
    public void onMouseRelease(final int n, final int n2, final int n3) {
        if (n >= this.getX() && n2 >= this.getY() + this.getFrameBoxHeight() && n <= this.getX() + this.getDimension().getWidth() && n2 <= this.getY() + this.getDimension().getHeight()) {
            for (final Component component : this.getComponents()) {
                if (component.isMouseOver(n, n2) && this.maximized) {
                    component.onMouseRelease(n, n2, n3);
                }
            }
        }
        Frame.isDragging = false;
    }
    
    public void setScrollAmmount(final int scrollAmmount) {
        this.scrollAmmount = scrollAmmount;
    }
    
    public void setPinned(final boolean pinned) {
        this.pinned = pinned;
    }
    
    public boolean isMouseOverBar(final int n, final int n2) {
        if (n >= this.getX()) {
            if (n2 >= this.getY() && n <= this.getX() + this.getDimension().getWidth() && n2 <= this.getY() + 15) {
                return true;
            }
        }
        return false;
    }
    
    public void setMaximizible(final boolean maximizible) {
        this.maximizible = maximizible;
    }
    
    public void updateComponents() {
        ++this.ticksSinceScroll;
        if (this.scrollAmmount < this.getMaxScroll()) {
            this.scrollAmmount = this.getMaxScroll();
        }
        if (this.scrollAmmount > 0) {
            this.scrollAmmount = 0;
        }
        for (final Component o : this.getComponents()) {
            o.onUpdate();
            if (o instanceof Container) {
                final Iterator iterator2 = ((Container)o).getComponents().iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().onUpdate();
                }
            }
            int n = this.getY() + this.getFrameBoxHeight();
            for (final Component o2 : this.getComponents()) {
                if (this.getComponents().indexOf(o2) < this.getComponents().indexOf(o)) {
                    n += (int)o2.getDimension().getHeight();
                }
            }
            o.setyBase(n);
            o.setyPos(o.getyBase() + this.scrollAmmount);
        }
        this.setDimension(new Dimension(this.getDimension().width, Wrapper.INSTANCE.mc().displayHeight / 3 + this.getComponents().size() + 1000));
    }
    
    static {
        Frame.isDragging = false;
    }
    
    public Frame(final int n, final int n2, final int n3, final int n4, final String s) {
        super(n, n2, n3, n4, ComponentType.FRAME, null, s);
        this.maximizible = true;
        this.visable = true;
        this.pinnable = true;
        this.ticksSinceScroll = 100;
        this.scrollAmmount = 0;
    }
    
    public boolean isPinnable() {
        return this.pinnable;
    }
    
    public void setMaximized(final boolean maximized) {
        this.maximized = maximized;
    }
    
    public int getMaxY() {
        return (int)(this.getY() + this.getDimension().getHeight());
    }
    
    public void setTicksSinceScroll(final int ticksSinceScroll) {
        this.ticksSinceScroll = ticksSinceScroll;
    }
    
    public int getMaxScroll() {
        if (this.getComponents().size() == 0) {
            return 0;
        }
        final Component component = this.getComponents().get(this.getComponents().size() - 1);
        return this.getMaxY() - (int)(component.getyBase() + component.getDimension().getHeight());
    }
    
    @Override
    public void renderChildren(final int n, final int n2) {
        if (this.isMaximized()) {
            final Iterator<Component> iterator = this.getComponents().iterator();
            while (iterator.hasNext()) {
                iterator.next().render(n, n2);
            }
        }
    }
    
    public boolean isMaximizible() {
        return this.maximizible;
    }
    
    public void scrollFrame(final int n) {
        this.scrollAmmount += n;
        this.ticksSinceScroll = 0;
    }
    
    public boolean isPinned() {
        return this.pinned;
    }
    
    @Override
    public void onMousePress(final int n, final int n2, final int n3) {
        if (this.isMouseOverBar(n, n2)) {
            ClickGui.getTheme().getRenderer().get(this.getComponentType()).doInteractions(this, n, n2);
            Frame.isDragging = true;
        }
        if (n >= this.getX()) {
            if (n2 >= this.getY() + ~this.getFrameBoxHeight() - 1) {
                if (n <= this.getX() + this.getDimension().getWidth() && n2 <= this.getY() + this.getDimension().getHeight()) {
                    for (final Component component : this.getComponents()) {
                        if (component.isMouseOver(n, n2) && this.maximized) {
                            component.onMousePress(n, n2, n3);
                            ClickGui.getTheme().getRenderer().get(this.getComponentType()).doInteractions(this, n, n2);
                        }
                    }
                }
            }
        }
    }
    
    public void setVisable(final boolean visable) {
        this.visable = visable;
    }
    
    public int getTicksSinceScroll() {
        return this.ticksSinceScroll;
    }
    
    public boolean isVisable() {
        return this.visable;
    }
    
    public int getFrameBoxHeight() {
        return ClickGui.getTheme().getFrameHeight();
    }
    
    public void setPinnable(final boolean pinnable) {
        this.pinnable = pinnable;
    }
    
    @Override
    public void onKeyPressed(final int n, final char c) {
        final Iterator<Component> iterator = this.getComponents().iterator();
        while (iterator.hasNext()) {
            iterator.next().onKeyPressed(n, c);
        }
    }
    
    @Override
    public void onMouseDrag(final int n, final int n2) {
        if (n >= this.getX() && n <= this.getX() + 80 && n2 >= this.getY() && n2 <= this.getY() + 20) {
            ClickGui.getTheme().getRenderer().get(this.getComponentType()).doInteractions(this, n, n2);
            Frame.isDragging = true;
        }
        if (n >= this.getX()) {
            if (n <= this.getX() + 80 && n2 >= this.getY() && n2 <= this.getY() + 20) {
                for (final Component component : this.getComponents()) {
                    if (component.isMouseOver(n, n2) && this.maximized) {
                        component.onMouseDrag(n, n2);
                        ClickGui.getTheme().getRenderer().get(this.getComponentType()).doInteractions(this, n, n2);
                        Frame.isDragging = true;
                    }
                }
            }
        }
    }
    
    public int getScrollAmmount() {
        return this.scrollAmmount;
    }
    
    public boolean isMaximized() {
        return this.maximized;
    }
}
