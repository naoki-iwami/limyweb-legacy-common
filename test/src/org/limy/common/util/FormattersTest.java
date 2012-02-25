package org.limy.common.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;


public class FormattersTest {
    
    @Test
    public void testGetDateFormat() {
        System.out.println(new Date(100, 2, 2, 20, 30, 40).getTime()); // 2000/3/2 20:30:40
        DateFormat formatJp = Formatters.getDateFormat(Formatters.D_VIEW_MDE, Locale.JAPAN);
        Assert.assertEquals("3/2(木)", formatJp.format(new Date(951996640000L)));
        DateFormat formatUs = Formatters.getDateFormat(Formatters.D_VIEW_MDE, Locale.US);
        Assert.assertEquals("3/2(Thu)", formatUs.format(new Date(951996640000L)));
    }

    @Test
    public void testDateFormat() {
        System.out.println(new Date(100, 2, 2, 20, 30, 40).getTime()); // 2000/3/2 20:30:40
        Assert.assertEquals("20000302",
                Formatters.dateFormat(Formatters.D_YYYYMMDD, new Date(951996640000L)));
    }

}
