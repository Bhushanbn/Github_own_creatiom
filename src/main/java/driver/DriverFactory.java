package driver;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void initDriver() {
        String browser = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

        try {
            if ("chrome".equalsIgnoreCase(browser)) {
                // Ensure ChromeDriver version matches the installed Chrome
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");

                // Fix for macOS/WebSocket "403 Forbidden" issue
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");

                if (headless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1920,1080");
                }

                tlDriver.set(new ChromeDriver(options));

            } else {
                throw new RuntimeException("Unsupported browser: " + browser);
            }

        } catch (Exception e) {
            System.err.println("❌ Failed to start WebDriver for browser: " + browser);
            e.printStackTrace();
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }

    public static void quitDriver() {
        try {
            if (tlDriver.get() != null) {
                tlDriver.get().quit();
                tlDriver.remove();
            }
        } catch (Exception e) {
            System.err.println("⚠️ Error while quitting WebDriver: " + e.getMessage());
        }
    }
}
