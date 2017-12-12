/**
 * 
 */
package com.quantum.pages;
  
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.quantum.utils.Utils;

/**
 * @author Lee Shoham
 * @date Jul 10, 2017
 */
public class HotelPage extends AbstractBasePageCommon {

	
	private String hotelName = null;
	
	@FindBy(locator = "hotel.description")
	private QAFWebElement description;
	@FindBy(locator = "hotel.bookNowBtn")
	private QAFWebElement bookNowBtn;

	public HotelPage(String hotel) {
		super();
		this.hotelName = hotel;
		validateHotelPage();
		
	}
	
	@Override
	protected void openPage(PageLocator locator, Object... args) {

	}

	private void validateHotelPage() {
		new QAFExtendedWebElement(String.format(ConfigurationManager.getBundle().getString("hotel.nameTitle"),
				this.hotelName , this.hotelName )).waitForVisible(10000);
	}
	

	public BookingPage bookNow() {
		Utils.swipeUpToObject(bookNowBtn, 10);
		bookNowBtn.click();
		return new BookingPage(hotelName);
			
	}
	


}
