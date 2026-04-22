# Logging Implementation Summary

This document provides a summary of the logging additions made to the petstore-api-automation project.

## Overview
Comprehensive logging has been added to all test cases and API endpoint classes to provide better visibility into test execution, request/response details, and test results.

## Files Modified

### Test Case Classes (Test Execution Logs)

#### 1. **UserTests.java**
- **Setup Method**: Logs initialization of test payload with user details
- **testPostUser()**: Logs user creation request, response status, and user ID
- **testGetUser()**: Logs user retrieval and validation of user details
- **testUpdateUser()**: Logs user update with new details
- **testDeleteUser()**: Logs user deletion and confirmation

Log Levels Used:
- `LogUtils.info()` - For test start/end and major operations
- `LogUtils.debug()` - For detailed data like username, email

#### 2. **PetTests.java**
- **dataSetUp()**: Logs initialization of pet test payload with pet name
- **postPetTest()**: Logs pet creation, response body, and pet ID
- **getPetTest()**: Logs pet retrieval and validation
- **updatePetTest()**: Logs pet update with new details
- **deletePetTest()**: Logs pet deletion and confirmation

Log Levels Used:
- `LogUtils.info()` - For test start/end and major operations
- `LogUtils.debug()` - For detailed pet data (name, ID)

#### 3. **StoreTests.java**
- **beforeClass()**: Logs initialization of store order payload with pet ID and quantity
- **post_Store()**: Logs store order creation and order ID
- **get_Store()**: Logs store order retrieval and validation
- **delete_Store()**: Logs store order deletion and confirmation

Log Levels Used:
- `LogUtils.info()` - For test start/end and major operations
- `LogUtils.debug()` - For detailed order data (pet ID, quantity)

### Endpoint Classes (API Request/Response Logs)

#### 4. **UserEndPoints.java**
- **post_User()**: Logs POST request to create user with payload details
- **get_User()**: Logs GET request with username parameter
- **put_User()**: Logs PUT request with username and updated payload
- **delete_User()**: Logs DELETE request with username parameter

Each method logs:
- Request endpoint URL
- Request parameters/payload details
- Response status code
- Response content type

#### 5. **PetEndPoints.java**
- **postPet()**: Logs POST request to create pet with name
- **getPet()**: Logs GET request with pet ID parameter
- **updatePet()**: Logs PUT request with pet ID and name
- **deletePet()**: Logs DELETE request with pet ID parameter

Each method logs:
- Request endpoint URL
- Request parameters/payload details
- Response status code
- Response content type

#### 6. **StoreEndPoints.java**
- **post_Store()**: Logs POST request with pet ID and quantity
- **get_Store()**: Logs GET request with order ID parameter
- **delete_Store()**: Logs DELETE request with order ID parameter

Each method logs:
- Request endpoint URL
- Request parameters/payload details
- Response status code
- Response content type

## Log Configuration

The project uses **Log4j2** for logging with the following configuration:

**File**: `src/test/resources/log4j2.xml`

### Appenders:
1. **ConsoleAppender**: Prints logs to console with pattern:
   ```
   %d{yyyy-MM-dd HH:mm:ss} [%-5level] %c{1} - %msg%n
   ```

2. **RollingFile**: Saves logs to `logs/test-execution.log` with daily rollover
   - File pattern: `logs/test-execution-%d{yyyy-MM-dd}.log`

### Log Level:
- **Root Level**: DEBUG (captures debug and above)

## Log Output Examples

### Test Logs (INFO level):
```
2026-04-19 10:30:45 [INFO ] UserTests - =============== UserTests Setup Started ===============
2026-04-19 10:30:45 [INFO ] UserTests - Test payload generated successfully
2026-04-19 10:30:45 [INFO ] UserTests - =============== UserTests Setup Completed ===============
2026-04-19 10:30:46 [INFO ] UserTests - =============== Test: POST User Started ===============
2026-04-19 10:30:46 [INFO ] UserTests - Sending POST request to create user: john_doe
2026-04-19 10:30:47 [INFO ] UserTests - POST request completed with status code: 200
2026-04-19 10:30:47 [INFO ] UserTests - User created successfully with ID: 12345
```

### API Endpoint Logs (DEBUG level):
```
2026-04-19 10:30:46 [DEBUG] UserEndPoints - Executing POST request to: /user
2026-04-19 10:30:46 [DEBUG] UserEndPoints - Request payload - Username: john_doe, Email: john@example.com
2026-04-19 10:30:47 [DEBUG] UserEndPoints - Response status code: 200
2026-04-19 10:30:47 [DEBUG] UserEndPoints - Response content type: application/json
```

## Benefits

1. **Better Visibility**: Track test execution flow and identify where failures occur
2. **Debugging**: Debug information helps understand request/response flow
3. **Audit Trail**: Complete execution history with timestamps
4. **Performance Monitoring**: Identify slow API calls through timing analysis
5. **Production Support**: Logs can be archived for investigation and analysis

## Log Levels Used

- **INFO**: High-level test operations (test start/end, major actions)
- **DEBUG**: Detailed data (parameters, payloads, response details)

## Accessing Logs

1. **Console Output**: View logs in real-time during test execution
2. **Log File**: Check `logs/test-execution.log` after test runs
3. **Dated Logs**: Previous execution logs are available as `logs/test-execution-YYYY-MM-DD.log`

## Notes

- All endpoint classes now use `LogUtils` for consistent logging
- Test classes use both INFO and DEBUG levels for appropriate granularity
- The existing `log4j2.xml` configuration supports these logging requirements
- LogUtils utility class is already present in the `utilites` package
