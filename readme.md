##RUNGWAY - selenium-web


###About Project
```
This project is build on the basis of Page Object Model, Page Object Model is a design pattern to create Object 
Repository for web UI elements. Under this model, for each page in the application, there should be corresponding page 
class. This Page class will find the UI elements of that page and also contains Page methods which perform operations 
on those UI elements.

Advantages of POM are 
  - Page Object Patten says operations and flows in the UI should be separated from verification. This concept makes our 
    code cleaner and easy to understand.
  - The Second benefit is the object repository is independent of test cases, so we can use the same object repository 
  for a different purpose with different tools. For example, we can integrate POM with TestNG/JUnit for functional 
  Testing.
  - Code becomes less and optimized because of the reusable page methods in the POM classes
  
Reporting:
- We use allure report, to generate a report locally
  - Via commandline > enter "allure serve allure-results", this should generate and open the report.
    Note: this will only work when tests are ran via command line eg: mvn clean test
- If running via Jenkins, there will allways be a report attached to a specific build. This is generated part of build.   
```

###Prerequisites
```
- Download selenium server jar from http://www.seleniumhq.org
- Download the latest chrome driver 
- Make sure JAVA_HOME is set correctly for your mac
```

###Download GIT Repository
```
- Git clone selenium-web repo
- Once you have clone the repo make sure you load the external libraries from maven
```

###Download External Libraries Based on POM Files
```
- Right click project directory via IntelliJ navigate to maven > reimport, this should re import all the dependencies 
```

###Run tests Via Jenkins
```
- Go to Jenkins > select selenium-web job > Build with parameters
    - Select the browser, at the moment we only have chrome. We willa dd more to this going forward
    - Select platform eg: Mac > Build 
```

###Run tests via IDE
```
- Start selenium standalone server  
- Run below via terminal
    - mvn clean test 

```
###Trouble Shooting on failed tests
```
1. Check the logs, look for error.
2. If running test via jenkins allure report should have the error and screenshots for all the failed tests. 
   This should give enough clarity on the test failure.
3. If you still cannot find out ** SHOUT AT ROHITH **
```