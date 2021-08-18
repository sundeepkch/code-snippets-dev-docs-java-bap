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
public class MobilityApiController {

    @Autowired
    private BapApplicationService bapApplicationService;

    @PostMapping("/mobility/search_by_pickup_location")
    public ResponseEntity searchByPickupLocation(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/search_by_pickup_drop_location")
    public ResponseEntity searchByPickupDropLocation(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/view_by_fare")
    public ResponseEntity viewByFare(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/view_by_provider")
    public ResponseEntity viewByProvider(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/calculate_fare")
    public ResponseEntity calculateFare(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/apply_offer")
    public ResponseEntity applyOffer(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSelectRequest request) {
        var response = bapApplicationService.generateSelectRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/select_mobility")
    public ResponseEntity selectMobility(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSelectRequest request) {
        var response = bapApplicationService.generateSelectRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/add_billing")
    public ResponseEntity addBillingDetails(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientInitRequest request) {
        var response = bapApplicationService.initializeOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/confirm_order")
    public ResponseEntity confirmOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientConfirmOrderRequest request) {
        var response = bapApplicationService.confirmOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/order_status")
    public ResponseEntity orderStatus(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.orderStatus(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/cancel_order")
    public ResponseEntity cancelOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.cancelOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/track_order")
    public ResponseEntity trackOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.trackOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/rate_driver")
    public ResponseEntity rateDriver(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientRatingRequest request) {
        var response = bapApplicationService.rateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobility/contact_provider")
    public ResponseEntity contactProvider(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSupportRequest request) {
        var response = bapApplicationService.support(request, headers);
        return ResponseEntity.ok(response);
    }

    // Endpoint for the client to poll the search data based on the message id
    @GetMapping("/mobility/on_search")
    public ResponseEntity searchByMessageId(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the select data based on the message id
    @GetMapping("/mobility/on_select")
    public ResponseEntity selectByMessageId(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the init data based on the message id
    @GetMapping("/mobility/on_init")
    public ResponseEntity initByMessageId(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the confirmed order based on the message id
    @GetMapping("/mobility/on_confirm")
    public ResponseEntity onConfirm(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the order status based on the message id
    @GetMapping("/mobility/on_order_status")
    public ResponseEntity onOrderStatus(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the cancelled order based on the message id
    @GetMapping("/mobility/on_cancel_order")
    public ResponseEntity onCancelOrder(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the order tracking based on the message id
    @GetMapping("/mobility/on_track_order")
    public ResponseEntity onTrackOrder(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the acknowledgement for the rating
    @GetMapping("/mobility/rate")
    public ResponseEntity rate(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll to get the support information
    @GetMapping("/mobility/on_get_support")
    public ResponseEntity onGetSupport(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }
}
