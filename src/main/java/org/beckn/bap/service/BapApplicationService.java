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
                constructRequestHeaders(),
                searchRequest,
                Response.class);
        if (searchResponse.getBody() == null || searchResponse.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse searchByItem(ClientSearchRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.search)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var intentBuilder = Intent.builder();

        // Construct the item object for the request from the item id or item code or item name
        // or available timings of the item or the price range of the item
        intentBuilder = intentBuilder.item(ClientAssembler.of(request.getItem()));

        // Construct the protocol specific object to be passed to the BPP/BG
        var searchRequest = SearchRequest.builder()
                .context(context)
                .message(SearchMessage.builder()
                        .intent(intentBuilder.build()).build()).build();

        // Call to look up function which returns the the public key and BPP/BG Endpoint to be called
        var url = lookUp(headers);

        // Call BPP/BG Search api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.search,
                constructRequestHeaders(),
                searchRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse searchBySeller(ClientSearchRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.search)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var intentBuilder = Intent.builder();

        // Construct provider object for the request from seller id, name or rating
        intentBuilder = intentBuilder.provider(ClientAssembler.of(request.getProvider()));

        // Construct the protocol specific object to be passed to the BPP/BG
        var searchRequest = SearchRequest.builder()
                .context(context)
                .message(SearchMessage.builder()
                        .intent(intentBuilder.build()).build()).build();

        // Call to look up function which returns the the public key and BPP/BG Endpoint to be called
        var url = lookUp(headers);

        // Call BPP/BG Search api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.search,
                constructRequestHeaders(),
                searchRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse searchByPickupDrop(ClientSearchRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.search)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var intentBuilder = Intent.builder();

        // Construct fulfilment object for the request from the start location gps and end location gps
        intentBuilder = intentBuilder.fulfillment(ClientAssembler.of(request.getFulfilment()));

        // Construct the protocol specific object to be passed to the BPP/BG
        var searchRequest = SearchRequest.builder()
                .context(context)
                .message(SearchMessage.builder()
                        .intent(intentBuilder.build()).build()).build();

        // Call to look up function which returns the the public key and BPP/BG Endpoint to be called
        var url = lookUp(headers);

        // Call BPP/BG Search api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.search,
                constructRequestHeaders(),
                searchRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse searchByDropLocation(ClientSearchRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.search)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        var intentBuilder = Intent.builder();

        // Construct fulfilment object for the request from the drop location gps
        intentBuilder = intentBuilder.fulfillment(ClientAssembler.of(request.getFulfilment()));

        // Construct the protocol specific object to be passed to the BPP/BG
        var searchRequest = SearchRequest.builder()
                .context(context)
                .message(SearchMessage.builder()
                        .intent(intentBuilder.build()).build()).build();

        // Call to look up function which returns the the public key and BPP/BG Endpoint to be called
        var url = lookUp(headers);

        // Call BPP/BG Search api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.search,
                constructRequestHeaders(),
                searchRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
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
                constructRequestHeaders(),
                selectRequest,
                Response.class);
        if (searchResponse.getBody() == null || searchResponse.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse selectAgency(ClientSelectRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.select)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP/BG
        // Select mobility option from the catalog to get a draft quote
        var selectRequest = SelectRequest.builder()
                .context(context)
                .message(Message.builder().order(Order.builder()
                        .items(ClientAssembler.of(request.getItems()))
                        .build()).build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Select api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.select,
                constructRequestHeaders(),
                selectRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse addSelectedItems(ClientSelectRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.select)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP/BG
        // Select items from the catalog to get a draft quote
        var selectRequest = SelectRequest.builder()
                .context(context)
                .message(Message.builder().order(Order.builder()
                        .items(ClientAssembler.of(request.getItems()))
                        .build()).build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Select api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.select,
                constructRequestHeaders(),
                selectRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
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
                constructRequestHeaders(),
                initRequest,
                Response.class);
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse addBillingDetails(ClientInitRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.init)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP
        // to initialize the order by providing the billing details
        var billing = ClientAssembler.of(request.getBilling());
        var initRequest = InitRequest.builder()
                .context(context)
                .message(Message.builder().order(Order.builder()
                        .items(ClientAssembler.of(request.getItems()))
                        .billing(billing)
                        .build()).build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Init api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.init,
                constructRequestHeaders(),
                initRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse addPickupLocation(ClientInitRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.init)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP
        // to initialize the order by providing the fulfilment details
        // with pickup location
        var fulfilment = ClientAssembler.of(request.getFulfilment());
        var initRequest = InitRequest.builder()
                .context(context)
                .message(Message.builder().order(Order.builder()
                        .fulfillment(fulfilment)
                        .build()).build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Init api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.init,
                constructRequestHeaders(),
                initRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse addFulfilmentDetails(ClientInitRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.init)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP
        // to initialize the order by providing the fulfilment details
        var fulfilment = ClientAssembler.of(request.getFulfilment());
        var initRequest = InitRequest.builder()
                .context(context)
                .message(Message.builder().order(Order.builder()
                        .fulfillment(fulfilment)
                        .build()).build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Init api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.init,
                constructRequestHeaders(),
                initRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse addOrgDetails(ClientInitRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.init)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP
        // to initialize the order by providing the billing details with the organization data
        var billing = ClientAssembler.of(request.getBilling());
        var initRequest = InitRequest.builder()
                .context(context)
                .message(Message.builder().order(Order.builder()
                        .items(ClientAssembler.of(request.getItems()))
                        .billing(billing)
                        .build()).build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Init api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.init,
                constructRequestHeaders(),
                initRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse initializeOrder(ClientInitRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.init)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP
        // to initialize the order with full terms for the chosen mobility option
        var items = ClientAssembler.of(request.getItems());
        var billingDetails = ClientAssembler.of(request.getBilling());
        var fulfilmentDetails = ClientAssembler.of(request.getFulfilment());
        var initRequest = InitRequest.builder()
                .context(context)
                .message(Message.builder().order(Order.builder()
                        .items(items)
                        .billing(billingDetails)
                        .fulfillment(fulfilmentDetails)
                        .build()).build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Init api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.init,
                constructRequestHeaders(),
                initRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse confirmOrder(ClientConfirmOrderRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.confirm)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP
        // to confirm the order with payment details if the request is pay and confirm
        var items = ClientAssembler.of(request.getItems());
        var billing = ClientAssembler.of(request.getBilling());
        var fulfilmentDetails = ClientAssembler.of(request.getFulfilment());
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

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Confirm api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.confirm,
                constructRequestHeaders(),
                confirmOrderRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse orderStatus(ClientOrderRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.status)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP
        // to get the status of the given order id
        var statusRequest = StatusRequest.builder()
                .context(context)
                .message(StatusMessage.builder()
                        .orderId(request.getOrderId())
                        .build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Status api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.status,
                constructRequestHeaders(),
                statusRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse trackOrder(ClientOrderRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.track)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP
        // to track the active trip/order
        var trackRequest = TrackRequest.builder()
                .context(context)
                .message(StatusMessage.builder()
                        .orderId(request.getOrderId())
                        .callbackUrl(request.getTrackingCallbackUrl())
                        .build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Track api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.track,
                constructRequestHeaders(),
                trackRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse cancelOrder(ClientOrderRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.cancel)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP
        // to cancel the active trip/order
        // The valid cancellation reasons will be fetched by the BPP's /get_cancellation_reasons endpoint
        var cancelRequest = CancelRequest.builder()
                .context(context)
                .message(CancelMessage.builder()
                        .orderId(request.getOrderId())
                        .cancellationReasonId(request.getCancellationReason())
                        .build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Cancel api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.cancel,
                constructRequestHeaders(),
                cancelRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse updateOrder(ClientUpdateOrderRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.update)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP
        // to update the order with the correct billing details
        var billing = ClientAssembler.of(request.getBilling());
        var updateOrderRequest = UpdateRequest.builder()
                .context(context)
                .message(UpdateMessage.builder().order(Order.builder()
                        .id(request.getOrderId())
                        .billing(billing)
                        .build()).build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Update api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.update,
                constructRequestHeaders(),
                updateOrderRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse rateOrder(ClientRatingRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.rating)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP
        // to rate the order with the rating category received from the BPP's /get_rating_categories endpoint
        var ratingRequest = RatingRequest.builder()
                .context(context)
                .message(RatingMessage.builder()
                        .id(request.getOrderId())
                        .value(BigDecimal.valueOf(request.getRating()))
                        .build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Rating api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.rating,
                constructRequestHeaders(),
                ratingRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    public ClientResponse support(ClientSupportRequest request, HttpHeaders headers) {
        // Generate message id
        var messageId = UUID.randomUUID().toString();

        // Check if transaction id exists in the request.
        // Generate if not exists
        var txnId = StringUtils.hasText(request.getTransactionId())
                ? request.getTransactionId()
                : UUID.randomUUID().toString();

        // Construct the Context based on the request parameters
        var context = Context.builder().domain(request.getDomain())
                .action(Context.ActionEnum.support)
                .messageId(messageId)
                .transactionId(txnId)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(new Date().toString())
                .build();

        // Construct the protocol specific object to be passed to the BPP
        // to get the support contact with the id of the entity for which support is required
        var supportRequest = SupportRequest.builder()
                .context(context)
                .message(SupportMessage.builder()
                        .refId(request.getRefId())
                        .build())
                .build();

        // Call to look up function which returns the the public key and BPP Endpoint to be called
        var url = lookUp(headers);

        // Call BPP Support api from the returned endpoint.
        // Construct request headers with the public key
        var responseEntity = apiClient.post(url[0] + Context.ActionEnum.support,
                constructRequestHeaders(),
                supportRequest,
                Response.class);

        // Validate the received response
        if (responseEntity.getBody() == null || responseEntity.getBody().getError() != null) {
            // Return custom error to the client
            return null;
        }
        // Return the Message Id & Transaction Id to the Client for polling the response
        return ClientResponse.of(messageId, txnId);
    }

    /**
     * Method to fetch the data from DB based on the message id
     *
     * @param messageId Message id of the search response
     * @return search response
     */
    public OnSearchRequest getSearchData(String messageId) {
        // logic to fetch the search response based on the message id goes here.
        return new OnSearchRequest();
    }

    public OnSelectRequest getQuotation(String messageId) {
        // logic to fetch the search response based on the message id goes here.
        return new OnSelectRequest();
    }

    public OnInitRequest get(String messageId) {
        // logic to fetch the search response based on the message id goes here.
        return new OnInitRequest();
    }

    /**
     * Method to construct response headers to call BPP/BG
     *
     * @return HttpHeaders - needs to be returning after constructing based on the signature
     */
    private HttpHeaders constructRequestHeaders() {
        var headers = new HttpHeaders();
        var authHeader = "Bearer TEST"; // Construct the auth header for response
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
