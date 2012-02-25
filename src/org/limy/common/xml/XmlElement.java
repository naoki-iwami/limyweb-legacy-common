/*
 * Created 2007/02/07
 * Copyright (C) 2003-2007  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of Limy Eclipse Plugin.
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

import java.util.Collection;
import java.util.List;

/**
 * XML要素を表すインターフェイスです。
 * @author Naoki Iwami
 */
public interface XmlElement {

    /**
     * 自分自身のコピーを作成します。
     * @return コピーされた要素
     */
    XmlElement cloneSelf();

    /**
     * 自分自身の直前（同系列）に自身のコピーを作成します。
     * @return コピーされた要素
     */
    XmlElement copyBeforeSelf();

    /**
     * 属性を追加します。
     * @param attr 属性
     */
    void setAttribute(XmlAttribute attr);
    
    /**
     * 属性を追加します。
     * @param name 属性名
     * @param value 属性値
     */
    void setAttribute(String name, String value);

    /**
     * 子要素を削除します。
     * @param child 子要素
     */
    void removeChild(XmlElement child);

    /**
     * 属性を持っているかどうかを返します。
     * @return 属性を持っていれば真
     */
    boolean hasAttributes();
    
    /**
     * 子要素を持っているかどうかを返します。
     * @return 子要素を持っていれば真
     */
    boolean hasChildren();
    
    /**
     * 子要素一覧を返します。
     * @return 子要素一覧
     */
    List<XmlElement> getChildren();

    /**
     * 属性値を返します。
     * @param name 属性名
     * @return 属性値
     */
    String getAttribute(String name);
    
    /**
     * 子要素を追加します。
     * @param child 子要素
     */
    void addChild(XmlElement child);
    
    /**
     * 子要素を追加します。
     * @param index 挿入位置
     * @param child 子要素
     */
    void addChild(int index, XmlElement child);

    /**
     * 要素名を取得します。
     * @return 要素名
     */
    String getName();

    /**
     * 要素値を取得します。
     * @return 要素値
     */
    String getValue();
    
    /**
     * 簡易XPath形式で指定された子要素の要素値を取得します。
     * @param xpath XPath文字列
     * @return 要素値
     */
    String getValue(String xpath);
    
    /**
     * 要素値を設定します。
     * @param value 要素値
     */
    void setValue(String value);
    
    /**
     * 子要素から特定の要素名のものを探し出します。
     * <p>
     * 複数見つかった場合は先頭のもの、見つからなかった場合はnullを返します。
     * </p>
     * @param nodeNode 検索する要素名
     * @return 見つかった要素
     */
    XmlElement searchSubNode(String nodeNode);
    
    /**
     * 子要素から特定の要素名のものを全て返します。
     * @param nodeNode 検索する要素名
     * @return 見つかった要素一覧
     */
    Collection<XmlElement> searchSubNodes(String nodeNode);
    
    /**
     * 属性一覧を取得します。
     * @return 属性一覧
     */
    Collection<XmlAttribute> getAttributes();
    
    
}
