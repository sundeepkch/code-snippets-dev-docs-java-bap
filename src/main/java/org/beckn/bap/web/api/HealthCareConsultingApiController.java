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
public class HealthCareConsultingApiController {

    @Autowired
    private BapApplicationService bapApplicationService;

    @PostMapping("/healthcare_consultation/search_by_department")
    public ResponseEntity searchByDepartment(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/search_by_symptom")
    public ResponseEntity searchBySymptom(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/search_by_doctor")
    public ResponseEntity searchByDoctor(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/search_by_clinic")
    public ResponseEntity searchByClinic(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/search_by_time")
    public ResponseEntity searchByTimePeriod(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/search_by_location")
    public ResponseEntity searchByLocation(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/select_consultation")
    public ResponseEntity selectConsultation(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSelectRequest request) {
        var response = bapApplicationService.generateSelectRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/add_billing")
    public ResponseEntity addBillingDetails(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientInitRequest request) {
        var response = bapApplicationService.initializeOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/confirm_prepaird_order")
    public ResponseEntity confirmPrepaidOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientConfirmOrderRequest request) {
        var response = bapApplicationService.confirmOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/confirm_postpaid_order")
    public ResponseEntity confirmPostpaidOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientConfirmOrderRequest request) {
        var response = bapApplicationService.confirmOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/order_status")
    public ResponseEntity orderStatus(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.orderStatus(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/update_billing_details")
    public ResponseEntity updateBillingDetails(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientUpdateOrderRequest request) {
        var response = bapApplicationService.updateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/reschedule_appointment")
    public ResponseEntity rescheduleAppointment(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientUpdateOrderRequest request) {
        var response = bapApplicationService.updateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/cancel_appointment")
    public ResponseEntity cancelAppointment(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.cancelOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/rate_doctor")
    public ResponseEntity rateDoctor(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientRatingRequest request) {
        var response = bapApplicationService.rateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/rate_clinic")
    public ResponseEntity rateClinic(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientRatingRequest request) {
        var response = bapApplicationService.rateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_consultation/contact_clinic")
    public ResponseEntity contactClinic(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSupportRequest request) {
        var response = bapApplicationService.support(request, headers);
        return ResponseEntity.ok(response);
    }

    // Endpoint for the client to poll the search data based on the message id
    @GetMapping("/healthcare_consultation/on_search")
    public ResponseEntity searchByMessageId(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.getSearchData(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the select data based on the message id
    @GetMapping("/healthcare_consultation/on_select")
    public ResponseEntity selectByMessageId(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the init data based on the message id
    @GetMapping("/healthcare_consultation/on_init")
    public ResponseEntity initByMessageId(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the confirmed order based on the message id
    @GetMapping("/healthcare_consultation/on_confirm")
    public ResponseEntity onConfirm(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the order status based on the message id
    @GetMapping("/healthcare_consultation/on_order_status")
    public ResponseEntity onOrderStatus(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the order updateing based on the message id
    @GetMapping("/healthcare_consultation/on_update_order")
    public ResponseEntity onUpdateOrder(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the cancelled order based on the message id
    @GetMapping("/healthcare_consultation/on_cancel_order")
    public ResponseEntity onCancelOrder(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the acknowledgement for the rating
    @GetMapping("/healthcare_consultation/rate")
    public ResponseEntity rate(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll to get the support information
    @GetMapping("/healthcare_consultation/on_get_support")
    public ResponseEntity onGetSupport(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }
}
