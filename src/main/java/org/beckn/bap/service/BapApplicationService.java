package org.beckn.bap.service;

import org.beckn.bap.common.RestApiClient;
import org.beckn.bap.dto.bap.*;
import org.beckn.bap.dto.client.ClientResponse;
import org.beckn.bap.dto.client.ClientSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BapApplicationService {

    @Autowired
    private RestApiClient apiClient;

    public ClientResponse search(ClientSearchRequest request, HttpHeaders headers) {
        var messageId = UUID.randomUUID().toString();

        // Check and create transaction id if not passed
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the request
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.search)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();
        var intentBuilder = Intent.builder();

        //construct fulfilment
        intentBuilder = intentBuilder.fulfillment(Fulfillment.builder()
                .type(request.getType())
                .start(FulfillmentStart.builder()
                        .location(Location.builder()
                                .gps(request.getEndLocation())
                                .build())
                        .build())
                .end(FulfillmentStart.builder()
                        .location(Location.builder()
                                .gps(request.getEndLocation())
                                .build())
                        .build()).build());
        //construct item
        intentBuilder = intentBuilder.item(Item.builder()
                .id(request.getItemId())
                .descriptor(Descriptor.builder()
                        .name(request.getItemName())
                        .code(request.getItemCode())
                        .build())
                .price(Price.builder()
                        .currency("INR") //Read currency from network
                        .minimumValue(request.getMinPrice())
                        .maximumValue(request.getMaxPrice())
                        .build())
                .build());

        //construct provider
        intentBuilder = intentBuilder.provider(Provider.builder()
                .id(request.getSellerId())
                .rating(request.getRating())
                .descriptor(Descriptor.builder()
                        .name(request.getSellerName())
                        .build())
                .build());

        var searchRequest = SearchRequest.builder()
                .context(context)
                .message(SearchMessage.builder()
                        .intent(intentBuilder.build()).build()).build();

        System.out.println(searchRequest);

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
        return ClientResponse.of(messageId, txnId);
    }

    /**
     * Method to fetch the data from DB based on the message id
     * @param messageId Message id of the search response
     * @return search response
     */
    public OnSearchRequest getSearchData(String messageId) {
        //TODO logic to fetch the search response based on the message id goes here.
        return new OnSearchRequest();
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
