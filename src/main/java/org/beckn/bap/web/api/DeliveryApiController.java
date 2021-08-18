package org.beckn.bap.web.api;

import lombok.extern.slf4j.Slf4j;
import org.beckn.bap.service.BapApplicationService;
import org.beckn.bap.web.api.client.*;
import org.beckn.bap.web.api.common.ClientRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class DeliveryApiController {

    @Autowired
    private BapApplicationService bapApplicationService;

    @PostMapping("/delivery/search_by_pickup_and_drop_location")
    public ResponseEntity searchByPickupDrop(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/search_by_available_timings")
    public ResponseEntity searchByAvailableTimings(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/search_by_price_range")
    public ResponseEntity searchByPriceRange(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/add_service")
    public ResponseEntity addService(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSelectRequest request) {
        var response = bapApplicationService.generateSelectRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/add_billing")
    public ResponseEntity addBillingDetails(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientInitRequest request) {
        var response = bapApplicationService.initializeOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/confirm_prepaird_order")
    public ResponseEntity confirmPrepaidOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientConfirmOrderRequest request) {
        var response = bapApplicationService.confirmOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/confirm_postpaid_order")
    public ResponseEntity confirmPostpaidOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientConfirmOrderRequest request) {
        var response = bapApplicationService.confirmOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/order_status")
    public ResponseEntity orderStatus(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.orderStatus(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/track_order")
    public ResponseEntity trackOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.trackOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/update_billing_details")
    public ResponseEntity updateBillingDetails(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientUpdateOrderRequest request) {
        var response = bapApplicationService.updateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/update_drop_location")
    public ResponseEntity updateDropLocation(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientUpdateOrderRequest request) {
        var response = bapApplicationService.updateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/rate_delivery")
    public ResponseEntity rateDelivery(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientRatingRequest request) {
        var response = bapApplicationService.rateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delivery/contact_delivery")
    public ResponseEntity contactDelivery(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSupportRequest request) {
        var response = bapApplicationService.support(request, headers);
        return ResponseEntity.ok(response);
    }

    // Endpoint for the client to poll the search data based on the message id
    @GetMapping("/delivery/on_search")
    public ResponseEntity searchByMessageId(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.getSearchData(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the select data based on the message id
    @GetMapping("/delivery/on_select")
    public ResponseEntity selectByMessageId(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the init data based on the message id
    @GetMapping("/delivery/on_init")
    public ResponseEntity initByMessageId(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the confirmed order based on the message id
    @GetMapping("/delivery/on_confirm")
    public ResponseEntity onConfirm(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the order status based on the message id
    @GetMapping("/delivery/on_order_status")
    public ResponseEntity onOrderStatus(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the order tracking based on the message id
    @GetMapping("/delivery/on_track_order")
    public ResponseEntity onTrackOrder(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the order updateing based on the message id
    @GetMapping("/delivery/on_update_order")
    public ResponseEntity onUpdateOrder(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the acknowledgement for the rating
    @GetMapping("/delivery/rate")
    public ResponseEntity rate(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll to get the support information
    @GetMapping("/delivery/on_get_support")
    public ResponseEntity onGetSupport(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }
}
