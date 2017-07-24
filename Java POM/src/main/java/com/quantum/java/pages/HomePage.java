/**
 * 
 */
package com.quantum.java.pages;

import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.quantum.listerners.QuantumReportiumListener;

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
		QuantumReportiumListener.logStepStart("Validate Home Page");
		getTitle().waitForAttribute("text", "PHPTRAVELS | Travel Technology Partner", 10000);
	}


	public LoginPage navToLoginPage() {
		QuantumReportiumListener.logStepStart("Navigate to login page");
		clickMenu();
		clickMyAccount();
		return clickLogin();
	}

	public CreateAccountPage navToCreateAccountPage() {
		QuantumReportiumListener.logStepStart("Navigate to create account page");
		clickMenu();
		clickMyAccount();
		return clickSignUp();
	}

	
	public HotelsPage navToHotelsPage() {
		clickMenu();;
		return clickHotels();
	}
}
