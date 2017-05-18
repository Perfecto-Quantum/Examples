# Quantum Example
This Folder contains different examples for using Quantum

Two Devices example:
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


\
