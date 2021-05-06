package com.github.acgs.autoconfigure.exception;

import com.github.acgs.autoconfigure.bean.Code;
import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * 令牌无效异常
 *
 * @author John@acgs-org
 * create time 2021/4/26
 */
public class TokenInvalidException extends HttpException {

    @Serial
    private static final long serialVersionUID = -7844470320210708005L;

    protected int code = Code.TOKEN_INVALID.getCode();

    protected int httpCode = HttpStatus.UNAUTHORIZED.value();

    public TokenInvalidException() {
        super(Code.TOKEN_INVALID.getCode(), Code.TOKEN_INVALID.getDescription());
        super.ifDefaultMessage = true;
    }

    public TokenInvalidException(String message) {
        super(message);
    }

    public TokenInvalidException(int code) {
        super(code, Code.TOKEN_INVALID.getDescription());
        this.code = code;
        super.ifDefaultMessage = true;
    }

    public TokenInvalidException(int code, String message) {
        super(code, message);
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getHttpCode() {
        return httpCode;
    }
}
