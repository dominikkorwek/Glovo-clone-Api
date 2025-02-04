package pl.dodo.eLunchApp.config.jackson;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class TimeModule {

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .toFormatter(Locale.ENGLISH);

    private static class GlobalInstantSerializer extends InstantSerializer {
        public GlobalInstantSerializer() {
            super(InstantSerializer.INSTANCE, false, false, FORMATTER);
        }
    }

    private static class GlobalInstantDeserializer extends InstantDeserializer<Instant> {
        public GlobalInstantDeserializer() {
            super(InstantDeserializer.INSTANT, FORMATTER);
        }
    }

    @NotNull
    public static JavaTimeModule javaTimeModule() {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(Instant.class, new GlobalInstantSerializer());
        module.addDeserializer(Instant.class, new GlobalInstantDeserializer());
        return module;
    }
}
