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
public class HotelsPage extends AbstractBasePageCommon {

	
	@FindBy(locator = "hotels.firstHotelResult")
	private QAFWebElement firstHotelResult;
	
	@FindBy(locator = "hotels.searchButton")
	private QAFWebElement serachButton;


	@FindBy(locator = "hotels.activeHotels")
	private QAFWebElement activeHotels;
	
	
	public HotelsPage() {
		super();
	}

	
	@Override
	protected void openPage(PageLocator locator, Object... args) {
	}
	
	public void validateHotelsPage() {
		activeHotels.waitForPresent(5000);
	}
	
	public void searchHotels() {
		serachButton.click();
		
	}
	public HotelPage navToFirstHotelRestult() {
		//Utils.swipeUpToObject(firstHotelResult, 10);
		String hotelName = firstHotelResult.getText();
		firstHotelResult.click();
		return new HotelPage(hotelName);
	}


	


}
