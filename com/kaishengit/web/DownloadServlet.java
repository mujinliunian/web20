package com.kaishengit.web;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileName = request.getParameter("file");
        String d = request.getParameter("d");

        if(fileName == null || "".equals(fileName)) {
            response.sendError(404);
        } else {
            File file = new File("D:/upload",fileName);
            if(!file.exists()) {
                response.sendError(404);
            } else {
                response.setContentType("application/octet-stream");
                if("true".equals(d)) {
                    //让浏览器弹出下载对话框
                    response.addHeader("Content-Disposition","attachment;fileName=\""+fileName+"\"");
                    //让浏览器出现合理的进度条
                    response.setContentLength(new Long(file.length()).intValue());
                }



                FileInputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = response.getOutputStream();

                IOUtils.copy(inputStream,outputStream);

                outputStream.flush();
                outputStream.close();
                inputStream.close();



            }
        }


    }
}
