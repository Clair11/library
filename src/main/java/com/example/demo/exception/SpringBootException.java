package com.example.demo.exception;

import com.example.demo.constant.CommonCode;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 16:07 2018/5/4
 * @Modified By:
 */

public class SpringBootException extends RuntimeException{
    private String code;

    public SpringBootException(String code,String mesg){
        super(mesg);
        this.code = code;
    }

    public SpringBootException(CommonCode commonCode){
        super(commonCode.getMesg());
        this.code = commonCode.getCode();
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
