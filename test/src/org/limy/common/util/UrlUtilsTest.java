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

import junit.framework.TestCase;

import org.junit.Test;

/**
 *
 * @author Naoki Iwami
 */
public class UrlUtilsTest extends TestCase {

    @Test
    public void testGetRelativeUrl() {
        
        File basePath = new File("/tmp");
        assertEquals("file.txt",
                UrlUtils.getRelativeUrl(basePath, new File(basePath, "file.txt")));
        assertEquals("testdir",
                UrlUtils.getRelativeUrl(basePath, new File(basePath, "testdir")));
        assertEquals("testdir/subfile",
                UrlUtils.getRelativeUrl(basePath, new File(basePath, "testdir/subfile")));
        
        assertEquals("../checkstyle/block_checks.html",
                UrlUtils.getRelativeUrl("/program/java/coding",
                        "/program/java/checkstyle/block_checks.html"));
        
        assertEquals("../../etc1/checkstyle/block_checks.html",
                UrlUtils.getRelativeUrl("/program/java/coding",
                        "/program/etc1/checkstyle/block_checks.html"));

        assertEquals("../sample.html",
                UrlUtils.getRelativeUrl("/program/java/coding",
                "/program/java/sample.html"));

        assertEquals("../plugin_memo.html",
                UrlUtils.getRelativeUrl("svn://192.168.1.5/all/trunk/www/html/src/program/eclipse/plugin",
                        "svn://192.168.1.5/all/trunk/www/html/src/program/eclipse/plugin_memo.html"));

        
    }

    @Test
    public void testGetRelativeUrlString() {
        assertEquals("file.txt",
                UrlUtils.getRelativeUrl("sub", "sub/file.txt"));
        assertEquals("file.txt",
                UrlUtils.getRelativeUrl("", "file.txt"));
        
        assertEquals("sub2/file.txt",
                UrlUtils.getRelativeUrl("sub", "sub/sub2/file.txt"));
        assertEquals("sub2/file.txt",
                UrlUtils.getRelativeUrl("", "sub2/file.txt"));

        assertEquals("../checkstyle/block_checks.html",
                UrlUtils.getRelativeUrl(
                        "program/java/coding", "program/java/checkstyle/block_checks.html"));
        assertEquals("../checkstyle/block_checks.html",
                UrlUtils.getRelativeUrl(
                        "coding", "checkstyle/block_checks.html"));

        assertEquals("../../etc1/checkstyle/block_checks.html",
                UrlUtils.getRelativeUrl(
                        "program/java/coding", "program/etc1/checkstyle/block_checks.html"));
        assertEquals("../../etc1/checkstyle/block_checks.html",
                UrlUtils.getRelativeUrl(
                        "java/coding", "etc1/checkstyle/block_checks.html"));

        assertEquals("../sample.html",
                UrlUtils.getRelativeUrl(
                        "program/java/coding", "program/java/sample.html"));
        assertEquals("../sample.html",
                UrlUtils.getRelativeUrl(
                        "coding", "sample.html"));

    }

    @Test
    public void testConcatUrl() {

        assertEquals("sample/aa", UrlUtils.concatUrl("sample", "aa"));
        assertEquals("sample/aa", UrlUtils.concatUrl("sample/", "aa"));
        assertEquals("index", UrlUtils.concatUrl("sample", "../index"));
        assertEquals("index", UrlUtils.concatUrl("sample/", "../index"));
        assertEquals("sample/index", UrlUtils.concatUrl("sample/sub", "../index"));
        assertEquals("sample", UrlUtils.concatUrl("sample", "."));
        assertEquals("sample/aa", UrlUtils.concatUrl("sample", "./aa"));

    }

    @Test
    public void testGetParent() {
        assertEquals("", UrlUtils.getParent("sample"));
        assertEquals("", UrlUtils.getParent("sample/"));
        assertEquals("sample", UrlUtils.getParent("sample/sub"));
        assertEquals("sample", UrlUtils.getParent("sample/sub/"));

    }
    
    @Test
    public void testGetFileName() {
        assertEquals("test.html", UrlUtils.getFileName("sample/test.html"));
        assertEquals("test.html", UrlUtils.getFileName("test.html"));
        assertEquals("test", UrlUtils.getFileName("sample/test"));
        assertEquals("test", UrlUtils.getFileName("test"));
    }
    
    @Test
    public void testGetBaseName() {
        assertEquals("test", UrlUtils.getBaseName("sample/test.html"));
        assertEquals("test", UrlUtils.getBaseName("test.html"));
        assertEquals("test", UrlUtils.getBaseName("sample/test"));
        assertEquals("test", UrlUtils.getBaseName("test"));
    }

}
