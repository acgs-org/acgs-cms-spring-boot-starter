package io.github.acgs.autoconfigure.exception;

import org.junit.jupiter.api.Test;

/**
 * @author John@acgs-org
 * create time 2021/4/29
 */
class BuilderExceptionTest {

    @Test
    public void exceptionTest() {
        try {
            throw new BuilderException("构建异常");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

}