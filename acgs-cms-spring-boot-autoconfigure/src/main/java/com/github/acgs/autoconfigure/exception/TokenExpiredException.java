package com.github.acgs.autoconfigure.exception;

import com.github.acgs.autoconfigure.bean.Code;
import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * 令牌过期异常
 *
 * @author John@acgs-org
 * create time 2021/4/26
 */
public class TokenExpiredException extends HttpException {

    @Serial
    private static final long serialVersionUID = -562886931687729013L;

    protected int code = Code.TOKEN_EXPIRED.getCode();

    protected int httpCode = HttpStatus.UNAUTHORIZED.value();

    public TokenExpiredException() {
        super(Code.TOKEN_EXPIRED.getCode(), Code.TOKEN_EXPIRED.getDescription());
        super.ifDefaultMessage = true;
    }

    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException(int code) {
        super(code, Code.TOKEN_EXPIRED.getDescription());
        this.code = code;
        super.ifDefaultMessage = true;
    }

    public TokenExpiredException(int code, String message) {
        super(code, message);
        this.code = code;
    }

    @Override
    public int getHttpCode() {
        return httpCode;
    }

    @Override
    public int getCode() {
        return code;
    }
}
