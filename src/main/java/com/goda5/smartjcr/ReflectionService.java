package com.goda5.smartjcr;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.beans.*;
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
        return allMethods.stream()
                .map(method ->
                        getFieldNameForMethod(method)
                                .map((Function<String, Optional<Map.Entry<Method, String>>>) fieldName ->
                                        Optional.of(new AbstractMap.SimpleImmutableEntry<>(method, fieldName)))
                                .orElse(Optional.empty()))
                .filter(Optional::isPresent)
                .collect(Collectors.toMap(e -> e.get().getKey(), e -> e.get().getValue()));
    }

    public List<Method> getAllMethods(List<Method> methods, Class<?> type) {
        methods.addAll(Arrays.asList(type.getDeclaredMethods()));
        if (type.getSuperclass() != null) {
            methods = getAllMethods(methods, type.getSuperclass());
        }
        return methods;
    }

    public Optional<String> getFieldNameForMethod(Method method) {
        Class<?> clazz = method.getDeclaringClass();
        BeanInfo info;
        try {
            info = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            return Arrays.stream(props)
                    .filter(pd -> method.equals(pd.getWriteMethod()) || method.equals(pd.getReadMethod()))
                    .map(FeatureDescriptor::getName)
                    .findFirst();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
