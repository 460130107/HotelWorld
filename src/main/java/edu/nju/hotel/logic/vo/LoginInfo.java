package edu.nju.hotel.logic.vo;

import javax.lang.model.element.Name;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
public class LoginInfo {
    private int id;
    private String psw;
    private String error;
    private String type;

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
}
