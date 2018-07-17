package com.example.demo.constant;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 15:19 2018/5/4
 * @Modified By:
 */

public enum CommonCode {
    SUCCESS("000","操作成功"),
    PARAMERROR("001","参数有误"),
    BORROWERROR("002","借阅失败，该书已被借走或已卖掉"),
    BACKERROR("003","还书异常"),
    USERPASSERROR("004","用户名或者密码错误"),
    ISEMPTY("005","没有用户数据");

    private String code;
    private String mesg;

    CommonCode() {
    }

    CommonCode(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }
}
