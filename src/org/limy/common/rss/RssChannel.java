/*
 * Created 2005/07/16
 * Copyright (C) 2003-2005  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of limy-xml.
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
package org.limy.common.rss;


/**
 * RSSチャネルを表すBeanクラスです。
 * @author Naoki Iwami
 */
public class RssChannel {
    
    // ------------------------ Fields
    
    /**
     * RSSのURL
     */
    private String rdfAbout;

    /**
     * タイトル
     */
    private String title;
    
    /**
     * リンク先
     */
    private String link;
    
    /**
     * 簡易説明文
     */
    private String description;
    
    /**
     * 言語
     */
    private String language;
    
    // ------------------------ Getter/Setter Methods

    /**
     * RSSのURLを取得します。
     * @return RSSのURL
     */
    public String getRdfAbout() {
        return rdfAbout;
    }

    /**
     * RSSのURLを設定します。
     * @param rdfAbout RSSのURL
     */
    public void setRdfAbout(String rdfAbout) {
        this.rdfAbout = rdfAbout;
    }

    /**
     * タイトルを取得します。
     * @return タイトル
     */
    public String getTitle() {
        return title;
    }

    /**
     * タイトルを設定します。
     * @param title タイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * リンク先を取得します。
     * @return リンク先
     */
    public String getLink() {
        return link;
    }

    /**
     * リンク先を設定します。
     * @param link リンク先
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 簡易説明文を取得します。
     * @return 簡易説明文
     */
    public String getDescription() {
        return description;
    }

    /**
     * 簡易説明文を設定します。
     * @param description 簡易説明文
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 言語を取得します。
     * @return 言語
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 言語を設定します。
     * @param language 言語
     */
    public void setLanguage(String language) {
        this.language = language;
    }
    
}
