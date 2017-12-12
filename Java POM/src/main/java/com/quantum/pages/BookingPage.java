/**
 * 
 */
package com.quantum.pages;
  
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.quantum.utils.*;

import static com.quantum.listerners.QuantumReportiumListener.logAssert;
import static com.quantum.utils.ReportUtils.logStepStart;

import org.testng.Assert;


/**
 * @author Lee Shoham
 * @date Jul 10, 2017
 */
public class BookingPage extends AbstractBasePageCommon {

	
	private String hotelName = null;
	
	@FindBy(locator = "booking.bookingSummaryTitle")
	private QAFWebElement bookingSummaryTitle;
	@FindBy(locator = "booking.bookingConfirmationHotelName")
	private QAFWebElement bookingConfirmationHotelName;
	
	@FindBy(locator = "booking.firstName")
	private QAFWebElement firstName;
	@FindBy(locator = "booking.lastName")
	private QAFWebElement lastName;
	@FindBy(locator = "booking.mobileNumber")
	private QAFWebElement mobileNumber;
	@FindBy(locator = "booking.email")
	private QAFWebElement email;
	@FindBy(locator = "booking.confirmEmail")
	private QAFWebElement confirmEmail;
	@FindBy(locator = "booking.address")
	private QAFWebElement address;
	@FindBy(locator = "booking.country")
	private QAFWebElement country;

	public BookingPage(String hotel) {
		super();
		this.hotelName = hotel;
		validateBookingPage();
		
	}
	
	

	@Override
	protected void openPage(PageLocator locator, Object... args) {

	}

	private void validateBookingPage() {
		logStepStart("Validate Booking Page");
		bookingSummaryTitle.waitForVisible(10000);	

	}

	
	public void fillForm(String first, String last, String mobile, String email, String address, String country) {
		
		this.firstName.sendKeys(first);
		this.lastName.sendKeys(last);
		this.mobileNumber.sendKeys(mobile);	
		this.email.sendKeys(email);
		this.confirmEmail.sendKeys(email);
		this.address.sendKeys(address);
		this.country.sendKeys(country);
		
	}
	
	public void validateBookingDetails() {

		try {
			new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("booking.bookingConfirmationHotelName"),
					this.hotelName , this.hotelName )).assertVisible("Hotel name");
			logAssert("Validate booking details. Hotel name "  + this.hotelName + " is visiable", true);
    		
			
		} catch (AssertionError n) {
    		logAssert("Validate booking details. Hotel name "  + this.hotelName + " is not visiable", false);
    		System.out.println("Validate booking details. Hotel name "  + this.hotelName + " is not visiable");
    		throw n;
    	}		
	}
	
	

}
