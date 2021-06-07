package com.web.avaamo.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.BaseFunctions;

public class MenuOptionsPopUp extends BaseFunctions{

	@FindBy(css="div[class$=icon-down]")
	private WebElement downIconBtn;
	
	@FindBy(css="iframe[name='avaamoIframe']")
	private WebElement iframe;
	
	@FindBy(css="a.botMenu__menu__item")
	private List<WebElement> menuOptions;
	
	public MenuOptionsPopUp(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * @author kirankumar
	 * @description - To click on down icon
	 * @return
	 * @throws Exception 
	 */
	public Boolean minimizeTheMenuOption() throws Exception {
		switchToIframe(iframe);
		return click(downIconBtn,"Down Icon");
	}
	
	/**
	 * @author kirankumar
	 * @param - sent the reqMenu
	 * @return
	 * @throws Exception
	 */
	public Boolean selectReqMenuOption(String reqMenu) throws Exception {
		switchToIframe(iframe);
		return selectValueFromListOfWebElements(menuOptions, "Start Over");
	}
}
