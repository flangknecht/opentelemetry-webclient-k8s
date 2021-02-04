package com.unitedinternet.mam.tracing.resource;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/")
    public String get(@RequestHeader Map<String, String> headers) {
        return "Service 2:\n" + headers.entrySet()
                .stream()
                .map(entry -> " " + entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }

}
