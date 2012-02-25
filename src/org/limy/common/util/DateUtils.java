/*
 * Created 2004/07/25
 * Copyright (C) 2003-2007  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of Limyweb.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.limy.common.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 日付関連のユーティリティクラスです。
 * @author Naoki Iwami
 */
public final class DateUtils {
    
    /**
     * private constructor
     */
    private DateUtils() { }
    
    /**
     * 2つの日付オブジェクトの年月が等しいかどうかを返します。
     * @param cal 日付オブジェクト1
     * @param date 日付オブジェクト2
     * @return 2つの日付オブジェクトの年月が等しければ true
     */
    public static boolean equalYM(Calendar cal, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) {
            return true;
        }
        return false;
    }
    
    /**
     * Dateオブジェクトのフィールド値を返します。
     * @param date Dateオブジェクト
     * @param field フィールド種別
     * @return フィールド値
     */
    public static int getField(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * DateオブジェクトからCalendarを生成して返します。
     * @param date Dateオブジェクト
     * @return 生成されたCalendar
     */
    public static Calendar createCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * 指定した日付の第1日を返します。
     * @param date 日付
     * @return 日付の第1日（4/25 -> 4/1）
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = createCalendar(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 指定した日付の次月の第1日を返します。
     * @param date 日付
     * @return 日付の次月の第1日（4/25 -> 5/1）
     */
    public static Date getFirstDayOfNextMonth(Date date) {
        Calendar cal = createCalendar(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    /**
     * 指定した日付の前月の第1日を返します。
     * @param date 日付
     * @return 日付の前月の第1日（4/25 -> 3/1）
     */
    public static Date getFirstDayOfPrevMonth(Date date) {
        Calendar cal = createCalendar(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 日付を取得します。
     * @param year 年
     * @param month 月
     * @param day 日
     * @return 日付
     */
    public static Date getDate(int year, int month, int day) {
        Calendar cal = getCalendar(year, month, day);
        return cal.getTime();
    }

    /**
     * カレンダーを取得します。
     * @param year 年
     * @param month 月
     * @param day 日
     * @return カレンダー
     */
    public static Calendar getCalendar(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal;
    }

    /**
     * 指定した日付の1日00:00:00を表すカレンダーを返します。
     * @param date 日付
     * @return 指定した日付の1日00:00:00を表すカレンダー
     */
    public static Calendar getCalendarPerMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
    

}
