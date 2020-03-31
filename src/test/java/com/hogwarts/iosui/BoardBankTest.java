package com.hogwarts.iosui;

import com.hogwarts.base.IOSUIBase;
import com.hogwarts.base.IOSUITasks;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class BoardBankTest extends IOSUIBase {
    private Logger logger = Logger.getLogger(BoardBankTest.class);

    @Test
    public void runTest() throws Exception {
        testcaseName = "BoardBank iOS UI automation test";

        String name = "Tester";

        IOSUITasks.clickSettingButton(driver);

        IOSUITasks.clickNewGameButton(driver);

        IOSUITasks.clickConfirmNewGameButton(driver);

        IOSUITasks.clickAddButton(driver);

        IOSUITasks.inputName(driver,name);

        IOSUITasks.clickAddPlayerButton(driver);

        Assert.assertTrue("检查选项是否添加成功！", IOSUITasks.isCannonExist(driver));

        IOSUITasks.clickPlayer(driver, name);

        IOSUITasks.clickDeleteMenu(driver);

        Assert.assertTrue("检查首页是否为空！", IOSUITasks.isDashboardEmpty(driver));
    }
}
