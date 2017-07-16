package com.vyshnivskyi.booking.infrastructure.utils;

import com.gargoylesoftware.htmlunit.WebWindowNotFoundException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class TabsUtils {
	public static void switchDriverToAnotherTab(WebDriver webDriver, int expectedTabIndexStartsFromOne) {
		ArrayList<String> windowsHandlesList = getWindowsHandlesList(webDriver);
		int countOfTabs = windowsHandlesList.size();
		if (countOfTabs >= expectedTabIndexStartsFromOne) {
			webDriver.switchTo().window(windowsHandlesList.get(expectedTabIndexStartsFromOne - 1));
		} else {
			throw new WebWindowNotFoundException("You want switch to " + expectedTabIndexStartsFromOne + " tab. But only " + countOfTabs + " tabs exist");
		}
	}

	public static ArrayList<String> getWindowsHandlesList(WebDriver webDriver) {
		return new ArrayList<>(webDriver.getWindowHandles());
	}

	public static void closeCurrentTab(WebDriver webDriver) {
		webDriver.close();
	}
}
