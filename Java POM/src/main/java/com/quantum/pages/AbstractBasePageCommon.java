/**
 * 
 */
package com.quantum.pages;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

import static com.quantum.listerners.QuantumReportiumListener.logStepStart;


/**
 * @author Lee Shoham
 * @date Jul 18, 2017
 */
public abstract class AbstractBasePageCommon extends WebDriverBaseTestPage<WebDriverTestPage> {


	@FindBy(locator = "base.pageTitle")
	private QAFWebElement title;
	@FindBy(locator = "base.menuDropDown")
	private QAFWebElement menuDropDown;
	@FindBy(locator = "base.accountDropDown")
	private QAFWebElement accountDropDown;
	@FindBy(locator = "base.menuMyAccount")
	private QAFWebElement myAccount;
	@FindBy(locator = "base.menuLogin")
	private QAFWebElement login;
	@FindBy(locator = "base.menuSignUp")
	private QAFWebElement signUp;
	@FindBy(locator = "base.hotels")
	private QAFWebElement hotels;
	@FindBy(locator = "base.menuLogout")
	private QAFWebElement logout;	
	@FindBy(locator = "base.homeMenu")
	private QAFWebElement homeMenu;

	public AbstractBasePageCommon() {
		super();
	}

	
	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
		
	}
	
	public QAFWebElement getTitle() {
		return title;
	}
	
	public void clickMenu() {
		menuDropDown.click();
	}
	
	public void clickHomeMenu() {
		homeMenu.click();
	}
	
	public HotelsPage clickHotels() {
		hotels.click();
		return new HotelsPage();
	}

	public void clickMyAccount() {
		myAccount.click();
	}

	public LoginPage clickLogin() {
		login.click();
		return new LoginPage();
	}
	
	public CreateAccountPage clickSignUp() {
		signUp.click();
		return new CreateAccountPage();
	}
	
	public boolean isLoggedIn() {

		boolean res = false;
		clickMenu();
		try {
			myAccount.isDisplayed();
		} catch(Exception e) {
			res = true;
		}
		clickMenu();
		return res;
	}

	public void clickAccountDropDown() {
		
		accountDropDown.click();
	}
	
	public void clickLogout() {
		logout.click();
		
	}
	
	public LoginPage logout() {
		logStepStart("Logout");
		clickMenu();
		clickAccountDropDown();
		clickLogout();
		return new LoginPage();
	}


	
}
