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

import java.util.ArrayList;
import java.util.List;

/**
 * RSSを表すBeanクラスです。
 * @author Naoki Iwami
 */
public class RssBean {
    
    // ------------------------ Fields

    /**
     * チャネル
     */
    private final RssChannel channel;
    
    /**
     * アイテム一覧
     */
    private List<RssItem> items = new ArrayList<RssItem>();
    
    // ------------------------ Constructors
    
    /**
     * RssBeanインスタンスを構築します。
     */
    public RssBean() {
        channel = new RssChannel();
    }
    
    // ------------------------ Public Methods

    /**
     * RSSアイテムを追加します。
     * @param item RSSアイテム
     */
    public void addItem(RssItem item) {
        this.items.add(item);
    }
    
    public RssItem getLatestItem() {
        if (items.isEmpty()) {
            return null;
        }
        return items.get(0);
    }
    
    // ------------------------ Getter/Setter Methods

    /**
     * チャネルを取得します。
     * @return チャネル
     */
    public RssChannel getChannel() {
        return channel;
    }

    /**
     * アイテム一覧を取得します。
     * @return アイテム一覧
     */
    public List<RssItem> getItems() {
        return items;
    }

    /**
     * アイテム一覧を設定します。
     * @param items アイテム一覧
     */
    public void setItems(List<RssItem> items) {
        this.items = items;
    }

}
