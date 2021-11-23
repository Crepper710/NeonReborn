package io.github.crepper710.neon_reborn.eventsystem;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EventBus {

    private static final Comparator<MethodInfo> COMPARATOR = Comparator.comparingInt(MethodInfo::getPriority);

    private final Map<Class<? extends Event>, List<MethodInfo>> eventMap = new HashMap<>();

    public void register(Object object) {
        for (Method m : object.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Subscribe.class)) {
                if (m.getParameterCount() == 1) {
                    if (Event.class.isAssignableFrom(m.getParameterTypes()[0])) {
                        Subscribe annotation = m.getAnnotation(Subscribe.class);
                        m.setAccessible(true);
                        for (Class<? extends Event> eventClass : annotation.value()) {
                            if (eventMap.containsKey(eventClass)) {
                                List<MethodInfo> temp = eventMap.get(eventClass);
                                if (temp != null) {
                                    temp.add(new MethodInfo(object, m, annotation.priority().getValue()));
                                    temp.sort(COMPARATOR);
                                    continue;
                                }
                            }
                            List<MethodInfo> temp = new ArrayList<>();
                            temp.add(new MethodInfo(object, m, annotation.priority().getValue()));
                            eventMap.put(eventClass, temp);
                        }
                    }
                }
            }
        }
    }

    public void register(Object object, Class<? extends Event> targetEventClass) {
        for (Method m : object.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Subscribe.class)) {
                if (m.getParameterCount() == 1) {
                    if (m.getParameterTypes()[0].isAssignableFrom(Event.class)) {
                        Subscribe annotation = m.getAnnotation(Subscribe.class);
                        m.setAccessible(true);
                        Arrays.stream(annotation.value()).filter(targetEventClass::equals).forEach(eventClass -> {
                            if (eventMap.containsKey(eventClass)) {
                                List<MethodInfo> temp = eventMap.get(eventClass);
                                if (temp != null) {
                                    temp.add(new MethodInfo(object, m, annotation.priority().getValue()));
                                    temp.sort(COMPARATOR);
                                    return;
                                }
                            }
                            List<MethodInfo> temp = new ArrayList<>();
                            temp.add(new MethodInfo(object, m, annotation.priority().getValue()));
                            eventMap.put(eventClass, temp);
                        });
                    }
                }
            }
        }
    }

    public void register(Object object, Class<? extends Event>[] targetEventClasses) {
        for (Class<? extends Event> targetEventClass : targetEventClasses) {
            register(object, targetEventClass);
        }
    }

    public void unregister(Object object) {
        for (List<MethodInfo> list : eventMap.values()) {
            list.removeIf(methodInfo -> methodInfo.equals(object));
        }
        eventMap.entrySet().forEach(entry -> {
            if (entry.getValue() != null) {
                if (!entry.getValue().isEmpty()) {
                    return;
                }
            }
            eventMap.remove(entry.getKey());
        });
    }

    public void unregister(Object object, Class<? extends Event> targetEventClass) {
        if (eventMap.containsKey(targetEventClass)) {
            if (eventMap.get(targetEventClass) != null) {
                eventMap.get(targetEventClass).removeIf(methodInfo -> methodInfo.equals(object));
            }
        }
        if (eventMap.containsKey(targetEventClass)) {
            if (eventMap.get(targetEventClass) != null) {
                if (!eventMap.get(targetEventClass).isEmpty()) {
                    return;
                }
            }
        }
        eventMap.remove(targetEventClass);
    }

    public void unregister(Object object, Class<? extends Event>[] targetEventClasses) {
        for (Class<? extends Event> targetEventClass : targetEventClasses) {
            unregister(object, targetEventClass);
        }
    }

    public void call(Event event) {
        Class<? extends Event> clazz = event.getClass();
        eventMap.forEach((eventClass, list) -> {
            if (eventClass != null) {
                if (eventClass.isAssignableFrom(clazz)) {
                    list.forEach(methodInfo -> {
                        try {
                            methodInfo.exec(event);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        });
    }

    private static class MethodInfo {

        private final Object source;
        private final Method method;
        private final int priority;

        private MethodInfo(Object source, Method method, int priority) {
            this.source = source;
            this.method = method;
            this.priority = priority;
        }

        public void exec(Event event) {
            try {
                method.invoke(source, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("failed to invoke method \"" + method.getName() + "\" in \"" + source.getClass().getName() + "\" with event \"" + event.getClass() + "\"", e);
            }
        }

        public int getPriority() {
            return priority;
        }

        public Method getMethod() {
            return method;
        }

        public Object getSource() {
            return source;
        }

    }

}