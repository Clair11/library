package com.example.demo.util;

import com.example.demo.ResponseVo;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 15:25 2018/5/4
 * @Modified By:
 */

public class ResponseVoUtil{
    public static String success(){
        ResponseVo responseVo = new ResponseVo();
        return responseVo.toString();
    }

    public static String success(Object o){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setData(o);
        return responseVo.toString();
    }

    public static String error(String code,String mesg){
        ResponseVo responseVo = new ResponseVo(code,mesg);
        return responseVo.toString();

    }
}
