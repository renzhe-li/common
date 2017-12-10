package com.allin.common.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.allin.common.constant.Constants;

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
	private String path;// Node::path
	private String priority;// Node::@priority
	private List<DataNode> childNodes;// Child Node list
	private Map<String, String> attributes; // attributeName=value

	public DataNode() {
		value = Constants.EMPTY_STRING;
		path = Constants.DIRECTORY_SEPARATOR;
		childNodes = new ArrayList<>();
		attributes = new HashMap<>();
	}

	public boolean containsAttribute(final String key) {
		return attributes.containsKey(key);
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

	public DataNode getChildNode(final String nodeName) {

		return getChildNode(nodeName, null);
	}

	public DataNode getChildNode(final String nodeName, final String priority) {
		if (nodeName == null || nodeName.isEmpty()) {
			return new DataNode();
		}
		if (priority == null || priority.isEmpty()) {
			final Optional<DataNode> optional = childNodes.stream()
					.filter(dataNode -> nodeName.equalsIgnoreCase(dataNode.getName())).findFirst();
			return optional.orElse(new DataNode());
		}

		final Optional<DataNode> optional = childNodes.stream()
				.filter(dataNode -> nodeName.equalsIgnoreCase(dataNode.getName())
						&& priority.equalsIgnoreCase(dataNode.getPriority()))
				.findFirst();
		return optional.orElse(new DataNode());
	}

	public List<DataNode> listChildNodes(final String nodeName) {
		if (nodeName == null || nodeName.isEmpty()) {
			return new ArrayList<>(Arrays.asList(new DataNode()));
		}
		return childNodes.stream().filter(dataNode -> nodeName.equalsIgnoreCase(dataNode.getName()))
				.collect(Collectors.toList());
	}

}
