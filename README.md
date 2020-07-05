# ApiAutomation
Tech stakes :
    Rest Assured
    TestNG
    Maven
    Extent Report
    Docker

About Framework :

This Framework Exhibits:

1.  Data Driven approach - Test data can be provided in testng data provider which is in TestData file inside testcases folder.
2.  It supports extent report - You can view the report in reports folder after the execution
3.  Tests framework testng supports different annotation to execute test with setup /cleanup /grouping
4.  Test are written in modular driven way.
5.  Dependency management is done via Maven build tool
6.  Got docker support test execution.

How to Execute :

        PRE-REQUISITE : MAVEN AND JAVA 1.8 ARE INSTALLED
    git pull https://github.com/Jagapd/ApiAutomation.git
        wait for maven dependency files to be imported
    Run
    mvn test command in the project location.
    Results are available in reports folder after test run

Via Docker :
Pre-requisite : Docker is installed.
Pull Docker Image by below command :
docker pull jagapd/api-automation-framework:1.0 
    
  Run docker Image :
    docker run jagapd/api-automation-framework:1.0 mvn test



