# component-telephone-service
Microservice to manage customer telephone details


1. Copy the folder to local
2. Use the commans  - mvn spring-boot:run -Dspring-boot.run.arguments=--SPRING_PROFILES_ACTIVE=local

3. Get all telephone details
http://localhost:8081/v1/telephone

4. Get telephone by customer
http://localhost:8081/v1/telephone/111111

5. Activate a number
http://localhost:8081/v1/telephone/111111/activate?telephone=1111111113


