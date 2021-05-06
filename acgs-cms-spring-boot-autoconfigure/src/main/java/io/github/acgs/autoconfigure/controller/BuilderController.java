package io.github.acgs.autoconfigure.controller;

import io.github.acgs.autoconfigure.annotation.AcgsBuild;
import io.github.acgs.autoconfigure.bean.Entity;
import io.github.acgs.autoconfigure.bean.TemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 自动构建方法入口
 * 继承该接口 以实现自动构建操作
 *
 * @author John@acgs-org
 * create time 2021/4/30
 */
@AcgsBuild
public abstract class BuilderController {

    @Autowired
    private TemplateBuilder builder;

    @PostMapping("")
    public void build(@RequestBody Entity entity) {
        builder.build(entity);
    }
}
