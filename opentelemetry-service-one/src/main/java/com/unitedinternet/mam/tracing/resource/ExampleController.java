package com.unitedinternet.mam.tracing.resource;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ExampleController {

    private final WebClient webClient;

    public ExampleController(WebClient.Builder webClientBuilder, @Value("${service.two.uri}") String uri) {
        webClient = webClientBuilder
                .baseUrl(uri)
                .build();
    }

    @GetMapping("/")
    public String get(@RequestHeader Map<String, String> headers) {
        return "Service 1:\n" + headers.entrySet()
                .stream()
                .map(entry -> " " + entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n")) + "\n" +
                webClient
                        .get()
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
    }
}
