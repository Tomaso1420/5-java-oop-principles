package com.example.task04;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ArrayList;

public class Logger {
    private final String Name;
    private Levels Level;

    private static ArrayList<MessageHandler> Handlers = new ArrayList<>();
    private static final HashMap<String, Logger> loggers = new HashMap<>();

    public Logger(String name) {
        Name = name;
        loggers.put(name, this);
        Handlers.add(new ConsoleHandler());
    }

    public Logger(String name, ArrayList<MessageHandler> messageHandlers) {
        Name = name;
        loggers.put(name, this);
        Handlers = messageHandlers;
    }

    public Logger(String name, Levels level, ArrayList<MessageHandler> messageHandlers) {
        Name = name;
        Level = level;
        Handlers = messageHandlers;
        loggers.put(name, this);
    }

    public String getName() {
        return Name;
    }

    public Levels getLevel() {
        return Level;
    }

    public void setLevel(Levels l) {
        Level = l;
    }

    public static Logger getLogger(String name) {
        if (loggers.get(name) == null) {
            loggers.put(name, new Logger(name));
        }
        return loggers.get(name);
    }

    public void debug(String message) {
        log(Levels.DEBUG, message);
    }

    public void debug(String format, Object... objects) {
        log(Levels.DEBUG, format, objects);
    }

    public void warning(String message) {
        log(Levels.WARNING, message);
    }

    public void warning(String format, Object... objects) {
        log(Levels.WARNING, format, objects);
    }

    public void info(String message) {
        log(Levels.INFO, message);
    }

    public void info(String format, Object... objects) {
        log(Levels.INFO, format, objects);
    }

    public void error(String message) {
        log(Levels.ERROR, message);
    }

    public void error(String format, Object... objects) {
        log(Levels.ERROR, format, objects);
    }

    public void log(Levels level, String message) {
        if (this.Level.ordinal() <= level.ordinal()) {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss"));
            String OutPut = String.format("[ %s ] %s %s - %s", level, date, Name, message);
            for (MessageHandler messageHandler : Handlers)
                messageHandler.handle(OutPut);
        }
    }

    public void log(Levels level, String format, Object... objects) {
        if (this.Level.ordinal() <= level.ordinal()) {
            String message = MessageFormat.format(format, objects);

            for (MessageHandler messageHandler : Handlers)
                messageHandler.handle(message);
        }
    }
}
