# java-china

Java中国是使用Blade框架搭建的论坛系统，致力于打造一个简洁优质的Java程序员论坛，支持@用户，github登录，emoji表情。

目前还在开发中，beta地址 [http://beta.java-china.org](http://java-china.org)

有任何问题可以发 [issues](https://github.com/junicorn/java-china/issues/new)

## 使用

1. 建立数据库java-china，编码为utf－8,导入 `java-china.sql`
2. 导入maven工程或者使用 `war:war` 命令打包一个war包
3. 启动tomcat，访问 http://127.0.0.1:8080/java-china

## 配置 [blade.properties]

- `app.site_url`：修改为你的网站地址
- `app.version`：当前版本，用户清除静态资源缓存
- `app.db_cahce`：是否开启数据库缓存(生产环境建议开启)
- `qiniu`：这部分修改为你的七牛空间配置
- `email`：这部分修改为你的邮箱配置，否则无法注册
- `db`：这部分为数据库配置，修改为你连接的数据库，用户，密码

## 预览图 

![alt](http://7xsk2r.com2.z0.glb.clouddn.com/QQ20160410-0.png)

