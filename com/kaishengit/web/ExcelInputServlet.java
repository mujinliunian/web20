package com.kaishengit.web;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.entity.Product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class ExcelInputServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File temPath = new File("F:/down");
        if (ServletFileUpload.isMultipartContent(request)){
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            diskFileItemFactory.setRepository(temPath);
            diskFileItemFactory.setSizeThreshold(1024);

            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            try {
                List<FileItem> fileItemList = servletFileUpload.parseRequest(request);
                for (FileItem item : fileItemList){
                    if(!item.isFormField()){
                        InputStream inputStream = item.getInputStream();
                        readExcel(inputStream);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("/home.do");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/import.jsp").forward(request,response);
    }

    private static void readExcel(InputStream inputStream) throws IOException {
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        ProductDao productDao = new ProductDao();

        for(int i = 1; i<=sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);
            Product prod = new Product();
            for(int j =  0 ; j<row.getLastCellNum();j++){
                Cell cell = row.getCell(j);
                if(j==0){
                    prod.setProdname(cell.getStringCellValue());
                }else if(j==1){
                    prod.setProdprice(new Double(cell.getNumericCellValue()).floatValue());
                }else  if(j==2){
                    prod.setProdnum(new Double(cell.getNumericCellValue()).intValue());
                }else if(j==3){
                    prod.setProdaddress(cell.getStringCellValue());
                }
            }
            productDao.save(prod);

        }

    }
}
