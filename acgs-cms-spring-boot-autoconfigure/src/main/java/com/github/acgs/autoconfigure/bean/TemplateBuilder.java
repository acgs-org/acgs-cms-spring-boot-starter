package com.github.acgs.autoconfigure.bean;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.github.acgs.autoconfigure.exception.BuilderException;
import com.github.acgs.autoconfigure.util.TemplateUtil;
import com.github.acgs.core.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

import static com.github.acgs.core.constant.BuilderConstant.COMMON_PATH;
import static com.github.acgs.core.constant.BuilderConstant.SEPARATOR;

/**
 * 通过模版自动构建代码
 *
 * @author John@acgs-org
 * create time 2021/4/29
 */
public class TemplateBuilder {

    private final String basePath;
    private final String driverType;
    private final boolean buildAll;
    private final Templates templates;

    /**
     * 实体映射
     */
    private Map<String, String> entityMap;

    public TemplateBuilder(String basePath, String driverType, boolean buildAll, Templates templates) {
        this.basePath = basePath;
        this.driverType = driverType;
        this.buildAll = buildAll;
        this.templates = templates;
    }

    public void build(Entity entity) {
        // 实体数据初步验证
        if (entity == null) {
            throw new BuilderException(Code.BUILDER_ENTITY_NOT_FOUND.getDescription());
        }
        if (entity.getValues().isEmpty()) {
            throw new BuilderException(Code.BUILDER_PARAMETER_ERROR.getDescription());
        }
        if (entity.getMethods().isEmpty()) {
            throw new BuilderException(Code.BUILDER_METHOD_ERROR.getDescription());
        }

        this.init(entity);

        switch (driverType) {
            case "mongo" -> this.mongoBuild();
            case "mybatis" -> this.mybatisBuild();
            default -> throw new BuilderException(Code.BUILDER_DRIVER_NOT_FOUND.getDescription());
        }
    }

    /**
     * 实体数据属性初始化方法
     *
     * @param entity 实体数据对象
     */
    private void init(Entity entity) {
        entityMap = new HashMap<>();
        entityMap.put("name", entity.getName());
        entityMap.put("upName", StringUtil.toUpper(entity.getName()));
        entityMap.put("entityItem", TemplateUtil.getEntityItem(entity.getValues()));
        entityMap.put("methodItem", TemplateUtil.getMethodItem(entity.getMethods(), entityMap));
        entityMap.put("packagePath", basePath);
        entityMap.put("filePath", COMMON_PATH + StringUtil.BaseToPath(basePath) + SEPARATOR
                + entityMap.get("name") + SEPARATOR + entityMap.get("upName"));
    }

    /**
     * MongoDB 构造模式
     */
    private void mongoBuild() {
        String filePath = entityMap.get("filePath");

        String entity = StrUtil.format(templates.getTemplate("entity"), entityMap);
        FileUtil.writeString(entity, filePath + ".java", "UTF-8");

        String repository = StrUtil.format(templates.getTemplate("mongo-repository"), entityMap);
        FileUtil.writeString(repository, filePath + "Repository.java", "UTF-8");

        if (buildAll) {
            String controller = StrUtil.format(templates.getTemplate("mongo-controller"), entityMap);
            FileUtil.writeString(controller, filePath + "Controller.java", "UTF-8");
        }

    }

    /**
     * Mybatis 构造模式
     */
    private void mybatisBuild() {

    }
}
