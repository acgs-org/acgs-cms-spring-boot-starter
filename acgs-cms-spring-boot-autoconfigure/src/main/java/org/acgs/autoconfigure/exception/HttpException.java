package org.acgs.autoconfigure.exception;

import org.acgs.autoconfigure.bean.Code;
import org.acgs.autoconfigure.interfaces.BaseResponse;
import org.springframework.http.HttpStatus;

/**
 * Http 相关异常类
 *
 * @author John@acgs-org
 * create time 2021/4/26
 */
public class HttpException extends RuntimeException implements BaseResponse {

    private static final long serialVersionUID = 2359767895161832954L;

    protected int httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    protected int code = Code.INTERNAL_SERVER_ERROR.getCode();

    /**
     * 是否是默认消息
     * true: 没有通过构造函数传入 message
     * false: 通过构造参数传入 message
     */
    protected boolean ifDefaultMessage = true;

    public HttpException() {
        super(Code.INTERNAL_SERVER_ERROR.getDescription());
    }

    public HttpException(String message) {
        super(message);
        this.ifDefaultMessage = false;
    }

    public HttpException(int code) {
        super(Code.INTERNAL_SERVER_ERROR.getDescription());
        this.code = code;
    }

    public HttpException(int code, int httpCode) {
        super(Code.INTERNAL_SERVER_ERROR.getDescription());
        this.httpCode = httpCode;
        this.code = code;
    }

    public HttpException(int code, String message) {
        super(message);
        this.code = code;
        this.ifDefaultMessage = false;
    }

    public HttpException(int code, String message, int httpCode) {
        super(message);
        this.httpCode = httpCode;
        this.code = code;
        this.ifDefaultMessage = false;
    }

    public HttpException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public HttpException(Throwable cause, int code, int httpCode) {
        super(cause);
        this.httpCode = httpCode;
        this.code = code;
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
        this.ifDefaultMessage = false;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

    @Override
    public int getHttpCode() {
        return this.httpCode;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    public boolean ifDefaultMessage() {
        return this.ifDefaultMessage;
    }
}
