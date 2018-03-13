package com.quantum.ios.steps;

import java.util.HashMap;

import org.openqa.selenium.Rectangle;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.steps.CommonStepsIntr.GeneralSteps;
import com.quantum.utils.DeviceUtils;
import com.quantum.utils.Locator;

public class IOSStepsLib implements GeneralSteps {

	@Override
	public void clickAlarm() {
		CommonStep.click("calculator.mainscreen.alarm");
	}
	@Override
	public void startClock() {
		DeviceUtils.startApp("Clock", "name");
		DeviceUtils.switchToContext("NATIVE");
	}
	@Override
	public void clickWorldClock() {
		CommonStep.click("clock.mainscreen.worldclock.btn");
		
	}
	
	
	
	@QAFTestStep(description="I click on the text {text}")
	public void clickText(String text) {
		HashMap<String, Object> params1 = new HashMap<>();
		params1.put("label", text);
		params1.put("timeout", "20");
		params1.put("threshold", "100");
		params1.put("ignorepunct", "punct");
		new WebDriverTestBase().getDriver().executeScript("mobile:button-text:click", params1);
	}
	
	@Override
	public void verifyCityAdded(String city) {
		QAFExtendedWebElement cityLbl = new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("clock.worldclock.city.lbl"),city , city ));
		cityLbl.assertText(city, "The added city does not match.");
	}
	@Override
	public void deleteCity(String city) {
		Locator.slideObjectLeft(String.format(ConfigurationManager.getBundle().getString("clock.worldclock.city.obj"),city , city ));
		CommonStep.click(String.format(ConfigurationManager.getBundle().getString("clock.worldclock.cityDelete.btn"),city , city ));
	}
	@Override
	public void addLocation(String location) {
		CommonStep.click("clock.worldclock.addNew.btn");
		QAFExtendedWebElement cityLbl = new QAFExtendedWebElement("clock.worldclock.search.txt");
		cityLbl.clear();
		cityLbl.sendKeys(location);
		clickText(location+",");	
	}
	
}
