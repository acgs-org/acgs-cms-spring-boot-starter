package org.acgs.autoconfigure.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 通用实体类对象
 *
 * @author John@acgs-org
 * create time 2021/4/26
 */
@Data
public class Entity {

    /**
     * 实体类 id
     */
    private String id;

    /**
     * 实体类 name
     */
    private String name;

    /**
     * 实体类参数列表
     * 映射模式: {属性名称: 属性类型}
     * 字段示例: {"id": "Long", "name", "String"}
     */
    private Map<String, String> values;

    /**
     * 实体类 REST 接口实现，值应为 http 请求类型
     * 示例: ["GET", "POST", "PUT"]
     */
    private List<String> methods;

}
