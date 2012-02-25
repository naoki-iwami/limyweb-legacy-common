/*
 * Created 2007/01/29
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
package org.limy.common.web;

import java.sql.Timestamp;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.limy.common.util.Formatters;

/**
 * Web関連のユーティリティクラスです。
 * @author Naoki Iwami
 */
public final class WebUtils {

    // ------------------------ Constructors
    
    /**
     * private constructor
     */
    private WebUtils() { }
    
    // ------------------------ Public Methods
    
    /**
     * ブラウザ種別を返します。
     * @param request HTTPリクエスト
     * @return ブラウザ種別（HTTPリクエストヘッダより判別）
     */
    public static BrowserType getBrowserType(HttpServletRequest request) {
        String agent = request.getHeader("user-agent");
        // この段階でgetParameterを呼んではいけない。このメソッドはフィルタ内からも呼ばれるため、
        // ここでgetParameterを呼ぶとエンコーディングがISO-8859-1に固定されてしまう。
        
        if (agent == null) {
            return BrowserType.VODAFONE;
        } else if (agent.indexOf("Semulator/") == 0) {
            return BrowserType.VODAFONE;
        } else if (agent.indexOf("J-PHONE") >= 0) {
            return BrowserType.VODAFONE;
        } else if (agent.indexOf("DoCoMo") >= 0) {
            return BrowserType.DOCOMO;
        } else if (agent.indexOf("UP.Browser") >= 0) {
            return BrowserType.EZWEB;
        } else {
            // それ以外はPC端末とする
            return BrowserType.PC;
        }
    }
    
    /**
     * タイムスタンプを取得します。nullだったら現在日付を返します。
     * @param dateStr 日付文字列
     * @return 取得したタイムスタンプ
     */
    public static Timestamp getTimestampDefault(String dateStr) {
        
        if (dateStr != null) {
            Timestamp timestamp = createTimestamp(dateStr);
            if (timestamp != null) {
                return timestamp;
            }
        }
        return new Timestamp(System.currentTimeMillis());
    }
    
    /**
     * 携帯からのアクセスかどうかを判別します。
     * @param hreq 
     * @return 携帯からのアクセスならば true
     */
    public static boolean isPhone(HttpServletRequest hreq) {
        return WebUtils.getBrowserType(hreq) != BrowserType.PC;
    }

    // ------------------------ Private Methods

    /**
     * 日付文字列をパースしてタイムスタンプを返します。
     * @param dateStr 日付文字列
     * @return タイムスタンプ
     */
    private static Timestamp createTimestamp(String dateStr) {
        try {
            switch (dateStr.length()) {
            case 14:
                return new Timestamp(
                        Formatters.dateParse(Formatters.D_YYYYMMDDHHMMSS, dateStr).getTime());
            case 8:
                return new Timestamp(
                        Formatters.dateParse(Formatters.D_YYYYMMDD, dateStr).getTime());
            case 6:
                return new Timestamp(
                        Formatters.dateParse(Formatters.D_YYYYMM, dateStr).getTime());
            default:
                return null;
            }
        } catch (ParseException e) {
            return null;
        }
    }

}
