## Ridecell SDET Assessment

In this tutorial you are going to learn <b>How to Automate UI and REST API's in Java</b> using <b>RESTAssured Library</b> and verify the result

The tech stack used for this tutorial are:
1. **JAVA** as the programming language for writing test code
2. **TestNg** as the framework
3. **Maven** as the build tool
4. **Eclipse** as the preferred IDE for writing java code.

#### Getting Started
Setup your machine.
1. Install JavaSE 1.8
2. Install Eclipse IDE
3. Configure Maven pom.xml with TestNG, RESTAssured, Selenium

#### Cloning & Importing the Project
1. Clone the project from ```git clone https://github.com/vinaykumarvvs/api-automation-tutorial.git```
2. Import the project (RidecellSDET) in Eclipse ```File -> New -> Project from Existing Sources -> Browse Project Location -> build.gradle```
3. Now click on ```auto import -> Ok``` wait until the IntelliJ downloads all the dependencies
4. Setup the project in your IDE

#### Running tests
1. You can run the tests directly from the Eclipse, by right-clicking at **testNG.xml” file and **Run test > TestNG**.
2. For Windows users: ```Maven clean build runTests```

---


#### Test Cases
1.	**Visit https://github.com/django this page using selenium**
2.	**Move to Repositories section**
3.	**Copy/Fetch all repository names and description [Actual Data]** https://github.com/priyankazade/selenuim-api-testing/RidecellSDET\src\test\java\com\ridecell\testcases\RepositoryFetching.java
4.	**Find API’s to get all repositories names and description under GitHub link i.e. https://github.com/django [Expected Data]** Send a get Request of an API and store the Response
5.	**Assert Actual data with Expected data.** Chaining Requests and validate Response Body

## Reports

```
Html Reports at the end of execution is available in /target folder with the name `emailable-report.html'
with test name, test status and test data

