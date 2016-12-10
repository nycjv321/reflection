package com.nycjv321.reflection;

import org.testng.annotations.Test;

import javax.jws.WebParam;

/**
 * Created by Javier L. Velasquez <nycjv321@gmail.com> on 12/9/16.
 */
@Test(enabled = false)
final class TestClass {

    @Test(enabled = false)
    @Deprecated
    public String stuff() {
        return "";
    }

    @Test(enabled = false)
    @Deprecated
    public final String stuff(@WebParam(header = true) String arg) {
        return arg;
    }


    public final String stuff(boolean arg) {
        return String.valueOf(arg);
    }


}
