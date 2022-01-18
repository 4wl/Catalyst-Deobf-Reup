/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils.system;

import java.util.Iterator;
import java.util.Set;
import net.minecraftforge.fml.common.MinecraftDummyContainer;
import java.lang.annotation.Annotation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.google.common.reflect.TypeToken;
import net.minecraftforge.fml.common.Loader;
import java.util.Map;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import net.minecraftforge.fml.common.eventhandler.IEventListener;
import net.minecraftforge.fml.common.eventhandler.ASMEventHandler;
import net.minecraftforge.fml.common.eventhandler.Event;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.common.ModContainer;
import java.lang.reflect.Method;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class Nan0EventRegister
{
    public static void register(final EventBus eventBus, final Class clazz, final Object o, final Method method, final ModContainer modContainer) {
        try {
            final int intValue = (int)ReflectionHelper.getPrivateValue((Class)EventBus.class, (Object)eventBus, new String[] { "busID" });
            final ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap)ReflectionHelper.getPrivateValue((Class)EventBus.class, (Object)eventBus, new String[] { "listeners" });
            final Constructor<Event> constructor = clazz.getConstructor((Class<?>[])new Class[0]);
            constructor.setAccessible(true);
            final Event event = constructor.newInstance(new Object[0]);
            final ASMEventHandler e = new ASMEventHandler(o, method, modContainer);
            event.getListenerList().register(intValue, e.getPriority(), (IEventListener)e);
            ArrayList<IEventListener> value = concurrentHashMap.get(o);
            if (value == null) {
                value = new ArrayList<IEventListener>();
                concurrentHashMap.put(o, value);
                ReflectionHelper.setPrivateValue((Class)EventBus.class, (Object)eventBus, (Object)concurrentHashMap, new String[] { "listeners" });
            }
            value.add((IEventListener)e);
        }
        catch (Exception ex) {}
    }
    
    public static void register(final EventBus eventBus, final Object key) {
        final ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap)ReflectionHelper.getPrivateValue((Class)EventBus.class, (Object)eventBus, new String[] { "listeners" });
        final Class<EventBus> clazz = EventBus.class;
        final String[] array = { null };
        final int n = 0;
        while (true) {
            switch (-235966589 - 1437677811 + 1) {
                case 2090469398: {
                    continue;
                }
                case 1537321102: {
                    array[n] = "listenerOwners";
                    final Map map = (Map)ReflectionHelper.getPrivateValue((Class)clazz, (Object)eventBus, array);
                    if (concurrentHashMap.containsKey(key)) {
                        return;
                    }
                    final MinecraftDummyContainer minecraftModContainer = Loader.instance().getMinecraftModContainer();
                    map.put(key, minecraftModContainer);
                    ReflectionHelper.setPrivateValue((Class)EventBus.class, (Object)eventBus, (Object)map, new String[] { "listenerOwners" });
                    final Set rawTypes = TypeToken.of((Class)key.getClass()).getTypes().rawTypes();
                    for (final Method method : key.getClass().getMethods()) {
                        for (final Class clazz2 : rawTypes) {
                            try {
                                if (clazz2.getDeclaredMethod(method.getName(), (Class[])method.getParameterTypes()).isAnnotationPresent((Class<? extends Annotation>)SubscribeEvent.class)) {
                                    register(eventBus, method.getParameterTypes()[0], key, method, (ModContainer)minecraftModContainer);
                                    break;
                                }
                                continue;
                            }
                            catch (NoSuchMethodException ex) {}
                        }
                    }
                }
                default: {
                    throw null;
                }
            }
        }
    }
}
