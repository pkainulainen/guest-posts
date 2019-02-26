package io.testproject.blog.testcontainers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DisplayName("Starts the docker container once before a test method is run")
class ContainerStartedOnceTest {

    @Container
    private static final BrowserWebDriverContainer BROWSER_CONTAINER = new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions())
            .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING, new File("./target/"));

    private static WebDriver browser;

    @BeforeAll
    static void configureBrowser() {
        browser = BROWSER_CONTAINER.getWebDriver();
    }

    @Test
    @DisplayName("The testproject.io web site should have the correct title")
    void testProjectWebSiteShouldHaveCorrectTitle() {
        browser.get("https://www.testproject.io");
        assertThat(browser.getTitle()).isEqualTo("TestProject - Community Powered Test Automation");
    }

    @Test
    @DisplayName("The testproject.io blog should have the correct title")
    void testProjectBlogShouldHaveCorrectTitle() {
        browser.get("https://blog.testproject.io/");
        assertThat(browser.getTitle()).isEqualTo("TestProject - Test Automation Blog");
    }
}
