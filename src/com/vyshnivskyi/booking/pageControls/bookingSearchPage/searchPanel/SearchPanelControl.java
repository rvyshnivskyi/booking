package com.vyshnivskyi.booking.pageControls.bookingSearchPage.searchPanel;

import com.vyshnivskyi.booking.infrastructure.waiter.WhileDo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import static com.vyshnivskyi.booking.infrastructure.executors.WebSelectorExecutor.$;
import static com.vyshnivskyi.booking.infrastructure.models.ConstantInstances.TIME_OUT;
import static com.vyshnivskyi.booking.pageControls.bookingSearchPage.searchPanel.SearchPanelElements.*;
import static java.util.logging.Logger.getGlobal;

public class SearchPanelControl {

	public SearchPanelControl chooseExpectedAddress(String address) {
		inputAddressIntoTheSearchField(address);
		WhileDo.wait($(SEARCH_ADDRESS_LIST)::isDisplayed, "Search address list is displayed", TIME_OUT).run();
		getExpectedAddressSelectorFromDropDown(address).click();
		getGlobal().log(Level.INFO, "Expected address was chosen from dropdown list");
		return this;
	}

	public SearchPanelControl chooseExpectedDate(Date checkInDate, Date checkOutDate) {
		setDataToInputField(CHECK_IN_MONTH_INPUT_SELECTOR, "" + checkInDate.getMonth())
				.setDataToInputField(CHECK_IN_MONTHDAY_INPUT_SELECTOR, "" + checkInDate.getDate())
				.setDataToInputField(CHECK_IN_YEAR_INPUT_SELECTOR, "" + checkInDate.getYear())
				.setDataToInputField(CHECK_OUT_MONTH_INPUT_SELECTOR, "" + checkOutDate.getMonth())
				.setDataToInputField(CHECK_OUT_MONTHDAY_INPUT_SELECTOR, "" + checkOutDate.getDate())
				.setDataToInputField(CHECK_OUT_YEAR_INPUT_SELECTOR, "" + checkOutDate.getYear());
		getGlobal().log(Level.INFO, "Date for searching was successfully inserted");
		return this;
	}

	public SearchPanelControl clickSearchSubmitButton() {
		$(SEARCH_PANEL_SELECTOR).findElement(SUBMIT_SEARCH_BUTTON).click();
		getGlobal().log(Level.INFO, "Search submit button was clicked. Searching must be started");
		return this;
	}

	private SearchPanelControl inputAddressIntoTheSearchField(String address) {
		setDataToInputField(INPUT_ADDRESS_FIELD_SELECTOR, address);
		getGlobal().log(Level.INFO, "[" + address + "] address for searching was successfully inserted");
		return this;
	}

	private WebElement getExpectedAddressSelectorFromDropDown(String address) {
		List<WebElement> addressDropDownList = $(SEARCH_PANEL_SELECTOR).findElements(ADDRESS_DROP_DOWN_ELEMENT_SELECTOR);
		for(WebElement dropDownElement : addressDropDownList) {
			if(dropDownElement.getText().contains(address)) return dropDownElement;
		}
		throw new SearchPanelException(String.format("%s was not found in dropdown list. Please check your address", address));
	}

	private SearchPanelControl setDataToInputField(By element, String dataToInput) {
		$(element).clearAndSendKeys(dataToInput);
		return this;
	}
}
