package com.vyshnivskyi.booking.helpers;

import com.vyshnivskyi.booking.pageControls.bookingSearchPage.BookingSearchPageControl;
import com.vyshnivskyi.booking.pageControls.bookingSearchResultPage.BookingSearchResultPageControl;
import com.vyshnivskyi.booking.pageControls.selectedHotelPage.SelectedHotelPageControl;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import static java.util.logging.Logger.getGlobal;

public class BookingSearchResultTestHelper {
	private final BookingSearchPageControl bookingSearchPage = new BookingSearchPageControl();
	private final BookingSearchResultPageControl bookingSearchResultPage = new BookingSearchResultPageControl();

	public BookingSearchResultTestHelper openBookingSearchPageAndSearch(Date checkInDate, Date checkOutDate, String address) {
		bookingSearchPage
				.open()
				.waitUntilOpened()
				.chooseExpectedAddress(address)
				.chooseExpectedDate(checkInDate, checkOutDate)
				.clickSearchSubmitButton();
		getGlobal().log(Level.INFO,
				String.format("Searching for checkIn [%s], checkOut [%s] in [%s] successfully done", checkInDate, checkOutDate, address));
		return this;
	}

	public BookingSearchResultTestHelper checkThatHotelNamesFromResultListIsTheSameAsInHotelInformation() {
		List<WebElement> resultHotelsList = bookingSearchResultPage.waitUntilOpened().getResultHotelsList();
		resultHotelsList.forEach(this::gatAndCheckHotelNameFromHotelInformationPage);
		getGlobal().log(Level.INFO, "Hotels name from information pages is the same as from searching result list");
		return this;
	}

	public BookingSearchResultTestHelper checkResultAddressesCorrespondence(String expectedAddress) {
		List<WebElement> resultHotelList = bookingSearchResultPage.getResultHotelsList();
		resultHotelList.forEach((WebElement e) -> checkCorrespondenceAddress(e, expectedAddress));
		getGlobal().log(Level.INFO, "Addresses of the found hotels is the same as expected [" + expectedAddress + "]");
		return this;
	}

	private void checkCorrespondenceAddress(WebElement hotelResultItem, String expectedAddress) {
		String currentHotelAddress = bookingSearchResultPage.getResultHotelItemAddress(hotelResultItem);
		Assert.assertTrue(currentHotelAddress.contains(expectedAddress),
				String.format("Address of [%s] hotel - [%s] doesn't contains expected [%s]",
						bookingSearchResultPage.getHotelNameFromResultItem(hotelResultItem),
						currentHotelAddress,
						expectedAddress));
	}

	private void gatAndCheckHotelNameFromHotelInformationPage(WebElement hotelsResultItem) {
		String hotelNameFromResultItem = bookingSearchResultPage.getHotelNameFromResultItem(hotelsResultItem);
		SelectedHotelPageControl currentHotelInformationPage = bookingSearchResultPage
				.openHotelPageInNewTab(hotelsResultItem)
				.waitUntilOpened();
		String hotelNameFromTheHotelInformationPage = currentHotelInformationPage.getHotelName();
		Assert.assertEquals(hotelNameFromResultItem, hotelNameFromTheHotelInformationPage);
		currentHotelInformationPage.closeHotelInformationTabAndSwitchToPreviewTab();
		bookingSearchResultPage.waitUntilOpened();
	}
}
