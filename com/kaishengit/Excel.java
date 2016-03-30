package com.kaishengit;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.entity.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;

import java.io.FileOutputStream;
import java.util.List;

public class Excel {
    public static void main(String[] args) throws Exception{
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("first page");
        String  saftName = WorkbookUtil.createSafeSheetName("second / page");
        Sheet sheet1 = workbook.createSheet(saftName);
        Row row0 = sheet.createRow(0);
        Cell cell0 =row0.createCell(0);
        cell0.setCellValue("产品名称");
        row0.createCell(1).setCellValue("价格");
        row0.createCell(2).setCellValue("数量");
        row0.createCell(3).setCellValue("产地");

        List<Product> productList = new ProductDao().findAll();
        for(int i =0;i<productList.size();i++){
            Product prod = productList.get(i);
            Row temp = sheet.createRow(i+1);
            temp.createCell(0).setCellValue(prod.getProdname());
            temp.createCell(1).setCellValue(prod.getProdprice());
            temp.createCell(2).setCellValue(prod.getProdnum());
            temp.createCell(3).setCellValue(prod.getProdaddress());
        }


        FileOutputStream outputStream =  new FileOutputStream("F:/data.xls");
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
