/*
 * Created 2003/07/24
 * Copyright (C) 2003-2005  Naoki Iwami (naoki@limy.org)
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
package org.limy.common.bean;

/**
 * 文字列と数値をペアで持つオブジェクトです。
 * 数値で比較することができます。
 * @author Naoki Iwami
 */
public class CountObject implements Comparable<CountObject> {
    
    // ------------------------ Fields

    /**
     * 文字列
     */
    private final String str;

    /**
     * ハッシュ値
     */
    private final int hash;

    /**
     * 数値
     */
    private int count;
    
    // ------------------------ Constructors
    
    /**
     * CountObjectインスタンスを構築します。
     * @param str 文字列
     */
    public CountObject(String str) {
        this.str = str;
        if (str != null) {
            this.hash = str.hashCode();
        } else {
            this.hash = 0;
        }
        this.count = 0;
    }

    /**
     * CountObjectインスタンスを構築します。
     * @param str 文字列
     * @param count 数値
     */
    public CountObject(String str, int count) {
        this.str = str;
        if (str != null) {
            this.hash = str.hashCode();
        } else {
            this.hash = 0;
        }
        this.count = count;
    }

    // ------------------------ Override Methods
    
    public boolean equals(Object obj) {
        if (obj instanceof CountObject) {
            if (str == null) {
                return ((CountObject)obj).getStr() == null;
            }
            return str.equals(((CountObject)obj).getStr());
        }
        return false;
    }

    public int hashCode() {
        return hash;
    }
    
    public String toString() {
        return getStr() + " : " + count;
    }
    
    // ------------------------ Implement Methods

    public int compareTo(CountObject o) {
        return count - o.count;
    }

    // ------------------------ Public Methods
    
    /**
     * 数値をインクリメントします。
     */
    public void increment() {
        ++count;
    }
    
    // ------------------------ Getter/Setter Methods

    /**
     * 文字列を取得します。
     * @return 文字列
     */
    public String getStr() {
        return str;
    }

    /**
     * 数値を取得します。
     * @return 数値
     */
    public int getCount() {
        return count;
    }

}
