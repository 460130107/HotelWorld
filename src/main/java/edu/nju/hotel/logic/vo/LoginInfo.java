package edu.nju.hotel.logic.vo;

import javax.lang.model.element.Name;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
public class LoginInfo {
    private int id;
    private String psw;
    private String errorMsg="";
    private String type;
    private String name;

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
