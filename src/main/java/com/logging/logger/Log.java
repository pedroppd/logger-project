package com.logging.logger;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@RequiredArgsConstructor
@Component
public class Log {

    @Value("${debug:false}")
    private Boolean showDebug;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getCanonicalName());
    private final Logger logDebug = LoggerFactory.getLogger("local.debug.logger");

    public void info(String message, Object... args) {
        logger(message, com.logging.logger.LogLevel.INFO, true, args);
    }

    public void warn(String message, Object... args) {
        logger(message, com.logging.logger.LogLevel.WARNING, true, args);
    }

    public void error(String message, Object... args) {
        logger(message, com.logging.logger.LogLevel.ERROR, true, args);
    }

    public void debug(String message, Object... args) {
        logger(message, com.logging.logger.LogLevel.DEBUG, showDebug, args);
    }

    private void logger(String message, com.logging.logger.LogLevel logLevel, boolean showInCustomAppender, Object[] args) {
        MDC.clear();

        AtomicBoolean error = new AtomicBoolean(false);
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>(args);

        MDC.put("serviceName", "service-name-example");

        list.forEach(o -> {
            if (list.size() >= 2) {
                MDC.put(String.valueOf(list.remove(0)), String.valueOf(list.remove(0)).replaceAll("\"", ""));
            } else {
                if (list.size() == 1) {
                    error.set(true);
                }
            }
        });
        if (!error.get()) {
            if (showInCustomAppender) {
                logLevel.exec(log, message);
            } else {
                logLevel.exec(logDebug, message);
            }
        } else {
            log.error("Register log error: message - [{}] - params: {}", message, args);
        }

        MDC.clear();
    }
}
