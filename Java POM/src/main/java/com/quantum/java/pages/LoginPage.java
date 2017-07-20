/**
 * 
 */
package com.quantum.java.pages;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

import com.quantum.listerners.QuantumReportiumListener;

/**
 * @author Lee Shoham
 * @date Jul 9, 2017
 */
public class LoginPage extends AbstractBasePageCommon {

	public LoginPage() {
		super();
		validateLoginPage();
	}
	
	@FindBy(locator = "login.usernameInput")
	private QAFWebElement usernameInput;
	@FindBy(locator = "login.passwordInput")
	private QAFWebElement passwordInput;
	@FindBy(locator = "login.loginBtn")
	private QAFWebElement loginBtn;

	
	/* (non-Javadoc)
	 * @see com.qmetry.qaf.automation.ui.AbstractTestPage#openPage(com.qmetry.qaf.automation.ui.api.PageLocator, java.lang.Object[])
	 */
	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
	}
	
	public void validateLoginPage() {
		
		QuantumReportiumListener.logStepStart("Validate Login Page");
		getTitle().waitForAttribute("text", "Login", 10000);
	}


	public AccountPage doLogin(String username, String password) {
		QuantumReportiumListener.logStepStart("Login");
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginBtn.click();
		return new AccountPage();
	}

}
