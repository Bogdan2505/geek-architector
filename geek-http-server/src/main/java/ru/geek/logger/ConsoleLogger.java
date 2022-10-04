package ru.geek.logger;

public class ConsoleLogger implements Logger {
    @Override
    public void info(String msg) {
        System.out.println(msg);
    }
}
