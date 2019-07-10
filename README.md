# Sales Message Processing Application

This Sales Message Processing Application that simulates the processing sales notification messages.

Technologies used:
- java
- Maven
- junit

Work flow and Assumtions:
- All data to be in memory.
- IO via console
- Sale value cannot be nagative
- show interval report every 10 messages.
- don't accept new mesages after 50 messages and show final report.

Build Instructions:
```$xslt
mvn clean package
```

To run:
```$xslt
java -jar target\sales-message-processing-1.0.jar
```
Input Message examples
- apple at 10p
- 20 sales of apples at 10p each
- Multiply 2p apples
- Add 20p apples
- Subtract 20p apples
