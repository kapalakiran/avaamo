package com.web.avaamo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.BaseFunctions;

public class AvaamoHomePage extends BaseFunctions{
	
	@FindBy(css="small.avm-welcome-notification-message")
	private WebElement notificationMsg;
	
	@FindBy(css="a.get-started-link")
	private WebElement getStartedBtn;
	
	public AvaamoHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * @author kirankumar 
	 * @description - to select notification & click on message
	 * @return Boolean
	 * @throws Exception 
	 */
	public Boolean clickOnNotificationAndGetStarted() throws Exception {
		waitUntilElementFound(notificationMsg);
		click(notificationMsg, "Notification Message");
		return click(getStartedBtn, "Get Started");
	}
}
