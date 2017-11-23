package com.allin.common.tool;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class XmlUtilsTest {

	private static final Logger LOG = LoggerFactory.getLogger(XmlUtilsTest.class);

	@Test(priority = 1)
	public void testDom2Map() throws DocumentException, IOException {
		long beginTime = System.currentTimeMillis();

		final Map<String, Object> map = XmlUtils.dom2Map(new File("src/test/resources/map.xml"));

		final List<Map<String, Object>> aList = (List<Map<String, Object>>) Map.class.cast(map.get("Databases"))
				.get("mysql");

		System.out.println(aList.get(0));
		System.out.println(aList.get(1));
		System.out.println(aList.get(2));

		LOG.info(map.get("Databases").toString());
		LOG.info("Use time:" + (System.currentTimeMillis() - beginTime) + "ms");
	}

}
