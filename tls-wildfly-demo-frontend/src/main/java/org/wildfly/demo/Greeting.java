package org.wildfly.demo;

public record Greeting(String message, String hostname, int localPort, String localAddress) {}
