package com.quantum.tests;

import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import com.quantum.generic.moreActions;
import com.quantum.utils.DeviceUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by uzie on 1/23/17.
 */
public class twoDevices extends WebDriverTestCase {


    @Test(description="SendSMS", groups={"@twoDevicesSMS"})
    public void SendSMS() {

        final String PhoneBNum = "13392344376";
        final String msg = "Test Message";

        moreActions.switchToDriver("perfecto");
        openApp();



        moreActions.switchToDriver("perfectodevii");
        openApp();



        //device A (send SMS)
        moreActions.switchToDriver("perfecto");

        getDriver().findElement(By.xpath("//*[@resource-id=\"com.android.mms:id/floating_action_button\"]")).click();
        getDriver().findElement(By.xpath("//*[@resource-id=\"com.android.mms:id/recipients_editor_to\"]")).sendKeys(PhoneBNum);
        getDriver().findElement(By.xpath("//*[@resource-id=\"com.android.mms:id/editor_body\"]")).sendKeys(msg);
        getDriver().findElement(By.xpath("//*[@resource-id=\"com.android.mms:id/editor_body\"]")).sendKeys(msg);
        getDriver().findElement(By.xpath("//*[@resource-id=\"com.android.mms:id/send_button\"]")).click();


        //deviceB
        moreActions.switchToDriver("perfectodevii");

         DeviceUtils.switchToContext("NATIVE_APP");
        DeviceUtils.startApp("com.android.mms", "identifier");

        getDriver().findElement(By.xpath("//*[@text=\""+msg+"\"]"));


    }


    private void openApp()
    {

        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        DeviceUtils.switchToContext("NATIVE_APP");
        try {
            DeviceUtils.closeApp("com.android.mms", "identifier");

        }catch (Exception e)
        {
            //nothing
        }
        DeviceUtils.startApp("com.android.mms", "identifier");
    }



    private void sleep(int ms)

    {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}
