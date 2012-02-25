package org.limy.common.util;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.limy.common.ftp.FtpInfo;

public final class FtpUtilsTest {

    @Test
    public void testConnectFtp() throws Exception {
        FTPClient client = new FTPClient();
        FtpInfo info = new FtpInfo();
        
        Properties prop = new Properties();
        prop.load(getClass().getResourceAsStream("FtpUtilsTest.properties"));
        info.setServerAddress(prop.getProperty("server"));
        info.setUserName(prop.getProperty("user"));
        info.setPassword(prop.getProperty("password"));
        info.setPath(prop.getProperty("path"));

        FtpUtils.connectFtp(client, info);
        
        InputStream stream = ClassLoader.getSystemResourceAsStream("resources/test.vm");
        assertTrue(FtpUtils.uploadFileFtp(client, info.getPath(), "__sample__/__sub__/index", stream));
        assertTrue(FtpUtils.deleteFile(client, "__sample__/__sub__/index"));
        assertTrue(FtpUtils.deleteDir(client, "__sample__/__sub__"));
        assertTrue(FtpUtils.deleteDir(client, "__sample__"));
        stream.close();

        stream = ClassLoader.getSystemResourceAsStream("resources/test.vm");
        assertTrue(FtpUtils.uploadFileFtp(client, info.getPath(), "__sample__", stream));
        assertTrue(FtpUtils.deleteFile(client, "__sample__"));
        stream.close();

        FtpUtils.disconnect(client);
    }

}
