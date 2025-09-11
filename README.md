# Car Sale API 🚗💨

Spring Boot приложение для управления автомобилями.

## 📌 Технологии
- Java 17
- Spring Boot 3
- Docker
- Swagger (OpenAPI 3)
- PostgreSQL
- Liquibase
- Hibernate

## 🚀 Запуск через Docker (рекомендуемый способ)
### 1. Запусти все приложение с БД.
'bash'
#### ```docker-compose up -d```
### 2. Остановка
`bash'
#### ```docker-compose up -d```
### 3. Просмотр логов
'bash'
#### ```docker-compose logs -f autosale```
3. Порты приложения
      Приложение: http://localhost:8080
      База данных: localhost:5432
      Swagger UI: http://localhost:8080/swagger-ui.html
      API Docs: http://localhost:8080/v3/api-docs

4. Docker информация
   Имя сервиса: autosale
   БД контейнер: cardealer_db
   Том данных: postgres_data (сохраняется после остановки)
   
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

