package com.logging.logger;

import java.util.function.Consumer;
import java.util.function.Function;

import org.slf4j.Logger;

public enum LogLevel {

    INFO(logger -> logger::info),
    WARNING(logger -> logger::warn),
    ERROR(logger -> logger::error),
    DEBUG(logger -> logger::debug);

    private final Function<Logger, Consumer<String>> execute;

    LogLevel(Function<Logger, Consumer<String>> execute) {
        this.execute = execute;
    }

    public void exec(Logger logger, String message) {
        this.execute.apply(logger).accept(message);
    }
}
