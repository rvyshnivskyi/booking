package com.vyshnivskyi.booking.pageControls.selectedHotelPage;

import com.vyshnivskyi.booking.infrastructure.utils.TabsUtils;
import com.vyshnivskyi.booking.infrastructure.waiter.WhileDo;
import com.vyshnivskyi.booking.pageControls.AbstractPage;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.vyshnivskyi.booking.infrastructure.executors.WebSelectorExecutor.$;
import static com.vyshnivskyi.booking.infrastructure.models.ConstantInstances.TIME_OUT;
import static com.vyshnivskyi.booking.infrastructure.models.ConstantInstances.WEB_DRIVER;
import static com.vyshnivskyi.booking.pageControls.selectedHotelPage.SelectedHotelPageElements.HOTEL_NAME_SELECTOR;

public class SelectedHotelPageControl extends AbstractPage {

	@Override
	public boolean isOpened() {
		return $(HOTEL_NAME_SELECTOR).isDisplayed();
	}

	@Override
	public SelectedHotelPageControl waitUntilOpened() {
		WhileDo.wait(this::isOpened, "Booking search result page is opened", TIME_OUT).run();
		Logger.getGlobal().log(Level.INFO, "Booking search result page was opened");
		return this;
	}

	public String getHotelName() {
		return $(HOTEL_NAME_SELECTOR).getText();
	}

	public void closeHotelInformationTabAndSwitchToPreviewTab() {
		TabsUtils.closeCurrentTab(WEB_DRIVER);
		TabsUtils.switchDriverToAnotherTab(WEB_DRIVER, 1);
		Logger.getGlobal().log(Level.INFO, "Hotel information page was closed adn switched to previous tab");
	}
}
