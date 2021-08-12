package org.beckn.bap.web.api;

import lombok.extern.slf4j.Slf4j;
import org.beckn.bap.dto.bap.OnSearchRequest;
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
        var response = bapCallbackApplicationService.OnSearch(request, headers);
        return ResponseEntity.ok(response);
    }

}
