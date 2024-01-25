package ru.itrum.core.task02;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/getCurrentDateTime")
    public LocalDateTimeFormatter getCurrentDateTime() {
        return new LocalDateTimeFormatter(LocalDateTime.now());
    }
}
