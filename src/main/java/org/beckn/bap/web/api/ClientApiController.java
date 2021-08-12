package org.beckn.bap.web.api;

import lombok.extern.slf4j.Slf4j;
import org.beckn.bap.dto.client.ClientSearchRequest;
import org.beckn.bap.service.BapApplicationService;
import org.beckn.bap.web.api.common.ClientRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ClientApiController {

    @Autowired
    private BapApplicationService bapApplicationService;

    @PostMapping(ClientRoutes.ON_SEARCH_BY_DROP_API)
    public ResponseEntity searchByDropLocation(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        var response = bapApplicationService.searchByDropLocation(request, headers);
        return ResponseEntity.ok(response);
    }
}
