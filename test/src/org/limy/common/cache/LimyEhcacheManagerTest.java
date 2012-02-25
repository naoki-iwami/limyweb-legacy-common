package org.limy.common.cache;

import org.junit.Test;

public class LimyEhcacheManagerTest {

    @Test
    public void test() {
        LimyEhcacheManager manager = LimyEhcacheManager.getInstance();
        manager.getCache("1");
        manager.getCache("2");
        manager.getCache("1");
        manager.removeAllCache();
    }

}
