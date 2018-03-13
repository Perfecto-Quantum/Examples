package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;

@QAFTestStepProvider
public interface CommonStepsIntr {

	public interface GeneralSteps {
		
		@QAFTestStep(description = "I click the alarm tab")
		public void clickAlarm();
		
		@QAFTestStep(description = "I click the world clock tab")
		public void clickWorldClock();
		
		@QAFTestStep(description="I add the location {location}")
		public void addLocation(String location);
		
		@QAFTestStep(description="I start the Clock Application")
		public void startClock();
		
		@QAFTestStep(description="I must have a clock for {city}")
		public void verifyCityAdded(String city);
	
		@QAFTestStep(description="I delete the city {city}")
		public void deleteCity(String city);
		
	}
}
