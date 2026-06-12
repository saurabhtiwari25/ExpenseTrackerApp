# Personal Expense Tracker REST API

A clean, production-ready RESTful API for tracking personal expenses — built with **Spring Boot 3.5.15**, **MongoDB**, and **Java 17**. Supports full CRUD operations with bean validation, Logging using @slf4j and proper HTTP status codes.

---
## Postman testing:

<img width="1190" height="833" alt="image" src="https://github.com/user-attachments/assets/c0a0faca-e943-4aa4-8005-8dbd49417f37" />


## Features

-  Full **CRUD** operations (Create, Read, Update, Delete)
-  **Bean Validation** with descriptive error messages
-  **MongoDB** for flexible, schema-less document storage
-  Proper **HTTP status codes** (201, 204, 404, 400)
-  Existence checks on update and delete (prevents silent failures)
-  Total expense calculation endpoint
-  Clean layered architecture (Controller → Service → Repository)
-  Lombok for boilerplate reduction

---

## 🛠 Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Language |
| Spring Boot | 3.5.15 | Application framework |
| Spring Data MongoDB | 4.5.12 | Database integration |
| Jakarta Validation | 3.0.2 | Request body validation |
| Lombok | Latest | Boilerplate code reduction |
| Maven | 3.9.11 | Build & dependency management |
| MongoDB- NoSQL database |

---

## Project Structure

```
expense-tracker/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/com/example/expensetracker/
        │   ├── ExpenseTrackerApplication.java    
        │   ├── controller/
        │   │   └── ExpenseController.java       
        │   ├── model/
        │   │   └── Expense.java                  
        │   ├── repository/
        │   │   └── ExpenseRepository.java        
        │   └── service/
        │       └── ExpenseService.java           
        └── resources/
            └── application.properties            
```

---

## Prerequisites

Before running this project, make sure you have the following installed:

| Tool | Purpose |
|---|---|
| **Java 17** (or higher) | Runtime environment |
| **Maven 3.9.11 | Dependency management and build |
| **MongoDB | Database server |

Verify installations:
```bash
java -version        # Should show 17+
mvn -version         # Should show 3.x
mongosh --version    # Should connect to MongoDB
```

---

## ⚙ Setup & Installation

### 1. Clone the repository
```bash
git clone https://github.com/saurabhtiwari25/ExpenseTracker.git
cd expense-tracker
```

### 2. Install dependencies
```bash
mvn clean install
```

### 3. Start MongoDB
Make sure MongoDB is running on the default port:

```bash
# run:
mongod

# Verify connection:
mongosh
```

### 4. Run the application
```bash
mvn spring-boot:run
```

The server will start at: **http://localhost:8080**

---

## 🗄 Database Configuration

Configuration is defined in `src/main/resources/application.properties`:

```properties
spring.application.name=expense-tracker
spring.data.mongodb.uri=mongodb://localhost:27017/expense_db
```

| Property | Value | Description |
|---|---|---|
| `spring.data.mongodb.uri` | `mongodb://localhost:27017/expense_db` | MongoDB connection URI |


### Verify in MongoDB Shell
```bash
mongosh
use expense_db
db.expenses.find().pretty()
```

---

## 🗄 Database Design

The project uses MongoDB as its primary data store. The main entity is the `Expense` document.

**Collection Name:** `expenses`

### Expense Document Schema
| Field | Type | Constraints | Description |
|---|---|---|---|
| `_id` | ObjectId | Auto-generated | Unique identifier for the document |
| `title` | String | Max 100 chars, Not Blank | Title or description of the expense |
| `amount` | Double | Strictly Positive (`> 0`) | The monetary value of the expense |
| `dateOfCreation` | Date (ISO) | Not Null | The date the expense occurred/was logged |

**Example Document:**
```json
{
  "_id": {
    "$oid": "6a2bdea2a37171609167a35f"
  },
  "title": "Weekly Holidays",
  "amount": 6000.0,
  "dateOfCreation": "2026-06-12"
}
```

---

## API Endpoints

| Method | Endpoint | Description | Status Codes |
|---|---|---|---|
| `GET` | `/api/expenses` | Get all expenses | `200 OK` |
| `GET` | `/api/expenses/{id}` | Get expense by ID | `200 OK` / `404 Not Found` |
| `POST` | `/api/expenses` | Create a new expense | `201 Created` / `400 Bad Request` |
| `PUT` | `/api/expenses/{id}` | Update an expense | `200 OK` / `404 Not Found` / `400 Bad Request` |
| `DELETE` | `/api/expenses/{id}` | Delete an expense | `204 No Content` / `404 Not Found` |
| `GET` | `/api/expenses/total` | Get total of all expenses | `200 OK` |

---

##  Request & Response Examples

### Create a New Expense

**Request:**
```http
POST /api/expenses
Content-Type: application/json
```
```json
{
    "title": "Weekly Holidays",
    "amount": 6000.0,
    "dateOfCreation": "2026-06-12"
}
```

**Response:** `201 Created`
```json
{
    "id": "6a2bdea2a37171609167a35f",
    "title": "Weekly Holidays",
    "amount": 6000.0,
    "dateOfCreation": "2026-06-12"
}
```

#created more
```json
[
    {
        "id": "6a2bdea2a37171609167a35f",
        "title": "Weekly Holidays",
        "amount": 6000.0,
        "dateOfCreation": "2026-06-12"
    },
    {
        "id": "6a2be03200e28e819f667dd2",
        "title": "Books",
        "amount": 1600.0,
        "dateOfCreation": "2026-06-13"
    },
    {
        "id": "6a2be08500e28e819f667dd3",
        "title": "Toys",
        "amount": 3200.0,
        "dateOfCreation": "2026-06-13"
    },
    {
        "id": "6a2bf0f3d7dabc1dee7fa1cf",
        "title": "Ola to Office",
        "amount": 180.0,
        "dateOfCreation": "2026-06-10"
    }
]
```
---

### Get All Expenses

**Request:**
```http
GET /api/expenses
```

**Response:** `200 OK`
```json
[
    {
        "id": "6a2bdea2a37171609167a35f",
        "title": "Weekly Holidays",
        "amount": 6000.0,
        "dateOfCreation": "2026-06-12"
    },
    {
        "id": "6a2be03200e28e819f667dd2",
        "title": "Books",
        "amount": 1600.0,
        "dateOfCreation": "2026-06-13"
    },
    {
        "id": "6a2be08500e28e819f667dd3",
        "title": "Toys",
        "amount": 3200.0,
        "dateOfCreation": "2026-06-13"
    },
    {
        "id": "6a2bf0f3d7dabc1dee7fa1cf",
        "title": "Ola to Office",
        "amount": 180.0,
        "dateOfCreation": "2026-06-10"
    }
]
```

---

### Get Expense by ID

**Request:**
```http
GET /api/expenses/666a1b2c3d4e5f6a7b8c9d0e
```

**Response:** `200 OK`
```json
{
    "id": "666a1b2c3d4e5f6a7b8c9d0e",
    "title": "Monthly Rent",
    "amount": 15000.00,
    "dateOfCreation": "2026-06-01"
}
```

**If not found:** `404 Not Found` (empty body)

---

### Update an Expense

**Request:**
```http
PUT /api/expenses/666a1b2c3d4e5f6a7b8c9d0e
Content-Type: application/json
```
```json
{
    "title": "Monthly Rent (Increased)",
    "amount": 17000.00,
    "dateOfCreation": "2026-06-01"
}
```

**Response:** `200 OK`
```json
{
    "id": "666a1b2c3d4e5f6a7b8c9d0e",
    "title": "Monthly Rent (Increased)",
    "amount": 17000.00,
    "dateOfCreation": "2026-06-01"
}
```

**If not found:** `404 Not Found` (empty body)

---

### Delete an Expense

**Request:**
```http
DELETE /api/expenses/666a1b2c3d4e5f6a7b8c9d0e
```

**Response:** `204 No Content` (empty body)

**If not found:** `404 Not Found` (empty body)

---

### Get Total Expenses

**Request:**
```http
GET /api/expenses/total
```

**Response:** `200 OK`
```json
17500.50
```

---

##  Validation Rules

All fields are validated when creating or updating an expense:

| Field | Rule | Error Message |
|---|---|---|
| `title` | Required, non-blank, max 100 chars | "Title cannot be blank" / "Title should be within 100 characters" |
| `amount` | Required, must be > 0 | "Amount is required" / "Amount cannot be negative" |
| `dateOfCreation` | Required | "Date of creation is required" |

**Example — Invalid request:**
```json
{
    "title": "",
    "amount": -100,
    "dateOfCreation": null
}
```
**Response:** `400 Bad Request` with validation error details.

---

##  Testing with Postman

1. **Import** the API endpoints into Postman
2. Set the base URL to `http://localhost:8080`
3. For `POST` and `PUT` requests:
   - Set header: `Content-Type: application/json`
   - Set body to **raw → JSON**

### Suggested Test Flow

```
1. POST   /api/expenses          → Create 2-3 expenses
2. GET    /api/expenses          → Verify all are listed
3. GET    /api/expenses/{id}     → Fetch one by ID
4. PUT    /api/expenses/{id}     → Update title/amount
5. GET    /api/expenses/{id}     → Verify update
6. GET    /api/expenses/total    → Check total sum
7. DELETE /api/expenses/{id}     → Delete one
8. GET    /api/expenses          → Confirm deletion
9. GET    /api/expenses/{bad-id} → Should return 404
10. POST  /api/expenses (empty)  → Should return 400
```

---

##  Error Handling

| Scenario | HTTP Status | Description |
|---|---|---|
| Expense not found | `404 Not Found` | GET, PUT, or DELETE with non-existent ID |
| Validation failure | `400 Bad Request` | Missing/invalid fields in POST or PUT |
| Malformed JSON | `400 Bad Request` | Request body is not valid JSON |
| Wrong Content-Type | `415 Unsupported Media Type` | Sending non-JSON content type |
| Server error | `500 Internal Server Error` | Unexpected runtime exception |

---

---

> Built with  Java +  Spring Boot +  MongoDB
