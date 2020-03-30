package com.hogwarts.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IOSUITasks {

    private static Logger logger = Logger.getLogger(IOSUITasks.class);

    public static void clickSettingButton(WebDriver driver) throws UINotFoundException {
        logger.info("单击 Setting 按钮");
        WebElement btn = findObject("//XCUIElementTypeNavigationBar[@name='BoardBank']/XCUIElementTypeButton[1]", Type.XPATH, driver);
        btn.click();
    }

    public static void clickAddButton(WebDriver driver) throws UINotFoundException {
        logger.info("单击 Add 按钮");
        WebElement btn = findObject("Add", Type.ID, driver);
        btn.click();
    }

    public static void clickNewGameButton(WebDriver driver) throws UINotFoundException {
        logger.info("单击 New Game 按钮");
        WebElement btn = findObject("//XCUIElementTypeStaticText[@name='New Game']", Type.XPATH, driver);
        btn.click();
    }

    public static void clickConfirmNewGameButton(WebDriver driver) throws UINotFoundException {
        logger.info("单击 New Game 按钮");
        WebElement btn = findObject("//XCUIElementTypeButton[@name='New Game']", Type.XPATH, driver);
        btn.click();
    }

    public static void clickAddPlayerButton(WebDriver driver) throws UINotFoundException {
        logger.info("单击 Add Player 按钮");
        WebElement btn = findObject("//XCUIElementTypeStaticText[@name='Add Player']", Type.XPATH, driver);
        btn.click();
    }

    public static void clickPlayer(WebDriver driver, String name) throws UINotFoundException {
        logger.info("单击 " + name + " 元素");
        WebElement icon = findObject(name, Type.ID, driver);
        icon.click();
    }

    public static void clickDeleteMenu(WebDriver driver) throws UINotFoundException {
        logger.info("单击 Delete 按钮");
        WebElement menu = findObject("Delete", Type.ID, driver);
        menu.click();
    }

    public static void inputName(WebDriver driver, String name) throws UINotFoundException {
        logger.info("输入 " + name);
        WebElement txt = findObject("//XCUIElementTypeTextField[contains(@value, 'Player')]", Type.XPATH, driver);
        txt.sendKeys(name);
    }

    public static boolean isCannonExist(WebDriver driver) throws UINotFoundException {
        logger.info("检查 Cannon 图标是否存在 ");
        boolean result = false;
        WebElement cat = findObject("cannon", Type.ID, driver);
        String txt = cat.getAttribute("name");
        if(txt.equalsIgnoreCase("cannon")){
            result = true;
        }

        return result;
    }


    private static WebElement findObject(String selector, Type type, WebDriver driver) throws UINotFoundException {
        return findObject(selector, type, driver, 30);
    }

    /**
     * 轮询查找页面元素
     *
     * @param selector 定位标记
     * @param type     类型 ： XPATH, ID
     * @param driver   WebDriver
     * @param waitMax  最长等待时间
     * @return
     * @throws UINotFoundException
     */
    private static WebElement findObject(String selector, Type type, WebDriver driver, int waitMax) throws UINotFoundException {
        int size = 0;
        WebElement obj = null;
        long start = System.currentTimeMillis();
        long now = System.currentTimeMillis();
        while (((now - start) < waitMax * 1000) && (size == 0)) {
            Tools.wait(2);
            if (type == Type.XPATH)
                obj = driver.findElement(By.xpath(selector));
            else
                obj = driver.findElement(By.id(selector));
            now = System.currentTimeMillis();
        }

        if (obj == null) {
            throw new UINotFoundException();
        } else {
            return obj;
        }
    }
}
