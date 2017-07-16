package com.vyshnivskyi.booking.pageControls.bookingSearchResultPage;

import com.vyshnivskyi.booking.infrastructure.utils.TabsUtils;
import com.vyshnivskyi.booking.infrastructure.waiter.WhileDo;
import com.vyshnivskyi.booking.pageControls.AbstractPage;
import com.vyshnivskyi.booking.pageControls.selectedHotelPage.SelectedHotelPageControl;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.vyshnivskyi.booking.infrastructure.executors.WebSelectorExecutor.$;
import static com.vyshnivskyi.booking.infrastructure.models.ConstantInstances.TIME_OUT;
import static com.vyshnivskyi.booking.infrastructure.models.ConstantInstances.WEB_DRIVER;
import static com.vyshnivskyi.booking.pageControls.bookingSearchResultPage.BookingSearchResultPageElements.*;

public class BookingSearchResultPageControl extends AbstractPage {

	@Override
	public BookingSearchResultPageControl waitUntilOpened() {
		WhileDo.wait(this::isOpened, "Booking search result page is opened", TIME_OUT).run();
		Logger.getGlobal().log(Level.INFO, "Booking search result page was opened");
		return this;
	}

	@Override
	public boolean isOpened() {
		return $(FOUND_HOTEL_LIST_CONTAINER).isDisplayed();
	}

	public String getResultHotelItemAddress(WebElement resultHotelItemContainer) {
		return resultHotelItemContainer.findElement(HOTEL_ITEM_ADDRESS_SELECTOR).getText();
	}

	public String getHotelNameFromResultItem(WebElement hotelItemContainer) {
		return hotelItemContainer.findElement(HOTEL_ITEM_NAME_SELECTOR).getText();
	}

	public SelectedHotelPageControl openHotelPageInNewTab(WebElement hotelItemContainer) {
		String hotelName = hotelItemContainer.findElement(HOTEL_ITEM_NAME_SELECTOR).getText();
		hotelItemContainer.findElement(OPEN_HOTEL_INFORMATION_BUTTON).click();
		TabsUtils.switchDriverToAnotherTab(WEB_DRIVER, 2);
		Logger.getGlobal().log(Level.INFO, "[" + hotelName + "] hotel information page was opened");
		return new SelectedHotelPageControl();
	}

	public List<WebElement> getResultHotelsList() {
		return $(FOUND_HOTEL_LIST_CONTAINER).findElements(HOTEL_ITEM_SELECTOR);
	}
}
