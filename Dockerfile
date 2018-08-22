FROM dojomadness/alpine-jdk8-maven:latest

ADD ./pom.xml pom.xml
ADD ./src src/

RUN mvn clean install && cp -f target/flying-demo-use-springboot.jar flying-demo-use-springboot.jar && rm -rf pom.xml src/ target/

CMD ["java", "-jar", "flying-demo-use-springboot.jar"]