package com.quantum.tests;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.quantum.utils.DeviceUtils;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by uzie on 5/30/17.
 */
public class beforeTestUtils {

    public static void  reinstallApp(String appLocation,String ApplicationName,RemoteWebDriver d)
    {

        //Params:
        //@location refers to the location in Perfecto repository for example:
        // PRIVATE:myCompany/myApp.apk

        //@appName - the app full name

        try {
            uninstallApp(ApplicationName, d);

        } catch (Exception e) {
            //nothing - app was not on the device

        }        try {
           installApp(appLocation, d);
        } catch (Exception e) {
            System.out.println("not install app");
        }
        startApp(ApplicationName, d);

    }



    private static void uninstallApp(String app, RemoteWebDriver d)
    {
        //install app is part of Quantum but I added it here so you will be able to use it without Quantum
        // if you are Quantum user don't use this function but ue:
       // DeviceUtils.uninstallApp

        HashMap params = new HashMap();
        params.put("by", "name");
        params.put("app", app);
        d.executeScript("mobile:application:uninstall",  params);
    }

    private static void installApp(String appLocation, RemoteWebDriver d)
    {
        //install app is part of Quantum but I added it here so you will be able to use it without Quantum
        // if you are Quantum user don't use this function but ue:
        // DeviceUtils.installApp

        HashMap params = new HashMap();
        params.put("file", appLocation);
        params.put("instrument", "noinstrument");
        d.executeScript("mobile:application:install", params);

    }

    private static void startApp(String app, RemoteWebDriver d)
    {
        //startApp  is part of Quantum but I added it here so you will be able to use it without Quantum
        // if you are Quantum user don't use this function but ue:
        // DeviceUtils.startApp
        HashMap params = new HashMap();

        params.put("by", "name");
        params.put("app", app);
        d.executeScript("mobile:application:open", params);

    }
}
