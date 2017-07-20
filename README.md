# Quantum Example
This folder contains different examples for using Quantum

## Two Devices example:
This example shows how to open and control two devices in one script.
In this example deviceA send a SMS to deviceB which read and validate it

how does it work?
The devices definition done in the TestNG.xml

    <parameter name="perfecto.capabilities.deviceName" value= "04157DF43344482C"></parameter>
    <parameter name="perfectodevii.capabilities.deviceName" value= "03157DF3A517B128"></parameter>

 These line contains two devices one called perfecto and the second perfectodevii.

In your test, you should work with the getDriver command which return the relevant driver.
To replace between the drivers use the command:
            moreActions.switchToDriver("perfectodevii");
            or
            moreActions.switchToDriver("perfecto");
			
## Java Page Object Model (POM) Example:
This example shows how to create a java project that uses Page Object Model with Quantum.

# BeforeTestUtils
This section contains expiation and actions need to be taken before executing test/flow
com.quantum.tests.beforeTestUtils class contains the implication for the these actions

## 1. Uninstall install app before execution
To reduce false negative and improve the script stability we recommend to install and un install the application before test/flow.

As part of the CI the app should be uploaded to the cloud after the build.
For Example: if you are using a Jenkins you can upload the latest build (APK or IPA) using [Perfecto plugin](https://wiki.jenkins-ci.org/display/JENKINS/MobileCloud+for+Jenkins+Plugin)
![Jenkins Plugin](/img/uploadAppJenkins.png)

After this upload command, the latest apk/ipa will be located in Perfecto repository and you can use the function:
### com.quantum.tests.beforeTestUtils.reinstallApp

## 2. Turn wifi off /on
Mobile device Wifi is not very stable, reset the devices WiFi before the test reduce dramatically networks issue.
In the ###com.quantum.tests.beforeTestUtils.resetWifi  you can find an example how to reset it on ios and Android

# Execution
This section defines the best practice for test executions.

## Tags management
in order to execute part of your scripts you should tag the tests, we suggest the following tags system:

priority - refer to the test priority and set of test it related to
for example:
* P0 - unit test / Dev tests
Set of standalone tests developed by the developers, for mobile app it can be espresso/XCTests.
These tests are part of the app and need to be installed on the device (as an APK or IPA).

* P1 - Smoke tests
Few end-2-End tests which contains the basic flows in yur app , for example install app, login , add item to cart , checkout
it should be executed every hour and should not take more then 15 minuets.

* P2 - Regression tests
The majority of tests which verify your application functionality.
It should be executed every night on the verity of devices and should includes different wind tunnel configuration

* feature tag - In some cases you would like to execute tests based on ## feature or code area that been changed.
For example: new "login" mechanism been implanted so in this case all the tests related to login should be executed

* unique tag - We also recommend to add a unique test name , this id will help to execute and debug the test
but also will help to analyze results and compare with other executions and other devices results

## How to configure the tags
All the test execution tools support TAGS - mosley using annotation
In Example in testNE:

@Test(description="AddItemToCart", groups={"@p1","Purchase","addItemToCart"})

This test conceded to the P1 , Purchase - flow and the unite mame is addItemToCart.



## Jira Tag
Automated test will find bug!! this bug should be fixed but it can take some time.
During this time the automation tests will be executed, in order to avoid 'noise' we want to pause the test.

The [Addon-Jira](https://github.com/Project-Quantum/Addon-Jira) shows how to check the bug status in Jira and not execute the test if it open.
* In order to use it add the jar to your project.
* Add this line to your testNG.xml <listener class-name="com.quantum.jiraAPI.jiraListener"/>
* Add the following tag to your test ## "@JIRA:Bug#"

## Parallel Execution
## Execution maintenance - Wait for device
 TBD
## Execution maintenance - 3 strike out
 TBD
## Execution maintenance - retry mechanism
TBD



