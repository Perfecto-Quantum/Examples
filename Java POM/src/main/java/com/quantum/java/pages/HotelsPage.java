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
public class HotelsPage extends AbstractBasePageCommon {

	public HotelsPage() {
		super();
		validateHotelsPage();
	}
	

	@FindBy(locator = "hotels.firstHotelResult")
	private QAFWebElement firstHotelResult;
	
	@Override
	protected void openPage(PageLocator locator, Object... args) {
	}
	
	public void validateHotelsPage() {
		QuantumReportiumListener.logStepStart("Validate Hotels Page");
		getTitle().waitForAttribute("text", "Hotels Listings", 10000);
	}
	
	public HotelPage navToFirstHotelRestult() {
		QuantumReportiumListener.logStepStart("Navigating to first hotel result");
		Utils.swipeUpToObject(firstHotelResult, 10);
		String hotelName = firstHotelResult.getText();
		firstHotelResult.click();
		return new HotelPage(hotelName);
	}


	


}
