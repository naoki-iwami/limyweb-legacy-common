/*
 * Created 2005/12/30
 * Copyright (C) 2003-2007  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of limyweb-common.
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
package org.limy.common.resource.bean;

import java.sql.Time;
import java.util.Date;

/**
 * アクセスログを表すBeanクラスです。
 * @author Naoki Iwami
 */
public class AccessLog  {

    // ------------------------ Fields

    /** カテゴリ番号 */
    private short categoryId;

    /** プライマリキー */
    private int id;

    /** 対象日付 */
    private Date targetDate;

    /** 対象日付 */
    private Time targetTime;

    /** IPアドレス */
    private String ipAddress;

    /** ターゲットURL */
    private String targetUrl;

    /** Referer文字列 */
    private String referer;

    /** User-Agent */
    private String userAgent;

    /** 検索文字列 */
    private String searchString;

    // ------------------------ Getter/Setter Methods

    /**
     * カテゴリ番号を取得します。
     * @return カテゴリ番号
     */
    public short getCategoryId() {
        return categoryId;
    }

    /**
     * カテゴリ番号を設定します。
     * @param categoryId カテゴリ番号
     */
    public void setCategoryId(short categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * プライマリキーを取得します。
     * @return プライマリキー
     */
    public int getId() {
        return id;
    }

    /**
     * プライマリキーを設定します。
     * @param id プライマリキー
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 対象日付を取得します。
     * @return 対象日付
     */
    public Date getTargetDate() {
        return (Date)targetDate.clone();
    }

    /**
     * 対象日付を設定します。
     * @param targetDate 対象日付
     */
    public void setTargetDate(Date targetDate) {
        this.targetDate = (Date)targetDate.clone();
    }

    /**
     * 対象日付を取得します。
     * @return 対象日付
     */
    public Time getTargetTime() {
        return targetTime;
    }

    /**
     * 対象日付を設定します。
     * @param targetTime 対象日付
     */
    public void setTargetTime(Time targetTime) {
        this.targetTime = targetTime;
    }

    /**
     * IPアドレスを取得します。
     * @return IPアドレス
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * IPアドレスを設定します。
     * @param ipAddress IPアドレス
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * ターゲットURLを取得します。
     * @return ターゲットURL
     */
    public String getTargetUrl() {
        return targetUrl;
    }

    /**
     * ターゲットURLを設定します。
     * @param targetUrl ターゲットURL
     */
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    /**
     * Referer文字列を取得します。
     * @return Referer文字列
     */
    public String getReferer() {
        return referer;
    }

    /**
     * Referer文字列を設定します。
     * @param referer Referer文字列
     */
    public void setReferer(String referer) {
        this.referer = referer;
    }

    /**
     * User-Agentを取得します。
     * @return User-Agent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * User-Agentを設定します。
     * @param userAgent User-Agent
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * 検索文字列を取得します。
     * @return 検索文字列
     */
    public String getSearchString() {
        return searchString;
    }

    /**
     * 検索文字列を設定します。
     * @param searchString 検索文字列
     */
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

}
