# Web Automation Java Selenium

This project is a web automation framework built using Java and Selenium. It is designed to facilitate automated testing of web applications with a modular and reusable structure.

## Features

- **Page Object Model (POM):** Organized structure for managing web elements and actions.
- **TestNG Integration:** Supports parallel test execution and advanced test configurations.
- **Custom Utilities:** Includes utilities for browser management, assertions, screenshots, and reporting.
- **Extent Reports:** Generates detailed and visually appealing test reports.
- **Configurable Properties:** Centralized configuration using `config.properties`.

## Project Structure

```
src/
  main/
    java/
      com/
        automation/
          pages/         # Page classes for web elements and actions
          utils/         # Utility classes for common functionalities
  test/
    java/
      com/
        automation/
          tests/         # Test classes for automated test cases
    resources/           # Configuration files and TestNG XML
```

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- Selenium WebDriver

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd Web-Automation-Java-Selenium
   ```

3. Install dependencies:
   ```bash
   mvn clean install
   ```

4. Update the `config.properties` file with the required configurations.

## Running Tests

1. Execute tests using Maven:
   ```bash
   mvn test
   ```

2. View the test reports in the `test-output` directory.

## Reporting

- Test execution results are available in the `test-output` folder.
- Extent Reports can be found at `test-output/extent-report.html`.

## Contributing

Contributions are welcome! Please follow the standard Git workflow:

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Submit a pull request.
