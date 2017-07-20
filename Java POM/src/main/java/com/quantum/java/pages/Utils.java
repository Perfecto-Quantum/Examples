/**
 * 
 */
package com.quantum.java.pages;

import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.quantum.utils.DeviceUtils;

/**
 * @author Lee Shoham
 * @date Jul 18, 2017
 */
public final class Utils {

	// this func will scroll to element for max scroll times, this covers both cases
	// of elements not found and not displayed
	public static void swipeUpToObject(QAFWebElement el, int maxScroll) {
		for (int i = maxScroll; i > 0; --i) {
			try {

				if (el.isDisplayed())
					return;
				else {
					DeviceUtils.swipe("50%,70%", "50%,20%");
				}
			} catch (Exception e) {

				DeviceUtils.swipe("50%,70%", "50%,20%");
			}

		}
	}

}
