/*
 * Created 2006/04/30
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
package org.limy.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.limy.common.ftp.FtpInfo;

/**
 * FTP関連ユーティリティクラスです。
 * @author Naoki Iwami
 */
public final class FtpUtils {

    /** log */
    private static final Log LOG = LogFactory.getLog(FtpUtils.class);
    
    /**
     * private constructor
     */
    private FtpUtils() { }
    
    /**
     * FTPに接続します。
     * <p>
     * 操作が完了したら、disconnectメソッドを呼び出して下さい。
     * </p>
     * @param client FTPクライアント
     * @param info FTP接続情報
     * @throws IOException I/O例外
     */
    public static void connectFtp(FTPClient client, FtpInfo info)
            throws IOException {
        
        client.connect(info.getServerAddress());
        if (!client.login(info.getUserName(), info.getPassword())) {
            throw new IOException("ログインに失敗しました。");
        }
        if (info.getPath() != null) {
            client.cwd(info.getPath());
        }
    }
    
    /**
     * FTP接続を開放します。
     * @param client FTPクライアント
     * @throws IOException I/O例外
     */
    public static void disconnect(FTPClient client) throws IOException {
        client.disconnect();
    }
    
    /**
     * ファイルをFTPでアップロードします。
     * @param client FTPクライアント
     * @param rootPath ルートディレクトリ
     * @param relativePath ルートディレクトリからの相対パス
     * @param contents アップロード内容
     * @return アップロードに成功したら真
     * @throws IOException I/O例外
     */
    public static boolean uploadFileFtp(FTPClient client,
            String rootPath, String relativePath, InputStream contents)
            throws IOException {

        int cwd = client.cwd(rootPath);
        if (cwd == FTPReply.FILE_UNAVAILABLE) {
            // ルートパスが存在しなかった場合
            if (rootPath.startsWith("/")) {
                mkdirsAbsolute(client, rootPath);
            } else {
                mkdirs(client, rootPath);
            }
        }
        
        LOG.debug("uploadFileFtp : " + rootPath + " ->" + relativePath);
        String targetDir = UrlUtils.getParent(relativePath);
        if (targetDir.startsWith("/")) {
            mkdirsAbsolute(client, targetDir);
        } else {
            mkdirs(client, targetDir);
        }
        client.cwd(rootPath);
        client.setFileType(FTP.BINARY_FILE_TYPE);
        client.site("chmod 664 " + relativePath);
        return client.storeFile(relativePath, contents);
    }

    /**
     * ファイルをFTPでアップロードします。
     * @param client FTPクライアント
     * @param relativePath ルートディレクトリからの相対パス
     * @param contents アップロード内容
     * @return アップロードに成功したら真
     * @throws IOException I/O例外
     */
    public static boolean uploadFileFtp(FTPClient client,
            String relativePath, InputStream contents)
            throws IOException {

        return uploadFileFtp(client, "/", relativePath, contents);
    }
    
    /**
     * ファイルを削除します。
     * @param client FTPクライアント
     * @param relativePath ルートディレクトリからの相対パス
     * @return 削除に成功したら真
     * @throws IOException I/O例外
     */
    public static boolean deleteFile(FTPClient client, String relativePath)
            throws IOException {
        
        return client.deleteFile(relativePath);
    }

    /**
     * ディレクトリを削除します。
     * @param client FTPクライアント
     * @param relativePath ルートディレクトリからの相対パス
     * @return 削除に成功したら真
     * @throws IOException I/O例外
     */
    public static boolean deleteDir(FTPClient client, String relativePath)
            throws IOException {
        
        return client.removeDirectory(relativePath);
    }

    // ------------------------ Private Methods
 
    /**
     * ディレクトリを（必要ならば）作成します。
     * @param client FTPクライアント
     * @param remotePath カレントディレクトリからの相対パス
     * @throws IOException I/O例外
     */
    private static void mkdirs(FTPClient client, String remotePath) throws IOException {
        if (client.cwd(remotePath) == FTPReply.FILE_UNAVAILABLE) {
            LOG.debug("Mkdir(ftp) : " + remotePath);
            
            StringTokenizer tokenizer = new StringTokenizer(remotePath, "/");
            StringBuilder subDir = new StringBuilder();
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                subDir.append(token);
                client.mkd(subDir.toString());
                subDir.append("/");
            }
        }
    }

    /**
     * ディレクトリを（必要ならば）作成します。
     * @param client FTPクライアント
     * @param absolutePath 絶対パス
     * @throws IOException I/O例外
     */
    private static void mkdirsAbsolute(FTPClient client, String absolutePath) throws IOException {
        String baseDir = absolutePath;
        while (client.cwd(baseDir) == FTPReply.FILE_UNAVAILABLE) {
            if (baseDir.lastIndexOf('/') < 0) {
                break;
            }
            baseDir = baseDir.substring(0, baseDir.lastIndexOf('/'));
        }
        if (baseDir.length() == absolutePath.length()) {
            return;
        }
        int pos = absolutePath.indexOf('/', baseDir.length() + 1);
        client.mkd(absolutePath.substring(0, pos));
        mkdirsAbsolute(client, absolutePath);
    }

}
