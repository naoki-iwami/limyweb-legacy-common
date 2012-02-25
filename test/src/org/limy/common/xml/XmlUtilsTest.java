package org.limy.common.xml;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.w3c.dom.Document;

public class XmlUtilsTest {

    @Test
    public void testParseDoc() throws IOException {
        
        InputStream in = ClassLoader.getSystemResourceAsStream("resources/sample-web.xml");
        Document doc = XmlUtils.parseDoc(in);
        assertNotNull(doc);
        in.close();
    }

    @Test
    public void testParse() throws IOException {
        InputStream in = ClassLoader.getSystemResourceAsStream("resources/sample-web.xml");
        XmlElement el = XmlUtils.parse(in);
        assertNotNull(el);
        in.close();
    }

}
