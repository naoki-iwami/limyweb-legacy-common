/*
 * Created 2006/07/29
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
package org.limy.common.cache;

/**
 * キャッシュを表すインターフェイスです。
 * @author Naoki Iwami
 */
public interface LimyCache {
    
    /**
     * オブジェクトをキャッシュに保存します。
     * @param key キャッシュキー
     * @param obj オブジェクト
     */
    void put(String key, Object obj);
    
    /**
     * オブジェクトをキャッシュから取得します。
     * @param key キャッシュキー
     * @return オブジェクト
     */
    Object get(String key);

    /**
     * オブジェクトをキャッシュから削除します。
     * @param key キャッシュキー
     */
    void remove(String key);

    /**
     * オブジェクトをキャッシュから階層的に削除します。
     * @param prefixKey キャッシュのプレフィックス文字列
     */
    void removeTree(String prefixKey);

    /**
     * 保存されたキャッシュを全てクリアします。
     */
    void clearAll();
    


}
