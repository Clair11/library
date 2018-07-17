package com.example.demo.handler;

import com.example.demo.ResponseVo;
import com.example.demo.exception.SpringBootException;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 16:24 2018/5/4
 * @Modified By:
 */
@ControllerAdvice //定义全局异常处理类
public class DefaultExceptionHandler {

    @ExceptionHandler(SpringBootException.class) //声明异常处理方法
    @ResponseBody
    public String getException(NativeWebRequest request,Exception e){
        ResponseVo responseVo = new ResponseVo();
        String result = "";
        if(e instanceof SpringBootException){
            responseVo.setCode(((SpringBootException) e).getCode());
            responseVo.setMesg(((SpringBootException)e).getMessage());
            result = responseVo.toString();
        }
        return result;
    }

    @ExceptionHandler(AuthenticationException.class) //声明异常处理方法
    @ResponseBody
    public String getRealmException(NativeWebRequest request,Exception e){
        ResponseVo responseVo = new ResponseVo();
        String result = "";
        if(e instanceof AuthenticationException){
            responseVo.setMesg(((AuthenticationException)e).getMessage());
            result = responseVo.toString();
        }
        return result;
    }


}
