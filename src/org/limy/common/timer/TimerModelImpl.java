/*
 * Created 2007/06/09
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
package org.limy.common.timer;

import java.util.Date;
import java.util.Timer;

/**
 * タイマー関連モデルの実装クラスです。
 * @author Naoki Iwami
 */
public class TimerModelImpl implements TimerModel {

    /** タイマー */
    private Timer timer = new Timer(true);
    
    // ------------------------ Implement Methods

    /**
     * 全てのタスクをキャンセルします。
     */
    public void cancel() {
        timer.cancel();
    }

    /**
     * 指定時間および一定の間隔で実行するタスクを定義します。
     * @param task タスク
     * @param firstTime 最初に実行する時刻
     * @param period 以後、実行する間隔
     */
    public void scheduleAtFixedRate(LimyTimerTask task, Date firstTime, long period) {
        timer.scheduleAtFixedRate(task.getTimerTask(), firstTime, period);
    }

    /**
     * 一定の間隔で実行するタスクを定義します。
     * @param task タスク
     * @param period 以後、実行する間隔
     */
    public void schedule(LimyTimerTask task, long period) {
        timer.schedule(task.getTimerTask(), period);
    }

}
