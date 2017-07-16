package com.vyshnivskyi.booking.infrastructure.waiter;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;
import java.util.logging.Level;

import static java.util.logging.Logger.getGlobal;

public class WhileDo {
	private final long timeToWait;
	private final Supplier<Boolean> booleanChecker;
	private final String instruction;
	private Runnable runBeforeChecking;

	public WhileDo(Supplier<Boolean> booleanChecker, String instruction, long timeToWait) {
		this.timeToWait = timeToWait;
		this.booleanChecker = booleanChecker;
		this.instruction = instruction;
	}

	public static WhileDo wait(Supplier<Boolean> booleanChecker, String instruction, long timeToWait) {
		return new WhileDo(booleanChecker, instruction, timeToWait);
	}

	public WhileDo executeBeforeCheck(Runnable additionAction) {
		this.runBeforeChecking = additionAction;
		return this;
	}

	public void run() {
		long timeBeforeStart = System.currentTimeMillis();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				getGlobal().log(Level.INFO, String.format("Wait until %s become [true]", instruction));
			}
		}, 0L, 1000);
		do {
			if (runBeforeChecking != null) runBeforeChecking.run();
			if (System.currentTimeMillis() > timeBeforeStart + timeToWait * 1000) {
				timer.cancel();
				throw new WhileDoException(String.format("(%s) isn't [true] during %d seconds", instruction, timeToWait));
			}
		} while (!booleanChecker.get());
		timer.cancel();
		getGlobal().log(Level.INFO, instruction + " == [true]");
	}
}
