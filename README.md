# MTG-Service API  


## Required Tools
| Tool | Purpose | Notes |
|------|----------|-------|
| **Java 17 +** | Run the Spring Boot 3.5 API | verify `java –version` |
| **Gradle 8 +** | build & run service | use `./gradlew bootRun` |
| **Docker Desktop** | host MariaDB container | make sure container `mtg` is running |
| **Bruno** | API testing tool | use the provided collection `/bruno/MTG-Service-Collection` |
| **VS Code + Java extensions** | IDE for development | Extension Pack for Java recommended |

---

## Database Setup

1. Open Docker Desktop → start container **`mtg`**  
   (compose file: `db/docker-compose.yml`)

![docker-not-running.png](mtg-service/assets/docker-not-running.png)

   ```bash
   cd db
   docker compose up -d
```
## **NOTE: IF YOU ARE GETTING ERRORS THAT DOCKER DESKTOP IS NOT RUNNING, MAKE SURE THAT YOU ARE IN THE DIRECTORY WHERE DOCKER-COMPOSE.YML, EITHER IN db FOLDER IN CS4900-API-MTG OR IN WHATEVER FOLDER YOU DOWNLOADED IT IN**

2. Verify it’s running: the green dot appears next to mtg in Docker Desktop. Port mapping 3306 → localhost 3306.

![docker-running.png](mtg-service/assets/docker-running.png)

---
## Run the Service

### Option 1 – VS Code

Right-click src/main/java/com/mtg/mtgservice/MtgServiceApplication.java → Run Java.

![run-java.png](mtg-service/assets/run-java.png)

### Option 2 – Gradle CLI

1. Ensure that you are in the correct directory of cs4900-api-mtg.
2. cd into mtg-service
```
cd mtg-service
```

Should look like this, `~/cs4900-api-mtg/mtg-service$`

## **NOTE: THE NEXT STEP WILL NOT WORK IF YOU ARE NOT IN MTG-SERVICE FOLDER** <br>
```
./gradlew bootRun
```

![java-app-starting.png](mtg-service/assets/java-app-start.png)

---
When startup completes, the console shows:

Started MtgServiceApplication ... Tomcat started on port 8080

---
## Test with Bruno

1. Open Bruno and load the collection
```
bruno/MTG-Service-Collection
```
![open-collection.png](mtg-service/assets/open-collection.png)

![collection-file-path.png](mtg-service/assets/collection-file-path.png)

2. Set the environment to local

3. Run the API rquests for each entity

![bruno.png](mtg-service/assets/bruno.png)

---
## Example Endpoints

### Card

| Method | Endpoint | Description |
|--------|-----------|--------------|
| GET | `/card` | Retrieve all cards |
| GET | `/card/:cardNumber/:setName` | Retrieve card by composite key |
| GET | `/card/search?q={text}` | Search by card name |
| POST | `/card` | Create a new card |
| PUT | `/card/{cardNumber}/{setName}` | Update an existing card |

### Example POST body:
```
{
  "cardNumber": 269,
  "setName": "ALP",
  "cardName": "Sol Ring",
  "cardType": "Artifact",
  "manaValue": "1",
  "cardCondition": "NM",
  "cardDescription": "{T}: Add {C}{C}.",
  "price": 2.50,
  "stock": 25
}
```
