package com.kaishengit.web;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.entity.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExportExcelServlets extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition","attachment;fileName=\"excel.xls\"");

        Workbook workbook = new HSSFWorkbook();
        String saftName = WorkbookUtil.createSafeSheetName("da/da");
        Sheet sheet = workbook.createSheet(saftName);
        Row row0 =sheet.createRow(0);
        Cell cell0 =row0.createCell(0);
        cell0.setCellValue("产品名称");
        row0.createCell(1).setCellValue("价格");
        row0.createCell(2).setCellValue("数量");
        row0.createCell(3).setCellValue("产地");

        List<Product> productList = new ProductDao().findAll();
        for (int i=0 ;i<productList.size();i++){
            Product product = productList.get(i);
            Row row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(product.getProdname());
            row.createCell(1).setCellValue(product.getProdprice());
            row.createCell(2).setCellValue(product.getProdnum());
            row.createCell(3).setCellValue(product.getProdaddress());
        }
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
