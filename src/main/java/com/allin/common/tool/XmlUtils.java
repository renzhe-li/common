package com.allin.common.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XmlUtils is a utility to processing XML file to Map <br>
 * <br>
 * To Mapping the XML Data completely, the XML file should follow this rule:<br>
 * <tab>if two or more nodes have the same name under the same node, the every
 * node need to add a attribute:priority="value". <br>
 * 
 * @author renzhe.li
 *
 */
public final class XmlUtils {

	private static final Logger LOG = LoggerFactory.getLogger(XmlUtils.class);

	private XmlUtils() {
	}

	public static Map<String, Object> dom2Map(final File file) {
		final SAXReader reader = new SAXReader();
		try {
			final Document document = reader.read(file);
			return dom2Map(document);
		} catch (final DocumentException e) {
			LOG.info("DocumentException occur when processing file:{}", file.getName(), e);
		}

		return new HashMap<>();
	}

	public static Map<String, Object> dom2Map(final Document doc) {
		final Map<String, Object> map = new HashMap<String, Object>();
		if (doc == null) {
			return map;
		}

		final Element root = doc.getRootElement();
		final Map<String, Object> childMap = dom2Map(root);

		map.put(root.getName(), childMap);

		return map;
	}

	private static Map<String, Object> dom2Map(final Element root) {
		final Map<String, Object> map = new HashMap<String, Object>();

		final List<Element> rootElements = root.elements();
		rootElements.stream().forEach(e -> {
			final List<Element> elements = e.elements();
			final List<Attribute> attributes = e.attributes();

			if (elements.isEmpty() && attributes.isEmpty()) {
				putParentMap(map, e.getName(), e.getText());
				return;
			}

			final Map<String, Object> properties = new HashMap<>();
			if (!elements.isEmpty()) {
				properties.putAll(dom2Map(e));
			}

			for (final Attribute attribute : attributes) {
				properties.put("@" + attribute.getName(), attribute.getValue());
			}

			putParentMap(map, e.getName(), properties);
		});

		return map;
	}

	@SuppressWarnings("unchecked")
	private static void putParentMap(final Map<String, Object> map, final String key, final Object value) {
		final Object originalValue = map.getOrDefault(key, null);
		if (originalValue == null) {
			map.put(key, value);
		} else if (originalValue instanceof List) {
			ArrayList.class.cast(originalValue).add(value);
		} else {
			final List<Object> values = new ArrayList<>();
			values.add(originalValue);
			values.add(value);
			map.put(key, values);
		}
	}

}