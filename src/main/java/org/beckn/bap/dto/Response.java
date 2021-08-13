package org.beckn.bap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private ResponseMessage message;
    private Error error;

    public static Response of(String ackNack, Error error) {
        var response = new Response();
        var status = Ack.AckNackStatusEnum.lookup(ackNack);
        if (status == Ack.AckNackStatusEnum.NACK) {
            response.setError(error);
        }
        response.setMessage(new ResponseMessage(new Ack(status)));
        return response;
    }
}
