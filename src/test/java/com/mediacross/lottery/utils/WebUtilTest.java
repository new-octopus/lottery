package com.mediacross.lottery.utils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;

import junit.framework.TestCase;

public class WebUtilTest extends TestCase {
	public void testParseUrlParameters() {
		assertNull(WebUtil.parseUrlParameters(""));
		Map paramMap = WebUtil.parseUrlParameters("a=a&a=b&b=c");
		assertEquals(2, paramMap.size());
		assertTrue(paramMap.get("a") instanceof List);
		assertEquals(2, ((List) paramMap.get("a")).size());
		assertEquals("a", ((List) paramMap.get("a")).get(0));
		assertEquals("b", ((List) paramMap.get("a")).get(1));
		assertEquals("c", paramMap.get("b"));
	}

}
