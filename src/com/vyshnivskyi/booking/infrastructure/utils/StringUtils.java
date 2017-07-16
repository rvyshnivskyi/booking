package com.vyshnivskyi.booking.infrastructure.utils;

import jdk.nashorn.internal.runtime.ParserException;

import java.util.Date;

public class StringUtils {
	private static final String STRING_PARSING_EXCEPTION_MESSAGE = "Can't parse date [%s]. Date for parsing must be in format [%s]";

	public static Date dateParser(String dateYyyyMmDd) {
		String dateFormat = "yyyy-MM-dd";
		try {
			String[] dateArrayForParse = dateYyyyMmDd.split("-");
			if (dateArrayForParse.length == 3) {
				Date resultDate = new Date();
				resultDate.setYear(toInt(dateArrayForParse[0]));
				resultDate.setMonth(toInt(dateArrayForParse[1]));
				resultDate.setDate(toInt(dateArrayForParse[2]));
				return resultDate;
			} else throw new ParserException(String.format(STRING_PARSING_EXCEPTION_MESSAGE, dateYyyyMmDd, dateFormat));
		} catch (ParserException ex) {
			throw new ParserException(String.format(STRING_PARSING_EXCEPTION_MESSAGE, dateYyyyMmDd, dateFormat));
		}
	}

	private static int toInt(String str) {
		return Integer.parseInt(str);
	}
}
