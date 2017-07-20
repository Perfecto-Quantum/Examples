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
public class CreateAccountPage extends AbstractBasePageCommon {

	public CreateAccountPage() {
		super();
		validateCreateAccountPage();
	}

	@FindBy(locator = "createAccount.firstName")
	private QAFWebElement firstName;
	@FindBy(locator = "createAccount.lastName")
	private QAFWebElement lastName;
	@FindBy(locator = "createAccount.mobileNumber")
	private QAFWebElement mobileNumber;
	@FindBy(locator = "createAccount.email")
	private QAFWebElement email;
	@FindBy(locator = "createAccount.password")
	private QAFWebElement password;
	@FindBy(locator = "createAccount.confirmPassword")
	private QAFWebElement confirmPassword;
	@FindBy(locator = "createAccount.signUpBtn")
	private QAFWebElement signUpBtn;

	@Override
	protected void openPage(PageLocator locator, Object... args) {
	}

	public void validateCreateAccountPage() {
		QuantumReportiumListener.logStepStart("Validate Create Account Page");
		getTitle().waitForAttribute("text", "Register", 10000);
	}

	public AccountPage createAccount(String first, String last, String mobile, String em, String pass, String confPass) {
		QuantumReportiumListener.logStepStart("Create account");
		firstName.sendKeys(first);
		lastName.sendKeys(last);
		mobileNumber.sendKeys(mobile);
		email.sendKeys(em);
		password.sendKeys(pass);
		confirmPassword.sendKeys(confPass);
		signUpBtn.click();
		
		return new AccountPage();
	}

}
