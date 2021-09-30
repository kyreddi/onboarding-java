FROM openjdk:8
ADD target/onboardig-service.jar onboardig-service.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/onboardig-service.jar"]