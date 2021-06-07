package com.web.avaamo.pages;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.BaseFunctions;
import com.utilities.TestDataGenerator;

public class ConversationPopUp extends BaseFunctions{

	@FindBy(css="textarea[class$=type-message]")
	private WebElement typeMsgTb;

	@FindBy(css="p[class*='desc text-content']")
	private List<WebElement> sentTextist;

	@FindBy(css="div[class*='default_card_link'] a")
	private List<WebElement> defaultCardLinks;

	@FindBy(css="a[type=web_page]")
	private WebElement downloadLinkBtn;

	@FindBy(xpath="//div[contains(text(),'Full name')]/following::input[1]")
	private WebElement fullNameTb;

	@FindBy(xpath="//div[contains(text(),'Address')]/following::textarea[1]")
	private WebElement addressTb;

	@FindBy(css="span[class$=option__circle]")
	private List<WebElement> genderRadioBtns;

	@FindBy(css="input[id^=picklist]")
	private WebElement policyRelatedTb;

	@FindBy(css="ul li")
	private List<WebElement> policyDropDowns;

	@FindBy(css="label[for^='rating']")
	private List<WebElement> starExperienceBtns;

	@FindBy(css="button[class*='btn default_card_submit']")
	private WebElement submitBtn;

	@FindBy(css="button[class*='btn default_card_submit'] strong")
	private WebElement submittedSuccessfullyBtn;

	@FindBy(css="div#webview_body button.close")
	private WebElement closeBtnInWebView;

	public ConversationPopUp(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author kirankumar
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public Boolean enterTextInTypeMessage(String text) throws Exception {
		Boolean Status = false;
		try {
			enterText(typeMsgTb, text+Keys.ENTER, "Type Message");
			String expectedText = getText(sentTextist.get(sentTextist.size()-1),"Sent Text");
			if(expectedText.equals(text)) {
				logPassed("Able to verify recently sent text");
				Status =true;
			}else
				logFailed("Unable to verify recently sent text"+"Expected - "+expectedText+"Actual - "+text);
		}catch (Exception e) {
			logFailedWithScreenshot(e.getMessage(),"Conversation Pop-up");
		}
		return Status;
	}
	/**
	 * @author kirankumar
	 * @description - to select the required default card link
	 * @param reqDefaultCard
	 * @return
	 * @throws Exception
	 */
	public Boolean selectDefaultCardLink(String reqDefaultCard) throws Exception {
		return selectValueFromListOfWebElements(defaultCardLinks,reqDefaultCard);
	}

	public Boolean selectDownloadLinkBtn() throws Exception {
		return click(downloadLinkBtn,"Download");
	}

	public Boolean fillFormAndVerifyItsStatus() throws Exception {
		Boolean status = false;
		try {
			TestDataGenerator objTestDataGenerator = new TestDataGenerator();
			waitUntilElementFound(fullNameTb);
			enterText(fullNameTb, objTestDataGenerator.getFullName(), "Full Name");
			enterText(addressTb, objTestDataGenerator.getfullAddress(), "Full Address");
			click(genderRadioBtns.get(0),"Male");
			click(policyRelatedTb, "Policy Related Information");
			click(policyDropDowns.get(1),"Very Often");
			scrollDown();
			scrollToElement(starExperienceBtns.get(0), "Star Experience");
			moveToElementAndClick(starExperienceBtns.get(1));
		}catch (Exception e) {
			logException(e);
		}finally {
			status = submitAndVerifyIt();
		}
		return status;
	}

	public Boolean submitAndVerifyIt() throws Exception {
		click(submitBtn,"Submit");
		Thread.sleep(3000);
		if(getText(submittedSuccessfullyBtn, "Submitted Successfully").equals("Submitted successfully")) {
			logPassed("Able to verify submit fnctionality");
			return true;
		}else
			logFailed("Unable to verify submit functionality");
		return false;
	}
	/**
	 * @author kirankumar
	 * @return
	 * @throws Exception
	 */
	public Boolean verifyGoogleLink() throws Exception {
		selectDefaultCardLink("Google");
		return click(closeBtnInWebView, "Close Button in web view");
	}
	/**
	 * @author kirankumar
	 * @return
	 * @throws Exception
	 */
	public Boolean verifyCallLink() throws Exception {
		selectDefaultCardLink("Call");
		switchToParentFrame();
		if(getNoOfTabs_Windows().size()==2) {
			return true;
		}else
			return false;
	}
}
