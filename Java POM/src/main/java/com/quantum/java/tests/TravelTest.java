/**
 * 
 */
package com.quantum.java.tests;


import java.util.Random;

import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import com.quantum.java.pages.AccountPage;
import com.quantum.java.pages.CreateAccountPage;
import com.quantum.java.pages.HomePage;
import com.quantum.java.pages.HotelPage;
import com.quantum.java.pages.HotelsPage;
import com.quantum.java.pages.LoginPage;


/**
 * @author Lee Shoham
 * @date Jul 9, 2017
 */
public class TravelTest extends WebDriverTestCase {


	@BeforeMethod
	public void beforeMethod() {
		getDriver().manage().deleteAllCookies();

		getDriver().get("/");
	}

	@Test(groups = { "regression", "login" })
	public void loginLogoutTest() {

		System.out.println("Login Logout Test");

		HomePage homePage = new HomePage();
		LoginPage loginPage = null;
		AccountPage accountPage = null;

		if (homePage.isLoggedIn()) {
			homePage.logout();
		}

		loginPage = homePage.navToLoginPage();
		accountPage = loginPage.doLogin("user@phptravels.com", "demouser");
		accountPage.validateAccountName("John Smith");
		loginPage = accountPage.logout();
	}

	@Test(groups = { "account" })
	public void createAccountTest() {

		System.out.println("Create Account Test");

		HomePage homePage = new HomePage();
		CreateAccountPage createAccountPage = null;
		AccountPage accountPage = null;
		if (homePage.isLoggedIn()) {
			homePage.logout();
		}

		createAccountPage = homePage.navToCreateAccountPage();
		Random rand = new Random();
		int n = rand.nextInt(5000) + 1;
		accountPage = createAccountPage.createAccount("aa", "bb", "1234", n + "@gmail.com", "123456", "123456");
		accountPage.validateAccountName("aa bb");
	}

	@Test(groups = { "reservation" })
	public void MakeHotelReservationAsLoggedUserTest() {

		System.out.println("Make Reservation Test");

		HomePage homePage = new HomePage();
		LoginPage loginPage = null;
		AccountPage accountPage = null;
		HotelsPage hotelsPage = null;
		HotelPage hotelPage = null;

		if (!homePage.isLoggedIn()) {
			
			loginPage = homePage.navToLoginPage();
			accountPage = loginPage.doLogin("user@phptravels.com", "demouser");
			hotelsPage = accountPage.navToHotelsPage();
		}
		else {
			hotelsPage = homePage.navToHotelsPage();
		}
		hotelPage = hotelsPage.navToFirstHotelRestult();
		hotelPage.performHotelBooking();

	}

}
