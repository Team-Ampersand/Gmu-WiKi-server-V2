FROM openjdk:11-jdk
WORKDIR /usr/src/app
COPY sms-infrastructure/build/libs/gmuwiki-infra-0.0.1-SNAPSHOT.jar gmuwiki-infra/build/libs/gmuwiki-infra-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","gmuwiki-infra/build/libs/gmuwiki-infra-0.0.1-SNAPSHOT.jar"]