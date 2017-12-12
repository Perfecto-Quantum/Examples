/**
 * 
 */
package com.quantum;

import java.util.Random;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import com.quantum.pages.AccountPage;
import com.quantum.pages.BookingPage;
import com.quantum.pages.CreateAccountPage;
import com.quantum.pages.HomePage;
import com.quantum.pages.HotelPage;
import com.quantum.pages.HotelsPage;
import com.quantum.pages.LoginPage;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;
import static com.quantum.listerners.QuantumReportiumListener.logAssert;
import static com.quantum.listerners.QuantumReportiumListener.logStepStart;


/**
 * @author Lee Shoham
 * @date Jul 9, 2017
 */
public class TravelTest extends WebDriverTestCase {


	@BeforeTest
	public void beforeTest() {
		
		logStepStart("Clear cookies");

		getDriver().manage().deleteAllCookies();

		getDriver().get("/");
	}

//	@Test(groups = {"regression","login"})
	public void loginLogoutTest() {

		logStepStart("Browse to home page");

		HomePage homePage = new HomePage();
		LoginPage loginPage = null;
		AccountPage accountPage = null;

		logStepStart("Check if logged in, and logout if needed");
		if (homePage.isLoggedIn()) {
			homePage.logout();
		}

		loginPage = homePage.navToLoginPage();
		accountPage = loginPage.doLogin(getBundle().getProperty("demoUserEmail").toString(), getBundle().getProperty("demoUserPassword").toString());
		accountPage.validateAccountName(getBundle().getProperty("demoUserFullName").toString());
		loginPage = accountPage.logout();
	}

//	@Test(groups = {"account"})
	public void createAccountTest() {

		logStepStart("Browse to home page");

		HomePage homePage = new HomePage();
		CreateAccountPage createAccountPage = null;
		AccountPage accountPage = null;
		
		logStepStart("Check if logged in, and logout if needed");
		if (homePage.isLoggedIn()) {
			homePage.logout();
		}

		createAccountPage = homePage.navToCreateAccountPage();
		Random rand = new Random();
		int n = rand.nextInt(5000) + 1;
		String firstname = getBundle().getProperty("accountFirstname").toString();
		String lastname = getBundle().getProperty("accountLastname").toString();
		String mobile = getBundle().getProperty("accountMobile").toString();
		String email = 	n + getBundle().getProperty("accountEmail").toString(); 
		String password = getBundle().getProperty("accountPassword").toString();
	
		accountPage = createAccountPage.createAccount(firstname, lastname, mobile, email, password, password);
		
		
		accountPage.validateAccountName(firstname+" "+lastname);


	}

	@Test(groups = {"booking"})
	public void hotelBookingValidation() {
		
		logStepStart("Browse to home page");
		
		HomePage homePage = new HomePage();
		LoginPage loginPage = null;
		AccountPage accountPage = null;
		HotelsPage hotelsPage = null;
		HotelPage hotelPage = null;
		BookingPage bookingPage = null;

		logStepStart("Check if logged in, and login if needed");
		if (!homePage.isLoggedIn()) {
			
			loginPage = homePage.navToLoginPage();
			accountPage = loginPage.doLogin(getBundle().getProperty("demoUserEmail").toString(), getBundle().getProperty("demoUserPassword").toString());
			accountPage.navToHotelsPage();
		} else {

			homePage.navToHotelsPage();
		}
		
		logStepStart("Select first hotel in the list");
		hotelsPage = new HotelsPage();
		hotelsPage.searchHotels();
		hotelPage = hotelsPage.navToFirstHotelRestult();

		logStepStart("Book hotel");
		bookingPage = hotelPage.bookNow();
		
		bookingPage.validateBookingDetails();

	}
}
