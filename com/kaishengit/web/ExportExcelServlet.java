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

public class ExportExcelServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition","attachment;fileName=\"data.xls\"");

        Workbook workbook = new HSSFWorkbook();

        String sheetName = WorkbookUtil.createSafeSheetName("第一季度数据/第一部分");
        Sheet sheet = workbook.createSheet(sheetName);

        Row row0 = sheet.createRow(0);
        Cell cell0 = row0.createCell(0);
        cell0.setCellValue("产品名称");
        row0.createCell(1).setCellValue("价格");
        row0.createCell(2).setCellValue("数量");
        row0.createCell(3).setCellValue("产地");

        List<Product> productList = new ProductDao().findAll();
        for(int i = 0;i < productList.size();i++) {
            Product prod = productList.get(i);
            Row tempRow = sheet.createRow(i+1);
            tempRow.createCell(0).setCellValue(prod.getProdname());
            tempRow.createCell(1).setCellValue(prod.getProdprice());
            tempRow.createCell(2).setCellValue(prod.getProdnum());
            tempRow.createCell(3).setCellValue(prod.getProdaddress());
        }

        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }
}
