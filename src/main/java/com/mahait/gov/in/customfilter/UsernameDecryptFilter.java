package com.mahait.gov.in.customfilter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mahait.gov.in.crypto.AESUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UsernameDecryptFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if ("/login".equals(request.getServletPath()) && "POST".equalsIgnoreCase(request.getMethod())) {
            HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request) {
                @Override
                public String getParameter(String name) {
                    if ("password".equals(name)) {
                        String password = super.getParameter(name);
                        if (password != null && password.length()==120) {
                          //  return username + "_AST";
                          //  return "ifms123";
                        	
                        	  AESUtil aesUtil = new AESUtil();

                      		password = aesUtil.decrypt("MESSAGE", password);
                        	
                            return password;
                            
                        }
                    }
                    return super.getParameter(name);
                }
            };

            filterChain.doFilter(wrappedRequest, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
