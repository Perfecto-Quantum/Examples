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
 * @date Jul 10, 2017
 */
public class HotelPage extends AbstractBasePageCommon {

	
	private String hotelName = null;
	
	public HotelPage(String hotel) {
		super();
		validateHotelPage();
		this.hotelName = hotel;
	}
	
	
	@FindBy(locator = "hotel.description")
	private QAFWebElement description;
	@FindBy(locator = "hotel.bookNowBtn")
	private QAFWebElement bookNowBtn;
	@FindBy(locator = "hotel.bookingConfirmationHotelName")
	private QAFWebElement bookingConfirmationHotelName;
	@FindBy(locator = "hotel.confirmBooking")
	private QAFWebElement confirmBooking;

	@Override
	protected void openPage(PageLocator locator, Object... args) {

	}

	private void validateHotelPage() {
		QuantumReportiumListener.logStepStart("Validate Hotel Page");
		description.waitForVisible(10000);
	}
	

	public void clickBookNow() {
		Utils.swipeUpToObject(bookNowBtn, 10);
		bookNowBtn.click();
	}
	
	public void validateHotelName() {
		Utils.swipeUpToObject(bookingConfirmationHotelName, 10);
		bookingConfirmationHotelName.verifyText(hotelName);
	}
	
	public void clickConfirmBooking() {
		confirmBooking.click();
	}
	
	public void validateInvoice() {
		getTitle().waitForAttribute("text", "Invoice", 10000);
	}
	
	public void performHotelBooking() {
		QuantumReportiumListener.logStepStart("Perform hotel booking");
		clickBookNow();
		validateHotelName();
		clickConfirmBooking();
		validateInvoice();
	}
	
	

}
