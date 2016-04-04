# java-china

Java中国论坛，使用Blade框架开发，演示地址 http://java-china.org

## 使用

1. 建立数据库java-china，编码为utf－8,导入 `java-china.sql`
2. 导入maven工程或者使用 `war:war` 命令打包一个war包
3. 启动tomcat，访问 http://127.0.0.1:8080/java-china

## 配置 [blade.properties]

- `app.site_url`：修改为你的网站地址
- `qiniu`：这部分修改为你的七牛空间配置
- `email`：这部分修改为你的邮箱配置，否则无法注册
- `db`：这部分为数据库配置，修改为你连接的数据库，用户，密码

## 预览图 

![alt](http://7xsk2r.com2.z0.glb.clouddn.com/QQ20160404-0.png)

