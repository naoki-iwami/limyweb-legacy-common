/*
 * Created 2005/11/03
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
 * Limyweb共通のキャッシュマネージャです。
 * @author Naoki Iwami
 */
public final class LimyCacheManager {
    
    // ------------------------ Class

    /**
     * ダミー用キャッシュクラスです。
     * @author Naoki Iwami
     */
    static class DummyLimyCache implements LimyCache {
        public void clearAll() {
            // do nothing
        }
        public Object get(String key) {
            return null; // do nothing
        }
        public void put(String key, Object obj) {
            // do nothing
        }
        public void remove(String key) {
            // do nothing
        }
        public void removeTree(String prefixKey) {
            // do nothing
        }
    }

    // ------------------------ Static Fields

    /**
     * ehcache環境ならば真にするフラグ
     */
    private static boolean existEhcache;
    
    /**
     * ダミー用の空キャッシュ
     */
    private static final LimyCache EMPTY_CACHE = new DummyLimyCache();

    // ------------------------ Static Methods
    
    static {
        try {
            Class.forName("net.sf.ehcache.Cache");
            existEhcache = true;
        } catch (ClassNotFoundException e) {
            existEhcache = false;
        }
    }
    
    /**
     * private constructor
     */
    private LimyCacheManager() { }
    
    /**
     * キャッシュを取得します。
     * @param cacheName キャッシュ名
     * @return キャッシュ
     */
    public static LimyCache getCache(String cacheName) {
        if (existEhcache) {
            return LimyEhcacheManager.getInstance().getCache(cacheName);
        }
        return EMPTY_CACHE; // デフォルトではダミーキャッシュを返す
    }

    /**
     * キャッシュを取得します。
     * @param clazz キャッシュを使用するクラス
     * @return キャッシュ
     */
    public static LimyCache getCache(Class clazz) {
        if (existEhcache) {
            return LimyEhcacheManager.getInstance().getCache(clazz.getName());
        }
        return EMPTY_CACHE; // デフォルトではダミーキャッシュを返す
    }

//    /**
//     * キャッシュを作成します（既に存在する場合はクリアしてから返します）。
//     * @param clazz キャッシュを使用するクラス
//     * @return キャッシュ
//     */
//    public static LimyCache createCache(Class clazz) {
//        if (existEhcache) {
//            return LimyEhcacheManager.getInstance().getCache(clazz.getName());
//        }
//        return EMPTY_CACHE; // デフォルトではダミーキャッシュを返す
//    }

}
