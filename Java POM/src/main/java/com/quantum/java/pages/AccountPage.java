/**
 * 
 */
package com.quantum.java.pages;

import org.testng.Assert;  

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

import com.quantum.listerners.QuantumReportiumListener;

/**
 * @author Lee Shoham
 * @date Jul 9, 2017
 */
public class AccountPage extends AbstractBasePageCommon {

	public AccountPage() {
		super();
		validateAccountPage();
	}
	

	@FindBy(locator = "account.welcomeMsg")
	private QAFWebElement welcomeMsg;


	
	@Override
	protected void openPage(PageLocator locator, Object... args) {
	}
	
	public void validateAccountPage() {
		getTitle().assertAttribute("text", "My Account");
	}


	public void validateAccountName(String name) {
		QuantumReportiumListener.logStepStart("Validate Account Page");
		Assert.assertEquals(welcomeMsg.getText(), "Hi, " + name);
	}
	

	public HotelsPage navToHotelsPage() {
		QuantumReportiumListener.logStepStart("Navigate to Hotels page");
		clickMenu();
		return clickHotels(); 
	}


}
