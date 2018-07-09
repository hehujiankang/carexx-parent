package com.sh.carexx.common.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public final class JAXBUtils {
	public static final String CHARSET = "UTF-8";
	
	public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	public static String convert2Xml(Object obj) throws Exception {
		return convert2Xml(obj, false, CHARSET);
	}

	public static String convert2Xml(Object obj, boolean isWithHeader, String encoding) throws Exception {
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
		StringWriter writer = new StringWriter();
		marshaller.marshal(obj, writer);
		if (!isWithHeader) {
			return writer.toString();
		}
		return XML_HEADER + writer.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T convert2JavaBean(String xml, Class<T> c) throws Exception {
		xml = xml.replace(XML_HEADER, "");
		JAXBContext context = JAXBContext.newInstance(c);
		Unmarshaller unmarshaller = context.createUnmarshaller();
	    return (T) unmarshaller.unmarshal(new StringReader(xml));
	}
}
