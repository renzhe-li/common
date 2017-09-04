package com.allin.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtil {

	private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

	private static final Locale DEFAULT_LOCALE = Locale.getDefault(Locale.Category.FORMAT);

	public static final String DATE_FORMAT_PATTRN = "yyyy-MM-hh HH:mm:ss.SSS";

	private DateUtil() {
	}

	public static String toString(final Date date, final String pattern) {
		LOG.info("Converting Date:{} to String with Pattern:{}, Local:{}", date, pattern,
				DEFAULT_LOCALE);
		return toString(date, pattern, DEFAULT_LOCALE);
	}

	public static String toString(final Date date, final String pattern, final Locale locale) {
		LOG.info("Converting Date:{} to String with Pattern:{}, Local:{}", date, pattern, locale);
		final DateFormat dateFormat = new SimpleDateFormat(pattern, locale);
		return dateFormat.format(date);
	}

	// public static Date toDate(final String dateValue, final String pattern) {
	// LOG.info("Converting String:{} to Date with Pattern:{}, Local:{}",
	// dateValue, pattern,
	// DEFAULT_LOCALE);
	// final DateFormat dateFormat = new SimpleDateFormat(pattern,
	// DEFAULT_LOCALE);
	// return dateFormat.parse(dateValue);
	// }

}
