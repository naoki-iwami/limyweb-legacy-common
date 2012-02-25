/*
 * Created 2007/02/24
 * Copyright (C) 2003-2007  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of Limyweb.
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
package org.limy.common.graph;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.JFreeChart;

import com.keypoint.PngEncoder;

/**
 * グラフ（JFreeChart）関連のユーティリティクラスです。
 * @author Naoki Iwami
 */
public final class GraphUtils {
    
    /**
     * private constructor
     */
    private GraphUtils() { }
    
    /**
     * PNG形式でチャートをファイル出力します。
     * @param chart チャート
     * @param pngFile 出力pngファイル
     * @param width イメージwidth
     * @param height イメージheight
     * @throws IOException I/O例外
     */
    public static void writeImagePng(JFreeChart chart, File pngFile, int width, int height)
            throws IOException {

        BufferedImage image = chart.createBufferedImage(width, height);
        FileOutputStream outStream = new FileOutputStream(pngFile);
        try {
            PngEncoder pngEncoder = new PngEncoder(image);
            pngEncoder.setCompressionLevel(9);
            outStream.write(pngEncoder.pngEncode());
        } finally {
            outStream.close();
        }
    }

}
