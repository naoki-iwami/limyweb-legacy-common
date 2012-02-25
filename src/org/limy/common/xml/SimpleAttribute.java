/*
 * Created 2006/08/19
 * Copyright (C) 2003-2006  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of limy-eclipse-qalab.
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
package org.limy.common.xml;

/**
 * XMLの属性を表します。
 * @author Naoki Iwami
 * @version 1.0.0
 */
public class SimpleAttribute implements XmlAttribute {
    
    // ------------------------ Fields

    /**
     * 属性名
     */
    private final String name;
    
    /**
     * 属性値
     */
    private final String value;

    // ------------------------ Constructors

    /**
     * SimpleAttributeインスタンスを構築します。
     * @param name 属性名
     * @param value 属性値
     */
    public SimpleAttribute(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    // ------------------------ Override Methods

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SimpleAttribute) {
            SimpleAttribute attr = (SimpleAttribute)obj;
            return attr.name.equals(name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append(name).append("=").append(value);
        return buff.toString();
    }
    
    // ------------------------ Implement Methods

    /**
     * 属性名を取得します。
     * @return 属性名
     */
    public String getName() {
        return name;
    }

    /**
     * 属性値を取得します。
     * @return 属性値
     */
    public String getValue() {
        return value;
    }


}
