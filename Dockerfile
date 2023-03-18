FROM adoptopenjdk:11-jdk
WORKDIR /app
COPY build.gradle settings.gradle gradlew ./
COPY gradle/ ./gradle/
RUN ./gradlew dependencies
COPY src/ ./src/
RUN ./gradlew build
WORKDIR /app/build/libs
CMD ["java", "-jar", "WordCount-0.0.1-SNAPSHOT.war"]
