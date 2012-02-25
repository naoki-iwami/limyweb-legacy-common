/*
 * Created 2007/08/08
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

import java.util.Collection;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;

/**
 * Reverse Ajaxを扱うクラスです。
 * @author Naoki Iwami
 */
public class LimyWebSession {

    /** Reverse Ajax用セッション一覧 */
    private final Collection<? extends ScriptSession> sessions;

    public LimyWebSession(Collection<? extends ScriptSession> sessions) {
        super();
        this.sessions = sessions;
    }

    /**
     * ブラウザにJavaScriptを送信（Reverse Ajax）します。
     * <p>
     * 表示中のページが Reverse Ajax に対応している必要があります。
     * </p>
     * @param javaScript JavaScript文
     */
    public void sendJavaScript(String javaScript) {
        ScriptBuffer script = new ScriptBuffer(javaScript);
        for (ScriptSession session : sessions) {
//            ((RealScriptSession)session).addScriptConduit(conduit)
            session.addScript(script);
        }
    }

}
