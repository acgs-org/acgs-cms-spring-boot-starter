package io.github.acgs.autoconfigure.interfaces;

/**
 * Response 返回方法接口
 * 必须全部实现
 *
 * @author John@acgs-org
 * create time 2021/4/26
 */
public interface BaseResponse {

    /**
     * @return 返回的信息
     */
    String getMessage();

    /**
     * @return http 状态码
     */
    int getHttpCode();

    /**
     * @return 错误码
     */
    int getCode();
}
