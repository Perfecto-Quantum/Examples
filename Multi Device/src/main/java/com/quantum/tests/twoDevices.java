package com.quantum.tests;

import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import com.quantum.generic.moreActions;
import com.quantum.utils.CloudUtils;
import com.quantum.utils.ConfigurationUtils;
import com.quantum.utils.DeviceUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by uzie on 1/23/17.
 */
public class twoDevices extends WebDriverTestCase {


    /**
     *  Send SMS from one device (perfecto) to second device (perfectodevii)
     *
     */



    @Test(description="SendSMS", groups={"@twoDevicesSMS"})
    public void SendSMS() {


        //String PhoneBNum = "13392344376";
        final String msg = "Test Message";

        moreActions.switchToDriver("perfecto");
        openApp();

        moreActions.switchToDriver("perfectodevii");
        openApp();
        String PhoneBNum = moreActions.getDevicePhoneNumber();

        //device A (send SMS)
        moreActions.switchToDriver("perfecto");


        getDriver().findElement("newMessage.buton").click();
        getDriver().findElement("recipients.editor").sendKeys(PhoneBNum);
        getDriver().findElement("editorbody.text").sendKeys(msg);
        getDriver().findElement("send.button").click();


        //deviceB
        moreActions.switchToDriver("perfectodevii");
        DeviceUtils.assertVisualText(msg);

    }


    private void openApp()
    {

        String appPackage = (String)getDriver().getCapabilities().getCapability("appPackage");
        try {
            DeviceUtils.closeApp(appPackage, "identifier");

        }catch (Exception e)
        {
            //nothing
        }
        DeviceUtils.startApp(appPackage, "identifier");
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
