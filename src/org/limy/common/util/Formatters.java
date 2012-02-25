/*
 * Created 2004/08/22
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 各種フォーマットを扱うクラスです。
 * @author Naoki Iwami
 */
public final class Formatters {
    
    /** フォーマット : yyyyMMdd形式 */
    public static final String D_YYYYMMDD = "yyyyMMdd";

    /** フォーマット : yyyyMMddHHmmss形式 */
    public static final String D_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** フォーマット : yyyyMMddHHmm形式 */
    public static final String D_YYYYMMDDHHMM = "yyyyMMddHHmm";

    /** フォーマット : yyyyMM形式 */
    public static final String D_YYYYMM = "yyyyMM";

    /** フォーマット : yyyy年M月形式 */
    public static final String D_JPN_VIEW_YYYYM = "yyyy年M月";

    /** フォーマット : ddHHMMSS形式 */
    public static final String D_DDHHMMSS = "ddHHmmss";

    /** フォーマット : dd形式 */
    public static final String D_DD = "dd";
    
    /** フォーマット : yyyy/M/d HH:mm:ss形式 */
    public static final String D_VIEW_YMDHMS = "yyyy/M/d HH:mm:ss";
    
    /** フォーマット : yyyy/M/d HH:mm形式 */
    public static final String D_VIEW_YMDHM = "yyyy/M/d HH:mm";

    /** フォーマット : yyyy/M/d HH:mm形式 */
    public static final String D_VIEW_YYYYMMDDHHMM = "yyyy/MM/dd HH:mm";

    /** フォーマット : yyyy/MM/dd HH:mm:ss形式 */
    public static final String D_VIEW_14 = "yyyy/MM/dd HH:mm:ss";

    /** フォーマット : yyyy/MM/dd（E） HH:mm:ss形式 */
    public static final String D_VIEW_14E = "yyyy/MM/dd（E） HH:mm:ss";
    
    /** フォーマット : yyyy/MM/dd（E）形式 */
    public static final String D_VIEW_8E = "yyyy/MM/dd（E）";

    /** フォーマット : yyyy/MM/dd形式 */
    public static final String D_VIEW_8 = "yyyy/MM/dd";

    /** フォーマット : M/d(E)形式 */
    public static final String D_VIEW_MDE = "M/d(E)";

    /** フォーマット : yyyy/M/d形式 */
    public static final String D_VIEW_YMD = "yyyy/M/d";
    
    /** フォーマット : yyyy/M形式 */
    public static final String D_VIEW_YM = "yyyy/M";
    
    /** フォーマット : M/d形式 */
    public static final String D_VIEW_MD = "M/d";
    
    /** フォーマット : d形式 */
    public static final String D_VIEW_D = "d";
    
    /** フォーマット : W3C形式 */
    public static final String W3C = "yyyy-MM-dd'T'HH:mm:ss+09:00";

    /** フォーマット : RFC822形式 */
    public static final String RFC822 = "EEE, dd MMM yyyy HH:mm:ss Z";

    /** フォーマット : 00形式 */
    public static final String N_00 = "00";
    
    // ------------------------ Constructors

    /**
     * private constructor
     */
    private Formatters() { }

    // ------------------------ Public Methods

    /**
     * 日付フォーマットを生成します。
     * @param key フォーマットキー文字列
     * @param locale ロケール
     * @return 日付フォーマット
     */
    public static DateFormat getDateFormat(String key, Locale locale) {
        return new SimpleDateFormat(key, locale);
    }

    /**
     * 日付をフォーマットします。
     * @param key フォーマットキー文字列
     * @param date 日付
     * @return フォーマット化された日付文字列
     */
    public static String dateFormat(String key, Date date) {
        return new SimpleDateFormat(key).format(date);
    }

    /**
     * 日付をフォーマットします。
     * @param key フォーマットキー文字列
     * @param date 日付
     * @param locale ロケール
     * @return フォーマット化された日付文字列
     */
    public static String dateFormat(String key, Date date, Locale locale) {
        return new SimpleDateFormat(key, locale).format(date);
    }

    /**
     * 日付文字列をパースします。
     * @param key フォーマットキー文字列
     * @param dateStr 日付文字列
     * @return 日付
     * @throws ParseException パース例外
     */
    public static Date dateParse(String key, String dateStr) throws ParseException {
        return new SimpleDateFormat(key).parse(dateStr);
    }

}
