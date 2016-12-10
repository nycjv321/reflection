package com.nycjv321.reflection;

import com.nycjv321.reflection.exceptions.MessageMissingException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Created by Javier L. Velasquez <nycjv321@gmail.com> on 12/9/16.
 */
public final class Receiver<T> {

    private final Class<T> clazz;

    private Receiver(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static <T> Receiver<T> of(Class<T> clazz) {
        return new Receiver<>(clazz);
    }

    public Message on(String methodName, Class<?> ... arguments) {
        try {
            return new Message(this.clazz.getMethod(methodName, arguments));
        } catch (NoSuchMethodException e) {
            if (arguments.length > 0) {
                throw new MessageMissingException(String.format("%s did not have a method named %s with the following arguments: %s", this.clazz, methodName, Arrays.toString(arguments)));
            } else {
                throw new MessageMissingException(String.format("%s did not have a method named %s", this.clazz, methodName));
            }
        }

    }

    public <R extends Annotation> R get(Class<R> annotation) {
        return this.clazz.getAnnotation(annotation);
    }

    public boolean isFinal() {
        return Modifier.isFinal(clazz.getModifiers());
    }

    public boolean isFinal(String methodName, Class<?> ... arguments) {
        return on(methodName, arguments).isFinal();
    }
}
