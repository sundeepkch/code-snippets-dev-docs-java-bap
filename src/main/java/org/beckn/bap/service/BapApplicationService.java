package org.beckn.bap.service;

import org.beckn.bap.common.RestApiClient;
import org.beckn.bap.dto.bap.*;
import org.beckn.bap.dto.client.ClientSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BapApplicationService {

    @Autowired
    private RestApiClient apiClient;

    public String searchByDropLocation(ClientSearchRequest request, HttpHeaders headers) {
        var messageId = UUID.randomUUID().toString();
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.search)
                .messageId(messageId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();
        var intent = Intent.builder().fulfillment(
                Fulfillment.builder().type("home-delivery")
                        .end(FulfillmentStart.builder()
                                .location(Location.builder()
                                        .gps(request.getEndLocation())
                                        .build())
                                .build())
                        .build())
                .build();

        var searchRequest = SearchRequest.builder()
                .context(context)
                .message(SearchMessage.builder()
                        .intent(intent).build()).build();

        var url = lookUp(headers);
        //Call BPP Search api
        var searchResponse = apiClient.post(url[0] + Context.ActionEnum.search,
                constructResponseHeaders(),
                searchRequest,
                Response.class);
        if (searchResponse.getBody() == null || searchResponse.getBody().getError() != null) {
            //TODO Return custom error to the client
            return null;
        }
        return messageId;
    }

    /**
     * Method to construct response headers to call BPP/BG
     *
     * @return HttpHeaders - needs to be returning after constructing based on the signature
     */
    private HttpHeaders constructResponseHeaders() {
        var headers = new HttpHeaders();
        var authHeader = "Bearer TEST"; //TODO: Construct the auth header for response
        headers.put(HttpHeaders.AUTHORIZATION, List.of(authHeader));
        return headers;
    }

    /**
     * Function to lookup the Public Key & URI based in the Subscriber ID from Header
     *
     * @param headers Authorization Header from the request
     * @return Array with Callback URI & Public Key
     */
    private String[] lookUp(HttpHeaders headers) {
        var publicKey = "";
        var uri = "http://localhost:8080/bpp/";
        return new String[]{uri, publicKey};
    }

}
