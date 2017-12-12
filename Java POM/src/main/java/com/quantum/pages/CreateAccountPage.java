/**
 * 
 */
package com.quantum.pages;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

import static com.quantum.utils.ReportUtils.logStepStart;

/**
 * @author Lee Shoham
 * @date Jul 9, 2017
 */
public class CreateAccountPage extends AbstractBasePageCommon {

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


	public CreateAccountPage() {
		super();
		validateCreateAccountPage();
	}

	
	@Override
	protected void openPage(PageLocator locator, Object... args) {
	}

	public void validateCreateAccountPage() {
		getTitle().waitForAttribute("text", "Register", 10000);
	}

	public AccountPage createAccount(String first, String last, String mobile, String em, String pass, String confPass) {
		
		logStepStart("Create account");
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
