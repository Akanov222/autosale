# Car Sale API 🚗💨

Spring Boot приложение для управления автомобилями.

## 📌 Технологии
- Java 17
- Spring Boot 3
- Rest API
- PostgreSQL
- Liquibase
- Hibernate
- Swagger (OpenAPI 3)

## 🚀 Запуск
1. Установи PostgreSQL и создай БД `cardealer_db`.
2. Настрой `application.properties`:
   ```properties

   spring.datasource.url=jdbc:postgresql://localhost:5432/cardealer_db
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   spring.datasource.driver-class-name=org.postgresql.Driver
   
   spring.jpa.hibernate.ddl-auto=create
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.properties.hibernate.format_sql=true

   spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.yaml
   spring.liquibase.enabled=true
   
3. Запусти приложение 
   #### 'mvn'
    mvn spring-boot:run
   
## 📚 API Документация
Доступна через Swagger UI:  
🔗 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Спецификация OpenAPI 3.0:  
🔗 [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## 🚀 Примеры запросов
### Получить все машины
'bash'
#### ```curl -X GET http://localhost:8080/api/cars```

### Добавить новую машину
'bash'
   #### ```curl -X POST http://localhost:8080/api/cars -H "Content-Type: application/json" -d '{"brand": "Toyota", "model": "Pajero", "year": "2020", "type": "TRUCK", "price": 35000.0, "loadCapacity": "1500.0"}'```

## 📦 Модели данных
### Смотри в Swagger UI:
🔗 [Car Request/Response](http://localhost:8080/swagger-ui.html#/car-controller)

