/*
 * Created 2006/04/26
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
package org.limy.common.repository;

/**
 * リポジトリの各種情報を保持するクラスです。
 * @author Naoki Iwami
 */
public class RepositoryInfo {
    
    /**
     * リポジトリURL
     */
    private String repositoryUrl;
    
    /**
     * 認証ユーザ名
     */
    private String authUser;
    
    /**
     * 認証パスワード
     */
    private String authPass;

    /**
     * リポジトリURLを取得します。
     * @return リポジトリURL
     */
    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    /**
     * リポジトリURLを設定します。
     * @param repositoryUrl リポジトリURL
     */
    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    /**
     * 認証ユーザ名を取得します。
     * @return 認証ユーザ名
     */
    public String getAuthUser() {
        return authUser;
    }

    /**
     * 認証ユーザ名を設定します。
     * @param authUser 認証ユーザ名
     */
    public void setAuthUser(String authUser) {
        this.authUser = authUser;
    }

    /**
     * 認証パスワードを取得します。
     * @return 認証パスワード
     */
    public String getAuthPass() {
        return authPass;
    }

    /**
     * 認証パスワードを設定します。
     * @param authPass 認証パスワード
     */
    public void setAuthPass(String authPass) {
        this.authPass = authPass;
    }

}
