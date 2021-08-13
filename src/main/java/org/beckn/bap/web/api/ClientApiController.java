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
public class ClientApiController {

    @Autowired
    private BapApplicationService bapApplicationService;

    @PostMapping(ClientRoutes.SEARCH_API)
    public ResponseEntity search(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.search(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ClientRoutes.SELECT_API)
    public ResponseEntity select(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSelectRequest request) {
        var response = bapApplicationService.select(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ClientRoutes.INIT_ORDER_API)
    public ResponseEntity initOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientInitRequest request) {
        var response = bapApplicationService.init(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ClientRoutes.CONFIRM_ORDER_API)
    public ResponseEntity confirmOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientConfirmUpdateOrderRequest request) {
        var response = bapApplicationService.confirmOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ClientRoutes.ORDER_STATUS_API)
    public ResponseEntity orderStatus(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.orderStatus(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ClientRoutes.TRACK_ORDER_API)
    public ResponseEntity trackOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.trackOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ClientRoutes.CANCEL_ORDER_API)
    public ResponseEntity cancelOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.cancelOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ClientRoutes.UPDATE_ORDER_API)
    public ResponseEntity updateOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientConfirmUpdateOrderRequest request) {
        var response = bapApplicationService.updateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ClientRoutes.RATE_ORDER_API)
    public ResponseEntity rateOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientRatingRequest request) {
        var response = bapApplicationService.rateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ClientRoutes.SUPPORT_API)
    public ResponseEntity support(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSupportRequest request) {
        var response = bapApplicationService.support(request, headers);
        return ResponseEntity.ok(response);
    }

    @GetMapping(ClientRoutes.SEARCH_BY_MSG_ID_API)
    public ResponseEntity searchByMessageId(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.getSearchData(messageId);
        return ResponseEntity.ok(data);
    }
}
