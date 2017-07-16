package com.vyshnivskyi.booking.infrastructure.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConstantInstances {
	public static final WebDriver WEB_DRIVER = new ChromeDriver();
	public static final int TIME_OUT = Integer.parseInt(System.getProperty("timeOut", "" + 30));
}
