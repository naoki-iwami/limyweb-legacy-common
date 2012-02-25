package org.limy.common.util;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

public class ProcessUtilsTest {

    @Test
    public void testExecProgram() throws IOException {
        
        StringWriter out = new StringWriter();
        ProcessUtils.execProgram(new File("."), out, "java", "-version");
        System.out.println(out);
    }

}
