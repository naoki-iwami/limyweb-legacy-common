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

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Ehcacheによるキャッシュ実装クラスです。
 * @author Naoki Iwami
 */
public class LimyEhcache implements LimyCache {

    /**
     * logger
     */
    private static final Log LOG = LogFactory.getLog(LimyEhcache.class);
    
    /**
     * キャッシュ
     */
    private Cache cache;
    
    // ------------------------ Constructors

    /**
     * LimyEhcacheインスタンスを構築します。
     * @param cache キャッシュ
     */
    public LimyEhcache(Cache cache) {
        this.cache = cache;
    }
    
    // ------------------------ Implement Methods

    public void clearAll() {
        LOG.debug("remove cache all.");
        
        cache.removeAll();
//        try {
//            cache.removeAll();
//        } catch (IOException e) {
//            logger.fatal(e);
//        }
    }

    public Object get(String key) {
        Object result = null;
        Element element = cache.get(key);
        if (element != null) {
            result = element.getObjectValue();
        }
//        logger.debug("load cache '" + key + "', result = " + result);
        LOG.debug("load cache '" + key + "'");
        return result;
    }

    public void put(String key, Object obj) {
        LOG.debug("save cache '" + key + "'");
        cache.put(new Element(key, obj));
    }

    public void remove(String key) {
        LOG.debug("remove cache '" + key + "'");
        cache.remove(key);
    }

    public void removeTree(String prefixKey) {
        LOG.debug("remove cache tree '" + prefixKey + "'...");
        for (String key : (List<String>)cache.getKeys()) {
            if (key.startsWith(prefixKey)) {
                LOG.debug("remove cache '" + key + "'");
                cache.remove(key);
            }
        }
    }
    
    public Cache getCache() {
        return cache;
    }

}
