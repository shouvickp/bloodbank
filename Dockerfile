FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY bloodbank-1.0.0.jar bloodbank-service.jar
EXPOSE 8080
CMD java -Dspring.profiles.active=$PROFILE -Duser.timezone="Asia/Kolkata" -jar bloodbank-service.jar --DB_HOSTNAME=$DATABASE_SVC --DB_PORT=$DATABASE_PORT --DB_USER=$DATABASE_USER --DB_PASSWORD=$DATABASE_PASSWORD --CLIENT_SECRET=$JWT_SECRET
