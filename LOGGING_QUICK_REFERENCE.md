# Logging Quick Reference Guide

## What Has Been Added

### Test Classes - Comprehensive Test Execution Logging

1. **UserTests.java** - User CRUD operations logging
2. **PetTests.java** - Pet CRUD operations logging  
3. **StoreTests.java** - Store order CRUD operations logging

Each test class now logs:
- ✅ Test setup initialization
- ✅ Test start/end boundaries
- ✅ Request details before API calls
- ✅ Response status codes
- ✅ Test assertions and validations
- ✅ Success/failure messages

### Endpoint Classes - API Request/Response Logging

1. **UserEndPoints.java** - User API calls
2. **PetEndPoints.java** - Pet API calls
3. **StoreEndPoints.java** - Store API calls

Each endpoint class now logs:
- ✅ Endpoint URL being called
- ✅ Request method (POST, GET, PUT, DELETE)
- ✅ Request parameters and payload details
- ✅ Response status codes
- ✅ Response content types

## How to Use

### View Console Logs
When you run your tests, logs will print to console in real-time with this format:
```
2026-04-19 10:30:45 [INFO ] UserTests - =============== UserTests Setup Started ===============
```

### View File Logs
After test execution, check the log file at:
```
logs/test-execution.log
```

Daily log files are created with pattern:
```
logs/test-execution-YYYY-MM-DD.log
```

## Log Levels

- **INFO** (ℹ️): High-level test milestones
  - Test start/end markers
  - Major operations (user created, pet updated, etc.)
  
- **DEBUG** (🔧): Detailed execution information
  - Request parameters and values
  - Response details
  - Data values (usernames, emails, IDs, etc.)

## Example Log Output

### Setup Logs
```
[INFO ] UserTests - =============== UserTests Setup Started ===============
[INFO ] UserTests - Test payload generated successfully
[DEBUG] UserTests - Username: john_doe_123
[DEBUG] UserTests - Email: john@example.com
[INFO ] UserTests - =============== UserTests Setup Completed ===============
```

### Test Execution Logs
```
[INFO ] UserTests - =============== Test: POST User Started ===============
[INFO ] UserTests - Sending POST request to create user: john_doe_123
[DEBUG] UserEndPoints - Executing POST request to: /user
[DEBUG] UserEndPoints - Request payload - Username: john_doe_123, Email: john@example.com
[DEBUG] UserEndPoints - Response status code: 200
[DEBUG] UserEndPoints - Response content type: application/json
[INFO ] UserTests - POST request completed with status code: 200
[INFO ] UserTests - User created successfully with ID: 12345
[INFO ] UserTests - =============== Test: POST User Completed ===============
```

## Files Modified Summary

| File | Changes |
|------|---------|
| UserTests.java | Added LogUtils import + 5 logging statements per test method |
| PetTests.java | Added LogUtils import + 5 logging statements per test method |
| StoreTests.java | Added LogUtils import + 5 logging statements per test method |
| UserEndPoints.java | Added LogUtils import + logs to all 4 endpoint methods |
| PetEndPoints.java | Added LogUtils import + logs to all 4 endpoint methods |
| StoreEndPoints.java | Added LogUtils import + logs to all 3 endpoint methods |

## Total Logging Added

- ✅ **6 files modified**
- ✅ **3 test classes** with comprehensive test execution logs
- ✅ **3 endpoint classes** with detailed API call logs
- ✅ **15+ test methods** instrumented with logging
- ✅ **11 endpoint methods** instrumented with logging
- ✅ **No compilation errors** - all changes validated

## Log Configuration File

**Location**: `src/test/resources/log4j2.xml`

- Console appender for real-time visibility
- File appender with daily rollover
- DEBUG level root logger
- Logs stored in `logs/` directory

## Next Steps

1. Run your tests: `mvn test` or `mvn clean test`
2. Check console output for real-time logs
3. Review `logs/test-execution.log` for complete execution history
4. Use logs to debug failed tests or understand execution flow

## Benefits

✨ **Improved Visibility**: See exactly what your tests are doing
🐛 **Easier Debugging**: Identify failures and trace execution flow
📊 **Better Documentation**: Logs provide execution trail for auditing
⚡ **Performance Insight**: Track API response times and failures
🔍 **Test Analysis**: Understand test dependencies and execution order
