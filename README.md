# Prisma

**PrismaBack** és el backend d’una plataforma educativa construïda amb **Spring Boot** i **Java 21**. Exposa una API REST que permet gestionar usuaris, lliçons, preguntes i respostes, seguint bones pràctiques d’arquitectura i consistència en les respostes.

> **Frontend:** [https://github.com/cuyass/prisma-front](https://github.com/cuyass/prisma-front)

---

## Tecnologies principals

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Lombok**
- **PostgreSQL**
- **CORS** configurat per al frontend a [http://localhost:5173](http://localhost:5173)
- **Resposta d’API unificada** per a totes les respostes

---

## 📦 Estructura del projecte
```
src/
├── main/
│ ├── java/com/prismaback/prismaback/
│ │ ├── controller/
│ │ ├── service/
│ │ ├── repository/
│ │ ├── model/
│ │ ├── DTO/
│ │ ├── exception/
│ │ └── response/
│ └── resources/
│ └── application.properties
└── test/
```
---

## ⚙️ Configuració i execució

### 1. Clona el repositori

git clone https://github.com/cuyass/prismaback.git
cd prismaback

### 2. Configura la base de dades

Edita `src/main/resources/application.properties` segons el teu entorn.  
Exemple per H2 (per defecte):

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true


### 3. Compila i executa el backend

./mvnw spring-boot:run

Per defecte, l’API estarà a [http://localhost:8080/api/v1](http://localhost:8080/api/v1)

---

## Endpoints principals

- **Lliçons:**  
  `GET /api/v1/lessons`  
  `GET /api/v1/lessons/{id}`  
  `POST /api/v1/lessons`  
  `PUT /api/v1/lessons/{id}`  
  `DELETE /api/v1/lessons/{id}`

- **Preguntes:**  
  `GET /api/v1/questions/lesson/{lessonId}`  
  `POST /api/v1/questions/lesson/{lessonId}`  
  `PUT /api/v1/questions/{questionId}`  
  `DELETE /api/v1/questions/{questionId}`

- **Respostes:**  
  `GET /api/v1/answers/question/{questionId}`  
  `POST /api/v1/answers/question/{questionId}`

- **Usuaris:**  
  `POST /api/v1/users`

---

## Format de resposta de l’API

Totes les respostes (èxit i error) segueixen aquest format:

{
"message": "Descriptiu del resultat",
"data": { /* objecte o llista, o null si és error */ },
"status": 200,
"timestamp": "2025-05-21T23:59:00.123"
}

---

## Gestió d’errors

El backend llença excepcions personalitzades i les transforma en respostes uniformes mitjançant un `@RestControllerAdvice` global.

Exemple d’error:

{
"message": "Usuari duplicat: Email ja registrat",
"data": null,
"status": 409,
"timestamp": "2025-05-21T23:59:00.123"
}

---

## Seguretat

- CORS habilitat només per al frontend en desenvolupament (`http://localhost:5173`).
- (Opcional) Pots afegir autenticació JWT, rols, etc.

---

## Desenvolupament i testing

- Utilitza **Lombok** per reduir boilerplate.
- Utilitza **DTOs** per exposar només les dades necessàries.
- Pots testar amb Postman, Insomnia o des del teu frontend React.

---

## Crèdits

Desenvolupat per Marion  
Contacte: https://www.linkedin.com/in/mariona-cuyas/

---
