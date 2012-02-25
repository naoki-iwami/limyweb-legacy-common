package org.limy.common.svn;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.io.SVNRepository;


public class SvnUtilsTest {
    
    /** svnserveパス */
    private static String SVNSERVE_EXEC;

    /** svnadminパス */
    private static String SVNADMIN_EXEC;

    /** SVNパス */
    private static String SVN_URL;
    
    private static String SVN_USER;
    
    private static String SVN_PASS;
    
    private static File SVN_DIR;

    private static Process process;
    
    @BeforeClass
    public static void setUpOnce() throws Exception {
        
        Properties prop = new Properties();
        prop.load(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("resources/test-common.properties"));
        
        SVN_DIR = new File(prop.getProperty("tmp.svn.dir"));
        SVN_USER = prop.getProperty("svn.user");
        SVN_PASS = prop.getProperty("svn.pass");
        SVNSERVE_EXEC = prop.getProperty("svnserve.exec");
        SVNADMIN_EXEC = prop.getProperty("svnadmin.exec");
        SVN_URL = prop.getProperty("svn.url");

        System.out.println("start svnserve...");
        
        ProcessBuilder builder = new ProcessBuilder(SVNSERVE_EXEC, "-d");
        builder.redirectErrorStream(true);
        process = builder.start();
        Thread.sleep(1000);
    }
    
    @AfterClass
    public static void tearDownOnce() throws Exception {
        System.out.println("stop svnserve.");
        process.destroy();
    }
    
    @Before
    public void setUp() throws Exception {
        
        FileUtils.deleteDirectory(SVN_DIR);

        System.out.println("setup...");
        Assert.assertFalse(SVN_DIR.mkdirs());
        
        ProcessBuilder builder = new ProcessBuilder(SVNADMIN_EXEC,
                "create", SVN_DIR.getAbsolutePath());
        builder.redirectErrorStream(true);
        Process process = builder.start();
        process.waitFor();
        
        FileUtils.writeStringToFile(new File(SVN_DIR, "conf/svnserve.conf"),
                "[general]\npassword-db = passwd",
                null);
        FileUtils.writeStringToFile(new File(SVN_DIR, "conf/passwd"),
                "[users]\nguest = guest",
                null);
        System.out.println("setup ok.");
    }

    @After
    public void tearDown() throws Exception {
        FileUtils.deleteDirectory(SVN_DIR);
    }

    @Test
    public void testAll() throws Exception {
        
        SVNRepository repository = SvnUtils.getRepository(SVN_URL, SVN_USER, SVN_PASS);

        SvnUtils.addFile(repository,
                "test",
                "abcde".getBytes(),
                "test comment");

        Assert.assertTrue(Arrays.equals("abcde".getBytes(), 
               SvnUtils.getRepositoryContents(SVN_URL + "test", SVN_USER, SVN_PASS)));

        SvnUtils.addFile(repository,
                "test2/tt",
                "ABCDE".getBytes(),
                "test comment");

        SvnUtils.addFile(repository,
                "sampledir/mm/44",
                "fffff".getBytes(),
                "test comment");

        Assert.assertTrue(Arrays.equals("fffff".getBytes(), 
                SvnUtils.getRepositoryContents(SVN_URL + "sampledir/mm/44",
                        SVN_USER, SVN_PASS)));

        SvnUtils.addFile(repository,
                "sampledir/mm/66",
                "f".getBytes(),
                "test comment");
        
        CommitFileInfo[] contents = SvnUtils.getMultiRepositoryContents(
                SVN_USER, SVN_PASS, SVN_URL,
                new String[] { "test", "test2/tt", "sampledir/mm/44", "sampledir/mm/66" },
                null
        );
        Assert.assertEquals(4, contents.length);
        Assert.assertTrue(Arrays.equals("abcde".getBytes(), contents[0].getContents()));
        Assert.assertTrue(Arrays.equals("ABCDE".getBytes(), contents[1].getContents()));
        Assert.assertTrue(Arrays.equals("fffff".getBytes(), contents[2].getContents()));
        Assert.assertTrue(Arrays.equals("f".getBytes(), contents[3].getContents()));
        
        Assert.assertNotNull(SvnUtils.getSvnInfo(SVN_URL + "test", SVN_USER, SVN_PASS));
        try {
            SvnUtils.getSvnInfo(SVN_URL + "dummy", SVN_USER, SVN_PASS);
            Assert.fail("存在しないSVNパスを指定したのにエラーが発生しませんでした。");
        } catch (SVNException e) {
            // success
        }

        SvnUtils.addDir(repository,
                "dir1",
                "test comment");

        SvnUtils.addDir(repository,
                "samplefir/mm/77",
                "test comment");

        SvnUtils.modifyFile(repository,
                "test2/tt",
                "000ABCDE111".getBytes(),
                "test comment add");
        
        Assert.assertTrue(Arrays.equals("000ABCDE111".getBytes(), 
                SvnUtils.getRepositoryContents(SVN_URL + "test2/tt",
                        SVN_USER, SVN_PASS)));

        SvnUtils.modifyMultiFile(repository,
                new CommitFileInfo[] {
                    new CommitFileInfo("test", "ABCDE".getBytes(), null),
                    new CommitFileInfo("test2/tt", "ABCDEkkkk".getBytes(), null),
                },
                "test comment multi");

    }

    @Test
    public void testAddMultiFile() throws Exception {
        
        SVNRepository repository = SvnUtils.getRepository(SVN_URL, SVN_USER, SVN_PASS);
        
        CommitFileInfo[] infos = new CommitFileInfo[] {
                new CommitFileInfo("abc", "abc".getBytes(), null),
                new CommitFileInfo("def", "abc".getBytes(), null),
                new CommitFileInfo("ghi", "abc".getBytes(), null),
        };
        SvnUtils.addMultiFile(repository, infos, "");
        
        infos = new CommitFileInfo[] {
                new CommitFileInfo("zzz", "abc".getBytes(), null),
                new CommitFileInfo("dir1/abc", "abc".getBytes(), null),
                new CommitFileInfo("dir1/def", "abc".getBytes(), null),
                new CommitFileInfo("dir2/abc", "abc".getBytes(), null),
                new CommitFileInfo("dir2/def", "abc".getBytes(), null),
        };

        SvnUtils.addMultiFile(repository, infos, "");
        
    }

}
