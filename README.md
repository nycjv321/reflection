# Reflection
This project provides a set of fluent APIs for performing reflection.

## About

This project aims to provide a straight forward, human friendly api to perform reflective operations.

The goal of this project is to provide the necessary support to test annotation configuration. 
Keeping this in mind, you may notice the current API focuses around Class, Method, and Parameter annotations. 

## Dependencies

- `com.intellij.annotations` - used to establish potential nullity
- `Java 8`

## Example Usage

Take a look at the unit tests. Current coverage is at `100%`!
 
If you're too impatient to look at the unit tests (shame on you), See below:

    // Extract the "arg" parameter's WebParam annotation. 
    // This metadata is found on the TestClass#stuff(String arg) method.
    Receiver
        .of(TestClass.class)
        .on("stuff", String.class)
        .criterion("arg").get(WebParam.class);
        
### Domain Language
In this API, abstractions of classes (T) are referred to Receivers of type T. 

     new Receiver<T>(clazz); // class representing a definition of T
     
Methods and their parameters are referred to as messages. To better understand methods as messages, take a look [here](https://en.wikipedia.org/wiki/Object-oriented_programming#Dynamic_dispatch.2Fmessage_passing).

Method parameters are referred to as "criterion". If this code is compiled using Java 8 and includes the `-parameters` argument, you can refer to parameters by name. See first example.
       
Alternatively, you may "search" for arguments. For example:

    Receiver.of(TestClass.class)
        .on("stuff", String.class)
        .argumentAnnotation(WebParam.class, webParam -> !webParam.header());
        
In the above example, we search for an argument's WebParam annotation where the WebParam#header() is false. 
The `Predicate` is an optional argument.
