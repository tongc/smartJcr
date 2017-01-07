package com.goda5.smartjcr;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.lang.reflect.Method;
import java.util.Map;

public class JcrService {
    private final ReflectionService reflectionService;

    public JcrService(ReflectionService reflectionService) {
        this.reflectionService = reflectionService;
    }

    public Node setProperties(Node node, Object object) throws RepositoryException {
        Map<Method, String> jcrPropertyMethods = reflectionService.getMethodsWithFieldNamesAnnotationWith(object, JcrProperty.class);
        Map<Method, String> jcrNodeMethods = reflectionService.getMethodsWithFieldNamesAnnotationWith(object, JcrNode.class);
        jcrNodeMethods.forEach((method, name) -> {
            try {
                Node subNode = node.addNode(name);
                setProperties(subNode, method.invoke(object));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        jcrPropertyMethods.forEach((method, name) -> {
            try {
                node.setProperty(name, String.valueOf(method.invoke(object)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return node;
    }
}
