package com.goda5.smartjcr;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Immutable
public class ReflectionService {
    public Map<Method, String> getMethodsWithFieldNamesAnnotationWith(Object object, Class<? extends Annotation> annotationClass) {
        return getAllMethodsWithFieldNames(object.getClass())
                .entrySet()
                .stream()
                .filter(methodAndFieldName -> methodAndFieldName.getKey().isAnnotationPresent(annotationClass))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Method, String> getAllMethodsWithFieldNames(Class<?> type) {
        List<Method> allMethods = getAllMethods(new ArrayList<>(), type);
        return allMethods.stream().map((Function<Method, Optional<Map.Entry<Method, String>>>) method -> {
            try {
                if (getFieldNameForMethod(method).isPresent()) {
                    return Optional.of(new AbstractMap.SimpleImmutableEntry<>(method, getFieldNameForMethod(method).get()));
                }
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
            return Optional.empty();
        }).filter(Optional::isPresent)
                .collect(Collectors.toMap(p -> p.get().getKey(), p -> p.get().getValue()));
    }

    public List<Method> getAllMethods(List<Method> methods, Class<?> type) {
        methods.addAll(Arrays.asList(type.getDeclaredMethods()));
        if (type.getSuperclass() != null) {
            methods = getAllMethods(methods, type.getSuperclass());
        }
        return methods;
    }

    public Optional<String> getFieldNameForMethod(Method method) throws IntrospectionException {
        Class<?> clazz = method.getDeclaringClass();
        BeanInfo info = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] props = info.getPropertyDescriptors();
        for (PropertyDescriptor pd : props) {
            if (method.equals(pd.getWriteMethod()) || method.equals(pd.getReadMethod())) {
                return Optional.of(pd.getName());
            }
        }
        return Optional.empty();
    }
}
