package org.beckn.bap.service;

import org.beckn.bap.common.RestApiClient;
import org.beckn.bap.dto.*;
import org.beckn.bap.web.api.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
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
        intentBuilder = intentBuilder.fulfillment(ClientAssembler.of(request.getFulfilment()));
        //construct item
        intentBuilder = intentBuilder.item(ClientAssembler.of(request.getItem()));

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

    public ClientResponse select(ClientSelectRequest request, HttpHeaders headers) {
        var messageId = UUID.randomUUID().toString();
        // Check and create transaction id if not passed
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the request
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.select)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var selectRequest = SelectRequest.builder()
                .context(context)
                .message(Message.builder().order(Order.builder()
                        .items(ClientAssembler.of(request.getItems()))
                        .build()).build())
                .build();

        var url = lookUp(headers);
        //Call BPP select api
        var searchResponse = apiClient.post(url[0] + Context.ActionEnum.select,
                constructResponseHeaders(),
                selectRequest,
                Response.class);
        if (searchResponse.getBody() == null || searchResponse.getBody().getError() != null) {
            //TODO Return custom error to the client
            return null;
        }
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse init(ClientInitRequest request, HttpHeaders headers) {
        var messageId = UUID.randomUUID().toString();
        // Check and create transaction id if not passed
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the request
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.init)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var billing = ClientAssembler.of(request.getBilling());
        var fulfilment = ClientAssembler.of(request.getFulfilment());

        var initRequest = InitRequest.builder()
                .context(context)
                .message(Message.builder().order(Order.builder()
                        .items(ClientAssembler.of(request.getItems()))
                        .billing(billing)
                        .fulfillment(fulfilment)
                        .build()).build())
                .build();

        var url = lookUp(headers);
        //Call BPP select api
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.init,
                constructResponseHeaders(),
                initRequest,
                Response.class);
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            //TODO Return custom error to the client
            return null;
        }
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse confirmOrder(ClientConfirmUpdateOrderRequest request, HttpHeaders headers) {
        var messageId = UUID.randomUUID().toString();
        // Check and create transaction id if not passed
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the request
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.confirm)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var items = ClientAssembler.of(request.getItems());
        var billing = ClientAssembler.of(request.getBilling());
        var quotation = ClientAssembler.of(request.getQuote());
        var payment = ClientAssembler.of(request.getPayment());
        var confirmOrderRequest = ConfirmRequest.builder()
                .context(context)
                .message(Message.builder().order(Order.builder()
                        .billing(billing)
                        .items(items)
                        .quote(quotation)
                        .payment(payment)
                        .build()).build())
                .build();

        var url = lookUp(headers);
        //Call BPP select api
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.confirm,
                constructResponseHeaders(),
                confirmOrderRequest,
                Response.class);
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            //TODO Return custom error to the client
            return null;
        }
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse orderStatus(ClientOrderRequest request, HttpHeaders headers) {
        var messageId = UUID.randomUUID().toString();
        // Check and create transaction id if not passed
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the request
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.status)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var statusRequest = StatusRequest.builder()
                .context(context)
                .message(StatusMessage.builder().orderId(request.getOrderId()).build())
                .build();

        var url = lookUp(headers);
        //Call BPP select api
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.status,
                constructResponseHeaders(),
                statusRequest,
                Response.class);
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            //TODO Return custom error to the client
            return null;
        }
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse trackOrder(ClientOrderRequest request, HttpHeaders headers) {
        var messageId = UUID.randomUUID().toString();
        // Check and create transaction id if not passed
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the request
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.track)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var trackRequest = TrackRequest.builder()
                .context(context)
                .message(StatusMessage.builder()
                        .orderId(request.getOrderId())
                        .callbackUrl(request.getTrackingCallbackUrl())
                        .build())
                .build();

        var url = lookUp(headers);
        //Call BPP select api
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.track,
                constructResponseHeaders(),
                trackRequest,
                Response.class);
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            //TODO Return custom error to the client
            return null;
        }
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse cancelOrder(ClientOrderRequest request, HttpHeaders headers) {
        var messageId = UUID.randomUUID().toString();
        // Check and create transaction id if not passed
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the request
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.cancel)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var cancelRequest = CancelRequest.builder()
                .context(context)
                .message(CancelMessage.builder()
                        .orderId(request.getOrderId())
                        .cancellationReasonId(request.getCancellationReason())
                        .build())
                .build();

        var url = lookUp(headers);
        //Call BPP select api
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.cancel,
                constructResponseHeaders(),
                cancelRequest,
                Response.class);
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            //TODO Return custom error to the client
            return null;
        }
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse updateOrder(ClientConfirmUpdateOrderRequest request, HttpHeaders headers) {
        var messageId = UUID.randomUUID().toString();
        // Check and create transaction id if not passed
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the request
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.update)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var items = ClientAssembler.of(request.getItems());
        var billing = ClientAssembler.of(request.getBilling());
        var quotation = ClientAssembler.of(request.getQuote());
        var payment = ClientAssembler.of(request.getPayment());
        var updateOrderRequest = UpdateRequest.builder()
                .context(context)
                .message(UpdateMessage.builder().order(Order.builder()
                        .id(request.getOrderId())
                        .state(request.getOrderState())
                        .billing(billing)
                        .items(items)
                        .quote(quotation)
                        .payment(payment)
                        .build()).build())
                .build();

        var url = lookUp(headers);
        //Call BPP select api
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.update,
                constructResponseHeaders(),
                updateOrderRequest,
                Response.class);
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            //TODO Return custom error to the client
            return null;
        }
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse rateOrder(ClientRatingRequest request, HttpHeaders headers) {
        var messageId = UUID.randomUUID().toString();
        // Check and create transaction id if not passed
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the request
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.rating)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var ratingRequest = RatingRequest.builder()
                .context(context)
                .message(RatingMessage.builder()
                        .id(request.getOrderId())
                        .value(BigDecimal.valueOf(request.getRating()))
                        .build())
                .build();

        var url = lookUp(headers);
        //Call BPP select api
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.rating,
                constructResponseHeaders(),
                ratingRequest,
                Response.class);
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            //TODO Return custom error to the client
            return null;
        }
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse support(ClientSupportRequest request, HttpHeaders headers) {
        var messageId = UUID.randomUUID().toString();
        // Check and create transaction id if not passed
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the request
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.support)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var supportRequest = SupportRequest.builder()
                .context(context)
                .message(SupportMessage.builder()
                        .refId(request.getRefId())
                        .build())
                .build();

        var url = lookUp(headers);
        //Call BPP select api
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.support,
                constructResponseHeaders(),
                supportRequest,
                Response.class);
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            //TODO Return custom error to the client
            return null;
        }
        return ClientResponse.of(messageId, txnId);
    }

    /**
     * Method to fetch the data from DB based on the message id
     *
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
