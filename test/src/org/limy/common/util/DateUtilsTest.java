package org.limy.common.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("boxing")
public class DateUtilsTest {

    @Test
    public void testEqualYM() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 1, 1, 10, 20, 30); // 2000/02/01 10:20:30
//        System.out.println(new Date(100, 2, 2, 20, 30, 40).getTime());
        Date date = new Date();
        
        date.setTime(949491040000L); // 2000/02/02 20:30:40
        Assert.assertTrue(DateUtils.equalYM(cal, date));
        date.setTime(951996640000L); // 2000/03/02 20:30:40
        assertFalse(DateUtils.equalYM(cal, date));
        
    }

    @Test
    public void testGetField() {
        Date date = new Date(949491040000L); // 2000/02/02 20:30:40
        Assert.assertEquals(2000, DateUtils.getField(date, Calendar.YEAR));
        Assert.assertEquals(1, DateUtils.getField(date, Calendar.MONTH));
        Assert.assertEquals(2, DateUtils.getField(date, Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testCreateCalendar() {
        Date date = new Date(949491040000L); // 2000/02/02 20:30:40
        assertNotNull(DateUtils.createCalendar(date));
    }

    @Test
    public void testGetFirstDayOfMonth() {
        Date date = new Date(949491040000L); // 2000/02/02 20:30:40
        Date resultDay = DateUtils.getFirstDayOfMonth(date);
        Assert.assertEquals(1, DateUtils.getField(resultDay, Calendar.MONTH));
        Assert.assertEquals(1, DateUtils.getField(resultDay, Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testGetFirstDayOfNextMonth() {
        Date date = new Date(949491040000L); // 2000/02/02 20:30:40
        Date resultDay = DateUtils.getFirstDayOfNextMonth(date);
        Assert.assertEquals(2, DateUtils.getField(resultDay, Calendar.MONTH));
        Assert.assertEquals(1, DateUtils.getField(resultDay, Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testGetFirstDayOfPrevMonth() {
        Date date = new Date(949491040000L); // 2000/02/02 20:30:40
        Date resultDay = DateUtils.getFirstDayOfPrevMonth(date);
        Assert.assertEquals(0, DateUtils.getField(resultDay, Calendar.MONTH));
        Assert.assertEquals(1, DateUtils.getField(resultDay, Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testGetDate() {
        Date date = DateUtils.getDate(2000, 2, 2);
        Assert.assertEquals(2000, DateUtils.getField(date, Calendar.YEAR));
        Assert.assertEquals(1, DateUtils.getField(date, Calendar.MONTH));
        Assert.assertEquals(2, DateUtils.getField(date, Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testGetCalendar() {
        Calendar cal = DateUtils.getCalendar(2000, 2, 2);
        Assert.assertEquals(2000, cal.get(Calendar.YEAR));
        Assert.assertEquals(1, cal.get(Calendar.MONTH));
        Assert.assertEquals(2, cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testGetCalendarPerMonth() {
        Date date = new Date(949491040000L); // 2000/02/02 20:30:40
        Calendar cal = DateUtils.getCalendarPerMonth(date);
        Assert.assertEquals(2000, cal.get(Calendar.YEAR));
        Assert.assertEquals(1, cal.get(Calendar.MONTH));
        Assert.assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(0, cal.get(Calendar.MINUTE));
        Assert.assertEquals(0, cal.get(Calendar.SECOND));
        Assert.assertEquals(0, cal.get(Calendar.MILLISECOND));
    }


}
