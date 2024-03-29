package com.logging.logger;

import ch.qos.logback.contrib.jackson.JacksonJsonFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class CustomJsonFormatter extends JacksonJsonFormatter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String toJsonString(Map m) throws IOException {
        m.remove("context");
        Optional.ofNullable(m.remove("mdc"))
                .ifPresent(mdc -> ((Map<Object, Object>) mdc).entrySet()
                        .forEach(oe -> m.put(String.valueOf(oe.getKey()), String.valueOf(oe.getValue())))
                );
        m.put("@timestamp", new Date());

        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        this.objectMapper.setDateFormat(df);

        return this.objectMapper.writeValueAsString(m);
    }
}