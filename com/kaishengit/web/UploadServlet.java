package com.kaishengit.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = null;
        String cardNum = null;




        //文件上传的路径
        File uploadPath = new File("D:/upload");
        //判断如果路径不存在，则创建
        if(!uploadPath.exists()) {
            uploadPath.mkdir();
        }

        //临时文件夹
        File tempPath = new File("D:/temp");
        if(!tempPath.exists()) {
            tempPath.mkdir();
        }

        //判断表单是否将enctype属性设置为multipart/form-data
        if(ServletFileUpload.isMultipartContent(request)) {

            DiskFileItemFactory itemFactory = new DiskFileItemFactory();
            //设置缓冲区
            itemFactory.setSizeThreshold(1024*1024);
            //设置临时文件夹
            itemFactory.setRepository(tempPath);

            ServletFileUpload servletFileUpload = new ServletFileUpload(itemFactory);
            //设置文件的最大体积
            servletFileUpload.setFileSizeMax(1024*1024*10);
            try {
                //解析表单元素，将表单元素分为文件元素和非文件元素
                List<FileItem> fileItemList = servletFileUpload.parseRequest(request);
                for(FileItem fileItem : fileItemList) {
                    if(fileItem.isFormField()) {
                        //获取表单元素的name属性值
                        String fieldName = fileItem.getFieldName();
                        if("name".equals(fieldName)) {
                            //获取表单元素的值
                            name = fileItem.getString("UTF-8");
                        } else if("cardnum".equals(fieldName)) {
                            cardNum = fileItem.getString();
                        }
                    } else {
                        //文件元素

                        //获取文件的mime头
                        String contentType = fileItem.getContentType();
                        //获取文件的真实名称
                        String fileName = fileItem.getName();//
                        String extName = fileName.substring(fileName.lastIndexOf("."));
                        //获取文件的大小（字节）
                        long fileSize = fileItem.getSize();

                        InputStream inputStream = fileItem.getInputStream();
                        fileName = UUID.randomUUID().toString() + extName;
                        FileOutputStream outputStream = new FileOutputStream(new File(uploadPath,fileName));
                        IOUtils.copy(inputStream,outputStream);
                        /*byte[] buffer = new byte[1024];
                        int len = -1;
                        while((len = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer,0,len);
                        }*/

                        outputStream.flush();
                        outputStream.close();
                        inputStream.close();


                    }
                }


                System.out.println("Name:" + name + " CardNum:" + cardNum);


            } catch (FileUploadException e) {
                e.printStackTrace();
            }


        } else {
            throw new RuntimeException("表单设置错误");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(request,response);
    }
}
