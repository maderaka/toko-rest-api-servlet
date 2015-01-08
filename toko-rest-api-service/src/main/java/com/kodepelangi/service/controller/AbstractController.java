package com.kodepelangi.service.controller;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author rakateja on 12/26/14.
 */
abstract class AbstractController extends HttpServlet{
    private String inputBodyContent;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Gson gson;

    public AbstractController(){
        this.setGson(new Gson());
    }

    private void buildInputBodyContent(){
        try{
            BufferedReader reader = this.getRequest().getReader();
            StringBuilder builder = new StringBuilder();
            String aux;

            while((aux = reader.readLine()) != null){
                builder.append(aux);
            }
            this.inputBodyContent = builder.toString();
        }catch (IOException e){

        }
    }

    public String getInputBodyContent(){
        this.buildInputBodyContent();
        return this.inputBodyContent;
    }

    /**
     *
     * @param data JsonElement
     */
    public void responseJson(Object data){
        String json = this.getGson().toJson(data);
        this.response.setContentType("application/json");
        try {
            PrintWriter writer = this.response.getWriter();
            writer.write(json);
            writer.flush();
        }catch (IOException e){

        }
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setRequestResponse(HttpServletRequest request, HttpServletResponse response){
        this.setRequest(request);
        this.setResponse(response);
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public abstract void doGet(HttpServletRequest request, HttpServletResponse response);

    public abstract void doPost(HttpServletRequest request, HttpServletResponse response);

    public abstract void doPut(HttpServletRequest request, HttpServletResponse response);

    public abstract void doDelete(HttpServletRequest request, HttpServletResponse response);
}
