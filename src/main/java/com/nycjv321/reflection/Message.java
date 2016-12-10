package com.nycjv321.reflection;

import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by Javier L. Velasquez <nycjv321@gmail.com> on 12/9/16.
 */
public final class Message {
    private final Method method;

    Message(Method method) {
        this.method = method;
    }

    public <T extends Annotation> T annotation(Class<T> annotation) {
        return this.method.getAnnotation(annotation);
    }

    public <T extends Annotation> T argumentAnnotation(Class<T> annotation) {
        return argumentAnnotation(annotation, null);
    }

    @Nullable
    public <T extends Annotation> T argumentAnnotation(Class<T> annotation, @Nullable Predicate<T> predicate) {
        for (Parameter parameter : method.getParameters()) {
            T argumentAnnotation = parameter.getAnnotation(annotation);
            if (Objects.nonNull(argumentAnnotation) && Objects.isNull(predicate) || predicate.test(argumentAnnotation)) {
                return argumentAnnotation;
            }
        }
        return null;
    }


    /**
     * This method only works if the Java compiler includes the "-parameter" argument.
     * If you don't have this argument enabled, you should instead use {@code Message#argumentAnnotation}.
     * @param name a name identifying a parameter
     * @return the criterion describing the parameter
     */
    @Nullable
    public Criterion criterion(String name) {
        for (Parameter parameter : method.getParameters()) {
            if (parameter.getName().equals(name)) {
                return new Criterion(parameter);
            }
        }
        return null;
    }


    boolean isFinal() {
        return Modifier.isFinal(this.method.getModifiers());
    }
}
