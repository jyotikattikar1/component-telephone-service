package com.belong.telephone.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author jyotikattikar
 *
 */
public class PropertyUtil {

	private PropertyUtil() {
	}

	public static String getProperty(String key) throws IOException {
		ClassPathResource resource = new ClassPathResource("/messages.properties");
		Properties prop = PropertiesLoaderUtils.loadProperties(resource);
		return (String) prop.get(key);
	}
}
