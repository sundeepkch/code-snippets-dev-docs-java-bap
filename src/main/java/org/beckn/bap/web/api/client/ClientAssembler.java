package org.beckn.bap.web.api.client;

import lombok.experimental.UtilityClass;
import org.beckn.bap.dto.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class ClientAssembler {

    public static Item of(ClientItem item) {
        if (item == null) return null;
        return Item.builder()
                .id(item.getItemId())
                .descriptor(Descriptor.builder()
                        .name(item.getItemName())
                        .code(item.getItemCode())
                        .build())
                .time(Time.builder()
                        .range(TimeRange.builder()
                                .start(item.getStartTime())
                                .end(item.getEndTime())
                                .build())
                        .build())
                .price(Price.builder()
                        .currency("INR") //Read currency from network
                        .minimumValue(item.getMinPrice())
                        .maximumValue(item.getMaxPrice())
                        .build())
                .tags((Tags) item.getTags())
                .build();
    }

    public static Fulfillment of(ClientFulfilment fulfilment) {
        if (fulfilment == null) return null;
        return Fulfillment.builder()
                .type(fulfilment.getType())
                .start(FulfillmentStart.builder()
                        .location(Location.builder()
                                .id(fulfilment.getStartLocationId())
                                .gps(fulfilment.getEndLocationGps())
                                .descriptor(Descriptor.builder()
                                        .name(fulfilment.getStartInstructionName())
                                        .shortDesc(fulfilment.getStartInstructionDesc())
                                        .build())
                                .build())
                        .build())
                .end(FulfillmentStart.builder()
                        .location(Location.builder()
                                .id(fulfilment.getEndLocationId())
                                .gps(fulfilment.getEndLocationGps())
                                .descriptor(Descriptor.builder()
                                        .name(fulfilment.getEndInstructionName())
                                        .shortDesc(fulfilment.getEndInstructionDesc())
                                        .build())
                                .build())
                        .build()).build();
    }

    public static Billing of(ClientBilling billing) {
        return Billing.builder()
                .name(billing.getName())
                .email(billing.getEmail())
                .phone(billing.getPhone())
                .address(Address.builder()
                        .door(billing.getAddressDoor())
                        .name(billing.getAddressName())
                        .locality(billing.getAddressLocality())
                        .city(billing.getAddressCity())
                        .state(billing.getAddressState())
                        .country(billing.getAddressCountry())
                        .areaCode(billing.getAddressAreaCode())
                        .build())
                .organization(Organization.builder()
                        .name(billing.getOrganization() == null ? null : billing.getOrganization().getName())
                        .cred(billing.getOrganization() == null ? null : billing.getOrganization().getCred())
                        .build())
                .build();
    }

    public static Quotation of(ClientQuote quote) {
        if (quote == null) return null;
        var quoteBreakup = quote.getBreakup().stream().map(q ->
                QuotationBreakup.builder()
                        .title(q.getTitle())
                        .price(Price.builder().currency(quote.getCurrency())
                                .value(q.getPrice()).build())
                        .build()).collect(toList());
        return Quotation.builder()
                .price(Price.builder()
                        .currency(quote.getCurrency())
                        .value(quote.getPrice())
                        .build())
                .breakup(quoteBreakup)
                .build();
    }

    public static Payment of(ClientPayment payment) {
        if (payment == null) return null;
        return Payment.builder()
                .uri(payment.getUri())
                .tlMethod(payment.getMethod())
                .params(PaymentParams.builder()
                        .amount(payment.getAmount())
                        .mode(payment.getMode())
                        .vpa(payment.getVpa())
                        .build())
                .type(payment.getType())
                .status(payment.getStatus())
                .build();

    }

    public static List<OrderItem> of(List<ClientSelectItemRequest> items) {
        if (CollectionUtils.isEmpty(items)) return null;
        var response = new ArrayList<OrderItem>();
        for (ClientSelectItemRequest itemRequest : items) {
            response.add(OrderItem.builder()
                    .id(itemRequest.getId())
                    .quantity(ItemQuantity.builder()
                            .selected(ItemQuantityAllocated.builder()
                                    .count(itemRequest.getQuantity())
                                    .build())
                            .build()).build());

        }
        return response;
    }

    public static Provider of(ClientProvider provider) {
        if(provider == null) return null;
        return Provider.builder()
                .id(provider.getProviderId())
                .descriptor(Descriptor.builder()
                        .name(provider.getProviderName())
                        .build())
                .rating(provider.getProviderRating())
                .build();
    }
}
