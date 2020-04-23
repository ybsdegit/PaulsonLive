package com.ybs;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.*;
import sun.misc.Cleaner;

/**
 * ExcelWriteTest
 *
 * @author Paulson
 * @date 2020/4/22 19:11
 */
public class ExcelWriteTest {

    String PATH = "D:\\Users\\ybsde\\IdeaProjects\\PaulsonLive\\ybs-poi";

    @Test
    public void testWrite03() throws IOException {
        // 1、创建工作薄 03版本
        Workbook workbook = new HSSFWorkbook();
        // 2、创建一个工作表
        Sheet sheet = workbook.createSheet("狂神观众统计表");
        // 3、创建行
        Row row1 = sheet.createRow(0);
        // 4、创建单元格
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("今日新增观众");

        Cell cell2 = row1.createCell(1);
        cell2.setCellValue("666");

        // 第二行
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        Cell cell22 = row2.createCell(1);
        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(time);

        // 生成一张表（IO） 流  03版本 xls
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "统计表1.xls");
        workbook.write(fileOutputStream);

        // 关闭流
        fileOutputStream.close();
        System.out.println("文件生成完毕");
    }

    @Test
    public void testWrite07() throws IOException {
        // 1、创建工作薄 03版本
        Workbook workbook = new XSSFWorkbook();
        // 2、创建一个工作表
        Sheet sheet = workbook.createSheet("狂神观众统计表");
        // 3、创建行
        Row row1 = sheet.createRow(0);
        // 4、创建单元格
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("今日新增观众");

        Cell cell2 = row1.createCell(1);
        cell2.setCellValue("666");

        // 第二行
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        Cell cell22 = row2.createCell(1);
        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(time);

        // 生成一张表（IO） 流  03版本 xls
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "统计表2.xlsx");
        workbook.write(fileOutputStream);

        // 关闭流
        fileOutputStream.close();
        System.out.println("文件生成完毕");
    }

    @Test
    public void testWrite03BigData() throws IOException {
        long begin = System.currentTimeMillis();

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++){
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(rowNum);
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "统计表03大文件.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("文件生成完毕");

        long end = System.currentTimeMillis();

        System.out.println((double)(end-begin)/1000);
    }

    @Test
    public void testWrite07BigData() throws IOException {
        // 耗时较长！ 优化，使用缓存
        long begin = System.currentTimeMillis();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        for (int rowNum = 0; rowNum < 100000; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++){
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(rowNum);
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "统计表07大文件.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("文件生成完毕");

        long end = System.currentTimeMillis();

        System.out.println((double)(end-begin)/1000);
    }

    @Test
    public void testWrite07BigDataS() throws IOException {
        // 使用缓存
        long begin = System.currentTimeMillis();

        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        for (int rowNum = 0; rowNum < 100000; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++){
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(rowNum);
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "统计表07大文件加速.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
       ((SXSSFWorkbook) workbook).dispose();
        System.out.println("文件生成完毕");

        long end = System.currentTimeMillis();

        System.out.println((double)(end-begin)/1000);
    }


}
