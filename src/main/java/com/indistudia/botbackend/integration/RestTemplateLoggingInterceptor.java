package com.indistudia.botbackend.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final String API_KEY_HEADER = "X-API-KEY";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info(
                "Outgoing HTTP request: method={}, uri={}, headers={}, body={}",
                request.getMethod(),
                request.getURI(),
                request.getHeaders(),
                body.length == 0 ? "<empty>" : new String(body, StandardCharsets.UTF_8)
        );

        return execution.execute(request, body);
    }

    private HttpHeaders maskSensitiveHeaders(HttpHeaders source) {
        HttpHeaders masked = new HttpHeaders();
        masked.putAll(source);
        if (masked.containsHeader(API_KEY_HEADER)) {
            masked.set(API_KEY_HEADER, "***masked***");
        }
        return masked;
    }
}
