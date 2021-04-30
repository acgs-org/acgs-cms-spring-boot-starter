<h1>acgs-cms-core</h1>

基于SpringBoot技术的动态构建CMS后端核心

## 简介

acgs 项目全程 Automatic content generate system. 单看名字你会认为它是一个代码生成器,虽然它确实有代码自动生成的功能,但这并非是它的全部面貌.

#### acgs 项目到底是什么

acgs项目是“交互式编程”思想的具体实现.意在实现一种交互式的业务处理方式.使用该技术手段可以使得众多非软件技术相关人员完成一系列的管理操作.将传统项目的具体实现交给用户去完成.

#### acgs 能做什么

你可以使用acgs项目搭建一个项目平台,然后将该平台直接递交给用户,由用户去实现具体的业务以及细节填充.

这并不是偷懒,而是一种交互式的编程思想.即人与机器间通过简单互动来实现复杂的业务逻辑,而不必依靠相关技术人员.

当然,你也可以将 acgs 当作一个工具包,通过它快速构建出你需要的项目,然后交付给用户.

也就是说, acgs 是一个具体实现,也是一个面向业务层的开发框架,具体要如何使用,由你来决定

#### acgs 的优点

1. 通过简单的3步操作即可完成,简单高效
2. “交互式编程”使得项目具有高度的自由化,个性化.

## 快速开始

1. 导入 maven 依赖

```html
<dependency>
    <groupId>org.acgs</groupId>
    <artifactId>acgs-cms-spring-boot-starter</artifactId>
    <version>0.1.0</version>
</dependency>
```

2. 添加相应的配置参数

```
acgs:
  build:
    basePath: org.acgs.cms.moudle
    driver: mongo
    buildAll: true
```

3. 继承接口

```java
@AcgsBuild
public class TestController extends BuilderController {}
```

4. 测试

```js
POST http://localhost:5000/acgs
Content-Type: application/json

{
  "id": 1,
  "name": "sku",
  "values": {
    "id": "Long",
    "name": "String",
    "price": "Double",
    "title": "String"
  },
  "methods": ["GET", "POST"]
}

###
```

```js
POST http://localhost:5000/v1/sku/add
Content-Type: application/json

{
  "id": 1,
  "name": "新华字典",
  "price": 200.4,
  "title": "没啥说的"
}
```

## 项目演示

#### acgs 后端项目

- [基于 SpringBoot 的后端项目](https://github.com/acgs-org/acgs-cms-spring-boot)

#### acgs 前端项目

- [基于 Vue 的前端项目](https://github.com/acgs-org/acgs-cms-vue)

## 联系与交流

- Tierney John
  + 邮箱: <tierney-john@hotmail.com>
  + 微信: 13470021561
- 张明翔
- 李昊
