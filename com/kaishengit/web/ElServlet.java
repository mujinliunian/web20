package com.kaishengit.web;

import com.kaishengit.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class ElServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setId(101);
        user.setUsername("Hanks");
        user.setPassword("123456");

        List<String> names = new ArrayList<>();
        names.add("n1");
        names.add("n2");
        names.add("n3");
        names.add("n4");
        names.add("n5");

        Map<String,Object> map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");

        /*Set<Map.Entry<String,Object>> entrySet = map.entrySet();
        for(Map.Entry<String,Object> entry : entrySet) {
            System.out.println(entry.getKey() + "->" + entry.getValue());;
        }*/


        /*Set<String> keys = map.keySet();
        for(String key : keys) {
            Object value = map.get(key);
            System.out.println(key + "->" + value);
        }*/



        request.setAttribute("nameList",names);
        request.setAttribute("data",map);




        request.setAttribute("message","<h3 style='color:red'>Hello,World</h3>");

        HttpSession session = request.getSession();
        session.setAttribute("user",user);

        String[] nameArray = {"tom","jack","zhangsan"};

        request.setAttribute("nameArray",nameArray);
        request.getRequestDispatcher("context.jsp").forward(request,response);
    }
}
