package org.beckn.bap.assembler;

import lombok.experimental.UtilityClass;
import org.beckn.bap.dto.*;
import org.beckn.bap.web.api.client.ClientAssembler;
import org.beckn.bap.web.api.client.ClientSearchRequest;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

@UtilityClass
public class BapAssembler {

    public SearchRequest generateSearchRequest(ClientSearchRequest request) {

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

        return SearchRequest.builder()
                .context(context)
                .message(SearchMessage.builder()
                        .intent(intentBuilder.build()).build()).build();

    }
}
