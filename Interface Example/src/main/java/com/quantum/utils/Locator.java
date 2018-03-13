package com.quantum.utils;

import org.openqa.selenium.Rectangle;

import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class Locator {
	/**
	 * This function will calculate the location of the element on the 
	 * device and manually tap the point location of the middle of the 
	 * element. 
	 * @param loc - locator to find the element to be clicked
	 * @param addressBar - navigation bar that takes up the top half of the 
	 * 					   device outside of the webview
	 */
		public static void touchObject(String loc, String addressBar) {
			int bannerY = getOffset(addressBar);
			int scaleFactor = getScale();
			//Gets the rectangle of the element we want to click
	        Rectangle rect = new QAFExtendedWebElement(loc).getRect();
	        //calculates the middle x value using the rectangle and multiplying the scale
			int x = (rect.getX()+(rect.getWidth()/2))*scaleFactor;
			//calculates the middle y value using the rectangle, adding the offset
			//and multiplying the scale
			int y = (rect.getY()+(rect.getHeight()/2)+bannerY)*scaleFactor;
			//Touch the device at the point calculated
			DeviceUtils.touch(x+","+y);	
		}
		/**
		 * Slides the provided object to the left
		 * @param loc object to slide
		 */
		public static void slideObjectLeft(String loc)
		{
			//uses 0.5 to get the middle of the Y
			float y = 0.5f;
			//Since we are sliding left, we want to start on the right side of the element 
			//and end on the left side
			float startX = (2.0f/3.0f);
			float endX = (1.0f/3.0f);
			//This calls the slide object using the constant values we set for 
			//the default left slide
			slideObject(loc, startX,endX, y);
		}
		/**
		 * 
		 * @param loc
		 * @param xStartMult
		 * @param xEndMult
		 * @param yMult
		 */
		public static void slideObject(String loc, float xStartMult,float xEndMult, float yMult)
		{
			slideObject(loc, xStartMult,xEndMult, yMult, yMult);
		}
		/**
		 * 
		 * @param loc
		 * @param xStartMult
		 * @param xEndMult
		 * @param yStartMult
		 * @param yEndMult
		 */
		public static void slideObject(String loc, float xStartMult,float xEndMult, float yStartMult,float yEndMult)
		{
			//Gets the current scale of the device
			int scaleFactor = getScale();
			//Gets the rectangle of the object to use the x,y and width, height
			Rectangle rect = new QAFExtendedWebElement(loc).getRect();
			//Gets point to start y
			int startY = Math.round(((rect.getY()+(rect.getHeight()*yStartMult)))*scaleFactor);
			//Gets point to stop y
			int endY = Math.round((rect.getY()+(rect.getHeight()*yEndMult))*scaleFactor);
			//Gets the point to start x
			int startX = Math.round((rect.getX()+(rect.getWidth()*xStartMult))*scaleFactor);
			//gets the point to stop y
			int endX = Math.round((rect.getX()+((rect.getWidth())*xEndMult))*scaleFactor);
			//swipes using the points provided
			DeviceUtils.swipe(startX+","+startY, endX+","+endY);
		}
		
		/**
		 * Gets the current application sacale for the device
		 * @return integer value of scale
		 */
		public static int getScale() {
			//Gets the resolution of the current device
			String deviceRes = DeviceUtils.getDeviceProperty("resolution");
	        //Gets the width of the root application viewport 
			int appWidth = new QAFExtendedWebElement("xpath=/*/*").getSize().getWidth();
	        //compares the resolution to the application dimensions to find out what the pixel scale is
			return Math.round(Integer.parseInt(deviceRes.split("\\*")[0])/appWidth);
		}
		/**
		 * Gets the offset of the header values to offset y value of the header element
		 * @param addressBar - header element to measure
		 * @return the y offset of the element
		 */
		public static int getOffset(String addressBar) {
			return getOffset(addressBar, "NATIVE_APP");
		}
		/**
		 * Gets the offset of the header values to offset y value of the header element
		 * @param addressBar - header element to measure
		 * @param context - context of the element to use
		 * @return the y offset of the element
		 */
		public static int getOffset(String addressBar, String context) {
			//Stores the current context so we can switch to it at the end
			String curContext = DeviceUtils.getCurrentContext();
			// Switch to native context
			DeviceUtils.switchToContext(context); 
			//Gets the rectangle of the welement to get the x,y and width height
			Rectangle view = new QAFExtendedWebElement(addressBar).getRect();		
			DeviceUtils.switchToContext(curContext); // Switch back to the original context
			//this gets the application size of the area above the viewport
			//we will use this to offset the element
			return (view.getY()+view.getHeight());
		}
}
