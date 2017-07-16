package com.vyshnivskyi.booking.pageControls.bookingSearchResultPage;

import org.openqa.selenium.By;

public class BookingSearchResultPageElements {
	public final static By FOUND_HOTEL_LIST_CONTAINER = By.id("hotellist_inner");
	public final static By HOTEL_ITEM_SELECTOR = By.className("sr_item");
	public final static By HOTEL_ITEM_ADDRESS_SELECTOR = By.className("address");
	public final static By HOTEL_ITEM_NAME_SELECTOR = By.className("sr-hotel__name");
	public final static By OPEN_HOTEL_INFORMATION_BUTTON = By.className("hotel_name_link");
}
