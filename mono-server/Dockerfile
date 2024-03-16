# 基于OpenJDK构建Spring Boot应用的Docker镜像
FROM openjdk:11-jre-slim

# 将目标jar包复制到容器中
COPY ./modules/app/target/monoserver-app-20230926.0.jar /app.jar

# 指定容器启动时执行的命令
ENTRYPOINT ["java","-jar","/app.jar"]
