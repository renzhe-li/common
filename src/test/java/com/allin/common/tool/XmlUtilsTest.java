package com.allin.common.tool;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.allin.common.dto.DataNode;

public class XmlUtilsTest {

	private static final Logger LOG = LoggerFactory.getLogger(XmlUtilsTest.class);

	@Test(priority = 1)
	public void testDom2Map() throws DocumentException, IOException {
		long beginTime = System.currentTimeMillis();

		final DataNode dataNode = XmlUtils.dom2DataNode(new File("src/test/resources/map.xml"));
		final List<DataNode> dataNodes = dataNode.getChildNodes();

		System.out.println(dataNodes.get(0));
		System.out.println(dataNodes.get(1));
		System.out.println(dataNodes.get(2));
		System.out.println(dataNodes.get(3));
		System.out.println(dataNodes.get(4));

		LOG.info(dataNode.getAttribute("Databases"));
		LOG.info("Use time:" + (System.currentTimeMillis() - beginTime) + "ms");
	}

}
