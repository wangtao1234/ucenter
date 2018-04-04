package com.example.ucenter.Filter;


import com.example.ucenter.utils.ResultMsg;
import com.example.ucenter.utils.ResultStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.misc.BASE64Decoder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2018/4/4.
 */

@SuppressWarnings("restriction")
public class HTTPBasicAuthorizeAttribute implements Filter {
    private static String Name = "test";
    private static String Password = "test";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        ResultStatusCode resultStatusCode = checkHTTPBasicAuthorize(servletRequest);
        if(resultStatusCode != ResultStatusCode.OK){
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json; charset=utf-8");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ObjectMapper mapper = new ObjectMapper();
            ResultMsg resultMsg = new ResultMsg(ResultStatusCode.PERMISSION_DENIED.getCode(),
                    ResultStatusCode.PERMISSION_DENIED.getMsg(), null);
            httpResponse.getWriter().write(mapper.writeValueAsString(resultMsg));
            return;
        }
        else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private ResultStatusCode checkHTTPBasicAuthorize(ServletRequest  request){
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            String auth = httpServletRequest.getHeader("Authorization");
            String HeadStr = auth.substring(0,5).toLowerCase();
            if(HeadStr.compareTo("basic") == 0){
                auth = auth.substring(6, auth.length());
                String decodedAuth = getFromBASE64(auth);
                if(decodedAuth != null){
                    String[] UserArray = decodedAuth.split(":");
                    if(UserArray != null && UserArray.length == 2){
                        if(UserArray[0].compareTo(Name) == 0 && UserArray[1].compareTo(Password) == 0){
                             return ResultStatusCode.OK;
                        }
                    }
                }
            }
            return ResultStatusCode.PERMISSION_DENIED;
        } catch (Exception e) {
            return ResultStatusCode.PERMISSION_DENIED;
        }
    }

    @Override
    public void destroy() {

    }

    private  String getFromBASE64(String s){
        if(s == null){
            return  null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (IOException e) {
            return null;
        }
    }
}
