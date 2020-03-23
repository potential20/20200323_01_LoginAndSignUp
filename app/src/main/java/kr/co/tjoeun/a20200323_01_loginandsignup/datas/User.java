package kr.co.tjoeun.a20200323_01_loginandsignup.datas;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String loginId;
    private String name;
    private String phone;
    private String memo;

    public User() {
    }

    public User(int id, String loginId, String name, String phone, String memo) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.phone = phone;
        this.memo = memo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
