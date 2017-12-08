package com.allin.common.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class DataNode {
	private String name;// Node::name
	private String value;// Node::value
	private List<DataNode> childNodes;// Child Node list
	private Map<String, String> attributes;// Node::[{@attributeName=value},{nodeName=value}]

	public DataNode() {
		value = null;
		childNodes = new ArrayList<>();
		attributes = new HashMap<>();
	}

	public String getAttribute(final String key) {
		return attributes.get(key);
	}

	public String getAttributeOrDefault(final String key, final String defaultValue) {
		return attributes.getOrDefault(key, defaultValue);
	}

	public void putAttribute(final String key, final String value) {
		attributes.put(key, value);
	}

	public void addChildNode(final DataNode childNode) {
		childNodes.add(childNode);
	}
}