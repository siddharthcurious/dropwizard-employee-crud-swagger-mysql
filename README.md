# EmployeeService

How to start the EmployeeService application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/dropwizard-employee-management-ws-1.0.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8085`

Swagger
---
1. To check available endpoints and test `http://localhost:8085/swagger`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
