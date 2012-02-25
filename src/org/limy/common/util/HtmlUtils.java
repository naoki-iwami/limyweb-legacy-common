/*
 * Created 2006/04/24
 * Copyright (C) 2003-2006  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of limy-portal.
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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * HTML関連のユーティリティクラスです。
 * @author Naoki Iwami
 */
public final class HtmlUtils {
    
    // ------------------------ Constants

    /**
     * HTMLクォート化マップ
     */
    private static final Map<Character, String> QUOTE_MAP = new LinkedHashMap<Character, String>();

    // ------------------------ Static Initializer
    
    static {
        QUOTE_MAP.put(Character.valueOf('&'), "&amp;");
        QUOTE_MAP.put(Character.valueOf('<'), "&lt;");
        QUOTE_MAP.put(Character.valueOf('>'), "&gt;");
    }
    
    /**
     * private constructor
     */
    private HtmlUtils() { }
    
    // ------------------------ Public Methods

    public static String convertHtml(String str) {
        return quoteHtml(str).replaceAll("\n", "<br />\n");
    }
    
    /**
     * HTML特殊文字をQuoteして返します。
     * @param str 変換前文字列
     * @return 変換後文字列
     */
    public static String quoteHtml(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder buff = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            appendQuoteChar(buff, c);
        }
        return buff.toString();
    }
    

    /**
     * HTTP形式の文字列に自動リンクを設定します。
     * @param str 文字列
     * @return 変換後文字列
     */
    public static String replaceAutoLink(String str) {
        int len = str.length();
        StringBuffer strBuff = new StringBuffer((int)(len * 1.05f));
        
        int startPos = 0;
        int startBuffPos = 0;
        String autoLink = "http://";
        for (int pos = 0; pos < len; pos++) {
            char c = str.charAt(pos);
            if (c == '\n') {
                if (str.substring(startPos).startsWith(autoLink)) {
                    strBuff.insert(startBuffPos,
                            "<a href=\"" + str.substring(startPos, pos) + "\">");
                    strBuff.append("</a>");
                }
                startPos = pos + 1;
                strBuff.append("<br>\n");
                startBuffPos = strBuff.length();
            } else {
                strBuff.append(c);
            }
        }
        return strBuff.toString();
    }

    // ------------------------ Private Methods

    /**
     * 文字列バッファに文字をHTMLクォートして追加します。
     * @param buff 文字列バッファ
     * @param c 文字
     */
    private static void appendQuoteChar(StringBuilder buff, char c) {
        String str = QUOTE_MAP.get(Character.valueOf(c));
        if (str != null) {
            buff.append(str);
        } else {
            buff.append(c);
        }
    }

}
