/*
 * Created 2006/05/26
 * Copyright (C) 2003-2006  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of Limy Eclipse Plugin.
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * プロセス関連のユーティリティクラスです。
 * @author Naoki Iwami
 */
public final class ProcessUtils {

    /**
     * private constructor
     */
    private ProcessUtils() {
        // empty
    }

    /**
     * プログラムを実行します。
     * @param execDir 実行ディレクトリ
     * @param out 出力先
     * @param args 実行パラメータ
     * @return 実行
     * @throws IOException I/O例外
     */
    public static int execProgram(File execDir, Writer out, String... args)
            throws IOException {
        
        StringBuilder buff = new StringBuilder();
        for (String arg : args) {
            buff.append(arg).append(' ');
        }
        ProcessBuilder builder = new ProcessBuilder(args);
        builder.directory(execDir);
        builder.environment().put("ANT_OPTS", "-Xmx256M"); // pmd-cpd対策
        
        builder.redirectErrorStream(true);
        Process process = builder.start();
        
        buff.setLength(0);
        BufferedInputStream stream = new BufferedInputStream(process.getInputStream());
        try {
            return waitEndProcess(out, process, stream);
        } finally {
            stream.close();
        }
    }
    
    // ------------------------ Private Methods

    /**
     * プロセスが終了するまで待機します。
     * @param out プロセスの実行結果（標準出力およびエラー出力）の出力先
     * @param process プロセス
     * @param stream プロセスの入力ストリーム（標準出力およびエラー出力）
     * @return プロセスの実行結果
     * @throws IOException I/O例外
     */
    private static int waitEndProcess(Writer out, Process process, BufferedInputStream stream)
            throws IOException {
        
        int exitValue = -1;

        while (true) {
            try {
                Thread.sleep(100);
                
                while (true) {
                    int size = stream.available();
                    if (size <= 0) {
                        break;
                    }
                    byte[] bs = new byte[size];
                    stream.read(bs, 0, size);
                    if (out != null) {
                        out.write(new String(bs));
                    }
                }
                exitValue = process.exitValue();
                break;
            } catch (IllegalThreadStateException e) {
                // empty
            } catch (InterruptedException e) {
                // do nothing
            }
        }
        return exitValue;
    }
    
}
