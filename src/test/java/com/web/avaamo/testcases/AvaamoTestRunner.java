package com.web.avaamo.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.utilities.BaseFunctions;
import com.utilities.TestDataGenerator;
import com.web.avaamo.pages.AvaamoHomePage;
import com.web.avaamo.pages.ConversationPopUp;
import com.web.avaamo.pages.MenuOptionsPopUp;

public class AvaamoTestRunner extends BaseFunctions{

	@BeforeMethod(alwaysRun=true)
	public void clickOnNotificationAndGetStarted() throws Exception {
		new AvaamoHomePage(driver).clickOnNotificationAndGetStarted();
	}
	
	@Test(enabled=true)
	public void verifyWelcomeScreen() {
		try {
			logInfo("To verify welcome screen");
			new MenuOptionsPopUp(driver).minimizeTheMenuOption();
			if(new ConversationPopUp(driver).enterTextInTypeMessage(TestDataGenerator.getRandomCharacterLimit(10))) 
				logPassed("Able to verify welcome screen functionality ");
			else
				logFailed("Unable to verify welcome screen functionality");
		}catch (Exception e) {
			logException(e);
		}
	}
	
	@Test(enabled=true)
	public void verifyStartOverMenuOption() {
		try {
			logInfo("Validate Start Over Menu Option -> Download Motor Policy flow");
			new MenuOptionsPopUp(driver).selectReqMenuOption("Start Over");
			ConversationPopUp objConversationPopUp = new ConversationPopUp(driver);
			objConversationPopUp.selectDefaultCardLink("Download Motor Policy");
			Boolean downloadBtnStatus = objConversationPopUp.selectDownloadLinkBtn();
			if(getNoOfTabs_Windows().size()==2 && downloadBtnStatus) 
				logPassed("Able to verify Start Over Menu Option -> Download Motor Policy flow ");
			else
				logFailed("Unable to verify Start Over Menu Option -> Download Motor Policy flow");
		}catch (Exception e) {
			logException(e);
		}
	}
	
	@Test(enabled=true)
	public void verifyFillFormFunctionality() {
		try {
			logInfo("Validate  Test Bot -> fill the form -> submit flow");
			new MenuOptionsPopUp(driver).minimizeTheMenuOption();
			ConversationPopUp objConversationPopUp = new ConversationPopUp(driver);
			objConversationPopUp.enterTextInTypeMessage("Test Bot");
			if(objConversationPopUp.fillFormAndVerifyItsStatus()) 
				logPassed("Able to verify Start Over Menu Option -> Download Motor Policy flow ");
			else
				logFailed("Unable to verify Start Over Menu Option -> Download Motor Policy flow");
		}catch (Exception e) {
			logException(e);
		}
	}
	
	@Test(enabled=true)
	public void verifyNewTestFunctionality() {
		try {
			logInfo("Validate 'New Test' -> fill the form -> submit flow");
			new MenuOptionsPopUp(driver).minimizeTheMenuOption();
			ConversationPopUp objConversationPopUp = new ConversationPopUp(driver);
			objConversationPopUp.enterTextInTypeMessage("New Test");
			Boolean googleLink = objConversationPopUp.verifyGoogleLink();
			Boolean callLinkStatus = objConversationPopUp.verifyCallLink();
			if( googleLink && callLinkStatus) 
				logPassed("Able to validate 'New Test' \n"+
						"1. Google -> Close Web view \n"+
						"2. Call -> Close Popup");
			else
				logFailed("Unable to validate 'New Test' \n"+
						"1. Google -> Close Web view \n"+
						"2. Call -> Close Popup");
		}catch (Exception e) {
			logException(e);
		}
	}
}
