package org.beckn.bap.common.exception;

import lombok.Getter;

@Getter
public class ServerErrorException extends RuntimeException {
    private final int status;

    public ServerErrorException(int status, String message) {
        super(message);
        this.status = status;
    }

    public ServerErrorException(String message) {
        this(500, message);
    }
}
