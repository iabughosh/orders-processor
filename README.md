# Orders CSV Processor

This project is developed using Java & Apache Camel.

## Prerequisites
- Java 17
- Maven 3.6+ (Optional otherwise maven wrapper is being bundled and can be used)

## Running the application
You need to provide two Java arguments : folderPath & fileName
```shell script
./mvnw clean compile camel:run -DfolderPath=/home/iabughosh/workspace/order-processor/src/test/data -DfileName=orders.csv
```

> **_NOTE:_**  Type Ctrl + C to shutdown the integration app.
