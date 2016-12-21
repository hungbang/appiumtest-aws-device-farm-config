package scenarios;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumTest {

    private static final String APP_PACKAGE = "com.neolab.app_management";
    private static final String APP_ACTIVITY_VALUE = "com.neolab.app_management.features.auth.LoginActivity";
    private static final String APP_ACTIVITY_MAIN = "com.neolab.app_management.features.manager.ManagerActivity";
    private static final String APP_ACTIVITY_LABLE = "appPackage";
    private static final String APP_PACKAGE_LABLE = "appActivity";
    private static final String LOCALHOST = "http://0.0.0.0:4723/wd/hub";
    private static final String HOST = "http://127.0.0.1:4723/wd/hub";
    
    private static AndroidDriver driver;

    @BeforeClass
    public static void setup(){
//        File appPath = new File("/Users/KAI/workstation/AN_AppiumTest/apps/app_management-debug.apk");
        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
//        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
//        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Emulator");
//        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
//        cap.setCapability(MobileCapabilityType.APP, appPath.getAbsolutePath());
//        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0");
//        cap.setCapability(MobileCapabilityType.BROWSER_NAME,Platform.ANDROID);

        try {
            driver = new AndroidDriver(new URL(HOST), cap);
            driver.startActivity(APP_PACKAGE, APP_ACTIVITY_VALUE);
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCase1(){
        Assert.assertNotNull(driver.getContext());
    }

    @Test
    public void tvCurrentStateisDisplayTestCase(){
        WebElement tvCurrentState = getTvCurrentState();
        Assert.assertTrue(tvCurrentState.isDisplayed());
    }

    private WebElement getTvCurrentState() {
        WebElement username = driver.findElement(By.id("com.neolab.app_management:id/ed_login_id"));
        username.click();
        username.sendKeys("username");
        driver.hideKeyboard();
        WebElement pass = driver.findElement(By.id("com.neolab.app_management:id/ed_password"));
        pass.click();
        pass.sendKeys("password");
        driver.hideKeyboard();
        driver.findElementById("cb_agree").click();
        driver.findElement(By.id("com.neolab.app_management:id/btn_login")).click();
        return driver.findElement(By.id("com.neolab.app_management:id/tv_current_state"));
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
