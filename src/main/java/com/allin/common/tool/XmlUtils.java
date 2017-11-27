package com.allin.common.tool;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allin.common.dto.DataNode;

/**
 * XmlUtils is a utility to processing XML file to {@code DataNode} <br>
 * <br>
 * Every XML Node has a {@code DataNode}:<br>
 * <tab>1.XML Node Name => DataNode::name <br>
 * <tab>2.if child Node is not exist, XML Node value => DataNode::value; else
 * null<br>
 * <tab>3.XML child nodes => DataNode::childNodes <br>
 * <tab>4.the attribute is stored in DataNode::attributes, the key
 * is @attributeName <br>
 * <tab>5.the {@code Map} attributes also store the child node, but only node
 * name and value.
 * 
 * @author renzhe.li
 *
 */
public final class XmlUtils {

	private static final Logger LOG = LoggerFactory.getLogger(XmlUtils.class);

	private XmlUtils() {
	}

	public static DataNode dom2DataNode(final File file) {
		final SAXReader reader = new SAXReader();
		try {
			final Document document = reader.read(file);
			return dom2DataNode(document);
		} catch (final DocumentException e) {
			LOG.error("DocumentException occur when processing file:{}", file.getName(), e);
		}

		return new DataNode();
	}

	public static DataNode dom2DataNode(final Document doc) {
		if (doc == null) {
			return new DataNode();
		}

		return getDataNodeFromElement(doc.getRootElement());
	}

	private static DataNode getDataNodeFromElement(final Element element) {
		final DataNode dataNode = new DataNode();

		final List<Attribute> attributes = element.attributes();
		attributes.stream()
				.forEach(attribute -> dataNode.putAttribute("@" + attribute.getName(), attribute.getValue()));
		LOG.debug("Success Putting {} Attributes to attributes Map for Node path:{}", attributes.size(),
				element.getPath());

		final List<Element> childElements = element.elements();
		childElements.stream().forEach(childElement -> {
			dataNode.putAttribute(childElement.getName(), null);
			if (childElement.elements().isEmpty()) {
				dataNode.putAttribute(childElement.getName(), childElement.getStringValue());
			}

			dataNode.addChildNode(getDataNodeFromElement(childElement));
		});
		LOG.debug("Success Adding {} DataNode to child Node List for Node path:{}", childElements.size(),
				element.getPath());

		dataNode.setName(element.getName());
		if (childElements.isEmpty()) {
			dataNode.setValue(element.getStringValue());
		}
		LOG.debug("Success Setting {} to DataNode::value for Node path:{}", dataNode.getValue(), element.getPath());

		return dataNode;
	}

}