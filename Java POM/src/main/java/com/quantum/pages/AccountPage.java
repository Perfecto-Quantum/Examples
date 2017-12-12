/**
 * 
 */
package com.quantum.pages;

import org.testng.Assert;  

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

import static com.quantum.listerners.QuantumReportiumListener.logAssert;
import static com.quantum.listerners.QuantumReportiumListener.logStepStart;

/**
 * @author Lee Shoham
 * @date Jul 9, 2017
 */
public class AccountPage extends AbstractBasePageCommon {

	
	@FindBy(locator = "account.title")
	private QAFWebElement title;
	@FindBy(locator = "account.welcomeMsg")
	private QAFWebElement welcomeMsg;

	
	public AccountPage() {
		super();
		validateAccountPage();
	}
	
	@Override
	protected void openPage(PageLocator locator, Object... args) {
	}
	
	public void validateAccountPage() {
		title.waitForPresent(5000);

	}


	public void validateAccountName(String name) {
		
		String accountName = welcomeMsg.getText();
		try {
			Assert.assertEquals(accountName, "Hi, " + name);
	   		logAssert("validate account name. Expected " + name + " actual " + accountName, true);
	   	 
		} catch (AssertionError n) {
    		logAssert("validate account name. Expected " + name + " actual " + accountName, false);
    		System.out.println("Expected account name "+ name + " actual " + accountName);
    		throw n;
    	}
	}
	

	public HotelsPage navToHotelsPage() {
		logStepStart("Navigate to hotels page");
		clickHomeMenu();
		return clickHotels(); 
	}


}
