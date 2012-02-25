/*
 * Created 2006/04/29
 * Copyright (C) 2003-2006  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of limy-portal.
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
package org.limy.common.ftp;

/**
 * FTP接続情報を表すクラスです。
 * @author Naoki Iwami
 */
public class FtpInfo {
    
    // ------------------------ Fields

    /**
     * FTPサーバアドレス
     */
    private String serverAddress;
    
    /**
     * ユーザ名
     */
    private String userName;
    
    /**
     * パスワード
     */
    private String password;
    
    /**
     * パス
     */
    private String path;
    
    // ------------------------ Getter/Setter Methods

    /**
     * FTPサーバアドレスを取得します。
     * @return FTPサーバアドレス
     */
    public String getServerAddress() {
        return serverAddress;
    }

    /**
     * FTPサーバアドレスを設定します。
     * @param serverAddress FTPサーバアドレス
     */
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    /**
     * ユーザ名を取得します。
     * @return ユーザ名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ユーザ名を設定します。
     * @param userName ユーザ名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * パスワードを取得します。
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定します。
     * @param password パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * パスを取得します。
     * @return パス
     */
    public String getPath() {
        return path;
    }

    /**
     * パスを設定します。
     * @param path パス
     */
    public void setPath(String path) {
        this.path = path;
    }
    
    
    

}
