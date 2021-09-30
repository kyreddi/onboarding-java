FROM openjdk:8
ADD target/payroll-cicd.jar payroll-cicd.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/payroll-cicd.jar"]