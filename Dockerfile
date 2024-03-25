FROM eclipse-temurin:17-jre-alpine
WORKDIR /myApp
COPY ./target/*.jar /myApp/PRGroceryAppName.jar

ENTRYPOINT ["java","-jar","PRGroceryAppName.jar"]