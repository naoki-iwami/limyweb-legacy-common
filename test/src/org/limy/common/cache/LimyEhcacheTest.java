package org.limy.common.cache;

import static org.junit.Assert.*;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.junit.Before;
import org.junit.Test;

public class LimyEhcacheTest {

    /**
     * Limyキャッシュ
     */
    private LimyEhcache limyEhcache;

    @Before
    public void setUp() throws Exception {
        
        CacheManager cacheManager = CacheManager.getInstance();
        if (cacheManager.cacheExists(this.getClass().getName())) {
            cacheManager.removalAll();
        }
        cacheManager.addCache(this.getClass().getName());
        Cache cache = cacheManager.getCache(this.getClass().getName());
        limyEhcache = new LimyEhcache(cache);
    }

    @Test
    public void test() {
        limyEhcache.put("key", "value");
        assertEquals("value", limyEhcache.get("key"));
        assertNull(limyEhcache.get("notKey"));
        limyEhcache.remove("key");
        limyEhcache.remove("notKey");
        assertNull(limyEhcache.get("key"));
        
        limyEhcache.put("1/abc", "");
        limyEhcache.put("1/def", "");
        limyEhcache.put("2/abc", "");
        limyEhcache.put("2/def", "");
        limyEhcache.put("2/ghi", "");
        limyEhcache.removeTree("1");
        assertNull(limyEhcache.get("1/abc"));
        assertNull(limyEhcache.get("1/def"));
        limyEhcache.clearAll();
        assertNull(limyEhcache.get("2/abc"));
    }
}
