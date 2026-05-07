# java-salesforce-automation

> A scalable, maintainable UI automation framework built from scratch — using Java, Selenium WebDriver, TestNG, and the Page Object Model (POM) design pattern.

---

## 📌 Project Overview

This project is a **production-style test automation framework** designed to automate Salesforce UI workflows. It was architected and implemented independently using IntelliJ IDEA and Maven, following industry-standard design principles to ensure the codebase remains clean, scalable, and easy to maintain as test coverage grows.

The framework is built around the **Page Object Model (POM)** pattern, with a clear separation between page interactions, test logic, and shared utilities — making it straightforward to extend with new test scenarios without touching existing code.

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| **Java 17+** | Core programming language |
| **Selenium WebDriver 4.x** | Browser automation |
| **TestNG** | Test execution and assertions |
| **WebDriverManager** | Automatic browser driver management |
| **Maven** | Build tool and dependency management |

---

## ✨ Features & Highlights

- **Zero manual driver setup** — WebDriverManager handles ChromeDriver/GeckoDriver automatically at runtime
- **Page Object Model (POM)** — Each UI page is encapsulated in its own class, isolating locators from test logic
- **Reusable base classes** — `BasePage` and `BaseTest` eliminate boilerplate and keep test classes focused
- **Centralized configuration** — Environment-level values (URL, credentials, timeouts) managed via `config.properties`
- **Explicit waits** — Robust synchronization using `WebDriverWait` to handle Salesforce's dynamic UI reliably
- **Clean test reporting** — TestNG generates HTML/XML test reports out of the box
- **Cross-browser ready** — Driver factory designed to support multiple browsers via a single config switch
- **Scalable by design** — Adding new pages or test cases requires no changes to existing code

---

## ✅ Prerequisites

Ensure the following are installed before setting up the project:

- **Java JDK 17+**
  Verify: `java -version`

- **Apache Maven 3.8+**
  Verify: `mvn -version`

- **Google Chrome** (or Firefox) — latest stable version

- **IntelliJ IDEA** (Community or Ultimate)
  - TestNG plugin enabled (bundled by default in most versions)

- **Git**
  Verify: `git --version`

---

## ⚙️ Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/saikiranmunagla/java-salesforce-automation.git
cd java-salesforce-automation
```

### 2. Install Dependencies

```bash
mvn clean install -DskipTests
```

Maven resolves all dependencies declared in `pom.xml` automatically. No manual driver downloads required.

### 3. Configure the Environment

Edit `src/main/resources/config.properties`:

```properties
# Browser configuration
browser=chrome

# Salesforce environment
base.url=https://your-salesforce-instance.lightning.force.com

# Credentials (use environment variables in CI/CD pipelines)
sf.username=your_username@example.com
sf.password=your_password

# Timeouts (in seconds)
implicit.wait=10
explicit.wait=15
```

> ⚠️ **Security note:** Never commit real credentials to version control. In CI/CD environments, inject credentials as environment variables or use a secrets manager.

---

## ▶️ Running Tests

### Run All Tests via Maven

```bash
mvn test
```

### Run a Specific TestNG Suite

```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run a Specific Test Class

```bash
mvn test -Dtest=LoginTest
```

### Run with a Different Browser

```bash
mvn test -Dbrowser=firefox
```

### View Test Reports

After execution, TestNG generates reports at:

```
target/surefire-reports/index.html
```

---

## 🔍 Example Test Walkthrough

### Scenario: Verify Successful Salesforce Login

**Page Object — `LoginPage.java`**

```java
public class LoginPage extends BasePage {

    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton   = By.id("Login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public HomePage clickLogin() {
        click(loginButton);
        return new HomePage(driver);
    }
}
```

**Test Class — `LoginTest.java`**

```java
public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login to Salesforce with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(config.get("sf.username"));
        loginPage.enterPassword(config.get("sf.password"));

        HomePage homePage = loginPage.clickLogin();

        Assert.assertTrue(homePage.isLoaded(), "Home page did not load after login");
    }
}
```

This structure keeps tests readable at a glance — no locator noise in test logic, and any UI changes only require updating the relevant Page Object class.

---

## 🔮 Future Improvements

- [ ] **Data-driven testing** — Integrate TestNG `@DataProvider` or Apache POI for Excel-based test data
- [ ] **CI/CD pipeline** — Add GitHub Actions workflow to trigger test runs on push/pull request
- [ ] **Extent Reports** — Replace default TestNG reports with richer HTML reports including screenshots on failure
- [ ] **Parallel execution** — Configure TestNG XML to run tests in parallel across multiple browsers
- [ ] **API layer** — Add RestAssured for Salesforce REST API tests to create test data programmatically
- [ ] **Docker support** — Run tests in a containerized headless Chrome environment via Selenium Grid

---

## 👤 Author

**Sai Kiran Munagala**
