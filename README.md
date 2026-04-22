# 🐾 Petstore API Automation Framework

A RestAssured-based API test automation framework for the [Swagger Petstore API](https://petstore.swagger.io/v2), built with Java, TestNG, and ExtentReports.

---

## 📌 What This Project Covers

| Module | Operations | Format |
|--------|-----------|--------|
| Pet    | POST, GET, PUT, DELETE | JSON + XML |
| Store  | POST, GET, DELETE | JSON |
| User   | POST, GET, PUT, DELETE | JSON |

**Total Tests: 15** across 4 test classes, running in parallel.

---

## 🏗️ Project Structure

```
petstore-api-automation/
├── src/test/java/
│   ├── endPoints/
│   │   ├── Routes.java              # All API endpoint URLs
│   │   ├── PetEndPoints.java        # Pet CRUD methods (JSON)
│   │   ├── PetEndPointsXML.java     # Pet CRUD methods (XML)
│   │   ├── StoreEndPoints.java      # Store CRUD methods
│   │   └── UserEndPoints.java       # User CRUD methods
│   ├── payLoads/
│   │   ├── PetPoJo.java             # Pet POJO (JSON)
│   │   ├── PetPoJoXML.java          # Pet POJO (XML with JAXB)
│   │   ├── StorePojo.java           # Store POJO
│   │   └── UserPojo.java            # User POJO
│   ├── testsCases/
│   │   ├── PetTests.java            # Pet JSON test cases
│   │   ├── PetsTestsXML.java        # Pet XML test cases
│   │   ├── StoreTests.java          # Store test cases
│   │   └── UserTests.java           # User test cases
│   └── utilites/
│       ├── CommonResponseVal.java   # Reusable common assertions
│       ├── ExtentReportListener.java# Extent HTML report listener
│       └── LogUtils.java            # Centralized Log4j2 logger
├── src/test/resources/
│   ├── log4j2.xml                   # Log4j2 configuration
│   ├── pets.json                    # JSON schema for Pet
│   ├── Store.json                   # JSON schema for Store
│   ├── User.json                    # JSON schema for User
│   └── xmlschema.xsd                # XSD schema for Pet XML
├── reports/                         # ExtentReports HTML output
├── logs/                            # Log4j2 log files
├── testng.xml                       # TestNG suite configuration
└── pom.xml                          # Maven dependencies & plugins
```

---

## ⚙️ Tech Stack

| Tool | Purpose |
|------|---------|
| Java 8 or higher | Programming language |
| RestAssured 5.3.0 | API testing library |
| TestNG 7.8.0 | Test runner & assertions |
| ExtentReports 5.0.9 | HTML test reporting |
| Log4j2 2.20.0 | Logging framework |
| DataFaker 2.0.0 | Dynamic test data generation |
| Jackson / JAXB | JSON & XML serialization |
| JSON Schema Validator | Schema-level response validation |
| Apache POI | Excel data-driven testing support |
| Maven | Build & dependency management |

---

## 🚀 How to Run

### Prerequisites
- Java 8 or higher
- Maven 3.6+

### Run all tests
```bash
mvn test
```

### Run specific test class
```bash
mvn test -Dtest=PetTests
```

### Run with specific testng.xml
```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## ✨ Key Framework Features

### ✅ Parallel Execution
All 4 test modules run in parallel using `parallel="tests"` and `thread-count="4"` in testng.xml.

### ✅ Thread-Safe ID Sharing
`ThreadLocal<Long>` is used to safely share IDs between test methods during parallel execution — preventing data corruption across threads.

### ✅ JSON + XML Coverage
Pet module is tested with both JSON and XML content types — demonstrating full content negotiation coverage.

### ✅ Schema Validation
- JSON responses validated against JSON Schema files (`pets.json`, `Store.json`, `User.json`)
- XML responses validated against XSD schema (`xmlschema.xsd`)

### ✅ Centralized Assertions
`CommonResponseVal` utility class eliminates code duplication — all common assertions (status code, response time, body size, content type) are centralized.

### ✅ Dynamic Test Data
DataFaker generates unique random data on every run — no hardcoded values, eliminating test data conflicts.

### ✅ Structured Logging
Log4j2 with dual appenders — logs printed to console AND saved to rolling file (`logs/test-execution.log`).

### ✅ HTML Reporting
ExtentReports generates a detailed HTML report at `reports/Report.html` after every test run.

---

## 📊 Sample Test Results

```
Tests run: 15, Failures: 0, Errors: 0, Skipped: 0

Pet JSON Tests  → 4/4 PASSED ✅
Pet XML Tests   → 4/4 PASSED ✅
Store Tests     → 3/3 PASSED ✅
User Tests      → 4/4 PASSED ✅
```

---

## 📁 Reports & Logs

After running tests:
- **HTML Report** → `reports/Report.html`
- **Log File** → `logs/test-execution.log`
- **TestNG Report** → `target/surefire-reports/`

---

## 👤 Author

**Rupesh**
- API Automation Framework using RestAssured + TestNG
