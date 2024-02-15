# technical-task-ui
A test framework that is based on the Page Object Model has been created to implement the task. Tests retrieve data from @DataProvider to be executed on different screen sizes. For the failed tests all evidences are captured and stored for further investigation.

**Technologies:** Java, Selenium WebDriver, Maven, TestNG

### Project Structure

```bash
technical-task-ui
├── src                       
│   │   
│   ├── main
│   │   └─── java
│   │        ├─── components <- reusable page components
│   │        ├─── pages      <- page objects
│   │        └─── utils      <- framework helpers: webdriver methods, enums
│   └── test
│       ├─── java
│       │    ├─── tests      <- tests
│       │    └─── utils      <- test helpers: listeners
│       └─── resources
│            └─── artifacts  <- failed tests artifacts: screenshot, html code     
├── pom.xml
└── README.md
```

Main classes:

```/main/java/pages/BasePage``` - methods to work with web elements and page components

```/main/java/utils/WebDriverManager``` - methods to manage and configure the webdriver instance

```/test/java/tests/BaseTest``` - setup and teardown methods


### Execution

Execute tests without browser GUI:
```shell
mvn clean test -Dheadless=true
```

Execute tests with browser GUI:
```shell
mvn clean test
```


If any test fails, corresponding screenshots and HTML source codes can be found under ```/test/java/resources/artifacts/``` folder. 
