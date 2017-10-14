package com.allin.common.dto;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlMap<K, V> extends HashMap<K, V> {

	private static final Logger LOG = LoggerFactory.getLogger(XmlMap.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XmlMap<K, V> getXmlMap(final String key) {
		if (key == null || key.isEmpty()) {
			LOG.info("Fail to get XmlMap for Key:{}", key);
			return this;
		}

		final XmlMap<K, V> map = new XmlMap<>();
		setMap(key, map);

		LOG.info("Success to get XmlMap for Key:{}", key);
		return map;
	}

	public Map<K, V> getMap(final String key) {
		if (key == null || key.isEmpty()) {
			LOG.info("Fail to get Map for Key:{}", key);
			return this;
		}

		final Map<K, V> map = new HashMap<>();
		setMap(key, map);

		LOG.info("Success to get Map for Key:{}", key);
		return map;
	}

	@SuppressWarnings("unchecked")
	private void setMap(final String currentKey, final Map<K, V> map) {
		map.put((K) currentKey, get(currentKey));

		final String childKeyHeader = currentKey + ".";
		final int length = currentKey.length();

		for (final K object : keySet()) {
			final String key = String.class.cast(object);
			if (key == null || key.length() <= length) {
				continue;
			}

			final String previous = key.substring(0, length + 1);
			if (childKeyHeader.equals(previous)) {
				map.put((K) key.substring(length + 1), get(key));
			}
		}
	}

}
