package org.limy.common.web;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.limy.common.LimyException;

public class SpamCheckerTest {

    @Test
    public void testCheckNotEmpty() throws LimyException {
        SpamChecker.checkNotEmpty("123");
        
        try {
            SpamChecker.checkNotEmpty("");
            fail("uncatched exception.");
        } catch (LimyException e) {
            // success
        }

        try {
            SpamChecker.checkNotEmpty(null);
            fail("uncatched exception.");
        } catch (LimyException e) {
            // success
        }

    }

    @Test
    public void testCheckJapanese() throws LimyException {
        SpamChecker.checkJapanese("あいう");
        
        try {
            SpamChecker.checkJapanese("");
            fail("uncatched exception.");
        } catch (LimyException e) {
            // success
        }

        try {
            SpamChecker.checkJapanese("abcde");
            fail("uncatched exception.");
        } catch (LimyException e) {
            // success
        }

        try {
            SpamChecker.checkJapanese(null);
            fail("uncatched exception.");
        } catch (LimyException e) {
            // success
        }

    }

}
