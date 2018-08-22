FROM maven:3.5.0-jdk-8-alpine AS builder

ADD ./pom.xml pom.xml
ADD ./src src/

# RUN mvn clean install && cp -f target/flying-demo-use-springboot.jar flying-demo-use-springboot.jar && rm -rf pom.xml src/ target/

RUN mvn clean install

From openjdk:8-jre-alpine

COPY --from=builder target/flying-demo-use-springboot.jar flying-demo-use-springboot.jar

# CMD ["java", "-jar", "flying-demo-use-springboot.jar"]
ENTRYPOINT ["java", "-jar", "flying-demo-use-springboot.jar", "--spring.datasource.password=${MYSQL_PASSWORD}"]