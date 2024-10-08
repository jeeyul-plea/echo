package kr.plea.redismodule.common.exception;

import lombok.Getter;

public class RootException extends RuntimeException {

    @Getter
    private final String code;

    @Getter
    private Object data;

    public RootException(String code, String message) {
        super(message);
        this.code = code;
    }

    public RootException(String code, Object data) {
        this.code = code;
        this.data = data;
    }
}
