package org.beckn.bap.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.beckn.bap.common.exception.ServerErrorException;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestOperations;

import java.io.IOException;

@Slf4j
@Component
public class RestApiClient {

    private final RestOperations template;
    private final ObjectMapper objectMapper;

    public RestApiClient(RestOperations template, ObjectMapper objectMapper) {
        this.template = template;
        this.objectMapper = objectMapper;
    }

    public <T> ResponseEntity<T> get(String url, HttpHeaders headers, Class<T> responseType) {
        ResponseEntity<String> response = template.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity(headers),
                String.class
        );
        try {
            return new ResponseEntity(
                    response.getBody() == null ? null : objectMapper.readValue(response.getBody(), responseType),
                    response.getHeaders(),
                    response.getStatusCode()
            );
        } catch (IOException e) {
            if (StringUtils.hasText(e.getMessage()) && e.getMessage().contains("'<'")) {
                String message = String.format("GET %s returned html body: %s", url, e.getMessage());
                throw new HttpClientErrorException(message, HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.toString(), HttpHeaders.EMPTY, null, null);
            }
            String message = String.format("GET %s returned unprocessable json body: %s", url, e.getMessage());
            throw new HttpClientErrorException(message, HttpStatus.UNPROCESSABLE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY.toString(), HttpHeaders.EMPTY, null, null);
        }
    }

    public <T> ResponseEntity<T> put(String url, HttpHeaders headers, Object body, Class<T> responseType) {
        return execute(HttpMethod.PUT, url, headers, body, responseType);
    }

    public <T> ResponseEntity<T> post(String url, HttpHeaders headers, Object body, Class<T> responseType) {
        return execute(HttpMethod.POST, url, headers, body, responseType);
    }

    public ResponseEntity<Object> delete(String url, HttpHeaders headers) {
        return execute(HttpMethod.DELETE, url, headers, null, Object.class);
    }

    public <T> ResponseEntity<T> execute(HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> responseType) {
        try {
            ResponseEntity<String> response = template.exchange(
                    url,
                    method,
                    body == null ? new HttpEntity(headers) : new HttpEntity(body, headers),
                    String.class
            );
            return new ResponseEntity(
                    response.getBody() == null ? null : objectMapper.readValue(response.getBody(), responseType),
                    response.getHeaders(),
                    response.getStatusCode()
            );
        } catch (IOException e) {
            if (StringUtils.hasText(e.getMessage()) && e.getMessage().contains("'<'")) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, e.getMessage());
            }
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        } catch (RestClientResponseException e) {
            HttpStatus status = HttpStatus.resolve(e.getRawStatusCode());
            if (status == HttpStatus.UNAUTHORIZED || status == HttpStatus.FORBIDDEN || status == HttpStatus.EXPECTATION_FAILED) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, e.getResponseBodyAsString());
            }
            if (status.is4xxClientError()) {
                throw new HttpClientErrorException(status, e.getResponseBodyAsString());
            }
            if (status == HttpStatus.GATEWAY_TIMEOUT) {
                throw new HttpClientErrorException(HttpStatus.GATEWAY_TIMEOUT, e.getResponseBodyAsString());
            }
            throw new ServerErrorException(e.getRawStatusCode(), e.getResponseBodyAsString());
        } catch (RestClientException e) {
            throw new ServerErrorException(e.getMessage());
        }
    }
}
