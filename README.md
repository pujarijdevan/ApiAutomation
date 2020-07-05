# ApiAutomation
Tech stakes :
   1. Rest Assured:
        It is java based library supports testing Restful web service. It is free and open sourced framework
        This library behaves like a headless Client to access REST web services.
        Sending https requests with user-friendly customizations is simple if one has a basic background of java.
        Rest-Assured library also provides the ability to validate the HTTP Responses received from the server.
        Test are easy to write
        It also supports BDD approach of writing test.
        It can be extended to proper BDD Cucumber framework if needed.

   2. TestNG:
        Functional testing framework which supports annotations which are easy to understand
        Easy to group test cases & prioritize test cases execution.
        Have inbuilt HTMl report support.
   3. Maven:
     For dependency management of various libraries
   4. Extent Report:
        Provides report in dashboard format
   5. Docker:
        Containerize the application & enthronement and run it anywhere.

About Framework :

This Framework Exhibits:

1.  Data Driven approach - Test data can be provided in testng data provider which is in TestData file inside testcases folder.
2.  It supports extent report - You can view the report in reports folder after the execution
3.  Tests framework testng supports different annotation to execute test with setup /cleanup /grouping
4.  Test are written in modular driven way.
5.  Dependency management is done via Maven build tool
6.  Got docker support test execution.

How to Execute :

Prerequisite : Maven and Java 1.8,git are installed in the system.
Open terminal
1. Execute git pull https://github.com/Jagapd/ApiAutomation.git
2. Wait for some time to pull  maven dependency libraries.
3. Execute mvn test command .
4. Results are available in reports folder after test run

Via Docker :
Prerequisite : Docker is installed.
1. Pull Docker Image of application by below command :
2. docker pull jagapd/api-automation-framework:1.0
3. Run docker Image :
4. docker run jagapd/api-automation-framework:1.0 mvn test



