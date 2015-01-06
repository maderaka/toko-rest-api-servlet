package com.kodepelangi.app;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

/**
 * @author Raka Teja <rakatejaa@gmail.com>
 */

@WebFilter(filterName="Filter",urlPatterns = {"/*"})

public class Filter implements javax.servlet.Filter {

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        this.request = (HttpServletRequest) servletRequest;
        this.response = (HttpServletResponse) servletResponse;

        try {
            String authHeader = this.request.getHeader("Authorization");
            String encodedCred = authHeader.substring(6);

            byte[] plainCred = DatatypeConverter.parseBase64Binary(encodedCred);
            String userPass = new String(plainCred);

            int pos = userPass.indexOf(":");
            String username = userPass.substring(0,pos);
            String password = userPass.substring(pos+1);

            if("admin".equals(username) && "secret".equals(password)){
                this.request.setAttribute("user",username);
                filterChain.doFilter(this.request, this.response);
            }else{
                this.response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }catch (NullPointerException e){
            this.response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }catch (Exception e){
            this.response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public void destroy() {

    }
}
