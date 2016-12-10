package com.nycjv321.reflection;


import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Javier L. Velasquez <nycjv321@gmail.com> on 12/9/16.
 */
public class ReceiverTest {

    private Receiver<TestClass> receiver;

    @Test(groups = "receiver")
    public void of() throws Exception {
        receiver = Receiver.of(TestClass.class);
        assertNotNull(receiver);
    }

    @Test(dependsOnMethods = "of", groups = "receiver")
    public void isFinal() throws Exception {
        assertTrue(receiver.isFinal());
    }

    @Test(dependsOnMethods = "of", groups = "receiver")
    public void get() throws Exception {
        Test test = receiver.get(Test.class);
        assertNotNull(test);
        assertFalse(test.enabled());
    }

    @Test(dependsOnMethods = "get", groups = "receiver")
    public void getNull() throws Exception {
        assertNull(receiver.get(Deprecated.class));
    }

}