package com.allin.common.tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

public final class XmlUtils {

	private XmlUtils() {
	}

	public static Map<String, Object> dom2Map(Document doc) {
		final Map<String, Object> map = new HashMap<String, Object>();
		if (doc == null) {
			return map;
		}

		final Element root = doc.getRootElement();
		map.putAll(dom2Map(root));

		return map;
	}

	private static Map<String, Object> dom2Map(final Element root) {
		final Map<String, Object> map = new HashMap<String, Object>();

		final List<Element> rootElements = root.elements();
		rootElements.stream().forEach(e -> {
			final List<Element> elements = e.elements();
			final List<Attribute> attributes = e.attributes();

			if (elements.isEmpty() && attributes.isEmpty()) {
				map.put(e.getName(), e.getText());
				return;
			}

			final Map<String, Object> properties = new HashMap<>();
			if (!elements.isEmpty()) {
				properties.putAll(dom2Map(e));
			}

			Optional<String> priority = Optional.ofNullable(null);
			for (final Attribute attribute : attributes) {
				final String name = attribute.getName();
				if (name.equals("priority")) {
					priority = Optional.ofNullable(attribute.getValue());
				}
				properties.put("@" + attribute.getName(), attribute.getValue());
			}

			if (priority.isPresent()) {
				map.put(e.getName() + "@" + priority.get(), properties);
			} else {
				map.put(e.getName(), properties);
			}
		});

		return map;
	}

}