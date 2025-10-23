# MTG Database – Table Initialization

This project contains the SQL initialization script (`MTG.sql`) and a Docker Compose setup for running a MariaDB instance. It also includes instructions for connecting with DBeaver.

---

## 1. Docker Compose Setup

The `docker-compose.yml` file defines a MariaDB container and maps the initialization script so that the database is created and seeded on the first startup.

Example `docker-compose.yml`:

```yaml
version: '3.8'

services:
  mariadb:
    image: mariadb:latest
    container_name: WSU_4900_DB_Design_MTG
    restart: always
    volumes:
      - ./MTG.sql:/docker-entrypoint-initdb.d/init.sql
    environment:
      - MYSQL_ROOT_PASSWORD=password
      - MYSQL_DATABASE=assignment5
      - MYSQL_USER=user
      - MYSQL_PASSWORD=password
    ports:
      - "3320:3306"
```

---

## 2. Start the Database with Docker Compose

1. Make sure you have **Docker Desktop** installed and running.  
2. In a terminal, navigate to the project folder that contains `docker-compose.yml`.  
3. Run the following command to start MariaDB:

```bash
docker compose up -d
```

- On the first start, MariaDB will automatically load the schema from `MTG.sql`.  
- To stop the container:  

```bash
docker compose down
```

- To restart the container after stopping:  

```bash
docker compose start
```

---

## 3. Connect to MariaDB with DBeaver

1. Open **DBeaver** and create a new connection.  
2. Enter the following settings:  
   - Database type: **MariaDB**  
   - Host: `localhost`  
   - Port: `3320`  
   - Database: `assignment5`  
   - User: `user`  
   - Password: `password`  
3. Click **Test Connection** to verify.  
4. Once connected, expand the database in the left sidebar to see all the initialized tables.

---

## 4. Resetting the Database

If you need to reload the schema from scratch, remove the container and its data volume:

```bash
docker compose down -v
docker compose up -d
```
