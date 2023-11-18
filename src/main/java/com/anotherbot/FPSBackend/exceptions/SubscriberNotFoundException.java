package com.anotherbot.FPSBackend.exceptions;
// non surveiller RuntimeException
// surveiller Exception il faut faire throws ds la signature
public class SubscriberNotFoundException extends Exception {
    public SubscriberNotFoundException(String message) {
        super(message);
    }
}
