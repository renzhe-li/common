package common;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.allin.common.tool.XmlUtils;

public class XmlUtilsTest {

	private static final Logger LOG = LoggerFactory.getLogger(XmlUtilsTest.class);

	@Test(priority = 1)
	public void testDom2Map() throws DocumentException, IOException {
		long beginTime = System.currentTimeMillis();

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/test/resources/map.xml"));

		Map<String, Object> map = XmlUtils.dom2Map(document);

		LOG.info(map.toString());
		LOG.info("Use time:" + (System.currentTimeMillis() - beginTime) + "ms");
	}

}
