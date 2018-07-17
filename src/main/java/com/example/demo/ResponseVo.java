package com.example.demo;

import com.example.demo.constant.CommonCode;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 15:16 2018/5/4
 * @Modified By:
 */

public class ResponseVo {
    private String mesg = CommonCode.SUCCESS.getMesg(); //是否成功
    private Object data;
    private String code = CommonCode.SUCCESS.getCode();  //状态码

    public ResponseVo() {
    }

    public ResponseVo(String mesg, String code) {
        this.mesg = mesg;
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        if(data == null){
            return "ResponseVo{" +
                    "mesg='" + mesg + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }else{
            return "ResponseVo{" +
                    "mesg='" + mesg + '\'' +
                    ", data=" + data.toString() +
                    ", code='" + code + '\'' +
                    '}';
        }

    }
}
