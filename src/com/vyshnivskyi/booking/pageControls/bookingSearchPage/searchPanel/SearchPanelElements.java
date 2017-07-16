package com.vyshnivskyi.booking.pageControls.bookingSearchPage.searchPanel;

import org.openqa.selenium.By;

public class SearchPanelElements {
	public static final By SEARCH_PANEL_SELECTOR = By.xpath(".//*[@id='frm']");
	public static final By INPUT_ADDRESS_FIELD_SELECTOR = By.id("ss");
	public static final By SEARCH_ADDRESS_LIST = By.className("c-autocomplete__list");
	public static final By ADDRESS_DROP_DOWN_ELEMENT_SELECTOR = By.tagName("li");
	public final static By CHECK_IN_MONTH_INPUT_SELECTOR = By.name("checkin_month");
	public final static By CHECK_IN_MONTHDAY_INPUT_SELECTOR = By.name("checkin_monthday");
	public final static By CHECK_IN_YEAR_INPUT_SELECTOR = By.name("checkin_year");
	public final static By CHECK_OUT_MONTH_INPUT_SELECTOR = By.name("checkout_month");
	public final static By CHECK_OUT_MONTHDAY_INPUT_SELECTOR = By.name("checkout_monthday");
	public final static By CHECK_OUT_YEAR_INPUT_SELECTOR = By.name("checkout_year");
	public final static By SUBMIT_SEARCH_BUTTON = By.className("sb-searchbox__button");
}
