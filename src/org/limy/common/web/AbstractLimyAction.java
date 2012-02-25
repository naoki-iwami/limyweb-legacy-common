/*
 * Created 2007/01/26
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
package org.limy.common.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.WebWorkConstants;
import com.opensymphony.webwork.config.Configuration;
import com.opensymphony.xwork.ActionSupport;

/**
 * 全てのアクションの基底クラスです。
 * @author Naoki Iwami
 */
public abstract class AbstractLimyAction extends ActionSupport {

    /** log */
    private static final Log LOG = LogFactory.getLog(AbstractLimyAction.class);
    
    @Override
    public String execute() throws Exception {
        
        try {
            return doExecute();
        } catch (Exception e) {
            LOG.error(e, e);
            throw e;
        }
    }

    // ------------------------ Web Getter Methods
    
    public String getCharset() {
        if (isPhone()) {
            // 携帯版は文字エンコーディングを Windows-31J に切り替え
            return "Windows-31J";
        }
        // webwork から文字エンコーディングを取得
        return (String)Configuration.get(WebWorkConstants.WEBWORK_I18N_ENCODING);
    }
    
    public String getSelfUri() {
        return ServletActionContext.getRequest().getRequestURI();
    }

    public String getSelfUrl() {
        return ServletActionContext.getRequest().getRequestURL().toString();
    }
    
    public String getJsessionId() {
        return ServletActionContext.getRequest().getRequestedSessionId();
    }

    public String getJID() {
        String id = ServletActionContext.getRequest().getRequestedSessionId();
        if (id != null) {
            return ";jsessionid=" + id;
        }
        return "";
    }

    // ------------------------ Abstract Methods

    /**
     * 各アクションのエントリメソッドです。
     * @return 遷移先
     */
    protected abstract String doExecute();
    
    // ------------------------ Protected Methods

    /**
     * ブラウザ種別を返します。
     * @return ブラウザ種別
     */
    protected BrowserType getBrowserType() {
        return WebUtils.getBrowserType(ServletActionContext.getRequest());
    }

    /**
     * 携帯からのアクセスかどうかを判別します。
     * @return 携帯からのアクセスならば true
     */
    protected boolean isPhone() {
        return WebUtils.getBrowserType(ServletActionContext.getRequest()) != BrowserType.PC;
    }

    /**
     * リモートホストIPアドレスを返します。
     * @return リモートホストIPアドレス
     */
    protected String getRemoteHost() {
        return ServletActionContext.getRequest().getRemoteHost();
    }
    
    protected Log getLog() {
        return LOG;
    }

}
