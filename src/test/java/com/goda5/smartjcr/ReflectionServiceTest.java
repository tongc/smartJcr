package com.goda5.smartjcr;

import com.goda5.smartjcr.model.Monkey;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ReflectionServiceTest {
    private ReflectionService objectUnderTest;

    @Before
    public void init() {
        objectUnderTest = new ReflectionService();
    }

    @Test
    public void shouldGetMethodsWithGivenAnnotation() {
        Monkey monkey = new Monkey("monkey");
        Map<Method, String> methodsWithFieldNamesAnnotationWith = objectUnderTest.getMethodsWithFieldNamesAnnotationWith(monkey, JcrProperty.class);
        assertThat(methodsWithFieldNamesAnnotationWith.size(), is(1));
    }
}