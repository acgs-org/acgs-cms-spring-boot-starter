package com.github.acgs.autoconfigure.exception;

import com.github.acgs.autoconfigure.bean.Code;

/**
 * 自动构建相关异常类
 *
 * @author John@acgs-org
 * create time 2021/4/29
 */
public class BuilderException extends RuntimeException {

    private int code = Code.BUILDER_ERROR.getCode();

    public BuilderException() {
        super(Code.BUILDER_ERROR.getDescription());
    }

    public BuilderException(String message) {
        super(message);
    }

    public BuilderException(int code) {
        super(Code.BUILDER_ERROR.getDescription());
        this.code = code;
    }
}
