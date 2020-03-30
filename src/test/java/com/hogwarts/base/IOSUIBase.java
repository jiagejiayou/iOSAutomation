package com.hogwarts.base;

import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by JiZhi.Qian on 2019/3/11.
 */
public abstract class IOSUIBase {
    private Logger logger = Logger.getLogger(IOSUIBase.class);
    private String appiumURL = "http://127.0.0.1:4723/wd/hub"; //appium 服务URL地址

    private String platformName = "iOS"; //平台名称
    private String deviceName = "iPhone XR"; //设备名称
    private String bundleId = "com.richardneitzke.MonoBank";//应用包名
    private String udid = "0FBDF47B-77CB-4989-AFE8-A2B50D1AE315";
    private String platformVersion = "12.2";
    private String automationName = "XCUITest";

    protected WebDriver driver;
    protected String testcaseName = "";

    @Before
    public void begin() throws MalformedURLException {
        //设置Desired Capabilities相关参数
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,platformVersion);
        capabilities.setCapability(MobileCapabilityType.APP, bundleId);
        capabilities.setCapability(MobileCapabilityType.UDID, udid);

        driver = new RemoteWebDriver(new URL(appiumURL), capabilities);
        logger.info("Implement drvier instance ...");
    }

    @After
    public void tearDown(){
        logger.info("Automation test " + testcaseName + " finished.");

        if (driver == null) {
            return;
        }

        driver.quit();
    }


}
