package org.beckn.bap.web.api;

import lombok.extern.slf4j.Slf4j;
import org.beckn.bap.dto.client.ClientSearchRequest;
import org.beckn.bap.service.BapApplicationService;
import org.beckn.bap.web.api.common.ClientRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.beckn.bap.common.Constants.DOMAIN_LOCAL_RETAIL;

@Slf4j
@RestController
public class LocalRetailClientApiController {

    @Autowired
    private BapApplicationService bapApplicationService;

    @PostMapping(ClientRoutes.SEARCH_API)
    public ResponseEntity search(
            @RequestHeader HttpHeaders headers,
            @RequestBody ClientSearchRequest request) {
        request.setDomain(DOMAIN_LOCAL_RETAIL);
        var response = bapApplicationService.search(request, headers);
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
