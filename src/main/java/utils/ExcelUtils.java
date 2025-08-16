package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
    public static Object[][] getSheetData(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            List<Object[]> data = new ArrayList<>();
            for (int r = 1; r < rowCount; r++) { // skip header row
                Row row = sheet.getRow(r);
                Object[] rowData = new Object[colCount];
                for (int c = 0; c < colCount; c++) {
                    Cell cell = row.getCell(c);
                    rowData[c] = cell == null ? "" : cell.toString();
                }
                data.add(rowData);
            }
            Object[][] array = new Object[data.size()][colCount];
            for (int i = 0; i < data.size(); i++) array[i] = data.get(i);
            return array;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
