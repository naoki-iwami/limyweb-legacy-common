package org.limy.common.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class WebUtilsTest {
    
    private ServletUnitClient sc;
    
    private WebRequest request;

    @Before
    public void setUp() throws Exception {
        ServletRunner sr = new ServletRunner(
                ClassLoader.getSystemResourceAsStream("resources/sample-web.xml"));
        sc = sr.newClient();
        request = new PostMethodWebRequest("http://localhost/dummy");
    }

    @Test
    public void testGetBrowserType() throws Exception {
        assertEquals(BrowserType.PC, WebUtils.getBrowserType(sc.newInvocation(request).getRequest()));
        request.setHeaderField("user-agent", "DoCoMo");
        assertEquals(BrowserType.DOCOMO, WebUtils.getBrowserType(sc.newInvocation(request).getRequest()));
        request.setHeaderField("user-agent", "UP.Browser");
        assertEquals(BrowserType.EZWEB, WebUtils.getBrowserType(sc.newInvocation(request).getRequest()));
        request.setHeaderField("user-agent", "J-PHONE");
        assertEquals(BrowserType.VODAFONE, WebUtils.getBrowserType(sc.newInvocation(request).getRequest()));
        request.setHeaderField("user-agent", "dummy");
        assertEquals(BrowserType.PC, WebUtils.getBrowserType(sc.newInvocation(request).getRequest()));

//        request.setHeaderField("user-agent", "dummy");
//        assertEquals("", WebUtils.getPcBrowserTypeShort(createWebResource()));
//        
//        request.setHeaderField("user-agent", "Firefox");
//        assertEquals("FF", WebUtils.getPcBrowserTypeShort(createWebResource()));
//
//        request.setHeaderField("user-agent", "Minefield");
//        assertEquals("FF", WebUtils.getPcBrowserTypeShort(createWebResource()));
//
//        request.setHeaderField("user-agent", "MSIE");
//        assertEquals("IE", WebUtils.getPcBrowserTypeShort(createWebResource()));
    }

    @Test
    public void testGetTimestampDefault() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(WebUtils.getTimestampDefault("20060515123456"));
        
        assertEquals(2006, cal.get(Calendar.YEAR));
        assertEquals(Calendar.MAY, cal.get(Calendar.MONTH));
        assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(12, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(34, cal.get(Calendar.MINUTE));
        assertEquals(56, cal.get(Calendar.SECOND));

        cal.setTime(WebUtils.getTimestampDefault("20060515"));
        assertEquals(2006, cal.get(Calendar.YEAR));
        assertEquals(Calendar.MAY, cal.get(Calendar.MONTH));
        assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
        
        cal.setTime(WebUtils.getTimestampDefault("200605"));
        assertEquals(2006, cal.get(Calendar.YEAR));
        assertEquals(Calendar.MAY, cal.get(Calendar.MONTH));

        // 不正な形式を指定した場合は現在時刻が返る（nullや例外にはならない）
        assertNotNull(WebUtils.getTimestampDefault("dummy-date"));
        assertNotNull(WebUtils.getTimestampDefault("2006051512345611"));
        assertNotNull(WebUtils.getTimestampDefault("200605151234XX"));

    }
    
    @Test
    public void testCreateWebSession() throws Exception {
        
//        DefaultWebContextBuilder builder = new DefaultWebContextBuilder();
//        WebContextFactory.setWebContextBuilder(builder);
//
//        InvocationContext invocation = sc.newInvocation(request);
//        DefaultContainer container = ContainerUtil.createDefaultContainer(
//                invocation.getServlet().getServletConfig());
//        
//        DefaultScriptSessionManager manager = new DefaultScriptSessionManager();
//        manager.setPageNormalizer(new DefaultPageNormalizer());
//        container.addParameter(ScriptSessionManager.class.getName(), manager);
//        
//        builder.set(
//                invocation.getRequest(),
//                invocation.getResponse(),
//                invocation.getServlet().getServletConfig(),
//                null,
//                container);
        
        // TODO HttpUnitを使って実践テスト
        WebDwrUtils.createWebSession();
        
    }
    
}
