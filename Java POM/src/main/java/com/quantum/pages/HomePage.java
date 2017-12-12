/**
 * 
 */
package com.quantum.pages;

import com.qmetry.qaf.automation.ui.api.PageLocator;

import static com.quantum.listerners.QuantumReportiumListener.logStepStart;

/**
 * @author Lee Shoham
 * @date Jul 9, 2017
 */
public class HomePage extends AbstractBasePageCommon {

	public HomePage() {
		super();
		validateHomePage();
	}


	
	@Override
	protected void openPage(PageLocator pageLocator, Object... args) {
	}


	public void validateHomePage() {
		getTitle().waitForAttribute("text", "PHPTRAVELS | Travel Technology Partner", 10000);
	}


	public LoginPage navToLoginPage() {
		clickMenu();
		clickMyAccount();
		return clickLogin();
	}

	public CreateAccountPage navToCreateAccountPage() {
		
		logStepStart("Navigate to create account");
		clickMenu();
		clickMyAccount();
		return clickSignUp();
	}

	
	public HotelsPage navToHotelsPage() {
		logStepStart("Navigate to hotels page");
		
		clickHomeMenu();
		return clickHotels();
	}
}
