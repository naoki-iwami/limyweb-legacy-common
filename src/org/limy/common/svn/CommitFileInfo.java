/*
 * Created 2006/09/02
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
package org.limy.common.svn;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * コミットするファイルの情報を格納します。
 * @author Naoki Iwami
 */
public class CommitFileInfo {
    
    /**
     * リポジトリルートからの相対パス
     */
    private String path;
    
    /**
     * コミットするファイル内容
     */
    private byte[] contents;
    
    /** 文字セット */
    private String charset;
    
    /** コミット時刻 */
    private Date committedDate;
    
    // ------------------------ Constructors
    
    /**
     * CommitFileInfoインスタンスを構築します。
     * @param path リポジトリルートからの相対パス
     * @param contents コミットするファイル内容
     * @param charset ファイル保存時の文字セット
     */
    public CommitFileInfo(String path, byte[] contents, String charset) {
        super();
        this.path = path;
        this.contents = new byte[contents.length];
        System.arraycopy(contents, 0, this.contents, 0, contents.length);
        this.charset = charset;
    }

    /**
     * CommitFileInfoインスタンスを構築します（ディレクトリ用）。
     * @param path リポジトリルートからの相対パス
     */
    public CommitFileInfo(String path) {
        super();
        this.path = path;
    }

    // ------------------------ Public Methods

    public String getContentStr() {
        if (charset == null) {
            return new String(contents);
        }
        try {
            return new String(contents, charset);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    
    // ------------------------ Getter/Setter Methods

    /**
     * リポジトリルートからの相対パスを取得します。
     * @return リポジトリルートからの相対パス
     */
    public String getPath() {
        return path;
    }

    /**
     * コミットするファイル内容を取得します。
     * @return コミットするファイル内容
     */
    public byte[] getContents() {
        byte[] results = new byte[contents.length];
        System.arraycopy(contents, 0, results, 0, contents.length);
        return results;
    }

    /**
     * コミットするファイル内容を設定します。
     * @param contents コミットするファイル内容
     */
    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    /**
     * 文字セットを設定します。
     * @param charset 文字セット
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * コミット時刻を取得します。
     * @return コミット時刻
     */
    public Date getCommittedDate() {
        return committedDate;
    }

    /**
     * コミット時刻を設定します。
     * @param committedDate コミット時刻
     */
    public void setCommittedDate(Date committedDate) {
        this.committedDate = committedDate;
    }
    
}
