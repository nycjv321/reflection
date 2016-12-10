package com.nycjv321.reflection;


import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

/**
 * Created by Javier L. Velasquez <nycjv321@gmail.com> on 12/9/16.
 */
public final class Criterion {
    private final Parameter parameter;

    Criterion(Parameter parameter) {
        this.parameter = parameter;
    }

    @Nullable
    public <T extends Annotation> T get(Class<T> annotation) {
        return this.parameter.getAnnotation(annotation);
    }
}
