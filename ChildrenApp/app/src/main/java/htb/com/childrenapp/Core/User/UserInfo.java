package htb.com.childrenapp.Core.User;

import java.io.Serializable;

import htb.com.childrenapp.CAApplication;

/**
 * 用户信息
 * Created by weidong_wu on 15/11/14.
 * 邮箱:wwdhao163@163.com
 */
public class UserInfo implements Serializable{

    private String phone;
    private String username;
    private int role;
    private String avatar;
    //private String verificationToken;
    private String id;

    private String token;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

//    public String getVerificationToken() {
//        return verificationToken;
//    }
//
//    public void setVerificationToken(String verificationToken) {
//        this.verificationToken = verificationToken;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        CAApplication.tokenId = token;
        this.token = token;
    }
}
