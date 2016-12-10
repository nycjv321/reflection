package com.nycjv321.reflection.exceptions;

/**
 * Created by Javier L. Velasquez <nycjv321@gmail.com> on 12/9/16.
 */
public class MessageMissingException extends RuntimeException {
    public MessageMissingException(String message) {
        super(message);
    }
}
