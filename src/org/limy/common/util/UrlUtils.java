/*
 * Created 2006/07/09
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

import java.io.File;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * URL関連のユーティリティクラスです。
 * @author Naoki Iwami
 */
public final class UrlUtils {
    
    /** logger */
    private static final Log LOG = LogFactory.getLog(UrlUtils.class);
    
    /**
     * private constructor
     */
    private UrlUtils() { }

    /**
     * 対象パスの、基準パスからの相対パスを取得します。
     * @param basePath 基準パス
     * @param targetPath 対象パス
     * @return 相対パス
     */
    public static String getRelativeUrl(File basePath, File targetPath) {
        
        String baseAbsPath = basePath.getAbsolutePath();
        String targetAbsPath = targetPath.getAbsolutePath();
        return getRelativeUrl(baseAbsPath, targetAbsPath);
    }

    /**
     * 対象パスの、基準ディレクトリからの相対パスを取得します。
     * @param orgBaseDir 基準ディレクトリ（後ろに/が無い形式）
     * @param orgTargetPath 対象パス
     * @return 相対パス
     */
    public static String getRelativeUrl(String orgBaseDir, String orgTargetPath) {
        String basePath = orgBaseDir.replace('\\', '/');
        String targetPath = orgTargetPath.replace('\\', '/');
        if (targetPath.startsWith(basePath + "/")) {
            return targetPath.substring(basePath.length() + 1);
        }
        StringBuilder result = new StringBuilder();
        
        int reverseNo = 0;
        while (basePath.length() > 0 &&  !targetPath.startsWith(basePath + "/")) {
            if (basePath.lastIndexOf('/') >= 0) {
                basePath = basePath.substring(0, basePath.lastIndexOf('/'));
            } else {
                basePath = "";
            }
            result.append("../");
            ++reverseNo;
        }
        if (basePath.length() == 0) {
            return result.toString() + targetPath;
        }
        return result.toString() + targetPath.substring(basePath.length() + 1);
    }

    /**
     * パスの親ディレクトリ名を返します。
     * @param orgPath パス
     * @return パスの親ディレクトリ名
     */
    public static String getParent(String orgPath) {
        if (orgPath.length() == 0) {
            return "";
        }
        
        String path;
        if (orgPath.charAt(orgPath.length() - 1) == '/') {
            path = orgPath.substring(0, orgPath.length() - 1);
        } else {
            path = orgPath;
        }
        int index = path.lastIndexOf('/');
        if (index >= 0) {
            return path.substring(0, index);
        }
        return "";
    }
    
    /**
     * パスのファイル名を取得します。
     * @param path パス
     * @return ファイル名
     */
    public static String getFileName(String path) {
        int index = path.lastIndexOf('/');
        if (index >= 0) {
            return path.substring(index + 1);
        }
        return path;
    }
    
    /**
     * 拡張子を除いたファイル名を取得します。
     * @param path パス
     * @return 拡張子を除いたファイル名
     */
    public static String getBaseName(String path) {
        String fileName = getFileName(path);
        int index = fileName.lastIndexOf('.');
        if (index >= 0) {
            return fileName.substring(0, index);
        }
        return fileName;
    }

    /**
     * 基準パスに相対パスを加え、その絶対パスを返します。
     * @param basePath 基準パス
     * @param url 相対パス
     * @return 絶対パス
     */
    public static String concatUrl(String basePath, String url) {
        StringTokenizer tokenizer = new StringTokenizer(url, "/");
        StringBuilder result = new StringBuilder(basePath);
        try {
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if (".".equals(token)) {
                    continue;
                }
                if ("..".equals(token)) {
                    if (result.charAt(result.length() - 1) == '/') {
                        result.setLength(result.length() - 1);
                    }
                    if (result.lastIndexOf("/") >= 0) {
                        result.setLength(result.lastIndexOf("/"));
                    } else {
                        result.setLength(0);
                    }
                    continue;
                }
                if (result.length() > 0 && result.charAt(result.length() - 1) != '/') {
                    result.append('/');
                }
                result.append(token);
            }
        } catch (StringIndexOutOfBoundsException e) {
            LOG.warn(e.getMessage(), e);
            return url;
        }
        return result.toString();
    }

}
