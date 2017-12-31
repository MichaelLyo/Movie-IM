# Movie
一个基于Amazon网站中电影信息的movie处理网站

## 环境
- Java 1.8
- Spring Boot
- ORM: Hibernate
- Database: Oracle/Timesten/HDFS+HIVE
- Maven包管理

### oracle环境注意
maven中没有提供oracle的jdbc可以直接引用，所以要手动添加，方法如下：
在命令行中cd到ojdbc6.jar所在的路径，并允许下面的这条命令 
```
 mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.1.0 -Dpackaging=jar -Dfile=ojdbc6.jar
```

