/*
 * Created 2007/08/16
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
package org.limy.common.web;

import java.util.Collection;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

/**
 * DWR関連のWebユーティリティクラスです。
 * @author Naoki Iwami
 */
public final class WebDwrUtils {
    
    /**
     * private constructor
     */
    private WebDwrUtils() { }

    /**
     * Reverse Ajax用のWebセッションを返します。
     * @return Webセッション
     */
    public static LimyWebSession createWebSession() {
        WebContext wctx = WebContextFactory.get();
        if (wctx == null) {
            return null; // Web経由以外から呼び出したとき
        }
        String currentPage = wctx.getCurrentPage();
        Collection<? extends ScriptSession> sessions = wctx.getScriptSessionsByPage(
                currentPage);
        return new LimyWebSession(sessions);
    }

}
