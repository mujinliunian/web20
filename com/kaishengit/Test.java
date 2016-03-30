package com.kaishengit;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.entity.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

        FileInputStream inputStream = new FileInputStream("D:/data.xls");
        Workbook workbook =  new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for(int i = 0;i <= sheet.getLastRowNum();i++) {
            Row row = sheet.getRow(i);
            for(int j = 0;j < row.getLastCellNum();j++) {
                Cell cell = row.getCell(j);
                Object value = null;
                if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    value = cell.getStringCellValue();
                } else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    value = cell.getNumericCellValue();
                }
                System.out.print(value+"\t");
            }
            System.out.println();
        }












//        Workbook workbook = new HSSFWorkbook();
//
//        String sheetName = WorkbookUtil.createSafeSheetName("第一季度数据/第一部分");
//        Sheet sheet = workbook.createSheet(sheetName);
//
//        Row row0 = sheet.createRow(0);
//        Cell cell0 = row0.createCell(0);
//        cell0.setCellValue("产品名称");
//        row0.createCell(1).setCellValue("价格");
//        row0.createCell(2).setCellValue("数量");
//        row0.createCell(3).setCellValue("产地");
//
//        List<Product> productList = new ProductDao().findAll();
//        for(int i = 0;i < productList.size();i++) {
//            Product prod = productList.get(i);
//            Row tempRow = sheet.createRow(i+1);
//            tempRow.createCell(0).setCellValue(prod.getProdname());
//            tempRow.createCell(1).setCellValue(prod.getProdprice());
//            tempRow.createCell(2).setCellValue(prod.getProdnum());
//            tempRow.createCell(3).setCellValue(prod.getProdaddress());
//        }
//
//
//        FileOutputStream outputStream = new FileOutputStream("D:/data.xls");
//        workbook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();

    }
}
