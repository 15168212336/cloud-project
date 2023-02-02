package cn.com.zhuge.jiayou.core.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 报表导出工具类
 * @author 诸葛
 * @date 2022/10/28 16:47
 **/
public class POIUtils {

    /**
     * 创建excel
     * @return
     */
    public static Workbook createWorkbook() {
        return new HSSFWorkbook();
    }

    /**
     * 创建sheet表并设置表的名字
     * @param workbook  文件对象
     * @param name      表名
     */
    public static Sheet createSheet(Workbook workbook, String name) {
        return workbook.createSheet(name);
    }

    /**
     * 创建行
     * @param sheet 表对象
     * @param i     创建第几行
     * @return
     */
    public static Row createRow(Sheet sheet,int i) {
        return sheet.createRow(i);
    }

    /**
     * 创建行
     *
     * @param sheet 表对象
     * @param i     创建第几行
     * @return
     */
    public static Row createRow(Sheet sheet, CellStyle cellStyle, int i, int high) {
        if (sheet == null) {
            throw new RuntimeException("表对象为空");
        }
        Row row = sheet.createRow(i);
        if (cellStyle != null) {
            row.setRowStyle(cellStyle);
        }
        row.setHeightInPoints(high);
        return row;
    }

    /**
     * 创建列
     * @param row  行/单元格
     * @param i    创建第几列
     * @return
     */
    public static Cell createCell(Row row, int i) {
        return row.createCell(i);
    }

    /**
     * 创建列
     * @param row  行/单元格
     * @param i    创建第几列
     * @param cellValue    单元格值
     * @return
     */
    public static Cell createCell(Row row, int i,String cellValue) {
        Cell cell = row.createCell(i);
        cell.setCellValue(cellValue);
        return cell;
    }

    /**
     * 设置列
     * @param row  行/单元格
     * @param cellValues    单元格值
     */
    public static void setCell(Row row, String ...cellValues) {
        for (int i = 0; i < cellValues.length; i++) {
            createCell(row, i, cellValues[i]);
        }
    }

    /**
     * 设置列
     *
     * @param sheet      表对象
     * @param cellStyle  单元格样式
     * @param row_i      行号
     * @param cellValues 单元格值
     */
    public static void setCell(Sheet sheet, CellStyle cellStyle, int row_i, String... cellValues) {
        setCell(sheet, cellStyle, row_i, 15,true, cellValues);
    }

    /**
     * 设置列
     *
     * @param sheet      表对象
     * @param cellStyle  单元格样式
     * @param high      单元格高度
     * @param row_i      行号
     * @param isAutoSize  是否自动调整列宽
     * @param cellValues 单元格值
     */
    public static void setCell(Sheet sheet, CellStyle cellStyle, int row_i,int high,boolean isAutoSize, String... cellValues) {
        Row row = createRow(sheet, null, row_i, high);
        for (int i = 0; i < cellValues.length; i++) {
            sheet.autoSizeColumn(i,isAutoSize);
            createCell(row, i, cellStyle, cellValues[i]);
        }
    }


    /**
     * 创建列
     *
     * @param row  行/单元格
     * @param i    创建第几列
     * @param cellStyle    单元格样式
     * @param cellValue    单元格值
     * @return
     */
    public static Cell createCell(Row row, int i, CellStyle cellStyle, String cellValue) {

        Cell cell = row.createCell(i);
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
        cell.setCellValue(cellValue);
        return cell;
    }



    /**
     * 创建指定单元格
     * @param sheet 表名
     * @param row_i 行数
     * @param cell_i   列数
     * @return
     */
    public static Cell createUnitCell(Sheet sheet, int row_i, int cell_i) {
        return createCell(createRow(sheet, row_i), cell_i);
    }


    //-----------------------------------字体设置----------------------------------------//
    /**
     * 创建字体样式
     * @param workbook  文件对象
     * @return
     */
    public static Font createFont(Workbook workbook) {
        return workbook.createFont();
    }

    /**
     * 设置字体样式
     * @param font      字体对象
     * @param fontName  字体名字
     * @param size      字体大小
     * @param color     字体颜色
     * @param underline 下划线    Font.U_DOUBLE
     * @param isBold    是否加粗
     * @param isItalics 是否斜体
     * @return
     */
    public static Font setFont(Font font, String fontName, short size, HSSFColor.HSSFColorPredefined color, byte underline, boolean isBold, boolean isItalics) {
        font.setFontName(fontName);
        font.setFontHeightInPoints(size);
        font.setColor(color.getIndex());
        font.setUnderline(underline);
        font.setBold(isBold);
        font.setItalic(isItalics);
        return font;
    }

    /**
     * 创建字体样式
     *
     * @param workbook  文件对象
     * @param fontName  字体名字
     * @param size      字体大小
     * @param isBold    是否加粗
     * @return
     */
    public static Font createFont(Workbook workbook, String fontName, short size, boolean isBold) {
        return createFont(workbook, fontName, size, HSSFColor.HSSFColorPredefined.BLACK, Font.U_NONE, isBold, false);
    }

    /**
     * 创建字体样式
     *
     * @param workbook  文件对象
     * @param fontName  字体名字
     * @param size      字体大小
     * @param color     字体颜色
     * @param underline 下划线    Font.U_DOUBLE
     * @param isBold    是否加粗
     * @param isItalics 是否斜体
     * @return
     */
    public static Font createFont(Workbook workbook, String fontName, short size, HSSFColor.HSSFColorPredefined color, byte underline, boolean isBold, boolean isItalics) {
        Font font = createFont(workbook, fontName);
        font.setFontHeightInPoints(size);
        font.setColor(color.getIndex());
        font.setUnderline(underline);
        font.setBold(isBold);
        font.setItalic(isItalics);
        return font;
    }

    /**
     *  创建字体样式
     * @param workbook
     * @param fontName
     * @return
     */
    public static Font createFont(Workbook workbook,String fontName) {
        Font font = createFont(workbook);
        font.setFontName(fontName);
        return font;
    }



    //-----------------------------------分割线-------------------------------------//


    //-----------------------------------单元格样式设置-------------------------------------//

    /**
     * 创建单元格格式
     * @param workbook 文件对象
     * @return
     */
    public static CellStyle createCellStyle(Workbook workbook) {
        return workbook.createCellStyle();
    }

    /**
     * 创建标准单元格样式
     *
     * @param workbook        文件对象
     * @param font            字体对象
     * @param isUDCenter      是否水平居中
     * @param isLGCenter      是否垂直居中
     * @return
     */
    public static CellStyle createCellStyle(Workbook workbook, Font font, boolean isUDCenter, boolean isLGCenter) {
        return createCellStyle(workbook, font, isUDCenter, isLGCenter, false);
    }

    /**
     * 创建标准单元格样式
     *
     * @param workbook        文件对象
     * @param font            字体对象
     * @param isUDCenter      是否水平居中
     * @param isLGCenter      是否垂直居中
     * @param isBorder      是否包围边框
     * @return
     */
    public static CellStyle createCellStyle(Workbook workbook, Font font, boolean isUDCenter, boolean isLGCenter,boolean isBorder) {
        HorizontalAlignment horizontalAlignment = null;
        if (isUDCenter) {
            horizontalAlignment = HorizontalAlignment.CENTER;
        }
        VerticalAlignment verticalAlignment = null;
        if (isLGCenter) {
            verticalAlignment = VerticalAlignment.CENTER;
        }
        if (isBorder) {
            return createCellStyle(workbook, font, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, horizontalAlignment, verticalAlignment, null, null);
        } else {
            return createCellStyle(workbook, font, null, null, null, null, horizontalAlignment, verticalAlignment, null, null);
        }
    }

    /**
     * 创建单元格样式
     *
     * @param workbook        文件对象
     * @param font            字体对象
     * @param top             上边框
     * @param bottom          下边框
     * @param left            左边框
     * @param right           右边框
     * @param alignment       水平居中
     * @param vertical        垂直居中
     * @param color           单元格前景填充色
     * @param fillPatternType 单元格前景填充样式
     * @return
     */
    public static CellStyle createCellStyle(Workbook workbook, Font font, BorderStyle top, BorderStyle bottom, BorderStyle left, BorderStyle right, HorizontalAlignment alignment, VerticalAlignment vertical, IndexedColors color, FillPatternType fillPatternType) {
        if (workbook == null) {
            throw new RuntimeException("文件对象为空");
        }
        CellStyle cellStyle = workbook.createCellStyle();
        if (font != null) {
            cellStyle.setFont(font);
        }
        setBottom(cellStyle, top, bottom, left, right);
        if (alignment != null) {
            cellStyle.setAlignment(alignment);
        }
        if (vertical != null) {
            cellStyle.setVerticalAlignment(vertical);
        }
        if (color != null) {
            cellStyle.setFillForegroundColor(color.getIndex());
        }
        if (fillPatternType != null) {
            cellStyle.setFillPattern(fillPatternType);
        }
        return cellStyle;
    }

    /**
     *  单元格边框设置
     * @param cellStyle 单元格对象
     * @param top             上边框
     * @param bottom          下边框
     * @param left            左边框
     * @param right           右边框
     * @return
     */
    public static CellStyle setBottom(CellStyle cellStyle, BorderStyle top, BorderStyle bottom, BorderStyle left, BorderStyle right) {
        if (top != null) {
            cellStyle.setBorderTop(top);
        }
        if (bottom != null) {
            cellStyle.setBorderBottom(bottom);
        }
        if (left != null) {
            cellStyle.setBorderLeft(left);
        }
        if (right != null) {
            cellStyle.setBorderRight(right);
        }
        return cellStyle;
    }

    //-----------------------------------分割线-------------------------------------//
    //-----------------------------------导出-------------------------------------//
    public static void export(String path,String fileName,Workbook workbook) {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(path+fileName+".xls");
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
