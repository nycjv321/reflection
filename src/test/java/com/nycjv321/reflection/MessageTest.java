package com.nycjv321.reflection;

import com.nycjv321.reflection.exceptions.MessageMissingException;
import org.testng.annotations.Test;

import javax.jws.WebParam;

import static org.testng.Assert.*;

/**
 * Created by Javier L. Velasquez <nycjv321@gmail.com> on 12/9/16.
 */
public class MessageTest {

    private Message message;
    private final Receiver<TestClass> receiver = Receiver.of(TestClass.class);

    @Test(dependsOnGroups = "receiver", groups = "messages")
    public void on() {
        message = receiver.on("stuff");
        assertNotNull(message);
    }

    @Test(dependsOnMethods = "on", dependsOnGroups = "receiver", groups = "messages", expectedExceptions = MessageMissingException.class)
    public void onNull() {
        receiver.on("Meep!");
    }


    @Test(dependsOnMethods = "on", dependsOnGroups = "receiver", groups = "messages", expectedExceptions = MessageMissingException.class)
    public void onNullWithArguments() {
        receiver.on("Meep!", boolean.class);
    }

    @Test(dependsOnMethods = "on", dependsOnGroups = "receiver", groups = "messages")
    public void annotation() {
        assertFalse(
            message
                .annotation(Test.class)
                .enabled()
        );
    }

    @Test(dependsOnMethods = "on", dependsOnGroups = "receiver", groups = "messages")
    public void isFinal() {
        assertTrue(
                Receiver
                    .of(TestClass.class)
                    .isFinal("stuff",String.class)
        );
    }

    /**
     * Once we know {@code Receiver#on} works on methods without arguments,
     * let's update our test message to represent messages that include arguments
     * (∩｀-´)⊃━☆ﾟ.*･｡ﾟ
     */
    @Test(dependsOnMethods = "on", dependsOnGroups = "receiver", groups = "messages")
    public void onWithArgument() {
        message = Receiver
                .of(TestClass.class)
                .on("stuff", String.class);
        assertNotNull(message);
    }


    @Test(dependsOnMethods = "onWithArgument", dependsOnGroups = "receiver", groups = "messages")
    public void argumentAnnotation() {
        assertTrue(
                message
                .argumentAnnotation(WebParam.class)
                .header()
        );
    }

    @Test(dependsOnMethods = "onWithArgument", dependsOnGroups = "receiver", groups = "messages")
    public void argumentAnnotationPredicate() {
        assertNull(message.argumentAnnotation(WebParam.class, webParam -> !webParam.header()));
    }


    @Test(dependsOnMethods = "onWithArgument", dependsOnGroups = "receiver", groups = "messages")
    public void argument() {
        assertNotNull(message.criterion("arg"));
    }

}