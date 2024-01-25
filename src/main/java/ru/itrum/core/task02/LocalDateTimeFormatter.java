package ru.itrum.core.task02;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

public class LocalDateTimeFormatter{
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy:MM:dd'##':HH:mm:ss:SSS", locale = "ru_RU")
    private LocalDateTime localDateTime;

    public LocalDateTimeFormatter(LocalDateTime dateTime) {
        this.localDateTime = dateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
