<p align="center">
  <img src="banner-dark.png" width="100%" />
</p>

# Cars CLI Application Documentation

## Badges

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-informational)
![License](https://img.shields.io/badge/License-Free-success)
![Status](https://img.shields.io/badge/Project-CLI-orange)

## Overview

This project is a simple Command-Line Interface (CLI) application for managing car details, user login, and basic operations. It is built using Java, JDBC, and PostgreSQL.

## Features

* User Registration
* User Login
* View Car Details
* OTP-based Password Reset
* Utility Functions for Console UI
* Clean DAO-Service-Controller Structure

## Technologies Used

* **Java 17+**
* **PostgreSQL**
* **JDBC**
* **Maven / IDE Build Tools**

## PostgreSQL JDBC Dependency

### ▶ For Maven users:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.2</version>
</dependency>
```

### ▶ For Gradle users:

```gradle
dependencies {
    implementation 'org.postgresql:postgresql:42.7.2'
}
```

### ▶ For IntelliJ / Manual Setup:

Download the JDBC driver manually:

🔗 [https://jdbc.postgresql.org/download/](https://jdbc.postgresql.org/download/)

Add the `.jar` file to your project:

```
File → Project Structure → Modules → Dependencies → + Add JAR
```

## Architecture Diagram (Mermaid)

```mermaid
graph TD;
    A[User CLI] --> B[Controller Layer]
    B --> C[Service Layer]
    C --> D[DAO Layer]
    D --> E[(PostgreSQL DB)]
    C --> F[Util Functions]
```

## Project Structure

```
src/main/java/
│
├── com.github.joel003
│   ├── dao/
│   │   └── UserDAO.java
│   ├── service/
│   │   └── UserService.java
│   ├── controller/
│   │   ├── CarController.java
│   │   └── UserController.java
│   ├── entity/
│   │   └── UserDetail.java
│   └── util/
│       ├── DBConnection.java
│       ├── ConsoleHelper.java
│       └── InputValidator.java
└── resources/
```

## Database Schema

Full SQL seed data provided here:

👉 [Download SQL Seed File](cars_seed_full.txt)

```
Table: userscars

u_id INT PRIMARY KEY
u_name VARCHAR(100)
u_email VARCHAR(100)
u_mobile VARCHAR(15)
m_address VARCHAR(100)
u_password VARCHAR(100)
```

## Example Car Table

A sample dataset of cars is included inside the project.

## How to Run

1. Make sure PostgreSQL is running.
2. Update DB credentials in `DBConnection.java`.
3. Compile and run the main class.

## How Login Works

* User enters email and password
* Application validates using `UserService.verifyUser()`
* On success, user navigates to user menu

## Password Reset Flow

* User enters registered email
* OTP is generated
* User must enter OTP within 10 seconds
* New password is accepted and updated

## Utility Classes

### DBConnection

Provides a single shared database connection.

### ConsoleHelper

Clears screen and formats CLI output.

### InputValidator

Reusable validation functions for email, phone, numbers, etc.

## Future Improvements

* Add Admin module
* Add Car Booking functionality
* Add JUnit Tests
* Add Logging with SLF4J
* Encrypt user passwords

## License

This project is licensed under the **MIT License**.

See the [LICENSE](LICENSE) file for full details.

## Additional Badges

![JDBC](https://img.shields.io/badge/JDBC-PostgreSQL-green)
![Architecture](https://img.shields.io/badge/Architecture-DAO--Service--Controller-yellow)
![CLI](https://img.shields.io/badge/Application-CLI-lightgrey)
![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20Linux-success)
![Status](https://img.shields.io/badge/Status-Active-brightgreen)

## 🎬 Run Demo

Below is a short demo of the CLI in action:

<p align="center">
  <img src="Cars_detail-video.gif" width="600" />
</p>

## 📸 CLI Screenshots

Click below to view all screenshots:

👉 [Open Screenshots Folder](Cars_Details_CLI-Screenshot/)

## UML Class Diagram

Below is the complete class diagram representing the system architecture.

![UML Diagram](Car_Details_CLI_UML.png)

## Database ER Diagram

```mermaid
erDiagram
    userscars {
        INT u_id PK
        VARCHAR u_name
        VARCHAR u_email
        VARCHAR u_mobile
        VARCHAR m_address
        VARCHAR u_password
    }

    carslist {
        INT c_id PK
        VARCHAR c_brand
        VARCHAR c_model
        VARCHAR c_color
        VARCHAR c_type
        INT c_price
    }
```
