package com.allin.common.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ReflectionUtils extends org.springframework.util.ReflectionUtils {

	private static final Logger LOG = LoggerFactory.getLogger(ReflectionUtils.class);

	private ReflectionUtils() {
	}

	/**
	 * o isInstance of Class.forName(className)
	 * 
	 * @param className
	 * @param o
	 * @return
	 */
	public static boolean isInstance(final String className, final Object o) {
		LOG.info("className:{}", className);
		try {
			final Class<?> clazz = Class.forName(className);
			return clazz.isInstance(o);
		} catch (ClassNotFoundException ex) {
			LOG.error("className:{}", className, ex);
			return false;
		}
	}

	public static boolean isInstance(final Class<?> clazz, final Object o) {

		return clazz.isInstance(o);
	}

}
