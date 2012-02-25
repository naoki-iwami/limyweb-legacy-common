/*
 * Created 2006/12/11
 * Copyright (C) 2003-2007  Naoki Iwami (naoki@limy.org)
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
package org.limy.common.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * DAO基底クラスです。
 * @author Naoki Iwami
 */
public class BaseDaoSupport extends SqlMapClientDaoSupport {

    // ------------------------ Protected Methods

    /**
     * 空のParamsインスタンスを返します。
     * @return 空のParamsインスタンス
     */
    protected Map<String, Object> createParams() {
        return new HashMap<String, Object>();
    }
    
    /**
     * SELECT文を発行して結果1件を返します。
     * @param id SQL文ID
     * @param params バインドパラメータ
     * @return 検索結果
     */
    protected Object select(String id, Map<String, Object> params) {
        return getSqlMapClientTemplate().queryForObject(id, params);
    }

    /**
     * SELECT文を発行して結果複数件を返します。
     * @param id SQL文ID
     * @param params バインドパラメータ
     * @return 検索結果（のリスト）
     */
    protected List<? extends Object> selects(String id, Map<String, Object> params) {
        return getSqlMapClientTemplate().queryForList(id, params);
    }
    
    /**
     * INSERT文を発行します。
     * @param id SQL文ID
     * @param params バインドパラメータ
     */
    protected void insert(String id, Map<String, Object> params) {
        getSqlMapClientTemplate().insert(id, params);
    }

    /**
     * INSERT文を発行します。
     * @param id SQL文ID
     * @param params バインドパラメータ
     * @return 挿入したレコードのAUTO INCREMENT値
     */
    protected int insertWithAutoIncrement(String id, Map<String, Object> params) {
        getSqlMapClientTemplate().insert(id, params);
        return ((Integer)getSqlMapClientTemplate().queryForObject(
                "getLastInsertId", null)).intValue();
    }

    /**
     * UPDATE文を発行します。
     * @param id SQL文ID
     * @param params バインドパラメータ
     */
    protected void update(String id, Map<String, Object> params) {
        getSqlMapClientTemplate().update(id, params);
    }
    
    /**
     * DELETE文を発行します。
     * @param id SQL文ID
     * @param params バインドパラメータ
     */
    protected void delete(String id, Map<String, Object> params) {
        getSqlMapClientTemplate().delete(id, params);
    }


    protected void setParam(Map<String, Object> params, String key, int value) {
        params.put(key, Integer.valueOf(value));
    }

    protected void setParam(Map<String, Object> params, String key, Object value) {
        params.put(key, value);
    }

}
