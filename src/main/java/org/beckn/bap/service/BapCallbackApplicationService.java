package org.beckn.bap.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.beckn.bap.client.BapApiClient;
import org.beckn.bap.common.RestApiClient;
import org.beckn.bap.dto.bap.OnSearchRequest;
import org.beckn.bap.dto.bap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestOperations;

@Service
public class BapCallbackApplicationService {

    @Autowired
    private RestApiClient apiClient;

    @Autowired
    private BapApiClient bapClient;

    public BapCallbackApplicationService(RestOperations apiClient, ObjectMapper objectMapper) {
        this.apiClient = new RestApiClient(apiClient, objectMapper);
    }

    /**
     * Function that receives the response from BPP/BG.
     *
     * @param request The request body
     * @param headers The headers received from BAP which needs to be validated
     * @return The response with ACK-NACK
     */
    public Response OnSearch(OnSearchRequest request, HttpHeaders headers) {
        // Validate the headers
        var isHeadersValid = validateHeaders(headers);
        //TODO: Construct and return error
        if (!isHeadersValid) return null;

        // Store the data received based on message id for the client to poll
        saveToDB(request);

        return Response.of("ACK", null);
    }

    private boolean validateHeaders(HttpHeaders headers) {
        //TODO: logic to validate the headers
        return !CollectionUtils.isEmpty(headers.get(HttpHeaders.AUTHORIZATION)) ||
                !CollectionUtils.isEmpty(headers.get(HttpHeaders.AUTHORIZATION));
    }

    /**
     * Method to save the received search response to DB.
     * To be retrieved later by the Client by Message ID.
     *
     * @param request Request object to be saved to DB
     */
    private void saveToDB(OnSearchRequest request) {

    }
}
