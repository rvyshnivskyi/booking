package com.vyshnivskyi.booking.infrastructure.executors;

import com.vyshnivskyi.booking.infrastructure.models.ConstantInstances;
import org.apache.commons.lang.Validate;
import org.openqa.selenium.*;

import java.util.List;
import java.util.logging.Level;

import static java.util.logging.Logger.getGlobal;

public class WebSelectorExecutor implements WebElement {
	private By by;
	private static WebDriver WEB_DRIVER = ConstantInstances.WEB_DRIVER;

	public WebSelectorExecutor(By by) {
		Validate.notNull(by, "web selector not to be null");
		this.by = by;
	}

	public static WebSelectorExecutor $(By by) {
		return new WebSelectorExecutor(by);
	}

	public WebSelectorExecutor setWebDriver(WebDriver webDriver) {
		Validate.notNull(webDriver, "web driver not to be null");
		this.WEB_DRIVER = webDriver;
		return this;
	}

	public WebElement findElement() {
		return this.WEB_DRIVER.findElement(this.by);
	}

	public List<WebElement> findElements() {
		return this.WEB_DRIVER.findElements(this.by);
	}

	public void click() {
		this.findElement().click();
	}

	public void submit() {
		this.findElement().submit();
	}

	public void sendKeys(CharSequence... charSequences) {
		this.findElement().sendKeys(charSequences);
	}

	public void clearAndSendKeys(CharSequence... charSequences) {
		WebElement element = this.findElement();
		element.clear();
		element.sendKeys(charSequences);
	}

	public void clear() {
		this.findElement().clear();
	}

	public String getTagName() {
		return this.findElement().getTagName();
	}

	public String getAttribute(String s) {
		return this.findElement().getAttribute(s);
	}

	public boolean isSelected() {
		try {
			return this.findElement().isSelected();
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public boolean isEnabled() {
		try {
			return this.findElement().isEnabled();
		} catch (NoSuchElementException ex) {
			getGlobal().log(Level.WARNING, ex.getMessage());
			return false;
		}
	}

	public String getText() {
		return this.findElement().getText();
	}

	public List<WebElement> findElements(By by) {
		return this.findElement().findElements(by);
	}

	public WebElement findElement(By by) {
		return this.findElement().findElement(by);
	}

	public boolean isNotDisplayed() {
		return !isDisplayed();
	}

	public boolean isDisplayed() {
		try {
			return isDisplayed(this.WEB_DRIVER.findElement(by));
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public static boolean isDisplayed(WebElement webElement) {
		try {
			return webElement.isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		} catch (WebDriverException ex) {
			getGlobal().log(Level.WARNING, ex.getMessage());
			return false;
		}
	}

	@Override
	public Point getLocation() {
		return this.findElement().getLocation();
	}

	@Override
	public Dimension getSize() {
		return this.findElement().getSize();
	}

	@Override
	public String getCssValue(String s) {
		return this.findElement().getCssValue(s);
	}
}