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
package org.limy.common.svn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.io.diff.SVNDeltaGenerator;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNInfo;
import org.tmatesoft.svn.core.wc.SVNLogClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.tmatesoft.svn.core.wc.admin.SVNLookClient;

import com.sun.corba.se.impl.oa.toa.TOA;

/**
 * Subversion関連のユーティリティクラスです。
 * @author Naoki Iwami
 */
public final class SvnUtils {
    
    // ------------------------ Constants

    /**
     * logger
     */
    private static final Log LOG = LogFactory.getLog(SvnUtils.class);
    
    /**
     * HEADリビジョン
     */
    private static final long REVISION_HEAD = -1;

    // ------------------------ Constructors

    static {
        SVNRepositoryFactoryImpl.setup();
    }
    
    /**
     * private constructor
     */
    private SvnUtils() { }
    
    // ------------------------ Public Methods
    
    /**
     * SVNリポジトリにファイルを追加します。
     * @param repository SVNリポジトリ
     * @param filePath ファイルパス
     * @param data ファイル内容（nullの場合ディレクトリと見なす）
     * @param comment コミットコメント
     * @return コミット情報
     * @throws SVNException SVN例外
     * @throws  
     */
    public static SVNCommitInfo addFile(
            SVNRepository repository,
            String filePath,
            byte[] data,
            String comment) throws SVNException {

        LOG.debug("Add SVN File ：" + repository.getLocation().getPath() + filePath);
        
        Collection<String> addDirs = new ArrayList<String>();
        
        appendFiles(repository, filePath, data != null, addDirs);
        
        ISVNEditor editor = repository.getCommitEditor(comment, null);
        
        editor.openRoot(REVISION_HEAD);
        addDirs(editor, addDirs);

        addFile(editor, filePath, data);

        for (int i = 0; i < addDirs.size(); i++) {
            editor.closeDir();
        }
        editor.closeDir();

        return editor.closeEdit();
        
    }
    
    public static SVNCommitInfo addMultiFile(
            SVNRepository repository,
            CommitFileInfo[] infos,
            String comment) throws SVNException {
        
        LOG.debug("Add SVN Multi File");
        
        Collection<String> addDirs = new ArrayList<String>();
        
        for (CommitFileInfo info : infos) {
            appendFiles(repository, info.getPath(),
                    info.getContents() != null, addDirs);
        }
        
        ISVNEditor editor = repository.getCommitEditor(comment, null);
        
        editor.openRoot(REVISION_HEAD);
        addDirs(editor, addDirs);

        for (CommitFileInfo info : infos) {
            addFile(editor, info.getPath(), info.getContents());
        }
        
        for (int i = 0; i < addDirs.size(); i++) {
            editor.closeDir();
        }
        editor.closeDir();

        return editor.closeEdit();

    }

    /**
     * SVNリポジトリにディレクトリを追加します。
     * @param repository SVNリポジトリ
     * @param filePath ディレクトリパス
     * @param comment コミットコメント
     * @return コミット情報
     * @throws SVNException SVN例外
     */
    public static SVNCommitInfo addDir(
            SVNRepository repository,
            String filePath,
            String comment) throws SVNException {
        
        return addFile(repository, filePath, null, comment);
    }
    
    public static SVNCommitInfo modifyFile(SVNRepository repository, String filePath,
            byte[] data, String comment) throws SVNException {
        
        LOG.debug("Modify SVN File  ：" + repository.getLocation().getPath() + filePath);
        
        ISVNEditor editor = repository.getCommitEditor(comment, null);
        editor.openRoot(REVISION_HEAD);
        editor.openFile(filePath, REVISION_HEAD);
        editor.applyTextDelta(filePath, null);
              
        SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
        String checksum = deltaGenerator.sendDelta(filePath,
                new ByteArrayInputStream(data), editor, true);
        
        editor.closeFile(filePath, checksum);        
        editor.closeDir();
        
        return editor.closeEdit();

    }

    public static SVNCommitInfo modifyMultiFile(
            SVNRepository repository,
            CommitFileInfo[] infos,
            String comment) throws SVNException {
        
        LOG.debug("Modify SVN Multi file");
        
        ISVNEditor editor = repository.getCommitEditor(comment, null);
        editor.openRoot(REVISION_HEAD);
        
        for (CommitFileInfo info : infos) {
            String filePath = info.getPath();
            editor.openFile(filePath, REVISION_HEAD);
            editor.applyTextDelta(filePath, null);
                  
            SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
            String checksum = deltaGenerator.sendDelta(filePath,
                    new ByteArrayInputStream(info.getContents()), editor, true);
            
            editor.closeFile(filePath, checksum);        
        }
        editor.closeDir();
        
        return editor.closeEdit();

    }

    /**
     * SVNリポジトリを取得します。
     * @param svnUrl リポジトリURL
     * @param userName 認証ユーザ名
     * @param password 認証パスワード
     * @return SVNリポジトリ
     * @throws SVNException SVN例外
     */
    public static SVNRepository getRepository(
            String svnUrl, String userName, String password) throws SVNException {
        
        SVNURL url = SVNURL.parseURIEncoded(svnUrl);
        SVNRepository repository = SVNRepositoryFactory.create(url);

        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(
                userName, password);
        repository.setAuthenticationManager(authManager);
        
        return repository;
    }
    
    /**
     * SVNリポジトリからファイルの情報を取得します。
     * @param svnUrl ファイルURL
     * @param userName 認証ユーザ名
     * @param password 認証パスワード
     * @return SVN情報
     * @throws SVNException SVN例外
     */
    public static SVNInfo getSvnInfo(
            String svnUrl, String userName, String password)
            throws SVNException {
        SVNWCClient wcClient = SVNClientManager.newInstance(
                SVNWCUtil.createDefaultOptions(true),
                userName, password).getWCClient();
        
        SVNInfoHandler handler = new SVNInfoHandler();
        wcClient.doInfo(
                SVNURL.parseURIEncoded(svnUrl),
                SVNRevision.UNDEFINED,
                SVNRevision.HEAD, true,
                handler);
        return handler.getInfo();
    }
    
    /**
     * SVNリポジトリからファイルの内容を取得します。
     * @param svnUrl ファイルURL
     * @param userName 認証ユーザ名
     * @param password 認証パスワード
     * @return ファイル内容
     * @throws SVNException SVN例外
     */
    public static byte[] getRepositoryContents(
            String svnUrl, String userName, String password) throws SVNException {
        
        SVNWCClient wcClient = SVNClientManager.newInstance(
                SVNWCUtil.createDefaultOptions(true),
                userName,
                password).getWCClient();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        wcClient.doGetFileContents(
                SVNURL.parseURIEncoded(svnUrl),
                SVNRevision.UNDEFINED,
                SVNRevision.HEAD, true, out);
        return out.toByteArray();
    }

    /**
     * SVNリポジトリから複数ファイルの内容を取得します。
     * @param userName 認証ユーザ名
     * @param password 認証パスワード
     * @param baseSvnUrl リポジトリルートURL
     * @param svnUrls ファイルURL一覧
     * @param charset 格納されたファイルの文字セット
     * @return ファイル内容
     * @throws SVNException SVN例外
     */
    public static CommitFileInfo[] getMultiRepositoryContents(
            String userName, String password, String baseSvnUrl,
            String[] svnUrls, String charset
            ) throws SVNException {
        
        SVNWCClient wcClient = SVNClientManager.newInstance(
                SVNWCUtil.createDefaultOptions(true),
                userName,
                password).getWCClient();

        CommitFileInfo[] results = new CommitFileInfo[svnUrls.length];
        for (int i = 0; i < svnUrls.length; i++) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            wcClient.doGetFileContents(
                    SVNURL.parseURIEncoded(baseSvnUrl + svnUrls[i]),
                    SVNRevision.UNDEFINED,
                    SVNRevision.HEAD, true, out);
            results[i] = new CommitFileInfo(svnUrls[i], out.toByteArray(), charset);
            
            SVNInfo info = wcClient.doInfo(SVNURL.parseURIEncoded(baseSvnUrl + svnUrls[i]),
                    SVNRevision.UNDEFINED, SVNRevision.HEAD);
            results[i].setCommittedDate(info.getCommittedDate());
        }
        return results;
    }
    
    public static void main(String[] args) throws SVNException {
        CommitFileInfo[] infos = getRecentCommitedEntries("naoki", "svnarzus",
                "svn://192.168.1.5/new_all/trunk/www/src/program", 20);
        System.out.println(infos);
        
    }
    
    public static CommitFileInfo[] getRecentCommitedEntries(
            String userName, String password, final String baseSvnUrl,
            int count) throws SVNException {
        
        DefaultSVNOptions myOptions = new DefaultSVNOptions();
        SVNClientManager clientManager = SVNClientManager.newInstance(myOptions,
                userName, password);
        SVNLogClient logClient = clientManager.getLogClient();
        final SVNWCClient wcClient = clientManager.getWCClient();

        final List<CommitFileInfo> infos = new ArrayList<CommitFileInfo>();
        
        int pos = baseSvnUrl.indexOf("://");
        pos = baseSvnUrl.indexOf('/', pos + 3);
        pos = baseSvnUrl.indexOf('/', pos + 1);
        final int prefixLen = baseSvnUrl.length() - pos;
        final String baseUrl = baseSvnUrl.substring(pos);

        ISVNLogEntryHandler handler = new ISVNLogEntryHandler() {
            public void handleLogEntry(SVNLogEntry logEntry) throws SVNException {
//                System.out.println(logEntry);
                Map map = logEntry.getChangedPaths();
                
                Date date = logEntry.getDate();
                for (Object entry : map.entrySet()) {
                    String path = ((Entry)entry).getKey().toString();
//                    System.out.println(path);
                    if (!path.startsWith(baseUrl)) {
                        continue;
                    }
                    path = path.substring(prefixLen);
                    if (path.startsWith("/.template/") || (!path.endsWith(".lrd"))) {
                        continue;
                    }
                    CommitFileInfo info = new CommitFileInfo(path);
                    info.setCommittedDate(date);
                    
                    infos.add(info);
                }
            }
        };
        Date date = new Date(new Date().getTime() - 1000L * 60 * 60 * 24 * 180); // 半年前
        logClient.doLog(SVNURL.parseURIEncoded(baseSvnUrl), new String[0],
                SVNRevision.HEAD, SVNRevision.create(date), SVNRevision.HEAD,
                true, true, 1000, handler);
        
        for (CommitFileInfo info : infos) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            String url = baseSvnUrl + info.getPath();
            if (!url.endsWith(".lrd")) {
                continue;
            }
            wcClient.doGetFileContents(
                    SVNURL.parseURIEncoded(url),
                    SVNRevision.UNDEFINED,
                    SVNRevision.HEAD, true, out);
            try {
                String lines = out.toString("EUC-JP");
                String[] split = lines.split("\\n");
                for (String line : split) {
                    if (line.startsWith("date=")) {
                        date = new SimpleDateFormat("yyyy/MM/dd").parse(line.substring(5));
                        info.setCommittedDate(date);
                    }
                    if (line.startsWith("=begin")) {
                        break;
                    }
                }
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        
        Collections.sort(infos, new Comparator<CommitFileInfo>() {
            public int compare(CommitFileInfo o1, CommitFileInfo o2) {
                return o1.getCommittedDate().compareTo(o2.getCommittedDate());
            }
        });
        
        Set<String> paths = new HashSet<String>();
        Collection<CommitFileInfo> results = new ArrayList<CommitFileInfo>();
        for (int i = infos.size() - 1; i >= 0; i--) {
            CommitFileInfo info = infos.get(i);
            if (paths.contains(info.getPath())) {
                continue;
            }
            
            results.add(info);
            paths.add(info.getPath());
            if (results.size() == count) {
                break;
            }
        }
        
        return results.toArray(new CommitFileInfo[results.size()]);
    }

    // ------------------------ Private Methods

    /**
     * リポジトリにファイルまたはディレクトリを追加します。
     * @param editor SVNEditor
     * @param filePath 今回追加するファイルパス
     * @param data 今回追加するファイルの内容（ディレクトリの場合null）
     * @param addFiles リポジトリに追加するファイル一覧
     * @throws SVNException SVN例外
     */
    private static void addFile(ISVNEditor editor, String filePath,
            byte[] data) throws SVNException {
                
        if (data != null) {
            editor.addFile(filePath, null, REVISION_HEAD);
            editor.applyTextDelta(filePath, null);
            SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
            String checksum = deltaGenerator.sendDelta(filePath,
                    new ByteArrayInputStream(data), editor, true);
            editor.closeFile(filePath, checksum);        
        }
        
    }

    /**
     * SVNリポジトリにディレクトリ情報を追加します。
     * @param editor SVNEditor
     * @param addDirs 追加するディレクトリ一覧
     * @throws SVNException SVN例外
     */
    private static void addDirs(ISVNEditor editor, Collection<String> addDirs)
            throws SVNException {
        
        for (String dir : addDirs) {
            editor.addDir(dir, null, REVISION_HEAD);
        }
//        for (String file : addFiles) {
//            editor.addFile(file, null, REVISION_HEAD);
//        }
    }

    private static void appendFiles(SVNRepository repository, String filePath,
            boolean isFile,
            Collection<String> addDirs)
            throws SVNException {
        
        String[] pathNames = filePath.split("/");
        StringBuilder buff = new StringBuilder();
        
        boolean isAddFile = false;
        for (String pathName : pathNames) {
            if (buff.length() > 0) {
                buff.append('/');
            }
            buff.append(pathName);
            String targetPath = buff.toString();
            SVNNodeKind kind = repository.checkPath(targetPath, REVISION_HEAD);
            if (kind == SVNNodeKind.NONE) {
                if (targetPath.equals(filePath) && isFile) {
                    isAddFile = true;
//                    addFiles.add(filePath);
                } else {
                    if (!addDirs.contains(targetPath)) {
                        addDirs.add(targetPath);
                    }
                }
            }
        }
        
        if (isFile && !isAddFile) {
//            if (isFile && addFiles.isEmpty()) {
            // 作成しようとしたファイルが既に存在している場合
            throw new SVNException(SVNErrorMessage.create(SVNErrorCode.ENTRY_EXISTS));
        }
    }
    
}
