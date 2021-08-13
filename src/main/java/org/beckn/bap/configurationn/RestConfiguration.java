package org.beckn.bap.configurationn;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
public class RestConfiguration {

    private ClientHttpRequestInterceptor loggingInterceptor() {
        var log = LoggerFactory.getLogger(ClientHttpRequestInterceptor.class);

        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                var response = execution.execute(request, body);
                logMessage(request, body, response);
                return response;
            }

            private void logMessage(HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException {
                int statusCode = response.getRawStatusCode();
                String logMessage = String.format(
                        "%s %s returned status %s",
                        request.getMethod(),
                        request.getURI(),
                        statusCode
                );
                if (statusCode > 299) {
                    log.warn(logMessage);
                } else {
                    log.info(logMessage);
                }
            }
        };
    }

    @Bean
    public RestOperations apiClient() {
        var restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(loggingInterceptor());
        return restTemplate;
    }
}
