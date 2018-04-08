package com.example.ucenter.Filter;


import com.example.ucenter.utils.JwtHelper;
import com.example.ucenter.utils.ResultMsg;
import com.example.ucenter.utils.ResultStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import sun.misc.BASE64Decoder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2018/4/2.
 */
@SuppressWarnings("restriction")
public class HTTPBearerAuthorizeAttribute implements Filter {

    private static String Name ="test";
    private static String password ="test";

    @Value("${audience.base64Secret}")
    private String base64Secret;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                filterConfig.getServletContext());

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        ResultMsg resultMsg;
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        String auth = httpServletRequest.getHeader("Authorization");
        if((auth !=null) && (auth.length() > 7)){
             String HeaderStr = auth.substring(0,6).toLowerCase();
             if(HeaderStr.compareTo("bearer") ==0){
                 auth = auth.substring(7,auth.length());
                 if(JwtHelper.parseJWT(auth,base64Secret) !=null){
                     filterChain.doFilter(servletRequest,servletResponse);
                     return;
                 }
             }
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper mapper = new ObjectMapper();
        resultMsg = new ResultMsg(ResultStatusCode.INVALID_TOKEN.getCode(),
                ResultStatusCode.INVALID_TOKEN.getMsg(),null);
        response.getWriter().write(mapper.writeValueAsString(resultMsg));
        return;
    }

    @Override
    public void destroy() {

    }

    private ResultStatusCode checkHTTPBasicAuthorize(ServletRequest request){
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            String auth = httpServletRequest.getHeader("Authorization");
            if( (auth !=null) && (auth.length() > 6)){
                 String HeadStr = auth.substring(0,5).toLowerCase();
                 if(HeadStr.compareTo("basic") == 0){
                       auth = auth.substring(6,auth.length());
                       String decodeAuth = getFromBASE64(auth);
                       if(decodeAuth != null){
                            String[] UserAarray  = decodeAuth.split(":");
                            if(UserAarray !=null && UserAarray.length ==2){
                                   if(UserAarray[0].compareTo(Name)==0
                                           && UserAarray[1].compareTo(password)==0){
                                          return ResultStatusCode.OK;
                                   }
                            }
                       }
                 }
            }
            return ResultStatusCode.PERMISSION_DENIED;
        } catch (Exception e) {
            return ResultStatusCode.PERMISSION_DENIED;
        }
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
            return  null;
        }
    }
}
