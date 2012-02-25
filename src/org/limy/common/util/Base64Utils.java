/*
 * Created 2004/01/28
 * Copyright (C) 2003-2005  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of Limy Core Library.
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * BASE64関連のユーティリティクラスです。
 * @author Naoki Iwami
 */
public final class Base64Utils {
    
    /**
     * private constructor
     */
    private Base64Utils() { }
    
    /**
     * 文字列をEUC-JP文字セットでBASE64エンコードします。
     * <p>
     * 文字列中にある改行コードは全て削除されます。
     * </p>
     * @param in 文字列
     * @return BASE64エンコードされた文字列
     */
    public static String encodeEucCookie(String in) {
        return encode(in, "EUC-JP").replaceAll("\\n", "").replaceAll("\\r", "");
    }

    /**
     * 文字列を指定した文字セットでBASE64エンコードします。
     * @param in 文字列
     * @param charset 文字セット
     * @return BASE64エンコードされた文字列
     */
    private static String encode(String in, String charset) {
        try {
            return new String(Base64.encodeBase64(in.getBytes(charset)));
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * BASE64文字列をデコードします（EUC）。
     * @param in BASE64文字列
     * @return デコードされた文字列
     */
    public static String decodeEuc(String in) {
        try {
            return new String(Base64.decodeBase64(in.getBytes("EUC-JP")));
        } catch (IOException e) {
            return null;
        }
    }

}
