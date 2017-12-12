/**
 * 
 */
package com.quantum.pages;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

import static com.quantum.listerners.QuantumReportiumListener.logStepStart;


/**
 * @author Lee Shoham
 * @date Jul 9, 2017
 */
public class LoginPage extends AbstractBasePageCommon {

	
	@FindBy(locator = "login.usernameInput")
	private QAFWebElement usernameInput;
	@FindBy(locator = "login.passwordInput")
	private QAFWebElement passwordInput;
	@FindBy(locator = "login.loginBtn")
	private QAFWebElement loginBtn;

	public LoginPage() {
		super();
		validateLoginPage();
	}

	
	/* (non-Javadoc)
	 * @see com.qmetry.qaf.automation.ui.AbstractTestPage#openPage(com.qmetry.qaf.automation.ui.api.PageLocator, java.lang.Object[])
	 */
	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
	}
	
	public void validateLoginPage() {
		getTitle().waitForAttribute("text", "Login", 10000);
	}


	public AccountPage doLogin(String username, String password) {
		
		logStepStart("Login user " + username);
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginBtn.click();
		return new AccountPage();
	}

}
