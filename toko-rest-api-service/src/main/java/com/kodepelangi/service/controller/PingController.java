package com.kodepelangi.service.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Ping Servlet Controller
 * @author Raka Teja
 */

@WebServlet(
        name = "PingServletController",
        description = "Test Servlet is work",
        urlPatterns = {"/ping"}
)
public class PingController extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter writer = response.getWriter();
        writer.println("<html>Pong</html>");
        writer.flush();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }

}
