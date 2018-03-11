package com.allin.common.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.tuple.Pair;

/**
 * 
 * @author renzhe.li
 *
 */
public final class DateUtils {
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("Asia/Shanghai");
	private static ThreadLocal<Map<Pair<String, TimeZone>, DateFormat>> formatters = ThreadLocal
			.withInitial(HashMap::new);

	/**
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toString(final Date date, final String pattern) {
		return getDateFormat(pattern, DEFAULT_TIME_ZONE).format(date);
	}

	/**
	 * 
	 * @param date
	 * @param pattern
	 * @param zone
	 * @return
	 */
	public static String toString(final Date date, final String pattern, final TimeZone zone) {
		return getDateFormat(pattern, zone).format(date);
	}

	/**
	 * 
	 * @param dateStr
	 * @param pattern
	 * @param zone
	 * @return
	 * @throws ParseException
	 */
	public static Date toDate(final String dateStr, final String pattern) throws ParseException {
		return getDateFormat(pattern, DEFAULT_TIME_ZONE).parse(dateStr);
	}

	/**
	 * 
	 * @param dateStr
	 * @param pattern
	 * @param zone
	 * @return
	 * @throws ParseException
	 */
	public static Date toDate(final String dateStr, final String pattern, final TimeZone zone) throws ParseException {
		return getDateFormat(pattern, zone).parse(dateStr);
	}

	public static DateFormat getDateFormat(final String pattern, final TimeZone zone) {
		final String validPattern = pattern == null ? YYYY_MM_DD_HH_MM_SS : pattern;
		final TimeZone validTimeZone = zone == null ? DEFAULT_TIME_ZONE : zone;

		DateFormat format = formatters.get().get(Pair.of(validPattern, validTimeZone));

		if (format == null) {
			format = new SimpleDateFormat(validPattern);
			format.setTimeZone(validTimeZone);

			formatters.get().put(Pair.of(validPattern, validTimeZone), format);
		}

		return format;
	}

	private DateUtils() {
	}

}
