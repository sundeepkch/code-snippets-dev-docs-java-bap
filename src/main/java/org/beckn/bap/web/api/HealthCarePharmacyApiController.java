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
public class HealthCarePharmacyApiController {

    @Autowired
    private BapApplicationService bapApplicationService;

    @PostMapping("/healthcare_pharmacy/search_by_delivery_location")
    public ResponseEntity searchByDeliveryLocation(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/search_by_home_delivery")
    public ResponseEntity searchByHomeDelivery(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/search_by_store_pickup")
    public ResponseEntity searchByStorePickup(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/search_by_sku_code")
    public ResponseEntity searchBySkuCode(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/search_by_product_name")
    public ResponseEntity searchByProductName(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/search_by_product_price")
    public ResponseEntity searchByProductPrice(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/view_provider_catalog")
    public ResponseEntity getProvideCatalog(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/search_store_name")
    public ResponseEntity searchByStoreName(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.generateSearchRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/add_items")
    public ResponseEntity addItems(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSelectRequest request) {
        var response = bapApplicationService.generateSelectRequest(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/add_billing")
    public ResponseEntity addBillingDetails(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientInitRequest request) {
        var response = bapApplicationService.initializeOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/confirm_prepaird_order")
    public ResponseEntity confirmPrepaidOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientConfirmOrderRequest request) {
        var response = bapApplicationService.confirmOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/confirm_postpaid_order")
    public ResponseEntity confirmPostpaidOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientConfirmOrderRequest request) {
        var response = bapApplicationService.confirmOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/order_status")
    public ResponseEntity orderStatus(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.orderStatus(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/track_order")
    public ResponseEntity trackOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.trackOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/update_billing_details")
    public ResponseEntity updateBillingDetails(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientUpdateOrderRequest request) {
        var response = bapApplicationService.updateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/update_quantity")
    public ResponseEntity updateItemQuantity(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientUpdateOrderRequest request) {
        var response = bapApplicationService.updateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/add_item")
    public ResponseEntity addItem(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientUpdateOrderRequest request) {
        var response = bapApplicationService.updateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/remove_item")
    public ResponseEntity removeItem(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientUpdateOrderRequest request) {
        var response = bapApplicationService.updateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/update_shipping_details")
    public ResponseEntity updateShippingDetails(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientUpdateOrderRequest request) {
        var response = bapApplicationService.updateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/cancel_order")
    public ResponseEntity cancelOrder(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientOrderRequest request) {
        var response = bapApplicationService.cancelOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/rate_delivery")
    public ResponseEntity rateDelivery(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientRatingRequest request) {
        var response = bapApplicationService.rateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/rate_pharmacy")
    public ResponseEntity ratePharmacy(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientRatingRequest request) {
        var response = bapApplicationService.rateOrder(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/contact_delivery")
    public ResponseEntity contactDelivery(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSupportRequest request) {
        var response = bapApplicationService.support(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/healthcare_pharmacy/contact_store")
    public ResponseEntity contactStore(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSupportRequest request) {
        var response = bapApplicationService.support(request, headers);
        return ResponseEntity.ok(response);
    }

    // Endpoint for the client to poll the search data based on the message id
    @GetMapping("/healthcare_pharmacy/on_search")
    public ResponseEntity searchByMessageId(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.getSearchData(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the selected order catalog based on the message id
    @GetMapping("/healthcare_pharmacy/on_select")
    public ResponseEntity getQuoteForItem(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.getQuotation(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the order billing data based on the message id
    @GetMapping("/healthcare_pharmacy/on_init")
    public ResponseEntity getInitializedOrder(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the confirmed order based on the message id
    @GetMapping("/healthcare_pharmacy/on_confirm")
    public ResponseEntity onConfirm(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the order status based on the message id
    @GetMapping("/healthcare_pharmacy/on_order_status")
    public ResponseEntity onOrderStatus(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the order tracking based on the message id
    @GetMapping("/healthcare_pharmacy/on_track_order")
    public ResponseEntity onTrackOrder(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the updated order details based on the message id
    @GetMapping("/healthcare_pharmacy/on_update_order")
    public ResponseEntity onUpdateOrder(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the cancelled order based on the message id
    @GetMapping("/healthcare_pharmacy/on_cancel_order")
    public ResponseEntity onCancelOrder(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll the acknowledgement for the rating
    @GetMapping("/healthcare_pharmacy/rate")
    public ResponseEntity rate(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

    // Endpoint for the client to poll to get the support information
    @GetMapping("/healthcare_pharmacy/on_get_support")
    public ResponseEntity onGetSupport(
            @PathVariable(ClientRoutes.PARAM_MESSAGE_ID) String messageId,
            @RequestHeader HttpHeaders headers) {
        var data = bapApplicationService.get(messageId);
        return ResponseEntity.ok(data);
    }

}
