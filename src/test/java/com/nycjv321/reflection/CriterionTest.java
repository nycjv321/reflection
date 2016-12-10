package com.nycjv321.reflection;

import org.testng.annotations.Test;

import javax.jws.WebParam;

import static org.testng.Assert.*;

/**
 * Created by Javier L. Velasquez <nycjv321@gmail.com> on 12/9/16.
 * These sort of calls only work when the Java compiler is ran with the "-parameters" argument.
 */
public class CriterionTest {

    private final Receiver<TestClass> receiver = Receiver.of(TestClass.class);

    @Test(dependsOnGroups = "messages", groups = "criterion")
    public void get() throws Exception {
        assertNotNull(
                receiver
                        .on("stuff", String.class)
                        .criterion("arg").get(WebParam.class)
        );
    }

    @Test(dependsOnGroups = "messages", groups = "criterion")
    public void getNullAnnotation() throws Exception {
        assertNull(
                receiver
                        .on("stuff", boolean.class)
                        .criterion("arg").get(WebParam.class)
        );
    }

    @Test(dependsOnGroups = "messages", groups = "criterion")
    public void getNullCriteria() throws Exception {
        assertNull(
                receiver
                        .on("stuff", boolean.class)
                        .criterion("i_dont_exist")
        );
    }



}