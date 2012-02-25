package org.limy.common.cache;

import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.CacheManager;

/**
 * Ehcache用のキャッシュマネージャです。
 * @author Naoki Iwami
 */
final class LimyEhcacheManager {
    
    // ------------------------ Static Fields
    
    /**
     * 唯一のインスタンス
     */
    private static final LimyEhcacheManager instance = new LimyEhcacheManager();
    
    // ------------------------ Fields
    
    /**
     * キャッシュ情報
     */
    private CacheManager manager = CacheManager.getInstance();
    
    /**
     * キャッシュ一覧
     */
    private Map<String, LimyEhcache> caches = new HashMap<String, LimyEhcache>();
    
    // ------------------------ Constructors

    /**
     * private constructor
     */
    private LimyEhcacheManager() { }

    // ------------------------ Public Methods

    public static LimyEhcacheManager getInstance() {
        return instance;
    }
    
    public LimyCache getCache(String cacheName) {
        if (!caches.containsKey(cacheName)) {
            // EhcacheをTomcatグローバルClasspathに置いた場合など、
            // サーブレットをリロードしてもCacheManager上には残っている
            if (manager.cacheExists(cacheName)) {
                manager.removeCache(cacheName);
            }
            
            manager.addCache(cacheName);
            
            caches.put(cacheName, new LimyEhcache(manager.getCache(cacheName)));
        }
        return caches.get(cacheName);
    }

    public void removeAllCache() {
        for (LimyCache cache : caches.values()) {
            cache.clearAll();
        }
    }
    
}
