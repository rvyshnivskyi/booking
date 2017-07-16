package com.vyshnivskyi.booking.infrastructure.listeners;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseListener extends TestListenerAdapter {

	@Override
	public void onTestStart(ITestResult tr) {
		log(tr.getName() + "--> test method started\n");
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		log(tr.getName() + "--> Test method failed\n");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log(tr.getName()+ "--> Test method skipped\n");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		log(tr.getName()+ "--> Test method success\n");
	}

	private void log(String string) {
		Logger.getGlobal().log(Level.INFO, string);
	}

}
