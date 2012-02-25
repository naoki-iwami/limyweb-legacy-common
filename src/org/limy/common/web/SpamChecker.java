/*
 * Created 2007/07/29
 * Copyright (C) 2003-2007  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of limyweb-common.
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.limy.common.LimyException;

/**
 * スパムチェッカーです。
 * @author Naoki Iwami
 */
public final class SpamChecker {
    
    private static final Pattern PAT_HTTP = Pattern.compile("http://",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

    /**
     * private constructor
     */
    private SpamChecker() { }
    
    /**
     * 文字列が1文字以上であることを確認します。
     * @param text 文字列
     * @throws LimyException 文字列が空の場合
     */
    public static void checkNotEmpty(String text) throws LimyException {
        if (StringUtils.isEmpty(text)) {
            throw new LimyException("1文字も入力されていません。");
        }
    }
    
    /**
     * 文字列に日本語がある事を確認します。
     * @param text 文字列
     * @throws LimyException 日本語が1文字も無い場合
     */
    public static void checkJapanese(String text) throws LimyException {
        int jcnt = 0;
        if (text != null) {
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (!(c >= 0 && c <= 255)) {
                    ++jcnt;
                }
            }
        }
        if (jcnt == 0) {
            throw new LimyException("日本語を最低1文字入力して下さい。");
        }
    }
    
    public static void checkKeyword(String text, String... keywords) throws LimyException {
        for (String keyword : keywords) {
            if (text.indexOf(keyword) >= 0) {
                throw new LimyException("禁止文字が含まれています。");
            }
        }
    }
    
    public static void checkHref(String text, int count) throws LimyException {
        Matcher matcher = PAT_HTTP.matcher(text);
        
        int cnt = 0;
        while (matcher.find()) {
            ++cnt;
        }
        if (cnt > count) {
            throw new LimyException("ＨＴＴＰ表記の数を減らして下さい。");
        }

    }


}
