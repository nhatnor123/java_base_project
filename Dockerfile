FROM adoptopenjdk:11-jre-hotspot as builder
WORKDIR /application

ARG APM_JAVA_AGENT_VER
RUN curl -Lo apm.jar https://search.maven.org/remotecontent\?filepath\=co/elastic/apm/elastic-apm-agent/${APM_JAVA_AGENT_VER}/elastic-apm-agent-${APM_JAVA_AGENT_VER}.jar

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:11-jre-hotspot
ENV TZ=Asia/Ho_Chi_Minh
WORKDIR /application
COPY --from=builder /application/apm.jar ./apm.jar
COPY --from=builder /application/dependencies/ ./
COPY --from=builder /application/spring-boot-loader/ ./
COPY --from=builder /application/snapshot-dependencies/ ./
COPY --from=builder /application/application/ ./
ENTRYPOINT exec java -javaagent:apm.jar $JAVA_OPTS org.springframework.boot.loader.JarLauncher