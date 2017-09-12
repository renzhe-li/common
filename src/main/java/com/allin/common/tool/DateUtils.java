package com.allin.common.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtils {

	private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);

	private static final Locale DEFAULT_LOCALE = Locale.getDefault(Locale.Category.FORMAT);

	private DateUtils() {
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

	public static Date toDate(final String dateValue, final String pattern) throws ParseException {
		LOG.info("Converting String:{} to Date with Pattern:{}, Local:{}", dateValue, pattern,
				DEFAULT_LOCALE);
		return toDate(dateValue, pattern, DEFAULT_LOCALE);
	}

	public static Date toDate(final String dateValue, final String pattern, final Locale locale)
			throws ParseException {
		LOG.info("Converting String:{} to Date with Pattern:{}, Local:{}", dateValue, pattern, locale);
		final DateFormat dateFormat = new SimpleDateFormat(pattern, locale);
		return dateFormat.parse(dateValue);
	}

}
