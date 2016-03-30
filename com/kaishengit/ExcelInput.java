package com.kaishengit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelInput {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("F:/data.xls");
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 0; i <=sheet.getLastRowNum() ; i++){
            Row row = sheet.getRow(i);
            for (int j = 0;j< row.getLastCellNum();j++){
                Cell cell = row.getCell(j);
                Object value = null;
                if(cell.getCellType() == cell.CELL_TYPE_STRING){
                    value=cell.getStringCellValue();
                }else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC){
                    value = cell.getNumericCellValue();
                }
                System.out.print(value+"\t");
            }
            System.out.println();
        }
    }
}
