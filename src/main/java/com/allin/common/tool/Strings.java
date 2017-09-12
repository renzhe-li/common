package com.allin.common.tool;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allin.common.constant.Constants;

public final class Strings {

	private static final Logger LOG = LoggerFactory.getLogger(Strings.class);

	private static final String CHARSET_DEFAULT_VALUE = "UTF-8";

	private Strings() {
	}

	public static boolean isNullOrEmpty(final String string) {
		return string == null || string.length() == 0;
	}

	/**
	 * String to int
	 * 
	 * @param num
	 * @return
	 */
	public static int toInteger(final String num) {
		if (isNullOrEmpty(num)) {
			return 0;
		}

		return Integer.parseInt(num);
	}

	/**
	 * String to long
	 * 
	 * @param num
	 * @return
	 */
	public static long toLong(final String num) {
		if (isNullOrEmpty(num)) {
			return 0;
		}

		return Long.parseLong(num);
	}

	/**
	 * String to BigDecimal
	 * 
	 * @param num
	 * @return
	 */
	public static BigDecimal toBigDecimal(final String num) {
		if (isNullOrEmpty(num)) {
			return null;
		}

		return new BigDecimal(num);
	}

	/**
	 * String to InputStream
	 * 
	 * @param inputString
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static InputStream toInputStream(final String inputString)
			throws UnsupportedEncodingException {
		return toInputStream(inputString, CHARSET_DEFAULT_VALUE);
	}

	/**
	 * String to InputStream
	 * 
	 * @param inputString
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static InputStream toInputStream(final String inputString, final String charset)
			throws UnsupportedEncodingException {
		if (isNullOrEmpty(inputString)) {
			return null;
		}

		return new ByteArrayInputStream(inputString.getBytes(charset));
	}

	/**
	 * Object to String
	 * 
	 * @param object
	 * @return
	 */
	public static String fromObject(final Object object) {
		if (object == null) {
			return null;
		}

		if (object instanceof Date) {
			LOG.info("object instanceof Date");
			return DateUtils.toString((Date) object, Constants.DATE_FORMAT_PATTRN2);
		}

		return String.valueOf(object);
	}

	/**
	 * Object to String with special format
	 * 
	 * @param object
	 * @param format
	 * @return
	 */
	public static String fromObject(final Object object, final String format) {
		if (object == null) {
			return null;
		}

		if (object instanceof Date) {
			return DateUtils.toString((Date) object, format);
		}

		return String.valueOf(object);
	}

}
