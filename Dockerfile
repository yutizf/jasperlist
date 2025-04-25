FROM eclipse-temurin:21-jdk

RUN apt-get update && apt-get install -y \
    unzip \
    wget \
    fontconfig \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean install -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/mysql-0.0.1-SNAPSHOT.jar"]