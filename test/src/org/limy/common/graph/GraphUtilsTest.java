package org.limy.common.graph;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.Assert;
import org.junit.Test;

public class GraphUtilsTest {

    @Test
    public void testWriteImagePng() throws IOException {
        
        File pngFile = File.createTempFile("limyweb-common", "png");
        pngFile.deleteOnExit();
        
        JFreeChart chart = ChartFactory.createBarChart(
                "", "", "", new DefaultCategoryDataset(),
                PlotOrientation.HORIZONTAL, false, false, false);
        GraphUtils.writeImagePng(chart, pngFile, 200, 200);
        Assert.assertTrue(pngFile.exists());
        System.out.println(pngFile);
        
    }

}
