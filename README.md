### README for the Spring Boot Application

# Football Player Stats API (Spring Boot)

## Overview
A Spring Boot REST API to manage football player statistics stored in a PostgreSQL database, supporting CRUD operations on the `player_static` table.

## Project Structure
```
football-stats-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/emtech/mondaytest/
│   │   │       ├── entity/
│   │   │       │   └── Player.java
│   │   │       ├── repository/
│   │   │       │   └── PlayerRepository.java
│   │   │       ├── service/
│   │   │       │   └── PlayerService.java
│   │   │       ├── controller/
│   │   │       │   └── PlayerController.java
│   │   │       └── MondaytestApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Prerequisites
- Java 17
- Maven
- PostgreSQL
- Git

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/gathuh/football-stats-api.git
cd football-stats-api
```

### 2. Set Up the PostgreSQL Database
1. Install PostgreSQL.
2. Create the `football` database:
   ```bash
   psql -U postgres
   CREATE DATABASE football;
   \q
   ```
3. Create the `player_static` table:
   ```sql
   psql -U gathu -d football
   CREATE TABLE player_static (
       name VARCHAR(100) PRIMARY KEY,
       nation VARCHAR(50),
       pos VARCHAR(50),
       age INTEGER,
       mp INTEGER,
       starts INTEGER,
       min DOUBLE PRECISION,
       gls DOUBLE PRECISION,
       pk DOUBLE PRECISION,
       crdy DOUBLE PRECISION,
       crdr DOUBLE PRECISION,
       xg DOUBLE PRECISION,
       xag DOUBLE PRECISION,
       team VARCHAR(100)
   );
   \q
   ```
4. Populate the table using the Python script (see [football-stats-scraper](https://github.com/your-username/football-stats-scraper)).

### 3. Configure the Application
1. Update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/football
   spring.datasource.username=gathu
   spring.datasource.password=your_password
   spring.datasource.driver-class-name=org.postgresql.Driver
   spring.jpa.hibernate.ddl-auto=validate
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.properties.hibernate.format_sql=true
   ```
2. Build and run:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

## API Endpoints
| Method | Endpoint                  | Description                  |
|--------|---------------------------|------------------------------|
| GET    | `/api/players`            | Retrieve all players         |
| GET    | `/api/players/{name}`     | Retrieve a player by name    |
| POST   | `/api/players`            | Add a new player             |
| PUT    | `/api/players/{name}`     | Update an existing player    |
| DELETE | `/api/players/{name}`     | Delete a player by name      |

## Contributing
1. Fork the repository.
2. Create a branch (`git checkout -b feature/your-feature`).
3. Commit changes (`git commit -m "Add your feature"`).
4. Push (`git push origin feature/your-feature`).
5. Open a pull request.

## License
MIT License. See [LICENSE](LICENSE).

---

### README for the Python Web Scraping Script

# Football Player Stats Scraper (Python)

## Overview
A Python script to scrape football player statistics, clean the data, and import it into a PostgreSQL `player_static` table for use by a Spring Boot API.

## Project Structure
```
football-stats-scraper/
├── data/
│   └── stats.csv
├── requirements.txt
├── scrape_data.py
├── prepare_data.py
└── README.md
```

## Prerequisites
- Python 3.12
- PostgreSQL
- Git

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/football-stats-scraper.git
cd football-stats-scraper
```

### 2. Set Up the PostgreSQL Database
1. Install PostgreSQL.
2. Create the `football` database:
   ```bash
   psql -U postgres
   CREATE DATABASE football;
   \q
   ```
3. Create the `player_static` table:
   ```sql
   psql -U gathu -d football
   CREATE TABLE player_static (
       name VARCHAR(100) PRIMARY KEY,
       nation VARCHAR(50),
       pos VARCHAR(50),
       age INTEGER,
       mp INTEGER,
       starts INTEGER,
       min DOUBLE PRECISION,
       gls DOUBLE PRECISION,
       pk DOUBLE PRECISION,
       crdy DOUBLE PRECISION,
       crdr DOUBLE PRECISION,
       xg DOUBLE PRECISION,
       xag DOUBLE PRECISION,
       team VARCHAR(100)
   );
   \q
   ```

### 3. Set Up the Python Environment
1. Create a virtual environment:
   ```bash
   python3 -m venv venv
   source venv/bin/activate
   ```
2. Install dependencies:
   ```bash
   pip install -r requirements.txt
   ```
   `requirements.txt`:
   ```
   pandas
   sqlalchemy
   psycopg2-binary
   beautifulsoup4
   requests
   ```

### 4. Scrape the Data
Run `scrape_data.py` to scrape data and save it as `data/stats.csv`:
```bash
python scrape_data.py
```

### 5. Clean and Import the Data
Run `prepare_data.py` to clean and import the data into PostgreSQL:
```bash
python prepare_data.py
```

## Usage
The script populates the `player_static` table, which is accessed by the Spring Boot API (see [football-stats-api](https://github.com/your-username/football-stats-api)).

## Contributing
1. Fork the repository.
2. Create a branch (`git checkout -b feature/your-feature`).
3. Commit changes (`git commit -m "Add your feature"`).
4. Push (`git push origin feature/your-feature`).
5. Open a pull request.

## License
MIT License. See [LICENSE](LICENSE).
