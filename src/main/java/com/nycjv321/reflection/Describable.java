package com.nycjv321.reflection;

import java.lang.annotation.Annotation;

/**
 * Created by fedora on 12/18/16.
 */
public interface Describable {

    <T extends Annotation> T get(Class<T> annotation);

}
