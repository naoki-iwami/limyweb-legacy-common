package org.limy.common.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class HtmlUtilsTest {

    @Test
    public void testQuoteHtml() {
        assertEquals("&lt;b&gt;M&amp;M&lt;/b&gt;", HtmlUtils.quoteHtml("<b>M&M</b>"));
        assertEquals(null, HtmlUtils.quoteHtml(null));
    }

}
