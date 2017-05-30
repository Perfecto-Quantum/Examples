# Quantum Example
This Folder contains different examples for using Quantum

##Two Devices example:
This example show how to open and control two devices in one script.
In this example deviceA send a SMS to deviceB which read and validate it

how does it work ?
The devices definition done in the TestNG.xml

    <parameter name="perfecto.capabilities.deviceName" value= "04157DF43344482C"></parameter>
    <parameter name="perfectodevii.capabilities.deviceName" value= "03157DF3A517B128"></parameter>

 These line contains two devices one called perfecto and the second perfectodevii.

In your test, you should work with the getDriver command which return the relevant driver.
To replace between the driver use the command:
            moreActions.switchToDriver("perfectodevii");
            or
            moreActions.switchToDriver("perfecto");


#BeforeTestUtils
This class contains functions to use before execution test
## 1. Uninstall install app before execution
To reduce false negative and improve the script stability we recommend to install and un install the application before test/flow.

As part of the CI the app should be uploaded to the cloud after the build.
For Example: if you are using a Jenkins you can upload the latest build (APK or IPA) using [Perfecto plugin](https://wiki.jenkins-ci.org/display/JENKINS/MobileCloud+for+Jenkins+Plugin)
![Jenkins Plugin](/img/uploadAppJenkins.png)

After this upload command, the latest apk/ipa will be located in Perfecto repository and you can use the function :
### com.quantum.tests.beforeTestUtils.reinstallApp

## 2. Turn wifi off /on
Mobile device Wifi is not very stable ,reset the devices WiFi before the test reduce dramatically networks issue.
In the ###com.quantum.tests.beforeTestUtils.resetWifi  you can find an example how to reset it on ios and Android
