/*
 * Created 2005/06/05
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
package org.limy.common.web;

/**
 * ブラウザ種別を表します。
 * @author Naoki Iwami
 */
public enum BrowserType {
    
    /** PC全般 */
    PC("PC"),
    /** 携帯（vodafone） */
    VODAFONE("vodafone"),
    /** 携帯（EZweb） */
    EZWEB("EZweb"),
    /** 携帯（DoCoMo） */
    DOCOMO("DoCoMo");
    
    // ------------------------ Fields

    /**
     * 表現用文字列
     */
    private String value;
    
    // ------------------------ Constructors
    
    /**
     * BrowserTypeを構築します。
     * @param value 表現用文字列
     */
    private BrowserType(String value) {
        this.value = value;
    }
    
    // ------------------------ Getter/Setter Methods

    /**
     * 表現用文字列を取得します。
     * @return 表現用文字列
     */
    public String getValue() {
        return value;
    }
    
}
