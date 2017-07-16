package com.vyshnivskyi.booking.pageControls.bookingSearchPage;

import com.vyshnivskyi.booking.infrastructure.waiter.WhileDo;
import com.vyshnivskyi.booking.pageControls.AbstractPage;
import com.vyshnivskyi.booking.pageControls.bookingSearchPage.searchPanel.SearchPanelControl;
import com.vyshnivskyi.booking.pageControls.bookingSearchResultPage.BookingSearchResultPageControl;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.vyshnivskyi.booking.infrastructure.executors.WebSelectorExecutor.$;
import static com.vyshnivskyi.booking.infrastructure.models.ConstantInstances.TIME_OUT;
import static com.vyshnivskyi.booking.infrastructure.models.ConstantInstances.WEB_DRIVER;
import static com.vyshnivskyi.booking.pageControls.bookingSearchPage.BookingSearchPageElements.URL;
import static com.vyshnivskyi.booking.pageControls.bookingSearchPage.searchPanel.SearchPanelElements.SEARCH_PANEL_SELECTOR;

public class BookingSearchPageControl extends AbstractPage {
	private final SearchPanelControl searchPanel = new SearchPanelControl();

	public BookingSearchPageControl open() {
		WEB_DRIVER.get(URL);
		return this;
	}

	@Override
	public BookingSearchPageControl waitUntilOpened() {
		WhileDo.wait(this::isOpened, "Booking search page is opened", TIME_OUT).run();
		Logger.getGlobal().log(Level.INFO, "Booking search page was opened");
		return this;
	}

	@Override
	public boolean isOpened() {
		return $(SEARCH_PANEL_SELECTOR).isDisplayed();
	}

	public BookingSearchPageControl chooseExpectedAddress(String address) {
		searchPanel.chooseExpectedAddress(address);
		return this;
	}

	public BookingSearchPageControl chooseExpectedDate(Date checkInDate, Date checkOutDate) {
		searchPanel.chooseExpectedDate(checkInDate, checkOutDate);
		return this;
	}

	public BookingSearchResultPageControl clickSearchSubmitButton() {
		searchPanel.clickSearchSubmitButton();
		return new BookingSearchResultPageControl();
	}
}
