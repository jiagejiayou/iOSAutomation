package com.hogwarts.base;

import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class IOSUIBase {
    private Logger logger = Logger.getLogger(IOSUIBase.class);
    private String propFileName = "iosautomation.properties";

    protected WebDriver driver;
    protected String testcaseName = "";

    @Before
    public void begin() throws MalformedURLException {
        //加载属性文件
        Properties prop = loadFromEnvProperties(propFileName);
        String appiumURL = prop.getProperty("appiumURL"); //appium 服务URL地址
        String platformName = prop.getProperty("platformName"); //平台名称
        String deviceName = prop.getProperty("deviceName"); //设备名称
        String bundleId = prop.getProperty("bundleId");//应用包名
        String udid = prop.getProperty("udid");
        String platformVersion = prop.getProperty("platformVersion");
        String automationName = prop.getProperty("automationName");

        logger.info("appiumURL = " + appiumURL);
        logger.info("platformName = " + platformName);
        logger.info("deviceName = " + deviceName);
        logger.info("bundleId = " + bundleId);
        logger.info("udid = " + udid);
        logger.info("platformVersion = " + platformVersion);
        logger.info("automationName = " + automationName);

        //设置Desired Capabilities相关参数
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,platformVersion);
        capabilities.setCapability(MobileCapabilityType.APP, bundleId);
        capabilities.setCapability(MobileCapabilityType.UDID, udid);

        driver = new RemoteWebDriver(new URL(appiumURL), capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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

    //加载配置文件
    private Properties loadFromEnvProperties(String propFileName) {
        Properties prop = null;

        String path = System.getProperty("user.home");

        //读入envProperties属性文件
        try {
            prop = new Properties();
            InputStream in = new BufferedInputStream(
                    new FileInputStream(path + File.separator + propFileName));
            prop.load(in);
            in.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
            logger.error("Load config file fail, please check " + path + " to confirm if the "
                    + propFileName + " file exist!");
        }

        return prop;
    }


}
