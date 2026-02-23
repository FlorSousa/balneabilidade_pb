# Stage 1: Build
FROM gradle:9.3.1-jdk21-ubi9 AS builder

WORKDIR /build

# Copiar os arquivos de configuração do Gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Download de dependências (cache layer)
RUN gradle dependencies --no-daemon

# Copiar o código fonte
COPY src ./src

# Compilar e construir o JAR
RUN gradle clean bootJar -x test --no-daemon

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copiar o JAR da etapa de build
COPY --from=builder /build/build/libs/*.jar app.jar

# Expor a porta
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

