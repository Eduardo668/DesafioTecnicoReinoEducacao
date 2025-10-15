# =============== BUILD ===============
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# cache de dependências
COPY pom.xml ./
RUN mvn -q -e -DskipTests dependency:go-offline

# copia o código e empacota
COPY src ./src
RUN mvn -q -e -DskipTests package

# =============== RUNTIME ===============
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

# opcional: usuário não-root
RUN addgroup -S app && adduser -S app -G app
USER app

# copia o jar gerado do estágio de build
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080
ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=default

# healthcheck simples
HEALTHCHECK --interval=30s --timeout=3s --retries=5 CMD wget -qO- http://127.0.0.1:8080/actuator/health || exit 1

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
