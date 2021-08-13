package org.beckn.bap.web.api;

import lombok.extern.slf4j.Slf4j;
import org.beckn.bap.dto.*;
import org.beckn.bap.service.BapCallbackApplicationService;
import org.beckn.bap.web.api.common.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BecknBapApiController {

    @Autowired
    private BapCallbackApplicationService bapCallbackApplicationService;

    @PostMapping(Routes.ON_SEARCH_API)
    public ResponseEntity onSearch(
            @RequestHeader HttpHeaders headers,
            @RequestBody OnSearchRequest request) {
        var response = bapCallbackApplicationService.onSearch(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.ON_SELECT_API)
    public ResponseEntity onSelect(
            @RequestHeader HttpHeaders headers,
            @RequestBody OnSelectRequest request) {
        var response = bapCallbackApplicationService.onSelect(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.ON_INIT_API)
    public ResponseEntity onInit(
            @RequestHeader HttpHeaders headers,
            @RequestBody OnInitRequest request) {
        var response = bapCallbackApplicationService.onInit(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.ON_CONFIRM_API)
    public ResponseEntity onConfirm(
            @RequestHeader HttpHeaders headers,
            @RequestBody OnConfirmRequest request) {
        var response = bapCallbackApplicationService.onConfirm(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.ON_STATUS_API)
    public ResponseEntity onStatus(
            @RequestHeader HttpHeaders headers,
            @RequestBody OnStatusRequest request) {
        var response = bapCallbackApplicationService.onStatus(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.ON_TRACK_API)
    public ResponseEntity onTrack(
            @RequestHeader HttpHeaders headers,
            @RequestBody OnTrackRequest request) {
        var response = bapCallbackApplicationService.onTrack(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.ON_CANCEL_API)
    public ResponseEntity onCancel(
            @RequestHeader HttpHeaders headers,
            @RequestBody OnCancelRequest request) {
        var response = bapCallbackApplicationService.onCancel(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.ON_UPDATE_API)
    public ResponseEntity onUpdate(
            @RequestHeader HttpHeaders headers,
            @RequestBody OnUpdateRequest request) {
        var response = bapCallbackApplicationService.onUpdate(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.ON_RATING_API)
    public ResponseEntity onRating(
            @RequestHeader HttpHeaders headers,
            @RequestBody OnRatingRequest request) {
        var response = bapCallbackApplicationService.onRating(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.ON_SUPPORT_API)
    public ResponseEntity onSupport(
            @RequestHeader HttpHeaders headers,
            @RequestBody OnSupportRequest request) {
        var response = bapCallbackApplicationService.onSupport(request, headers);
        return ResponseEntity.ok(response);
    }
}
