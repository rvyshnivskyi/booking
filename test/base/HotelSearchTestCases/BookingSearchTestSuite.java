package base.HotelSearchTestCases;

import base.BaseTest;
import com.vyshnivskyi.booking.helpers.BookingSearchResultTestHelper;
import com.vyshnivskyi.booking.infrastructure.utils.StringUtils;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookingSearchTestSuite extends BaseTest {
	private final BookingSearchResultTestHelper bookingSearchResultTestHelper = new BookingSearchResultTestHelper();

	@Test
	@Parameters({"address", "checkInDate(yyyy-mm-dd)", "checkOutDate(yyyy-mm-dd)"})
	public void testSearchResultAddresses(@Optional("New York City") String address, @Optional("2017-09-20") String checkInDate, @Optional("2017-09-25") String checkOutDate) {
		openSearchPageAndSearch(address, checkInDate, checkOutDate)
				.checkResultAddressesCorrespondence(address);
	}

	@Test
	@Parameters({"address", "checkInDate(yyyy-mm-dd)", "checkOutDate(yyyy-mm-dd)"})
	public void testSearchResultHotelsNames(@Optional("New York City") String address, @Optional("2017-09-20") String checkInDate, @Optional("2017-09-25") String checkOutDate) {
		openSearchPageAndSearch(address, checkInDate, checkOutDate)
				.checkThatHotelNamesFromResultListIsTheSameAsInHotelInformation();
	}

	private BookingSearchResultTestHelper openSearchPageAndSearch(String address, String checkInDate, String checkOutDate) {
		return bookingSearchResultTestHelper
				.openBookingSearchPageAndSearch(
						StringUtils.dateParser(checkInDate),
						StringUtils.dateParser(checkOutDate),
						address);
	}
}
