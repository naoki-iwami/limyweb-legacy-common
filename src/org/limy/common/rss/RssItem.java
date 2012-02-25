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

import java.util.Date;
import java.util.Locale;

import org.limy.common.util.Formatters;
import org.limy.common.util.HtmlUtils;

/**
 * RSSアイテムを表すBeanクラスです。
 * @author Naoki Iwami
 */
public class RssItem {

    // ------------------------ Fields

    /**
     * リンク先
     */
    private final String link;

    /**
     * タイトル
     */
    private final String title;

    /**
     * 詳細
     */
    private String description;

    /**
     * 日付
     */
    private final Date date;

    // ------------------------ Constructors

    /**
     * RssItemインスタンスを構築します。
     * @param link リンク先
     * @param title タイトル
     * @param date 日付
     */
    public RssItem(String link, String title, Date date) {
        this.link = link;
        this.title = title;
        this.date = (Date)date.clone();
    }

    /**
     * RssItemインスタンスを構築します。
     * @param link リンク先
     * @param title タイトル
     * @param description 詳細
     * @param date 日付
     */
    public RssItem(String link, String title, String description, Date date) {
        this.link = link;
        this.title = title;
        this.date = (Date)date.clone();
        if (description != null) {
            if (description.length() > 200) {
                this.description = description.substring(0, 200) + " ...";
            } else {
                this.description = description;
            }
        }
            
    }

    // ------------------------ Public Methods

    public String getW3cDate() {
        return Formatters.dateFormat(Formatters.W3C, date);
    }

    public String getRfcDate() {
        return Formatters.dateFormat(Formatters.RFC822, date, Locale.US);
    }

    // ------------------------ Getter/Setter Methods

    /**
     * リンク先を取得します。
     * @return リンク先
     */
    public String getLink() {
        return HtmlUtils.quoteHtml(link);
    }

    /**
     * タイトルを取得します。
     * @return タイトル
     */
    public String getTitle() {
        return title; // CDATA内に入れるのでクオートは必要無い
//        return HtmlUtils.quoteHtml(title);
    }

    /**
     * 日付を取得します。
     * @return 日付
     */
    public Date getDate() {
        return (Date)date.clone();
    }

    /**
     * 詳細を取得します。
     * @return 詳細
     */
    public String getDescription() {
        return description;
    }

}
