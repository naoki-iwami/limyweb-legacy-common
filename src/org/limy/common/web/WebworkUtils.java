/*
 * Created 2007/07/08
 * Copyright (C) 2003-2007  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of limyweb-lrd.
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

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.limy.common.util.Base64Utils;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.config.ServletContextSingleton;

/**
 * Webwork関連のユーティリティクラスです。
 * @author Naoki Iwami
 */
public final class WebworkUtils {
    
    /** コンテキストルートURL */
    private static String contextRootUrl;
    
    /**
     * private constructor
     */
    private WebworkUtils() { }
    
    /**
     * アプリケーション内ファイルのローカルパスを返します。
     * @param path appRootからの相対パス
     * @return 実際のファイル
     */
    public static File getFile(String path) {
        ServletContext servletContext = ServletContextSingleton.getInstance()
                .getServletContext();
        if (servletContext == null) {
            // JUnitテスト用
            return new File(path);
        }
        return new File(servletContext.getRealPath(path));
    }

    /**
     * コンテキストルートURLを返します。
     * @return コンテキストルートURL (例)http://localhost:8080/limyweb-lrd/
     */
    public static String getContextRootUrl() {
        
        if (contextRootUrl == null) {
            // Webworkから取得
            HttpServletRequest request = ServletActionContext.getRequest();
            if (request == null) {
                // Dwr経由の場合
                request = WebContextFactory.get().getHttpServletRequest();
            }
            
            String serverUrl = request.getRequestURL().toString();
            int pos = 0;
            for (int i = 0; i < 4; i++) {
                pos = serverUrl.indexOf('/', pos) + 1;
            }
            contextRootUrl = serverUrl.substring(0, pos);
        }
        return contextRootUrl;
    }
    
    public static HttpSession getSession() {
        return ServletActionContext.getRequest().getSession();
    }

    public static String getRemoteAddr() {
        return ServletActionContext.getRequest().getRemoteAddr();
    }

    public static String getReferer() {
        return ServletActionContext.getRequest().getHeader("referer");
    }

    public static String getUserAgent() {
        return ServletActionContext.getRequest().getHeader("user-agent");
    }

    /**
     * クッキーを取得します。
     * @param cookieName クッキー名
     * @return 取得したクッキー文字列（またはnull）
     */
    public static String getCookieString(String cookieName) {
        String cookieString = null;
        Cookie[] cookies = ServletActionContext.getRequest().getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieName)) {
                    cookieString = Base64Utils.decodeEuc(cookies[i].getValue());
                    break;
                }
            }
        }
        return cookieString;
    }
    
    /**
     * クッキーを追加します。
     * <p>
     * 有効期限は1年です。
     * </p>
     * @param cookieName クッキー名
     * @param cookieValue クッキー文字列
     */
    public static void addCookie(String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(
                    cookieName, Base64Utils.encodeEucCookie(cookieValue));
        cookie.setVersion(0);
        
        cookie.setMaxAge(3600 * 24 * 365); // 有効期限は1年
        ServletActionContext.getResponse().addCookie(cookie);
    }
    
    public static BrowserType getBrowserType() {
        return WebUtils.getBrowserType(ServletActionContext.getRequest());
    }

}
