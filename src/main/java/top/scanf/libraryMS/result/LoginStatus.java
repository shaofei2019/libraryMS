package top.scanf.libraryMS.result;

import java.io.Serializable;

public class LoginStatus implements Serializable {
    private Integer code;
    private String status;
    private String msg;
    private String token;

    public LoginStatus() {
    }

    public LoginStatus(Integer code, String status, String msg, String token) {
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.token = token;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
